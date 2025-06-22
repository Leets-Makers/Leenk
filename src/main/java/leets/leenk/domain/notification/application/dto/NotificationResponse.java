package leets.leenk.domain.notification.application.dto;

import java.time.LocalDateTime;

import leets.leenk.domain.notification.domain.entity.NotificationContent;
import leets.leenk.domain.notification.domain.entity.NotificationType;
import lombok.Builder;

@Builder
public record NotificationResponse(
	String id,
	UserInfo userInfo,
	NotificationType notificationType,
	NotificationContent content,
	LocalDateTime createDate,
	LocalDateTime updateDate

) {
	@Builder
	public record UserInfo(
		Long userId
	) {}
}
