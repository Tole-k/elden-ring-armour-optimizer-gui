package sqlite;

import item.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArmouryLoader extends SQLiteLoader {
    public void createArmoury(String tableName) {
        if (!tableExists(tableName)) {
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate("create table " + tableName + "(id INTEGER PRIMARY KEY, name VARCHAR,Phy REAL, VsStr REAL, VsSla REAL, VsPie REAL, Mag REAL, Fir REAL, Lit REAL, Hol REAL, Imm REAL, Rob REAL, Foc REAL, Vit REAL, Poi REAL, Wgt REAL)");
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void loadIntoArmoury(String tableName, List<Item> items) {
        createArmoury(tableName);
        for (Item item : items) {
            try {
                PreparedStatement itemInsertion = connection.prepareStatement("insert into " + tableName + " Values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                itemInsertion.setString(1, item.name);
                for (int i = 0; i < item.stats.length; i++) {
                    itemInsertion.setFloat(i + 2, item.stats[i]);
                }
                itemInsertion.executeUpdate();
                itemInsertion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<Item> loadFromArmoury(String tableName) {
        List<Item> items = new ArrayList<>();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("select * from " + tableName)) {
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
