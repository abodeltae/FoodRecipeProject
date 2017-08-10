package com.example.aabdelnazeer.foodrecipeproject.DataLayer;

 public class DataLayerConstants {
	private static final String API_KEY="37cc5a2b2aaad164ba7e88dee3fde4f0";
	private static final String SEARCH_URL="http://food2fork.com/api/search";
	private static final String RECIPE_GET_URL="http://food2fork.com/api/get";

	 public static String getApiKey() {
		 return API_KEY;
	 }

	 public static String getSearchUrl() {
		 return SEARCH_URL;
	 }

	 public static String getRecipeGetUrl() {
		 return RECIPE_GET_URL;
	 }
 }
