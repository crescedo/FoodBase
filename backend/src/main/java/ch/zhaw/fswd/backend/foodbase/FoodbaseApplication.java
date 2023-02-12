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
import java.util.Arrays;
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

		Random random = new Random();

		// User One hinzufügen
		
		User userOne = Builder.createUser("TESTUSER_ONE", "test", "one", "one", random, roleRepository);
		userRepository.save(userOne);
		
		User userTwo = Builder.createUser("TESTUSER_TWO", "test", "two", "two", random, roleRepository);		
		userRepository.save(userTwo);
	// Rezepte generieren
	for (int f = 0; f < 2; f++) {

		Recipe newRecipe = new Recipe();

		newRecipe.setCreator(userOne);
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
				image.setUrl("https://source.unsplash.com/random/500x300?sig=" + random.nextInt(100) + 1);
				images.add(image);
			}
			step.setImages(images);
			steps.add(step);
		}
		if (f % 7 == 0) {
			newRecipe.setTitle("Curry Bean Paste");
		} else {
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
		thumbnail.setUrl("https://source.unsplash.com/random/500x300?sig=" + random.nextInt(10) + 1);
		newRecipe.setThumbnailUrl(thumbnail);
		recipeRepository.save(newRecipe);
	}
		// Kürbisbrot erstellen
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

		/* // Popette erstellen
		Recipe polpette = new Recipe();

		polpette.setCreator(userOne);

		polpette.setCategory(categoryRepository.findById(4L).get());
		ArrayList<Step> stepss = new ArrayList<Step>();

		String recipeTitlee = "Polpette";
		String recipeDescriptionn = "Feine kleine Rundumeli";
		String stepTitlee = "Polpette vorbereiten";
		String contentt = "Zucchetti salzen, in einem Sieb ca. 30 Min. ziehen lassen, gut ausdrücken. Zucchetti mit Hackfleisch, Zwiebel, Knoblauch und Paniermehl mischen, würzen. Von Hand gut kneten, bis sich die Zutaten zu einer kompakten Masse verbinden. Masse zu 18 Bällchen formen, eine Vertiefung eindrücken. Gorgonzola hineingeben und mit der Masse umschliessen, zu Bällchen formen.";

		Step stepp = new Step();
		stepp.setTitle(stepTitlee.toUpperCase());
		stepp.setContent(contentt);

		ArrayList<Image> imagess = new ArrayList<>();

		Image imagee = new Image();
		imagee.setUrl("https://image.brigitte.de/12258660/t/K4/v3/w1440/r1.5/-/hackfleisch-fehler.jpg");
		imagess.add(imagee);

		stepp.setImages(imagess);
		stepss.add(stepp);

		polpette.setTitle(recipeTitlee);
		polpette.setDescriptionShort(recipeDescriptionn);
		polpette.setCookingSteps(stepss);
		Duration durationn = Duration.ofHours(1);
		durationn = durationn.plusMinutes(00);
		polpette.setCookingTime(durationn);
		polpette.setIsoString(polpette.getCookingTime().toString());
		polpette.setCreatedAt(LocalDateTime.now());
		polpette.setDifficulty(2);

		ArrayList<IngredientQuantity> ingredientss = new ArrayList<IngredientQuantity>();

		IngredientQuantity aa = new IngredientQuantity();
		aa.setIngredient(ingredientRepository.findById(21L).get());
		aa.setMeasure(measureRepository.findById(3L).get());
		aa.setQuantity(200D);
		ingredientss.add(aa);

		IngredientQuantity ab = new IngredientQuantity();
		ab.setIngredient(ingredientRepository.findById(22L).get());
		ab.setMeasure(measureRepository.findById(3L).get());
		ab.setQuantity(600D);
		ingredientss.add(ab);

		IngredientQuantity ac = new IngredientQuantity();
		ac.setIngredient(ingredientRepository.findById(23L).get());
		ac.setMeasure(measureRepository.findById(7L).get());
		ac.setQuantity(1D);
		ingredientss.add(ac);

		IngredientQuantity ad = new IngredientQuantity();
		ad.setIngredient(ingredientRepository.findById(17L).get());
		ad.setMeasure(measureRepository.findById(2L).get());
		ad.setQuantity(1D);
		ingredientss.add(ad);

		polpette.setIngredients(ingredientss);
		polpette.setServings(18);
		Image thumbnaill = new Image();
		thumbnaill.setUrl(
				"https://cdn.apartmenttherapy.info/image/upload/f_jpg,q_auto:eco,c_fill,g_auto,w_1500,ar_4:3/k%2FPhoto%2FRecipe%20Ramp%20Up%2F2021-08-Polpette%2Fpolpette_v.2_1_01");
		polpette.setThumbnailUrl(thumbnaill);
		recipeRepository.save(polpette);

		// Kürbissuppe erstellen
		Recipe nrezept = new Recipe();

		nrezept.setCreator(userOne);

		nrezept.setCategory(categoryRepository.findById(3L).get());
		ArrayList<Step> stepsn = new ArrayList<Step>();

		String recipeTitlen = "Kürbissuppe";
		String recipeDescriptionnn = "Perfekt für die kalte Herbst oder Winterzeit";
		String stepTitlen = "Kürbis verarbeiten";
		String contentn = "Zwiebel und Curry in Butter andünsten. Kürbis, Rüebli und Kartoffel dazugeben, mitdünsten. Bouillon beifügen, aufkochen, würzen. Zugedeckt 20-25 Minuten köcheln.";

		Step stepn = new Step();
		stepn.setTitle(stepTitlen.toUpperCase());
		stepn.setContent(contentn);

		ArrayList<Image> imagesn = new ArrayList<>();

		Image imagen = new Image();
		imagen.setUrl(
				"https://static.wixstatic.com/media/667a4c_a793efc58a6442f79609555f1af31118~mv2.jpg/v1/fill/w_1208,h_806,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/667a4c_a793efc58a6442f79609555f1af31118~mv2.jpg");
		imagesn.add(imagen);

		stepn.setImages(imagesn);
		stepsn.add(stepn);

		nrezept.setTitle(recipeTitlen);
		nrezept.setDescriptionShort(recipeDescriptionnn);
		nrezept.setCookingSteps(stepsn);
		Duration durationnn = Duration.ofHours(0);
		durationnn = durationnn.plusMinutes(40);
		nrezept.setCookingTime(durationnn);
		nrezept.setIsoString(nrezept.getCookingTime().toString());
		nrezept.setCreatedAt(LocalDateTime.now());
		nrezept.setDifficulty(1);

		ArrayList<IngredientQuantity> ingredientsn = new ArrayList<IngredientQuantity>();

		IngredientQuantity na = new IngredientQuantity();
		na.setIngredient(ingredientRepository.findById(23L).get());
		na.setMeasure(measureRepository.findById(7L).get());
		na.setQuantity(1D);
		ingredientsn.add(na);

		IngredientQuantity nb = new IngredientQuantity();
		nb.setIngredient(ingredientRepository.findById(15L).get());
		nb.setMeasure(measureRepository.findById(3L).get());
		nb.setQuantity(400D);
		ingredientsn.add(nb);

		IngredientQuantity nc = new IngredientQuantity();
		nc.setIngredient(ingredientRepository.findById(24L).get());
		nc.setMeasure(measureRepository.findById(2L).get());
		nc.setQuantity(1.5D);
		ingredientsn.add(nc);

		IngredientQuantity nd = new IngredientQuantity();
		nd.setIngredient(ingredientRepository.findById(1L).get());
		nd.setMeasure(measureRepository.findById(8L).get());
		nd.setQuantity(0.8D);
		ingredientsn.add(nd);

		nrezept.setIngredients(ingredientsn);
		nrezept.setServings(6);
		Image thumbnailn = new Image();
		thumbnailn.setUrl(
				"https://res.cloudinary.com/swissmilk/image/fetch/ar_16:10,g_auto,w_540,c_fill,f_auto,q_auto,fl_progressive/https://api.swissmilk.ch/wp-content/uploads/2019/06/kuerbissuppe-2560x1707.jpg");
		nrezept.setThumbnailUrl(thumbnailn);
		recipeRepository.save(nrezept); */

	}

	
}
