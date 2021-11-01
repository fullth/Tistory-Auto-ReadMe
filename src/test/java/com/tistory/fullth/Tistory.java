package com.tistory.fullth;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Tistory {

    /**
     * https://www.tistory.com/oauth/authorize?
     *   client_id={client-id}
     *   &redirect_uri={redirect-uri}
     *   &response_type=code
     *   &state={state-param}
     * */
    @Test
    void getAuthorizeCode() {
        String client_id = "";
        String redirect_uri = "";

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
        String client_id = "";
        String client_secret = "";
        String redirect_uri = "";
        String code = "";

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
        String access_token = "";
        String output = "UTF-8";
        String blogName = "";
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
