package dev.rafaelbarragan.api.finanza.security;

import dev.rafaelbarragan.api.finanza.domain.user.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    public SecurityFilter(TokenService tokenService, UserService userService){
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null) {
            token = token.replace("Bearer", "").trim();
            String subject = tokenService.getSubject(token);
            UserDetails userDetails = userService.findUserDetail(subject);
            var autehtication = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autehtication);
        }
        filterChain.doFilter(request, response);
    }
}
