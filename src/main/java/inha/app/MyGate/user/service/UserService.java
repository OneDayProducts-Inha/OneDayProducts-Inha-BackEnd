package inha.app.MyGate.user.service;

import inha.app.MyGate.common.Exception.BaseException;
import inha.app.MyGate.user.dto.request.LoginRequest;
import inha.app.MyGate.user.dto.request.UserInfoRequest;
import inha.app.MyGate.user.dto.response.LoginResponse;
import inha.app.MyGate.user.dto.response.SignUpResponse;
import inha.app.MyGate.user.dto.response.UserInfoResponse;
import inha.app.MyGate.user.entity.User;
import inha.app.MyGate.user.repository.UserRepository;
import inha.app.MyGate.utils.JwtService;
import inha.app.MyGate.utils.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private JwtService jwtService;

    @Transactional
    public SignUpResponse createUser(UserInfoRequest request) throws BaseException {
        User newUser = userRepository.save(request.toEntity());
        return new SignUpResponse(newUser.getUserId(), newUser.getName(), newUser.getPhone_num());
    }

    // 사용자 정보 조회
    public UserInfoResponse getUserInfo(Long userId) throws BaseException {
        User user = userRepository.findByUserIdAndStatus(userId, true).orElseThrow(() -> new BaseException(USER_NOT_FOUND));
        return UserInfoResponse.toDto(user);
    }

    @Transactional
    public void updateUserInfo(Long userId, UserInfoRequest request) throws BaseException {
        User user = userRepository.findByUserIdAndStatus(userId, true).orElseThrow(() -> new BaseException(USER_NOT_FOUND));
        user.updateUser(request);
        userRepository.save(user);
    }

    // Login
    public LoginResponse loginUser(LoginRequest loginRequest) throws BaseException{
        User user = userRepository.findUserByPhoneNum(loginRequest.getPhoneNum());
        String encryptPw;
        try {
            encryptPw = new SHA256().encrypt(loginRequest.getPw());
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }

        String originalEncryptPw = new SHA256().encrypt(user.getPw());
        if (originalEncryptPw.equals(encryptPw)) {
            String jwt = jwtService.createJwt(user.getUserId());
            return new LoginResponse(user.getUserId(), user.getName(), user.getPhone_num(), jwt); // 바꿀예정
        } else {
            throw new BaseException(FAILED_TO_LOGIN);
        }


    }
}
