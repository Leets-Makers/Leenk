package leets.leenk.domain.notification.application.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import leets.leenk.domain.notification.domain.entity.NotificationContent;
import leets.leenk.domain.notification.domain.entity.NotificationType;
import lombok.Builder;

@Builder
public record NotificationResponse(
	String id,
	UserInfo userInfo,
	NotificationType notificationType,
	Boolean isRead,
	NotificationContentResponse contents,
	LocalDateTime createDate,
	LocalDateTime updateDate

) {
	@Builder
	public record UserInfo(
		Long userId
	) {}

	@Builder
	public record NotificationContentResponse(
		@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
		NotificationContent content
	) {}
}
