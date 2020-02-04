package com.xxbb.actionform;
/**  
    * @Title: ReaderTypeForm.java
    * @Package com.xxbb.actionform
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年1月28日
    * @version V1.0  
    */

public class ReaderTypeForm {
	private Integer id;
    private String name;
    private int allowBorrowAmount;
	public ReaderTypeForm() {
		super();
	}
	@Override
	public String toString() {
		return "ReaderTypeForm [id=" + id + ", name=" + name + ", allowBorrowAmount=" + allowBorrowAmount + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAllowBorrowAmount() {
		return allowBorrowAmount;
	}
	public void setAllowBorrowAmount(int allowBorrowAmount) {
		this.allowBorrowAmount = allowBorrowAmount;
	}

}
