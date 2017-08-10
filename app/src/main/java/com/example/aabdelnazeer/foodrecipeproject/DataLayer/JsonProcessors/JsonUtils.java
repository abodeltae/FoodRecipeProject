package com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

 class JsonUtils {

	 static String getString(JSONObject jsonObject,String key) throws JSONException {
		if(jsonObject.has(key)){
			return jsonObject.getString(key);
		}
		return null;
	}
	 static int getInt(JSONObject jsonObject,String key) throws JSONException {
		if(jsonObject.has(key)){
			return jsonObject.getInt(key);
		}
		return 0;
	}
	 static double getDouble(JSONObject jsonObject,String key )throws JSONException{
		if(jsonObject.has(key)){
			return jsonObject.getDouble(key);
		}
		return 0;
	}
	 static JSONArray getJsonArray(JSONObject jsonObject,String key)throws JSONException{
		if(jsonObject.has(key)){
			return jsonObject.getJSONArray(key);
		}
		return null;
	}
}
