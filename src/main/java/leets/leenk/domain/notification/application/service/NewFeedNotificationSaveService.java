package leets.leenk.domain.notification.application.service;

import java.util.List;
import java.util.Objects;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.notification.application.mapper.NotificationMapper;
import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.notification.domain.repository.NotificationRepository;
import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.service.UserSettingGetService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewFeedNotificationSaveService {

	private final UserSettingGetService userSettingGetService;
	private final NotificationMapper notificationMapper;
	private final ApplicationEventPublisher eventPublisher;
	private final NotificationRepository notificationRepository;

	@Transactional
	public void save(List<User> users, Feed feed){
		// IsNewFeedNotify가 True인 글쓴이를 제외한 모든 유저에게 이벤트 발생
		users.stream()
			.filter(user -> !Objects.equals(user.getId(), feed.getUser().getId()))
			.forEach(
			user -> {
				Notification notification = notificationMapper.toNewFeedNotification(feed, user);
				notificationRepository.save(notification);
				if(userSettingGetService.findByUser(user).isNewFeedNotify())
					eventPublisher.publishEvent(notification);	// 알림을 허용한 유저
			}
		);
	}
}
