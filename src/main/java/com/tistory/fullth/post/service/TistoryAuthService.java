package com.tistory.fullth.post.service;

import org.springframework.stereotype.Service;

@Service
public interface TistoryAuthService {
    String getAuthorizeCode();
    String getAccessToken();
    String requestApi(String apiURL);
}
