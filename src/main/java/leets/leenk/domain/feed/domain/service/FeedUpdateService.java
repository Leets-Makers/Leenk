package leets.leenk.domain.feed.domain.service;

import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.feed.domain.entity.Reaction;
import org.springframework.stereotype.Service;

@Service
public class FeedUpdateService {

    public void updateTotalReaction(Feed feed, Reaction reaction, long reactionCount) {
        feed.increaseTotalReactionCount(reactionCount);
        reaction.increaseReactionCount(reactionCount);
    }
}
