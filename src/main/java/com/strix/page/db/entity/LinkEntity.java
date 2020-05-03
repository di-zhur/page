package com.strix.page.db.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "link", schema = "public", catalog = "site")
public class LinkEntity {
    private UUID id;
    private UUID pageId;
    private String value;
    private PageEntity pageByPageId;

    @Id
    @Column(name = "id", columnDefinition = "UUID")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "page_id")
    public UUID getPageId() {
        return pageId;
    }

    public void setPageId(UUID pageId) {
        this.pageId = pageId;
    }

    @Basic
    @Column(name = "value", columnDefinition = "TEXT")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkEntity that = (LinkEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(pageId, that.pageId) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pageId, value);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id", referencedColumnName = "id", insertable = false, updatable = false)
    public PageEntity getPageByPageId() {
        return pageByPageId;
    }

    public void setPageByPageId(PageEntity pageByPageId) {
        this.pageByPageId = pageByPageId;
    }
}
