package inha.app.MyGate.user.service;

import inha.app.MyGate.common.Exception.BaseException;
import inha.app.MyGate.user.dto.request.SignUpRequest;
import inha.app.MyGate.user.dto.response.SignUpResponse;
import inha.app.MyGate.user.dto.response.UserInfoResponse;
import inha.app.MyGate.user.entity.User;
import inha.app.MyGate.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static inha.app.MyGate.common.Exception.BaseResponseStatus.USERS_EMPTY_USER_ID;

@Configuration
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public SignUpResponse createUser(SignUpRequest request) throws BaseException {
        User newUser = userRepository.save(request.toEntity());
        return new SignUpResponse(newUser.getUserId(), newUser.getName(), newUser.getPhone_num());
    }

    // 사용자 정보 조회
    public UserInfoResponse getUserInfo(Long userId) throws BaseException {
        User user = userRepository.findByUserIdAndStatus(userId, true).orElseThrow(() -> new BaseException(USERS_EMPTY_USER_ID));
        return UserInfoResponse.toDto(user);
    }
}
