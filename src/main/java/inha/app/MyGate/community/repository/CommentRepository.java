package inha.app.MyGate.community.repository;

import inha.app.MyGate.community.entity.Comment;
import inha.app.MyGate.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByCommentIdAndStatus(Long commentId, Boolean status);
    List<Comment> findByUserAndStatus(User user, Boolean status);
}
