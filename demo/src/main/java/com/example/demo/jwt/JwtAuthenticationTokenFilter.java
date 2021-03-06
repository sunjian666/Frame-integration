/*
package com.example.demo.jwt;

import com.example.demo.exception.CheckVerifyCodeException;
import com.example.demo.util.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter{

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = httpServletRequest.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            final String authToken = authHeader.substring(tokenHead.length());
            String useraccount = jwtUtil.getUserAccountFromToken(authToken);
            logger.info("JwtAuthenticationTokenFilter[doFilterInternal] checking authentication " + useraccount);

            if (useraccount != null && SecurityContextHolder.getContext().getAuthentication() == null){

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(useraccount);

                if(jwtUtil.validateToken(authToken, userDetails)){
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    logger.info("JwtAuthenticationTokenFilter[doFilterInternal]  authenticated user " + useraccount + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

                */
/*if (!CodeUtil.checkVerifyCode(httpServletRequest)){
                    throw new CheckVerifyCodeException();
                }*//*


            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
*/
