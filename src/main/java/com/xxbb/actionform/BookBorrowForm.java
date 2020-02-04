package com.xxbb.actionform;

/**
 * @Title: BookBorrowForm.java
 * @Package com.xxbb.actionform
 * @Description: TODO 借阅信息
 * @author 徐斌
 * @date 2020年1月27日
 * @version V1.0
 */

public class BookBorrowForm {
	// tb_bookborrow
	private Integer id;
	private Integer readerId;
	private Integer bookId;
	private String borrowTime;
	private String returnTime;
	private String operator;
	private Integer ifBack;

	// tb_book
	private String bookType;
	private String bookBarcode;
	private String bookName;
	private String bookcaseName;
	private Double price;
	private String author;
	// tb_reader
	private String readerName;
	private String sex;
	private String readerBarcode;
	private String birthday;
	private String paperType;
	private String paperNo;
	private String telephone;
	private String readerType;
	private Integer degree;

	// tb_publishing
	private String publishName;

	public BookBorrowForm() {
		super();
	}

	@Override
	public String toString() {
		return "BookBorrowForm [id=" + id + ", readerId=" + readerId + ", bookId=" + bookId + ", borrowTime="
				+ borrowTime + ", returnTime=" + returnTime + ", operator=" + operator + ", ifBack=" + ifBack
				+ ", bookType=" + bookType + ", bookBarcode=" + bookBarcode + ", bookName=" + bookName
				+ ", bookcaseName=" + bookcaseName + ", price=" + price + ", author=" + author + ", readerName="
				+ readerName + ", sex=" + sex + ", readerBarcode=" + readerBarcode + ", birthday=" + birthday
				+ ", paperType=" + paperType + ", paperNo=" + paperNo + ", telephone=" + telephone + ", readerType="
				+ readerType + ", degree=" + degree + ", publishName=" + publishName + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReaderId() {
		return readerId;
	}

	public void setReaderId(Integer readerId) {
		this.readerId = readerId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getIfBack() {
		return ifBack;
	}

	public void setIfBack(Integer ifBack) {
		this.ifBack = ifBack;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
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

	public String getBookcaseName() {
		return bookcaseName;
	}

	public void setBookcaseName(String bookcaseName) {
		this.bookcaseName = bookcaseName;
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

	public String getReaderName() {
		return readerName;
	}

	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getReaderBarcode() {
		return readerBarcode;
	}

	public void setReaderBarcode(String readerBarcode) {
		this.readerBarcode = readerBarcode;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public String getPaperNo() {
		return paperNo;
	}

	public void setPaperNo(String paperNo) {
		this.paperNo = paperNo;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getReaderType() {
		return readerType;
	}

	public void setReaderType(String readerType) {
		this.readerType = readerType;
	}

	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	public String getPublishName() {
		return publishName;
	}

	public void setPublishName(String publishName) {
		this.publishName = publishName;
	}

}
