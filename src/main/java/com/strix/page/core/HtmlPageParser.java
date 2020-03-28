package com.strix.page.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class HtmlPageParser {

    private HtmlPageParser() {
    }

    public static Document parse(String html) {
        Assert.isTrue(!StringUtils.isEmpty(html), "html can't be empty");
        return Jsoup.parse(html);
    }

}
