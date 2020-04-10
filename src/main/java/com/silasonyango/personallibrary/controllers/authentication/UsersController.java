package com.silasonyango.personallibrary.controllers.authentication;

import com.silasonyango.personallibrary.models.authentication.UserModel;
import com.silasonyango.personallibrary.repository.authentication.UserRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/get_users")
    public List<UserModel> getAllUsers() {
        return userRepository.getUsers();
    }

    @PostMapping("/create_user")
    public UserModel createUser(@Valid UserModel user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }

    @PostMapping("/get_by_email")
    public List<UserModel> findByEmail(@Valid @RequestBody JSONObject emailOject) {
        return userRepository.findByEmail(emailOject.getAsString("attemptedEmail"));
    }

    @PostMapping("/authenticate")
    public JSONObject authenticate(@Valid @RequestBody JSONObject credentialsObject) {
        UserModel myUser = new UserModel();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        JSONObject myresponseObject = new JSONObject();
        List<UserModel> myUserList = userRepository.findByEmail(credentialsObject.getAsString("attemptedEmail"));
        myUser = myUserList.get(0);

        if(passwordEncoder.matches(credentialsObject.getAsString("attemptedPassword"),myUser.getPassword())){
            myresponseObject.put("success", true);
            myresponseObject.put("userDetails", myUser);
        }else {

            myresponseObject.put("success", false);
            myresponseObject.put("successMessage", "Login was not successful. Incorrect user or password");
        }

        return myresponseObject;
    }

}
