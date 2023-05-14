package stepDefinations;

import io.cucumber.java.Before;

public class Hooks {

	StepDefination sd;

	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable {
		sd = new StepDefination();
		if (StepDefination.place_id == null) {
			sd.add_place_payload_with("Tim", "German", "London");
			sd.user_calls_with_http_request("AddPlaceAPI", "POST");
			sd.verify_place_Id_created_maps_to_using("Tim", "GetPlaceAPI");
		}
	}

}
