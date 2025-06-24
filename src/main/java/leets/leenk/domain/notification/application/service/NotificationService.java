package leets.leenk.domain.notification.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.feed.domain.entity.LinkedUser;
import leets.leenk.domain.feed.domain.entity.Reaction;
import leets.leenk.domain.feed.domain.repository.FeedRepository;
import leets.leenk.domain.feed.domain.repository.LinkedUserRepository;
import leets.leenk.domain.feed.domain.repository.ReactionRepository;
import leets.leenk.domain.notification.application.usecase.NotificationUsecase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
    // 해당 서비스는 테스트용으로 추후 머지 전에 삭제 예정

    private final NotificationUsecase notificationUsecase;
    private final LinkedUserRepository linkedUserRepository;
    private final FeedRepository feedRepository;
    private final ReactionRepository reactionRepository;

    // 피드 태그 알림 테스트를 위한 메소드
    @Transactional
    public void temporaryTagNotification() {
        Feed feed = feedRepository.findById(1L).orElseThrow();
        List<LinkedUser> linkedUsers = linkedUserRepository.findAllByFeed(feed);
        notificationUsecase.saveTagNotification(feed, linkedUsers);
    }

    // 첫 리액션 알림 테스트를 위한 메소드
    @Transactional
    public void temporaryFeedFirstReactionNotification() {
        Reaction reaction = reactionRepository.findById(1L).orElseThrow();

        notificationUsecase.saveFirstReactionNotification(reaction);
    }


    // 새로운 피드 알림 테스트를 위한 메소드
    @Transactional
    public void temporaryNewFeedNotification() {
        Feed feed = feedRepository.findById(1L).orElseThrow();
        notificationUsecase.saveNewFeedNotification(feed);
    }

    // 리액션 수 알림 테스트를 위한 메소드
    @Transactional
    public void temporaryReactionCountNotification() {
        Feed feed = feedRepository.findById(1L).orElseThrow();
        Long reactionCount = 50L;

        notificationUsecase.saveReactionCountNotification(feed, reactionCount);
    }

}
