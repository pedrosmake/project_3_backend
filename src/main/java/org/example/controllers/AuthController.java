package org.example.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.services.AuthService;
import org.example.models.LoginRequest;
import org.example.models.RegisterRequest;
import org.example.exceptions.InvalidException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Auth API")
@Path("/api/auth")
@SwaggerDefinition(
        securityDefinition = @SecurityDefinition(
                apiKeyAuthDefinitions = {
                        @ApiKeyAuthDefinition(
                                key = HttpHeaders.AUTHORIZATION,
                                name = HttpHeaders.AUTHORIZATION,
                                in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER
                        )
                }
        )
)
public class AuthController {

    private final AuthService authService;
    private static final Logger LOGGER = LogManager.getLogger();

    public AuthController(final AuthService authService) {
        this.authService = authService;
        LOGGER.info("AuthController initialized");
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(final LoginRequest loginRequest) {

        try {
            LOGGER.info("Login request received");
            return Response.ok().entity(authService.login(loginRequest))
                    .build();
        } catch (SQLException e) {
            LOGGER.error("Login SQL exception");
            LOGGER.error(e.getMessage());
            return Response.serverError().build();
        } catch (InvalidException e) {
            LOGGER.error("Login InvalidException");
            LOGGER.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(final RegisterRequest registerRequest) {
        try {
            LOGGER.info("Register request received");
            authService.register(registerRequest);
            return Response.ok().build();
        } catch (InvalidException e) {
            LOGGER.error("Register InvalidException");
            LOGGER.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (SQLException e) {
            LOGGER.error("Register SQL exception");
            LOGGER.error(e.getMessage());
            return Response.serverError().build();
        }
    }

}
