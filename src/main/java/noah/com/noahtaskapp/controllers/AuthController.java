package noah.com.noahtaskapp.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import noah.com.noahtaskapp.dtos.LoginUserRequest;
import noah.com.noahtaskapp.dtos.TokenResponse;
import noah.com.noahtaskapp.repositories.UserRepository;
import noah.com.noahtaskapp.services.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginUserRequest loginUserRequest){
authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserRequest.getEmail(),loginUserRequest.getPassword()));

String token= jwtService.generateToken(loginUserRequest.getEmail());
return ResponseEntity.ok(new TokenResponse(token));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public  ResponseEntity<Void> handleBadCredentialsException(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/validate")
    public boolean validateToken(@RequestHeader("Authorization") String authHeader){

        var token=authHeader.replace("Bearer "," ").trim();
        return jwtService.validateToken(token);

    }

}
