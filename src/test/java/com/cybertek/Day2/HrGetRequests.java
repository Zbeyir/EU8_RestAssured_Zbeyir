package com.cybertek.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//import static io.restassured.RestAssured.given; // 2. adim improt ettik 1. adim da kisa yol kullanarak
import static io.restassured.RestAssured.*; // 3. adim .given() ' i sildik ve onun yerine * yazdik
//import static org.junit.jupiter.api.Assertions.assertEquals; //2. adim improt ettik 1. adim da kisa yol kullanarak
import static org.junit.jupiter.api.Assertions.*; //3. adim .assertEquals ' i sildik ve onun yerine * yazdik


public class HrGetRequests {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name
    @BeforeAll
    public static void init() {

        //save baseurl inside this variable so that we dont need to type each http method.
        RestAssured.baseURI = "http://44.202.63.224:1000/ords/hr";

    }

    @DisplayName("GET request to /regions")
    @Test
    public void test1() {
        Response response = get("/regions");

        //print the status code
        System.out.println(response.statusCode());

    }

    /*
        Given accept type is application/json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains   Americas
     */

    @DisplayName("GET request to /regions/2 ")
    @Test
    public void test2() {

        //  Response response = RestAssured.get("/regions");
        //ben direkt bu yukari da ki gibi yazip gecmistim hata vermedi ama alltaki tam dogru
        //cünkü bizden .json olarak istiyor
//=============================================

        //  Response response = RestAssured.given().accept(ContentType.JSON)
        //                .when()
        //                  .get("/regions/2");

        Response response = given().accept(ContentType.JSON).
                when()
                .get("/regions/2");
        // bu yukaridan RestAssured. bunu sildik --1.adim


        //verify status code
        assertEquals(200, response.statusCode());
        // 1. adim da Assertions. 'ni sildik

        //verify content type
        assertEquals("application/json", response.contentType());

        response.prettyPrint();  // bunu yazinca body 'i oldugu gibi alip geliyor

        //verify body contains Americas
        assertTrue(response.body().asString().contains("Americas"));


    }

}
