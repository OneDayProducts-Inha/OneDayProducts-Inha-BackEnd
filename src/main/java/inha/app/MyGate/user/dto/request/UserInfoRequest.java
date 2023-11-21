package inha.app.MyGate.user.dto.request;

import inha.app.MyGate.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequest {
    private String name;
    private String pw;
    private String phoneNum;

    public User toEntity(){
        return User.builder()
                .name(name)
                .phone_num(phoneNum)
                .pw(pw)
                .build();
    }
}
