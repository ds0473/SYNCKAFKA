package com.nt.model;

public class Result {

	private String name;
	private String percentage;
	private String result;

	public Result() {
	}

	public Result(String name, String percentage, String result) {
		this.name = name;
		this.percentage = percentage;
		this.result = result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Result [name=" + name + ", percentage=" + percentage + ", result=" + result + "]";
	}

}