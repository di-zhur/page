package com.strix.page.core.html;

import com.strix.page.core.dto.Link;
import org.jsoup.nodes.Document;

import javax.swing.text.html.HTML;
import java.util.List;
import java.util.stream.Collectors;

public class HtmlLinksInformation extends HtmlInformation<List<Link>> {

    public HtmlLinksInformation(Document document) {
        super(document);
    }

    @Override
    public List<Link> receive() {
        return document.select(HTML.Tag.A.toString() + "[" + HTML.Attribute.HREF.toString() + "]")
                .stream()
                .map(link -> new Link(link.attr(HTML.Attribute.HREF.toString()), link.text()))
                .collect(Collectors.toList());
    }

}
