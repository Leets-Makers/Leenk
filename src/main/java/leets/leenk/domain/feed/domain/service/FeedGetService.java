package leets.leenk.domain.feed.domain.service;

import leets.leenk.domain.feed.domain.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedGetService {

    private final FeedRepository feedRepository;


}
