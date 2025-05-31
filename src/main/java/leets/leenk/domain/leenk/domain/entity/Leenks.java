package leets.leenk.domain.leenk.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import leets.leenk.global.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "leenks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Leenks extends BaseEntity {

    @Id
    @Column(name = "leenk_id")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_id", nullable = false, updatable = false)
//    private User user;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(length = 100)
    private String content;

    @Column(nullable = false, length = 25)
    private String placeName;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "min_participants", nullable = false)
    private Long minParticipants;

    @Column(name = "max_participants", nullable = false)
    private Long maxParticipants;

//    @Column(columnDefinition = "TEXT")
//    private String imageUrls;
}
