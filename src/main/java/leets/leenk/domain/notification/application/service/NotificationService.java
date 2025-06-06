package leets.leenk.domain.notification.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import leets.leenk.domain.notification.application.mapper.FeedFirstReactionMapper;
import leets.leenk.domain.notification.application.mapper.NotificationMapper;
import leets.leenk.domain.notification.domain.entity.event.FeedFirstLikeEvent;
import leets.leenk.domain.notification.domain.entity.LinkedUser;
import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.notification.domain.entity.details.FeedFirstLike;
import leets.leenk.domain.notification.domain.entity.details.FeedFirstLikeDetail;
import leets.leenk.domain.notification.domain.repository.NotificationRepository;
import leets.leenk.domain.user.domain.entity.User;
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
		Optional<Notification> existingNotification = notificationRepository.findByFeedFirstLikeDetailFeedId(feedId);

		Notification notification;

		FeedFirstLike feedFirstLike = feedFirstReactionMapper.toFeedFirstReaction(user);
		if (existingNotification.isEmpty()) { 	// 존재하지 않는 알림인 경우
			notification = notificationMapper.toFirstReactionNotification(author, feedId, feedFirstLike);
		}
		else{	// 이미 존재하는 알림에 추가
			notification = existingNotification.get();

			if(notificationRepository.findByFeedIdAndUserIdInFirstLikes(feedId, user.getId()).isPresent()){
				System.out.println("이미 있음!");
				//  이미 해당 유저에 대한 알림이 존재하므로 중복 생성 방지
				return ;
			}

			FeedFirstLikeDetail detail = notification.getFeedFirstLikeDetail();
			detail.getFeedFirstLikes().add(feedFirstLike);
		}

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
}
