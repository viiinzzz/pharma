package org.viiinzzz.pharma.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.viiinzzz.pharma.entity.Upload;

@Repository
public interface UploadRepository extends PagingAndSortingRepository<Upload, String> {
}
