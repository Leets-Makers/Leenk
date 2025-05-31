package leets.leenk.domain.leenk.domain.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ParticipantStatus {
    PARTICIPATING("참여중"),
    COMPLETED("활동 완료");

    private final String displayValue;
}
