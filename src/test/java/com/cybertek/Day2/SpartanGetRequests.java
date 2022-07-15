package com.cybertek.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {

    String baseUrl = "http://44.202.63.224:8000";
    //!!!--burada dikkat dünkünden farkli olarak "/api/spartans" bu ksimi sildik----!!!


//    Given Accept type application/json
//    When user send GET request to api/spartans end point
//    Then status code must 200
//    And response Content Type must be application/json
//    And response body should include spartan result

    @Test
    public void test1() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans");

        //printing status code from response object
        System.out.println("response.statusCode() = " + response.statusCode());


        //priting response content type from response object
        System.out.println("response.contentType() = " + response.contentType());

        //print whole result body
        response.prettyPrint();

        //how to do API testing then ?
        //verify status code is 200
        Assertions.assertEquals(200, response.statusCode() );

        //verify content type is application/json
        Assertions.assertEquals("application/json", response.contentType());
    }


    /*
        Given accept header is application/json
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json
        and json body should contain Fidole
     */

    @DisplayName("GET one spartan /api/spartans/3 and verify")
    @Test
    public void test2() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans/3");



        //how to do API testing then ?
        //verify status code is 200
        Assertions.assertEquals(200, response.statusCode());

        //verify content type is application/json
        Assertions.assertEquals("application/json", response.contentType());

        //verify json body contains Fidole
        Assertions.assertTrue(response.asString().contains("Fidole"));
        //bu yönteme gercek hayat ta kullanmiyoruz simdilik kullaniyoruz,
        // cünkü "Fidole" bellki gender da yaziyor name de degil

     }





}
