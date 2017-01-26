package com.redhat.drools.rule.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
@XmlRootElement(name="ruleset")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
@JsonPropertyOrder({ "nameSpace", "imports", "declares","functions","rules"})
public class RuleSet {
	
	@XmlElement(name="nameSpace")
	private String nameSpace;
	@XmlElement(name="imports")
	private List<String> imports = new ArrayList<String>();
	@XmlElement(name="declares")
	private List<TypeDeclaration> declares =  new ArrayList<TypeDeclaration>();
	@XmlElement(name="functions")
	private List<Function>functions= new ArrayList<Function>();
	@XmlElement(name="rules")
	private List<Rule>rules = new ArrayList<Rule>();

	
	/**
	 * @return the pkg
	 */
	public String getNameSpace() {
		return nameSpace;
	}

	/**
	 * @param pkg the pkg to set
	 */
	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	/**
	 * @return the imports
	 */
	public List<String> getImports() {
		return imports;
	}

	/**
	 * @param imports the imports to set
	 */
	public void setImports(List<String> imports) {
		this.imports = imports;
	}
	public List<TypeDeclaration> getDeclares() {
		return declares;
	}

	public void setDeclares(List<TypeDeclaration> declares) {
		this.declares = declares;
	}

	/**
	 * @return the functions
	 */
	public List<Function> getFunctions() {
		return functions;
	}

	/**
	 * @param functions the functions to set
	 */
	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	/**
	 * @return the rules
	 */
	public List<Rule> getRules() {
		return rules;
	}

	/**
	 * @param rules the rules to set
	 */
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public void addImport(String imp){
		this.imports.add(imp);
	}
	
	public void addFunction(Function function){
		this.functions.add(function);
	}
	
	
	public void addRule(Rule rule){
		this.rules.add(rule);
	}
	
	public void addDeclare(TypeDeclaration td){
		this.declares.add(td);
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((declares == null) ? 0 : declares.hashCode());
		result = prime * result + ((functions == null) ? 0 : functions.hashCode());
		result = prime * result + ((imports == null) ? 0 : imports.hashCode());
		result = prime * result + ((nameSpace == null) ? 0 : nameSpace.hashCode());
		result = prime * result + ((rules == null) ? 0 : rules.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RuleSet other = (RuleSet) obj;
		if (declares == null) {
			if (other.declares != null)
				return false;
		} else if (!declares.equals(other.declares))
			return false;
		if (functions == null) {
			if (other.functions != null)
				return false;
		} else if (!functions.equals(other.functions))
			return false;
		if (imports == null) {
			if (other.imports != null)
				return false;
		} else if (!imports.equals(other.imports))
			return false;
		if (nameSpace == null) {
			if (other.nameSpace != null)
				return false;
		} else if (!nameSpace.equals(other.nameSpace))
			return false;
		if (rules == null) {
			if (other.rules != null)
				return false;
		} else if (!rules.equals(other.rules))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RuleSet [nameSpace=");
		builder.append(nameSpace);
		builder.append(", imports=");
		builder.append(imports);
		builder.append(", declares=");
		builder.append(declares);
		builder.append(", functions=");
		builder.append(functions);
		builder.append(", rules=");
		builder.append(rules);
		builder.append("]");
		return builder.toString();
	}


	
	

}
