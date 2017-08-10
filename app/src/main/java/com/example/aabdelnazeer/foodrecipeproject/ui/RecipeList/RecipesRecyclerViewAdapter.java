package com.example.aabdelnazeer.foodrecipeproject.ui.RecipeList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aabdelnazeer.foodrecipeproject.DataLayer.models.Recipe;
import com.example.aabdelnazeer.foodrecipeproject.R;
import com.example.aabdelnazeer.foodrecipeproject.RecipeApp;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class RecipesRecyclerViewAdapter extends RecyclerView.Adapter<RecipesRecyclerViewAdapter.VH> {
	WeakReference<AdapterView.OnItemSelectedListener> listnerWeakRefrecne=new WeakReference<AdapterView.OnItemSelectedListener>(null);
	WeakReference<LoadMoreListner>loadMoreListnerWeakReference=new WeakReference<LoadMoreListner>(null);
	List<Recipe> itemList = new ArrayList<>();

	public void addItems(List<Recipe> items) {
		int firstNewPosition = itemList.size();
		itemList.addAll(items);
		notifyItemRangeInserted(firstNewPosition, items.size());
	}

	public void setItemList(List<Recipe> items) {
		itemList.clear();
		itemList.addAll(items);
		notifyDataSetChanged();
	}

	@Override
	public VH onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(RecipeApp.getInstance());
		View view = inflater.inflate(R.layout.recipe_row, parent, false);
		return new VH(view);
	}

	@Override
	public void onBindViewHolder(final VH holder, int position) {
		holder.view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AdapterView.OnItemSelectedListener listner = listnerWeakRefrecne.get();
				if(listner != null){
					listner.onItemSelected(null,v,holder.getAdapterPosition(),-1);
				}
			}
		});
		holder.recipeTitleTv.setText(itemList.get(position).getTitle());
		LoadMoreListner loadmoreLisnter = loadMoreListnerWeakReference.get();
		if(position == itemList.size()-1&&loadmoreLisnter != null){
			loadmoreLisnter.loadMore();
		}

	}

	public void setOnClickListner(AdapterView.OnItemSelectedListener listner) {
		listnerWeakRefrecne = new WeakReference<AdapterView.OnItemSelectedListener>(listner);

	}
	public void setLoadMoreListner(LoadMoreListner listner){
		loadMoreListnerWeakReference=new WeakReference<LoadMoreListner>(listner);
	}

	@Override
	public int getItemCount() {
		return itemList.size();
	}

	static class VH extends RecyclerView.ViewHolder {
		View view;
		ImageView recipeIconIv;
		TextView recipeTitleTv;

		public VH(View itemView) {
			super(itemView);
			this.view=itemView;
			recipeIconIv = (ImageView) itemView.findViewById(R.id.recipeRowIconIv);
			recipeTitleTv = (TextView) itemView.findViewById(R.id.recipeRowTitleTv);
		}
	}
}
