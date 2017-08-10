package com.example.aabdelnazeer.foodrecipeproject.DataLayer.repo;

import com.example.aabdelnazeer.foodrecipeproject.CallBacks.SuccessFailureCallBack;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors.JsonProcessor;

public interface RecipeRepoClient {
	void getRecipe(String id, SuccessFailureCallBack callBack, JsonProcessor jsonProcessor);
	void getRecipes(int page,String query,SuccessFailureCallBack callBack, JsonProcessor jsonProcessor);
	void getRecipes(String query,SuccessFailureCallBack callBack, JsonProcessor jsonProcessor);
	void getRecipes(JsonProcessor jsonProcessor,SuccessFailureCallBack callBack);

}
