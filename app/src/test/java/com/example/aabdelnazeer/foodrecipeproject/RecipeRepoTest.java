package com.example.aabdelnazeer.foodrecipeproject;

import com.example.aabdelnazeer.foodrecipeproject.CallBacks.SuccessFailureCallBack;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors.JsonProcessor;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors.RecipeJsonProcessor;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors.RecipeListJsonProcessor;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.repo.RecipeRepoClient;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.repo.RecipeRepoImpl;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RecipeRepoTest {
	private boolean validJsonProcessor,paramsvalid,callbackvalid;
	private SuccessFailureCallBack callback = new SuccessFailureCallBack() {
		@Override
		public void onSuccess(Object data) {
			if(data.equals("checkCorrectDataPassed"))
				callbackvalid = true;
		}

		@Override
		public void onFail(Object... data) {

		}
	};
	@Test
	public void repoGetRecipeDetailTest(){
		resetFlags();
		final String recipeId="35382";
		RecipeRepoClient webRecipeRepoClientMock=new RecipeRepoClient() {
			@Override
			public void getRecipe(String id, SuccessFailureCallBack callBack, JsonProcessor jsonProcessor) {
				validJsonProcessor=jsonProcessor instanceof RecipeJsonProcessor;
				paramsvalid=id.equals(recipeId);
				callBack.onSuccess("checkCorrectDataPassed");
			}

			@Override
			public void getRecipes(int page, String query, SuccessFailureCallBack callBack, JsonProcessor jsonProcessor) {

			}

			@Override
			public void getRecipes(String query, SuccessFailureCallBack callBack, JsonProcessor jsonProcessor) {

			}

			@Override
			public void getRecipes(JsonProcessor jsonProcessor, SuccessFailureCallBack callBack) {

			}
		};
		RecipeRepoImpl imp=new RecipeRepoImpl(webRecipeRepoClientMock);

		imp.getRecipe(recipeId,callback);
		valiadte();


	}
	@Test
	public void repoGetRecipesTest(){
		resetFlags();
		final int targetPage=1;
		final String targetQuery="indian";

		RecipeRepoClient webRecipeRepoClientMock=new RecipeRepoClient() {
			@Override
			public void getRecipe(String id, SuccessFailureCallBack callBack, JsonProcessor jsonProcessor) {

			}

			@Override
			public void getRecipes(int page, String query, SuccessFailureCallBack callBack, JsonProcessor jsonProcessor) {
				validJsonProcessor=jsonProcessor instanceof RecipeListJsonProcessor;
				paramsvalid=page==targetPage && targetQuery.equals(query);
				callBack.onSuccess("checkCorrectDataPassed");
			}

			@Override
			public void getRecipes(String query, SuccessFailureCallBack callBack, JsonProcessor jsonProcessor) {

			}

			@Override
			public void getRecipes(JsonProcessor jsonProcessor, SuccessFailureCallBack callBack) {

			}
		};
		RecipeRepoImpl imp=new RecipeRepoImpl(webRecipeRepoClientMock);

		imp.getRecipes(targetPage,targetQuery,callback);
		valiadte();


	}
	private void valiadte() {
		assertTrue("wrong JsonProcessor ", validJsonProcessor);
		assertTrue("wrong parms", paramsvalid);
		assertTrue("callback Not called or not reciving the sent data",callbackvalid);
	}

	private void resetFlags() {
		validJsonProcessor = paramsvalid=callbackvalid =false;
	}
}
