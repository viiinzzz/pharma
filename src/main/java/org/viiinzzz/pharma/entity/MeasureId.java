package org.viiinzzz.pharma.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MeasureId implements Serializable {
    private String clientId;

    private LocalDateTime time;

    public MeasureId() {
        // Required by Hibernate
    }

    public MeasureId(String clientId, LocalDateTime time) {
        this.clientId = clientId;
        this.time = time;
    }

    // equals() and hashCode()
}
