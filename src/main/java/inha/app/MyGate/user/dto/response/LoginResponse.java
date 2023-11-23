package inha.app.MyGate.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    @Schema(type = "String", description = "jwt token", example = "eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJpZCI6NiwiaWF0IjoxNzAwNjY5ODE1LCJleHAiOjE3MDIxNDEwNDR9.LJ94SpOj_fv5fTF-XhKBdqxCQVl_bGZTnu8rPZSnLdc", required = true)
    private String jwt;
}
