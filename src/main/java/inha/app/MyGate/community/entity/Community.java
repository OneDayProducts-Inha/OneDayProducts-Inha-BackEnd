package inha.app.MyGate.community.entity;

import inha.app.MyGate.comment.entity.Comment;
import inha.app.MyGate.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "Community")
@Entity
@Getter
@Setter
public class Community {
    @Id
    @Column(name = "communityId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long communityId;
    private String title;
    private String content;
    private String category;
    private boolean status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
