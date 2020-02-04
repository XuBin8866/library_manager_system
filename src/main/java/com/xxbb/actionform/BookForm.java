package com.xxbb.actionform;

/**
 * @Title: BookForm.java
 * @Package com.xxbb.actionform
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐斌
 * @date 2020年1月28日
 * @version V1.0
 */

public class BookForm {
	private Integer id;
	private Integer bookTypeId;
	private String bookBarcode;
	private String bookName;
	private Integer bookcaseId;
	private Double price;
	private String author;
	private String translator;
	private String isbn;
	private int page;
	private String intime;
	private int ifDelete;
	private String operator;
	private Integer degree;// 借阅次数

	private String bookTypeName;
	private int limitedTime;

	private String publishName;

	private String bookcaseName;

	public BookForm() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBookTypeId() {
		return bookTypeId;
	}

	public void setBookTypeId(Integer bookTypeId) {
		this.bookTypeId = bookTypeId;
	}

	public String getBookBarcode() {
		return bookBarcode;
	}

	public void setBookBarcode(String bookBarcode) {
		this.bookBarcode = bookBarcode;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Integer getBookcaseId() {
		return bookcaseId;
	}

	public void setBookcaseId(Integer bookcaseId) {
		this.bookcaseId = bookcaseId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTranslator() {
		return translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getIntime() {
		return intime;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	public int getIfDelete() {
		return ifDelete;
	}

	public void setIfDelete(int ifDelete) {
		this.ifDelete = ifDelete;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	public String getBookTypeName() {
		return bookTypeName;
	}

	public void setBookTypeName(String bookTypeName) {
		this.bookTypeName = bookTypeName;
	}

	public int getLimitedTime() {
		return limitedTime;
	}

	public void setLimitedTime(int limitedTime) {
		this.limitedTime = limitedTime;
	}

	public String getPublishName() {
		return publishName;
	}

	public void setPublishName(String publishName) {
		this.publishName = publishName;
	}

	public String getBookcaseName() {
		return bookcaseName;
	}

	public void setBookcaseName(String bookcaseName) {
		this.bookcaseName = bookcaseName;
	}

}
