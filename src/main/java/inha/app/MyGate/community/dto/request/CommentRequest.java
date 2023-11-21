package inha.app.MyGate.community.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    @Schema(type = "String", description = "댓글", example = "댓글!", required = true)
    private String comment;
}
