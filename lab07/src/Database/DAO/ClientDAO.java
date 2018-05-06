package Database.DAO;

import Database.Entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private DbConnection dbConnection;

    public ClientDAO() {
        this.dbConnection = new DbConnection();
    }

    public ClientDAO(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<Client> getEntities() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String statement = "SELECT * FROM clients";

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
            clients.add(new Client(resultSet.getInt("id_client"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    resultSet.getString("identity_number"),
                    resultSet.getString("phone_number")
            ));

        preparedStatement.close();
        return clients;
    }

    public Client saveEntity(Client entity) throws SQLException {
        String statement = "INSERT INTO clients (id_client, first_name, last_name, email, identity_number, " +
                "phone_number) VALUES (?, ?, ?, ?, ?, ?)";

        entity.setId_client(getFreeId());

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
        preparedStatement.setInt(1, entity.getId_client());
        preparedStatement.setString(2, entity.getFirst_name());
        preparedStatement.setString(3, entity.getLast_name());
        preparedStatement.setString(4, entity.getEmail());
        preparedStatement.setString(5, entity.getIdentity_number());
        preparedStatement.setString(6, entity.getPhone_number());

        preparedStatement.execute();

        preparedStatement.close();
        return entity;
    }

    private int getFreeId() throws SQLException {
        String statement = "SELECT MAX(id_client) from clients";

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.first();
        int maxID = resultSet.getInt(1);
        return maxID+1;
    }


    public Boolean updateEntity(Client entity) throws SQLException {
        String statement = "UPDATE clients SET first_name=?, " +
                "last_name=?, email=?, identity_number=?, phone_number=? WHERE id_client=?";

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
        preparedStatement.setString(1, entity.getFirst_name());
        preparedStatement.setString(2, entity.getLast_name());
        preparedStatement.setString(3, entity.getEmail());
        preparedStatement.setString(4, entity.getIdentity_number());
        preparedStatement.setString(5, entity.getPhone_number());

        preparedStatement.setInt(6, entity.getId_client());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        return true;
    }

    public Client getEntityById(int id) throws SQLException {
        String statement = "SELECT * FROM clients WHERE id_client=?";

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        Client client = new Client(resultSet.getInt("id_client"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getString("identity_number"),
                resultSet.getString("phone_number"));

        preparedStatement.close();
        return client;
    }

    public Boolean deleteEntityById(int id) throws SQLException {
        String statement = "DELETE FROM clients WHERE id_client=?";

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
        preparedStatement.setInt(1, id);

        Boolean methodSucceeded = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return methodSucceeded;
    }
}
