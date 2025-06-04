package leets.leenk.domain.notification.domain.entity;

import lombok.Getter;

@Getter
public enum NotificationType {
	FEED_TAG("태그", "%s님이 회원님을 태그하였습니다");

	private final String title;
	private final String content;

	NotificationType(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public String getFormattedContent(String name){
		return String.format(content, name);
	}
}
