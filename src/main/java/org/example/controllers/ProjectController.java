package org.example.controllers;

import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.exceptions.DoesNotExistException;
import org.example.models.Employee;
import org.example.models.ProjectRequest;
import org.example.models.ProjectStatusRequest;
import org.example.services.ProjectService;


import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Api("Project")
@Path("/api/projects")
public class ProjectController {

    private static final Logger logger1 = LogManager.getLogger();

    ProjectService projectService;

    public ProjectController(final ProjectService projectService) {
        this.projectService = projectService;
        logger1.info("Project controller initialized");
    }

    @GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjectById(
            @PathParam("productId") final int productId) {
        logger1.info("getProjectbyId request received");
        try {
            return Response.ok()
                    .entity(projectService.getProductById(productId)).build();
        } catch (SQLException e) {
            logger1.error("getProjectById request SQL failed");
            return Response.serverError().build();
        } catch (DoesNotExistException e) {
            logger1.error("GetProjectById DoesNotExistException");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProject(final ProjectRequest projectRequest) {
        logger1.info("createProject request received");
        try {
            return Response
                    .status(Response.Status.CREATED)
                    .entity(projectService.createProject(projectRequest))
                    .build();

        } catch (SQLException e) {
            logger1.error("createProject request SQL Failed");
            return Response.serverError().build();

        }
//        catch (InvalidException e) {
//            return Response.status(Response.Status.BAD_REQUEST)
//            .entity(e.getMessage()).build();
//        }
    }

    @PUT
    @Path("/{id}/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProject(final @PathParam("id") int id,
                                  final ProjectStatusRequest status) {
        logger1.info("updateProject request received");
        try {
            projectService.setStatus(id, status);
            return Response.noContent().build();
        }  catch (SQLException e) {
            logger1.error("updateProject request SQL failed");
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{projectID}/employee/{employeeID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeEmployee(
            final @PathParam("projectID") int projectID,
            final @PathParam("employeeID") int employeeID) {
        logger1.info("removeEmployee request received");
        try {
            projectService.removeEmployee(projectID, employeeID);
            return Response.noContent().build();
        } catch (SQLException e) {
            logger1.error("removeEmployee request SQL failed");
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{projectID}/employees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEmployee(
            final @PathParam("projectID") int projectID,
            final List<Employee> employeeList) {
        logger1.info("addEmployee request received");
        try {
            projectService.addEmployee(projectID, employeeList);
            return Response.noContent().build();
        } catch (SQLException e) {
            logger1.error("addEmployee request SQL failed");
            return Response.serverError().build();
        }
    }
}
