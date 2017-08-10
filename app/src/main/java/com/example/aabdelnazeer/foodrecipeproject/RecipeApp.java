package com.example.aabdelnazeer.foodrecipeproject;

import android.app.Application;

import com.example.aabdelnazeer.foodrecipeproject.DataLayer.BitmapDownloader.BitmapDownloader;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.BitmapDownloader.BitmapDownloaderImp;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.repo.RecipeRepo;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.repo.RecipeRepoImpl;

public class RecipeApp extends Application {
	private static RecipeApp INSTANCE;
	private RecipeRepo recipeRepo;
	private BitmapDownloader bitmapDownloader;
	@Override
	public void onCreate() {
		super.onCreate();
		INSTANCE=this;
		recipeRepo= new RecipeRepoImpl();
		bitmapDownloader = new BitmapDownloaderImp();

	}

	public static RecipeApp getInstance(){
		return INSTANCE;
	}

	public RecipeRepo getRecipeRepo(){
		return recipeRepo;
	}

	public BitmapDownloader getBitmapDownloader() {
		return bitmapDownloader;
	}
}
