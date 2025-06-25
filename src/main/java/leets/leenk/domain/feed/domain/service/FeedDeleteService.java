package leets.leenk.domain.feed.domain.service;

import leets.leenk.domain.feed.domain.entity.Feed;
import org.springframework.stereotype.Service;

@Service
public class FeedDeleteService {
    public void delete(Feed feed) {
        feed.delete();
    }
}
