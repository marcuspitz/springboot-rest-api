package com.example.restservice.infrastructure.repositories.shared;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaginationRepository<T> {

	Page<T> findAll(Pageable pageable);

}
