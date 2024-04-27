package com.chanthai.phoneshop.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class TokenVerifyFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader  = request.getHeader("Authorization");
        if (Objects.isNull(authorizationHeader) || !authorizationHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        String token = authorizationHeader.replace("Bearer ", "");
        String secretKey = "asdfaseif@#$%@#$asdfaseif@#$%@#$asdfaseif@#$%@#$";
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        String username = body.getSubject();
        List<Map<String,String>> authorities = (List<Map<String, String>>) body.get("authoreities");
        Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
                .map(x -> new SimpleGrantedAuthority(x.get("authority")))
                .collect(Collectors.toSet());

        Authentication authentication = new UsernamePasswordAuthenticationToken(username,null,grantedAuthorities);
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        filterChain.doFilter(request,response);
    }
}
