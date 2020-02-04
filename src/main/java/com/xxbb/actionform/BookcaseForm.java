package com.xxbb.actionform;
/**  
    * @Title: BookCaseForm.java
    * @Package com.xxbb.actionform
    * @Description: TODO 书架
    * @author 徐斌
    * @date 2020年1月27日
    * @version V1.0  
    */

public class BookcaseForm {
	private Integer id;
    private String name;
    
	public BookcaseForm() {

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
	@Override
	public String toString() {
		return "BookCaseForm [id=" + id + ", name=" + name + "]";
	}
	
    
}
