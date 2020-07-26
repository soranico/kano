package com.kano.application.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * <pre>
 * @title com.kano.application.listener.ProfilesApplicationListener
 * @description
 *        <pre>
 *          根据pom中的<profile.active></profile.active>来选取配置文件
 *        </pre>
 *
 * @author soranico
 * @version 1.0
 * @date 2020/7/26
 *
 * </pre>
 */
@Slf4j
public class ProfilesApplicationListener implements ApplicationListener<ApplicationStartingEvent> {

    private static final String DEFAULT_PROFILE = "dev";
    private static final String PROPERTIES = "properties";
    private static final String PROFILE_ACTIVE = "profile.active";
    private static final int ADJUST_LENGTH = ("target" + File.separator + "classes").length() + 1;
    private static final String SLASH = "/";
    private static final String SUFFIX = "pom.xml";

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        String projectPath = this.getClass().getClass().getResource(SLASH).getPath();
        try {
            String profile = loadProfile(projectPath.substring(0, projectPath.length() - ADJUST_LENGTH).concat(SUFFIX));
            event.getSpringApplication().setAdditionalProfiles(profile);
        } catch (Exception e) {
            log.error("spring boot start load profile exception", e);
            System.exit(0);
        }

    }

    private static String loadProfile(String file) throws ParserConfigurationException, SAXException, IOException {
        String profile = DEFAULT_PROFILE;
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        NodeList properties = doc.getElementsByTagName(PROPERTIES);
        int parentLength = properties.getLength();
        String profileActive = PROFILE_ACTIVE;
        Node node;
        for (int i = 0; i < parentLength; i++) {
            NodeList childNodes = properties.item(i).getChildNodes();
            int childLength = childNodes.getLength();
            for (int j = 0; j < childLength; j++) {
                if (profileActive.equals((node = childNodes.item(j)).getNodeName())) {
                    profile = node.getTextContent();
                    return profile;
                }
            }
        }
        return profile;

    }
}
