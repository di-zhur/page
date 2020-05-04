package com.strix.page.core.html;

import org.jsoup.nodes.Document;

public abstract class HtmlInformation<T> {

    protected final Document document;

    public HtmlInformation(Document document) {
        this.document = document;
    }

    public abstract T receive();

}
