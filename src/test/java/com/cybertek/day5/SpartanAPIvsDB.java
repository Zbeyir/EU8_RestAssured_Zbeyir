package com.cybertek.day5;

import com.cybertek.utilities.DBUtils;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SpartanAPIvsDB extends SpartanTestBase {

    @DisplayName("GET one spartan from api and database")
    @Test
    public void testDB1(){

        //get id,name,gender phone  from database
        //get same information from api
        //compare

        //1. get id,name,gender phone  from database

        String query = "select SPARTAN_ID,NAME,GENDER,PHONE from SPARTANS\n" +
                "where SPARTAN_ID = 15";

        //save data inside the map
        Map<String, Object> dbMap = DBUtils.getRowMap(query);
        System.out.println(dbMap);



        //2.get info from api
        Map<String,Object> apiMAP = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and().contentType("application/json")
                .extract().response().as(Map.class);

        System.out.println(apiMAP);

        //3.compare database vs api --> we assume expected result is db
        assertThat(apiMAP.get("id").toString(),is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(apiMAP.get("name"),is(dbMap.get("NAME")));
        assertThat(apiMAP.get("gender"),is(dbMap.get("GENDER")));
        assertThat(apiMAP.get("phone").toString(),is(dbMap.get("PHONE").toString()));




    }


}
