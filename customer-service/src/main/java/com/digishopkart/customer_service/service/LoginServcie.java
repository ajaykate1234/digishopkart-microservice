package com.digishopkart.customer_service.service;

import com.digishopkart.customer_service.entity.User;
import com.digishopkart.customer_service.enums.Roles;
import com.digishopkart.customer_service.repository.LoginRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
@Log4j2
public class LoginServcie {

    @Autowired
    private LoginRepository repo;
//    spring.security.oauth2.client.registration.google.client-id=156204871014-085snmkiot5ajp5vfh5bjbkvhvsg2523.apps.googleusercontent.com
//    spring.security.oauth2.client.registration.google.client-secret=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
//    spring.security.oauth2.client.registration.google.scope=profile, email
//    spring.security.oauth2.client.registration.google.redirect-uri=http://localhost:8086/oauth2/callback

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;

    public User loginUser(User user){
       log.info("loginUser: User LoggedIn");
        Optional<User> existingUser = repo.findByEmail(user.getEmail());

        if(existingUser.isEmpty()){
//            User newUser = new User();
//            newUser.setUsername(user.getUsername());
//            newUser.setEmail(user.getEmail());
//            newUser.setProfilePicture(user.getAttribute("picture"));
//            newUser.setGoogleId(user.getAttribute("sub"));
            List l= new ArrayList();
            l.add(Roles.USER);
            user.setRoles(l); // Default role is USER
            User uu = repo.save(user);
            log.info("getUserInfoService : new used added : {}", uu);
        }
        log.info("Used already registered");
        return user;
    }

    public User registerUser(User user) {
        User res =  repo.save(user);
        log.info("registerUser res: {}",res);
        return res;
    }

    public Map<String, Object> getUserInfoService(OAuth2User user) {
        String email = user.getAttribute("email");


        Optional<User> existingUser = repo.findByEmail(email);

        if(existingUser.isEmpty()){
            User newUser = new User();
            newUser.setUsername(user.getAttribute("name"));
            newUser.setEmail(email);
            newUser.setProfilePicture(user.getAttribute("picture"));
            newUser.setGoogleId(user.getAttribute("sub"));
            List l= new ArrayList();
            l.add(Roles.USER);
            newUser.setRoles(l); // Default role is USER
            repo.save(newUser);
            log.info("getUserInfoService : new used added");
        }
        log.info("Used already registered");
        return user.getAttributes();
    }


    public String exchangeTokenService(String authorizationCode) {
//        public String getAccessToken(String authorizationCode, String clientId, String clientSecret, String redirectUri) {
          try {
              String tokenUrl = "https://oauth2.googleapis.com/token";

              // Create form data (MultiValueMap) for the token exchange
              MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
              requestBody.add("code", authorizationCode);
              requestBody.add("client_id", clientId);
              requestBody.add("client_secret", clientSecret);
              requestBody.add("redirect_uri", redirectUri);
              requestBody.add("grant_type", "authorization_code");

              // Send POST request to Google's token endpoint
              RestTemplate restTemplate = new RestTemplate();
              HttpHeaders headers = new HttpHeaders();
              headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

              HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);
              ResponseEntity<Map> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, entity, Map.class);

              log.info("exchangeTokenService : response :{}",response);
              // Get access token from the response
              Map<String, String> responseBody = response.getBody();
              return responseBody != null ? responseBody.get("access_token") : null;

          }catch (Exception e){
              log.error("exchangeTokenService : Exceptionn: {}",e);
              return e.getMessage();
          }
//        }
    }

    public User getUserInfoFromGoogleService(String accessToken) {
        try {
            String userInfoUrl = "https://www.googleapis.com/oauth2/v3/userinfo";

            // Set the Authorization header with Bearer token
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + accessToken);

            // Send GET request to Google API
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(userInfoUrl, HttpMethod.GET, entity, String.class);

            User user = parseJsonResponse(response);

            log.info("getUserInfoFromGoogleService: user : {}",user);
            // Return the user info as a string (JSON response)
            return user;

        }catch (Exception e){
            log.error("getUserInfoFromGoogleService : exception :{}",e);
            return null;
        }
    }

    private User parseJsonResponse(ResponseEntity<String> response) throws JsonProcessingException {

        ObjectMapper objectMapper= new ObjectMapper();
        JsonNode jsonNode= objectMapper.readTree(response.getBody());

        User user= new User();
        user.setGoogleId(jsonNode.get("sub").asText());
        user.setName(jsonNode.get("name").asText());
        user.setEmail(jsonNode.get("email").asText());
        user.setPicture(jsonNode.get("picture").asText());
        user.setFamily_name(jsonNode.get("family_name").asText());
        user.setGiven_name(jsonNode.get("given_name").asText());
        user.setEmail_verified(jsonNode.get("email_verified").asBoolean());
        return user;
    }
}
