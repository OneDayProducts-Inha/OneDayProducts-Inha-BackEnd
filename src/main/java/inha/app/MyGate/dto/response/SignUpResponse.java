package inha.app.MyGate.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpResponse {
    private Long id;
    private String name;
    private String phoneNum;
}
