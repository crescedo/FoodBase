package ch.zhaw.fswd.backend.foodbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import org.hibernate.cfg.Environment;
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
		
		String[] words = {
				"lorem", "ipsum", "dolor", "sit", "amet,", "consectetur", "adipiscing", "elit",
				"sed", "do", "eiusmod", "tempor", "incididunt", "ut", "labore", "et", "dolore", "magna",
				"aliqua."
		};
		
		
  
		
		String s = FullResponseBuilder.getFullResponse("https://picsum.photos/200/300");
		System.out.println(s);
		// neues User hinzufügen
		Random random = new Random();

		User newUser = new User();
		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName("Gianni");
		userInfo.setLastName("Rivera");
		Image im = new Image();
		im.setUrl("https://picsum.photos/id/" + random.nextInt(1, 500) + "/200");
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
		for (int f = 0; f < 20; f++) {

			Recipe newRecipe = new Recipe();
			newRecipe.setCreatedBy(user);
			Long l = random.nextLong(1, 2);
			Category category = categoryRepository.findById(l).get();
			newRecipe.setCategory(category);
			ArrayList<Step> steps = new ArrayList<Step>();
			int maxLoop = random.nextInt(3, 10);
			
			StringBuilder title=new StringBuilder();
			StringBuilder content=new StringBuilder();
			for (int i = 0; i < maxLoop; i++) {
				
				int numberOfParagraphs = 3;
				int numberOfSentences = 5;
				
				for (int a = 0; a < numberOfParagraphs; a++) {
					
					title.append(words[random.nextInt(words.length)] + " ");
					
					for (int b = 0; b < numberOfSentences; b++) {
					   int wordCount = random.nextInt(7) + 3;
		   
					   for (int c = 0; c < wordCount; c++) {
						  content.append(words[random.nextInt(words.length)] + " ");
					   }
		   
					   content.append("\n");
					}
		   
					content.append("\n");
				 }
				Step step = new Step();
				step.setStepOrder(i + 1);
				step.setTitle(title.toString().toUpperCase());
				step.setContent(content.toString());
				ArrayList<Image> images = new ArrayList<>();
				for (int j = 0; j < 10; j++) {
					Image image = new Image();
					image.setUrl("https://picsum.photos/id/" + random.nextInt(1, 500) + "/200");
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

				l = random.nextLong(1, 20);
				IngredientQuantity iq = new IngredientQuantity();
				iq.setIngredient(ingredientRepository.findById(l).get());
				l = random.nextLong(1, 5);
				iq.setMeasure(measureRepository.findById(l).get());
				iq.setQuantity((double) Math.round(random.nextDouble(1, 50)));
				ingredients.add(iq);
			}

			newRecipe.setIngredients(ingredients);
			newRecipe.setServings(4);
			newRecipe.setThumbnailUrl(null);
			recipeRepository.save(newRecipe);
		}

		
		Recipe newRecipeJess = new Recipe();
		
		newRecipeJess.setCreatedBy(user);
		
		Long l = random.nextLong(1, 2);
		
		Category category = categoryRepository.findById(l).get();
		
		newRecipeJess.setCategory(category);
		
		ArrayList<Step> steps = new ArrayList<Step>();
		
		int maxLoop = random.nextInt(3, 10);
		
		StringBuilder title=new StringBuilder();
		StringBuilder content=new StringBuilder();
		for (int i = 0; i < maxLoop; i++) {
			
			int numberOfParagraphs = 3;
			int numberOfSentences = 5;
			
			for (int a = 0; a < numberOfParagraphs; a++) {
				
				title.append(words[random.nextInt(words.length)] + " ");
				
				for (int b = 0; b < numberOfSentences; b++) {
				   int wordCount = random.nextInt(7) + 3;
	   
				   for (int c = 0; c < wordCount; c++) {
					  content.append(words[random.nextInt(words.length)] + " ");
				   }
	   
				   content.append("\n");
				}
	   
				content.append("\n");
			 }

			Step step = new Step();

			step.setStepOrder(i + 1);
			
			step.setTitle(title.toString().toUpperCase());
			step.setContent(content.toString());
			ArrayList<Image> images = new ArrayList<>();
			for (int j = 0; j < 10; j++) {
				Image image = new Image();
				image.setUrl("https://picsum.photos/id/" + random.nextInt(1, 500) + "/200");
				images.add(image);
			}
			step.setImages(images);
			steps.add(step);
		}
		newRecipeJess.setCookingSteps(steps);

		Duration duration = Duration.ofHours(1);

		newRecipeJess.setCookingTime(duration);

		newRecipeJess.setCreatedAt(LocalDateTime.now());

		newRecipeJess.setDifficulty(4);

		ArrayList<IngredientQuantity> ingredients = new ArrayList<IngredientQuantity>();

		for (int i = 0; i < 5; i++) {

			l = random.nextLong(1, 20);
			IngredientQuantity iq = new IngredientQuantity();
			iq.setIngredient(ingredientRepository.findById(1L).get());
			l = random.nextLong(1, 5);
			iq.setMeasure(measureRepository.findById(1L).get());
			iq.setQuantity((double) Math.round(random.nextDouble(1, 50)));
			ingredients.add(iq);
		}

		newRecipeJess.setIngredients(ingredients);
		newRecipeJess.setServings(4);
		Image thumbnail = new Image();
		thumbnail.setUrl("https://picsum.photos/id/" + random.nextInt(1, 500) + "/200");
		newRecipeJess.setThumbnailUrl(thumbnail);

		recipeRepository.save(newRecipeJess);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonUser = mapper.writeValueAsString(user);
		// String jsonRecipe = mapper.writeValueAsString(newRecipe);
		System.out.println(jsonUser);
		// System.out.println(jsonRecipe);

	}

}
