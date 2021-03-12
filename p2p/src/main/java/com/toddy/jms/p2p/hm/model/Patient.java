package com.toddy.jms.p2p.hm.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Patient implements Serializable {

	private Integer id;
	private String name;
	private String insuranceProvider;
	private Double copay;
	private Double amountToBePayed;

	public Patient(String name, String insuranceProvider, Double copay, Double amountToBePayed) {
		this.name = name;
		this.insuranceProvider = insuranceProvider;
		this.copay = copay;
		this.amountToBePayed = amountToBePayed;
	}

	public Patient() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInsuranceProvider() {
		return this.insuranceProvider;
	}

	public void setInsuranceProvider(String insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
	}

	public Double getCopay() {
		return this.copay;
	}

	public void setCopay(Double copay) {
		this.copay = copay;
	}

	public Double getAmountToBePayed() {
		return this.amountToBePayed;
	}

	public void setAmountToBePayed(Double amountToBePayed) {
		this.amountToBePayed = amountToBePayed;
	}

	@Override
	public String toString() {
		return "Patient [getId()=" + getId() + ", getName()=" + getName() + ", getInsuranceProvider()="
				+ getInsuranceProvider() + ", getCopay()=" + getCopay() + ", getAmountToBePayed()="
				+ getAmountToBePayed() + "]";
	}

}
