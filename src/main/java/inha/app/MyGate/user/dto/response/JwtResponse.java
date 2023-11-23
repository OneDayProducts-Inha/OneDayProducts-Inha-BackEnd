package inha.app.MyGate.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse {
    @Schema(type = "String", description = "jwt token", example = "eyJ0hKBdqxCbGZTnu8rPZSnLdc", required = true)
    private String jwt;
}
