package org.example.services;

import io.jsonwebtoken.Jwts;
import org.example.daos.AuthDao;
import org.example.exceptions.Entity;
import org.example.exceptions.InvalidException;
import org.example.models.LoginRequest;
import org.example.models.RegisterRequest;
import org.example.models.User;
import org.example.validators.RegisterValidator;


import java.security.Key;
import java.sql.SQLException;
import java.util.Date;

public class AuthService {

    private final static int JWT_EXPIRATION_MILLIS = 28_800_000;
    private final AuthDao authDao;
    private final RegisterValidator registerValidator;
    private final Key key;

    public AuthService(AuthDao authDao, RegisterValidator registerValidator, Key key) {
        this.authDao = authDao;
        this.registerValidator = registerValidator;
        this.key = key;
    }

    public String login(LoginRequest loginRequest)
            throws SQLException, InvalidException {
        User user = authDao.getUser(loginRequest);

        if (user == null) {
            throw new InvalidException(Entity.USER, "Invalid credentials");
        }

        return generateJwtToken(user);
    }

    private String generateJwtToken(User user) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .issuedAt(new Date(currentTimeMillis))
                .expiration(new Date(currentTimeMillis + JWT_EXPIRATION_MILLIS))
                .claim("Role", user.getRoleId())
                .subject(user.getUsername())
                .issuer("Tails")
                .signWith(key)
                .compact();
    }

    public void register(RegisterRequest registerRequest)
            throws InvalidException, SQLException {
        registerValidator.validate(registerRequest);
        authDao.register(registerRequest);
    }
}
