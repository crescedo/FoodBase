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
		im.setUrl("https://i.stack.imgur.com/l60Hf.png");
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
		imTwo.setUrl("https://i.stack.imgur.com/l60Hf.png");
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
			Builder.createIngredientQuantitiy(ingredientRepository.findById(1L).get(), measureRepository.findById(3L).get(), 400D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(2L).get(), measureRepository.findById(3L).get(), 300D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(3L).get(), measureRepository.findById(2L).get(), 1D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(4L).get(), measureRepository.findById(3L).get(),50D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(5L).get(), measureRepository.findById(2L).get(), 1.5D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(6L).get(), measureRepository.findById(8L).get(), 1.5D)
			);

		Recipe kürbisBrot =Builder.createRecipe(userOne, categoryRepository.findById(2L).get(), steps, ingredients, kürbisBrotTitle, kürbisBrotDescription, 1, 2, Builder.createImage("https://www.eatbetter.de/sites/eatbetter.de/files/styles/full_width_tablet_4_3/public/2020-09/kuerbisbrot_1.jpg?h=4521fff0&itok=DP5-5sqK"), Duration.ofHours(3).plusMinutes(30));	
		recipeRepository.save(kürbisBrot);

	//Kürbissuppe
		String kürbisSuppeTitle = "Kürbissuppe";
		String kürbisSuppeDescription = "Für den kühlen Herbst oder Winter.";
		String kürbisSuppeFirstStepTitle = "Kürbis dünsten";
		String kürbisSuppefirstStepContent = "Zwiebel und Curry in Butter andünsten. Kürbis, Rüebli und Kartoffel dazugeben, mitdünsten. Bouillon beifügen, aufkochen, würzen. Zugedeckt 20-25 Minuten köcheln.";
		
		List<Image> kSimages =  Arrays.asList(
			Builder.createImage("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.gutekueche.de%2Feinfaches-kuerbisgemuese-rezept-14577&psig=AOvVaw0vM8qaW8vrnZUaPQXJNGf7&ust=1676297937629000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCIjh6MmWkP0CFQAAAAAdAAAAABAE")
			);
		
		List<Step> kSsteps =  Arrays.asList(
			Builder.createStep(kürbisSuppeFirstStepTitle,  kürbisSuppefirstStepContent,kSimages,1)
			);
	
		List<IngredientQuantity> kSingredients =  Arrays.asList(
			Builder.createIngredientQuantitiy(ingredientRepository.findById(7L).get(), measureRepository.findById(7L).get(), 1D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(1L).get(), measureRepository.findById(3L).get(), 400D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(6L).get(), measureRepository.findById(8L).get(), 0.8D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(8L).get(), measureRepository.findById(1L).get(),1D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(9L).get(), measureRepository.findById(3L).get(), 180D)
			);

		Recipe kürbisSuppe =Builder.createRecipe(userOne, categoryRepository.findById(3L).get(), kSsteps, kSingredients, kürbisSuppeTitle, kürbisSuppeDescription, 1, 1, Builder.createImage("https://res.cloudinary.com/swissmilk/image/fetch/ar_16:10,g_auto,w_720,c_fill,f_auto,q_auto,fl_progressive/https://api.swissmilk.ch/wp-content/uploads/2019/06/kuerbissuppe-2560x1707.jpg"), Duration.ofHours(1).plusMinutes(40));	
		recipeRepository.save(kürbisSuppe); 
	
		//Valentinsdessert
		String vdTitle = "Valentinsdessert";
		String vdDescription = "Zuckersüss und nur für die Liebsten.";
		String vdFirstStepTitle = "Biscuit";
		String vdFirstStepContent = "Butterguetzli und Amaretti in einen Tiefkühlbeutel geben, mit dem Wallholz fein zerdrücken oder mit dem Cutter mahlen. Mit Butter mischen, auf den vorbereiteten Formenboden verteilen, gut andrücken, kühl stellen.";
		String vdSecondStepTitle = "Mascarpone-Quark-Masse";
		String vdSecondStepContent = "Mascarpone, Magerquark und Rahmhalter gut verrühren. Zitronenschale und -saft dazugeben. Alle Zuckerarten miteinander vermischen, ebenfalls dazugeben. Bis zur Verwendung kühl stellen.";
		
		List<Image> vDimages =  Arrays.asList(
			Builder.createImage("https://www.br.de/radio/bayern1/kuchen-ohne-spezialbackform-104~_v-img__16__9__xl_-d31c35f8186ebeb80b0cd843a7c267a0e0c81647.jpg?version=4bff4")
			);
		
		List<Step> vDsteps =  Arrays.asList(
			Builder.createStep(vdFirstStepTitle,  vdFirstStepContent,vDimages,1)
			);
	
		List<IngredientQuantity> vDingredients =  Arrays.asList(
			Builder.createIngredientQuantitiy(ingredientRepository.findById(10L).get(), measureRepository.findById(3L).get(), 100D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(11L).get(), measureRepository.findById(3L).get(), 100D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(12L).get(), measureRepository.findById(7L).get(), 1D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(13L).get(), measureRepository.findById(1L).get(),4D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(9L).get(), measureRepository.findById(3L).get(), 500D)
			);

		Recipe Valentinsdessert =Builder.createRecipe(userOne, categoryRepository.findById(5L).get(), vDsteps, vDingredients, vdTitle, vdDescription, 12, 3, Builder.createImage("https://res.cloudinary.com/swissmilk/image/fetch/ar_16:10,g_auto,w_720,c_fill,f_auto,q_auto,fl_progressive/https://api.swissmilk.ch/wp-content/uploads/2022/12/SM2022_DIVE_89_quarktorte-mit-beeren-ohne-backen-2560x1708.jpg"), Duration.ofHours(1).plusMinutes(15));	
		recipeRepository.save(Valentinsdessert);

		//Zmorge Bagel
		String zbTitle = "Zmorge Bagel";
		String zbDescription = "Kohlenhydrate zum Frühstück";
		String zbFirstStepTitle = "Teig";
		String zbFirstStepContent = "Mehl und alle Zutaten bis und mit Salz in der Schüssel der Küchenmaschine mischen. Milchwasser dazugiessen, Honig und Mandelmus beigeben, mit dem Knethaken der Küchenmaschine ca. 5 Min. zu einem weichen, glatten Teig kneten. Zugedeckt bei Raumtemperatur ca. 2 Std. aufgehen lassen.";
		
		List<Image> zbimages =  Arrays.asList(
			Builder.createImage("https://images.ichkoche.at/data/image/variations/496x384/2/brotteig-grundrezept-mit-hefe-img-19661.jpg")
			);
		
		List<Step> zbsteps =  Arrays.asList(
			Builder.createStep(zbFirstStepTitle,  zbFirstStepContent,zbimages,1)
			);
	
		List<IngredientQuantity> zbingredients =  Arrays.asList(
			Builder.createIngredientQuantitiy(ingredientRepository.findById(2L).get(), measureRepository.findById(3L).get(), 400D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(3L).get(), measureRepository.findById(1L).get(), 1D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(4L).get(), measureRepository.findById(1L).get(), 2D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(5L).get(), measureRepository.findById(3L).get(),20D)
			);

		Recipe ZmorgeBagel =Builder.createRecipe(userOne, categoryRepository.findById(1L).get(), zbsteps, zbingredients, zbTitle, zbDescription, 8, 1, Builder.createImage("https://cdn.gutekueche.de/upload/rezept/2140/vollkorn-bagel.jpg"), Duration.ofHours(2).plusMinutes(30));	
		recipeRepository.save(ZmorgeBagel); 

		//Bierbrot
		String bbTitle = "Bierbrot";
		String bbDescription = "Klingt gut für den Feierabend?";
		String bbFirstStepTitle = "Teig";
		String bbFirstStepContent = "Mehle, Backpulver und Salz in einer Schüssel mischen. Bier und Joghurt dazugiessen, mit einer Kelle gut mischen, bis ein flüssiger, weicher Teig entsteht. Eine zweite Schüssel mit einem rund zugeschnittenen Backpapier (ca. 30 cm Ø) auslegen, Teig daraufgeben.";
		
		List<Image> bbimages =  Arrays.asList(
			Builder.createImage("https://christopherlangbrotsommelier.files.wordpress.com/2022/02/den-teig-richtig-kneten-christopher-lang.jpg?w=640")
			);
		
		List<Step> bbsteps =  Arrays.asList(
			Builder.createStep(bbFirstStepTitle,  bbFirstStepContent,bbimages,1)
			);
	
		List<IngredientQuantity> bbingredients =  Arrays.asList(
			Builder.createIngredientQuantitiy(ingredientRepository.findById(2L).get(), measureRepository.findById(3L).get(), 400D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(3L).get(), measureRepository.findById(1L).get(), 1D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(14L).get(), measureRepository.findById(8L).get(), 0.4D),
			Builder.createIngredientQuantitiy(ingredientRepository.findById(5L).get(), measureRepository.findById(3L).get(),20D)
			);

		Recipe BierBrot =Builder.createRecipe(userOne, categoryRepository.findById(1L).get(), bbsteps, bbingredients, bbTitle, bbDescription, 8, 2, Builder.createImage("https://www.brooot.de/wp-content/uploads/sites/21/2022/02/Schnelles-5-Stunden-Bierbrot.jpg"), Duration.ofHours(1).plusMinutes(15));	
		recipeRepository.save(BierBrot); 
		 
	}

}
