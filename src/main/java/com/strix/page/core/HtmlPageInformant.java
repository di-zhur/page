package com.strix.page.core;

import com.strix.page.core.dto.PageLink;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HtmlPageInformant {

    private static final List<String> HTML_TOPIC_TAGS = List.of(TAG.H1, TAG.H2, TAG.H3, TAG.P);

    private HtmlPageInformant() {
    }

    public static List<PageLink> getLinks(Document document) {
       return document.select(TAG.A + "[" + ATTR.HREF + "]")
               .stream()
               .map(link -> new PageLink(link.attr(ATTR.HREF), link.text()))
               .collect(Collectors.toList());
    }

    public static Map<String, List<String>> getTopics(Document document) {
        return HTML_TOPIC_TAGS.parallelStream()
                .map(tag -> {
                    List<String> texts = document.getElementsByTag(tag)
                            .stream()
                            .map(Element::text)
                            .filter(text -> !StringUtils.isEmpty(text))
                            .collect(Collectors.toList());
                    return Map.entry(tag, texts);
                })
                .filter(it -> !CollectionUtils.isEmpty(it.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry:: getValue));
    }


    private static class ATTR {

        private static final String HREF = "href";

    }

    private static class TAG {

        private static final String A = "a";

        private static final String H1 = "h1";

        private static final String H2 = "h2";

        private static final String H3 = "h3";

        private static final String P = "p";

    }

}
