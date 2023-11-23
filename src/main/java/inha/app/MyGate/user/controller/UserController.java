package inha.app.MyGate.user.controller;

import inha.app.MyGate.common.Exception.BaseException;
import inha.app.MyGate.common.Exception.BaseResponse;
import inha.app.MyGate.common.Exception.BaseResponseStatus;
import inha.app.MyGate.common.resolver.LoginUser;
import inha.app.MyGate.user.dto.request.LoginRequest;
import inha.app.MyGate.user.dto.request.UserInfoRequest;
import inha.app.MyGate.user.dto.response.LoginResponse;
import inha.app.MyGate.user.dto.response.SignUpResponse;
import inha.app.MyGate.user.dto.response.UserInfoResponse;
import inha.app.MyGate.user.entity.User;
import inha.app.MyGate.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static inha.app.MyGate.common.Exception.BaseResponseStatus.POST_USERS_EMPTY_PW;
import static inha.app.MyGate.common.Exception.BaseResponseStatus.USERS_EMPTY_USER_ID;

@Api(tags = "유저", value = "회원 정보 관리 기능 구현한 User Controller 입니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    // 회원가입
    @Operation(summary = "회원가입", description = "휴대폰번호, 이름, 비밀번호로 회원가입한다. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(1000)요청에 성공했습니다."),
    })
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

    // login
    @Operation(summary = "로그인", description = "휴대폰번호, 비밀번호로 로그인한다. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(1000)요청에 성공했습니다. \n (2010)유저 아이디 값을 확인해주세요. \n (2030)비밀번호를 입력해주세요. \n (3014)없는 아이디거나 비밀번호가 틀렸습니다."),
    })
    @GetMapping("/log-in")
    public BaseResponse<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) throws BaseException {
        try {
            // stringUtils.hasText -> true, false
//            // null값 체크
//            if (loginRequest.getPhoneNum() == null) {
//                return new BaseResponse<>(USERS_EMPTY_USER_ID);
//
//            }
//            if (loginRequest.getPw() == null) {
//                return new BaseResponse<>(POST_USERS_EMPTY_PW);
//            }
//
//            LoginResponse res = userService.loginUser(loginRequest);
//
//
//            User loggedInUser = null;
//            loggedInUser.setUserId(res.getId());
//            loggedInUser.setUserName(res.getName());
//            loggedInUser.setPhoneNum(res.getPhoneNum());
//            return new BaseResponse<>(res);
            return new BaseResponse<>(this.userService.loginUser(loginRequest));

        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }





    // ArgumentResolver 사용해 세션 검증을 하는 방식
    @GetMapping("/")
    public String homeLoginArgumentResolver(@LoginUser User loginUser, Model model){
        // 세션에 회원 데이터가 없으면 home
        if(loginUser == null){
            return "home";
        }

        // 세션이 유지되면 로그인으로 이동
        model.addAttribute("user", loginUser);
        return "loginHome";
    }

}
