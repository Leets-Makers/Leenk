package leets.leenk.domain.feed.domain.service;

import leets.leenk.domain.feed.domain.entity.LinkedUser;
import leets.leenk.domain.feed.domain.repository.LinkedUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkedUserSaveService {

    private final LinkedUserRepository linkedUserRepository;

    public void save(LinkedUser linkedUser) {
        linkedUserRepository.save(linkedUser);
    }

    public void saveAll(List<LinkedUser> linkedUsers) {
        linkedUserRepository.saveAll(linkedUsers);
    }
}
