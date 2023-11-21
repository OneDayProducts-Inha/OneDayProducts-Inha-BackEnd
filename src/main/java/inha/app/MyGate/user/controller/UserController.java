package inha.app.MyGate.user.controller;

import inha.app.MyGate.common.Exception.BaseException;
import inha.app.MyGate.common.Exception.BaseResponse;
import inha.app.MyGate.user.dto.request.SignUpRequest;
import inha.app.MyGate.user.dto.response.SignUpResponse;
import inha.app.MyGate.user.dto.response.UserInfoResponse;
import inha.app.MyGate.user.service.UserService;
import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = "유저", value = "회원 정보 관리 기능 구현한 User Controller 입니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/sign-up")
    public BaseResponse<SignUpResponse> createUser(@RequestBody SignUpRequest request) throws BaseException {
        SignUpResponse res = userService.createUser(request);
        return new BaseResponse<>(res);
    }

    // 사용자 정보 조회
    @GetMapping("/mypage")
    public BaseResponse<UserInfoResponse> getUserInfo() {
        try{
            Long userId = 1L;
            return new BaseResponse<>(userService.getUserInfo(userId));
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    // 사용자 정보 수정

}
