package leets.leenk.domain.feed.application.mapper;

import leets.leenk.domain.feed.application.dto.response.FeedUserResponse;
import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.feed.domain.entity.LinkedUser;
import leets.leenk.domain.user.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class FeedUserMapper {

    public LinkedUser toLinkedUser(User user, Feed feed) {
        return LinkedUser.builder()
                .feed(feed)
                .user(user)
                .build();
    }

    public FeedUserResponse toFeedUserResponse(User user) {
        return FeedUserResponse.builder()
                .userId(user.getId())
                .profileImage(user.getProfileImage())
                .name(user.getName())
                .build();
    }
}
