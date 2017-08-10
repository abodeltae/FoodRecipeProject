package com.example.aabdelnazeer.foodrecipeproject.DataLayer.repo;

import com.example.aabdelnazeer.foodrecipeproject.CallBacks.SuccessFailureCallBack;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors.RecipeJsonProcessor;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors.RecipeListJsonProcessor;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.models.Recipe;

import java.util.List;

public class RecipeRepoImpl implements RecipeRepo {

	private  RecipeRepoClient webRecipeRepoClient;

	//ToDo implement cache Client if possible
	public  RecipeRepoImpl() {
		webRecipeRepoClient = new WebRecipeRepoClient();
	}
	public RecipeRepoImpl(RecipeRepoClient client){
		webRecipeRepoClient=client;
	}

	@Override
	public void getRecipe(String id, SuccessFailureCallBack<Recipe> callBack) {
		webRecipeRepoClient.getRecipe(id,callBack,new RecipeJsonProcessor());
	}


	@Override
	public void getRecipes(int page, String query, SuccessFailureCallBack<List<Recipe>> callBack) {
		webRecipeRepoClient.getRecipes(page,query,callBack,new RecipeListJsonProcessor());
	}

	@Override
	public void getRecipes(String query, SuccessFailureCallBack<List<Recipe>> callBack) {
		getRecipes(1,query,callBack);
	}

	@Override
	public void getRecipes(SuccessFailureCallBack<List<Recipe>> callBack) {
		getRecipes(null,callBack);
	}
}