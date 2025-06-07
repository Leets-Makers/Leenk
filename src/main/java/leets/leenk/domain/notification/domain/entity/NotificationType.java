package leets.leenk.domain.notification.domain.entity;

import lombok.Getter;

@Getter
public enum NotificationType {
	FEED_TAG("태그", "%s님이 회원님을 태그하였습니다"),
	NEW_FEED("새로운 피드", "새로운 게시글이 올라왔어 %s"),
	FEED_FIRST_REACTION("새로운 좋아요", "내가 쓴 피드에 좋아요를 받았어 %s"),
	FEED_REACTION_COUNT("좋아요", "내가 쓴 피드에 좋아요를 %d개 받았어");

	private final String title;
	private final String content;

	NotificationType(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public String getFormattedContent(String name){
		return String.format(content, name);
	}

	public String getFormattedContent(long reactionCount){
		return String.format(content, reactionCount);
	}
}
