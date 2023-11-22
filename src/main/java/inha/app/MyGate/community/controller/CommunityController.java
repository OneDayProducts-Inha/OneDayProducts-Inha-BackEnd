package inha.app.MyGate.community.controller;

import inha.app.MyGate.common.Exception.BaseException;
import inha.app.MyGate.common.Exception.BaseResponse;
import inha.app.MyGate.common.Exception.BaseResponseStatus;
import inha.app.MyGate.community.dto.request.CommentRequest;
import inha.app.MyGate.community.dto.request.PostReq;
import inha.app.MyGate.community.dto.response.CommunityResponse;
import inha.app.MyGate.community.dto.response.PostRes;
import inha.app.MyGate.community.service.CommunityService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

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
    public BaseResponse<List<CommunityResponse>> getMyCommunity() {
        try{
            Long userId = 1L;
            return new BaseResponse<>(communityService.getMyCommunity(userId));
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
                                          @RequestBody CommentRequest request) {
        try{
            Long userId = 1L;
            communityService.postComment(userId, communityId, request);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }
    @ResponseBody
    @Operation(summary = "커뮤니티 글 작성", description = "커뮤니티 글을 작성한다. ")
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
            List<CommunityResponse> list = communityService.getCommunityCtgList(category);
            System.out.println(list);
            return new BaseResponse<>(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/postList")
    @Operation(summary = "커뮤니티 글 전체 list 조회", description = "커뮤니티 글 리스트를 전체 조회한다.")
    public BaseResponse<List<CommunityResponse>> getCommList() {
        try {
            List<CommunityResponse> list = communityService.getCommunityList();
            System.out.println(list);
            return new BaseResponse<>(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
