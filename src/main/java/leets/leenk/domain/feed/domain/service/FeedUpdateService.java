package leets.leenk.domain.feed.domain.service;

import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.feed.domain.entity.Reaction;
import leets.leenk.domain.user.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public class FeedUpdateService {

    public void updateTotalReaction(Feed feed, Reaction reaction, User user, long reactionCount) {
        feed.increaseTotalReactionCount(reactionCount);
        reaction.increaseReactionCount(reactionCount);
        user.increaseTotalReactionCount(reactionCount);
    }
}
