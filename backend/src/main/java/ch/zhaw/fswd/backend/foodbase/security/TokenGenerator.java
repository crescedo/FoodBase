package ch.zhaw.fswd.backend.foodbase.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ch.zhaw.fswd.backend.foodbase.entity.LoginInfo;
import ch.zhaw.fswd.backend.foodbase.entity.LoginInfoRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenGenerator {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration = 3600L;

    @Autowired
    private LoginInfoRepository loginInfoRepository;

    public UserAuthResponse generateJWT(String username) {

        UserAuthResponse userAuthResponse = new UserAuthResponse();

        Optional<LoginInfo> login = loginInfoRepository.getLoginInfoByUserName(username);
        if (!login.isPresent()) {
            return null;
        }
        LoginInfo loggedInUser = login.get();
        if (loggedInUser == null) {
            return null;
        }
        userAuthResponse.setLoginName(loggedInUser.getLoginName());

        Map<String, Object> claimsMap = new HashMap<>();
        String rolesCSV = "";
        for (int i = 0; i < loggedInUser.getRoles().size(); i++) {
            String roleName = loggedInUser.getRoles().get(i).getRoleName();
            rolesCSV = rolesCSV + roleName;
            userAuthResponse.getRoles().add(roleName);
            if (i < loggedInUser.getRoles().size() - 1) {
                rolesCSV = rolesCSV + ",";
            }
        }

        claimsMap.put("ROLES", rolesCSV);

        Date creationDate = new Date();
        Date expirationDate = new Date(creationDate.getTime() + expiration * 1000);

        String token = Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(loggedInUser.getLoginName())
                .addClaims(claimsMap)
                .setIssuedAt(creationDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        userAuthResponse.setExpiresAt(expirationDate);
        userAuthResponse.setJwsToken(token);
        System.out.println("TOKEN: " + token);
        return userAuthResponse;
    }

}