package com.tistory.fullth.post.service;

public interface AuthService {
    String getAuthorizeCode();
    String getAccessToken();
    String requestApi(String apiURL);
}
