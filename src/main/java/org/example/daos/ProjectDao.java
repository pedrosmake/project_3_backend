package org.example.daos;

import org.example.models.Employee;
import org.example.models.Project;
import org.example.models.ProjectRequest;
import org.example.models.ProjectStatus;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class ProjectDao {

    public static final Integer ID_1 = 1;
    public static final Integer ID_2 = 2;
    public static final Integer ID_3 = 3;
    public static final Integer ID_4 = 4;
    public static final Integer ID_5 = 5;

    public Project getProjectByID(final int id) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM Project WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return new Project(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("value"),
                        ProjectStatus.valueOf(resultSet.getString("status"))
                );
            }
        }
        return null;
    }

    public int createProject(final ProjectRequest projectRequest)
            throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        String insertStatement =
                "INSERT INTO Project "
                        + "(name, value, status, client_id, techlead_id)"
                        + " VALUES (?,?,?,?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement,
                Statement.RETURN_GENERATED_KEYS);

        st.setString(ID_1, projectRequest.getName());
        st.setDouble(ID_2, projectRequest.getValue());
        st.setString(ID_3, projectRequest.getStatus().toString());
        st.setInt(ID_4, projectRequest.getClientId());
        st.setInt(ID_5, projectRequest.getTechleadId());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

    public void updateProject(final int id, final ProjectStatus status)
            throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        String updateStatement = "UPDATE Project SET Status = ? WHERE id = ?";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setString(ID_1, String.valueOf(status));
        st.setInt(ID_2, id);

        st.executeUpdate();
    }

    public void removeEmployee(final int employeeID, final int projectID)
            throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        String updateEndDate = "UPDATE Project_Employee SET"
                + " end_date = ? WHERE employee_id = ? AND project_id = ?";

        PreparedStatement st = c.prepareStatement(updateEndDate);

        st.setDate(ID_1, Date.valueOf(LocalDate.now()));
        st.setInt(ID_2, employeeID);
        st.setInt(ID_3, projectID);

        st.executeUpdate();
    }

    public void addEmplyee(final List<Employee> employeeList,
                           final int projectID) throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        for (Employee employee : employeeList) {
            String updateEmployeeList =
                    "INSERT INTO Project_Employee "
                            + "(project_id, employee_id, start_date)"
                            + " VALUES (?, ?, ?)";

            PreparedStatement st = c.prepareStatement(updateEmployeeList);

            st.setInt(ID_1, projectID);
            st.setInt(ID_2, employee.getId());
            st.setDate(ID_3, Date.valueOf(LocalDate.now()));

            st.executeUpdate();
        }

    }
}
