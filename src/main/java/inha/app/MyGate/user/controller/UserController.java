package inha.app.MyGate.user.controller;

import inha.app.MyGate.common.Exception.BaseException;
import inha.app.MyGate.common.Exception.BaseResponse;
import inha.app.MyGate.common.Exception.BaseResponseStatus;
import inha.app.MyGate.common.resolver.LoginUser;
import inha.app.MyGate.user.dto.request.LoginRequest;
import inha.app.MyGate.user.dto.request.UserInfoRequest;
import inha.app.MyGate.user.dto.response.JwtResponse;
import inha.app.MyGate.user.dto.response.UserInfoResponse;
import inha.app.MyGate.user.entity.User;
import inha.app.MyGate.user.service.UserService;
import inha.app.MyGate.utils.JwtService;
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
    @Operation(summary = "회원가입", description = "휴대폰번호, 닉네임, 비밀번호, 위치, 프로필이미지, 성별, 생년월일로 회원가입한다. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(1000)요청에 성공했습니다. \n (2012) 중복된 핸드폰 번호입니다. \n (2032) 중복된 닉네임입니다. \n (2033) 휴대폰 번호 형식이 올바르지 않습니다. \n (2034) 성별 형식이 올바르지 않습니다."),
    })
    @PostMapping("/sign-up")
    public BaseResponse<JwtResponse> createUser(@RequestBody UserInfoRequest request) {
        try{
            JwtResponse res = userService.createUser(request);
            return new BaseResponse<>(res);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    // 사용자 정보 조회
    @Operation(summary = "사용자 정보 조회 - 장채은", description = "사용자 정보를 조회한다. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(1000)요청에 성공했습니다.\n (2014)유저를 찾을 수 없습니다."),
    })
    @GetMapping("/mypage")
    public BaseResponse<UserInfoResponse> getUserInfo(@LoginUser User user) {
        try{
            return new BaseResponse<>(userService.getUserInfo(user));
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

    // login
    @Operation(summary = "로그인", description = "휴대폰번호, 비밀번호로 로그인한다. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(1000)요청에 성공했습니다. \n (2010)유저 아이디 값을 확인해주세요. \n (2030)비밀번호를 입력해주세요. \n (3014)없는 아이디거나 비밀번호가 틀렸습니다."),
    })
    @PostMapping("/log-in")
    public BaseResponse<JwtResponse> loginUser(@RequestBody LoginRequest loginRequest){
        try {
            return new BaseResponse<>(userService.loginUser(loginRequest));
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


}