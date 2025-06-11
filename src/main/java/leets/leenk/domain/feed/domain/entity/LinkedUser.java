package leets.leenk.domain.feed.domain.entity;

import jakarta.persistence.*;
import leets.leenk.domain.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@SuperBuilder
@Table(
        name = "linked_users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"feed_id", "user_id"})
        })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LinkedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "feed_id", nullable = false, updatable = false)
    private Feed feed;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

}
