package com.leopin.parkfifty.admin.web.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.leopin.parkfifty.admin.domain.AdminDomain;
import com.leopin.parkfifty.shared.domain.Company;
import com.leopin.parkfifty.shared.domain.CompanyAndUser;
import com.leopin.parkfifty.shared.domain.CompanyUser;
import com.leopin.parkfifty.shared.domain.ExceptionInfo;

//@RunWith(SpringJUnit4ClassRunner.class)  
//@ContextConfiguration(locations = { "classpath:parkfifty-servlet.xml" }) 
public class AdminJsonWebTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminJsonWebTests.class);
	
	private static final String adminURL = "http://localhost:8889/admin";
//	private String scheme = "http";
//	private String host = "localhost";
//	private int port = 8888;
//	private String path = "admin";
	
	
	@Before
	public void setUp() throws Exception {

	}
	
//	@Test
	public void testGetCompanyById() {
			
		Map<String, String> urlVars = new HashMap<String, String>();
		urlVars.put("urlPrefix", adminURL);
		urlVars.put("companyId", "14");
		ResponseEntity<Company> response = new RestTemplate().getForEntity("{urlPrefix}/company/{companyId}", Company.class, urlVars);
		
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new AssertionError("Exception Occurred. Http Status code: " + response.getStatusCode());
		}
		
		assertEquals("Test Company", response.getBody().getName());
	}

//	@Test(expected=HttpClientErrorException.class)
	public void testGetCompanyByInvalidId() throws Exception {
		
		try {
			Map<String, String> urlVars = new HashMap<String, String>();
			urlVars.put("urlPrefix", adminURL);
			urlVars.put("companyId", "9999999");
			ResponseEntity<ExceptionInfo> response = new RestTemplate().getForEntity("{urlPrefix}/company/{companyId}", ExceptionInfo.class, urlVars);
			
		} catch (HttpClientErrorException e) {

			assertEquals(HttpStatus.METHOD_FAILURE, e.getStatusCode());
			ObjectMapper mapper = new ObjectMapper();
			ExceptionInfo ei = mapper.readValue(e.getResponseBodyAsString(), ExceptionInfo.class);
			assertEquals("error.app.admin.company.not.found", ei.getKey());
			assertEquals("Unable to find company by 9999999.", ei.getDescription());
			throw e;
			
		}
		


	}
	
//	@Test
	public void testGetCompanyByName() {
		Map<String, String> urlVars = new HashMap<String, String>();
		urlVars.put("urlPrefix", adminURL);
		urlVars.put("companyName", "This is a Good Company 45397");
		
		ResponseEntity<Company> response = new RestTemplate().getForEntity("{urlPrefix}/company/{companyName}", Company.class, urlVars);
		
		assertEquals("This is a Good Company 45397", response.getBody().getName());
		assertEquals("9194553262", response.getBody().getPriPhone());
		assertEquals("9194470110", response.getBody().getFax());
		
	}
	
//	@Test(expected=HttpClientErrorException.class)
	public void testGetCompanyByInvalidName() throws Exception {
		try {
			Map<String, String> urlVars = new HashMap<String, String>();
			urlVars.put("urlPrefix", adminURL);
			urlVars.put("companyName", "Park Fifty");
			
			ResponseEntity<Company> response = new RestTemplate().getForEntity("{urlPrefix}/company/{companyName}", Company.class, urlVars);
			
			assertEquals("Test Company", response.getBody().getName());
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.METHOD_FAILURE, e.getStatusCode());
			ObjectMapper mapper = new ObjectMapper();
			ExceptionInfo ei = mapper.readValue(e.getResponseBodyAsString(), ExceptionInfo.class);
			assertEquals("error.app.admin.company.not.found", ei.getKey());
			assertEquals("Unable to find company by Park Fifty.", ei.getDescription());
			throw e;
		}
	}	
	
//	@Test
	public void testAddVerifyDeleteCompany() {
		
		Map<String, String> urlVars = new HashMap<String, String>();
		urlVars.put("urlPrefix", adminURL);
		urlVars.put("companyName", "Delete Company");
		
		Company company = new Company();
		company.setName("Delete Company");
		company.setEmail("gpinto@bbandt.com");
		company.setUrl("http://www.ashriv.com");
		company.setPriPhone("(919) 455-3262");
		company.setSecPhone("");
		company.setFax("(919) 447-0110");
		
		LOGGER.debug(company.toString());

		ResponseEntity<Company> response = new RestTemplate().postForEntity("{urlPrefix}/company", company, Company.class, urlVars);
		
		assertNotNull(response.getBody().getId());		

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		
		ResponseEntity<Company> responseGet = new RestTemplate().getForEntity("{urlPrefix}/company/{companyName}", Company.class, urlVars);
		assertEquals("Delete Company", responseGet.getBody().getName());
		
		new RestTemplate().delete("{urlPre`fix}/company/{companyName}", urlVars);
		
	}
	
	@Test
	public void testAddCompanyAndUser() {
		
		Map<String, String> urlVars = new HashMap<String, String>();
		urlVars.put("urlPrefix", adminURL);
		

		CompanyAndUser companyAndUser = AdminDomain.getCompanyAndUser();
		String companyName = companyAndUser.getCompany().getName();
		String userId = companyAndUser.getCompanyUser().getUserId();
		
		ResponseEntity<CompanyAndUser> response = new RestTemplate().postForEntity("{urlPrefix}/company", companyAndUser, CompanyAndUser.class, urlVars);
		
		assertEquals(companyName, response.getBody().getCompany().getName());
		assertEquals(userId, response.getBody().getCompanyUser().getUserId());
		assertNotNull(response.getBody().getCompany().getId());
		assertNotNull(response.getBody().getCompanyUser().getId());
		
	}
	
//	@Test
	public void testAddCompanyUser() {
		
		Map<String, String> urlVars = new HashMap<String, String>();
		urlVars.put("urlPrefix", adminURL);
		
		CompanyUser companyUser = AdminDomain.getCompanyUser(25L);
		String userId = companyUser.getUserId();
		LOGGER.debug(companyUser.toString());
		
		HttpHeaders httpHeaders = new HttpHeaders();
		List<MediaType> mediaTypeList = new ArrayList<MediaType>();
		mediaTypeList.add(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(mediaTypeList);
		HttpEntity<CompanyUser> httpEntity = new HttpEntity<CompanyUser>(companyUser, httpHeaders);
		ResponseEntity<CompanyUser> response = new RestTemplate().postForEntity("{urlPrefix}/company/companyUser", httpEntity, CompanyUser.class, urlVars);
		
		assertEquals(userId, response.getBody().getUserId());
		LOGGER.debug(response.getBody().toString());
		assertNotNull(response.getBody().getId());
		
	}
	
//	@Test
//	public void testAddVerifyDeleteCompany() throws Exception 
//		HttpClient httpClient = new DefaultHttpClient();
//		
//		HttpPost request = new HttpPost(adminURL + "/company");
//		request.setHeader(new BasicHeader("Accept", "application/json"));
//		
//		request.se
//		
//		HttpResponse response = httpClient.execute(request);
//		
//		HttpEntity entity = response.getEntity();
//		
//		ObjectMapper mapper = new ObjectMapper();
//		Company company = mapper.readValue(entity.getContent(), Company.class);
//		
//		assertEquals("Park Fifty", company.getName());
//			
//	}
	
//	@Test
//	public void testGetCompanyById() {
//		try {
//			
//			HttpClient httpClient = new DefaultHttpClient();
//			
//			HttpGet request = new HttpGet(adminURL + "/company/1");
//			request.setHeader(new BasicHeader("Accept", "application/json"));
//			
//			HttpResponse response = httpClient.execute(request);
//			
//			HttpEntity entity = response.getEntity();
//			
//			ObjectMapper mapper = new ObjectMapper();
//			Company company = mapper.readValue(entity.getContent(), Company.class);
//			
//			assertEquals("Park Fifty", company.getName());
//			
//		} catch (Exception e) {
//			throw new AssertionError("Exception Occurred");
//		}
//	}

//	@Test
//	public void testGetCompanyByInvalidId() {
//		try {
//			
//			HttpClient httpClient = new DefaultHttpClient();
//			
//			HttpGet request = new HttpGet(adminURL + "/company/9999999");
//			request.setHeader(new BasicHeader("Accept", "application/json"));
//			
//			HttpResponse response = httpClient.execute(request);
//			
//			HttpEntity entity = response.getEntity();
//			
//			assertEquals(HttpStatus.SC_METHOD_FAILURE, response.getStatusLine().getStatusCode());
//			
//		
//			ObjectMapper mapper = new ObjectMapper();
//			ExceptionInfo ei = mapper.readValue(entity.getContent(), ExceptionInfo.class);
//			assertEquals("error.app.admin.company.not.found", ei.getKey());
//			assertEquals("Unable to find company by 9999999.", ei.getDescription());
//			
////			Company company = mapper.readValue(entity.getContent(), Company.class);
////			
////			assertEquals("Park Fifty", company.getName());
//			
//		} catch (Exception e) {
//			throw new AssertionError("Exception Occurred");
//		}
//	}
//	@Test
//	public void testGetCompanyByName() {
//		try {
//			
//			
//			HttpClient httpClient = new DefaultHttpClient();
//			String name = encode("Park Fifty", "UTF-8");
//			HttpGet request = new HttpGet(adminURL + "/company/" + name);
//			
//			request.setHeader(new BasicHeader("Accept", "application/json"));
//			
//			HttpResponse response = httpClient.execute(request);
//			
//			HttpEntity entity = response.getEntity();
//			
//			ObjectMapper mapper = new ObjectMapper();
//			Company company = mapper.readValue(entity.getContent(), Company.class);
//			
//			assertEquals("Park Fifty", company.getName());
//			
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage(), e);
//			throw new AssertionError(e.getMessage());
//		}
//	}
	
	
}
