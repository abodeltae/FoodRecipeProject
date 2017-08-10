package com.example.aabdelnazeer.foodrecipeproject.ui.RecipeList;

public interface RecipeListViewListener {
	void onClickRecipe(int  position);
	void onSearchQuery(String query);
	void loadNextPage();
}
