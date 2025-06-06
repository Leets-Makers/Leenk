package leets.leenk.domain.notification.domain.entity.details;

import leets.leenk.domain.notification.domain.entity.NotificationContent;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LeenkDetail extends NotificationContent {

	private Long leenkId;
	private String AuthorName;
	private String Place;
	private String AppointmentTime;
	private String leenkName;

}
