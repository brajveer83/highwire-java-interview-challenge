package com.highwire.interview.controllers;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.highwire.interview.model.Fact;
import com.highwire.interview.model.FactCategory;
import com.highwire.interview.service.FactService;

/**
 * Controller for access to Facts.
 * Requests and responses are transmitted using JSON objects.
 */
@RestController
@RequestMapping("/api/facts")
public class FactApiController {

    @Autowired
    private FactService factService;

    /**
     * @return all of the facts in the system
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Fact> getFacts() {
        return this.factService.getAllFacts();
    }

    /**
     * Uncomment this code if you want to create some initial data on startup for testing endpoints.
     */
    @PostConstruct
    public void createInitialEntities() {
        this.factService.createFact(new Fact("The sky is blue", new FactCategory("science")));
        this.factService.createFact(new Fact("Grass is green", new FactCategory("science")));
        this.factService.createFact(new Fact("I think, therefore I am", new FactCategory("philosophy")));
        this.factService.createFact(new Fact("I think, but I am not sure", new FactCategory("computing")));
    }
    
    
}
