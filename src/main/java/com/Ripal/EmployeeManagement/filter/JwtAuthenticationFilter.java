package com.Ripal.EmployeeManagement.filter;

import com.Ripal.EmployeeManagement.configuration.JwtHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    @Lazy
    private UserDetailsService userDetailsService;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChan) throws ServletException, IOException {
        String requestHeader = request.getHeader("Authorization");
        logger.info("Header : {}", requestHeader);

        String userName = null;
        String token = null;

        if(requestHeader != null && requestHeader.startsWith("Bearer")){
            token = requestHeader.substring(7);

            try{
                userName = this.jwtHelper.getUserNameFormToken(token);
            }catch(IllegalArgumentException e){
                logger.info("Illegal Argument while fetching the username");
                e.printStackTrace();
            }catch(ExpiredJwtException e){
                logger.info("Token is expired");
                e.printStackTrace();
            }catch(MalformedJwtException e){
                logger.info("There is issue in token, may be changed something");
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            logger.info("Invalid header value");
        }

        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
            Boolean validateToken = this.jwtHelper.validateToken(token, userName);

            if(Boolean.TRUE.equals(validateToken)){
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                logger.info("validation fails");
            }
        }
        filterChan.doFilter(request, response);
    }

}
