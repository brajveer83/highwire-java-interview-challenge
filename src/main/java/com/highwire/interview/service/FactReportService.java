package com.highwire.interview.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.highwire.interview.model.Fact;
import com.highwire.interview.model.FactCategory;
import com.highwire.interview.model.Report;

@Service
public class FactReportService {

	public List<Report> generateReport(List<Fact> facts) {
		Map<String, Report> reportsByCategory = new HashMap<>();
		for (Fact fact : facts) {
			FactCategory category = fact.getFactCategory();
			Report report = reportsByCategory.get(category.getCategory());
			if (report == null) {
				report = new Report();
				report.setCategory(category.getCategory());
				report.setCount(1);
				reportsByCategory.put(category.getCategory(), report);
			} else {
				long count = report.getCount();
				report.setCount(count + 1);
			}
		}
		List<Report> reports = new ArrayList<Report>(reportsByCategory.values());
		Collections.sort(reports, new FactCategoryComparator());
		return reports;
	}
	
	class FactCategoryComparator implements Comparator<Report> {
		@Override
		public int compare(Report factObj1, Report factObj2) {
			return factObj1.getCategory().compareTo(factObj2.getCategory());
		}
	}

}
