package ch.zhaw.fswd.backend.foodbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.time.Duration;

import java.time.LocalDateTime;

import ch.zhaw.fswd.backend.foodbase.entity.*;
import com.fasterxml.jackson.datatype.jsr310.*;

import Util.FullResponseBuilder;;

@SpringBootApplication
public class FoodbaseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FoodbaseApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private IngredientRepository ingredientRepository;

	@Autowired
	private MeasureRepository measureRepository;

	@Override
	public void run(String... args) throws Exception {

		String s = FullResponseBuilder.getFullResponse("https://picsum.photos/200/300");
		System.out.println(s);
		// neues User hinzufügen
		Random rn = new Random();

		User newUser = new User();
		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName("Gianni");
		userInfo.setLastName("Rivera");
		Image im = new Image();
		im.setUrl("https://picsum.photos/id/" + rn.nextInt(1, 500) + "/200");
		userInfo.setProfilePic(im);
		newUser.setUserInfo(userInfo);
		LoginInfo login = new LoginInfo();
		login.setEmail("gianni.rivera@gmail.com");
		login.setPassword("12345");
		login.setUserName("roi7");
		newUser.setLoginInfo(login);
		newUser.setCookings(new ArrayList<Cooking>());
		newUser.setFavorites(new ArrayList<Recipe>());

		userRepository.save(newUser);

		User user = userRepository.findById(newUser.getId()).get();

		Recipe newRecipe = new Recipe();
		newRecipe.setCreatedBy(user);
		Long l = rn.nextLong(1, 2);
		Category category = categoryRepository.findById(l).get();
		newRecipe.setCategory(category);
		ArrayList<Step> steps = new ArrayList<Step>();
		int maxLoop = rn.nextInt(3, 10);
		for (int i = 0; i < maxLoop; i++) {

			Step step = new Step();
			step.setStepOrder(i + 1);
			step.setTitle("Test");
			step.setContent("ASKLJDKLASJDKLASJKLAJSDKLJASDKLJASKDJKLSA");
			ArrayList<Image> images = new ArrayList<>();
			for (int j = 0; j < 10; j++) {
				Image image = new Image();
				image.setUrl("https://picsum.photos/id/" + rn.nextInt(1, 500) + "/200");
				images.add(image);
			}
			step.setImages(images);
			steps.add(step);
		}
		newRecipe.setCookingSteps(steps);
		Duration duration = Duration.ofHours(1);
		newRecipe.setCookingTime(duration);

		newRecipe.setCreatedAt(LocalDateTime.now());
		newRecipe.setDifficulty(3);

		ArrayList<IngredientQuantity> ingredients = new ArrayList<IngredientQuantity>();
		for (int i = 0; i < 5; i++) {

			l = rn.nextLong(1, 20);
			IngredientQuantity iq = new IngredientQuantity();
			iq.setIngredient(ingredientRepository.findById(l).get());
			l = rn.nextLong(1, 5);
			iq.setMeasure(measureRepository.findById(l).get());
			iq.setQuantity((double) Math.round(rn.nextDouble(1, 50)));
			ingredients.add(iq);
		}

		newRecipe.setIngredients(ingredients);
		newRecipe.setServings(4);
		newRecipe.setThumbnailUrl(null);
		recipeRepository.save(newRecipe);

		ObjectMapper mapper = new ObjectMapper();
		String jsonUser = mapper.writeValueAsString(user);
		//String jsonRecipe = mapper.writeValueAsString(newRecipe);
		System.out.println(jsonUser);
		//System.out.println(jsonRecipe);

	}

}
