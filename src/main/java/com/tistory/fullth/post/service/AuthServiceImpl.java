package com.tistory.fullth.post.service;

import com.tistory.fullth.post.config.Properties;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service("authService")
public class AuthServiceImpl implements AuthService {

    private final Properties properties;

    public AuthServiceImpl(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String getAuthorizeCode() {
        String apiURL = "https://www.tistory.com/oauth/authorize?"
                + "client_id=" + properties.getClientId() + "&redirect_uri=" + properties.getRedirectUrl() + "&response_type=code";

        return apiURL;
    }
    @Override
    public String getAccessToken() {
        String apiURL = "https://www.tistory.com/oauth/access_token?"
                + "client_id=" + properties.getClientId()
                + "&client_secret=" + properties.getClientSecret()
                + "&redirect_uri=" + properties.getRedirectUrl()
                + "&code=" + properties.getCode()
                + "&grant_type=authorization_code";

        return requestApi(apiURL);
    }

    @Override
    public String requestApi(String apiURL){ // TODO: Separate method
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
            System.out.println(response.toString()); // TODO: Add Logger
            return response.toString();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}

