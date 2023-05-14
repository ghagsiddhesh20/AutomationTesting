package stepDefinations;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utility;

public class StepDefination extends Utility {

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild testdata = new TestDataBuild();
	static String place_id;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws Throwable {
		res = given().spec(reqSpecification()).body(testdata.addPlacePayload(name, language, address));

	}
	
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
		res = given().spec(reqSpecification()).body(testdata.deletePayLoad(place_id));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		APIResources apiresource = APIResources.valueOf(resource);
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("post")) {
			response = res.when().post(apiresource.getResource());
		} else if (method.equalsIgnoreCase("get")) {
			response = res.when().get(apiresource.getResource());
		} else if (method.equalsIgnoreCase("delete")) {
			response = res.when().delete(apiresource.getResource());
		}
		else if (method.equalsIgnoreCase("put")) {
			response = res.when().put(apiresource.getResource());
		}
	}

	@Then("the API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} is response body is {string}")
	public void is_response_body_is(String keyValue, String ExpectedValue) {

		assertEquals(getJsonPath(response, keyValue), ExpectedValue);
	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedname, String resource) throws IOException {
		place_id = getJsonPath(response, "place_id");
		res = given().spec(reqSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "Get");
		String actualname = getJsonPath(response, "name");
		assertEquals(actualname, expectedname);
	}
}
