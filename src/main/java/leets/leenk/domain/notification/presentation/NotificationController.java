package leets.leenk.domain.notification.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import leets.leenk.domain.notification.application.dto.NotificationCountResponse;
import leets.leenk.domain.notification.application.dto.NotificationListResponse;
import leets.leenk.domain.notification.application.service.NotificationService;
import leets.leenk.domain.notification.application.usecase.NotificationUsecase;
import leets.leenk.global.auth.application.annotation.CurrentUserId;
import leets.leenk.global.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "NOTIFICATION", description = "알림 API")
@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

	private final NotificationService notificationService;
	private final NotificationUsecase notificationUsecase;

	// 알림 생성을 위한 임시 컨트롤러. 추후 삭제 예정
	@Operation(summary = "태그 알림 발행 API")
	@PostMapping("/tag")
	public CommonResponse<Void> TagNotification(){
		notificationService.temporaryTagNotification();
		return null;
	}

	@Operation(summary = "임시 첫 좋아요 알림 발행 API")
	@PostMapping("/first_reaction")
	public CommonResponse<Void> FeedFirstReactionNotification(){
		notificationService.temporaryFeedFirstReactionNotification();
		return null;
	}

	@Operation(summary = "임시 새로운 피드 알림 발행 API")
	@PostMapping("/new_feed")
	public CommonResponse<Void> NewFeedNotification(){
		notificationService.temporaryNewFeedNotification();
		return null;
	}

	@Operation(summary = "임시 누적 공감 알림 발행 API")
	@PostMapping("/reaction_count")
	public CommonResponse<Void> ReactionCountNotification(){
		notificationService.temporaryReactionCountNotification();
		return null;
	}

	@Operation(summary = "최근 알림 조회 API [무한스크롤] / 사용자의 최근 알림 목록을 페이지 단위로 조회합니다. pageNumber: 0부터 시작")
	@GetMapping()
	public CommonResponse<NotificationListResponse> getNotifications(@CurrentUserId Long userId,
		@RequestParam("page") int pageNumber,@RequestParam("size") int pageSize){
		return CommonResponse.success(NotificationResponseCode.NOTIFICATION_READ_SUCCESS,
			notificationUsecase.getNotifications(userId, pageNumber, pageSize));
	}

	@Operation(summary = "알림 개수 조회 API")
	@GetMapping("/count")
	public CommonResponse<NotificationCountResponse> getNotificationCount(@CurrentUserId long userId){
		return CommonResponse.success(NotificationResponseCode.NOTIFICATION_COUNT_READ_SUCCESS,
			notificationUsecase.getNotificationCount(userId));
	}

	@Operation(summary = "단일 알림 읽음 처리 API")
	@PatchMapping("/{notificationId}")
	public CommonResponse<Void> markAsRead(@CurrentUserId long userId,
		@PathVariable String notificationId){
		notificationUsecase.markNotificationAsRead(userId, notificationId);
		return CommonResponse.success(NotificationResponseCode.NOTIFICATION_MARK_AS_READ_SUCCESS);
	}
}
