package leets.leenk.domain.media.domain.application;


import leets.leenk.domain.media.domain.entity.enums.MediaType;

public record FeedMediaResponse(
        int position,
        String mediaUrl,
        MediaType mediaType
) {
}
