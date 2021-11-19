package com.tistory.fullth.post.dto;

import com.tistory.fullth.post.entity.Post;

public class PostResponseDTO {

    private long id;
    private String title;
    private String uri;

    public PostResponseDTO(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.uri = entity.getUri();
    }
}
