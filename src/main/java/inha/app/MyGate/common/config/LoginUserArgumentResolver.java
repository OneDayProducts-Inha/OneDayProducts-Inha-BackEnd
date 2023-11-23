package inha.app.MyGate.common.config;

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
import javax.servlet.http.HttpSession;

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
        HttpSession session = request.getSession(false);
        String token = extractJwtToken(request);
        if(session == null){
            return null;
        }
        if(token != null && jwtTokenProvider.validateToken(token)){
            String phoneNum = jwtTokenProvider.getPhoneNumFromToken(token);

            // 여기서는 간단하게 phoneNum을 기반으로 User 객체를 생성해서 반환합니다.
            User user = userRepository.findUserByPhoneNum(phoneNum);
            return user;
        }
        return session.getAttribute(SessionConst.LOGIN_USER);
    }

    private String extractJwtToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
