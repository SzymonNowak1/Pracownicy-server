package pl.szynow.workers.service;

import pl.szynow.workers.entity.User;
import pl.szynow.workers.exception.*;

import java.util.Collection;

public interface UserService {

    User getById( Long id ) throws UserNotFoundException;

    User getByUsername( String username ) throws UserNotFoundException;

    boolean isUsernameInUse( String username );

    boolean isEmailInUse( String email );

    boolean isUsernamePasswordCorrect( String username, String password );

    User registerUser( String username, String password, String email ) throws UserAlreadyExistsException, UserEmailAlreadyExistsException;

    User activateUser( String username, String code ) throws UserNotFoundException, UserActivateCodeNotCorrectException, UserAlreadyActivatedException;

    User activateUser( String username ) throws UserNotFoundException, UserAlreadyActivatedException;

    User addRoles( String username, Collection<String> roles ) throws UserNotFoundException, RoleNotFoundException;

    User removeRoles( String username, Collection<String> roles ) throws UserNotFoundException, RoleNotFoundException;

}
