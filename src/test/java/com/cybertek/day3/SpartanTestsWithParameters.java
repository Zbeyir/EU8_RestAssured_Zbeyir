package com.cybertek.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithParameters {


    @BeforeAll
    public static void init() {

        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://44.202.63.224:8000";
    }

    /*
          Given accept type is Json
          And Id parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "Blythe" should be in response payload ==> BUNUN MANASI BODY DEMEK
     */

    @DisplayName("GET request to /api/spartans/{5} WITH id 5")
    @Test
    public void test1() {

        //  Response response = given().accept(ContentType.JSON).when().get("/api/spartans/5");
        //ben yukaridaki gibi yaptim ama hoca alta ki gibi yapti
        Response response = given()
                .accept(ContentType.JSON)
                .and().pathParam("id", 5) // burada ki and() 'i silebiliriz ama böyle daha okunakli
                .when()
                .get("/api/spartans/{id}");


        //verify status code is 406
        assertEquals(200, response.statusCode());

        //verify content type
        assertEquals("application/json", response.contentType());

        //verify Blythe in the json payload/body
        assertTrue(response.body().asString().contains("Blythe"));

    }

    /*
     TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */

    @DisplayName("GET request to /api/spartans/{id} Negative Test")
    @Test
    public void test2() {

            Response response = given()
                    .accept(ContentType.JSON)
                    .and().pathParam("id", 500) // burada ki and() 'i silebiliriz ama böyle daha okunakli
                    .when()
                    .get("/api/spartans/{id}");

            //verify status code is 404
            assertEquals(404, response.statusCode());

            //verify content type
            assertEquals("application/json", response.contentType());

            //verify Not Found in the json payload/body
            assertTrue(response.body().asString().contains("Not Found"));
    }


    /*
     Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @DisplayName("GET request to /api/spartans/search with Query Params")
    @Test
    public void test3() {

        Response response = given()
                .accept(ContentType.JSON)
                .and().queryParam("nameContains","e") //bu test tin bir üstekinden farki buralar
                .and().queryParam("gender","Female")  //bu test tin bir üstekinden farki buralar
                .when()
                .get("/api/spartans/search");

        //verify status code is 200
        assertEquals(200, response.statusCode());

        //verify content type
        assertEquals("application/json", response.contentType());

        //  And "Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));
        //   And "Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));
    }

    @DisplayName("GET request to /api/spartans/search with Query Params")
    @Test
    public void test4() {

        Response response = given()
                .accept(ContentType.JSON).log().all() // bunun da tek farki-->log().all()-->bu bize icerigini görmemizi sagliyor zorunlu degil
                .and().queryParam("nameContains","e")
                .and().queryParam("gender","Female")
                .when()
                .get("/api/spartans/search");

        //verify status code is 200
        assertEquals(200, response.statusCode());

        //verify content type
        assertEquals("application/json", response.contentType());

        //  And "Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));
        //   And "Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));
    }



    @DisplayName("GET request to /api/spartans/search with Query Params (MAP)")
    @Test
    public void test5(){
        //create a map and add query parameters
        Map<String,Object> queryMap = new HashMap<>();  // bunun da test3 'den farki burasi MAP ile yaptik bunu
        queryMap.put("nameContains","e");
        queryMap.put("gender","Female");

        Response response = given()
                .log().all()
                .accept(ContentType.JSON)
                .and().queryParams(queryMap)  // tabii dogal olark birde farki burasi
                .when()
                .get("/api/spartans/search");


        //verify status code is 200
        assertEquals(200, response.statusCode());

        //verify content type
        assertEquals("application/json", response.contentType());

        //  And "Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));
        //   And "Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));




    }



}
