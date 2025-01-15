package com.psp.jokeapi;

import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;  

/**
 *
 * @author Alejandro
 */
public class JokeAPI {

    public static void main(String[] args) {
        String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
//        Student student = gson.fromJson(jsonString, Student.class);

    }
}
