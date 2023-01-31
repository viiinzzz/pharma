package org.viiinzzz.pharma.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import org.viiinzzz.pharma.entity.Measure;
import org.viiinzzz.pharma.repository.MeasureRepository;
import org.viiinzzz.pharma.repository.UploadRepository;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;


@RestController
public class MeasureController {

    private static final Logger logger = LoggerFactory.getLogger(MeasureController.class);
    
    @Autowired
    private MeasureRepository measureRepository;

    @Autowired
    private UploadRepository uploadRepository;

    @GetMapping("/measure/all")
    List<DTOMeasure> all() {
        return StreamSupport.stream(
                measureRepository.findAll(Sort.by(Sort.Direction.DESC, "time"))
                        .spliterator(), false)
                .map(measure -> new DTOMeasure(measure))
                .collect(Collectors.toList());
    }

    @GetMapping("/measure/stats")
    List<DTOUploadStats> allStats() {
        return new ArrayList<>(StreamSupport.stream(
                measureRepository.findAll()
                        .spliterator(), false)
                .collect(groupingBy(Measure::getUploadId))
                .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> new DTOUploadStats(entry.getValue()))).values());
    }
}
