package inha.app.MyGate.user.service;

import inha.app.MyGate.common.Exception.BaseException;
import inha.app.MyGate.user.dto.request.SignUpRequest;
import inha.app.MyGate.user.dto.response.SignUpResponse;
import inha.app.MyGate.user.entity.User;
import inha.app.MyGate.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
@Configuration
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public SignUpResponse createUser(SignUpRequest request) throws BaseException {
        User newUser = userRepository.save(request.toEntity());
        return new SignUpResponse(newUser.getUserId(), newUser.getName(), newUser.getPhone_num());
    }
}
