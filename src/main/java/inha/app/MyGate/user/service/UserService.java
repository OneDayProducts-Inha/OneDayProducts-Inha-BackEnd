package inha.app.MyGate.user.service;

import inha.app.MyGate.common.Exception.BaseException;
import inha.app.MyGate.user.dto.request.LoginRequest;
import inha.app.MyGate.user.dto.request.UserInfoRequest;
import inha.app.MyGate.user.dto.response.JwtResponse;
import inha.app.MyGate.user.dto.response.UserInfoResponse;
import inha.app.MyGate.user.entity.Gender;
import inha.app.MyGate.user.entity.User;
import inha.app.MyGate.user.repository.UserRepository;
import inha.app.MyGate.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static inha.app.MyGate.common.Exception.BaseResponseStatus.*;

/**
 * todo: save 가 필요한 API 생성 시 transactional 어노테이션을 붙여주세요
 */
@Configuration
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Transactional
    public JwtResponse createUser(UserInfoRequest request) throws BaseException {
        // 이미 있는 휴대폰 번호면 예외처리
        Optional<User> existUser = userRepository.existsByPhoneNum(request.getPhoneNum());
        if(existUser != null){
            throw new BaseException(USERS_EXISTS_PHONE_NUM);
        }
        User user = User.toEntity(request);
        userRepository.save(user);
        System.out.println("Gender: " + user.getGender());
        return new JwtResponse(jwtService.createJwt(user.getUserId()));
    }

    // 사용자 정보 조회
    public UserInfoResponse getUserInfo(User user) throws BaseException {
        return UserInfoResponse.toDto(user);
    }

    @Transactional
    public void updateUserInfo(Long userId, UserInfoRequest request) throws BaseException {
        User user = userRepository.findByUserIdAndStatus(userId, true).orElseThrow(() -> new BaseException(USER_NOT_FOUND));
        user.updateUser(request);
        userRepository.save(user);
    }

    // Login
    public JwtResponse loginUser(LoginRequest loginRequest) throws BaseException{
        User user = userRepository.findByPhoneNumAndStatus(loginRequest.getPhoneNum(), true).orElseThrow(() -> new BaseException(USER_NOT_FOUND));
        //  비밀번호 맞는지 확인
        if(!user.getPw().equals(loginRequest.getPw())){
            throw new BaseException(FAILED_TO_LOGIN);
        }
        // 토큰 생성
        return new JwtResponse(jwtService.createJwt(user.getUserId()));
    }
}
