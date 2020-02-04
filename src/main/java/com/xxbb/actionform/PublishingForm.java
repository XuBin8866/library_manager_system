package com.xxbb.actionform;
/**  
    * @Title: PublishingForm.java
    * @Package com.xxbb.actionform
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年1月28日
    * @version V1.0  
    */

public class PublishingForm {
	private String isbn;
	private String publishName;
	public PublishingForm() {
		super();
	}
	@Override
	public String toString() {
		return "PublishingForm [isbn=" + isbn + ", publishName=" + publishName + "]";
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getPublishName() {
		return publishName;
	}
	public void setPublishName(String publishName) {
		this.publishName = publishName;
	}
	
}
