package api.tests;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class TwitterAPIToken {
	String oauth2;
	@Test
	public void testApiOauthToken() {
		baseURI="https://api.twitter.com/";
		basePath="oauth2/token";
		
		Response response=given().accept(ContentType.URLENC)
				.auth()
				.preemptive()
				.basic("GcExgJg85VjOL3apjp3kqC75b", "6ji2yWmc2VnQsSXlp3am6OXqiKTcm1aQldTwDlIfBCQGtK3ECS")
				.param("grant_type", "client_credentials")
				.expect()
				.statusCode(200)
				.when()
				.post();
		JsonPath jsonPath=response.jsonPath();
		oauth2=jsonPath.getString("access_token");
		System.out.println(oauth2);
	}

}
