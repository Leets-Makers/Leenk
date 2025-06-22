package leets.leenk.domain.notification.application.usecase;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.feed.domain.entity.LinkedUser;
import leets.leenk.domain.feed.domain.entity.Reaction;
import leets.leenk.domain.notification.application.dto.NotificationCountResponse;
import leets.leenk.domain.notification.application.dto.NotificationListResponse;
import leets.leenk.domain.notification.application.mapper.NotificationMapper;
import leets.leenk.domain.notification.application.mapper.NotificationResponseMapper;
import leets.leenk.domain.notification.application.service.FirstReactionNotificationSaveService;
import leets.leenk.domain.notification.application.service.NewFeedNotificationSaveService;
import leets.leenk.domain.notification.application.service.NotificationCountGetService;
import leets.leenk.domain.notification.application.service.NotificationGetService;
import leets.leenk.domain.notification.application.service.NotificationMarkReadService;
import leets.leenk.domain.notification.application.service.ReactionCountNotificationSaveService;
import leets.leenk.domain.notification.application.service.TagNotificationSaveService;
import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.notification.domain.entity.NotificationType;
import leets.leenk.domain.notification.domain.repository.NotificationRepository;
import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.entity.UserSetting;
import leets.leenk.domain.user.domain.service.UserGetService;
import leets.leenk.domain.user.domain.service.UserSettingGetService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationUsecase {
	private final NotificationGetService notificationGetService;
	private final NotificationCountGetService notificationCountGetService;

	private final NotificationMarkReadService notificationMarkReadService;
	private final NewFeedNotificationSaveService newFeedNotificationSaveService;
	private final ReactionCountNotificationSaveService reactionCountNotificationSaveService;
	private final TagNotificationSaveService tagNotificationSaveService;
	private final FirstReactionNotificationSaveService firstReactionNotificationSaveService;

	private final UserSettingGetService userSettingGetService;
	private final UserGetService userGetService;

	private final NotificationResponseMapper mapper;
	private final NotificationMapper notificationMapper;
	private final NotificationRepository notificationRepository;

	@Transactional(readOnly = true)
	public NotificationListResponse getNotifications(Long userId, int pageNumber, int pageSize){
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "updateDate"));
		Slice<Notification> notifications = notificationGetService.findRecentNotifications(userId, pageable);

		return mapper.toNotificationListResponse(notifications);
	}

	@Transactional(readOnly = true)
	public NotificationCountResponse getNotificationCount(long userId) {
		User user = userGetService.findById(userId);
		return mapper.toCountResponse(notificationCountGetService.getNotificationCount(user));
	}

	public void saveFirstReactionNotification(Reaction reaction){
		if(isFirstReactionDuplicated(reaction)){
			//  이미 해당 유저에 대한 알림이 존재하므로 중복 생성 방지
			return ;
		}
		Notification notification = notificationRepository.findFeedFirstReactionByFeedId(NotificationType.FEED_FIRST_REACTION,
				reaction.getFeed().getId())
			.orElseGet(()->notificationMapper.toFirstReactionNotification(reaction.getFeed()));

		UserSetting userSetting = userSettingGetService.getUserSetting(notification.getUserId());
		firstReactionNotificationSaveService.save(userSetting, notification, reaction);
	}

	public void saveNewFeedNotification(Feed feed){
		List<User> users = userSettingGetService.getUsersToNotifyNewFeed();
		newFeedNotificationSaveService.save(users, feed);
	}

	public void saveReactionCountNotification(Feed feed, Long reactionCount){
		Notification notification = notificationRepository.findByFeedIdAndReactionCount(NotificationType.FEED_REACTION_COUNT,
				feed.getId(), reactionCount)
			.orElseGet(() -> notificationMapper.toReactionCountNotification(feed));

		reactionCountNotificationSaveService.save(feed, reactionCount, notification);

	}

	public void saveTagNotification(Feed feed, List<LinkedUser> linkedUsers){
		tagNotificationSaveService.save(feed, linkedUsers);
	}

	@Transactional
	public void markNotificationAsRead(Long userId, String notificationId) {
		User user = userGetService.findById(userId);
		notificationMarkReadService.markReadNotification(user, notificationId);
	}

	private Boolean isFirstReactionDuplicated(Reaction reaction){
		return notificationRepository.findByFeedIdAndUserIdInFirstReactions(
			NotificationType.FEED_FIRST_REACTION, reaction.getFeed().getId(), reaction.getUser().getId()).isPresent();
	}
}
