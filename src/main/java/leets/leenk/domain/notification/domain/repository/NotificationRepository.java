package leets.leenk.domain.notification.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;

import leets.leenk.domain.notification.domain.entity.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String>, NotificationRepositoryCustom {

    Slice<Notification> findPageByUserId(Pageable pageable, Long userId);

    long countByUserIdAndIsReadFalse(Long id);
}
