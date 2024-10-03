package managers;

import csv.CsvReader;
import item.Item;
import itemsets.Armoury;
import sqlite.ArmouryLoader;

import java.util.Comparator;
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
        List<Item> helms = armoury.getHelms();
        helms.sort(Comparator.comparing(item -> item.name));
        return helms;
    }

    public List<Item> showChests() {
        List<Item> chestArmour = armoury.getChestArmour();
        chestArmour.sort(Comparator.comparing(item -> item.name));
        return chestArmour;
    }

    public List<Item> showGauntlets() {
        List<Item> gauntlets = armoury.getGauntlets();
        gauntlets.sort(Comparator.comparing(item -> item.name));
        return gauntlets;
    }

    public List<Item> showLegs() {
        List<Item> legArmour = armoury.getLegArmour();
        legArmour.sort(Comparator.comparing(item -> item.name));
        return legArmour;
    }
}
