package dev.rafaelbarragan.api.finanza.controller;

import dev.rafaelbarragan.api.finanza.domain.user.dto.UserAutenticar;
import dev.rafaelbarragan.api.finanza.domain.user.dto.UserCreate;
import dev.rafaelbarragan.api.finanza.domain.user.dto.UserResponse;
import dev.rafaelbarragan.api.finanza.domain.user.entity.User;
import dev.rafaelbarragan.api.finanza.domain.user.service.UserService;
import dev.rafaelbarragan.api.finanza.security.TokenDTO;
import dev.rafaelbarragan.api.finanza.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/login")
@Transactional
public class LoginController {

    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager manager;

    @Autowired
    public LoginController(UserService userService, TokenService tokenService, AuthenticationManager manager){
        this.userService = userService;
        this.tokenService = tokenService;
        this.manager = manager;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserCreate create, UriComponentsBuilder builder){
        UserResponse response = userService.create(create);
        URI uri = builder.path("/user/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid UserAutenticar autenticar){
        Authentication authToken = new UsernamePasswordAuthenticationToken(autenticar.userName(), autenticar.clave());
        Authentication userAutenticado = manager.authenticate(authToken);
        String JWTtoken = tokenService.generarToken((User) userAutenticado.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(JWTtoken));
    }

}
