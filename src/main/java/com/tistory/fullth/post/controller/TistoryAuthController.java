package com.tistory.fullth.post.controller;

import com.tistory.fullth.post.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/tistory/auth")
public class TistoryAuthController {

    private final AuthService authService;

    public TistoryAuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/code")
    public @ResponseBody
    String getAuthorizeCode() {
        String authorizeCode = authService.getAuthorizeCode();
        return authorizeCode;
    }

    @GetMapping("/token")
    public @ResponseBody String getAccessToken() {
        String accessToken = authService.getAccessToken();
        return accessToken;
    }
}
