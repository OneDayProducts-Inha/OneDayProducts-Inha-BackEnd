package inha.app.MyGate.community.controller;

import inha.app.MyGate.common.Exception.BaseResponse;
import inha.app.MyGate.community.dto.request.PostReq;
import inha.app.MyGate.community.dto.response.PostRes;
import inha.app.MyGate.community.service.CommunityService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static inha.app.MyGate.common.Exception.BaseResponseStatus.SUCCESS;

@Api(tags = "커뮤니티", value = "커뮤니티 관리 기능 구현한 community Controller 입니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {
    private final CommunityService communityService;

    //글 작성
    @ResponseBody
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
}
