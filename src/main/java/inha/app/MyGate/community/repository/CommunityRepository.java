package inha.app.MyGate.community.repository;

import inha.app.MyGate.community.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    Optional<Community> findByCommunityIdAndStatus(Long communityId, Boolean status);
}
