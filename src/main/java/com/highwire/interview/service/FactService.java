package com.highwire.interview.service;

import com.google.common.collect.Lists;
import com.highwire.interview.repository.FactRepository;
import com.highwire.interview.model.Fact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Application service for interfacing the presentation layer with the data layer.
 * This class is the transactional boundary.
 */
@Service
@Transactional
public class FactService {

    @Autowired
    private FactRepository factRepository;

    /**
     * Create a new Fact, and return it with an id.
     * @param fact the fact to create. Cannot have an id when passed in.
     * @return the persisted fact with an id.
     */
    public Fact createFact(Fact fact) {
        Assert.isNull(fact.getId(), "The new fact cannot have an ID before creation");
        return this.factRepository.save(fact);
    }
    
    /**
     * Retrieve every fact in the database.
     * @return all of the facts in a list.
     */
    public List<Fact> getAllFacts() {
        return Lists.newArrayList(this.factRepository.findAll());
    }
}
