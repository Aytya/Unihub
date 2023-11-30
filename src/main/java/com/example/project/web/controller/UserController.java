package com.example.project.web.controller;

import com.example.project.domain.model.User;
import com.example.project.domain.reponseModels.UserInfoResponse;
import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://127.0.0.1:4200")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String Hello(){
        return "HELLO";
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserInfoResponse> getUserByEmail(@PathVariable String email){
        User user = userService.findUserByEmail(email);
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        userInfoResponse.setId(user.getId());
        userInfoResponse.setEmail(user.getEmail());
        userInfoResponse.setFirstName(user.getFirstName());
        userInfoResponse.setLastName(user.getLastName());
        userInfoResponse.setRole(user.getRole());
        return ResponseEntity.ok(userInfoResponse);
    }
//    @GetMapping("/{token}")
//    public ResponseEntity<User> getUserByToken(@PathVariable String token) {
//        Optional<User> user = userService.findUserByToken(token);
//        System.out.println("controller - " + user.get());
//        if(user.isPresent())
//            return new ResponseEntity<>(user.get(), HttpStatus.OK);
//        else
//            return ResponseEntity.notFound().build();
//    }
}
