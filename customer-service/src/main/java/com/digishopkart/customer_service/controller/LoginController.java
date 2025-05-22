package com.digishopkart.customer_service.controller;

import com.digishopkart.customer_service.entity.User;
import com.digishopkart.customer_service.enums.Roles;
import com.digishopkart.customer_service.service.LoginServcie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/digi")
@RequestMapping("/oauth2")
@Log4j2
@CrossOrigin(origins = "**") // Allow Vue frontend
public class LoginController {


    @Autowired
    private LoginServcie loginServcie;

    @GetMapping("/googleAuthUrlCall")
    public ResponseEntity<String> googleAuthUrlCall(){
//        loginServcie.googleAuthUrlCallService();
         String res = loginServcie.buildGoogleAuthUrl();
         log.info("googleAuthUrlCall end...!: res {}", res);
         return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/callback")
    public ResponseEntity<?> handleCallback(@RequestParam("code") String authorizationCode) {

        log.info("handleCallback start...!");
        // Handle the OAuth 2.0 callback (e.g., exchange the code for an access token)
        // and process the response
        log.info("Authorization successful. You can now use the token :{}",authorizationCode);

        String accessToken =  loginServcie.exchangeTokenService(authorizationCode);
        log.info("accessToken : {}",accessToken);

        User userInfo= loginServcie.getUserInfoFromGoogleService(accessToken);
        log.info("userInfo {}",userInfo);
        return new ResponseEntity<>(userInfo,HttpStatus.OK);
    }

    @GetMapping("/callback1")
    public ResponseEntity<User> handleCallback1(@RequestParam("code") String authorizationCode) {
        log.info("handleCallback1 start...!");
        // Handle the OAuth 2.0 callback (e.g., exchange the code for an access token)
        // and process the response
        log.info("Authorization successful. You can now use the token :{}",authorizationCode);

        String accessToken =  loginServcie.exchangeTokenService(authorizationCode);
        log.info("accessToken : {}",accessToken);

        User userInfo= loginServcie.getUserInfoFromGoogleService(accessToken);
        log.info("userInfo {}",userInfo);
        return new ResponseEntity<>(userInfo,HttpStatus.OK);
    }

    @GetMapping("/exchangeToken")
    public ResponseEntity<?> exchangeToken(@RequestParam String code){
       String res =  loginServcie.exchangeTokenService(code);
       return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/getUserDataFromGoogle")
    public ResponseEntity<?> getUserDataFromGoogle(@RequestParam String accessToken){
        User res= loginServcie.getUserInfoFromGoogleService(accessToken);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        log.info("registerUser controller :{}",user);
        User response =  loginServcie.registerUser(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String username,
                                          @RequestParam String email,
                                          @RequestParam String password ){
        List<Roles> l= new ArrayList<>();
        l.add(Roles.USER);
        l.add(Roles.ADMIN);
        User u= new User();
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(password);
        u.setRoles(l);


        log.info("registerUser controller :{}",u);
        User response =  loginServcie.loginUser(u);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PostMapping("/user/login")
//    public ResponseEntity<?> userLogin(@RequestBody User user){
//        log.info("userLogin controller :{}",user);
//        User response =  loginServcie.loginUser(user);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @GetMapping("/user/info")
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal OAuth2User user){

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }


        log.info("getUserInfo : user : {}",user);
        Map<String, Object> response =  loginServcie.getUserInfoService(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/admin/home")
    public String adminPage() {
        return "Welcome, Admin!";
    }

    @GetMapping("/info")
    public Map<String, String> getUserInfo(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .collect(Collectors.toMap(cookie -> cookie.getName(), cookie -> cookie.getValue()));
    }
}
