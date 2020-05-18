package com.strix.page.core.html;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.swing.text.html.HTML;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HtmlTopicsInformation extends HtmlInformation<Map<String, List<String>>> {

    private static final List<String> HTML_TOPIC_TAGS = List.of(HTML.Tag.H1.toString(), HTML.Tag.H2.toString(),
            HTML.Tag.H3.toString(), HTML.Tag.P.toString());

    public HtmlTopicsInformation(Document document) {
        super(document);
    }

    @Override
    public Map<String, List<String>> receive() {
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
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
