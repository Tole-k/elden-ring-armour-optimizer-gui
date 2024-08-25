package managers;

import csv.CsvReader;
import item.Item;
import itemsets.Armoury;
import sqlite.ArmouryLoader;

import java.util.List;

public class ArmouryManager {
    private final CsvReader csvReader;
    private final ArmouryLoader armouryLoader;
    private final Armoury armoury;
    private List<Item> helms;
    private List<Item> chestsArmour;
    private List<Item> gauntlets;
    private List<Item> legArmour;

    public ArmouryManager() {
        csvReader = new CsvReader();
        armouryLoader = new ArmouryLoader();
        armoury = new Armoury();
    }

    public void updateDatabase() {
        helms = csvReader.load("res/helms.csv");
        chestsArmour = csvReader.load("res/chests.csv");
        gauntlets = csvReader.load("res/gauntlets.csv");
        legArmour = csvReader.load("res/legs.csv");
        armouryLoader.connect();
        armouryLoader.deleteTable("Helms");
        armouryLoader.loadIntoArmoury("Helms", helms);
        armouryLoader.deleteTable("ChestArmour");
        armouryLoader.loadIntoArmoury("ChestArmour", chestsArmour);
        armouryLoader.deleteTable("Gauntlets");
        armouryLoader.loadIntoArmoury("Gauntlets", gauntlets);
        armouryLoader.deleteTable("LegArmour");
        armouryLoader.loadIntoArmoury("LegArmour", legArmour);
        armouryLoader.disconnect();
    }

    public void buildArmoury() {
        armouryLoader.connect();
        armoury.setHelms(armouryLoader.loadFromArmoury("Helms"));
        armoury.setChestArmour(armouryLoader.loadFromArmoury("ChestArmour"));
        armoury.setGauntlets(armouryLoader.loadFromArmoury("Gauntlets"));
        armoury.setLegArmour(armouryLoader.loadFromArmoury("LegArmour"));
        armouryLoader.disconnect();
        armoury.reSort();
    }

    public List<Item> showHelms() {
        return armoury.getHelms();
    }

    public List<Item> showChests() {
        return armoury.getChestArmour();
    }

    public List<Item> showGauntlets() {
        return armoury.getGauntlets();
    }

    public List<Item> showLegs() {
        return armoury.getLegArmour();
    }
}
