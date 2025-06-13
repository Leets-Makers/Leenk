package leets.leenk.domain.user.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import leets.leenk.domain.user.domain.entity.User;
import leets.leenk.domain.user.domain.entity.UserSetting;
import leets.leenk.domain.user.domain.repository.UserSettingRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSettingGetService {
	private final UserSettingRepository userSettingRepository;

	public List<User> getUsersToNotifyNewFeed() {
		return userSettingRepository.findAllByIsNewFeedNotifyTrue()
			.stream()
			.map(UserSetting::getUser)
			.toList();
	}

	public UserSetting getUserSetting(Long userId){
		return userSettingRepository.findByUserId(userId);
	}
}
