package inha.app.MyGate.community.entity;

import inha.app.MyGate.common.entity.BaseEntity;
import inha.app.MyGate.community.entity.Community;
import inha.app.MyGate.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @Builder
    public Comment(String content, User user, Community community) {
        this.content = content;
        this.user = user;
        this.community = community;
    }

    public static Comment toEntity(String content, User user, Community community){
        return Comment.builder()
                .content(content)
                .user(user)
                .community(community)
                .build();
    }
}
