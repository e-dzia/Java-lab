package Database.DAO;


import Database.Entities.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private DbConnection dbConnection;

    public RoomDAO() {
        try {
            this.dbConnection = new DbConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RoomDAO(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<Room> getEntities() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String statement = "SELECT * FROM rooms";

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
            rooms.add(new Room(resultSet.getInt("id_room"),
                    resultSet.getBoolean("is_open"),
                    resultSet.getInt("price"),
                    resultSet.getString("type")
            ));

        preparedStatement.close();
        return rooms;
    }

    public Room saveEntity(Room entity) throws SQLException {
        String statement = "INSERT INTO rooms (id_room, type, price, is_open" +
                ") VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
        preparedStatement.setInt(1, entity.getId_room());
        preparedStatement.setString(2, entity.getType());
        preparedStatement.setInt(3, entity.getPrice());
        preparedStatement.setBoolean(4, entity.isIs_open());

        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next())
            entity.setId_room(resultSet.getInt(1));
        else
            entity = null;

        preparedStatement.close();
        return entity;
    }


    public Boolean updateEntity(Room entity) throws SQLException {
        String statement = "UPDATE rooms SET 'type'=?, " +
                "'price'=?, 'is_open'=? WHERE 'id_room'=?";

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
        preparedStatement.setString(1, entity.getType());
        preparedStatement.setInt(2, entity.getPrice());
        preparedStatement.setBoolean(3, entity.isIs_open());

        preparedStatement.setInt(4, entity.getId_room());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        return true;
    }

    public Room getEntityById(int id) throws SQLException {
        String statement = "SELECT * FROM rooms WHERE id_room=?";

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        Room room = new Room(resultSet.getInt("id_room"),
                resultSet.getBoolean("is_open"),
                resultSet.getInt("price"),
                resultSet.getString("type"));

        preparedStatement.close();
        return room;
    }

    public Boolean deleteEntityById(int id) throws SQLException {
        String statement = "DELETE FROM rooms WHERE id_room=?";

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
        preparedStatement.setInt(1, id);

        Boolean methodSucceeded = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return methodSucceeded;
    }
}
