package Database.DAO;


import Database.Entities.Reservation;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationDAO {
    private DbConnection dbConnection;

    public ReservationDAO() {
        try {
            this.dbConnection = new DbConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ReservationDAO(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<Reservation> getEntities() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String statement = "SELECT * FROM reservations";

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
            reservations.add(new Reservation(resultSet.getInt("id_reservation"),
                resultSet.getDate("arrival"),
                resultSet.getDate("departure"),
                resultSet.getInt("id_client"),
                resultSet.getInt("id_room"),
                resultSet.getInt("payment"),
                resultSet.getInt("id_employee_create"),
                resultSet.getInt("id_employee_modify")
            ));

        preparedStatement.close();
        return reservations;
    }

    public Reservation saveEntity(Reservation entity) throws SQLException {
        String statement = "INSERT INTO reservations (id_reservation, arrival, departure, payment, id_client, " +
                "id_room, id_employee_create, id_employee_modify) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
        preparedStatement.setInt(1, entity.getId_reservation());
        preparedStatement.setDate(2, (java.sql.Date) entity.getArrival());
        preparedStatement.setDate(3, (java.sql.Date) entity.getDeparture());
        preparedStatement.setInt(4, entity.getPayment());
        preparedStatement.setInt(5, entity.getId_client());
        preparedStatement.setInt(6, entity.getId_room());
        preparedStatement.setInt(7, entity.getId_employee_create());
        preparedStatement.setInt(8, entity.getId_employee_modify());

        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next())
            entity.setId_reservation(resultSet.getInt(1));
        else
            entity = null;

        preparedStatement.close();
        return entity;
    }


    public Boolean updateEntity(Reservation entity) throws SQLException {
        String statement = "UPDATE reservations SET 'arrival'=?, " +
                "'departure'=?, 'payment'=?, 'id_client'=?, 'id_room'=?, 'id_employee_create'=?, " +
                "'id_employee_modify'=? WHERE 'id_reservation'=?";

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
        preparedStatement.setDate(1, (java.sql.Date) entity.getArrival());
        preparedStatement.setDate(2, (java.sql.Date) entity.getDeparture());
        preparedStatement.setInt(3, entity.getPayment());
        preparedStatement.setInt(4, entity.getId_client());
        preparedStatement.setInt(5, entity.getId_room());
        preparedStatement.setInt(6, entity.getId_employee_create());
        preparedStatement.setInt(7, entity.getId_employee_modify());

        preparedStatement.setInt(8, entity.getId_reservation());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        return true;
    }

    public Reservation getEntityById(int id) throws SQLException {
        String statement = "SELECT * FROM reservations WHERE id_reservationt=?";

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        Reservation reservation = new Reservation(resultSet.getInt("id_reservation"),
                resultSet.getDate("arrival"),
                resultSet.getDate("departure"),
                resultSet.getInt("id_client"),
                resultSet.getInt("id_room"),
                resultSet.getInt("payment"),
                resultSet.getInt("id_employee_create"),
                resultSet.getInt("id_employee_modify"));

        preparedStatement.close();
        return reservation;
    }

    public Boolean deleteEntityById(int id) throws SQLException {
        String statement = "DELETE FROM reservations WHERE id_reservation=?";

        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(statement);
        preparedStatement.setInt(1, id);

        Boolean methodSucceeded = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return methodSucceeded;
    }
}
