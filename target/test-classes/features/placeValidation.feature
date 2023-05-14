
Feature: Validating Place API's

  @AddPlace @Regression
  Scenario Outline: Verify if Place is being Successfully created using AddPlaceApi
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "post" http request
    Then the API call is success with status code 200
    And "status" is response body is "OK"
    And "scope" is response body is "APP"
    And verify place_Id created maps to "<name>" using "GetPlaceAPI"

    Examples: 
      | name         | language   | address             |
      | Sid Infotech | English-EN | Nepean sea Road     |
      | WTC          | Latin-LN   | Marine Drive Street |

  @DeletePlace @Regression
  Scenario: Verify if Delete Place functionality is working
    Given DeletePlace Payload
    When user calls "DeletePlaceAPI" with "delete" http request
    Then the API call is success with status code 200
    And "status" is response body is "OK"
