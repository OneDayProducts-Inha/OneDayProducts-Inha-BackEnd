package inha.app.MyGate.user.dto.request;

import inha.app.MyGate.user.entity.Gender;
import inha.app.MyGate.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @Schema(type = "String", description = "성별", example = "남성", required = true, allowableValues = {"여성", "남성"})
    private String gender;
    @Schema(type = "String", description = "주소", example = "인천시 남동구", required = true)
    private String location;
    @Schema(type = "LocalDate", description = "생년월일", example = "yyyy-mm-dd", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
    @Schema(type = "String", description = "이미지 URL", example = "1", required = true)
    private String imgUrl;
}
