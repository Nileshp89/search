package com.infy.solr;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Component;

import com.infy.beans.ComplaintBean;
import com.infy.solrj.SolrServerFactory;

@Component
public class SolrUtil {

	HttpSolrServer server;

	@PostConstruct
	public void init() {
		String solrURL = "http://localhost:9212/solr/datacollection";
		server = (HttpSolrServer) SolrServerFactory.getInstance().createServer(
				solrURL);
		
		
	}

	public void addDatatoIndex(ComplaintBean bean) throws SolrServerException, IOException {
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("datereceived", bean.getDate());
		doc.addField("product", bean.getProduct());
		doc.addField("issue", bean.getIssue());
		doc.addField("complaint", bean.getCompliantText());
		doc.addField("companyresponse",bean.getCompanyResponse());
		doc.addField("company", bean.getCompany());
		doc.addField("zip", bean.getZipCode());
		doc.addField("complaintid", bean.getComplaintId());
	
		server.add(doc);
		server.commit();
	}
	
	
	public void searchData(String queryText,String filterQuery) throws SolrServerException, IOException{
		
		System.out.println("Searching data");
		
		SolrQuery query = new SolrQuery();
	    query.setQuery("complaint:"+queryText);
	    query.addFilterQuery(filterQuery);
	    query.setStart(0);    
	    query.set("defType", "edismax");
	    
	    
	    System.out.println(query.toQueryString());

	    QueryResponse response = server.query(query);
	    SolrDocumentList results = response.getResults();
	    for (int i = 0; i < results.size(); ++i) {
	      System.out.println(results.get(i));
	    }
		
	}

}
