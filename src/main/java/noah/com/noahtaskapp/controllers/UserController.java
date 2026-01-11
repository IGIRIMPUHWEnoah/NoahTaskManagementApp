package noah.com.noahtaskapp.controllers;

import jakarta.validation.Valid;
import noah.com.noahtaskapp.dtos.RegisterUserRequest;
import noah.com.noahtaskapp.dtos.UserDtos;
import noah.com.noahtaskapp.models.UserModel;
import noah.com.noahtaskapp.repositories.UserRepository;
import noah.com.noahtaskapp.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class UserController  {


    private final UserRepository userRepository;
    private   UserService userService;
    UserController(UserService userService, UserRepository userRepository){
        this.userService=userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    public ResponseEntity<?> RegisterUser( @Valid @RequestBody RegisterUserRequest registerUserRequest){

        UserModel user=new UserModel();
        user.setUsername(registerUserRequest.getUsername());
        user.setEmail(registerUserRequest.getEmail());
        user.setPassword(registerUserRequest.getPassword());

      if(userRepository.existsByEmail(user.getEmail())) return ResponseEntity.badRequest().body(Map.of("email","Email is already registered."));

        userService.createUser(user);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/users")
    public List<UserDtos>  getAllUser(){

        return userService.getAllUsers().stream().map(user->new UserDtos(user.getUsername(),user.getEmail())).toList();

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDtos>  getUserById(@PathVariable Long id) {

        var user=userService.getUserById(id);

        if(user==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new UserDtos(user.getUsername(),user.getEmail()));

    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PutMapping("/users/{id}")
    public  void updateUser(@RequestBody  UserModel userModel,@PathVariable  Long id){

        userService.updateUser(userModel,id);
    }



}
