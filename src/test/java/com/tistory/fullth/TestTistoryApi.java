package com.tistory.fullth;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class TestTistoryApi {

    /**
     * tistory.autoReadMe.appId=72b5e8874f2b21c2100221ee966eb861
     * tistory.autoReadMe.secret=72b5e8874f2b21c2100221ee966eb86183dc36ffb47ec29de71586aa2df47adb41f4866a
     * tistory.autoReadMe.serviceUrl=http://fullth.readme.fullth
     * */
    private static final String client_id = "72b5e8874f2b21c2100221ee966eb861";
    private static final String client_secret = "72b5e8874f2b21c2100221ee966eb86183dc36ffb47ec29de71586aa2df47adb41f4866a";
    private static final String redirect_uri = "https://fullth.tistory.com/";

    /**
     * https://www.tistory.com/oauth/authorize?
     *   client_id={client-id}
     *   &redirect_uri={redirect-uri}
     *   &response_type=code
     *   &state={state-param}
     * */
    @Test
    void getAuthorizeCode() {
        String apiURL = "https://www.tistory.com/oauth/authorize?"
                + "client_id=" + client_id + "&redirect_uri=" + redirect_uri + "&response_type=code";

        requestApi(apiURL);
    }

    /**
     * GET https://www.tistory.com/oauth/access_token?
     *   client_id={client-id}
     *   &client_secret={client-secret}
     *   &redirect_uri={redirect-uri}
     *   &code={code}
     *   &grant_type=authorization_code
     * */
    @Test
    void getAccessToken() {
        String code = "af0f88d687db2cdc9c6b9bf48b8c70829657c3edbf452b20b15af171092c4658cc003b9b";

        String apiURL = "https://www.tistory.com/oauth/access_token?"
                + "client_id=" + client_id
                + "&client_secret=" + client_secret
                + "&redirect_uri=" + redirect_uri
                + "&code=" + code
                + "&grant_type=authorization_code";

        requestApi(apiURL);
    }

    /**
     * GET https://www.tistory.com/apis/post/list?
     *   access_token={access-token}
     *   &output={output-type}
     *   &blogName={blog-name}
     *   &page={page-number}
     * */
    @Test
    void getPosts() {
        String access_token = "8f4c1b88ec93951d8e6254f035b16d86_1f554d16f931e793d92facb24fff88ee";
        String output = "UTF-8";
        String blogName = "fullth";
        String page = "1";

        String apiURL = "https://www.tistory.com/apis/post/list?"
                + "access_token=" + access_token
                + "&output=" + output
                + "&blogName=" + blogName
                + "&page=" + page;

        requestApi(apiURL);
    }

    public void requestApi(String apiURL){
        try {
            System.out.println(apiURL);
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
