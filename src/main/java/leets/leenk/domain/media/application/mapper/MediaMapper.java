package leets.leenk.domain.media.application.mapper;

import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.media.application.dto.request.FeedMediaRequest;
import leets.leenk.domain.media.domain.entity.Media;
import org.springframework.stereotype.Component;

@Component
public class MediaMapper {

    public Media toMedia(Feed feed, FeedMediaRequest request) {
        return Media.builder()
                .feed(feed)
                .position(request.position())
                .mediaUrl(request.mediaUrl())
                .mediaType(request.mediaType())
                .build();
    }
}
