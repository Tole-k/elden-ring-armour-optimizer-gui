package sqlite;

import item.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InventoryLoader extends SQLiteLoader {
    public void createInventory(String tableName) {
        if (!tableExists(tableName + "Inv")) {
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate("create table " + tableName + "Inv" + " (id int primary key not null)");
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addToInventory(String tableName, int id) {
        createInventory(tableName);
        try {
            PreparedStatement idInsertion = connection.prepareStatement("INSERT INTO " + tableName + "Inv" + " VALUES(?)");
            idInsertion.setInt(1, id);
            idInsertion.executeUpdate();
            idInsertion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeFromInventory(String tableName, int id) {
        try {
            PreparedStatement idRemoval = connection.prepareStatement("DELETE FROM " + tableName + "Inv" + " WHERE id=?");
            idRemoval.setInt(1, id);
            idRemoval.executeUpdate();
            idRemoval.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Item> showInventory(String tableName) {
        ArrayList<Item> items = new ArrayList<>();
        createInventory(tableName);
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("select * from " + tableName + "Inv" + " NATURAL JOIN " + tableName)) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                float[] stats = new float[14];
                for (int i = 0; i < resultSet.getMetaData().getColumnCount() - 2; i++) {
                    stats[i] = (float) Math.round(resultSet.getFloat(i + 3) * 10) / 10;
                }
                items.add(new Item(name, id, stats));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }
}
