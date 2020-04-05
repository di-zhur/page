package com.strix.page.controller.dto;

import java.util.Objects;

public class PageInfoInput {

    private String url;

    public PageInfoInput() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageInfoInput that = (PageInfoInput) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
        return "PageInfoInput{" +
                "url='" + url + '\'' +
                '}';
    }
}
