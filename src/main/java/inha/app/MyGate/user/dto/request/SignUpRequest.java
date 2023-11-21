package inha.app.MyGate.user.dto.request;

import inha.app.MyGate.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SignUpRequest {
    private String name;
    private String pw;
    private String phoneNum;

    public User toEntity(){
        return User.builder()
                .name(name)
                .phone_num(phoneNum)
                .pw(pw)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .status(true)
                .build();

    }
}
