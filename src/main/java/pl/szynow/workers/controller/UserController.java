package pl.szynow.workers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szynow.workers.configuration.CurrentlyLoggedUser;
import pl.szynow.workers.entity.User;
import pl.szynow.workers.model.UserBody;
import pl.szynow.workers.model.UserResponse;

import javax.validation.Valid;


@RequestMapping( "/${security.endpoint.users.prefix}" )
public interface UserController {

    @PostMapping( "/register" )
    ResponseEntity<Object> register(@RequestBody @Valid UserBody user);

    @PostMapping( "/activate" )
    ResponseEntity<Object> activate(@RequestParam("code") String code, @CurrentlyLoggedUser User user );

    @GetMapping( "/roles" )
    ResponseEntity<UserResponse> getRoles(@RequestParam("username") String username );

    @PostMapping( "/roles" )
    ResponseEntity<UserResponse> addRoles(@RequestParam("username") String username, @RequestParam("roles") String roles );

    @DeleteMapping( "/roles" )
    ResponseEntity<UserResponse> removeRoles(@RequestParam("username") String username, @RequestParam("roles") String roles );

}
