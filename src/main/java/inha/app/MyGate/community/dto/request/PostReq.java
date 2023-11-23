package inha.app.MyGate.community.dto.request;


import inha.app.MyGate.community.entity.Community;
import inha.app.MyGate.user.entity.User;
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


    public Community toEntity(User user) {
        Community communityEntity = Community.builder()
                .title(title)
                .content(content)
                .category(category)
                .user(user)
                .build();
        return communityEntity;
    }
}
