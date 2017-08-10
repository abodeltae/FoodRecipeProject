package com.example.aabdelnazeer.foodrecipeproject;

import com.example.aabdelnazeer.foodrecipeproject.DataLayer.models.Recipe;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors.RecipeJsonProcessor;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors.RecipeListJsonProcessor;

import org.json.JSONException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RecipeParsingTests {
	@Test
	public void recipeJsonParseTest() throws Exception {
		String jsonString = "{\"recipe\":{ " +
				"\"publisher\": \"Closet Cooking\"," +
				" \"f2f_url\": \"http://food2fork.com/view/35382\"," +
				" \"ingredients\": [ \"2 jalapeno peppers, cut in half lengthwise and seeded\", " +
				"\"2 slices sour dough bread\", \"1 tablespoon butter, room temperature\"," +
				" \"2 tablespoons cream cheese, room temperature\", " +
				"\"1/2 cup jack and cheddar cheese, shredded\"," +
				" \"1 tablespoon tortilla chips, crumbled\" ]," +
				" \"source_url\": \"http://www.closetcooking.com/2011/04/jalapeno-popper-grilled-cheese-sandwich.html\", " +
				"\"recipe_id\": \"35382\"," +
				" \"image_url\": \"http://static.food2fork.com/Jalapeno2BPopper2BGrilled2BCheese2BSandwich2B12B500fd186186.jpg\"," +
				" \"social_rank\": 100.0," +
				" \"publisher_url\": \"http://closetcooking.com\", " +
				"\"title\": \"Jalapeno Popper Grilled Cheese Sandwich\" }}";
		Recipe target_recipe = new Recipe();
		target_recipe.setPublisher("Closet Cooking");
		target_recipe.setF2f_url("http://food2fork.com/view/35382");
		target_recipe.setSource_url("http://www.closetcooking.com/2011/04/jalapeno-popper-grilled-cheese-sandwich.html");
		target_recipe.setRecipe_id("35382");
		target_recipe.setImage_url("http://static.food2fork.com/Jalapeno2BPopper2BGrilled2BCheese2BSandwich2B12B500fd186186.jpg");
		target_recipe.setSocial_rank(100.0);
		target_recipe.setPublisher_url("http://closetcooking.com");
		target_recipe.setTitle("Jalapeno Popper Grilled Cheese Sandwich");
		List<String>ingridients= Arrays.asList("2 jalapeno peppers, cut in half lengthwise and seeded", "2 slices sour dough bread", "1 tablespoon butter, room temperature", "2 tablespoons cream cheese, room temperature", "1/2 cup jack and cheddar cheese, shredded", "1 tablespoon tortilla chips, crumbled");
		target_recipe.setIngredients(ingridients);
		boolean equals=target_recipe.equals(new RecipeJsonProcessor().process(jsonString));
		assertTrue("parsed json recipe doesnt match expected recipe object",equals);
	}
	@Test
	public void recipeNonMatchingJsonTest() throws Exception {
		// publisher name not matching
		String jsonString = "{\"recipe\":{ " +
				"\"publisher\": \"WrongPublisherNAMME\"," +
				" \"f2f_url\": \"http://food2fork.com/view/35382\"," +
				" \"ingredients\": [ \"2 jalapeno peppers, cut in half lengthwise and seeded\", " +
				"\"2 slices sour dough bread\", \"1 tablespoon butter, room temperature\"," +
				" \"2 tablespoons cream cheese, room temperature\", " +
				"\"1/2 cup jack and cheddar cheese, shredded\"," +
				" \"1 tablespoon tortilla chips, crumbled\" ]," +
				" \"source_url\": \"http://www.closetcooking.com/2011/04/jalapeno-popper-grilled-cheese-sandwich.html\", " +
				"\"recipe_id\": \"35382\"," +
				" \"image_url\": \"http://static.food2fork.com/Jalapeno2BPopper2BGrilled2BCheese2BSandwich2B12B500fd186186.jpg\"," +
				" \"social_rank\": 100.0," +
				" \"publisher_url\": \"http://closetcooking.com\", " +
				"\"title\": \"Jalapeno Popper Grilled Cheese Sandwich\" }}";
		Recipe target_recipe = new Recipe();
		target_recipe.setPublisher("Closet Cooking");
		target_recipe.setF2f_url("http://food2fork.com/view/35382");
		target_recipe.setSource_url("http://www.closetcooking.com/2011/04/jalapeno-popper-grilled-cheese-sandwich.html");
		target_recipe.setRecipe_id("35382");
		target_recipe.setImage_url("http://static.food2fork.com/Jalapeno2BPopper2BGrilled2BCheese2BSandwich2B12B500fd186186.jpg");
		target_recipe.setSocial_rank(100.0);
		target_recipe.setPublisher_url("http://closetcooking.com");
		target_recipe.setTitle("Jalapeno Popper Grilled Cheese Sandwich");
		List<String>ingridients= Arrays.asList("2 jalapeno peppers, cut in half lengthwise and seeded", "2 slices sour dough bread", "1 tablespoon butter, room temperature", "2 tablespoons cream cheese, room temperature", "1/2 cup jack and cheddar cheese, shredded", "1 tablespoon tortilla chips, crumbled");
		target_recipe.setIngredients(ingridients);
		boolean equals=target_recipe.equals(new RecipeJsonProcessor().process(jsonString));
		assertFalse("parsing test malformed and returning true when expecting false",equals);
	}
	@Test
	public void recipeJsonArrayParseTest() throws JSONException {
		String jsonString = "{\"count\":2,\"recipes\":[" +
				"{ " +
				"\"publisher\": \"Closet Cooking\"," +
				" \"f2f_url\": \"http://food2fork.com/view/35382\"," +
				" \"source_url\": \"http://www.closetcooking.com/2011/04/jalapeno-popper-grilled-cheese-sandwich.html\", " +
				"\"recipe_id\": \"35382\"," +
				" \"image_url\": \"http://static.food2fork.com/Jalapeno2BPopper2BGrilled2BCheese2BSandwich2B12B500fd186186.jpg\"," +
				" \"social_rank\": 100.0," +
				" \"publisher_url\": \"http://closetcooking.com\", " +
				"\"title\": \"Jalapeno Popper Grilled Cheese Sandwich\" }," +
				"{ " +
				"\"publisher\": \"Closet Cooking\"," +
				" \"f2f_url\": \"http://food2fork.com/view/35382\"," +
				" \"source_url\": \"http://www.closetcooking.com/2011/04/jalapeno-popper-grilled-cheese-sandwich.html\", " +
				"\"recipe_id\": \"35382\"," +
				" \"image_url\": \"http://static.food2fork.com/Jalapeno2BPopper2BGrilled2BCheese2BSandwich2B12B500fd186186.jpg\"," +
				" \"social_rank\": 100.0," +
				" \"publisher_url\": \"http://closetcooking.com\", " +
				"\"title\": \"Jalapeno Popper Grilled Cheese Sandwich\" }" +
				"]}";
		Recipe target_recipe = new Recipe();
		target_recipe.setPublisher("Closet Cooking");
		target_recipe.setF2f_url("http://food2fork.com/view/35382");
		target_recipe.setSource_url("http://www.closetcooking.com/2011/04/jalapeno-popper-grilled-cheese-sandwich.html");
		target_recipe.setRecipe_id("35382");
		target_recipe.setImage_url("http://static.food2fork.com/Jalapeno2BPopper2BGrilled2BCheese2BSandwich2B12B500fd186186.jpg");
		target_recipe.setSocial_rank(100.0);
		target_recipe.setPublisher_url("http://closetcooking.com");
		target_recipe.setTitle("Jalapeno Popper Grilled Cheese Sandwich");
		List<Recipe> recipes=new ArrayList<>();
		recipes.add(target_recipe);
		recipes.add(target_recipe);
		boolean equals=recipes.equals(new RecipeListJsonProcessor().process(jsonString));
		assertTrue("parsed json recipe doesnt match expected recipe object",equals);
	}
}