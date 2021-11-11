package com.tistory.fullth.post.controller;

import com.tistory.fullth.post.service.TistoryAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/tistory/auth")
public class TistoryAuthController {

    private final TistoryAuthService tistoryAuthService;

    public TistoryAuthController(TistoryAuthService tistoryAuthService) {
        this.tistoryAuthService = tistoryAuthService;
    }

    @GetMapping("/code")
    public @ResponseBody
    String getAuthorizeCode() {
        String authorizeCode = tistoryAuthService.getAuthorizeCode();
        return authorizeCode;
    }

    @GetMapping("/token")
    public @ResponseBody String getAccessToken() {
        String accessToken = tistoryAuthService.getAccessToken();
        return accessToken;
    }
}
