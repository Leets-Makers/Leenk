package leets.leenk.domain.feed.application.usecase;

import leets.leenk.domain.feed.application.dto.request.FeedUpdateRequest;
import leets.leenk.domain.feed.application.dto.request.FeedUploadRequest;
import leets.leenk.domain.feed.application.dto.request.ReactionRequest;
import leets.leenk.domain.feed.application.dto.response.FeedDetailResponse;
import leets.leenk.domain.feed.application.dto.response.FeedListResponse;
import leets.leenk.domain.feed.application.dto.response.ReactionUserResponse;
import leets.leenk.domain.feed.application.mapper.FeedLinkedUserMapper;
import leets.leenk.domain.feed.application.mapper.FeedMapper;
import leets.leenk.domain.feed.application.mapper.ReactionMapper;
import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.feed.domain.entity.LinkedUser;
import leets.leenk.domain.feed.domain.entity.Reaction;
import leets.leenk.domain.feed.domain.service.*;
import leets.leenk.domain.media.domain.application.mapper.MediaMapper;
import leets.leenk.domain.media.domain.entity.Media;
import leets.leenk.domain.media.domain.service.MediaGetService;
import leets.leenk.domain.media.domain.service.MediaSaveService;
import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.service.UserGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedUsecase {

    private final UserGetService userGetService;

    private final FeedGetService feedGetService;
    private final FeedSaveService feedSaveService;
    private final FeedUpdateService feedUpdateService;

    private final MediaGetService mediaGetService;
    private final MediaSaveService mediaSaveService;

    private final LinkedUserGetService linkedUserGetService;
    private final LinkedUserSaveService linkedUserSaveService;

    private final ReactionGetService reactionGetService;
    private final ReactionSaveService reactionSaveService;

    private final FeedMapper feedMapper;
    private final MediaMapper mediaMapper;
    private final FeedLinkedUserMapper feedLinkedUserMapper;
    private final ReactionMapper reactionMapper;

    /*
        추후 n+1, FetchJoin과 성능 테스트 진행할 것
     */
    @Transactional(readOnly = true)
    public FeedListResponse getFeeds(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Slice<Feed> slice = feedGetService.findAll(pageable);

        List<Feed> feeds = slice.getContent();
        List<Media> medias = mediaGetService.findAll(feeds);

        Map<Long, List<Media>> mediaMap = medias.stream()
                .collect(Collectors.groupingBy(media -> media.getFeed().getId()));

        return feedMapper.toFeedListResponse(slice, mediaMap);
    }

    @Transactional(readOnly = true)
    public FeedDetailResponse getFeedDetail(Long feedId) {
        Feed feed = feedGetService.findById(feedId);
        List<Media> medias = mediaGetService.findAll(feed);
        List<LinkedUser> linkedUsers = linkedUserGetService.findAll(feed);

        return feedMapper.toFeedDetailResponse(feed, medias, linkedUsers);
    }

    @Transactional
    public void uploadFeed(long userId, FeedUploadRequest request) {
        User author = userGetService.findById(userId);
        Feed feed = feedMapper.toFeed(author, request.description());
        feedSaveService.save(feed);

        List<Media> medias = request.media().stream()
                .map(mediaRequest -> mediaMapper.toMedia(feed, mediaRequest))
                .toList();
        mediaSaveService.saveAll(medias);

        List<LinkedUser> linkedUsers = getLinkedUsers(author, request.userId(), feed);
        linkedUserSaveService.saveAll(linkedUsers);
    }

    private List<LinkedUser> getLinkedUsers(User author, List<Long> userIds, Feed feed) {
        Set<User> users = new HashSet<>(userGetService.findAll(userIds));
        users.add(author); // 중복 자동 제거

        return users.stream()
                .map(user -> feedLinkedUserMapper.toLinkedUser(user, feed))
                .toList();
    }

    @Transactional
    public void reactToFeed(long userId, long feedId, ReactionRequest request) {
        User user = userGetService.findById(userId);
        Feed feed = feedGetService.findById(feedId);
        Reaction reaction = reactionGetService.findByFeedAndUser(feed, user)
                .orElseGet(() ->
                        reactionSaveService.save(
                                reactionMapper.toReaction(user, feed, 0L)
                        )
                );

        feedUpdateService.updateTotalReaction(feed, reaction, request.reactionCount());
    }

    @Transactional(readOnly = true)
    public List<ReactionUserResponse> getReactionUser(Long feedId) {
        Feed feed = feedGetService.findById(feedId);
        List<Reaction> reactions = reactionGetService.findAll(feed);

        return reactions.stream()
                .map(reactionMapper::toResponse)
                .toList();
    }

    public void updateFeed(FeedUpdateRequest request) {
    }
}
