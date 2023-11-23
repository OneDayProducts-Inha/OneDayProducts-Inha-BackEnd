package inha.app.MyGate.community.dto.response;


import inha.app.MyGate.community.entity.Comment;
import inha.app.MyGate.community.entity.Community;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class CommunityRes {
    private Long communityId;
    private String title;
    private String content;
    private String category;
    private List<Comment> commentList;

    public static CommunityRes toDto(Community community){
        return CommunityRes.builder()
                .communityId(community.getCommunityId())
                .title(community.getTitle())
                .content(community.getContent())
                .category(community.getCategory())
                .commentList(community.getComments()!= null ? community.getComments() : Collections.emptyList())
                .build();
    }
}
