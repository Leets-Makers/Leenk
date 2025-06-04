package leets.leenk.domain.notification.domain.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TagNotificationEvent {

	List<LinkedUser> linkedUsers;

}
