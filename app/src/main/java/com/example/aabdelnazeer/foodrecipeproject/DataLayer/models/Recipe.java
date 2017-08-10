package com.example.aabdelnazeer.foodrecipeproject.DataLayer.models;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable{
	private String publisher;
	private String f2f_url;
	private String source_url;
	private String recipe_id;
	private String image_url;
	private double social_rank;
	private String publisher_url;
	private String title;
	private List<String> ingredients;

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getF2f_url() {
		return f2f_url;
	}

	public void setF2f_url(String f2f_url) {
		this.f2f_url = f2f_url;
	}

	public String getSource_url() {
		return source_url;
	}

	public void setSource_url(String source_url) {
		this.source_url = source_url;
	}

	public String getRecipe_id() {
		return recipe_id;
	}

	public void setRecipe_id(String recipe_id) {
		this.recipe_id = recipe_id;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public double getSocial_rank() {
		return social_rank;
	}

	public void setSocial_rank(double social_rank) {
		this.social_rank = social_rank;
	}

	public String getPublisher_url() {
		return publisher_url;
	}

	public void setPublisher_url(String publisher_url) {
		this.publisher_url = publisher_url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Recipe recipe = (Recipe) o;

		if (Double.compare(recipe.social_rank, social_rank) != 0) return false;
		if (publisher != null ? !publisher.equals(recipe.publisher) : recipe.publisher != null)
			return false;
		if (f2f_url != null ? !f2f_url.equals(recipe.f2f_url) : recipe.f2f_url != null)
			return false;
		if (source_url != null ? !source_url.equals(recipe.source_url) : recipe.source_url != null)
			return false;
		if (recipe_id != null ? !recipe_id.equals(recipe.recipe_id) : recipe.recipe_id != null)
			return false;
		if (image_url != null ? !image_url.equals(recipe.image_url) : recipe.image_url != null)
			return false;
		if (publisher_url != null ? !publisher_url.equals(recipe.publisher_url) : recipe.publisher_url != null)
			return false;
		if (title != null ? !title.equals(recipe.title) : recipe.title != null) return false;
		return ingredients != null ? ingredients.equals(recipe.ingredients) : recipe.ingredients == null;

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = publisher != null ? publisher.hashCode() : 0;
		result = 31 * result + (f2f_url != null ? f2f_url.hashCode() : 0);
		result = 31 * result + (source_url != null ? source_url.hashCode() : 0);
		result = 31 * result + (recipe_id != null ? recipe_id.hashCode() : 0);
		result = 31 * result + (image_url != null ? image_url.hashCode() : 0);
		temp = Double.doubleToLongBits(social_rank);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (publisher_url != null ? publisher_url.hashCode() : 0);
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
		return result;
	}
}
