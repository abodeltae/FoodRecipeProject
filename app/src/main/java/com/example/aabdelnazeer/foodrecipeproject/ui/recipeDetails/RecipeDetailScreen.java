package com.example.aabdelnazeer.foodrecipeproject.ui.recipeDetails;

import android.graphics.Bitmap;
import android.view.View;

import com.example.aabdelnazeer.foodrecipeproject.DataLayer.models.Recipe;

public interface RecipeDetailScreen  {
	void showIngredientsLoading(boolean showLoading);
	void setRecipe(Recipe recipe);
	void setRecipeBitmap(Bitmap bitmap);
	void setRecipeDetailScreenListner(RecipeDetailListner listner);
	View getView();
}
