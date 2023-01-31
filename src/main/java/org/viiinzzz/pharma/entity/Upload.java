package org.viiinzzz.pharma.entity;

import org.viiinzzz.pharma.help.TimeHelper;
import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Document
public class Upload {

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private String clientId;

    @Column(nullable = false)
    private LocalDateTime uploadTime;

    @Column(nullable = true)
    private String text;

    @Column(nullable = false)
    private Boolean valid;


    @Override
    public String toString() {
        return (text == null ? "" : (this.valid ? "valid" : "invalid")) + " upload " + this.id
                + " from " + this.clientId
                + " at " + TimeHelper.format(this.uploadTime)
                + (text == null ? "" : "\n" + this.text);
    }

    public Upload() {
        // Required by Hibernate
    }

    public Upload(final String clientId, final LocalDateTime uploadTime) {
        this.clientId = clientId;
        this.uploadTime = uploadTime;
        this.valid = true;
        this.text = null;
    }

    public String getId() {
        return id;
    }
    public void setId(final String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }
    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }
    public void setUploadTime(final LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Boolean getValid() {
        return valid;
    }
    public void setValid(final Boolean valid) {
        this.valid = valid;

        if (valid) {
            this.text = null;
        }
    }

    public String getText() {
        return text;
    }
    public void setText(final String tet) {
        this.text = text;
    }
}
