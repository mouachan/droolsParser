package com.redhat.drools.rule.template.converter;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.junit.Before;
import org.junit.Test;

import com.redhat.drools.rule.parser.RuleTemplateConverter;
import com.redhat.drools.rule.template.CustomDataProvider;

public class ConverterTest {
	private static Logger logger = Logger.getLogger(ConverterTest.class);
	private RuleTemplateConverter converter = null;
	private ArrayList<String[]> rows = new ArrayList<String[]>();


	@Before
	public void setUp() {
		converter = new RuleTemplateConverter();
	}

	@Test
	public void testCSVConverter() {
		String drl = converter.convertToDrlFromCSV("/data/data.csv", "/templates/template.drl", 10, 2);
		logger.info(drl);
		assertTrue(drl.indexOf("myObject.setIsValid(1, 2)") > 0);
		assertTrue(drl.indexOf("myObject.size () > 2") > 0);
		assertTrue(drl.indexOf("Foo(myObject.getColour().equals(red),\n myObject.size () > 1") > 0);




		drl = converter.convertToDrlFromCSV("/data/data.csv", "/templates/template.drl", 20, 2);

		logger.info(drl);
		assertNotNull(drl);


	}

	@Test
	public void testGenerateDRLFromTemplateWithRows() throws Exception {
		rows.add(new String[] { "1", "STANDARD", "FLAT", null, "SBLC", "ISS", "Commission", "Party 1", "USD", null,
				"750", "dummy" });
		rows.add(new String[] { "15", "STANDARD", "FLAT", "Entity Branch 1", "SBLC", "ISS", "Commission", null, "YEN",
				null, "1600", "dummy" });
		rows.add(new String[] { "12", "STANDARD", "FLAT", null, "SBLC", "ISS", "Postage", null, "YEN", null, "40",
				"dummy" });
		rows.add(new String[] { "62", "STANDARD", "FLAT", null, "SBLC", "ISS", "Telex", null, "YEN", "< 30000", "45",
				"dummy" });
		CustomDataProvider tdp = new CustomDataProvider(rows);
		final DataProviderCompiler converter = new DataProviderCompiler();
		final String drl = converter.compile(tdp, "/com/amadeus/templates/rule_template_1.drt");
		logger.info(drl);
	}
	
	@Test
	public void testGenerateDRLFromMultipleTemplateWithRows() throws Exception {
		rows.add(new String[] { "1", "STANDARD", "FLAT", null, "SBLC", "ISS", "Commission", "Party 1", "USD", null,
				"750", "dummy" });
		rows.add(new String[] { "15", "STANDARD", "FLAT", "Entity Branch 1", "SBLC", "ISS", "Commission", null, "YEN",
				null, "1600", "dummy" });
		rows.add(new String[] { "12", "STANDARD", "FLAT", null, "SBLC", "ISS", "Postage", null, "YEN", null, "40",
				"dummy" });
		rows.add(new String[] { "62", "STANDARD", "FLAT", null, "SBLC", "ISS", "Telex", null, "YEN", "< 30000", "45",
				"dummy" });
		CustomDataProvider tdp = new CustomDataProvider(rows);
		final DataProviderCompiler converter = new DataProviderCompiler();
		final String drl = converter.compile(tdp, "/com/amadeus/templates/rule_template_2.drt");
		logger.info(drl);
	}
}
