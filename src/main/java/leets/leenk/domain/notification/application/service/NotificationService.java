package leets.leenk.domain.notification.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import leets.leenk.domain.notification.application.mapper.FeedFirstReactionMapper;
import leets.leenk.domain.notification.application.mapper.FeedLikeCountMapper;
import leets.leenk.domain.notification.application.mapper.NotificationMapper;
import leets.leenk.domain.notification.domain.entity.details.FeedLikeCount;
import leets.leenk.domain.notification.domain.entity.event.FeedFirstLikeEvent;
import leets.leenk.domain.notification.domain.entity.LinkedUser;
import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.notification.domain.entity.details.FeedFirstLike;
import leets.leenk.domain.notification.domain.entity.details.FeedFirstLikeDetail;
import leets.leenk.domain.notification.domain.entity.event.FeedLikeCountEvent;
import leets.leenk.domain.notification.domain.repository.NotificationRepository;
import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.entity.UserSetting;
import leets.leenk.domain.user.domain.repository.UserRepository;
import leets.leenk.domain.user.domain.service.UserGetService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

	private final ApplicationEventPublisher eventPublisher;
	private final UserGetService userGetService;	// 차후 삭제 예정
	private final NotificationMapper notificationMapper;
	private final NotificationRepository notificationRepository;
	private final FeedFirstReactionMapper feedFirstReactionMapper;
	private final FeedLikeCountMapper feedLikeCountMapper;

	// todo: 추후 피드에 머지된 linkedUser 엔티티와 Tag 엔티티를 파라미터로 받을 예정
	@Transactional
	public void createTagNotification(List<LinkedUser> linkedUsers) {
		List<Notification> notifications = notificationMapper.toFeedTagNotification(linkedUsers);
		notificationRepository.saveAll(notifications);

		notifications.forEach(eventPublisher::publishEvent);
	}

	// 테스트를 위한 임시 태그 알림 메서드
	@Transactional
	public void temporaryTagNotification() {
		List<LinkedUser> linkedUsers = new ArrayList<>();

		User user = userGetService.findById(1);
		LinkedUser linkedUser = new LinkedUser(user, 1L);

		User user2 = userGetService.findById(2);
		LinkedUser linkedUser2 = new LinkedUser(user2, 1L);

		linkedUsers.add(linkedUser);
		linkedUsers.add(linkedUser2);

		createTagNotification(linkedUsers);
	}

	@Transactional
	public void createFirstReactionNotification(User user, User author, Long feedId) {
		if(notificationRepository.findByFeedIdAndUserIdInFirstLikes(feedId, user.getId()).isPresent()){
			//  이미 해당 유저에 대한 알림이 존재하므로 중복 생성 방지
			return ;
		}
		Notification notification = notificationRepository.findByFeedFirstLikeDetailFeedId(feedId)
			.orElseGet(()->notificationMapper.toFirstReactionNotification(author, feedId));

		FeedFirstLike feedFirstLike = feedFirstReactionMapper.toFeedFirstReaction(user);

		notification.getFeedFirstLikeDetail().getFeedFirstLikes().add(feedFirstLike);
		// 알림의 FeedFirstLikes 리스트에 추가
		notificationRepository.save(notification);

		FeedFirstLikeEvent feedFirstLikeEvent = new FeedFirstLikeEvent(feedFirstLike, notification.getDeviceToken());
		eventPublisher.publishEvent(feedFirstLikeEvent);

	}

	// 첫 리액션 알림 테스트를 위한 메소드, 추후 삭제 예정
	// 같은 메소드 내에서의 @Transactional은 적용 받지 않음
	@Transactional
	public void temporaryFeedFirstLikeNotification() {
		User user1 = userGetService.findById(1);
		User user2 = userGetService.findById(2);
		createFirstReactionNotification(user1, user2, 1L);
	}

	
	@Transactional
	public void createNewFeedNotification(Long feedId){	// Todo: Feed로 받아오기
		/*
		Todo: User 도메인 머지된 후 UserSettingRepository를 이용해
		 IsNewFeedNotify가 True인 모든 유저에게 이벤트 발생
		List<UserSetting> userSettings = UserSettingRepository.findAllByIsNewFeedNotifyTrue();
		 */

		User user1 = userGetService.findById(1);

		UserSetting setting1 = UserSetting.builder()
			.user(user1)
			.isNewLeenkNotify(true)
			.isLeenkStatusNotify(true)
			.isNewFeedNotify(true)
			.isNewReactionNotify(true)
			.build();

		User user2 = userGetService.findById(2);

		UserSetting setting2 = UserSetting.builder()
			.user(user2)
			.isNewLeenkNotify(true)
			.isLeenkStatusNotify(true)
			.isNewFeedNotify(true)
			.isNewReactionNotify(true)
			.build();

		List<UserSetting> userSettings = List.of(setting1, setting2);

		userSettings.forEach(
			userSetting -> {
				Notification notification = notificationMapper.toNewFeedNotification(feedId, userSetting.getUser());
				notificationRepository.save(notification);
				eventPublisher.publishEvent(notification);
			}
		);
	}

	// 새로운 피드 알림 테스트를 위한 메소드, 추후 삭제 예정
	@Transactional
	public void temporaryNewFeedNotification() {
		Long feedId = 1L;
		createNewFeedNotification(feedId);
	}

	// 리액션 수 알림 테스트를 위한 메소드, 추후 삭제 예정
	@Transactional
	public void temporaryReactionCountNotification() {
		Long feedId = 1L;
		Long reactionCount = 50L;
		User user = userGetService.findById(1);

		createReactionCountNotification(feedId, user, reactionCount);
	}

	// Todo : Feed 객체로 파라미터 받아오기
	// 파라미터 : Feed feed, Long reactionCount
	@Transactional
	public void createReactionCountNotification(Long feedId, User user, Long reactionCount) {

		Notification notification = notificationRepository.findByFeedIdAndLikeCount(feedId, reactionCount)
			.orElseGet(() -> notificationMapper.toReactionCountNotification(feedId, user));

		FeedLikeCount feedLikeCount = feedLikeCountMapper.toFeedLikeCount(reactionCount);

		notification.getFeedLikeCountDetail().getFeedLikeCounts().add(feedLikeCount);
		FeedLikeCountEvent feedLikeCountEvent = new FeedLikeCountEvent(feedLikeCount, user.getFcmToken());
		eventPublisher.publishEvent(feedLikeCountEvent);

		notificationRepository.save(notification);
	}

}
