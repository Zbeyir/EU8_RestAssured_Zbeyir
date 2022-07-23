package com.cybertek.day5;

import org.hamcrest.MatcherAssert;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat; //1-) numaradan geldi
import static org.hamcrest.Matchers.*;   //2-) numaradan geldi


public class HamcrestMatchersIntro {

    @Test
    public void test1(){

       // MatcherAssert.assertThat();  //1-)--> normal de böyle idi biz direkt yukari gönderdik kisa yollla
        assertThat(5+5, is(10));  //2-)

        assertThat(5+5,equalTo(10));
        //matchers has 2 overloaded version
        //first that accept actual value
        //second taht accept another matchers
        //below examples is method is accepting another matchers equal to make it readable
        assertThat(5+5,is(equalTo(10)));

        //======= bu 3 e de ayni kapiya cikar===>yukaridaki ve assigdaki

        assertThat(5+5,not(9));
        assertThat(5+5,is(not(9)));
        assertThat(5+5,is(not(equalTo(9))));

        //number comparison====> bu altta kilerin hepsi method() yukaridaki gibi ihtiyaca binayen kullaniyoruz
        //greaterThan()
        //greaterThanOrEqualTo()
        //lessThan()
        //lessThanOrEqualTo()
        assertThat(5+5,is(greaterThan(9)));

    }


    @DisplayName("Assertion with String")
    @Test
    public void stringHamcrest(){

        String text = "EU8 is learning Hamcrest";

        //checking for euqality is same as numbers
        assertThat(text,is("EU8 is learning Hamcrest"));
        assertThat(text,equalTo("EU8 is learning Hamcrest"));
        assertThat(text,is(equalTo("EU8 is learning Hamcrest")));

        //check if this text starts with EU8
        assertThat(text,startsWith("EU8") );

        //now do it in case insensitive manner
        assertThat(text,startsWithIgnoringCase( "eu8"));

        //endsWith()
        assertThat(text,endsWith("rest"));

        //check if text contains String learning
        assertThat(text,containsString("learning"));  //!!! dikkat sadece contains yazarsan hata verir
        assertThat(text,containsStringIgnoringCase("LEARNING"));


        String str ="  ";

        //check if above str is blank
        assertThat(str,blankString());

        //check if trimmed str is empty string
        assertThat(str.trim(),emptyString());



    }


}
