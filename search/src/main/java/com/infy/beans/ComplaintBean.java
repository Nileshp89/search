package com.infy.beans;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ComplaintBean {
	
	@JsonProperty(value = "Complaint ID")
	private String complaintId;
	
	@JsonProperty(value = "Date received")
	private String date;
	@JsonProperty(value = "Product")
	private String product;
	@JsonProperty(value = "Issue")
	private String issue;
	@JsonProperty(value = "Consumer complaint narrative")
	private String compliantText;
	@JsonProperty(value = "Company public response")
	private String companyResponse;
	@JsonProperty(value = "Company")
	private String company;
	@JsonProperty(value = "ZIP code")
	private String zipCode;
	
	
	public String getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ComplaintBean [complaintId=" + complaintId + ", date=" + date
				+ ", product=" + product + ", issue=" + issue
				+ ", compliantText=" + compliantText + ", companyResponse="
				+ companyResponse + ", company=" + company + ", zipCode="
				+ zipCode + "]";
	}
	
	
}

