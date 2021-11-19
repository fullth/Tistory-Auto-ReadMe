package com.tistory.fullth.post.service;

import com.tistory.fullth.post.dto.PostRequestDTO;
import com.tistory.fullth.post.dto.PostResponseDTO;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;

import java.util.List;

@Service
public interface PostService {
    String getPostList();
    String getPostListURL();
    List getParseXML();
    String getTagValue(String tag, Element eElement);
    public List<PostResponseDTO> findAll();
    public long save(final PostRequestDTO params);
}
