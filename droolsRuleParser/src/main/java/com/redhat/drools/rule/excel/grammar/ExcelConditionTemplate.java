package com.redhat.drools.rule.excel.grammar;

public class ExcelConditionTemplate {
	private Cell left;
	private Cell op;
	private Cell right;
	/**
	 * @return the left
	 */
	public Cell getLeft() {
		return left;
	}
	/**
	 * @param left the left to set
	 */
	public void setLeft(Cell left) {
		this.left = left;
	}
	/**
	 * @return the op
	 */
	public Cell getOp() {
		return op;
	}
	/**
	 * @param op the op to set
	 */
	public void setOp(Cell op) {
		this.op = op;
	}
	/**
	 * @return the right
	 */
	public Cell getRight() {
		return right;
	}
	/**
	 * @param right the right to set
	 */
	public void setRight(Cell right) {
		this.right = right;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExcelConditionTemplate [left=");
		builder.append(left);
		builder.append(", op=");
		builder.append(op);
		builder.append(", right=");
		builder.append(right);
		builder.append("]");
		return builder.toString();
	}
}
