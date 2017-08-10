package com.example.aabdelnazeer.foodrecipeproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aabdelnazeer.foodrecipeproject.CallBacks.SuccessFailureCallBack;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.models.Recipe;
import com.example.aabdelnazeer.foodrecipeproject.ui.RecipeList.RecipeListView;
import com.example.aabdelnazeer.foodrecipeproject.ui.RecipeList.RecipeListViewImp;
import com.example.aabdelnazeer.foodrecipeproject.ui.RecipeList.RecipeListViewListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	RecipeListView recipeListView;
	RecipeListViewListener listener = createRecipeListViewListner();
	List<Recipe>recipes=new ArrayList<>();
	int currentPage=1;
	String currentQuery = null;
	private boolean moreAvailable=true;
	private SuccessFailureCallBack<List<Recipe>> getRecipesCallBack=createGetRecipeCallback();




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		recipeListView=new RecipeListViewImp(this);
		recipeListView.setRecipeListViewListner(listener);
		setContentView(recipeListView.getView());
		recipeListView.showLoadingView(true);
		RecipeApp.getInstance().getRecipeRepo().getRecipes(getRecipesCallBack);


	}
	private SuccessFailureCallBack<List<Recipe>> createGetRecipeCallback() {
		return new SuccessFailureCallBack<List<Recipe>>() {
			@Override
			public void onSuccess(List<Recipe> data) {
				recipeListView.setRecipeList(data);
				recipes.clear();
				recipes.addAll(data);
				recipeListView.showLoadingView(false);
			}

			@Override
			public void onFail(Object... data) {
				recipeListView.showLoadingView(false);
				System.out.println();
			}
		};
	}
	private RecipeListViewListener createRecipeListViewListner() {
		return new RecipeListViewListener() {
			@Override
			public void onClickRecipe(int position) {
					Recipe recipe=recipes.get(position);
				Intent intent= RecipeDetailActivity.getStartIntent(MainActivity.this,recipe);
				startActivity(intent);
			}

			@Override
			public void onSearchQuery(String query) {
				currentPage =1;
				moreAvailable=true;
				currentQuery=query;
				recipeListView.showLoadingView(true);
				RecipeApp.getInstance().getRecipeRepo().getRecipes(currentPage,query,getRecipesCallBack);

			}

			@Override
			public void loadNextPage() {
				if(!moreAvailable)return;
				recipeListView.showLoadMoreLoadingView(true);
				RecipeApp.getInstance().getRecipeRepo().getRecipes(++currentPage, currentQuery, new SuccessFailureCallBack<List<Recipe>>() {
					@Override
					public void onSuccess(List<Recipe> data) {
						recipeListView.showLoadMoreLoadingView(false);
						recipeListView.addRecipes(data);
						recipes.addAll(data);
					}

					@Override
					public void onFail(Object... data) {
						recipeListView.showLoadMoreLoadingView(false);
						moreAvailable=false;
					}
				});

			}
		};
	}
}
