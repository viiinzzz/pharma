package org.viiinzzz.pharma.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.viiinzzz.pharma.entity.Measure;

import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DTOMeasure {

    private String clientId;
    private LocalDateTime time;
    private float value;

    public DTOMeasure(Measure measure) {
        this.clientId = measure.getUpload().getClientId();
        this.time = measure.getTime();
        this.value = measure.getValue();
    }
}
