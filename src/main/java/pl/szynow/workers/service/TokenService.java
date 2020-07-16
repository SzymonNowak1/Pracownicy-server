package pl.szynow.workers.service;

import pl.szynow.workers.entity.Token;
import pl.szynow.workers.exception.InvalidUsernamePasswordException;
import pl.szynow.workers.exception.TokenNotFoundException;

public interface TokenService {

    Token getToken(String username, String password ) throws InvalidUsernamePasswordException;

    Token getToken(String token) throws TokenNotFoundException;

    void purge();

}
