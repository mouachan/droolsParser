package com.redhat.drools.rule.template;

import java.util.Iterator;
import java.util.List;

import org.drools.template.DataProvider;

public class CustomDataProvider implements DataProvider {
	private Iterator<String[]> iterator;

	public CustomDataProvider(List<String[]> rows) {
		this.iterator = rows.iterator();
	}

	public boolean hasNext() {
		return iterator.hasNext();
	}

	public String[] next() {
		return iterator.next();
	}
}
