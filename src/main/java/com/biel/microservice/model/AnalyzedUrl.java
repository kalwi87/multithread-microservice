package com.biel.microservice.model;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(value = AuditingEntityListener.class)
@Table(name = "analyzedurl")
public class AnalyzedUrl extends BaseEntity {

    public AnalyzedUrl(){}

    private String userId;

    private String url;

    @ElementCollection
    private List<String> internalLinks;

    @CreatedDate
    @Type(type="java.sql.Timestamp")
    @Column(updatable = false)
    private Date createdDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<String> getInternalLinks() {
        return internalLinks;
    }

    public void setInternalLinks(List<String> internalLinks) {
        this.internalLinks = internalLinks;
    }

    @Override
    public String toString() {
        return "AnalyzedUrl{" +
                "userId='" + userId + '\'' +
                ", url='" + url + '\'' +
                ", internalLinks=" + internalLinks +
                ", createdDate=" + createdDate +
                '}';
    }
}
