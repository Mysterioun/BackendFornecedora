package br.ufsm.sci.pi.JWT;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;


    Claims claims = null;
    private String userName = null;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String url = request.getRequestURI();


        try {

            if (!url.contains("login")) {

                String authorizationHeader = request.getHeader("Authorization");
                String token = null;

                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

                    token = authorizationHeader.substring(7);
                    userName = jwtUtil.extractUsername(token);
                    claims = jwtUtil.extractAllClaims(token);
                }


                System.out.println("Token: " + token);
                String username = new JwtUtil().getUsernameToken(token);
                System.out.println("username: " + username);
                System.out.println("Token expirado? " + new JwtUtil().isTokenExpirado(token));

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                    if (!new JwtUtil().isTokenExpirado(token)) {

                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);

                    }

                }

            }

            filterChain.doFilter(request, response);


        } catch (ExpiredJwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Expirado");
        } catch (AccessDeniedException e) {
            System.out.println("caiu no AccessDanieExcption");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Expirado");
        }

    }

    public boolean isAdmin() {
        return "admin".equalsIgnoreCase((String) claims.get("role"));
    }


    public boolean isCliente() {
        return "cliente".equalsIgnoreCase((String) claims.get("role"));
    }

    public String getCurrentUser() {
        return userName;
    }
}

