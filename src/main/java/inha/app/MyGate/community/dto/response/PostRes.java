package inha.app.MyGate.community.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class PostRes {
    private String title;
    private String content;
    private String category;
}
