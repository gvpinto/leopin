package com.leopin.parkfifty.webtest.admin.json;

import static java.net.URLEncoder.encode;
import static org.junit.Assert.assertEquals;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leopin.parkfifty.shared.domain.Company;

public class AdminJsonWebTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminJsonWebTests.class);
	
	private static final String adminURL = "http://localhost:8888/admin";
//	private String scheme = "http";
//	private String host = "localhost";
//	private int port = 8888;
//	private String path = "admin";
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetCompanyById() {
		try {
			
			HttpClient httpClient = new DefaultHttpClient();
			
			HttpGet request = new HttpGet(adminURL + "/company/1");
			request.setHeader(new BasicHeader("Accept", "application/json"));
			
			HttpResponse response = httpClient.execute(request);
			
			HttpEntity entity = response.getEntity();
			
			ObjectMapper mapper = new ObjectMapper();
			Company company = mapper.readValue(entity.getContent(), Company.class);
			
			assertEquals("Park Fifty", company.getName());
			
		} catch (Exception e) {
			throw new AssertionError("Exception Occurred");
		}
	}
	

	@Test
	public void testGetCompanyByName() {
		try {
			
			HttpClient httpClient = new DefaultHttpClient();
			String name = encode("Park Fifty", "UTF-8");
			HttpGet request = new HttpGet(adminURL + "/company/" + name);
			
//			HttpGet request = new HttpGet(URIUtils.createURI(
//					scheme, host, port, path + "/company/Park Fifty", null, null));
			
			request.setHeader(new BasicHeader("Accept", "application/json"));
			
			HttpResponse response = httpClient.execute(request);
			
			HttpEntity entity = response.getEntity();
			
			ObjectMapper mapper = new ObjectMapper();
			Company company = mapper.readValue(entity.getContent(), Company.class);
			
			assertEquals("Park Fifty", company.getName());
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new AssertionError(e.getMessage());
		}
	}
	

}
