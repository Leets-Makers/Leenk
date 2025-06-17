package leets.leenk.domain.feed.domain.service;

import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.feed.domain.entity.LinkedUser;
import leets.leenk.domain.feed.domain.repository.LinkedUserRepository;
import leets.leenk.domain.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkedUserGetService {

    private final LinkedUserRepository linkedUserRepository;

    public List<LinkedUser> findAll(Feed feed) {
        return linkedUserRepository.findAllByFeed(feed);
    }

    public Slice<Feed> findAllByUser(User user, Pageable pageable) {
        return linkedUserRepository.findFeedsByLinkedUser(user, pageable);
    }
}
