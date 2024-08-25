package sqlite;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteLoader {
    private final String connectionString = "jdbc:sqlite:db/database.db";
    protected Connection connection = null;

    public void connect() {
        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean tableExists(String tableName) {
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet resultSet = meta.getTables(null, null, tableName, new String[]{"TABLE"});
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTable(String tableName) {
        if (tableExists(tableName)) {
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate("DELETE FROM " + tableName);
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
