package inha.app.MyGate.user.service;

import inha.app.MyGate.common.Exception.BaseException;
import inha.app.MyGate.user.dto.request.UserInfoRequest;
import inha.app.MyGate.user.dto.response.SignUpResponse;
import inha.app.MyGate.user.dto.response.UserInfoResponse;
import inha.app.MyGate.user.entity.User;
import inha.app.MyGate.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static inha.app.MyGate.common.Exception.BaseResponseStatus.USERS_EMPTY_USER_ID;

/**
 * todo: save 가 필요한 API 생성 시 transactional 어노테이션을 붙여주세요
 */
@Configuration
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public SignUpResponse createUser(UserInfoRequest request) throws BaseException {
        User newUser = userRepository.save(request.toEntity());
        return new SignUpResponse(newUser.getUserId(), newUser.getName(), newUser.getPhone_num());
    }

    // 사용자 정보 조회
    public UserInfoResponse getUserInfo(Long userId) throws BaseException {
        User user = userRepository.findByUserIdAndStatus(userId, true).orElseThrow(() -> new BaseException(USERS_EMPTY_USER_ID));
        return UserInfoResponse.toDto(user);
    }

    @Transactional
    public void updateUserInfo(Long userId, UserInfoRequest request) throws BaseException {
        User user = userRepository.findByUserIdAndStatus(userId, true).orElseThrow(() -> new BaseException(USERS_EMPTY_USER_ID));
        user.updateUser(request);
        userRepository.save(user);
    }
}
