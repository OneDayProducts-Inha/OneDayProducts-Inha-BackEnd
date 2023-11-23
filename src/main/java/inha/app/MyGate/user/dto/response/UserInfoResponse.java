package inha.app.MyGate.user.dto.response;

import inha.app.MyGate.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserInfoResponse {
    @Schema(type = "Long", description = "사용자 id", example = "1", required = true)
    private Long id;
    @Schema(type = "String", description = "성명", example = "홍길동", required = true)
    private String name;
    @Schema(type = "String", description = "휴대폰", example = "010-0000-0000", required = true)
    private String phoneNum;
    @Schema(type = "String", description = "지역", example = "인천", required = true)
    private String location;
    @Schema(type = "Integer", description = "나이", example = "70", required = true)
    private Integer age;
    @Schema(type = "String", description = "성별", example = "남성", required = true, allowableValues = {"남성", "여성"})
    private String gender;
    @Schema(type = "String", description = "프로필 이미지", example = "1", required = true)
    private String imgUrl;

    public static UserInfoResponse toDto(User user){
        return UserInfoResponse.builder()
                .id(user.getUserId())
                .name(user.getUserName())
                .phoneNum(user.getPhoneNum())
                .location(user.getLocation())
                .age(User.getAmericanAge(user.getBirth()))
                .gender(user.getGender().getValue())
                .imgUrl(user.getImgUrl())
                .build();
    }


}
