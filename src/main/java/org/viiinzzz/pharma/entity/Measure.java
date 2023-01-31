package org.viiinzzz.pharma.entity;

import org.viiinzzz.pharma.help.TimeHelper;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;


@Entity
@Document
@IdClass(MeasureId.class)
public class Measure {

    // @Id
    // @org.springframework.data.annotation.Id
    // @GeneratedValue(generator = "system-uuid")
    // @GenericGenerator(name = "system-uuid", strategy = "uuid")
    // private String id;

    @Id
    @Column(nullable = false)
    private String clientId;

    @Id
    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = true)
    private float value;

    @ManyToOne
    @JoinColumn(name = "uploadId")
    private Upload upload;

    @Override
    public String toString() {
        return "upload " + this.upload.getId() + ": "
                + this.value
                + " at " + TimeHelper.format(this.time);
    }

    public String getUploadId() { return upload.getId(); }

    public Measure() {
        // Required by Hibernate
    }

    public Measure(final Upload upload, final String time, final String value) {
        this.upload = upload;
        this.clientId = upload.getClientId();
        this.time = TimeHelper.parse(time);
        this.value = Float.parseFloat(value);
    }

    /*public String getId() {
        return id;
    }
    public void setId(final String id) {
        this.id = id;
    }*/

    public Upload getUpload() {
        return upload;
    }
    public void setUpload(final Upload upload) {
        this.upload = upload;
    }

    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(final LocalDateTime time) {
        this.time = time;
    }

    public float getValue() {
        return value;
    }
    public void setValue(final float value) {
        this.value = value;
    }
}
