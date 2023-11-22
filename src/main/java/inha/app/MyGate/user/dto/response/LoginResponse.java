package inha.app.MyGate.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    @Schema(type = "Long", description = "사용자 id", example = "1", required = true)
    private Long id;
    @Schema(type = "String", description = "성명", example = "홍길동", required = true)
    private String name;
    @Schema(type = "String", description = "휴대폰", example = "010-0000-0000", required = true)
    private String phoneNum;
    @Schema(type = "String", description = "jwt token", example = "eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJpZCI6NiwiaWF0IjoxNzAwNjY5ODE1LCJleHAiOjE3MDIxNDEwNDR9.LJ94SpOj_fv5fTF-XhKBdqxCQVl_bGZTnu8rPZSnLdc", required = true)
    private String jwt;
}
