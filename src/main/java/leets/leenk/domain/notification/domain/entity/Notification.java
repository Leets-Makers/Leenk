package leets.leenk.domain.notification.domain.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import leets.leenk.global.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "notifications")
public class Notification extends BaseEntity {

	@Id
	private String id;

	private Long userId;

	private String deviceToken;

	@Enumerated(EnumType.STRING)
	private NotificationType notificationType;

	private Boolean isRead;

	private NotificationContent content;

	public void setIsReadFalse(){
		this.isRead = false;
	}

	public void setIsReadTrue(){
		this.isRead = true;
	}

}
