package leets.leenk.domain.user.domain.service;

import leets.leenk.domain.user.application.exception.UserNotFoundException;
import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserGetService {

    private final UserRepository userRepository;

    public User findById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    public Optional<User> existById(long userId) {
        return userRepository.findById(userId);
    }

    public List<User> findAll(List<Long> userIds) {
        return userRepository.findAllByIdIn(userIds);
    }

    public List<User> findAll() {
        return userRepository.findAllByOrderByName();
    }
}
