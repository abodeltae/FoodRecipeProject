package com.example.aabdelnazeer.foodrecipeproject.ui.RecipeList;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aabdelnazeer.foodrecipeproject.DataLayer.models.Recipe;
import com.example.aabdelnazeer.foodrecipeproject.R;

import java.lang.ref.WeakReference;
import java.util.List;

public class RecipeListViewImp extends RelativeLayout implements RecipeListView{
	private RecipesRecyclerViewAdapter adapter;
	private WeakReference<RecipeListViewListener> recipeListViewListenerWeakReference =new WeakReference<>(null);
	private ProgressBar progressBar;
	private LoadMoreListner loadMoreListner= createLoadMoreListner();
	private AdapterView.OnItemSelectedListener adapterClickListner= createAdapterClickListner();
	ProgressBar loadingMoreProgressBar;




	public RecipeListViewImp(Context context) {
		super(context);
		init(context);
	}



	private void init(Context context){
		inflate(context, R.layout.recipe_list_screen,this);
		progressBar= (ProgressBar) findViewById(R.id.progressBar);
		loadingMoreProgressBar=(ProgressBar)findViewById(R.id.recipeListScreenLoadingMoreProgressBar);
		RecyclerView recyclerListView= (RecyclerView) findViewById(R.id.recipeListScreenRecyclerListView);
		adapter=new RecipesRecyclerViewAdapter();
		recyclerListView.setAdapter(adapter);
		recyclerListView.setLayoutManager(new LinearLayoutManager(context));
		adapter.setOnClickListner(adapterClickListner);
		adapter.setLoadMoreListner(loadMoreListner);
		EditText queryEdit= (EditText) findViewById(R.id.recipeListScreenQueryEditText);
		queryEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				RecipeListViewListener listner = recipeListViewListenerWeakReference.get();
				if(listner == null) return true;

				if (event != null) {
					// if shift key is down, then we want to insert the '\n' char in the TextView;
					// otherwise, the default action is to send the message.
					if (!event.isShiftPressed()) {
						listner.onSearchQuery(v.getText().toString());
						return true;
					}
					return false;
				}

				listner.onSearchQuery(v.getText().toString());
				return true;
			}
		});

	}
	private AdapterView.OnItemSelectedListener createAdapterClickListner() {
		return new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				RecipeListViewListener listener = recipeListViewListenerWeakReference.get();
				if(listener!= null){
					listener.onClickRecipe(position);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		};
	}
	private LoadMoreListner createLoadMoreListner() {
		return loadMoreListner = new LoadMoreListner() {
			@Override
			public void loadMore() {
				RecipeListViewListener listener = recipeListViewListenerWeakReference.get();
				if(listener!= null){
					listener.loadNextPage();
				}
			}
		};
	}

	@Override
	public void setRecipeList(List<Recipe> recipes) {
		adapter.setItemList(recipes);
	}

	@Override
	public void addRecipes(List<Recipe> recipes) {
		adapter.addItems(recipes);
	}

	@Override
	public void showLoadingView(boolean showLoading) {
		progressBar.setVisibility(showLoading?VISIBLE:INVISIBLE);
	}

	@Override
	public void showLoadMoreLoadingView(boolean showLoading) {
		loadingMoreProgressBar.setVisibility(showLoading?VISIBLE:GONE);
	}

	public void setRecipeListViewListner(final RecipeListViewListener listner) {
		recipeListViewListenerWeakReference =new WeakReference<>(listner);

	}

	@Override
	public View getView() {
		return this;
	}
}
