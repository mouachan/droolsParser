package com.redhat.drools.rule.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;
@XmlRootElement(name="rhs")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
public class Rhs {
	@XmlElement(name="consequences")
	List<Consequence> consequences = new ArrayList<Consequence>();

	/**
	 * @return the consequences
	 */
	public List<Consequence> getConsequences() {
		return consequences;
	}

	/**
	 * @param consequences
	 *            the consequences to set
	 */
	public void setConsequences(List<Consequence> consequences) {
		this.consequences = consequences;
	}

	public void addConsequence(Consequence consequence) {
		this.consequences.add(consequence);
	}

	public String buildRhs() {
		StringBuilder builder = new StringBuilder();
		int index = 0;
		for (Consequence consequence : this.consequences) {
			builder.append("  "+consequence.getText() + ";");
			index++;
			if (index != consequences.size())
				builder.append("\n");
		}
		return builder.toString();
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consequences == null) ? 0 : consequences.hashCode());
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
		Rhs other = (Rhs) obj;
		if (consequences == null) {
			if (other.consequences != null)
				return false;
		} else if (!consequences.equals(other.consequences))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rhs [consequences=");
		builder.append(consequences);
		builder.append("]");
		return builder.toString();
	}

}
