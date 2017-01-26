package com.redhat.drools.rule.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlRootElement(name = "node")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
@JsonPropertyOrder({ "gate", "expression", "children"})
public class Node {

	@XmlEnum
	public enum Gate {
		OR, AND
	};
	@XmlElement(name = "gate")
	private Gate gate;
	
	@JsonInclude(Include.NON_NULL)
	@XmlElement(name = "children")
	private List<Node> children = new ArrayList<Node>();
	@JsonInclude(Include.NON_NULL)
	@XmlElement(name = "expression")
	private Expression expression;

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	public Gate getGate() {
		return gate;
	}

	public void setGate(Gate gate) {
		this.gate = gate;
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public void addChildren(Node children) {
		this.children.add(children);
	}

	@JsonIgnore
	public boolean expNotNull() {
		return (expression != null);
	}

	public int numExpression() {
		int i = 0;
		for (Node child : children) {
			if (child.expNotNull())
				i++;
		}
		return i;

	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((expression == null) ? 0 : expression.hashCode());
		result = prime * result + ((gate == null) ? 0 : gate.hashCode());
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
		Node other = (Node) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (expression == null) {
			if (other.expression != null)
				return false;
		} else if (!expression.equals(other.expression))
			return false;
		if (gate != other.gate)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Node [gate=");
		builder.append(gate);
		builder.append(", children=");
		builder.append(children);
		builder.append(", expression=");
		builder.append(expression);
		builder.append("]");
		return builder.toString();
	}



}
