package pl.szynow.workers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.szynow.workers.configuration.CurrentlyLoggedUser;
import pl.szynow.workers.entity.User;
import pl.szynow.workers.model.TokenResponse;
import pl.szynow.workers.model.UserResponse;

@RequestMapping( "/${security.endpoint.token.prefix}" )
public interface TokenController {

    @PostMapping( "/token" )
    ResponseEntity<TokenResponse> token(@RequestParam("username") final String username, @RequestParam("password") final String password);

    @GetMapping( "/current" )
    ResponseEntity<UserResponse> current(@CurrentlyLoggedUser User user);

}
