package leets.leenk.domain.notification.application.dto;

import lombok.Builder;

@Builder
public record NotificationCountResponse(
	long notificationCount
) {
}
