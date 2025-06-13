package leets.leenk.domain.feed.domain.service;

import leets.leenk.domain.feed.domain.entity.Feed;
import leets.leenk.domain.feed.domain.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedGetService {

    private final FeedRepository feedRepository;

    public Feed findById(Long feedId) {
        return feedRepository.findByDeletedAtIsNullAndId(feedId).orElseThrow(() -> new IllegalArgumentException("해당 feed가 존재하지 않습니다."));
    }

    public Slice<Feed> findAll(Pageable pageable) {
        return feedRepository.findAllByDeletedAtIsNullWithUser(pageable);
    }

}
