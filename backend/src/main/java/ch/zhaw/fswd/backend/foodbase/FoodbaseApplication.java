package ch.zhaw.fswd.backend.foodbase;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

import Util.FullResponseBuilder;

import ch.zhaw.fswd.backend.foodbase.controller.RecipeController;
import ch.zhaw.fswd.backend.foodbase.entity.Category;
import ch.zhaw.fswd.backend.foodbase.entity.CategoryRepository;
import ch.zhaw.fswd.backend.foodbase.entity.Cooking;
import ch.zhaw.fswd.backend.foodbase.entity.Image;
import ch.zhaw.fswd.backend.foodbase.entity.IngredientQuantity;
import ch.zhaw.fswd.backend.foodbase.entity.IngredientRepository;
import ch.zhaw.fswd.backend.foodbase.entity.LoginInfo;
import ch.zhaw.fswd.backend.foodbase.entity.MeasureRepository;
import ch.zhaw.fswd.backend.foodbase.entity.Recipe;
import ch.zhaw.fswd.backend.foodbase.entity.RecipeRepository;
import ch.zhaw.fswd.backend.foodbase.entity.Role;
import ch.zhaw.fswd.backend.foodbase.entity.RoleRepository;
import ch.zhaw.fswd.backend.foodbase.entity.Step;
import ch.zhaw.fswd.backend.foodbase.entity.User;
import ch.zhaw.fswd.backend.foodbase.entity.UserInfo;
import ch.zhaw.fswd.backend.foodbase.entity.UserRepository;
import ch.zhaw.fswd.backend.foodbase.security.TokenGenerator;
import ch.zhaw.fswd.backend.foodbase.security.UserAuthResponse;
import ch.zhaw.fswd.backend.foodbase.entity.LoginInfoRepository;
import ch.zhaw.fswd.backend.foodbase.controller.UserController;

@SpringBootApplication
public class FoodbaseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FoodbaseApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						// This is only really relevant for development, where we have different servers
						// for frontend and backend   
						.allowedOrigins("http://localhost:8100")
						.allowedMethods("GET", "PUT", "POST", "DELETE")
						// AllowCredentials is necessary, as it sets 'Access-Control-Allow-Credentials'.
						//    
						// Otherwise Angular's HttpClient will not pass the Cookie back.      
						.allowCredentials(true);
			}
		};
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

	@Autowired
	private LoginInfoRepository loginInfoRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private TokenGenerator tokenGenerator;

	@Autowired
	private RecipeController recipeController;

	@Autowired
	private UserController userController;

	@Override
	public void run(String... args) throws Exception {

		String[] words = {
				"lorem", "ipsum", "dolor", "sit", "amet,", "consectetur", "adipiscing", "elit",
				"sed", "do", "eiusmod", "tempor", "incididunt", "ut", "labore", "et", "dolore", "magna",
				"aliqua."
		};

		// neues User hinzufügen
		Random random = new Random();

		User userOne = new User();
		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName("Gianni");
		userInfo.setLastName("Rivera");
		Image im = new Image();
		im.setUrl("https://source.unsplash.com/random/200x200?sig=" + random.nextInt(10) +1);
		userInfo.setProfilePic(im);
		userOne.setUserInfo(userInfo);
		LoginInfo login = new LoginInfo();
		login.setLoginName("user");
		login.setPasswordHash(new BCryptPasswordEncoder().encode("user"));
		Role r = new Role();
		r.setRoleName("ROLE_USER");
		roleRepository.save(r);
		login.getRoles().add(r);

		userOne.setLoginInfo(login);
		userOne.setCookings(new ArrayList<Cooking>());
		userOne.setFavorites(new ArrayList<Recipe>());

		userRepository.save(userOne);

		User userTwo = new User();
		UserInfo userTwoInfo = new UserInfo();
		userTwoInfo.setFirstName("Giampiero");
		userTwoInfo.setLastName("Mughini");
		Image imTwo = new Image();
		imTwo.setUrl("https://source.unsplash.com/random/200x200?sig="+ random.nextInt(10)+1 );
		userTwoInfo.setProfilePic(imTwo);
		userTwo.setUserInfo(userTwoInfo);
		LoginInfo loginTwo = new LoginInfo();
		loginTwo.setLoginName("p");
		loginTwo.setPasswordHash(new BCryptPasswordEncoder().encode("p"));
		Role rTwo = new Role();
		rTwo.setRoleName("ROLE_USER");
		// roleRepository.save(r);
		loginTwo.getRoles().add(rTwo);

		userTwo.setLoginInfo(loginTwo);
		userTwo.setCookings(new ArrayList<Cooking>());
		userTwo.setFavorites(new ArrayList<Recipe>());

		userRepository.save(userTwo);

		User user = userRepository.findById(userOne.getId()).get();

		// Rezepte generieren
		for (int f = 0; f < 20; f++) {

			Recipe newRecipe = new Recipe();

			newRecipe.setCreator(user);
			Long l = 1L;

			Category category = categoryRepository.findById(l).get();
			newRecipe.setCategory(category);
			ArrayList<Step> steps = new ArrayList<Step>();
			int maxLoop = random.nextInt(10) + 1;

			StringBuilder recipeTitle = new StringBuilder();
			StringBuilder recipeDescription = new StringBuilder();
			StringBuilder stepTitle = new StringBuilder();
			StringBuilder content = new StringBuilder();
			
			for (int z = 0; z < 4; z++) {
				recipeTitle.append(words[random.nextInt(words.length)] + " ");
			}
			for (int z = 0; z < 5; z++) {
				recipeDescription.append(words[random.nextInt(words.length)] + " ");
			}

			for (int i = 0; i < maxLoop; i++) {

				int numberOfParagraphs = 2;
				int numberOfSentences = 2;

				for (int a = 0; a < numberOfParagraphs; a++) {

					stepTitle.append(words[random.nextInt(words.length)] + " ");

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
				step.setTitle(stepTitle.toString().toUpperCase());
				step.setContent(content.toString());
				ArrayList<Image> images = new ArrayList<>();
				for (int j = 0; j < 3; j++) {
					Image image = new Image();
					image.setUrl("https://source.unsplash.com/random/500x300?sig="+ random.nextInt(100)+1);
					images.add(image);
				}
				step.setImages(images);
				steps.add(step);
			}
			if (f % 7 == 0) {
				newRecipe.setTitle("Curry Bean Paste");
			} else{
				newRecipe.setTitle(recipeTitle.toString());
			}
			newRecipe.setDescriptionShort(recipeDescription.toString());
			newRecipe.setCookingSteps(steps);
			Duration duration = Duration.ofHours(1);
			duration = duration.plusMinutes(20);
			newRecipe.setCookingTime(duration);
			newRecipe.setIsoString(newRecipe.getCookingTime().toString());
			newRecipe.setCreatedAt(LocalDateTime.now());
			newRecipe.setDifficulty(3);

			ArrayList<IngredientQuantity> ingredients = new ArrayList<IngredientQuantity>();

			for (int i = 0; i < 5; i++) {

				l = 5L;
				IngredientQuantity iq = new IngredientQuantity();
				iq.setIngredient(ingredientRepository.findById(l).get());
				l = 5L;
				iq.setMeasure(measureRepository.findById(l).get());
				iq.setQuantity((double) Math.round(1D));
				ingredients.add(iq);
			}

			newRecipe.setIngredients(ingredients);
			newRecipe.setServings(4);
			Image thumbnail = new Image();
			thumbnail.setUrl("https://source.unsplash.com/random/500x300?sig="+ random.nextInt(10)+1);
			newRecipe.setThumbnailUrl(thumbnail);
			recipeRepository.save(newRecipe);
		}
//userController.updateFavorites(recipeRepository.findById(1L).get(), "user");
		List<Recipe> recipes = recipeController.findAllRecipesByTitle("Curry Bean");
		/*
		 * String username = "user";
		 * 
		 * UserAuthResponse userAuthResponse = tokenGenerator.generateJWT(username);
		 * Cookie cookie = new Cookie("Authentication", userAuthResponse.getJwsToken());
		 */

		// System.out.println(
		// FullResponseBuilder.getFullResponse("http://localhost:8080/auth/token"));

		// ObjectMapper mapper = new ObjectMapper();
		// String jsonUser = mapper.writeValueAsString(user);

		// String jsonRecipe = mapper.writeValueAsString(newRecipe);
		// System.out.println(jsonUser);
		// System.out.println(jsonRecipe);

	}

}
