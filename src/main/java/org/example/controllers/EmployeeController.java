package org.example.controllers;

import io.swagger.annotations.Api;
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

    EmployeeService employeeService;

    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @POST
    @Path("/delivery")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDeliveryEmployee(
            final EmployeeRequest employeeRequest) {

        try {
            return Response.ok().
                    entity(employeeService.
                            createEmployee(employeeRequest)).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().build();
        } catch (InvalidException e) {
            return Response.status(Response.Status.BAD_REQUEST).
                    entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/sales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSalesEmployee(
            final SalesEmployeeRequest salesEmployeeRequest) {

        try {
            return Response.ok().
                    entity(employeeService.
                            createEmployee(salesEmployeeRequest)).build();
        } catch (SQLException | FailedToCreateException e) {
            return Response.serverError().build();
        } catch (InvalidException e) {
            return Response.status(Response.Status.BAD_REQUEST).
                    entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/delivery")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() {
        try {
            return Response.ok().
                    entity(employeeService.getAllEmployees()).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/sales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSalesEmployees() {
        try {
            return Response.ok().
                    entity(employeeService.getAllSalesEmployees()).build();
        } catch (SQLException e) {
            return Response.serverError().build();
        }
    }
}
