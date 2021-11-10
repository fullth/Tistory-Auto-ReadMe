package com.tistory.fullth.post.service;

import com.tistory.fullth.post.config.Properties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tistoryPostService")
public class TistoryPostServiceImpl implements TistoryPostService{

    private final Properties properties;

    public TistoryPostServiceImpl(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void getAuthorizeCode() {
        String apiURL = "https://www.tistory.com/oauth/authorize?"
                + "client_id=" + properties.getClientId() + "&redirect_uri=" + properties.getRedirectUrl() + "&response_type=code";

        System.out.println(apiURL);
    }

    @Override
    public void getAccessToken() {

    }

    @Override
    public void requestApi(String apiURL) {

    }

    @Override
    public void getPostList() {

    }
}

