package com.taskfusion.taskApp.config;

import com.taskfusion.taskApp.Utility.JwtToken;
import com.taskfusion.taskApp.models.UserTbl;
import com.taskfusion.taskApp.services.GetUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HttpServletBean;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    //    @Autowired
//    private  JwtToken _jwtToken;
    @Autowired
    private GetUserDetails _getUserDetails;
    private final String secretKey = "5171488efc1d8a4f2f07edc493851cbeb6adf7238e8e46964f04c8216116de87"; // Replace with your actual secret key
    //    private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
    private final Key key =  Keys.hmacShaKeyFor(secretKey.getBytes());//JwtToken.getSecurekey();//Keys.secretKeyFor(SignatureAlgorithm.HS256);

//    public JwtAuthenticationFilter(GetUserDetails getUserDetails,JwtToken jwtToken, HandlerExceptionResolver handlerExceptionResolver) {
//        this._jwtToken = jwtToken;
//        this._handlerExceptionResolver = handlerExceptionResolver;
//        this._getUserDetails=getUserDetails;
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String username = claims.getSubject();
                if (username != null) {
                    UserTbl userdetails = _getUserDetails.getUserDetails(username);

                    List<GrantedAuthority> authorities = new ArrayList<>();
                    String role = "Admin";
                    if (role != null) {
                        authorities.add(new SimpleGrantedAuthority("Role_" + role));
                    }
                    Authentication authentication = new UsernamePasswordAuthenticationToken(userdetails, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    //response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } catch (Exception ex) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Error:" + ex.getMessage());
                // return ;
            }
        }
        filterChain.doFilter(request, response);
    }
}
