package com.tistory.fullth.post.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public interface TistoryPostService {
    void getAuthorizeCode();
    void getAccessToken();
    void requestApi(String apiURL);
    void getPostList();
}
