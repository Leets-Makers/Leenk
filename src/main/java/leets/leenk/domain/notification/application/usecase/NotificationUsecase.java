package leets.leenk.domain.notification.application.usecase;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import leets.leenk.domain.notification.application.dto.NotificationCountResponse;
import leets.leenk.domain.notification.application.dto.NotificationResponse;
import leets.leenk.domain.notification.application.mapper.NotificationMapper;
import leets.leenk.domain.notification.application.service.NotificationCountGetService;
import leets.leenk.domain.notification.application.service.NotificationGetService;
import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.service.UserGetService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationUsecase {
	private final NotificationGetService notificationGetService;
	private final NotificationCountGetService notificationCountGetService;
	private final NotificationMapper mapper;
	private final UserGetService userGetService;

	public Slice<NotificationResponse> getNotifications(Long userId, int pageNumber, int pageSize){
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "updateDate"));
		Slice<Notification> notifications = notificationGetService.findRecentNotifications(userId, pageable);

		return notifications.map(mapper::toResponse);
	}

	public NotificationCountResponse getNotificationCount(long userId) {
		User user = userGetService.findById(userId);
		return mapper.toCountResponse(notificationCountGetService.getNotificationCount(user));
	}
}
