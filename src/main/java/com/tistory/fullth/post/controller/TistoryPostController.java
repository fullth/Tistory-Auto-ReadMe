package com.tistory.fullth.post.controller;

import com.tistory.fullth.post.service.TistoryAuthService;
import com.tistory.fullth.post.service.TistoryPostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/tistory/post")
public class TistoryPostController {

    private final TistoryPostService tistoryPostService;
    private final TistoryAuthService tistoryAuthService;

    public TistoryPostController(TistoryPostService tistoryPostService, TistoryAuthService tistoryAuthService) {
        this.tistoryPostService = tistoryPostService;
        this.tistoryAuthService = tistoryAuthService;
    }

    @GetMapping("/list")
    public @ResponseBody String getPostList() {
        String postList = tistoryPostService.getPostList();
        return postList;
    }

    @GetMapping("/xml")
    public void getParseXML() throws ParserConfigurationException, IOException, SAXException {
    }
}
