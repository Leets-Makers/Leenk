package leets.leenk.domain.notification.application.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.notification.application.mapper.FeedReactionCountMapper;
import leets.leenk.domain.notification.application.mapper.NotificationMapper;
import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.notification.domain.entity.details.FeedReactionCount;
import leets.leenk.domain.notification.domain.entity.event.FeedReactionCountEvent;
import leets.leenk.domain.notification.domain.repository.NotificationRepository;
import leets.leenk.domain.user.domain.service.UserSettingGetService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReactionCountNotificationSaveService {

	private final ApplicationEventPublisher eventPublisher;
	private final NotificationMapper notificationMapper;
	private final NotificationRepository notificationRepository;
	private final FeedReactionCountMapper feedReactionCountMapper;
	private final UserSettingGetService userSettingGetService;

	@Transactional
	public void createReactionCountNotification(Feed feed, Long reactionCount) {

		Notification notification = notificationRepository.findByFeedIdAndReactionCount(feed.getId(), reactionCount)
			.orElseGet(() -> notificationMapper.toReactionCountNotification(feed));

		FeedReactionCount feedReactionCount = feedReactionCountMapper.toFeedReactionCount(reactionCount);

		notification.getFeedReactionCountDetail().getFeedReactionCounts().add(feedReactionCount);
		FeedReactionCountEvent feedReactionCountEvent = new FeedReactionCountEvent(feedReactionCount, feed.getUser().getFcmToken());

		notificationRepository.save(notification);

		if(userSettingGetService.getUserSetting(notification.getUserId()).isNewReactionNotify())
			eventPublisher.publishEvent(feedReactionCountEvent);
	}
}
