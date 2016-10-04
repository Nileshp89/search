package com.infy.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.infy.beans.ComplaintBean;



public class JsonParser {
	
	public ComplaintBean[] getComplaints(String complaintsStr) {

		ComplaintBean[] complaints = null;
		
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.configure(
				DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {
			complaints = mapper.readValue(complaintsStr, ComplaintBean[].class);
		} catch (JsonGenerationException e) {
			System.out.println(" MESSAGE= " + "\"JsonGenerationException\"");
		} catch (JsonMappingException e) {
			System.out.println(" MESSAGE= " + "\"JsonMappingException\"");
		} catch (IOException e) {
			System.out.println(" MESSAGE= " + "\"IOException\"");
		} catch (Exception e) {
			System.out.println(" MESSAGE= " + "\"Exception\"");
		}
		
		for(int i=0; i<complaints.length; i++) {
			System.out.println(complaints[i].getCompany());
			
		}
		
		return complaints;
		
	}
}
