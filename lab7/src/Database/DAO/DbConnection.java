package Database.DAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    private static String url = "jdbc:mysql://localhost:3306/hoteljava";
    private Connection connection;

    public DbConnection(){
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password","");

        try {
            connection = DriverManager.getConnection(url,connectionProps);
            connection.setAutoCommit(true); //TODO: zmienić?
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Nie udalo sie polaczyc z baza danych.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Wystapil blad.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (!connection.isClosed())
            connection.close();
    }
}
