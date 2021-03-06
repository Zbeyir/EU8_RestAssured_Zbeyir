package com.cybertek.day5;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest {

    /*
     given accept type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
     */

    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1(){
/*
mesele neyin gerekli olup olmadigini anlamak istersen üstüne gel
ve su yazi cikiyorsa----->Syntactic sugar---> demek ki olsada olur olmasada
ama mesela--->and()---> more readable hale getiriyor
mesela -->assertThat()-->'de  Syntactic sugar olsa da olur olmasa da
 */

           given()
                .accept(ContentType.JSON)
                .and().pathParam("id", 15)
           .when()
                 .get("http://44.202.63.224:8000/api/spartans/{id}")
           .then()
                .statusCode(200)
                .and().assertThat()
                   .contentType("application/json")
                .and()
                .body("id",equalTo(15)
                ,"name",equalTo("Meta")
                ,"gender",equalTo("Female")
                ,"phone",is(1938695106));

    }





    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void teacherData(){

        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",21887)
        .when()
                .get("http://api.cybertektraining.com/teacher/{id}")
        .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Content-Length",is("275"))
                .and()
                .header("Date",notNullValue())
                .and().assertThat()
                .body("teachers[0].firstName",is("Leonel"))
                .body("teachers[0].lastName",is("Messi"))
                .body("teachers[0].gender",equalTo("Male"));

        /*
        mesela -->"Date"---> yerine "Dateee" gibi yanlis biersey  yazdin mi sana hata vereck
        ve altta konsolda olmasi gerekeni yazacak
         */

    }

    @DisplayName("GET request to teacher/all and chaining")
    @Test
    public void teachersTest(){


        //verify Andrii,Anreas,Breana inside the all teachers

        given()
                .accept(ContentType.JSON)
                .when()
                .get("http://api.cybertektraining.com/teacher/all")
                .then()
                .statusCode(200)
                .and()
                .body("teachers.firstName",hasItems("Andrii","Anreas","Breana"));
        //ayni sekilde teacher ismi olmayan birini yazincada hata vereck ve olanlari yazdiracak konsol da

    }


}
