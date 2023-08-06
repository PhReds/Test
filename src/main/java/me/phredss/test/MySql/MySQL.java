package me.phredss.test.MySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private String host = "localhost";
    private String port = "3306"; //3308
    private String database = "phredssplugins";
    private String username = "root";
    private String password = "";

    private Connection connection;

    //Controllo se è connesso
    public boolean isConnected() {
        return (connection == null ? false : true);
    }

    //Connessione
    public void connect() throws ClassNotFoundException, SQLException {
        if (!isConnected()) {
            connection = DriverManager.getConnection("jdbc:mysql://" +
                            host + ":" + port + "/" + database + "?useSSL=false",
                    username, password);
        }
    }

    //Discconnessione
    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
