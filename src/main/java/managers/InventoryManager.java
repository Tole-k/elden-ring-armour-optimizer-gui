package managers;
import item.Item;
import itemsets.Inventory;
import sqlite.InventoryLoader;

import java.util.List;

public class InventoryManager {
    private final InventoryLoader inventoryLoader;
    private final Inventory inventory;
    public InventoryManager() {
        inventoryLoader = new InventoryLoader();
        inventory = new Inventory();
    }
    public void updateInventory(String tableName, List<Integer>toAdd, List<Integer>toRemove)
    {
        inventoryLoader.connect();
        for(int id : toAdd)
        {
            inventoryLoader.addToInventory(tableName,id);
        }
        for(int id : toRemove)
        {
            inventoryLoader.removeFromInventory(tableName,id);
        }
        inventoryLoader.disconnect();
    }
    public void buildInventory()
    {
        inventoryLoader.connect();
        inventory.setHelms(inventoryLoader.showInventory("Helms"));
        inventory.setChestArmour(inventoryLoader.showInventory("ChestsArmour"));
        inventory.setGauntlets(inventoryLoader.showInventory("Gauntlets"));
        inventory.setLegArmour(inventoryLoader.showInventory("LegArmour"));
        inventoryLoader.disconnect();
    }
    public List<Item> showHelms()
    {
        return inventory.getHelms();
    }
    public List<Item> showChests()
    {
        return inventory.getChestArmour();
    }
    public List<Item> showGauntlets()
    {
        return inventory.getGauntlets();
    }
    public List<Item> showLegs()
    {
        return inventory.getLegArmour();
    }
}

