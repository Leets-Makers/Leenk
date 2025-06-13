package leets.leenk.domain.notification.application.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import leets.leenk.domain.feed.domain.entity.Reaction;
import leets.leenk.domain.notification.application.mapper.FeedFirstReactionMapper;
import leets.leenk.domain.notification.application.mapper.NotificationMapper;
import leets.leenk.domain.notification.domain.entity.Notification;
import leets.leenk.domain.notification.domain.entity.details.FeedFirstReaction;
import leets.leenk.domain.notification.domain.entity.event.FeedFirstReactionEvent;
import leets.leenk.domain.notification.domain.repository.NotificationRepository;
import leets.leenk.domain.user.domain.service.UserSettingGetService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FirstReactionNotificationSaveService {

	private final ApplicationEventPublisher eventPublisher;
	private final NotificationRepository notificationRepository;
	private final FeedFirstReactionMapper feedFirstReactionMapper;
	private final NotificationMapper notificationMapper;
	private final UserSettingGetService userSettingGetService;

	@Transactional
	public void createFirstReactionNotification(Reaction reaction) {
		if(notificationRepository.findByFeedIdAndUserIdInFirstReactions(reaction.getFeed().getId(), reaction.getUser().getId()).isPresent()){
			//  이미 해당 유저에 대한 알림이 존재하므로 중복 생성 방지
			return ;
		}
		Notification notification = notificationRepository.findByFeedFirstReactionDetailFeedId(reaction.getFeed().getId())
			.orElseGet(()->notificationMapper.toFirstReactionNotification(reaction.getFeed()));

		FeedFirstReaction feedFirstReaction = feedFirstReactionMapper.toFeedFirstReaction(reaction.getUser());

		notification.getFeedFirstReactionDetail().getFeedFirstReactions().add(feedFirstReaction);
		// 알림의 FeedFirstReactions 리스트에 추가
		notificationRepository.save(notification);

		FeedFirstReactionEvent feedFirstReactionEvent = new FeedFirstReactionEvent(feedFirstReaction, notification.getDeviceToken());

		// isNewReactionNotify 가 True인 사용자일 경우 푸시 알림
		if(userSettingGetService.getUserSetting(notification.getUserId()).isNewReactionNotify())
			eventPublisher.publishEvent(feedFirstReactionEvent);
	}
}
