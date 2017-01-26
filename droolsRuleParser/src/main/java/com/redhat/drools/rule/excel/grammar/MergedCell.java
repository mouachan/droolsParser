package com.redhat.drools.rule.excel.grammar;

public class MergedCell {
	private Cell from;
	private Cell to;
	private String value;
	private Cell operator;
	/**
	 * @return the from
	 */
	public Cell getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(Cell from) {
		this.from = from;
	}
	/**
	 * @return the to
	 */
	public Cell getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(Cell to) {
		this.to = to;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the operator
	 */
	public Cell getOperator() {
		return operator;
	}
	/**
	 * @param operator the operator to set
	 */
	public void setOperator(Cell operator) {
		this.operator = operator;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MergedCell [from=");
		builder.append(from);
		builder.append(", to=");
		builder.append(to);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
	
}
