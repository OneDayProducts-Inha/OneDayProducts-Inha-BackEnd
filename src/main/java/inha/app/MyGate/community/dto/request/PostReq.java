package inha.app.MyGate.community.dto.request;


import inha.app.MyGate.community.entity.Community;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostReq {
    private String title;
    private String content;
    private String category;


    public Community toEntity() {
        Community communityEntity = Community.builder()
                .title(title)
                .content(content)
                .category(category)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .status(true)
                .build();
        return communityEntity;
    }
}
