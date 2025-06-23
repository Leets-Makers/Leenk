package leets.leenk.domain.notification.application.service;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.notification.application.mapper.NotificationMapper;
import leets.leenk.domain.feed.domain.entity.LinkedUser;
import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.notification.domain.repository.NotificationRepository;
import leets.leenk.global.sqs.application.dto.SqsMessageEvent;
import leets.leenk.global.sqs.application.mapper.SqsMessageEventMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagNotificationSaveService {

	private final NotificationMapper notificationMapper;
	private final ApplicationEventPublisher eventPublisher;
	private final NotificationRepository notificationRepository;
	private final SqsMessageEventMapper sqsMessageEventMapper;

	public void save(Feed feed, List<LinkedUser> linkedUsers) {
		linkedUsers.forEach(linkedUser -> {
			Notification notification = notificationMapper.toFeedTagNotification(feed, linkedUser);
			notificationRepository.save(notification);

			String deviceToken = linkedUser.getUser().getFcmToken();
			SqsMessageEvent event = sqsMessageEventMapper.toSqsMessageEvent(notification, deviceToken);
			eventPublisher.publishEvent(event);
		});
	}
}
