package leets.leenk.domain.notification.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.global.common.entity.BaseEntity;
import lombok.Getter;

// 알림을 위한 임시 엔티티
@Getter
public class LinkedUser extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lined_user_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;		// 태그 받은 유저

	private Long feedId;	// 임시 id, feed로 변경할 예정

	public LinkedUser(User user, Long feedId){
		this.user = user;
		this.feedId = feedId;
	}
}
