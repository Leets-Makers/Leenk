package leets.leenk.domain.notification.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import leets.leenk.domain.notification.application.mapper.TagNotificationEventMapper;
import leets.leenk.domain.notification.domain.entity.LinkedUser;
import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.service.UserGetService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

	private final ApplicationEventPublisher eventPublisher;
	private final TagNotificationEventMapper tagNotificationEventMapper;
	private final UserGetService userGetService;	// 차후 삭제 예정

	// todo: 추후 피드에 머지된 linkedUser 엔티티와 태그한 사람의 유저를 파라미터로 받도록 수정
	@Transactional
	public void createTagNotification(List<LinkedUser> linkedUsers) {
		eventPublisher.publishEvent(tagNotificationEventMapper
			.toTagNotificationEvent(linkedUsers));
	}

	// 임시 태그 알림 메서드
	@Transactional
	public void temporaryTagNotification() {
		List<LinkedUser> linkedUsers = new ArrayList<>();

		User user = userGetService.findById(1);
		LinkedUser linkedUser = new LinkedUser(user, 1L);

		User user2 = userGetService.findById(2);
		LinkedUser linkedUser2 = new LinkedUser(user2, 1L);

		linkedUsers.add(linkedUser);
		linkedUsers.add(linkedUser2);

		createTagNotification(linkedUsers);
	}

}
