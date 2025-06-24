package leets.leenk.domain.user.domain.service.user;

import leets.leenk.domain.user.application.exception.UserAlreadyLeaveException;
import leets.leenk.domain.user.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserDeleteService {

    public void leave(User user) {
        if (user.isLeave()) {
            throw new UserAlreadyLeaveException();
        }

        user.leave();
    }

    public void delete(User user) {
        user.delete();
    }
}
