package leets.leenk.domain.user.domain.service.user;

import leets.leenk.domain.user.application.exception.UserAlreadyDeletedException;
import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDeleteService {

    private final UserRepository userRepository;

    public void leave(User user) {
        if (user.isDeleted()) {
            throw new UserAlreadyDeletedException();
        }

        user.delete();
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}
