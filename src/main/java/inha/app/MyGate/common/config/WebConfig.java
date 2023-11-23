package inha.app.MyGate.common.config;

import inha.app.MyGate.common.resolver.LoginUserArgumentResolver;
import inha.app.MyGate.user.repository.UserRepository;
import inha.app.MyGate.utils.JwtService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginResolver;
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers){
        resolvers.add(loginResolver);
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*") // url 패턴
                .allowedOriginPatterns("")
                // todo: server url 생성 시 변경 필요
                .allowedOrigins("*")
                .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PATCH.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name(),
                        HttpMethod.HEAD.name(), HttpMethod.TRACE.name(), HttpMethod.OPTIONS.name()) // 허용 method
                .allowedHeaders("Authorization", "Content-Type"); // 허용 header
                //.allowCredentials(true);
    }

}
