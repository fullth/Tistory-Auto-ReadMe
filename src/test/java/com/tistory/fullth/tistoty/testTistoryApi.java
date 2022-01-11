package com.tistory.fullth.tistoty;

import com.tistory.fullth.post.service.FileService;
import com.tistory.fullth.post.service.PostService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@SpringBootTest
class testTistoryApi {

    @Autowired
    PostService postService;

    @Autowired
    FileService fileService;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private String client_id = "";
    private String client_secret = "";
    private String redirect_uri = "";

    /**
     * https://www.tistory.com/oauth/authorize?
     *   client_id={client-id}
     *   &redirect_uri={redirect-uri}
     *   &response_type=code
     *   &state={state-param}
     * */
    @Test
    @Ignore
    void getAuthorizeCode() {
        String apiURL = "https://www.tistory.com/oauth/authorize?"
                + "client_id=" + client_id + "&redirect_uri=" + redirect_uri + "&response_type=code";

        LOGGER.info("getAuthorizeCode() ::: " + apiURL);
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
    @Ignore
    void getAccessToken() {
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
    @Ignore
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

    @Test
    @Ignore
    public void testParseTistoryPostList() {
        List postList = postService.parseTistoryPostList(1);
        LOGGER.info(postList.toString());
    }

    @Test
    @Ignore
    public void testCreateNewTistoryReadMeFile() throws IOException {
        int beforeFileWriteTotalCount = 0;
        LOGGER.info("Before Total Page Count ::: " + String.valueOf(beforeFileWriteTotalCount));

        int afterFileWriteTotalCount = 57;
        LOGGER.info("After Total Page Count ::: " + String.valueOf(afterFileWriteTotalCount));

        int page = 0;

        if(afterFileWriteTotalCount > beforeFileWriteTotalCount) {
            page = (afterFileWriteTotalCount - beforeFileWriteTotalCount) / 10;
        } else {
            LOGGER.info("추가된 포스팅이 없습니다.");
        }

        int remain = (afterFileWriteTotalCount - beforeFileWriteTotalCount) % 10;

        for(int i = 1; i <= page; i++) {
            fileService.createNewTistoryReadMeFile(i);
        }
        if(remain != 0)
            fileService.createNewTistoryReadMeFile(page += 1);

        LOGGER.info("파일이 생성(수정)되었습니다.");
    }

    @Test
    @Ignore
    public void testGetTotalTistoryPostCount() {
        int totalCount = postService.getTotalPageCount();
        LOGGER.info("Total Page Count ::: " + String.valueOf(totalCount));
    }

    public void requestApi(String apiURL){
        try {
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
            LOGGER.info("API Response ::: " + response.toString());
        } catch (Exception e) {
            LOGGER.error("requestApi() error ::: "+ e);
        }
    }
}
