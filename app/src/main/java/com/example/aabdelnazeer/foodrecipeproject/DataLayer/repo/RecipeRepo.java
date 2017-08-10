package com.example.aabdelnazeer.foodrecipeproject.DataLayer.repo;

import com.example.aabdelnazeer.foodrecipeproject.CallBacks.SuccessFailureCallBack;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.models.Recipe;

import java.util.List;

public interface RecipeRepo {
	void getRecipe(String id, SuccessFailureCallBack<Recipe> callBack);

	void getRecipes(int page, String query, SuccessFailureCallBack<List<Recipe>> callBack);

	void getRecipes(String query, SuccessFailureCallBack<List<Recipe>> callBack);

	void  getRecipes(SuccessFailureCallBack<List<Recipe>> callBack);
}
