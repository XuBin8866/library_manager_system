package com.xxbb.actionform;

/**
 * @Title: ParameterForm.java
 * @Package com.xxbb.actionform
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐斌
 * @date 2020年1月28日
 * @version V1.0
 */

public class ParameterForm {
	private int cost;
	private Integer id;
	private int validity;

	public ParameterForm() {
	}
	
	@Override
	public String toString() {
		return "ParameterForm [cost=" + cost + ", id=" + id + ", validity=" + validity + "]";
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setValidity(int validity) {
		this.validity = validity;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public int getValidity() {
		return validity;
	}
}
