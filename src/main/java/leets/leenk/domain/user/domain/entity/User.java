package leets.leenk.domain.user.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import leets.leenk.global.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Entity
@SuperBuilder
@Table(name="users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @Column(name = "user_id")
    private Long id;

    @Size(max = 10)
    @Column(length = 10, nullable = false)
    private String name;

    @Column(nullable = false)
    private int cardinal;

    private String profileImage;

    @Size(max = 4)
    @Column(length = 4)
    private String mbti;

    @Size(max = 60)
    @Column(length = 60)
    private String introduction;

    private String fcmToken;

    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String kakaoTalkId;

    @Column(nullable = false)
    private long totalReactionCount;

    private LocalDateTime deleteDate;

    public void updateKakaoTalkId(String kakaoTalkId) {
        this.kakaoTalkId = kakaoTalkId;
    }

    public void updateProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void updateIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void updateMbti(String mbti) {
        this.mbti = mbti;
    }

    public void increaseTotalReactionCount(long reactionCount) {
        this.totalReactionCount += reactionCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public void delete() {
        this.deleteDate = LocalDateTime.now();
    }
}
