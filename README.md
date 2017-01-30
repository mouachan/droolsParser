## droolsParser PoC


* droolsParser/droolsRuleParser:  
- load a DRL/JSON (from file or as a String), create a RuleSet meta-model and convert it to JSON/DRL.
- generate DRL from a rule template with CSV data or a tab of String 

### Usage
- load and convert DRL to JSON String 
```
RuleParser rp = new RuleParser();
rp.readDrlFromFile(path + "rules.drl");
RuleSet ruleset = rp.parseRuleSet();
PackageDescr pkg = rp.buildDescrFromRuleSet(ruleset);
String rstojson = rp.convertObjectToJson(ruleset);
```
- convert DRL String to JSON String                     
```
RuleParser rp = new RuleParser();
rp.readDrlFromString(drl);
RuleSet ruleset = rp.parseRuleSet();
PackageDescr pkg = rp.buildDescrFromRuleSet(ruleset);
String rstojson = rp.convertObjectToJson(ruleset);
```
- generate DRL from template with CSV values
```
String drl = converter.convertToDrlFromCSV("/data/data.csv", "/templates/template.drl", 10, 2);
```

- generate DRL from template with rows values
```
rows.add(new String[] { "1", "STANDARD", "FLAT", null, "SBLC", "ISS", "Commission", "Party 1", "USD", null,"750", "dummy" });
rows.add(new String[] { "15", "STANDARD", "FLAT", "Entity Branch 1", "SBLC", "ISS", "Commission", null, "YEN",null, "1600", "dummy" });
rows.add(new String[] { "12", "STANDARD", "FLAT", null, "SBLC", "ISS", "Postage", null, "YEN", null, "40","dummy" });
rows.add(new String[] { "62", "STANDARD", "FLAT", null, "SBLC", "ISS", "Telex", null, "YEN", "< 30000", "45","dummy" });
CustomDataProvider tdp = new CustomDataProvider(rows);
final DataProviderCompiler converter = new DataProviderCompiler();
final String drl = converter.compile(tdp, "/com/amadeus/templates/rule_template_1.drt");
```
#### RuleSet Model
![rulset_model](https://cloud.githubusercontent.com/assets/12033900/22420352/5d1b47e8-e6e2-11e6-848e-0b5b7d7bb616.png)


### Description
*PackageDescrResourceVisitor.java* : allow to visit a package descriptor, only ImportDescr, FunctionDescr, ANDDescr, OrDescr, ExprConstraintDescr, PatternDescr,AttributeDescr, TypeDeclarationDescr are implemented, a RuleSet Object is created from the visited descriptor.  

