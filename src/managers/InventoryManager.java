package managers;

import item.Item;
import itemsets.Inventory;
import optimizer.Optimizer;
import sqlite.InventoryLoader;

import java.util.Comparator;
import java.util.List;
import java.util.Comparator;

public class InventoryManager {
    private final InventoryLoader inventoryLoader;
    private final Inventory inventory;
    private Optimizer optimizer;

    public InventoryManager() {
        inventoryLoader = new InventoryLoader();
        inventory = new Inventory();
    }

    public void addToInventory(String tableName, List<Integer> toAdd) {
        inventoryLoader.connect();
        for (int id : toAdd) {
            inventoryLoader.addToInventory(tableName, id);
        }
        inventoryLoader.disconnect();
        buildInventory();
    }

    public void removeFromInventory(String tableName, List<Integer> toRemove) {
        inventoryLoader.connect();
        for (int id : toRemove) {
            inventoryLoader.removeFromInventory(tableName, id);
        }
        inventoryLoader.disconnect();
        buildInventory();
    }

    public void buildInventory() {
        inventoryLoader.connect();
        inventory.setHelms(inventoryLoader.showInventory("Helms"));
        inventory.setChestArmour(inventoryLoader.showInventory("ChestArmour"));
        inventory.setGauntlets(inventoryLoader.showInventory("Gauntlets"));
        inventory.setLegArmour(inventoryLoader.showInventory("LegArmour"));
        inventoryLoader.disconnect();
        inventory.reSort();
    }

    public List<Item> optimize(int helmId, int chestId, int gauntletId, int legId, float base_weight, float weight_limit, float coefficient, float minPoiseLevel, int priority) {
        buildInventory();
        optimizer = new Optimizer(inventory);
        Item helm = inventory.getHelms().stream().filter(item -> item.id == helmId).findFirst().orElse(null);
        Item chest = inventory.getChestArmour().stream().filter(item -> item.id == chestId).findFirst().orElse(null);
        Item gauntlet = inventory.getGauntlets().stream().filter(item -> item.id == gauntletId).findFirst().orElse(null);
        Item leg = inventory.getLegArmour().stream().filter(item -> item.id == legId).findFirst().orElse(null);
        optimizer.setupBaseState(helm, chest, gauntlet, leg, base_weight, priority, weight_limit, coefficient, minPoiseLevel);
        return optimizer.findBestSet();
    }


    public List<Item> showHelms() {
        List<Item> helms = inventory.getHelms();
        helms.sort(Comparator.comparing(item -> item.name));
        return helms;
    }

    public List<Item> showChests() {
        List<Item> chestArmour = inventory.getChestArmour();
        chestArmour.sort(Comparator.comparing(item -> item.name));
        return chestArmour;
    }

    public List<Item> showGauntlets() {
        List<Item> gauntlets = inventory.getGauntlets();
        gauntlets.sort(Comparator.comparing(item -> item.name));
        return gauntlets;
    }

    public List<Item> showLegs() {
        List<Item> legArmour = inventory.getLegArmour();
        legArmour.sort(Comparator.comparing(item -> item.name));
        return legArmour;
    }
}

