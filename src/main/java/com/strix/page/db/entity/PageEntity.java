package com.strix.page.db.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "page", schema = "public", catalog = "site")
public class PageEntity {
    private UUID id;
    private String url;
    private UUID userId;
    private String option;
    private Date dateCreated;
    private Collection<LinkEntity> linksById;
    private Collection<TopicEntity> topicsById;

    @Id
    @Column(name = "id", columnDefinition = "UUID")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "user_id")
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "option")
    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Basic
    @Column(name = "date_created", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageEntity that = (PageEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(url, that.url) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(option, that.option) &&
                Objects.equals(dateCreated, that.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, userId, option, dateCreated);
    }

    @OneToMany(mappedBy = "pageByPageId")
    public Collection<LinkEntity> getLinksById() {
        return linksById;
    }

    public void setLinksById(Collection<LinkEntity> linksById) {
        this.linksById = linksById;
    }

    @OneToMany(mappedBy = "pageByPageId")
    public Collection<TopicEntity> getTopicsById() {
        return topicsById;
    }

    public void setTopicsById(Collection<TopicEntity> topicsById) {
        this.topicsById = topicsById;
    }
}
