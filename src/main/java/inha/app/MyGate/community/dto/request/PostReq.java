package inha.app.MyGate.community.dto.request;


import inha.app.MyGate.common.Exception.BaseException;
import inha.app.MyGate.community.entity.Category;
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


    public Community toEntity(User user) throws BaseException {
        Community communityEntity = Community.builder()
                .title(title)
                .content(content)
                .category(Category.getCategoryByValue(category))
                .user(user)
                .build();
        return communityEntity;
    }
}
