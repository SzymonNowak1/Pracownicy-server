package pl.szynow.workers.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szynow.workers.configuration.ErrorEntityPreparator;
import pl.szynow.workers.controller.TokenController;
import pl.szynow.workers.entity.Role;
import pl.szynow.workers.entity.Token;
import pl.szynow.workers.entity.User;
import pl.szynow.workers.exception.InvalidUsernamePasswordException;
import pl.szynow.workers.model.ErrorResponse;
import pl.szynow.workers.model.TokenResponse;
import pl.szynow.workers.model.UserResponse;
import pl.szynow.workers.service.TokenService;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@RestController
public class TokenControllerImpl implements TokenController {

    @Autowired
    private ErrorEntityPreparator errorEntity;

    @Autowired
    private TokenService tokenService;

    @Override
    public ResponseEntity<TokenResponse> token(final String username, final String password) {
        Token token = tokenService.getToken(username, password);
        TokenResponse response = new TokenResponse(
                token.getUser().getUsername(),
                token.getToken(),
                DateTimeFormatter.ISO_DATE_TIME.format(token.getExpires()),
                token.getUser().getRoles().stream().map(Role::getName).collect(Collectors.toList())
                );
        return new ResponseEntity<>( response, HttpStatus.OK );
    }

    @Override
    public ResponseEntity<UserResponse> current(User user) {
        UserResponse userBody = new UserResponse(user.getUsername(), user.getEmail(), user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));

        return new ResponseEntity<>( userBody, HttpStatus.OK );
    }

    @ExceptionHandler( { InvalidUsernamePasswordException.class } )
    public ResponseEntity<ErrorResponse> handleInvalidUsernamePasswordException( Exception ex ) {
        return errorEntity.prepare( HttpStatus.BAD_REQUEST, ex );
    }

}
