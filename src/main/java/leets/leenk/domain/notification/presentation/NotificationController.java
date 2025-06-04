package leets.leenk.domain.notification.presentation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import leets.leenk.domain.notification.application.service.NotificationService;
import leets.leenk.global.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "NOTIFICATION")
@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

	private final NotificationService notificationService;

	// 태그 알림 생성을 위한 임시 컨트롤러. 추후 삭제 예정
	@Operation(summary = "태그 알림 발행 API")
	@PostMapping
	public CommonResponse<Void> TagNotification(){
		notificationService.temporaryTagNotification();
		return null;
	}

}
