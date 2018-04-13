package Database.DAO;

import Database.Entities.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private DbConnection dbConnection;

    public EmployeeDAO() {
        try {
            this.dbConnection = new DbConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public EmployeeDAO(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<Employee> getEntities() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String statement = "SELECT * FROM employees";

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
            employees.add(new Employee(resultSet.getInt("id_employee"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    resultSet.getString("salt"),
                    resultSet.getString("hash_code")
            ));

        preparedStatement.close();
        return employees;
    }

    public Employee saveEntity(Employee entity) throws SQLException {
        String statement = "INSERT INTO employees (id_employee, first_name, last_name, email, salt, " +
                "hash_code) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
        preparedStatement.setInt(1, entity.getId_employee());
        preparedStatement.setString(2, entity.getFirst_name());
        preparedStatement.setString(3, entity.getLast_name());
        preparedStatement.setString(4, entity.getEmail());
        preparedStatement.setString(5, entity.getSalt());
        preparedStatement.setString(6, entity.getHash_code());

        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next())
            entity.setId_employee(resultSet.getInt(1));
        else
            entity = null;

        preparedStatement.close();
        return entity;
    }


    public Boolean updateEntity(Employee entity) throws SQLException {
        String statement = "UPDATE employees SET 'first_name'=?, " +
                "'last_name'=?, 'email'=?, 'salt'=?, 'hash_code'=? WHERE 'id_employee'=?";

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
        preparedStatement.setString(1, entity.getFirst_name());
        preparedStatement.setString(2, entity.getLast_name());
        preparedStatement.setString(3, entity.getEmail());
        preparedStatement.setString(4, entity.getSalt());
        preparedStatement.setString(5, entity.getHash_code());

        preparedStatement.setInt(6, entity.getId_employee());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        return true;
    }

    public Employee getEntityById(int id) throws SQLException {
        String statement = "SELECT * FROM employees WHERE id_employee=?";

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        Employee employee = new Employee(resultSet.getInt("id_employee"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getString("salt"),
                resultSet.getString("hash_code"));

        preparedStatement.close();
        return employee;
    }

    public Boolean deleteEntityById(int id) throws SQLException {
        String statement = "DELETE FROM employees WHERE id_employee=?";

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
        preparedStatement.setInt(1, id);

        Boolean methodSucceeded = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return methodSucceeded;
    }
}
