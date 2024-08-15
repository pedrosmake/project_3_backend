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

    private static final Logger logger1 = LogManager.getLogger();

    EmployeeService employeeService;

    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
        logger1.info("Employee controller initialized");
    }

    @POST
    @Path("/delivery")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDeliveryEmployee(
            final EmployeeRequest employeeRequest) {
        logger1.info("createDeliveryEmployee request received");
        try {
            return Response.ok().
                    entity(employeeService.
                            createEmployee(employeeRequest)).build();
        } catch (SQLException e) {
            logger1.error("createDeliveryEmployee request SQL failed");

            return Response.serverError().build();
        } catch (InvalidException e) {
            logger1.error("createDeliveryEmployee request Invalid Exception");
            return Response.status(Response.Status.BAD_REQUEST).
                    entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/sales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSalesEmployee(
            final SalesEmployeeRequest salesEmployeeRequest) {
        logger1.info("createSalesEmployee request received");
        try {
            return Response.ok().
                    entity(employeeService.
                            createEmployee(salesEmployeeRequest)).build();
        } catch (SQLException | FailedToCreateException e) {
            logger1.error("createSalesEmployee SQL failed to create");
            return Response.serverError().build();
        } catch (InvalidException e) {
            logger1.error("crateSalesEmployee request Invalid exception");
            return Response.status(Response.Status.BAD_REQUEST).
                    entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/delivery")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() {
        logger1.info("getAllEmployees request received");
        try {
            return Response.ok().
                    entity(employeeService.getAllEmployees()).build();
        } catch (SQLException e) {
            logger1.error("getAllEmployees request SQL failed");
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/sales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSalesEmployees() {
        logger1.info("getAllSalesEmployees request received");
        try {
            return Response.ok().
                    entity(employeeService.getAllSalesEmployees()).build();
        } catch (SQLException e) {
            logger1.error("getAllSalesEmployees request SQL failed");
            return Response.serverError().build();
        }
    }
}
