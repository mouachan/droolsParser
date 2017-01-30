## droolsParser PoC


* droolsParser/droolsRuleParser:  
- load a DRL/JSON (from file or as a String), create a RuleSet meta-model and convert it to JSON/DRL.
- convert a rule template with CSV data or a tab of String to DRL rules 

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
#### RuleSet Model
![rulset_model](https://cloud.githubusercontent.com/assets/12033900/22420352/5d1b47e8-e6e2-11e6-848e-0b5b7d7bb616.png)


### Documentation

