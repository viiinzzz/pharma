package org.viiinzzz.pharma.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.data.domain.Sort;
import org.viiinzzz.pharma.entity.Measure;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DTOUploadStats {

    private String clientId;
    private LocalDateTime measuresBeginTime;
    private LocalDateTime measuresEndTime;
    private float measuresMinimumValue;
    private float measuresMaximumValue;
    private float measuresAverageValue;

    public DTOUploadStats(List<Measure> measures) {
        if (measures.isEmpty()) {
            throw new IllegalArgumentException("must have at least one measure");
        }

        Measure firstMeasure = measures.stream().findFirst().get();
        clientId = firstMeasure.getUpload().getClientId();
        measuresBeginTime = firstMeasure.getTime();
        measuresEndTime = measuresBeginTime;
        measuresMinimumValue = firstMeasure.getValue();
        measuresMaximumValue = measuresMinimumValue;
        measuresAverageValue = measuresMinimumValue;

        double sum = 0;
        int count = 0;
        for(Measure measure : measures) {
            if (measure.getUpload().getClientId() != clientId) {
                throw new IllegalArgumentException("clientId mismatch");
            }

            float value = measure.getValue();
            if (value < measuresMinimumValue) {
                measuresMinimumValue = value;
            } else if (value > measuresMaximumValue) {
                measuresMaximumValue = value;
            }
            sum += value;
            count++;

            LocalDateTime time = measure.getTime();
            if (time.compareTo(measuresBeginTime) < 0) {
                measuresBeginTime = time;
            } else if (time.compareTo(measuresEndTime) > 0) {
                measuresEndTime = time;
            }
        }
        measuresAverageValue = (float)(sum / count);
    }
}
