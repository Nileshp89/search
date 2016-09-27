package com.infy;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;


public class SolrUtil {
	
	CloudSolrServer server;
	
	
	public SolrUtil(){
		CloudSolrServer server = new CloudSolrServer("localhost:9983");
		server.setDefaultCollection("mycollection");
	}
		
	public void addDatatoIndex() throws SolrServerException, IOException{	
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField( "id", "2");
		doc.addField( "title", "A lovely summer holiday");
		server.add(doc);
		server.commit();
	}
	
	
}
