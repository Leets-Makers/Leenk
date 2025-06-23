package leets.leenk.domain.user.domain.service.user;

import leets.leenk.domain.user.application.exception.UserAlreadyDeletedException;
import leets.leenk.domain.user.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserDeleteService {

    public void delete(User user) {
        if (user.isDeleted()) {
            throw new UserAlreadyDeletedException();
        }

        user.delete();
    }
}
