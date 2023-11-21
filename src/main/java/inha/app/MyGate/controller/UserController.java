package inha.app.MyGate.controller;

import inha.app.MyGate.config.BaseException;
import inha.app.MyGate.config.BaseResponse;
import inha.app.MyGate.dto.request.SignUpRequest;
import inha.app.MyGate.dto.response.SignUpResponse;
import inha.app.MyGate.model.User;
import inha.app.MyGate.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "유저", value = "회원 정보 관리 기능 구현한 User Controller 입니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/sign-up")
    public BaseResponse<SignUpResponse> createUser(@RequestBody SignUpRequest request) throws BaseException {
        SignUpResponse res = userService.createUser(request);
        return new BaseResponse<>(res);
    }
}
