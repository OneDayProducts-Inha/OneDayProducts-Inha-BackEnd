package inha.app.MyGate.user.dto.request;

import inha.app.MyGate.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @Schema(type = "String", description = "휴대폰", example = "010-0000-0000", required = true)
    private String phoneNum;
    @Schema(type = "String", description = "비밀번호", example = "asdf1234!", required = true)
    private String pw;

    public User toLogin(){
        return User.builder()
                .phone_num(phoneNum)
                .pw(pw)
                .build();
    }

}
