package com.tistory.fullth.post.controller;

import com.tistory.fullth.post.config.Properties;
import com.tistory.fullth.post.service.FileService;
import com.tistory.fullth.post.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/tistory/file")
public class TistoryFileController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final Properties properties;
    private final PostService postService;
    private final FileService fileService;

    TistoryFileController(Properties properties, PostService postService, FileService fileService) {
        this.properties = properties;
        this.postService = postService;
        this.fileService = fileService;
    }

    @PutMapping("/tistory-post-list")
    public String createMarkDownFile() throws IOException {

        int beforeFileWriteTotalCount = properties.getTotalPage();
        LOGGER.info("Before Total Page Count ::: " + String.valueOf(beforeFileWriteTotalCount));

        int afterFileWriteTotalCount = postService.getTotalPageCount();
        LOGGER.info("After Total Page Count ::: " + String.valueOf(afterFileWriteTotalCount));

        int page = 0;

        if(afterFileWriteTotalCount > beforeFileWriteTotalCount) {
            page = (afterFileWriteTotalCount - beforeFileWriteTotalCount) / 10;
        } else {
            return "추가된 포스팅이 없습니다.";
        }

        int remain = (afterFileWriteTotalCount - beforeFileWriteTotalCount) % 10;

        for(int i = 1; i <= page; i++) {
            fileService.createNewTistoryReadMeFile(i);
        }
        if(remain != 0)
            fileService.createNewTistoryReadMeFile(page += 1);

        return "파일이 생성(수정)되었습니다.";
    }
}
