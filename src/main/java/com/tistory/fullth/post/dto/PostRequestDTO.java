package com.tistory.fullth.post.dto;

import com.tistory.fullth.post.entity.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRequestDTO {

    private String title;
    private String uri;

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .uri(uri)
                .build();
    }
}
