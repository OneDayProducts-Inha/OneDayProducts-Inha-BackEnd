package inha.app.MyGate.user.dto.request;

import inha.app.MyGate.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequest {
    @Schema(type = "String", description = "성명", example = "홍길동", required = true)
    private String name;
    @Schema(type = "String", description = "비밀번호", example = "asdf1234!", required = true)
    private String pw;
    @Schema(type = "String", description = "휴대폰", example = "010-0000-0000", required = true)
    private String phoneNum;

    public User toEntity(){
        return User.builder()
                .userName(name)
                .phoneNum(phoneNum)
                .pw(pw)
                .build();
    }
}
