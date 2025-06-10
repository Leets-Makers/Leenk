package leets.leenk.domain.feed.domain.service;

import leets.leenk.domain.feed.domain.enitty.Feed;
import leets.leenk.domain.feed.domain.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedSaveService {

    private final FeedRepository feedRepository;

    public void save(Feed feed) {
        feedRepository.save(feed);
    }
}
