package leets.leenk.domain.notification.application.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import leets.leenk.domain.notification.application.mapper.FeedReactionCountMapper;
import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.notification.domain.entity.content.FeedReactionCount;
import leets.leenk.domain.notification.domain.entity.content.FeedReactionCountNotificationContent;
import leets.leenk.domain.notification.domain.repository.NotificationRepository;
import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.entity.UserSetting;
import leets.leenk.global.sqs.application.mapper.SqsMessageEventMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReactionCountNotificationSaveService {

	private final ApplicationEventPublisher eventPublisher;
	private final NotificationRepository notificationRepository;
	private final FeedReactionCountMapper feedReactionCountMapper;
	private final SqsMessageEventMapper sqsMessageEventMapper;

	@Transactional
	public void save(Long reactionCount, Notification notification, UserSetting userSetting, User user) {

		FeedReactionCount feedReactionCount = feedReactionCountMapper.toFeedReactionCount(reactionCount);

		FeedReactionCountNotificationContent content = (FeedReactionCountNotificationContent)notification.getContent();
		content.getFeedReactionCounts().add(feedReactionCount);

		notification.markUnread();
		notificationRepository.save(notification);


		if(userSetting.isNewReactionNotify())
			eventPublisher.publishEvent(sqsMessageEventMapper.fromFeedReactionCount(feedReactionCount, user.getFcmToken()));
	}
}
