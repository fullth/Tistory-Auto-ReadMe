package com.tistory.fullth.post.controller;

import com.tistory.fullth.post.service.TistoryPostService;
import com.tistory.fullth.post.service.TistoryPostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/tistory/v1")
public class TistoryPostController {

    private final TistoryPostService tistoryPostService;

    public TistoryPostController(TistoryPostService tistoryPostService) {
        this.tistoryPostService = tistoryPostService;
    }

    @GetMapping("/test")
    public void test() {
        tistoryPostService.getAuthorizeCode();
    }
}
