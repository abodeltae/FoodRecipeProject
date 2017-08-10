package com.example.aabdelnazeer.foodrecipeproject.ui.recipeDetails;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aabdelnazeer.foodrecipeproject.DataLayer.models.Recipe;
import com.example.aabdelnazeer.foodrecipeproject.R;

import java.lang.ref.WeakReference;

public class RecipeDetailsScreenImp extends RelativeLayout implements RecipeDetailScreen {
	private WeakReference<RecipeDetailListner> recipeListnerWeakRefrence = new WeakReference<>(null);
	private TextView titleTv;
	private TextView publisherTv;
	private TextView rankTv;
	private ImageView recipeIv;
	private ProgressBar ingredientsProgressBar;
	private IngredientsAdapter ingredientsAdapter;

	public RecipeDetailsScreenImp(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		inflate(context, R.layout.recipe_detail_screen, this);
		titleTv = (TextView) findViewById(R.id.recipeDetailScreenTitleTv);
		publisherTv = (TextView) findViewById(R.id.recipeDetailScreenSPublisherTv);
		rankTv = (TextView) findViewById(R.id.recipeDetailScreenRankTv);
		recipeIv = (ImageView) findViewById(R.id.recipeDetailScreenRecipeImageView);

		RecyclerView ingredientsRecyclerView = (RecyclerView) findViewById(R.id.recipeDetailScreenIngredientsRecyclerView);
		ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		ingredientsAdapter=new IngredientsAdapter(getContext());
		ingredientsRecyclerView.setAdapter(ingredientsAdapter);
		ingredientsProgressBar = (ProgressBar) findViewById(R.id.recipeDetailScreenIngredientsProgressBar);
		TextView openInstructions = (TextView) findViewById(R.id.recipeDetailScreenViewInstructionsTv);
		TextView openOriginal = (TextView) findViewById(R.id.recipeDetailScreenShowOriginalTv);
		openInstructions.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RecipeDetailListner listner = recipeListnerWeakRefrence.get();
				if (listner != null) {
					listner.openInstructions();
				}
			}
		});

		openOriginal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RecipeDetailListner listner = recipeListnerWeakRefrence.get();
				if (listner != null) {
					listner.openOriginal();
				}
			}
		});

	}

	@Override
	public void showIngredientsLoading(boolean showLoading) {
		ingredientsProgressBar.setVisibility(showLoading ? VISIBLE : GONE);
	}

	@Override
	public void setRecipe(Recipe recipe) {
		titleTv.setText(recipe.getTitle());
		publisherTv.setText(recipe.getPublisher());
		String rankFormat = getContext().getString(R.string.rankFormat);
		String rankText = String.format(rankFormat, recipe.getSocial_rank() );
		rankTv.setText(rankText);
		if(recipe.getIngredients()!=null){
			ingredientsAdapter.addItems(recipe.getIngredients());
		}

	}

	@Override
	public void setRecipeBitmap(Bitmap bitmap) {
		recipeIv.setScaleType(ImageView.ScaleType.FIT_XY);
		recipeIv.setImageBitmap(bitmap);
	}


	@Override
	public void setRecipeDetailScreenListner(RecipeDetailListner listner) {
		recipeListnerWeakRefrence = new WeakReference<>(listner);
	}

	@Override
	public View getView() {
		return this;
	}


}
