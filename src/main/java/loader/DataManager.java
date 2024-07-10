package loader;
import item.Item;

import java.util.List;

public class DataManager {
    private final CsvReader csvReader;
    private final SQLiteManager armouryLoader;
    private List<Item> helms;
    private List<Item> chestsArmour;
    private List<Item> gauntlets;
    private List<Item> legArmour;
    public DataManager() {
        csvReader = new CsvReader();
        armouryLoader = new SQLiteManager("armoury.db");
    }
    public void loadFromCsv()
    {
        helms = csvReader.load("res/helms.csv");
        chestsArmour = csvReader.load("res/chests.csv");
        gauntlets = csvReader.load("res/gauntlets.csv");
        legArmour = csvReader.load("res/legs.csv");
        armouryLoader.connect();
        armouryLoader.loadIntoTable("Helms",helms);
        armouryLoader.loadIntoTable("ChestsArmour",chestsArmour);
        armouryLoader.loadIntoTable("Gauntlets",gauntlets);
        armouryLoader.loadIntoTable("LegArmour",legArmour);
        armouryLoader.disconnect();
    }
    public void readFromSQL()
    {
        armouryLoader.connect();
        armouryLoader.disconnect();
    }

}
