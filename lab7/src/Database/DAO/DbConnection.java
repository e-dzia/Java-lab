package Database.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    private static String url = "jdbc:mysql://localhost:3306/hoteljava";
    private Connection connection;

    public DbConnection() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password","");

        connection = DriverManager.getConnection(url,connectionProps);
        connection.setAutoCommit(true); //TODO: zmienić?
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (!connection.isClosed())
            connection.close();
    }
}
