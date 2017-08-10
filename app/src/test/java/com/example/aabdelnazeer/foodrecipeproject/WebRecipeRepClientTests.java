package com.example.aabdelnazeer.foodrecipeproject;

import org.junit.Test;

import com.example.aabdelnazeer.foodrecipeproject.CallBacks.SuccessFailureCallBack;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.httpClient.AsyncAuthenticatedHttpClient;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.DataLayerConstants;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors.JsonProcessor;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.models.Recipe;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors.RecipeJsonProcessor;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors.RecipeListJsonProcessor;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.repo.WebRecipeRepoClient;

import java.util.Map;

import static org.junit.Assert.assertTrue;


public class WebRecipeRepClientTests {
	private boolean linkvalid,paramsvalid,callbackvalid;

	private Recipe recipe =new Recipe();





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
	public void getRecipeTest(){ // check wiring of layers is correct
		resetFlags();
		final String recipeId="35382";
		AsyncAuthenticatedHttpClient mockHttpClientforGetRecipeDetailCall=new AsyncAuthenticatedHttpClient() {
			@Override
			public void asyncCallAPiLink(String link, Map<String, Object> params, SuccessFailureCallBack callBack, JsonProcessor processor) {
				linkvalid = link.equals(DataLayerConstants.getRecipeGetUrl());
				paramsvalid= params.containsKey("rId")&&params.get("rId").equals(recipeId);
				callBack.onSuccess("checkCorrectDataPassed");
			}
		};
		WebRecipeRepoClient client=new WebRecipeRepoClient(mockHttpClientforGetRecipeDetailCall);
		client.getRecipe(recipeId,callback,new RecipeJsonProcessor());
		valiadte();
	}

	@Test
	public void getRecipesTest(){ // check wiring of layers is correct
		resetFlags();
		 AsyncAuthenticatedHttpClient mockHttpClientforGetRecipesCall = new AsyncAuthenticatedHttpClient() {
			@Override
			public void asyncCallAPiLink(String link, Map<String, Object> params, SuccessFailureCallBack callBack, JsonProcessor processor) {
				linkvalid = link.equals(DataLayerConstants.getSearchUrl());
				paramsvalid= params.containsKey("page")&&params.get("page").equals(1)&&params.containsKey("q")&&params.get("q").equals("indian");
				recipe.setPublisher("publisher");
				callBack.onSuccess("checkCorrectDataPassed");


			}
		};
		WebRecipeRepoClient client=new WebRecipeRepoClient(mockHttpClientforGetRecipesCall);
		client.getRecipes(1,"indian",callback,new RecipeListJsonProcessor());
		valiadte();
		resetFlags();

	}

	//todo test the rest of overloaded shortcut methods

	private void valiadte() {
		assertTrue("wrong url ",linkvalid);
		assertTrue("wrong parms", paramsvalid);
		assertTrue("callback Not called or not reciving the sent data",callbackvalid);
	}

	private void resetFlags() {
		linkvalid= paramsvalid=callbackvalid =false;
	}
}
