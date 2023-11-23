package inha.app.MyGate.user.entity;

import inha.app.MyGate.common.entity.BaseEntity;
import inha.app.MyGate.user.dto.request.UserInfoRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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
    private String location;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate birth;
    private String imgUrl;

    @Builder
    public User(String userName, String phoneNum, String pw, String location, Gender gender, LocalDate birth, String imgUrl) {
        this.userName = userName;
        this.phoneNum = phoneNum;
        this.pw = pw;
        this.location = location;
        this.gender = gender;
        this.birth = birth;
        this.imgUrl = imgUrl;
    }

    public void updateUser(UserInfoRequest request) {
        if (!request.getPhoneNum().equals(phoneNum)) phoneNum = request.getPhoneNum();
        if (!request.getName().equals(userName)) phoneNum = request.getPhoneNum();
        if (!request.getPhoneNum().equals(pw)) phoneNum = request.getPhoneNum();
    }

    public static int getAmericanAge(LocalDate date) {
        LocalDate now  = LocalDate.now();
        int americanAge = now.minusYears(date.getYear()).getYear(); // (1)
        if (date.plusYears(americanAge).isAfter(now)) {
            americanAge = americanAge -1;
        }
        return americanAge;
    }
}
