package br.ufsm.sci.pi.JWT;

import br.ufsm.sci.pi.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {


    public static final long TEMPO_VIDA = Duration.ofSeconds(400).toMillis();

    private String secret = "mysterioun";

    public String extractUsername(String token){
        return extractClamis(token, Claims::getSubject);
    }


    public <T> T extractClamis(String token, Function<Claims, T> claimsResolver){
        final Claims claims =extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }


    public String generateToken(String username, String role){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return createToken(claims, username);
    }


    private String createToken(Map<String, Object> claims, String subject){

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 600 * 600 * 100))
                .signWith(SignatureAlgorithm.HS256, secret).compact();

    }



    public String getUsernameToken(String token){
        if(token !=null){
            return this.parseToken(token).getSubject();
        }else{
            return null;
        }
    }

    public boolean isTokenExpirado(String token){

        if(token !=null){
            return this.parseToken(token).getExpiration().before(new Date());
        }else{
            return false;
        }
    }

    private Claims parseToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace("Bearer", ""))
                .getBody();
    }

    public String geraToken(Usuario usuario){

        final Map<String, Object> claims = new HashMap<>();
        claims.put("sub", usuario.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+this.TEMPO_VIDA))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

}
