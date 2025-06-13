package leets.leenk.domain.feed.domain.service;

import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.feed.domain.entity.Reaction;
import leets.leenk.domain.feed.domain.repository.ReactionRepository;
import leets.leenk.domain.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReactionGetService {

    private final ReactionRepository reactionRepository;

    public Optional<Reaction> findByFeedAndUser(Feed feed, User user) {
        return reactionRepository.findByFeedAndUser(feed, user);
    }

    public List<Reaction> findAll(Feed feed) {
        return reactionRepository.findAllByFeed(feed);
    }
}
