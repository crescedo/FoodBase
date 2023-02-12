package Util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ch.zhaw.fswd.backend.foodbase.entity.Category;
import ch.zhaw.fswd.backend.foodbase.entity.Cooking;
import ch.zhaw.fswd.backend.foodbase.entity.Image;
import ch.zhaw.fswd.backend.foodbase.entity.IngredientQuantity;
import ch.zhaw.fswd.backend.foodbase.entity.LoginInfo;
import ch.zhaw.fswd.backend.foodbase.entity.Recipe;
import ch.zhaw.fswd.backend.foodbase.entity.Role;
import ch.zhaw.fswd.backend.foodbase.entity.RoleRepository;
import ch.zhaw.fswd.backend.foodbase.entity.Step;
import ch.zhaw.fswd.backend.foodbase.entity.Ingredient;
import ch.zhaw.fswd.backend.foodbase.entity.Measure;
import ch.zhaw.fswd.backend.foodbase.entity.User;
import ch.zhaw.fswd.backend.foodbase.entity.UserInfo;

public class Builder {
	public static User createUser(String fName, String lName, String uName, String uPass, Random rn,RoleRepository roleRepository) {
		User user = new User();
		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName(fName);
		userInfo.setLastName(lName);
		Image im = new Image();
		im.setUrl("https://source.unsplash.com/random/200x200?sig=" + rn.nextInt(10) + 1);
		userInfo.setProfilePic(im);
		user.setUserInfo(userInfo);
		LoginInfo login = new LoginInfo();
		login.setLoginName(uName);
		login.setPasswordHash(new BCryptPasswordEncoder().encode(uPass));
		Role r = new Role();
		r.setRoleName("ROLE_USER");
		try {
			roleRepository.save(r);
		} catch (Exception e) {
		}
		login.getRoles().add(r);

		user.setLoginInfo(login);
		user.setCookings(new ArrayList<Cooking>());
		user.setFavorites(new ArrayList<Recipe>());
		return user;
	}

	public static Recipe createRecipe(User creator, Category category, List<Step> cookingSteps,List<IngredientQuantity> ingredients, String title, String descriptionShort, int servings,int difficulty,Image thumbnail,Duration duration) {
				
		Recipe recipe = new Recipe();

		recipe.setCreator(creator);
		recipe.setCategory(category);
		recipe.setCookingSteps(cookingSteps);
		recipe.setIngredients(ingredients);
		recipe.setTitle(title);
		recipe.setDescriptionShort(descriptionShort);
		recipe.setServings(servings);
		recipe.setThumbnailUrl(thumbnail);	
		recipe.setCookingTime(duration);
		recipe.setIsoString(recipe.getCookingTime().toString());
		recipe.setCreatedAt(LocalDateTime.now());
		recipe.setDifficulty(difficulty);

		return recipe;
	}
	public static Step createStep(String title, String content, List<Image> images,int order) {
		Step step = new Step();
		step.setTitle(title.toUpperCase());
		step.setContent(content);
		step.setImages(images);
		step.setStepOrder(order);
		return step;
	}
	
	public static Image createImage(String url){
		Image image = new Image();
		image.setUrl(url);
		return image;
	}
	public static IngredientQuantity createIngredientQuantitiy(Ingredient ingredient,Measure measure, Double quantity){
		IngredientQuantity iq = new IngredientQuantity();
		iq.setIngredient(ingredient);
		iq.setMeasure(measure);
		iq.setQuantity(quantity);
		return iq;
	}
}
