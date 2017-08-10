package com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors;

import org.json.JSONException;

public  interface JsonProcessor <T> {

	T process(String jsonString) throws JSONException;


}
