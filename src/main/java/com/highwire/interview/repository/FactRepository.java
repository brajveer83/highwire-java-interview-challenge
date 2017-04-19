package com.highwire.interview.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.highwire.interview.model.Fact;

/**
 * Spring data JPA repository for the retrieval of facts
 */
@Repository
public interface FactRepository extends CrudRepository<Fact, Long> {
}
