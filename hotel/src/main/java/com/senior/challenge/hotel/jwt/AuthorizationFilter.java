package com.senior.challenge.hotel.jwt;

import com.senior.challenge.hotel.rpc.RPCClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthorizationFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        String token = null;
        if (Optional.ofNullable(requestTokenHeader).isPresent()) {
            token = requestTokenHeader.substring(7);
            try {
                RPCClient client = new RPCClient();
                var validJwt = client.call(token);
                if(!Boolean.parseBoolean(validJwt))
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            } catch (TimeoutException | InterruptedException e) {
                e.printStackTrace();
            }

            chain.doFilter(request, response);
        }
    }
}