package com.tistory.fullth.post.service;

import com.tistory.fullth.post.config.Properties;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService {
    private final Properties properties;

    private final AuthService authService;

    public PostService(Properties properties, AuthService authService) {
        this.properties = properties;
        this.authService = authService;
    }

    public String getPostList() {
        String access_token = properties.getToken();
        String output = "UTF-8";
        String blogName = properties.getBlogName();
        int page = 1;

        String apiURL = "https://www.tistory.com/apis/post/list?"
                + "access_token=" + access_token
                + "&output=" + output
                + "&blogName=" + blogName
                + "&page=" + page;

        return authService.requestApi(apiURL);
    }

    public String getPostListURL(int page) {
        String access_token = properties.getToken();
        String output = "UTF-8";
        String blogName = properties.getBlogName();

        String apiURL = "https://www.tistory.com/apis/post/list?"
                + "access_token=" + access_token
                + "&output=" + output
                + "&blogName=" + blogName
                + "&page=" + page;

        return apiURL;
    }

    public Element xmlParser(int page) {
        String url = getPostListURL(page);

        Document documentInfo = null;
        try {
            documentInfo = (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url);
        } catch (Exception e) {

        }
        documentInfo.getDocumentElement().normalize();
        //System.out.println("Root: " + documentInfo.getDocumentElement().getNodeName());

        Element root = documentInfo.getDocumentElement(); // tistory

        return root;
    }

    public int getTotalPageCount() {
        Element root = xmlParser(1);

        NodeList itemList = root.getElementsByTagName("item");

        Node itemNode = itemList.item(0);
        Element element = (Element) itemNode;

        int totalCount = Integer.parseInt(element.getElementsByTagName("totalCount").item(0).getTextContent());

        return totalCount;
    }

    public List<Map<String, String>> parseTistoryPostList(int page) {
        Element root = xmlParser(page); // tistory

        NodeList nodeList = root.getElementsByTagName("post");

        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Map<String, String> map = new HashMap<>();
            Node nNode = nodeList.item(i);
            Element eElement = (Element) nNode;

            map.put("title", getTagValue("title", eElement));
            map.put("postUrl", getTagValue("postUrl", eElement));
            map.put("date", getTagValue("date", eElement));

            list.add(map);
        }

        return list;
    }

    public String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }
}
