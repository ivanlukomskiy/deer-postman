package com.ivanlukomskiy.deer.postman.security;

import com.ivanlukomskiy.deer.postman.model.User;
import com.ivanlukomskiy.deer.postman.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.ivanlukomskiy.deer.postman.model.User.Status.ACTIVE;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 14.12.2017.
 */
@Service
@NoArgsConstructor
public class TokenService {
    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    private static final String SECRET_KEY = "eHN5KnI3Qjw7e1lOdiVlLw==";
    private static final String USER_TYPE = "user";

    private static final String CLIENT_TYPE_KEY = "client_type";
    private static final String USER_ID_KEY = "user_id";
    private static final String USER_NAME_KEY = "username";

    private MessageDigest digest;

    @Autowired
    private UserService userService;

    @Value("${deer-postman.security.create-admin:true}")
    private boolean createAdminUserIfEmpty;

    @PostConstruct
    @SneakyThrows
    public void init() {
        digest = MessageDigest.getInstance("SHA-256");
        if (createAdminUserIfEmpty && userService.findByLogin("admin") == null) {
            User admin = new User();
            admin.setId(1);
            admin.setLogin("admin");
            admin.setPasswordHash(getPasswordHash("admin"));
            admin.setRegisterDate(new Date());
            admin.setRole(User.Role.ADMIN);
            admin.setStatus(ACTIVE);
            userService.createUser(admin);
        }
    }

    @Transactional(readOnly = true)
    public String getToken(String login, String password) {
        Objects.requireNonNull(login, "Username should be not null");
        Objects.requireNonNull(password, "Username should be not null");

        User user = userService.findByLogin(login);

        if (user == null) {
            throw new IllegalArgumentException("User with login " + login + " not found");
        }

        if (!getPasswordHash(password).equals(user.getPasswordHash())) {
            throw new IllegalArgumentException("Incorrect password");
        }

        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put(CLIENT_TYPE_KEY, USER_TYPE);
        tokenData.put(USER_ID_KEY, user.getId());
        tokenData.put(USER_NAME_KEY, user.getLogin());
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setClaims(tokenData);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM.getJcaName());
        return jwtBuilder.signWith(SIGNATURE_ALGORITHM, signingKey).compact();
    }

    private static void getAdminHash() {
        TokenService tokenService = new TokenService();
        tokenService.init();
        System.out.println(tokenService.getPasswordHash("admin"));
    }

    public static void main(String[] args) {
        getAdminHash();
    }

    @SneakyThrows
    private String getPasswordHash(String password) {
        return new String(digest.digest(password.getBytes(UTF_8)), UTF_8);
    }

    private Map<String, Object> decode(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token)
                .getBody();
        Map<String, Object> result = new HashMap<>();
        claims.forEach(result::put);
        return result;
    }

    @Transactional(readOnly = true)
    boolean isTokenMatchesRoles(String token, User.Role[] roles) {
        Map<String, Object> tokenData = decode(token);

        Integer id = Integer.valueOf(tokenData.get(USER_ID_KEY).toString());
        User user = userService.getUser(id);

        if (user == null) {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }

        for (User.Role availableRole : roles) {
            if (availableRole == user.getRole()) {
                return true;
            }
        }
        return false;
    }

    @Transactional(readOnly = true)
    public User getUserByToken(String token) {
        Map<String, Object> tokenData = decode(token);
        Integer id = Integer.valueOf(tokenData.get(USER_ID_KEY).toString());
        return userService.getUser(id);
    }
}
