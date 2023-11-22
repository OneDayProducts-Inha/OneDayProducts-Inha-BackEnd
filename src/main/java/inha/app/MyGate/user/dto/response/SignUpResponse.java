package inha.app.MyGate.user.dto.response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpResponse {
    @Schema(type = "Long", description = "사용자 id", example = "1", required = true)
    private Long id;
    @Schema(type = "String", description = "성명", example = "홍길동", required = true)
    private String name;
    @Schema(type = "String", description = "휴대폰", example = "010-0000-0000", required = true)
    private String phoneNum;
}
