package com.example.aabdelnazeer.foodrecipeproject.DataLayer.repo;

import com.example.aabdelnazeer.foodrecipeproject.CallBacks.SuccessFailureCallBack;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.httpClient.AsyncAuthenticatedHttpClient;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.httpClient.AsyncAuthenticatedHttpClientImp;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.DataLayerConstants;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors.JsonProcessor;

import java.util.HashMap;
import java.util.Map;

public class WebRecipeRepoClient implements RecipeRepoClient {

	private final AsyncAuthenticatedHttpClient httpclient;

	WebRecipeRepoClient(){
		httpclient = new AsyncAuthenticatedHttpClientImp(DataLayerConstants.getApiKey());
	}
	public WebRecipeRepoClient(AsyncAuthenticatedHttpClient httpclient){
		this.httpclient=httpclient;
	}


	 @Override
	 public void getRecipe(String id, SuccessFailureCallBack callBack, JsonProcessor jsonProcessor) {
		 Map<String,Object> params=new HashMap<>();
		 params.put("rId",id);
		 httpclient.asyncCallAPiLink(DataLayerConstants.getRecipeGetUrl(),params,callBack,jsonProcessor);
	 }

	 @Override
	 public void getRecipes(int page, String query, SuccessFailureCallBack callBack, JsonProcessor jsonProcessor) {
		 Map<String,Object> params=new HashMap<>();
		 params.put("page",page);
		 params.put("q",query);
		 httpclient.asyncCallAPiLink(DataLayerConstants.getSearchUrl(),params,callBack,jsonProcessor);
	 }

	 @Override
	 public void getRecipes(String query, SuccessFailureCallBack callBack, JsonProcessor jsonProcessor) {
		getRecipes(1,query,callBack,jsonProcessor);
	 }

	 @Override
	 public void getRecipes(JsonProcessor jsonProcessor, SuccessFailureCallBack callBack) {
		 getRecipes(null,callBack,jsonProcessor);
	 }
 }
