package inha.app.MyGate.user.entity;

import inha.app.MyGate.comment.entity.Comment;
import inha.app.MyGate.common.entity.BaseEntity;
import inha.app.MyGate.community.entity.Community;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "userName")
    private String name;

    @Column(name = "phone_num")
    private String phone_num;

    private String pw;


    @Builder
    public User(String name, String phone_num, String pw, boolean status) {
        this.name = name;
        this.phone_num = phone_num;
        this.pw = pw;
    }
}
