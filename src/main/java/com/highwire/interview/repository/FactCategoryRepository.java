package com.highwire.interview.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.highwire.interview.model.FactCategory;

/**
 * Spring data JPA repository for the retrieval of fact categories.
 */
@Repository
public interface FactCategoryRepository extends CrudRepository<FactCategory, Long> {
}
