package org.example.services;

import org.example.daos.ProjectDao;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.Entity;
import org.example.models.Employee;
import org.example.models.Project;
import org.example.models.ProjectRequest;
import org.example.models.ProjectStatusRequest;

import java.sql.SQLException;
import java.util.List;

public class ProjectService {
    ProjectDao projectDao;

    public ProjectService(final ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public int createProject(final ProjectRequest projectRequest)
            throws SQLException {
        //zwalidowac

        int id = projectDao.createProject(projectRequest);

//        if(id == -1){
//            throw new FailedToCreateException();
//        }
        return id;
    }

    public void setStatus(final int id, final ProjectStatusRequest status)
            throws SQLException {
        Project projectToUpdate = projectDao.getProjectByID(id);

//        do zrobienia
//        if(projectToUpdate == null) {
//            throw new DoesNotExistException();
//        }

        projectDao.updateProject(id, status.getProjectStatus());
    }

    public void removeEmployee(final int projectID, final int employeeID)
            throws SQLException {
        Project projectToRemove = projectDao.getProjectByID(projectID);
//        if (projectToRemove == null) {
//            throw new DoesNotExistException(Entity.PRODUCT);
//        }

        projectDao.removeEmployee(employeeID, projectID);
    }

    public void addEmployee(final int projectID,
                            final List<Employee> employeeList)
            throws SQLException {
        Project projectToUpdate = projectDao.getProjectByID(projectID);
//        if (projectToUpdate == null) {
//            throw new DoesNotExistException(Entity.PROJECT);
//        }
        projectDao.addEmplyee(employeeList, projectID);

    }

    public Project getProductById(int productId)
            throws SQLException, DoesNotExistException {
        Project project = projectDao.getProjectByID(productId);
        if (project == null){
            throw new DoesNotExistException(Entity.PROJECT);
        }
        return project;
    }
}
