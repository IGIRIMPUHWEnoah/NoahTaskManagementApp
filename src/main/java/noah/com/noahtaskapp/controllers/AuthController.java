package noah.com.noahtaskapp.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import noah.com.noahtaskapp.dtos.LoginUserRequest;
import noah.com.noahtaskapp.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginUserRequest loginUserRequest){
var user=userRepository.findByEmail(loginUserRequest.getEmail()).orElse(null);

if(user==null) return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

      if (!passwordEncoder.matches(loginUserRequest.getPassword(),user.getPassword())){
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
return ResponseEntity.ok().build();
    }
}
