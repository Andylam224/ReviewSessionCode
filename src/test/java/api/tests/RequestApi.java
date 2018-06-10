package api.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class RequestApi {
	//junit 5 came out
	//a lot of new features
	
	/*
	 * Send a get request to https://reqres.in/api/users
	 * including query param - page=2
	 * Accept type: JSON
	 * Verify status code 200, verify response body
	 */
	@Test
	public void getUsersTest() {
	Response response=	given().params("page",2).and().accept(ContentType.JSON).when().get("https://reqres.in/api/users");
		
		System.out.println(response.getStatusLine());
		System.out.println(response.getContentType());
		System.out.println(response.headers());
		System.out.println(response.body().asString());
		
	//add assertions for parts of response.
		assertEquals(200, response.statusCode());
		//assert contentType
		assertTrue(response.contentType().contains("application/json"));
		//assert header
		Header header=new Header("X-Powered-By","Express");
		assertTrue(response.headers().asList().contains(header));
		
		
		//checking data using jsonPath - total=12
		JsonPath json=response.jsonPath();
		assertEquals(12, json.getInt("total"));
		//total page is 4
		assertEquals(4, json.getInt("total_pages"));
		//first id is 4
		assertEquals(4,json.getInt("data.id[0]"));
		//verify that Charles's id is 5
		//find charles first
		System.out.println(json.getInt("data.find{it.first_name =='Charles'}.id"));
		assertEquals(5,json.getInt("data.find{it.first_name =='Charles'}.id"));
		//language is groovy - when you have array, you can use find with {} following
		//it.firstname is like this.firstname in java
		
		//make sure whoever id is 6, their first name is Tracey, last name is Ramos
		//Assert using JsonPath that person with id 6
		//System.out.println(json.getString("data.find{it.id=='5'}.first_name)"));
		assertEquals("Tracey",json.getString("data.find{it.id==6}.first_name"));
		assertEquals("Ramos",json.getString("data.find{it.id==6}.last_name"));

	}
	

}
