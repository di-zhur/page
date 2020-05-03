package com.strix.page.core;

import com.strix.page.core.html.HtmlLinksInformation;
import com.strix.page.core.html.HtmlTopicsInformation;
import com.strix.page.core.html.HtmlInformant;
import com.strix.page.core.dto.PageLink;
import com.strix.page.core.dto.Topics;
import com.strix.page.db.HtmlPageStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HtmlInfoServiceImpl implements HtmlInfoService {

    private final HtmlInformant htmlInformant;
    private final HtmlPageStorage htmlPageStorage;

    @Autowired
    public HtmlInfoServiceImpl(HtmlInformant htmlInformant, HtmlPageStorage htmlPageStorage) {
        this.htmlInformant = htmlInformant;
        this.htmlPageStorage = htmlPageStorage;
    }

    @Override
    public List<PageLink> getMainLinks(String url) {
        List<PageLink> pageLinks = htmlInformant.receive(url,
                document -> new HtmlLinksInformation(document).receive());
        // TODO: 03.05.2020 saving in other service
        htmlPageStorage.saveLinks(url, pageLinks);
        return pageLinks;
    }

    @Override
    public List<Topics> getTopics(String url) {
        Map<String, List<String>> topics = htmlInformant.receive(url,
                document -> new HtmlTopicsInformation(document).receive());
        // TODO: 03.05.2020 saving in other service
        htmlPageStorage.saveTopics(url, topics);
        return topics.entrySet().stream()
                .map(it -> new Topics(it.getKey(), it.getValue()))
                .collect(Collectors.toList());
    }

}
