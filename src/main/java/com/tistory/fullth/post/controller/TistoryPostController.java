package com.tistory.fullth.post.controller;

import com.tistory.fullth.post.service.TistoryPostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/tistory/post")
public class TistoryPostController {

    private final TistoryPostService tistoryPostService;

    public TistoryPostController(TistoryPostService tistoryPostService) {
        this.tistoryPostService = tistoryPostService;
    }

    @GetMapping("/list")
    public @ResponseBody String getPostList() {
        String postList = tistoryPostService.getPostList();
        return postList;
    }
}
