package com.tistory.fullth.post.controller;

import com.tistory.fullth.post.dto.PostRequestDTO;
import com.tistory.fullth.post.service.AuthService;
import com.tistory.fullth.post.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
@Controller
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

    @GetMapping("/xml")
    public void getParseXML() throws ParserConfigurationException, IOException, SAXException {
    }

    @PostMapping("/post")
    public Long save(@RequestBody final PostRequestDTO params) {
        return postService.save(params);
    }
}
