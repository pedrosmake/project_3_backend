package org.example.controllers;

import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.models.EmployeeRequest;
import org.example.models.SalesEmployeeRequest;
import org.example.services.EmployeeService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Employee Controller")
@Path("/api/employees")
public class EmployeeController {

    private static final Logger logger = LogManager.getLogger();

    EmployeeService employeeService;

    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
        logger.info("Employee controller initialized");
    }

    @POST
    @Path("/delivery")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDeliveryEmployee(
            final EmployeeRequest employeeRequest) {
        logger.info("createDeliveryEmployee request received");
        try {
            return Response.ok().
                    entity(employeeService.
                            createEmployee(employeeRequest)).build();
        } catch (SQLException e) {
            logger.error("createDeliveryEmployee request SQL failed");
            return Response.serverError().build();
        } catch (InvalidException e) {
            logger.error("createDeliveryEmployee request Invalid Exception");
            return Response.status(Response.Status.BAD_REQUEST).
                    entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/sales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSalesEmployee(
            final SalesEmployeeRequest salesEmployeeRequest) {
        logger.info("createSalesEmployee request received");
        try {
            return Response.ok().
                    entity(employeeService.
                            createEmployee(salesEmployeeRequest)).build();
        } catch (SQLException | FailedToCreateException e) {
            logger.error("createSalesEmployee SQL failed to create");
            return Response.serverError().build();
        } catch (InvalidException e) {
            logger.error("crateSalesEmployee request Invalid exception");
            return Response.status(Response.Status.BAD_REQUEST).
                    entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/delivery")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() {
        logger.info("getAllEmployees request received");
        try {
            return Response.ok().
                    entity(employeeService.getAllEmployees()).build();
        } catch (SQLException e) {
            logger.error("getAllEmployees request SQL failed");
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/sales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSalesEmployees() {
        logger.info("getAllSalesEmployees request received");
        try {
            return Response.ok().
                    entity(employeeService.getAllSalesEmployees()).build();
        } catch (SQLException e) {
            logger.error("getAllSalesEmployees request SQL failed");
            return Response.serverError().build();
        }
    }
}
