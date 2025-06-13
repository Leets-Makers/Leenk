package leets.leenk.domain.notification.application.service;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.notification.application.mapper.NotificationMapper;
import leets.leenk.domain.feed.domain.entity.LinkedUser;
import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.notification.domain.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagNotificationSaveService {

	private final NotificationMapper notificationMapper;
	private final ApplicationEventPublisher eventPublisher;
	private final NotificationRepository notificationRepository;

	@Transactional
	public void createTagNotification(Feed feed, List<LinkedUser> linkedUsers) {
		List<Notification> notifications = notificationMapper.toFeedTagNotification(feed, linkedUsers);
		notificationRepository.saveAll(notifications);

		notifications.forEach(eventPublisher::publishEvent);
	}
}
