package com.strix.page.core.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public abstract class HtmlInformation<T> {

    protected final Document document;

    public HtmlInformation(Document document) {
        this.document = document;
    }

    public abstract T receive();

    protected Document parse(String html) {
        Assert.isTrue(!StringUtils.isEmpty(html), "html can't be empty");
        return Jsoup.parse(html);
    }

}
