package leets.leenk.domain.leenk.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import leets.leenk.domain.leenk.domain.entity.enums.ParticipantStatus;
import leets.leenk.global.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@SuperBuilder
@Table(name = "leenk_participants")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LeenkParticipants extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leenk_participant_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "leenk_id", nullable = false, updatable = false)
    private Leenks leenk;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_id", nullable = false, updatable = false)
//    private User user;

    @Column(name = "joined_at", nullable = false)
    private LocalDateTime joinedAt;

    @Column(nullable = false)
    private ParticipantStatus status;

    @Builder.Default
    @Column(name = "is_host", nullable = false)
    private Boolean isHost = false;

    @Column(name = "current_participants", nullable = false)
    private Long currentParticipants;
}
