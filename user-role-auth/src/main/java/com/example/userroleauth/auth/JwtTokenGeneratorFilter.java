package com.example.userroleauth.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.stream.Collectors;

@Deprecated
public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String jwtToken = Jwts.builder().setIssuer("Yaka Creation").setSubject("JWT Token")
                    .claim("username", authentication.getName())
                    .claim("authorities", getAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + JwtUtil.TOKEN_VALIDITY))
                    .signWith(SignatureAlgorithm.HS256, JwtUtil.JWT_SECRET)
                    .compact();
            response.setHeader(JwtUtil.TOKEN_HEADER, jwtToken);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/auth/authentication");
    }

    private String getAuthorities(Collection<? extends GrantedAuthority> authorities) {
        HashSet<String> authoritiesSet = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toCollection(HashSet::new));
        return String.join(",", authoritiesSet);
    }
}
