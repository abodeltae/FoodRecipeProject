package com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors;

import com.example.aabdelnazeer.foodrecipeproject.DataLayer.models.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecipeJsonProcessor implements JsonProcessor<Recipe> {

	@Override
	public Recipe process(String jsonString) throws JSONException {
		JSONObject jsonObject =new JSONObject(jsonString);
		return process(jsonObject.getJSONObject("recipe"));
	}


	Recipe process(JSONObject jsonObject) throws JSONException {
		Recipe recipe=new Recipe();
		recipe.setTitle(JsonUtils.getString(jsonObject,"title"));
		recipe.setF2f_url(JsonUtils.getString(jsonObject,"f2f_url"));
		recipe.setPublisher(JsonUtils.getString(jsonObject,"publisher"));
		recipe.setSource_url(JsonUtils.getString(jsonObject,"source_url"));
		recipe.setRecipe_id(JsonUtils.getString(jsonObject,"recipe_id"));
		recipe.setSocial_rank(JsonUtils.getDouble(jsonObject,"social_rank"));
		recipe.setPublisher_url(JsonUtils.getString(jsonObject,"publisher_url"));
		recipe.setImage_url(JsonUtils.getString(jsonObject,"image_url"));
		JSONArray ingredientsJsonArray =JsonUtils.getJsonArray(jsonObject,"ingredients");
		if(ingredientsJsonArray != null){
			List<String> ingredients=new ArrayList<>();
			for (int i = 0; i < ingredientsJsonArray.length(); i++) {
				ingredients.add(ingredientsJsonArray.getString(i));
			}
			recipe.setIngredients(ingredients);
		}
		return recipe;	}
}
