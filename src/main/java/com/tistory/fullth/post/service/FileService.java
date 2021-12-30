package com.tistory.fullth.post.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FileService {

    private final PostService postService;

    public FileService(PostService postService) {
        this.postService = postService;
    }

    public void createNewTistoryReadMeFile() throws IOException {
        String fileName = "";
        if(fileName == null || fileName == "") {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            Date nowdate = new Date();
            String dateString = formatter.format(nowdate);
            fileName = "tistory_post_list.md";
        }

        File file = new File("D:/TEST/" + fileName);

        Writer out = null;
        out = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file), "UTF-8"
                )
        );

        StringBuilder sb = new StringBuilder();
        sb.append("## _블로그 글 목록_ \n\n" );

        List<Map<String, String>> postList = postService.parseTistoryPostList();

        int i = 1;
        for (Map post : postList) {
            sb.append(i + ": [");
            sb.append(post.get("title"));
            sb.append("]");
            sb.append("(");
            sb.append(post.get("postUrl"));
            sb.append(")");

            sb.append("\n\n");
            i++;
        }

        out.write(sb.toString());
        out.flush();
        out.close();
    }
}
