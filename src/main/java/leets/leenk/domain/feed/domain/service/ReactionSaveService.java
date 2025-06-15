package leets.leenk.domain.feed.domain.service;

import leets.leenk.domain.feed.domain.entity.Reaction;
import leets.leenk.domain.feed.domain.repository.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReactionSaveService {
    private final ReactionRepository reactionRepository;

    public Reaction save(Reaction reaction) {
        return reactionRepository.save(reaction);
    }
}
