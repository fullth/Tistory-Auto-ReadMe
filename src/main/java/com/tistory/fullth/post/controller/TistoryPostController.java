package com.tistory.fullth.post.controller;

import com.tistory.fullth.post.service.AuthService;
import com.tistory.fullth.post.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tistory/post")

public class TistoryPostController {
    private final PostService postService;
    private final AuthService authService;

    public TistoryPostController(PostService postService, AuthService authService) {
        this.postService = postService;
        this.authService = authService;
    }

    @GetMapping("/list")
    public @ResponseBody String getPostList() {
        String postList = postService.getPostList();
        return postList;
    }
}
