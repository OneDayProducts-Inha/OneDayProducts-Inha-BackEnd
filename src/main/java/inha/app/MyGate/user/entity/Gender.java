package inha.app.MyGate.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Gender {
    MALE("남성"),
    FEMALE("여성");
    private final String value;

    public static Gender getGenderByValue(String value) {
        return Arrays.stream(Gender.values())
                .filter(r -> r.getValue().equals(value))
                .findAny().orElseThrow(null);
    }
}
