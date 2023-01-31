package org.viiinzzz.pharma.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import  org.viiinzzz.pharma.entity.Measure;

@Repository
public interface MeasureRepository extends PagingAndSortingRepository<Measure, String> {
}
