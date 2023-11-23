package inha.app.MyGate.community.entity;

import inha.app.MyGate.common.Exception.BaseException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static inha.app.MyGate.common.Exception.BaseResponseStatus.CATEGORY_NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum Category {
    MEET("만남"),
    HOBBY("취미"),
    HEALTH("건강"),
    FESTIVAL("행사");
    private final String value;

    public static Category getCategoryByValue(String value) throws BaseException {
        return Arrays.stream(Category.values())
                .filter(category -> category.getValue().equals(value))
                .findAny()
                .orElseThrow(() -> new BaseException(CATEGORY_NOT_FOUND));
    }
}