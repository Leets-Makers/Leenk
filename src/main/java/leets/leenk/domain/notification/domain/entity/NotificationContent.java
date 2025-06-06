package leets.leenk.domain.notification.domain.entity;

import leets.leenk.global.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class NotificationContent extends BaseEntity {

	private String title;
	private String body;
	private Boolean isRead;

}
