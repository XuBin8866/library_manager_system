package com.xxbb.actionform;
/**  
    * @Title: ManagerForm.java
    * @Package com.xxbb.actionform
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年1月28日
    * @version V1.0  
    */

public class ManagerForm {
	private Integer id;  //管理员ID号
    private String name;   //管理员名称
    private String pwd;  //管理员密码
    private int systemSet;  //系统设置权限
    private int readerSet;   //读者管理权限
    private int bookSet;   //图书管理权限
    private int borrowSet;   //图书借还权限
    private int systemQuery;    //系统查询权限
	public ManagerForm() {
		
	}
	@Override
	public String toString() {
		return "ManagerForm [id=" + id + ", name=" + name + ", pwd=" + pwd + ", systemSet=" + systemSet + ", readerSet="
				+ readerSet + ", bookSet=" + bookSet + ", borrowSet=" + borrowSet + ", systemQuery=" + systemQuery
				+ "]";
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getSystemSet() {
		return systemSet;
	}
	public void setSystemSet(int systemSet) {
		this.systemSet = systemSet;
	}
	public int getReaderSet() {
		return readerSet;
	}
	public void setReaderSet(int readerSet) {
		this.readerSet = readerSet;
	}
	public int getBookSet() {
		return bookSet;
	}
	public void setBookSet(int bookSet) {
		this.bookSet = bookSet;
	}
	public int getBorrowSet() {
		return borrowSet;
	}
	public void setBorrowSet(int borrowSet) {
		this.borrowSet = borrowSet;
	}
	public int getSystemQuery() {
		return systemQuery;
	}
	public void setSystemQuery(int systemQuery) {
		this.systemQuery = systemQuery;
	}
}