package pl.szynow.workers.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import pl.szynow.workers.configuration.CurrentlyLoggedUser;
import pl.szynow.workers.configuration.ErrorEntityPreparator;
import pl.szynow.workers.controller.UserController;
import pl.szynow.workers.entity.Role;
import pl.szynow.workers.entity.User;
import pl.szynow.workers.exception.UserActivateCodeNotCorrectException;
import pl.szynow.workers.exception.UserAlreadyActivatedException;
import pl.szynow.workers.exception.UserAlreadyExistsException;
import pl.szynow.workers.exception.UserEmailAlreadyExistsException;
import pl.szynow.workers.model.ErrorResponse;
import pl.szynow.workers.model.UserBody;
import pl.szynow.workers.model.UserResponse;
import pl.szynow.workers.service.FreemarkerService;
import pl.szynow.workers.service.TokenService;
import pl.szynow.workers.service.UserService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserControllerImpl implements UserController {

    @Value("${security.email.verification}")
    private boolean emailVerification;

    @Autowired
    private ErrorEntityPreparator errorEntity;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private MailSender emailSender;

    @Autowired
    private FreemarkerService freemarkerService;

    @Override
    public ResponseEntity<Object> register(UserBody user) {
        User registeredUser = userService.registerUser(user.getUsername(), user.getPassword(), user.getEmail());

        if ( emailVerification ) {
            // send verification email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Kod aktywacyjny");

            String text = freemarkerService.generateActivationMail(user.getUsername(), registeredUser.getGenerated());
            message.setText(text);

            emailSender.send(message);
        } else {
            userService.activateUser( registeredUser.getUsername() );
        }

        return new ResponseEntity<>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Object> activate(String code, User user ) {
        if ( emailVerification ) {
            userService.activateUser(user.getUsername(), code);
        } else {
            return new ResponseEntity<>( HttpStatus.FORBIDDEN );
        }
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @Override
    public ResponseEntity<UserResponse> getRoles(String username ) {
        User user = userService.getByUsername(username);

        UserResponse response = new UserResponse( user.getUsername(), user.getEmail(), user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));

        return new ResponseEntity<>( response, HttpStatus.OK );
    }

    @Override
    public ResponseEntity<UserResponse> addRoles(String username, String roles ) {
        List<String> requestedRoles = Arrays.asList( roles.split("\\s*,\\s*"));

        User user = userService.addRoles(username, requestedRoles);

        UserResponse response = new UserResponse( user.getUsername(), user.getEmail(), user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));

        return new ResponseEntity<>( response, HttpStatus.OK );
    }

    @Override
    public ResponseEntity<UserResponse> removeRoles(String username, String roles ) {
        List<String> requestedRoles = Arrays.asList( roles.split("\\s*,\\s*"));

        User user = userService.removeRoles(username, requestedRoles);

        UserResponse response = new UserResponse( user.getUsername(), user.getEmail(), user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));

        return new ResponseEntity<>( response, HttpStatus.OK );
    }

    @ExceptionHandler( { UserAlreadyExistsException.class, UserEmailAlreadyExistsException.class, UserActivateCodeNotCorrectException.class, UserAlreadyActivatedException.class } )
    public ResponseEntity<ErrorResponse> handleRegisterExceptions( Exception ex ) {
        return errorEntity.prepare( HttpStatus.FORBIDDEN, ex );
    }

}
