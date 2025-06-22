package leets.leenk.domain.notification.application.usecase;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import leets.leenk.domain.notification.application.dto.NotificationCountResponse;
import leets.leenk.domain.notification.application.dto.NotificationListResponse;
import leets.leenk.domain.notification.application.mapper.NotificationResponseMapper;
import leets.leenk.domain.notification.application.service.NotificationCountGetService;
import leets.leenk.domain.notification.application.service.NotificationGetService;
import leets.leenk.domain.notification.application.service.NotificationMarkReadService;
import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.service.UserGetService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationUsecase {
	private final NotificationGetService notificationGetService;
	private final NotificationCountGetService notificationCountGetService;
	private final NotificationMarkReadService notificationMarkReadService;
	private final NotificationResponseMapper mapper;
	private final UserGetService userGetService;

	@Transactional(readOnly = true)
	public NotificationListResponse getNotifications(Long userId, int pageNumber, int pageSize){
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "updateDate"));
		Slice<Notification> notifications = notificationGetService.findRecentNotifications(userId, pageable);

		return mapper.toNotificationListResponse(notifications);
	}

	@Transactional(readOnly = true)
	public NotificationCountResponse getNotificationCount(long userId) {
		User user = userGetService.findById(userId);
		return mapper.toCountResponse(notificationCountGetService.getNotificationCount(user));
	}

	@Transactional
	public void markNotificationAsRead(Long userId, String notificationId) {
		User user = userGetService.findById(userId);
		notificationMarkReadService.markReadNotifiaction(user, notificationId);
	}
}
