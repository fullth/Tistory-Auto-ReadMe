package com.tistory.fullth.post.service;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String getAuthorizeCode();
    String getAccessToken();
    String requestApi(String apiURL);
}
