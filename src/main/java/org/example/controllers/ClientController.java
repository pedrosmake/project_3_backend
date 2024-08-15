package org.example.controllers;

import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.services.ClientService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;


@Api("Engineering Academy Dropwizard Clients API")
@Path("/api/clients")
public class ClientController {

    private static final Logger logger = LogManager.getLogger();

    ClientService clientService;

    public ClientController(final ClientService clientService) {
        this.clientService = clientService;
        logger.info("ClientController initialized");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClients() {
        logger.info("Get clients request received");
        try {
            return Response.ok().entity(clientService.getAllClients()).build();
        } catch (SQLException e) {
            logger.info("Clients request SQL failed");
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/top")
    @Produces(MediaType.APPLICATION_JSON)
    public Response topClient() {
        logger.info("Get top client request received");
        try {
            return Response.ok().entity(clientService.getTopClient()).build();
        } catch (SQLException e) {
            logger.info("Top client request SQL failed");
            return Response.serverError().build();
        }
    }
}
