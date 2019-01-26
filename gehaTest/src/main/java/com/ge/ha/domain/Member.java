package com.ge.ha.domain;

public class Member {
	private int memberCode;
	private String id;
	private String password;
	private String memberName;
	private String gender;
	private String authority;
	private String businessLicense;
	private String authCode;
	private String status;
	
	

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



	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Member [memberCode=" + memberCode + ", id=" + id + ", password=" + password + ", memberName="
				+ memberName + ", gender=" + gender + ", authority=" + authority + ", businessLicense="
				+ businessLicense + ", authCode=" + authCode + ", status=" + status + "]";
	}



}
