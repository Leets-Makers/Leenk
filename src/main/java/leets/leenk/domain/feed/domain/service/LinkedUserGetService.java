package leets.leenk.domain.feed.domain.service;

import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.feed.domain.entity.LinkedUser;
import leets.leenk.domain.feed.domain.repository.LinkedUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkedUserGetService {

    private final LinkedUserRepository linkedUserRepository;

    public List<LinkedUser> findAll(Feed feed) {
        return linkedUserRepository.findAllByFeed(feed);
    }
}
