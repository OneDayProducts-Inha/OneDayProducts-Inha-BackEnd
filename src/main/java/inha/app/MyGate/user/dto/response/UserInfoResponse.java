package inha.app.MyGate.user.dto.response;

import inha.app.MyGate.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserInfoResponse {
    private Long id;
    private String name;
    private String phoneNum;

    public static UserInfoResponse toDto(User user){
        return UserInfoResponse.builder()
                .id(user.getUserId())
                .name(user.getName())
                .phoneNum(user.getPhone_num())
                .build();
    }
}
