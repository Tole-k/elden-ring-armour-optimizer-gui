package optimizer;

import item.Item;
import itemsets.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Optimizer {
    private static final Item naked = new Item("Naked", new float[14]);
    private final Inventory inventory;
    private List<Item> helms;
    private List<Item> chests;
    private List<Item> gauntlets;
    private List<Item> legArmour;
    private int priority;
    private float weight_limit;
    private float base_weight;
    private Item bestHelm;
    private Item bestChest;
    private Item bestGauntlet;
    private Item bestLeg;
    private float coefficient;
    private float minPoiseLevel;

    public Optimizer(Inventory inventory) {
        this.inventory = inventory;
        helms = inventory.getHelms();
        chests = inventory.getChestArmour();
        gauntlets = inventory.getGauntlets();
        legArmour = inventory.getLegArmour();
    }

    public List<Item> eliminate_suboptimal(List<Item> items, int priority) {
        Map<Float, Item> hm = new HashMap<>();
        for (Item item : items) {
            if (!hm.containsKey(item.stats[13])) {
                hm.put(item.stats[13], item);
            } else if (hm.get(item.stats[13]).stats[priority] < item.stats[priority]) {
                hm.put(item.stats[13], item);
            } else if (hm.get(item.stats[13]).stats[priority] == item.stats[priority]) {
                double itemSum = IntStream.range(0, item.stats.length).mapToDouble(i -> item.stats[i]).sum();
                double othersum = IntStream.range(0, hm.get(item.stats[13]).stats.length).mapToDouble(i -> hm.get(item.stats[13]).stats[i]).sum();
                if (itemSum > othersum) {
                    hm.put(item.stats[13], item);
                }
            }
        }
        return new ArrayList<>(hm.values());
    }

    public void preprocess() {
        helms.add(naked);
        chests.add(naked);
        gauntlets.add(naked);
        legArmour.add(naked);
        // helms = eliminate_suboptimal(helms, priority);
        // chests = eliminate_suboptimal(chests, priority);
        // gauntlets = eliminate_suboptimal(gauntlets, priority);
        // legArmour = eliminate_suboptimal(legArmour, priority);
    }

    public void setupBaseState(Item helm, Item chest, Item gauntlet, Item leg, float base_weight, int priority, float weight_limit, float coefficient, float minPoiseLevel) {
        bestHelm = helm;
        bestChest = chest;
        bestGauntlet = gauntlet;
        bestLeg = leg;
        this.priority = priority;
        this.weight_limit = weight_limit;
        this.coefficient = coefficient;
        this.base_weight = base_weight;
        this.minPoiseLevel = minPoiseLevel;
        preprocess();
    }

    public List<Item> findBestSet() {
        boolean done = false;
        if ((float) Math.round(base_weight * 10) / 10 >= (float) Math.round(coefficient * weight_limit * 10) / 10) {
            return null;
        }
        if (bestHelm != null && bestChest != null && bestGauntlet != null && bestLeg != null) {
            return List.of(bestHelm, bestChest, bestGauntlet, bestLeg);
        }
        if (bestHelm != null) {
            helms = List.of(bestHelm);
        }
        if (bestChest != null) {
            chests = List.of(bestChest);
        }
        if (bestGauntlet != null) {
            gauntlets = List.of(bestGauntlet);
        }
        if (bestLeg != null) {
            legArmour = List.of(bestLeg);
        }
        float best = -1;
        for (Item chest : chests) {
            if ((float) Math.round((chest.stats[13] + base_weight) * 10) / 10 >= (float) Math.round(coefficient * weight_limit * 10) / 10) {
                continue;
            }
            for (Item leg : legArmour) {
                if ((float) Math.round((chest.stats[13] + leg.stats[13] + base_weight) * 10) / 10 >= (float) Math.round(coefficient * weight_limit * 10) / 10) {
                    continue;
                }
                for (Item helm : helms) {
                    if ((float) Math.round((chest.stats[13] + leg.stats[13] + helm.stats[13] + base_weight) * 10) / 10 >= (float) Math.round(coefficient * weight_limit * 10) / 10) {
                        continue;
                    }
                    for (Item gauntlet : gauntlets) {
                        if ((float) Math.round((chest.stats[13] + leg.stats[13] + helm.stats[13] + gauntlet.stats[13] + base_weight) * 10) / 10 >= (float) Math.round(coefficient * weight_limit * 10) / 10) {
                            continue;
                        }
                        if (chest.stats[12] + leg.stats[12] + helm.stats[12] + gauntlet.stats[12] < minPoiseLevel) {
                            continue;
                        }
                        if (calcDefense(chest.stats[priority], leg.stats[priority], helm.stats[priority], gauntlet.stats[priority]) > best) {
                            done = true;
                            best = calcDefense(chest.stats[priority], leg.stats[priority], helm.stats[priority], gauntlet.stats[priority]);
                            bestHelm = helm;
                            bestChest = chest;
                            bestGauntlet = gauntlet;
                            bestLeg = leg;
                        }
                    }
                }
            }
        }
        if (!done) {
            return null;
        }
        return List.of(bestHelm, bestChest, bestGauntlet, bestLeg);
    }

    public float calcDefense(float val1, float val2, float val3, float val4) {
        return 100 * (1 - (1 - val1 / 100) * (1 - val2 / 100) * (1 - val3 / 100) * (1 - val4 / 100));
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public float getWeight_limit() {
        return weight_limit;
    }

    public void setWeight_limit(float weight_limit) {
        this.weight_limit = weight_limit;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }
}
