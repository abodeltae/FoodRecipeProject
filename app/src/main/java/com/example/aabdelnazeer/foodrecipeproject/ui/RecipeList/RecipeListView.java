package com.example.aabdelnazeer.foodrecipeproject.ui.RecipeList;

import android.view.View;

import com.example.aabdelnazeer.foodrecipeproject.DataLayer.models.Recipe;

import java.util.List;

public interface RecipeListView {
	void setRecipeList(List<Recipe> recipes);
	void addRecipes(List<Recipe>recipes);
	void showLoadingView(boolean showLoading);
	void showLoadMoreLoadingView(boolean showLoading);
	void setRecipeListViewListner(RecipeListViewListener listner);
	View getView();
}
