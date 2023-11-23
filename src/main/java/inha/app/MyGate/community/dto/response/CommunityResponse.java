package inha.app.MyGate.community.dto.response;

import inha.app.MyGate.community.entity.Comment;
import inha.app.MyGate.community.entity.Community;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CommunityResponse {
    @Schema(type = "Long", description = "커뮤니티 id", example = "댓글!", required = true)
    private Long communityId;
    @Schema(type = "String", description = "제목", example = "바둑하실 분", required = true)
    private String title;
    @Schema(type = "String", description = "내용", example = "바둑 같이 하실분 구해요", required = true)
    private String content;
    @Schema(type = "String", description = "카테고리", example = "취미", required = true)
    private String category;

    public static CommunityResponse toDto(Community community){
        return CommunityResponse.builder()
                .communityId(community.getCommunityId())
                .title(community.getTitle())
                .content(community.getContent())
                .category(community.getCategory().getValue())
                .build();
    }

    public static CommunityResponse toDto(Comment comment){
        return CommunityResponse.builder()
                .communityId(comment.getCommunity().getCommunityId())
                .title(comment.getCommunity().getTitle())
                .content(comment.getCommunity().getContent())
                .category(comment.getCommunity().getCategory().getValue())
                .build();
    }
}
