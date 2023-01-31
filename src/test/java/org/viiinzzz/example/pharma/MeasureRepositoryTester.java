package org.viiinzzz.pharma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.viiinzzz.pharma.repository.MeasureRepository;

@Component
public class MeasureRepositoryTester {

    @Autowired
    private MeasureRepository measureRepository;

    public void save_and_find() {
        System.out.println(measureRepository.count());
        final Measure measure = measureRepository.save(new Measure("PROBETEST", "2022-02-01 08:00:00", "20.3"));
        final Measure found = measureRepository.findOne(measure.getId());
        assertThat(found.getValue()).isEqualTo(20.3);
    }

}
