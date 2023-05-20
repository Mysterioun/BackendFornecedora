package br.ufsm.sci.pi.controller;

import br.ufsm.sci.pi.JWT.JwtUtil;
import br.ufsm.sci.pi.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<Object> autenticacao(@RequestBody Usuario usuario){

        System.out.println("email: "+usuario.getEmail());
        System.out.println("senha: "+usuario.getSenha());
        try{
            final Authentication authentication = this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha()));

            if(authentication.isAuthenticated()){
                //colocamos nossa instancia autenticada no contexto do spring security
                SecurityContextHolder.getContext().setAuthentication(authentication);

                System.out.println("Gerando token de autorizacao ****");
                String token = new JwtUtil().geraToken(usuario);

                usuario.setToken(token);
                usuario.setSenha("");

                return new ResponseEntity<>(usuario, HttpStatus.OK);
            }

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Usuário ou senha incorretos!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Usuário ou senha incorretos!", HttpStatus.BAD_REQUEST);
    }
}
