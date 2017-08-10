package com.example.aabdelnazeer.foodrecipeproject.ui.recipeDetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aabdelnazeer.foodrecipeproject.R;

import java.util.ArrayList;
import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.VH> {

	private final Context context;
	private List<String>items=new ArrayList<>();

	public IngredientsAdapter(Context context){
		this.context=context;
	}
	public void addItems(List<String>items){
		this.items.addAll(items);
		notifyDataSetChanged();
	}
	@Override
	public VH onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater=LayoutInflater.from(context);
		View view =inflater.inflate(R.layout.ingredient_row,parent,false);
		return new VH(view);
	}

	@Override
	public void onBindViewHolder(VH holder, int position) {
		holder.itemTitleTv.setText(items.get(position));
	}

	@Override
	public int getItemCount() {
		return items.size();
	}
	 static class VH extends RecyclerView.ViewHolder{
			TextView itemTitleTv;
		 VH(View itemView) {
			super(itemView);
			itemTitleTv= (TextView) itemView.findViewById(R.id.ingredientRowTitleTv);
		}
	}
}
