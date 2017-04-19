package com.highwire.interview.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.highwire.interview.model.Fact;
import com.highwire.interview.model.Report;
import com.highwire.interview.service.FactReportService;
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
    
    @Autowired
    private FactReportService factReportService;

    /**
     * @return all of the facts in the system
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Fact> getFacts() {
        return this.factService.getAllFacts();
    }

    @RequestMapping(value = "/report",method = RequestMethod.GET)
    public List<Report> getFactsReport() {
    	List<Fact> facts = this.factService.getAllFacts();
    	List<Report> reports = factReportService.generateReport(facts);
		return reports;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Fact> createFact( @RequestBody Fact fact) {
    	Fact newFact = this.factService.createFact(fact);
		return new ResponseEntity<Fact>(newFact, HttpStatus.CREATED);
    }
    
}
