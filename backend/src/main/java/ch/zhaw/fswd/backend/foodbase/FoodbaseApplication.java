package ch.zhaw.fswd.backend.foodbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import ch.zhaw.fswd.backend.foodbase.entity.*;

@SpringBootApplication
public class FoodbaseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FoodbaseApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoginInfoRepository loginInfoRepository;
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public void run(String... args) throws Exception {

		//neues User hinzufügen
		User newUser = new User();
		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName("Gianni");
		userInfo.setLastName("Rivera");
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
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(user);
		System.out.println(json);
	}

}
