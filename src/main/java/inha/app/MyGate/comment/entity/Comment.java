package inha.app.MyGate.comment.entity;

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
    private boolean status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;
}
