package com.apontamento.apontamentohorasapi.controller;

import com.apontamento.apontamentohorasapi.controller.form.LoginForm;
import com.apontamento.apontamentohorasapi.model.User;
import com.apontamento.apontamentohorasapi.repository.UserRepository;
import com.apontamento.apontamentohorasapi.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository users;

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody @Valid LoginForm data) {

        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            Optional<User> usuario = this.users.findByUsername(username);
            String token = jwtTokenProvider.createToken(username, usuario
                    .orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());

            Map<Object, Object> model = new HashMap<>();
            model.put("id", usuario.get().getId());
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
