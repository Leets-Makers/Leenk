package leets.leenk.domain.user.domain.service.user;

import leets.leenk.domain.user.application.exception.UserNotFoundException;
import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserGetService {

    private final UserRepository userRepository;

    public User findById(long userId) {
        return userRepository.findByIdAndDeleteDateIsNull(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    public Optional<User> existById(long userId) {
        return userRepository.findById(userId);
    }

    public List<User> findAll(List<Long> userIds) {
        return userRepository.findAllByIdInAndDeleteDateIsNull(userIds);
    }

    public List<User> findAll() {
        return userRepository.findAllByDeleteDateIsNullOrderByName();
    }

    public Slice<User> findAll(Pageable pageable) {
        return userRepository.findAllByDeleteDateIsNullOrderByName(pageable);
    }
}
