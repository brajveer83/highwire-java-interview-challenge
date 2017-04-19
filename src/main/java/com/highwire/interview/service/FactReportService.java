package com.highwire.interview.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.highwire.interview.model.Fact;

@Service
public class FactReportService {

	public void generateReport(List<Fact> facts) {
		Collections.sort(facts, new FactCategoryComparator());
	}
	
	class FactCategoryComparator implements Comparator<Fact> {
		@Override
		public int compare(Fact factObj1, Fact factObj2) {
			return factObj1.getFactCategory().getCategory().compareTo(factObj2.getFactCategory().getCategory());
		}
	}

}
