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

    public void createNewTistoryReadMeFile(int page) throws IOException {
        String fileName = "ReadMe.md";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

        BufferedWriter bw = new BufferedWriter(
                new FileWriter("D:/Tistory/Tistory-Auto-ReadMe/" + fileName, true));

        PrintWriter pw = new PrintWriter(bw, true);

        List<Map<String, String>> postList = postService.parseTistoryPostList(page);

        for (Map post : postList) {
            pw.append("[");
            pw.append(post.get("title").toString());
            pw.append("]");
            pw.append("(");
            pw.append(post.get("postUrl").toString());
            pw.append(")");

            pw.append("\n\n");
        }

        pw.flush();
        pw.close();
    }
}
