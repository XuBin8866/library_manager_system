package com.xxbb.actionform;
/**  
    * @Title: BookTypeForm.java
    * @Package com.xxbb.actionform
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年1月28日
    * @version V1.0  
    */

public class BookTypeForm {
	private Integer id;
	private String typeName;
	private int limitedTime;
	public BookTypeForm() {
		
	}
	
	@Override
	public String toString() {
		return "BookTypeForm [id=" + id + ", typeName=" + typeName + ", limitedTime=" + limitedTime + "]";
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getLimitedTime() {
		return limitedTime;
	}

	public void setLimitedTime(int limitedTime) {
		this.limitedTime = limitedTime;
	}
	
	
}
