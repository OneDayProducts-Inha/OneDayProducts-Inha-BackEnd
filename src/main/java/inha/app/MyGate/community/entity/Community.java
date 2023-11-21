package inha.app.MyGate.community.entity;

import inha.app.MyGate.common.entity.BaseEntity;
import inha.app.MyGate.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community extends BaseEntity {
    @Id
    @Column(name = "community_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long communityId;
    private String title;
    private String content;
    private String category;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Community(String title, String content, String category, List<Comment> comments, User user) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.comments = comments;
        this.user = user;
    }
}
