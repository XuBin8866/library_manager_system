package com.xxbb.actionform;
/**  
    * @Title: LibraryForm.java
    * @Package com.xxbb.actionform
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年1月28日
    * @version V1.0  
    */

public class LibraryForm {
	private Integer id;
	private String address;
    private String curator;
    private String email;
    private String introduce;
    private String libraryName;
    private String telephone;
    private String createDate;
    private String url;
	public LibraryForm() {
		
	}
	@Override
	public String toString() {
		return "LibraryForm [id=" + id + ", address=" + address + ", curator=" + curator + ", email=" + email
				+ ", introduce=" + introduce + ", libraryName=" + libraryName + ", telephone=" + telephone
				+ ", createDate=" + createDate + ", url=" + url + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCurator() {
		return curator;
	}
	public void setCurator(String curator) {
		this.curator = curator;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getLibraryName() {
		return libraryName;
	}
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
    
}
