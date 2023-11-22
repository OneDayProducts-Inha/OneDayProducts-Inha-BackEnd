package inha.app.MyGate.community.service;

import inha.app.MyGate.common.Exception.BaseException;
import inha.app.MyGate.community.dto.request.CommentRequest;
import inha.app.MyGate.community.dto.request.PostReq;
import inha.app.MyGate.community.dto.response.CommunityResponse;
import inha.app.MyGate.community.dto.response.PostRes;
import inha.app.MyGate.community.entity.Comment;
import inha.app.MyGate.community.entity.Community;
import inha.app.MyGate.community.repository.CommentRepository;
import inha.app.MyGate.community.repository.CommunityRepository;
import inha.app.MyGate.user.entity.User;
import inha.app.MyGate.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static inha.app.MyGate.common.Exception.BaseResponseStatus.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public void postComment(Long userId, Long communityId, CommentRequest request) throws BaseException {
        User user = userRepository.findByUserIdAndStatus(userId, true).orElseThrow(() -> new BaseException(USER_NOT_FOUND));
        if(!StringUtils.hasText(request.getComment())) throw new BaseException(POST_EMPTY_COMMENT);
        Community community = communityRepository.findByCommunityIdAndStatus(communityId, true).orElseThrow(() -> new BaseException(COMMUNITY_NOT_FOUND));
        commentRepository.save(Comment.toEntity(request.getComment(), user, community));
    }

    // todo : groupby 해결 필요
    public List<CommunityResponse> gerMyComment(Long userId) throws BaseException {
        User user = userRepository.findByUserIdAndStatus(userId, true).orElseThrow(() -> new BaseException(USER_NOT_FOUND));
        return commentRepository.findByUserAndStatus(user, true)
                .stream().map(CommunityResponse::toDto).collect(Collectors.toList());
    }

    public List<CommunityResponse> getMyCommunity(Long userId) throws BaseException {
        User user = userRepository.findByUserIdAndStatus(userId, true).orElseThrow(() -> new BaseException(USER_NOT_FOUND));
        return communityRepository.findByUserAndStatus(user, true)
                .stream().map(CommunityResponse::toDto).collect(Collectors.toList());
    }


    @Transactional
    public PostRes createPost(PostReq postReq) {
        Community community = communityRepository.save(postReq.toEntity());

        return new PostRes(community.getTitle(), community.getContent(), community.getCategory());
    }

    public List<CommunityResponse> getCommunityCtgList(String category) throws BaseException {
        return communityRepository.findByCategoryAndStatus(category, true)
                .stream().map(CommunityResponse::toDto).collect(Collectors.toList());
    }

    public List<CommunityResponse> getCommunityList() throws BaseException {
        return communityRepository.findByStatus(true)
                .stream().map(CommunityResponse::toDto).collect(Collectors.toList());
    }

    public CommunityResponse getPostInfo(Long id) throws BaseException {
        Community community = communityRepository.findByCommunityIdAndStatus(id, true)
                .orElseThrow(() -> new BaseException(COMMUNITY_ID_NOT_FOUND));

        return CommunityResponse.toDto(community);
    }

    public List<CommunityResponse> searchCommunities(String keyword) {
        List<Community> communities = communityRepository.findByTitleContaining(keyword);
        return communities.stream()
                .map(CommunityResponse::toDto)
                .collect(Collectors.toList());
    }
}
