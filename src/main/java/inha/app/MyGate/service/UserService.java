package inha.app.MyGate.service;

import inha.app.MyGate.config.BaseException;
import inha.app.MyGate.dto.request.SignUpRequest;
import inha.app.MyGate.dto.response.SignUpResponse;
import inha.app.MyGate.model.User;
import inha.app.MyGate.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
@Configuration
@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    public SignUpResponse createUser(SignUpRequest request) throws BaseException {
        User newUser = userRepository.save(request.toEntity());
        return new SignUpResponse(newUser.getUserId(), newUser.getName(), newUser.getPhone_num());
    }
}
