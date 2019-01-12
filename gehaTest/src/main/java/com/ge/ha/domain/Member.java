package com.ge.ha.domain;

public class Member {
	private int memberCode;
	private String id;
	private String password;
	private String memberName;
	private String sex;
	private String authorityCode;
	private String businessLicense;
	
	

	public int getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	@Override
	public String toString() {
		return "Member [memberCode=" + memberCode + ", id=" + id + ", password=" + password + ", memberName="
				+ memberName + ", sex=" + sex + ", authorityCode=" + authorityCode + ", businessLicense="
				+ businessLicense + "]";
	}

}
