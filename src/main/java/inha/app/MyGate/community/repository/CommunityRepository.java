package inha.app.MyGate.community.repository;

import inha.app.MyGate.community.entity.Community;
import inha.app.MyGate.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    Optional<Community> findByCommunityIdAndStatus(Long communityId, Boolean status);
    List<Community> findByUserAndStatus(User user, Boolean status);

    List<Community> findByCategoryAndStatus(String category, Boolean status);
    List<Community> findByStatus(Boolean status);
    List<Community> findByTitleContaining(String keyword);
    List<Community> findByCategoryContainingAndTitleContaining(String category, String title);
}
