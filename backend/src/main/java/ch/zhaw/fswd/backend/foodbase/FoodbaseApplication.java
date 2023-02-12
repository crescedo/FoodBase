package ch.zhaw.fswd.backend.foodbase;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

import Util.Builder;
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
		
		String kürbisBrotTitle = "Kürbisbrot à la Mama";
		String kürbisBrotDescription = "Leckeres und gesundes Brot für Zwischendurch";
		String kürbisBrotFirstStepTitle = "Teig";
		String kürbisBrotfirstStepContent = "Kürbis mit der Röstiraffel in die Schüssel der Küchenmaschine reiben (ergibt ca. 200 g), 2 EL davon beiseitestellen. Mehl, Salz, Leinsamen, Kürbiskerne und Hefe beigeben, mischen. Wasser dazugiessen, mit dem Knethacken zu einem weichen, glatten Teig kneten. Zugedeckt bei Raumtemperatur ca. 2 Std. aufgehen lassen.";
		
		List<Image> images =  Arrays.asList(
			Builder.createImage("https://www.gourmetgeeks.de/wp-content/uploads/2015/10/kuerbisbrot-11.jpg")
			);
		
		List<Step> steps =  Arrays.asList(
			Builder.createStep(kürbisBrotFirstStepTitle,  kürbisBrotfirstStepContent,images,1)
			);
	
		List<IngredientQuantity> ingredients =  Arrays.asList(
			Builder.createIngredientQuantitiy(ingredientRepository.findById(15L).get(), measureRepository.findById(3L).get(), 400D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(16L).get(), measureRepository.findById(3L).get(), 300D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(18L).get(), measureRepository.findById(3L).get(), 50D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(19L).get(), measureRepository.findById(3L).get(),50D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(20L).get(), measureRepository.findById(2L).get(), 1.5D)
			);

		Recipe kürbisBrot =Builder.createRecipe(userOne, categoryRepository.findById(2L).get(), steps, ingredients, kürbisBrotTitle, kürbisBrotDescription, 1, 1, Builder.createImage("https://www.eatbetter.de/sites/eatbetter.de/files/styles/full_width_tablet_4_3/public/2020-09/kuerbisbrot_1.jpg?h=4521fff0&itok=DP5-5sqK"), Duration.ofHours(3).plusMinutes(30));	
		recipeRepository.save(kürbisBrot);

		
	}

}
