package sample.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection
{
    Connection connection;

    public Connection getConnection() throws ClassNotFoundException
    {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

        String Url="jdbc:derby:src/sample/storage;create=true";
        try {
            connection= DriverManager.getConnection(Url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
