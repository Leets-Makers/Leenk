package leets.leenk.domain.feed.application.usecase;

import leets.leenk.domain.feed.application.dto.request.FeedUpdateRequest;
import leets.leenk.domain.feed.application.dto.request.FeedUploadRequest;
import leets.leenk.domain.feed.application.dto.response.FeedDetailResponse;
import leets.leenk.domain.feed.application.dto.response.FeedResponse;
import leets.leenk.domain.feed.application.dto.response.ReactionUserResponse;
import leets.leenk.domain.feed.domain.service.FeedGetService;
import leets.leenk.domain.feed.domain.service.FeedSaveService;
import leets.leenk.domain.feed.domain.service.FeedUpdateService;
import leets.leenk.domain.user.domain.service.UserGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedUsecase {

    private final UserGetService userGetService;
    private final FeedGetService feedGetService;
    private final FeedSaveService feedSaveService;
    private final FeedUpdateService feedUpdateService;

    public List<FeedResponse> getFeeds() {
        return null;
    }

    public FeedDetailResponse getFeedDetail(Long feedId) {
        return null;
    }

    public void uploadFeed(FeedUploadRequest request) {

    }

    public void reactToFeed(Long feedId) {
    }

    public List<ReactionUserResponse> getLikedUsers(Long feedId) {
        return null;
    }

    public void updateFeed(FeedUpdateRequest request) {
    }
}
