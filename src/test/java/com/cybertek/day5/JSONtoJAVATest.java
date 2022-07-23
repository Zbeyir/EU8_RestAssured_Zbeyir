package com.cybertek.day5;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class JSONtoJAVATest extends SpartanTestBase {


    @DisplayName("GET one Spartan and deserialize to Map")
    @Test
    public void oneSpartanToMap() {

        Response response = given().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();

        //get the json and convert it to the map
        Map<String,Object> jsonMap = response.as(Map.class);  // map e cevirdik

        System.out.println(jsonMap.toString());

        String actualName = (String) jsonMap.get("name"); // sonra get ile cagirip test yaptik
        assertThat(actualName,is("Meta"));

    }

    @DisplayName("GET all spartans to JAVA data structure")
    @Test
    public void getAllSpartanTest(){

        Response response = get("/api/spartans").then().statusCode(200).extract().response();

        //we need to convert json to java  which is deserialize

        List<Map<String,Object>> jsonList = response.as(List.class); // burada tüm spartan 'i map in icine aldik amp icinde tekra map


        //--jsonList.get(1).get("name")--> burada daha önce yaptigimiz items[1].names gibi birsey--> ama artik java da map a cevirdik
        //onun icin ilk get array ikinci hangi element ise onu yaziyoruz
        System.out.println(jsonList.get(1).get("name"));

        Map<String,Object>spartan3 = jsonList.get(2);// burada sadec 3. siradki spartan 'i istedik ve onun icin index 2 yazdik get e
        System.out.println("spartan3 = " + spartan3);


    }

}
