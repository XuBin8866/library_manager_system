package com.xxbb.actionform;
/**  
    * @Title: ReaderForm.java
    * @Package com.xxbb.actionform
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年1月28日
    * @version V1.0  
    */

public class ReaderForm {
	private Integer id;
	private String barcode;
    private String birthday;
    private String createDate;
    private String email;
    private String name;
    private String operator;
    private String paperNO;
    private String paperType;
    private String remark;
    private String sex;
    private String telephone;
    private String vocation;
    

    private int typeId;
    private String typeName;
    private int allowBorrowAmount;
	public ReaderForm() {
		super();
	}
	@Override
	public String toString() {
		return "ReaderForm [id=" + id + ", barcode=" + barcode + ", birthday=" + birthday + ", createDate=" + createDate
				+ ", email=" + email + ", name=" + name + ", operator=" + operator + ", paperNO=" + paperNO
				+ ", paperType=" + paperType + ", remark=" + remark + ", sex=" + sex + ", telephone=" + telephone
				+ ", vocation=" + vocation + ", typeId=" + typeId + ", typeName=" + typeName + ", allowBorrowAmount="
				+ allowBorrowAmount + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getPaperNO() {
		return paperNO;
	}
	public void setPaperNO(String paperNO) {
		this.paperNO = paperNO;
	}
	public String getPaperType() {
		return paperType;
	}
	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getVocation() {
		return vocation;
	}
	public void setVocation(String vocation) {
		this.vocation = vocation;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getAllowBorrowAmount() {
		return allowBorrowAmount;
	}
	public void setAllowBorrowAmount(int allowBorrowAmount) {
		this.allowBorrowAmount = allowBorrowAmount;
	}
    
}
