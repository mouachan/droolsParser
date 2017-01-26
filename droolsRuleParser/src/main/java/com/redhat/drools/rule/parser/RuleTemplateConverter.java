package com.redhat.drools.rule.parser;

import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.drools.decisiontable.InputType;

public class RuleTemplateConverter {
	final ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();

	public String convertToDrlFromCSV(String pathCSV, String pathTemplate, int startRow, int startCol) {
		return converter.compile(pathCSV, pathTemplate, InputType.CSV, startRow, startCol);
	}

}
