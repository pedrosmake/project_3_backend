package org.example.controllers;

import io.swagger.annotations.Api;
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
    ClientService clientService;

    public ClientController(final ClientService clientService) {
        this.clientService = clientService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClients() {
        try {
            return Response.ok().entity(clientService.getAllClients()).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/top")
    @Produces(MediaType.APPLICATION_JSON)
    public Response topClient() {
        try {
            return Response.ok().entity(clientService.getTopClient()).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }
}
