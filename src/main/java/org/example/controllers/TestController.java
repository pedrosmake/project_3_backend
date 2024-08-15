package org.example.controllers;

import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.services.TestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Test API")
@Path("/api/test")
public class TestController {

    private static final Logger logger = LogManager.getLogger();

    TestService testService;

    public TestController(final TestService testService) {
        this.testService = testService;
        logger.info("TestController initialized");
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response testConnection() {
        logger.info("testConnection request received");
        try {
            return Response.ok().entity(testService.testConnection()).build();
        } catch (SQLException e) {
            logger.error("testConnection SQL failed");
            return Response.serverError().build();
        }
    }
}
