package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String language, String address) {
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("(+91)9833098750");
		p.setWebsite("https://rahullshettyacademy.com");
		p.setName(name);
		List<String> myA = new ArrayList<String>();
		myA.add("shoe park");
		myA.add("storey");
		myA.add("my data");
		p.setTypes(myA);

		Location l = new Location();
		l.setLat(-38.383494);
		l.setLat(33.457845);
		p.setLocation(l);
		
		return p;
	}
	
	public String deletePayLoad(String placeID) {
		return "{\r\n    \"place_id\":\""+placeID+"\"\r\n}";
	}
}
