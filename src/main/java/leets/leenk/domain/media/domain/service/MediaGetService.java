package leets.leenk.domain.media.domain.service;

import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.media.application.exception.MediaNotFoundException;
import leets.leenk.domain.media.domain.entity.Media;
import leets.leenk.domain.media.domain.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaGetService {

    private final MediaRepository mediaRepository;

    public Media findById(long mediaId) {
        return mediaRepository.findById(mediaId)
                .orElseThrow(MediaNotFoundException::new);
    }

    public List<Media> findAll(Feed feed) {
        return mediaRepository.findAllByFeedOrderByPosition(feed);
    }

    public List<Media> findAll(List<Feed> feeds) {
        return mediaRepository.findAllByFeedInOrderByPosition(feeds);
    }
}
