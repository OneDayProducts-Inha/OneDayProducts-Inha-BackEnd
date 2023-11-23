package inha.app.MyGate.common.resolver;

import inha.app.MyGate.common.Exception.BaseException;
import inha.app.MyGate.common.Exception.BaseResponseStatus;
import inha.app.MyGate.user.entity.User;
import inha.app.MyGate.user.repository.UserRepository;
import inha.app.MyGate.utils.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

import static inha.app.MyGate.common.Exception.BaseResponseStatus.INVALID_JWT;

@Slf4j
@AllArgsConstructor
public class LoginUserArgumentResolver  implements HandlerMethodArgumentResolver {
    private JwtService jwtTokenProvider;
    private UserRepository userRepository;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("supportParameter 실행");
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(LoginUser.class);
        boolean hasUserType = User.class.isAssignableFrom(parameter.getParameterType());
        return hasLoginAnnotation && hasUserType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info("resolveArgument 실행");
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String token = extractJwtToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            throw new BaseException(INVALID_JWT);
        }
        String id = jwtTokenProvider.getUserIdFromToken(token);
        try{
            return userRepository.findByUserIdAndStatus(Long.valueOf(id), true).orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_FOUND));
        }catch (NumberFormatException e){
            throw new BaseException(INVALID_JWT);
        }
    }

    private String extractJwtToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
