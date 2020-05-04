package com.strix.page.core.html;

import com.strix.page.core.dto.Link;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class HtmlInformantTest {

    @Autowired
    private HtmlInformant htmlInformant;

    @Test
    public void testGetMainLinks() {
        List<Link> links = htmlInformant.receive("https://www.youtube.com/watch?v=q8KkjaW8PDc",
                document -> new HtmlLinksInformation(document).receive());
        assertNotNull(links);
    }

    @Test
    public void testGetTopics() {
        Map<String, List<String>> topics = htmlInformant.receive("https://yandex.ru/",
                document -> new HtmlTopicsInformation(document).receive());
        assertNotNull(topics);
    }

}
