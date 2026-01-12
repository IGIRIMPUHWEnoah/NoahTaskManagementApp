package noah.com.noahtaskapp.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
@Value("${spring.jwt.secrete}")
    private  String secrete;

    public  String generateToken(String email){

 return        Jwts.builder().setSubject(email).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+1000*60*60*10)).signWith(Keys.hmacShaKeyFor(secrete.getBytes())).compact();

    }
}
