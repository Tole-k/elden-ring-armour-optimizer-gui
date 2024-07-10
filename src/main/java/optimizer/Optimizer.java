package optimizer;

import item.Item;
import itemsets.Inventory;

import java.util.*;

public class Optimizer
{
    private List<Item> helms;
    private List<Item> chests;
    private List<Item> gauntlets;
    private List<Item> legArmour;
    private final Inventory inventory;
    private int priority;
    private float weight_limit;
    private float base_weight;
    private static final Item naked = new Item("Naked",new float[14]);
    private Item bestHelm;
    private Item bestChest;
    private Item bestGauntlet;
    private Item bestLeg;
    private float coefficient;
    private float minPoiseLevel;

    public Optimizer(Inventory inventory)
    {
        this.inventory = inventory;
        helms = inventory.getHelms();
        chests = inventory.getChestArmour();
        gauntlets = inventory.getGauntlets();
        legArmour = inventory.getLegArmour();
    }
    public void eliminate_suboptimal(List<Item> items, int priority)
    {
        Map<Float, Float> hm = new HashMap<>();
        Iterator<Item> it = items.iterator();
        while (it.hasNext())
        {
            Item item = it.next();
            if(!hm.containsKey(item.stats[13]))
            {
                hm.put(item.stats[13],item.stats[priority]);
            }
            else if (hm.get(item.stats[13]) < item.stats[priority])
            {
                hm.put(item.stats[13],item.stats[priority]);
            }
            else
            {
                it.remove();
            }
        }
    }
    public void preprocess()
    {
        helms.add(naked);
        chests.add(naked);
        gauntlets.add(naked);
        legArmour.add(naked);
        eliminate_suboptimal(helms, priority);
        eliminate_suboptimal(chests, priority);
        eliminate_suboptimal(gauntlets, priority);
        eliminate_suboptimal(legArmour, priority);
    }
    public void setupBaseState(Item helm, Item chest, Item gauntlet, Item leg, float base_weight, int priority, float weight_limit, float coefficient, float minPoiseLevel)
    {
        bestHelm = helm;
        bestChest = chest;
        bestGauntlet = gauntlet;
        bestLeg = leg;
        this.priority = priority;
        this.weight_limit = weight_limit;
        this.coefficient = coefficient;
        this.base_weight = base_weight;
        this.minPoiseLevel = minPoiseLevel;
        if(helm != null)
        {
            this.base_weight += helm.stats[13];
        }
        if(chest != null)
        {
            this.base_weight += chest.stats[13];
        }
        if(gauntlet != null)
        {
            this.base_weight += gauntlet.stats[13];
        }
        if(leg != null)
        {
            this.base_weight += leg.stats[13];
        }
        preprocess();
    }
    public List<Item> findBestSet()
    {
        boolean done = false;
        if(base_weight > coefficient*weight_limit)
        {
            return null;
        }
        if(bestHelm != null && bestChest != null && bestGauntlet != null && bestLeg != null)
        {
            return List.of(bestHelm,bestChest,bestGauntlet,bestLeg);
        }
        if(bestHelm != null)
        {
            helms = List.of(bestHelm);
        }
        if(bestChest != null)
        {
            chests = List.of(bestChest);
        }
        if(bestGauntlet != null)
        {
            gauntlets = List.of(bestGauntlet);
        }
        if(bestLeg != null)
        {
            legArmour = List.of(bestLeg);
        }
        float best = -1;
        for(Item chest:chests)
        {
            if(chest.stats[13] + base_weight > coefficient*weight_limit)
            {
                continue;
            }
            for(Item leg:legArmour)
            {
                if(chest.stats[13] + leg.stats[13] + base_weight > coefficient*weight_limit)
                {
                    continue;
                }
                for(Item helm:helms)
                {
                    if(chest.stats[13] + leg.stats[13] + helm.stats[13] + base_weight > coefficient*weight_limit)
                    {
                        continue;
                    }
                    for(Item gauntlet:gauntlets)
                    {
                        if (chest.stats[13] + leg.stats[13] + helm.stats[13] + gauntlet.stats[13] + base_weight > coefficient*weight_limit)
                        {
                            continue;
                        }
                        if(chest.stats[12] + leg.stats[12] + helm.stats[12] + gauntlet.stats[12] < minPoiseLevel)
                        {
                            continue;
                        }
                        if(chest.stats[priority] + leg.stats[priority] + helm.stats[priority] + gauntlet.stats[priority] > best)
                        {
                            done=true;
                            best = chest.stats[priority] + leg.stats[priority] + helm.stats[priority] + gauntlet.stats[priority];
                            bestHelm = helm;
                            bestChest = chest;
                            bestGauntlet = gauntlet;
                            bestLeg = leg;
                        }
                    }
                }
            }
        }
        if(!done)
        {
            return null;
        }
        return List.of(bestHelm,bestChest,bestGauntlet,bestLeg);
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
