package itemsets;

import item.Item;

import java.util.Comparator;
import java.util.List;

public class ItemSet {
    private List<Item> helms;
    private List<Item> chestArmour;
    private List<Item> gauntlets;
    private List<Item> legArmour;

    public List<Item> getHelms() {
        return helms;
    }
    public void setHelms(List<Item> helms) {
        this.helms = helms;
    }

    public List<Item> getChestArmour() {
        return chestArmour;
    }

    public void setChestArmour(List<Item> chestArmour) {
        this.chestArmour = chestArmour;
    }

    public List<Item> getGauntlets() {
        return gauntlets;
    }

    public void setGauntlets(List<Item> gauntlets) {
        this.gauntlets = gauntlets;
    }

    public List<Item> getLegArmour() {
        return legArmour;
    }

    public void setLegArmour(List<Item> legArmour) {
        this.legArmour = legArmour;
    }

    public void reSort() {
        helms.sort(Comparator.comparing(a -> a.stats[13]));
        chestArmour.sort(Comparator.comparing(a -> a.stats[13]));
        gauntlets.sort(Comparator.comparing(a -> a.stats[13]));
        legArmour.sort(Comparator.comparing(a -> a.stats[13]));
    }
}
