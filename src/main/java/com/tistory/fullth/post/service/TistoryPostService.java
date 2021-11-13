package com.tistory.fullth.post.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Element;

import java.util.List;

@Service
public interface TistoryPostService {
    String getPostList();
    String getPostListURL();
    List getParseXML();
    String getTagValue(String tag, Element eElement);
    void insertPostList();
}
