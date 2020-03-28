package com.strix.page.core;

import com.strix.page.core.dto.PageLink;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class HtmlPageInfoFactoryTest {

    @Autowired
    private HtmlPageInfoFactory htmlPageInfoFactory;

    @Test
    public void testGetMainLinks() {
        List<PageLink> pageLinks = htmlPageInfoFactory.getMainLinks("https://www.youtube.com/watch?v=q8KkjaW8PDc");
        assertNotNull(pageLinks);
    }

    @Test
    public void testGetTopics() {
        Map<String, List<String>> topics = htmlPageInfoFactory.getTopics("https://yandex.ru/");
        assertNotNull( topics);
    }

}
