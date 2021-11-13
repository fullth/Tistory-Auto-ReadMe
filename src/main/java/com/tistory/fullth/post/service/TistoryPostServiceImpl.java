package com.tistory.fullth.post.service;

import com.tistory.fullth.post.config.Properties;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("tistoryPostService")
public class TistoryPostServiceImpl implements TistoryPostService{

    private final Properties properties;

    private final TistoryAuthService tistoryAuthService;

    public TistoryPostServiceImpl(Properties properties,
                                  TistoryAuthService tistoryAuthService) {
        this.properties = properties;
        this.tistoryAuthService = tistoryAuthService;
    }

    @Override
    public String getPostList() {
        String access_token = properties.getToken();
        String output = "UTF-8";
        String blogName = properties.getBlogName();
        String page = "1";

        String apiURL = "https://www.tistory.com/apis/post/list?"
                + "access_token=" + access_token
                + "&output=" + output
                + "&blogName=" + blogName
                + "&page=" + page;

        return tistoryAuthService.requestApi(apiURL);
    }

    @Override
    public String getPostListURL() {
        String access_token = properties.getToken();
        String output = "UTF-8";
        String blogName = properties.getBlogName();
        String page = "1";

        String apiURL = "https://www.tistory.com/apis/post/list?"
                + "access_token=" + access_token
                + "&output=" + output
                + "&blogName=" + blogName
                + "&page=" + page;

        return apiURL;
    }

    @Override
    public List getParseXML() {
        String url = getPostListURL();
        Document documentInfo = null;
        try {
            documentInfo = (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        documentInfo.getDocumentElement().normalize();
        System.out.println("Root: " + documentInfo.getDocumentElement().getNodeName());

        Element root = documentInfo.getDocumentElement(); // tistory
        NodeList nodeList = root.getElementsByTagName("post");

        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Map<String, String> map = new HashMap<>();
            Node nNode = nodeList.item(i);
            Element eElement = (Element) nNode;

            map.put("title", getTagValue("title", eElement));

            list.add(map);
        }

        return list;
    }

    @Override
    public String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    @Override
    public void insertPostList() {

    }


}
