package inha.app.MyGate.user.entity;

import inha.app.MyGate.common.entity.BaseEntity;
import inha.app.MyGate.user.dto.request.UserInfoRequest;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    private String phoneNum;

    private String pw;


    @Builder
    public User(String name, String phone_num, String pw, boolean status) {
        this.userName = name;
        this.phoneNum = phone_num;
        this.pw = pw;
    }

    public void updateUser(UserInfoRequest request) {
        if (!request.getPhoneNum().equals(phoneNum)) phoneNum = request.getPhoneNum();
        if (!request.getName().equals(userName)) phoneNum = request.getPhoneNum();
        if (!request.getPhoneNum().equals(pw)) phoneNum = request.getPhoneNum();
    }
}
