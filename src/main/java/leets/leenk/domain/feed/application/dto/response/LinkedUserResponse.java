package leets.leenk.domain.feed.application.dto.response;

public record LinkedUserResponse(
        long userId,
        boolean isAuthor,
        String profileImage,
        String name
) {
}
