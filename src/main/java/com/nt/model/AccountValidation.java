package com.nt.model;

public class AccountValidation {
		
	private String acNumber;
	private String acName;
	private String acType;
	

	public AccountValidation() {

	}


	public AccountValidation(String acNumber, String acName, String acType) {
		super();
		this.acNumber = acNumber;
		this.acName = acName;
		this.acType = acType;
	}


	public String getAcNumber() {
		return acNumber;
	}


	public void setAcNumber(String acNumber) {
		this.acNumber = acNumber;
	}


	public String getAcName() {
		return acName;
	}


	public void setAcName(String acName) {
		this.acName = acName;
	}


	public String getAcType() {
		return acType;
	}


	public void setAcType(String acType) {
		this.acType = acType;
	}


	@Override
	public String toString() {
		return "AccountValidation [acNumber=" + acNumber + ", acName=" + acName + ", acType=" + acType + "]";
	}

		
}