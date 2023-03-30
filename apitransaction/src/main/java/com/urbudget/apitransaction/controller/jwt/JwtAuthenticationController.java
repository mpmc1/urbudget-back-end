package com.urbudget.apitransaction.controller.jwt;

import com.urbudget.apitransaction.domain.person.Person;
import com.urbudget.apitransaction.domain.jwt.JwtRequest;
import com.urbudget.apitransaction.domain.jwt.JwtResponse;
import com.urbudget.apitransaction.jwt.JwtTokenUtil;
import com.urbudget.apitransaction.service.JwtUserDetailsService;
import com.urbudget.apitransaction.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws CustomException {

        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getUsername());

            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }

    }

    @PostMapping("/register")
    public Person savePerson(@RequestBody Person person)  {
        return userDetailsService.save(person);
    }

    private void authenticate(String username, String password) throws CustomException {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new CustomException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new CustomException("INVALID_CREDENTIALS", e);
        }
    }
}

