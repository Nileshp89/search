package com.infy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.infy.nlpprocessor.IssueClassifier;
import com.infy.nlpprocessor.TextEntityExtractor;
import com.infy.nlpprocessor.TextPreProcessor;
import com.infy.solr.SolrUtil;

import gate.Annotation;
import gate.AnnotationSet;
import gate.Document;
import gate.util.GateException;

@Controller
public class SearchController {

	@Autowired
	private TextEntityExtractor textEntityExtractor;

	@Autowired
	private TextPreProcessor textPreProcessor;
	
	@Autowired
	private IssueClassifier issueClasifier;
	
	@Autowired
	SolrUtil solrUtil;
	
	Map<String, String> issueNames = new HashMap<String,String>();
	
	@PostConstruct
	private void init(){
			
		issueNames.put("OT", "Other");
		issueNames.put("RW", "Rewards");
		issueNames.put("LF", "Late fee");
		issueNames.put("BD", "Billing disputes");
		issueNames.put("CLID", "Credit line increase/decrease");
		issueNames.put("ITFR", "Identity theft / Fraud / Embezzlement");
		issueNames.put("OTF", "Other fee");
		issueNames.put("CCA", "Closing/Cancelling account");
		issueNames.put("DA", "Delinquent account");
		issueNames.put("CSCR", "Customer service / Customer relations");
		issueNames.put("OF", "Overlimit fee");
		issueNames.put("AAM", "Advertising and marketing");
		issueNames.put("APD", "Application processing delay");
		issueNames.put("APROIR", "APR or interest rate");
		issueNames.put("BS", "Billing statement");
		issueNames.put("PR", "Privacy");
		issueNames.put("TI", "Transaction issue");
		issueNames.put("PP", "Payoff process");
		issueNames.put("BK", "Bankruptcy");
		issueNames.put("CD", "Credit determination");
		issueNames.put("CCPDP", "Credit card protection / Debt protection");
	}

	@RequestMapping(produces = "application/json", value = "/search", method = RequestMethod.GET)
	public ResponseEntity<Object> greeting(
			@RequestParam(value = "query", required = false) String query, Model model)
			throws GateException, IOException, SolrServerException {

		textPreProcessor.POSTagData(query);
		
		String issueSC = issueClasifier.test(query);
		
		String issue = issueNames.get(issueSC);
		
		Document doc = textEntityExtractor.extractEntities(query);

		List<String> companyNames = textEntityExtractor.getEntityData("Organization", doc);

		
		solrUtil.searchData(query, "issue:\""+issue+"\"");
		
		
		ResponseEntity<Object> entity = new ResponseEntity<>(HttpStatus.ACCEPTED);

		return entity;
	}

}