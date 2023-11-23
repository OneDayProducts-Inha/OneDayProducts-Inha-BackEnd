package inha.app.MyGate.user.repository;

import inha.app.MyGate.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserIdAndStatus(Long userId, Boolean status);
    Optional<User> findByPhoneNumAndStatus(String phone, Boolean status);
}
