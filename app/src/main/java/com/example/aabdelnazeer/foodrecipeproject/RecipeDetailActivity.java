package com.example.aabdelnazeer.foodrecipeproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aabdelnazeer.foodrecipeproject.CallBacks.SuccessFailureCallBack;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.models.Recipe;
import com.example.aabdelnazeer.foodrecipeproject.ui.recipeDetails.RecipeDetailListner;
import com.example.aabdelnazeer.foodrecipeproject.ui.recipeDetails.RecipeDetailScreen;
import com.example.aabdelnazeer.foodrecipeproject.ui.recipeDetails.RecipeDetailsScreenImp;

public class RecipeDetailActivity extends AppCompatActivity {

	private static final String EXTRA_RECIPE = "EXTRA_RECIPE";
	private Recipe recipe;
	private SuccessFailureCallBack<Recipe> callBack = createRecipeDetailCallback();
	private RecipeDetailScreen screen;
	private RecipeDetailListner recipeDetaliScreenListner = createRecipeDetailScreenListner();

	private RecipeDetailListner createRecipeDetailScreenListner() {
		return new RecipeDetailListner() {
			@Override
			public void openInstructions() {
				startActivity(ViewOnlyWebViewActivity.getStartIntent(RecipeDetailActivity.this,recipe.getTitle(),recipe.getF2f_url()));

			}

			@Override
			public void openOriginal() {
				startActivity(ViewOnlyWebViewActivity.getStartIntent(RecipeDetailActivity.this,recipe.getTitle(),recipe.getSource_url()));
			}
		};
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		screen = new RecipeDetailsScreenImp(this);
		recipe = (Recipe) getIntent().getSerializableExtra(EXTRA_RECIPE);
		screen.setRecipe(recipe);
		screen.setRecipeDetailScreenListner(recipeDetaliScreenListner);
		setContentView(screen.getView());
		screen.showIngredientsLoading(true);

		RecipeApp.getInstance().getRecipeRepo().getRecipe(recipe.getRecipe_id(),callBack);
		fetchAndDisplayRecipeBitmap(recipe.getImage_url());
	}

	private void fetchAndDisplayRecipeBitmap(String image_url) {
		RecipeApp.getInstance().getBitmapDownloader().getBitMapFromUrl(image_url, new SuccessFailureCallBack<Bitmap>() {
			@Override
			public void onSuccess(Bitmap data) {
				screen.setRecipeBitmap(data);
			}

			@Override
			public void onFail(Object... data) {

			}
		});
	}

	private SuccessFailureCallBack<Recipe> createRecipeDetailCallback() {
		return new SuccessFailureCallBack<Recipe>() {
			@Override
			public void onSuccess(Recipe data) {
				screen.showIngredientsLoading(false);
				screen.setRecipe(data);
			}

			@Override
			public void onFail(Object... data) {
				screen.showIngredientsLoading(true);


			}
		};
	}



	public static Intent getStartIntent(Context context, Recipe recipe) {
		Intent intent = new Intent(context, RecipeDetailActivity.class);
		intent.putExtra(EXTRA_RECIPE, recipe);
		return intent;
	}

}
