package com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors;

import com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors.JsonProcessor;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors.JsonUtils;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors.RecipeJsonProcessor;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.models.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecipeListJsonProcessor implements JsonProcessor<List<Recipe>> {


	@Override
	public List<Recipe> process(String jsonString) throws JSONException {
		JSONObject jsonObject= new JSONObject(jsonString);
		int itemCount= JsonUtils.getInt(jsonObject,"count");

		JSONArray jsonArray =JsonUtils.getJsonArray(jsonObject,"recipes");
		List<Recipe> recipes = new ArrayList<>();
		RecipeJsonProcessor processor=new RecipeJsonProcessor();
		for (int i = 0; i < jsonArray.length(); i++) {
			recipes.add(processor.process(jsonArray.getJSONObject(i)));
		}
		return recipes;
	}


}
