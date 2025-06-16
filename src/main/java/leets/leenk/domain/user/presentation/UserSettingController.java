package leets.leenk.domain.user.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import leets.leenk.domain.user.application.dto.request.FeedbackRequest;
import leets.leenk.domain.user.application.usecase.UserSettingUsecase;
import leets.leenk.global.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "USER-SETTING")
@RestController
@RequestMapping("/user-setting")
@RequiredArgsConstructor
public class UserSettingController {

    private final UserSettingUsecase userSettingUsecase;

    @PostMapping("/feedback")
    @Operation(summary = "의견 남기기 API")
    public CommonResponse<Void> sendFeedback(@RequestBody @Valid FeedbackRequest request) {
        userSettingUsecase.sendFeedback(request.feedback());

        return CommonResponse.success(ResponseCode.SEND_FEEDBACK);
    }
}
