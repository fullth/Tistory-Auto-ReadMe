package com.tistory.fullth.post.service;

import com.tistory.fullth.post.config.Properties;
import org.springframework.stereotype.Service;

@Service("tistoryPostService")
public class TistoryPostServiceImpl implements TistoryPostService{

    private final Properties properties;

    private final TistoryAuthService tistoryAuthService;

    public TistoryPostServiceImpl(Properties properties,
                                  TistoryAuthService tistoryAuthService) {
        this.properties = properties;
        this.tistoryAuthService = tistoryAuthService;
    }

    @Override
    public String getPostList() {
        String access_token = properties.getToken();
        String output = "UTF-8";
        String blogName = properties.getBlogName();
        String page = "1";

        String apiURL = "https://www.tistory.com/apis/post/list?"
                + "access_token=" + access_token
                + "&output=" + output
                + "&blogName=" + blogName
                + "&page=" + page;

        return tistoryAuthService.requestApi(apiURL);
    }
}
