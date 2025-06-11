package leets.leenk.domain.feed.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import leets.leenk.domain.feed.application.dto.request.FeedUpdateRequest;
import leets.leenk.domain.feed.application.dto.request.FeedUploadRequest;
import leets.leenk.domain.feed.application.dto.response.FeedDetailResponse;
import leets.leenk.domain.feed.application.dto.response.FeedResponse;
import leets.leenk.domain.feed.application.dto.response.ReactionUserResponse;
import leets.leenk.domain.feed.application.usecase.FeedUsecase;
import leets.leenk.global.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "FEED")
@RestController
@RequestMapping("/feeds")
@RequiredArgsConstructor
public class FeedController {

    private final FeedUsecase feedUsecase;

    @GetMapping
    @Operation(summary = "피드 조회 API")
    public CommonResponse<List<FeedResponse>> getFeeds() {
        List<FeedResponse> response = feedUsecase.getFeeds();

        return CommonResponse.success(ResponseCode.GET_ALL_FEED, response);
    }

    @GetMapping("/{feedId}")
    @Operation(summary = "피드 상세 조회 API")
    public CommonResponse<FeedDetailResponse> getFeedDetail(@PathVariable @Positive Long feedId) {
        FeedDetailResponse response = feedUsecase.getFeedDetail(feedId);

        return CommonResponse.success(ResponseCode.GET_FEED_DETAIL, response);
    }

    @PostMapping
    @Operation(summary = "피드 업로드 API")
    public CommonResponse<Void> uploadFeed(@RequestBody @Valid FeedUploadRequest request) {
        feedUsecase.uploadFeed(request);

        return CommonResponse.success(ResponseCode.UPLOAD_FEED);
    }

    @PostMapping("/{feedId}/reactions")
    @Operation(summary = "공감하기 API")
    public CommonResponse<Void> reactToFeed(@PathVariable @Positive Long feedId) {
        feedUsecase.reactToFeed(feedId);

        return CommonResponse.success(ResponseCode.CREATE_REACTION);
    }

    @GetMapping("/{feedId}/reactions")
    @Operation(summary = "피드 공감 유저 목록 조회 API")
    public CommonResponse<List<ReactionUserResponse>> getLikedUsers(@PathVariable @Positive Long feedId) {
        List<ReactionUserResponse> response = feedUsecase.getLikedUsers(feedId);

        return CommonResponse.success(ResponseCode.GET_REACTED_USERS, response);
    }

    @PatchMapping
    @Operation(summary = "피드 수정 API")
    public CommonResponse<Void> updateFeed(@RequestBody @Valid FeedUpdateRequest request) {
        feedUsecase.updateFeed(request);

        return CommonResponse.success(ResponseCode.UPDATE_FEED);
    }

}
