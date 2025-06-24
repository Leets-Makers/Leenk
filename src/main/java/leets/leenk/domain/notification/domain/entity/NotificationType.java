package leets.leenk.domain.notification.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationType {
    FEED_TAG("태그", "%s이 나를 게시글에 언급했어"),
    NEW_FEED("새로운 피드", "새로운 게시글이 올라왔어"),
    FEED_FIRST_REACTION("새로운 좋아요", "내가 쓴 피드에 공감을 받았어"),
    FEED_REACTION_COUNT("좋아요", "내가 쓴 피드에 좋아요를 %d개 받았어");

    private final String title;
    private final String content;

    public String getFormattedContent(String name) {
        return String.format(content, name);
    }

    public String getFormattedContent(Long reactionCount) {
        return String.format(content, reactionCount);
    }
}
