package leets.leenk.domain.user.domain.service;

import leets.leenk.domain.user.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserDeleteService {

    public void delete(User user) {
        user.delete();
    }
}
