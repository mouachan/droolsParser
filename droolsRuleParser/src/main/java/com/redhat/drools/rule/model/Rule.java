package com.redhat.drools.rule.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;




@XmlRootElement(name="rule")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
@JsonPropertyOrder({ "name", "attributes", "lhs","rhs"})
public class Rule {
	
	@XmlElement(name="name")
	private String name;
	@XmlElement(name="attributes")
	private List<RuleAttribute> attributes = new ArrayList<RuleAttribute>();
	@XmlElement(name="lhs")
	private Node lhs;
	@XmlElement(name="rhs")
	private Rhs rhs;

	public Rule() {
		rhs = new Rhs();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the attributes
	 */
	public List<RuleAttribute> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes
	 *            the attributes to set
	 */
	public void setAttributes(List<RuleAttribute> attributes) {
		this.attributes = attributes;
	}

	/**
	 * @return the lhs
	 */
	public Node getLhs() {
		return lhs;
	}

	/**
	 * @param lhs
	 *            the lhs to set
	 */
	public void setLhs(Node lhs) {
		this.lhs = lhs;
	}

	/**
	 * @return the rhs
	 */
	public Rhs getRhs() {
		return rhs;
	}

	/**
	 * @param rhs
	 *            the rhs to set
	 */
	public void setRhs(Rhs rhs) {
		this.rhs = rhs;
	}

	public void addAttribute(RuleAttribute attr) {
		if (attr != null)
			this.attributes.add(attr);
	}

	public String buildRule() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		builder.append("rule \"");
		builder.append(this.name);
		builder.append("\"");
		builder.append("\n");
		for (RuleAttribute attribute : attributes) {
			builder.append("  " + attribute.getName() + " " + attribute.getValue());
			builder.append("\n");
		}
		builder.append(" when");
		builder.append("\n");
		//TODO generate DRL of lhs from tree of conditions
		//builder.append(this.lhs.buildLhs());
		builder.append("then");
		builder.append("\n");
		builder.append(this.rhs.buildRhs());
		builder.append("\n");
		builder.append(" end");
		return builder.toString();
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result + ((lhs == null) ? 0 : lhs.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rhs == null) ? 0 : rhs.hashCode());
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
		Rule other = (Rule) obj;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		if (lhs == null) {
			if (other.lhs != null)
				return false;
		} else if (!lhs.equals(other.lhs))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rhs == null) {
			if (other.rhs != null)
				return false;
		} else if (!rhs.equals(other.rhs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rule [name=");
		builder.append(name);
		builder.append(", attributes=");
		builder.append(attributes);
		builder.append(", lhs=");
		builder.append(lhs);
		builder.append(", rhs=");
		builder.append(rhs);
		builder.append("]");
		return builder.toString();
	}
	
	
}
