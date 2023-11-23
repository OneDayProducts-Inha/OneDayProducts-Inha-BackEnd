package inha.app.MyGate.community.controller;

import inha.app.MyGate.common.Exception.BaseException;
import inha.app.MyGate.common.Exception.BaseResponse;
import inha.app.MyGate.common.Exception.BaseResponseStatus;
import inha.app.MyGate.common.resolver.LoginUser;
import inha.app.MyGate.community.dto.request.CommentRequest;
import inha.app.MyGate.community.dto.request.PostReq;
import inha.app.MyGate.community.dto.response.CommunityRes;
import inha.app.MyGate.community.dto.response.CommunityResponse;
import inha.app.MyGate.community.dto.response.PostRes;
import inha.app.MyGate.community.service.CommunityService;
import inha.app.MyGate.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static inha.app.MyGate.common.Exception.BaseResponseStatus.SUCCESS;

@Api(tags = "커뮤니티", value = "커뮤니티 관리 기능 구현한 community Controller 입니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {
    private final CommunityService communityService;


    @Operation(summary = "사용자 작성 게시물 - 장채은", description = "사용자가 작성한 게시글을 확인한다. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(1000)요청에 성공했습니다."),
    })
    @PostMapping("/mypost")
    public BaseResponse<List<CommunityResponse>> getMyCommunity(@LoginUser User user) {
        try{
            return new BaseResponse<>(communityService.getMyCommunity(user));
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @Operation(summary = "사용자 작성 댓글 - 장채은", description = "사용자가 작성한 댓글을 확인한다. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(1000)요청에 성공했습니다."),
    })
    @PostMapping("/mycomment")
    public BaseResponse<List<CommunityResponse>> gerMyComment() {
        try{
            Long userId = 1L;
            return new BaseResponse<>(communityService.gerMyComment(userId));
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @Operation(summary = "커뮤니티 댓글 - 장채은", description = "커뮤니티 댓글을 생성한다. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "(1000)요청에 성공했습니다.\n (2014)유저를 찾을 수 없습니다. \n(2019)댓글을 입력해주세요. \n (2020)커뮤니티 글을 찾을 수 없습니다."),
    })
    @PostMapping("/comment/{communityId}")
    public BaseResponse<Void> postComment(@Parameter(description = "(Long) 커뮤니티 id", example = "1") @PathVariable(name = "communityId") Long communityId,
                                          @RequestBody CommentRequest request,
                                          @LoginUser User user) {
        try{
            communityService.postComment(user, communityId, request);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }
    @ResponseBody
    @Operation(summary = "커뮤니티 글 작성", description = "커뮤니티 글을 작성한다.")
    @PostMapping("/post")
    public BaseResponse<String> createPost(@RequestBody PostReq postReq){
        try{
            PostRes postRes = communityService.createPost(postReq);
            System.out.println(postRes.toString());
            return new BaseResponse<>(SUCCESS);
        }catch (Exception e){
            return new BaseResponse<>(e.getMessage());
        }
    }

    @GetMapping("/category/list")
    @Operation(summary = "커뮤니티 글 카테고리별 list 조회", description = "커뮤니티 글 리스트를 카테고리별로 조회한다.")
    public BaseResponse<List<CommunityResponse>> getCommCtgList(@RequestParam("category") String category) {
        try {
            return new BaseResponse<>(communityService.getCommunityCtgList(category));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/postList")
    @Operation(summary = "커뮤니티 글 전체 list 조회", description = "커뮤니티 글 리스트를 전체 조회한다.")
    public BaseResponse<List<CommunityResponse>> getCommList() {
        try {
            return new BaseResponse<>(communityService.getCommunityList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/post/{communityId}")
    @Operation(summary = "커뮤니티 글 상세보기", description = "커뮤니티 글 정보를 상세 조회한다.")
    public BaseResponse<CommunityRes> getPostInfo(@PathVariable(name = "communityId") Long communityId) {
        try {
            CommunityRes communityRes = communityService.getPostInfo(communityId);
           return new BaseResponse<>(communityRes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/post/search")
    @Operation(summary = "커뮤니티 글 검색(전체)", description = "커뮤니티에 작성된 전체 글 중 제목에 keword가 포함된 글을 검색한다.")
    public BaseResponse<List<CommunityResponse>> searchCommunities(@RequestParam String keyword) {
        return new BaseResponse<>(communityService.searchCommunities(keyword));
    }

    @GetMapping("/post/search-category")
    @Operation(summary = "커뮤니티 카테고리별 글 검색", description = "커뮤니티에서 해당 카테고리에 작성된 글 중 제목에 keword가 포함된 글을 검색한다.")
    public BaseResponse<List<CommunityResponse>> searchCommunitiesByCategoryAndTitle(@RequestParam String category, @RequestParam String keyword) {
        return new BaseResponse<>(communityService.searchCommunitiesByCategory(category, keyword));
    }
}
