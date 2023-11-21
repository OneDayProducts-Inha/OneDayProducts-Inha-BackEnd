package inha.app.MyGate.community.service;

import inha.app.MyGate.community.dto.request.PostReq;
import inha.app.MyGate.community.dto.response.PostRes;
import inha.app.MyGate.community.entity.Community;
import inha.app.MyGate.community.repository.CommunityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class CommunityService {
    private final CommunityRepository communityRepository;

    public Community save(Community community) {
        return communityRepository.save(community);
    }
    @Transactional
    public PostRes createPost(PostReq postReq) {
        Community community = communityRepository.save(postReq.toEntity());

        return new PostRes(community.getTitle(), community.getContent(), community.getCategory());
    }
}
