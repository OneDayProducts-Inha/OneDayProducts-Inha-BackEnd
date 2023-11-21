package inha.app.MyGate.user.controller;

import inha.app.MyGate.common.Exception.BaseException;
import inha.app.MyGate.common.Exception.BaseResponse;
import inha.app.MyGate.common.Exception.BaseResponseStatus;
import inha.app.MyGate.user.dto.request.UserInfoRequest;
import inha.app.MyGate.user.dto.response.SignUpResponse;
import inha.app.MyGate.user.dto.response.UserInfoResponse;
import inha.app.MyGate.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    public BaseResponse<SignUpResponse> createUser(@RequestBody UserInfoRequest request) throws BaseException {
        SignUpResponse res = userService.createUser(request);
        return new BaseResponse<>(res);
    }

    // 사용자 정보 조회
    @Operation(summary = "사용자 정보 조회 - 장채은", description = "사용자 정보를 조회한다. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(1000)요청에 성공했습니다.\n (2014)유저를 찾을 수 없습니다."),
    })
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
    @Operation(summary = "사용자 정보 수정 - 장채은", description = "사용자 정보를 수정한다. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(1000)요청에 성공했습니다.\n (2014)유저를 찾을 수 없습니다."),
    })
    @PostMapping("/mypage")
    public BaseResponse<Void> updateUserInfo(@RequestBody UserInfoRequest request) {
        try{
            Long userId = 1L;
            userService.updateUserInfo(userId, request);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

}
