package com.cybertek.day4;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\": 2}")
                .when()
                .get("/countries");


        assertEquals(200, response.statusCode());

        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));


        //print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print first CountryId
        String firstCountryId = response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);


        //print second country name
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);


        //print "http://44.202.63.224:1000/ords/hr/countries/CA"

        //String thirdHref = response.path("items[2].links.href"); // . nokta bir alt element e gigiyor, ama önce array in icine girmen lazim -->bu durum da sonuc faild
        //String thirdHref = response.path("items[2].links[1].href"); // birtane array var [1] --> bunu yazdigimiz da sonuc-->faild
        String thirdHref = response.path("items[2].links[0].href");
        System.out.println("thirdHref = " + thirdHref);


        //get me all country names
        List<String> countryName = response.path("items.country_name");
        System.out.println("countryName = " + countryName);


        //assert that all regions ids are equal to 2
        List<Integer> allRegionsIds = response.path("items.region_id");

        for (Integer regionsId : allRegionsIds) {

            System.out.println("regionsId = " + regionsId);
            assertEquals(2,regionsId);
        }



    }


}
