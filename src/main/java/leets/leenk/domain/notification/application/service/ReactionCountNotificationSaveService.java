package leets.leenk.domain.notification.application.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.notification.application.mapper.FeedReactionCountMapper;
import leets.leenk.domain.notification.application.mapper.NotificationMapper;
import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.notification.domain.entity.NotificationType;
import leets.leenk.domain.notification.domain.entity.content.FeedReactionCount;
import leets.leenk.domain.notification.domain.entity.content.FeedReactionCountNotificationContent;
import leets.leenk.domain.notification.domain.entity.event.FeedReactionCountEvent;
import leets.leenk.domain.notification.domain.repository.NotificationRepository;
import leets.leenk.domain.user.domain.service.UserSettingGetService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReactionCountNotificationSaveService {

	private final ApplicationEventPublisher eventPublisher;
	private final NotificationRepository notificationRepository;
	private final FeedReactionCountMapper feedReactionCountMapper;
	private final UserSettingGetService userSettingGetService;

	@Transactional
	public void save(Feed feed, Long reactionCount, Notification notification) {

		FeedReactionCount feedReactionCount = feedReactionCountMapper.toFeedReactionCount(reactionCount);

		FeedReactionCountNotificationContent content = (FeedReactionCountNotificationContent)notification.getContent();
		content.getFeedReactionCounts().add(feedReactionCount);

		notification.markUnread();
		notificationRepository.save(notification);

		FeedReactionCountEvent feedReactionCountEvent = new FeedReactionCountEvent(feedReactionCount, feed.getUser().getFcmToken());

		if(userSettingGetService.getUserSetting(notification.getUserId()).isNewReactionNotify())
			eventPublisher.publishEvent(feedReactionCountEvent);
	}
}
