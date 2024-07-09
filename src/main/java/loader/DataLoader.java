package loader;
import item.Item;

import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private CsvLoader csvLoader;
    private List<Item> helms;
    private List<Item> chestsArmour;
    private List<Item> gauntlets;
    private List<Item> legArmour;
    public DataLoader() {
        csvLoader = new CsvLoader();
    }
    public void loadFromCsv()
    {
        helms = csvLoader.load("res/helms.csv");
        chestsArmour = csvLoader.load("res/chests.csv");
        gauntlets = csvLoader.load("res/gauntlets.csv");
        legArmour = csvLoader.load("res/legs.csv");
        for (Item item : helms) {
            System.out.println(item.toString());
        }
    }
}
