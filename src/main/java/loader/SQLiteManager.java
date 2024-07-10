package loader;
import item.Item;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteManager {
    private final String connectionString;
    private Connection connection=null;
    public SQLiteManager(String url) {
        connectionString = "jdbc:sqlite:db/"+url;
    }
    public void connect() {
        try {
            connection = DriverManager.getConnection(connectionString);
            System.out.println("Connection to SQLite has been established.");
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
    public void createTable(String tableName) {
        if(!tableExists(tableName)) {
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate("create table " + tableName + " (id int primary key, name VARCHAR,Phy REAL, VsStr REAL, VsSla REAL, VsPie REAL, Mag REAL, Fir REAL, Lit REAL, Hol REAL, Imm REAL, Rob REAL, Foc REAL, Vit REAL, Poi REAL, Wgt REAL)");
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void loadIntoTable(String tableName, List<Item> items) {
        createTable(tableName);
        for(Item item : items) {
            try {
                PreparedStatement itemInsertion = connection.prepareStatement("insert into "+tableName+" Values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                itemInsertion.setString(1,item.name);
                for(int i = 0;i<item.stats.length;i++) {
                    itemInsertion.setFloat(i+2,item.stats[i]);
                }
                itemInsertion.executeUpdate();
                itemInsertion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
