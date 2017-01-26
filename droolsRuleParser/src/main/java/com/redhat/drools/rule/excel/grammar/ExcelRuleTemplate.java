package com.redhat.drools.rule.excel.grammar;

import java.util.ArrayList;
import java.util.List;

public class ExcelRuleTemplate {
	
	private Cell rulename;
	private List<MergedCell>facts;
	private List<ExcelConditionTemplate>conditions;
	private List<Cell>actions;
	
	public ExcelRuleTemplate(){
		facts = new ArrayList<MergedCell>();
		conditions = new ArrayList<ExcelConditionTemplate>();
		actions = new ArrayList<Cell>();
	}
	/**
	 * @return the rulesname
	 */
	public Cell getRuleName() {
		return rulename;
	}
	/**
	 * @param rulesname the rulesname to set
	 */
	public void setRuleName(Cell rulename) {
		this.rulename = rulename;
	}
	/**
	 * @return the facts
	 */
	public List<MergedCell> getFacts() {
		return facts;
	}
	/**
	 * @param facts the facts to set
	 */
	public void setFacts(List<MergedCell> facts) {
		this.facts = facts;
	}
	/**
	 * @return the conditions
	 */
	public List<ExcelConditionTemplate> getConditions() {
		return conditions;
	}
	/**
	 * @param conditions the conditions to set
	 */
	public void setConditions(List<ExcelConditionTemplate> conditions) {
		this.conditions = conditions;
	}
	/**
	 * @return the actions
	 */
	public List<Cell> getActions() {
		return actions;
	}
	/**
	 * @param actions the actions to set
	 */
	public void setActions(List<Cell> actions) {
		this.actions = actions;
	}
	
	public void addFact(MergedCell mergedfact){
		this.facts.add(mergedfact);
	}
	
	public void addCondition(ExcelConditionTemplate ectemplate){
		this.conditions.add(ectemplate);
	}
	
	public void addAction(Cell action){
		this.actions.add(action);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExcelRuleTemplate [rulename=");
		builder.append(rulename);
		builder.append(", facts=");
		builder.append(facts);
		builder.append(", conditions=");
		builder.append(conditions);
		builder.append(", actions=");
		builder.append(actions);
		builder.append("]");
		return builder.toString();
	}
}
