package inha.app.MyGate.user.entity;

import inha.app.MyGate.common.Exception.BaseException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static inha.app.MyGate.common.Exception.BaseResponseStatus.INVALID_GENDER;

@Getter
@RequiredArgsConstructor
public enum Gender {
    MALE("남성"),
    FEMALE("여성");
    private final String value;

    public static Gender getGenderByValue(String value) throws BaseException {
        return Arrays.stream(Gender.values())
                .filter(r -> r.getValue().equals(value))
                .findAny().orElseThrow(() -> new BaseException(INVALID_GENDER));
    }
}
