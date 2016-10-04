package com.infy.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.solr.client.solrj.SolrServerException;

import com.infy.beans.ComplaintBean;
import com.infy.solr.SolrUtil;

public class LoadData {

	public static void main(String[] args) throws IOException, SolrServerException {
		
		String text = "";
		File file = new File("D:\\LatestCode-Search\\search\\search\\new 4");
		text = FileUtils.readFileToString(file,"UTF-8");
		
		JsonParser jsonParser = new JsonParser();
		ComplaintBean[] beans = jsonParser.getComplaints(text);
		
		SolrUtil solrUtil = new SolrUtil();
		
		
		for (int i = 0; i < beans.length; i++) {
		
			solrUtil.addDatatoIndex(beans[i]);
		}
		
		System.out.println("Successfully indexed ...");
		
		
	}
}
