package ch.zhaw.fswd.backend.foodbase;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import ch.zhaw.fswd.backend.foodbase.entity.RoleRepository;

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
				 // This is only really relevant for development, where we have different servers for frontend and backend   
				.allowedOrigins("http://localhost:8100")
				.allowedMethods("GET", "PUT", "POST", "DELETE")
				// AllowCredentials is necessary, as it sets 'Access-Control-Allow-Credentials'.    
				// Otherwise Angular's HttpClient will not pass the Cookie back.      
				.allowCredentials(true);}
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


	@Override
	public void run(String... args) throws Exception {

		String[] words = {
				"lorem", "ipsum", "dolor", "sit", "amet,", "consectetur", "adipiscing", "elit",
				"sed", "do", "eiusmod", "tempor", "incididunt", "ut", "labore", "et", "dolore", "magna",
				"aliqua."
		};

		

		// neues User hinzufügen
		Random random = new Random();

		User newUser = new User();
		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName("Gianni");
		userInfo.setLastName("Rivera");
		Image im = new Image();
		im.setUrl("https://picsum.photos/id/" + random.nextInt( 500) + "/200");
		userInfo.setProfilePic(im);
		newUser.setUserInfo(userInfo);
		LoginInfo login = new LoginInfo();
		login.setLoginName("user");
        login.setPasswordHash(new BCryptPasswordEncoder().encode("user"));
        Role r = new Role();
        r.setRoleName("ROLE_USER");
        roleRepository.save(r);
        login.getRoles().add(r);
		
		newUser.setLoginInfo(login);
		newUser.setCookings(new ArrayList<Cooking>());
		newUser.setFavorites(new ArrayList<Recipe>());

		userRepository.save(newUser);

		User user = userRepository.findById(newUser.getId()).get();
		
		//Rezepte generieren
		for (int f = 0; f < 20; f++) {

			Recipe newRecipe = new Recipe();
			newRecipe.setCreator(user);
			Long l = 1L;
			Category category = categoryRepository.findById(l).get();
			newRecipe.setCategory(category);
			ArrayList<Step> steps = new ArrayList<Step>();
			int maxLoop = random.nextInt(10)+1;

			StringBuilder title = new StringBuilder();
			StringBuilder content = new StringBuilder();
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
					image.setUrl("https://picsum.photos/id/" + (random.nextInt( 500)+1) + "/200");
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
			/* for (int i = 0; i < 5; i++) {

				l = 5L;
				IngredientQuantity iq = new IngredientQuantity();
				iq.setIngredient(ingredientRepository.findById(l).get());
				l = 5L;
				iq.setMeasure(measureRepository.findById(l).get());
				iq.setQuantity((double) Math.round(1D));
				ingredients.add(iq);
			} */

			newRecipe.setIngredients(ingredients);
			newRecipe.setServings(4);
			newRecipe.setThumbnailUrl(null);
			recipeRepository.save(newRecipe);
		}
		
		
		String username = "user";
        
        UserAuthResponse userAuthResponse = tokenGenerator.generateJWT(username);
        Cookie cookie = new Cookie("Authentication", userAuthResponse.getJwsToken());

	//System.out.println(	FullResponseBuilder.getFullResponse("http://localhost:8080/auth/token"));

		//ObjectMapper mapper = new ObjectMapper();
		//String jsonUser = mapper.writeValueAsString(user);
		// String jsonRecipe = mapper.writeValueAsString(newRecipe);
		//System.out.println(jsonUser);
		// System.out.println(jsonRecipe);

	}

}
