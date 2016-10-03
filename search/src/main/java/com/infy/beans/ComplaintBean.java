package com.infy.beans;

import java.util.Date;
import java.util.List;

public class ComplaintBean {
	
	private String complaintId;
	private Date date;
	private String product;
	private String issue;
	private String compliantText;
	private String companyResponse;
	private String company;
	private String zipCode;
	private List<String> tags;
	
	
	public String getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getCompliantText() {
		return compliantText;
	}
	public void setCompliantText(String compliantText) {
		this.compliantText = compliantText;
	}
	public String getCompanyResponse() {
		return companyResponse;
	}
	public void setCompanyResponse(String companyResponse) {
		this.companyResponse = companyResponse;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
