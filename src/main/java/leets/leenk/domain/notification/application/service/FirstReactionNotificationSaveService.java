package leets.leenk.domain.notification.application.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import leets.leenk.domain.feed.domain.entity.Reaction;
import leets.leenk.domain.notification.application.mapper.FeedFirstReactionMapper;
import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.notification.domain.entity.content.FeedFirstReaction;
import leets.leenk.domain.notification.domain.entity.content.FeedFirstReactionNotificationContent;
import leets.leenk.domain.notification.domain.repository.NotificationRepository;
import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.entity.UserSetting;
import leets.leenk.global.sqs.application.mapper.SqsMessageEventMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FirstReactionNotificationSaveService {

	private final ApplicationEventPublisher eventPublisher;
	private final NotificationRepository notificationRepository;
	private final FeedFirstReactionMapper feedFirstReactionMapper;
	private final SqsMessageEventMapper sqsMessageEventMapper;

	@Transactional
	public void save(User user, UserSetting userSetting, Notification notification, Reaction reaction) {
		FeedFirstReaction feedFirstReaction = feedFirstReactionMapper.toFeedFirstReaction(reaction.getUser());

		FeedFirstReactionNotificationContent content =
			(FeedFirstReactionNotificationContent) notification.getContent();

		content.getFeedFirstReactions().add(feedFirstReaction);
		// 알림의 FeedFirstReactions 리스트에 추가

		notification.markUnread();
		notificationRepository.save(notification);

		// isNewReactionNotify 가 True인 사용자일 경우 푸시 알림
		if(userSetting.isNewReactionNotify())
			eventPublisher.publishEvent(sqsMessageEventMapper.fromFeedFirstReaction(feedFirstReaction, user.getFcmToken()));
	}
}
