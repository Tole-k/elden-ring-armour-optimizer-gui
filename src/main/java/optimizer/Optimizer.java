package optimizer;

import item.Item;

import java.util.*;

public class Optimizer
{
    private static Optimizer self = null;
    private final List<Item> helms;
    private final List<Item> chests;
    private final List<Item> gauntlets;
    private final List<Item> legArmour;
    private final int priority;
    private final float weight_limit;
    public static Optimizer getInstance(List<Item> helms, List<Item> chests, List<Item> gauntlets, List<Item> legArmour, int priority, float weight_limit)
    {
        if(self == null)
        {
            self = new Optimizer(helms, chests, gauntlets, legArmour, priority, weight_limit);
        }
        return self;
    }
    private Optimizer(List<Item> helms, List<Item> chests, List<Item> gauntlets, List<Item> legArmour, int priority, float weight_limit)
    {
        this.helms = helms;
        this.chests = chests;
        this.gauntlets = gauntlets;
        this.legArmour = legArmour;
        this.priority = priority;
        this.weight_limit = weight_limit;
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
    public void add_naked(List<Item>items)
    {
        String name = "Naked";
        float[] stats = new float[14];
        Item item = new Item(name,stats);
        items.add(item);
    }
    public void preprocess()
    {
        add_naked(helms);
        add_naked(chests);
        add_naked(gauntlets);
        add_naked(legArmour);
        eliminate_suboptimal(helms, priority);
        eliminate_suboptimal(chests, priority);
        eliminate_suboptimal(gauntlets, priority);
        eliminate_suboptimal(legArmour, priority);
    }
    public void findBestSet()
    {
        float best = -1;
        List<Item> bestSet = new ArrayList<>();
        float remaining_weight = weight_limit;
        for(Item chest : chests){
            if(chest.stats[13] >remaining_weight)
            {
                continue;
            }
            remaining_weight = weight_limit - chest.stats[13];
            for (Item gauntlets : gauntlets)
            {
                if(gauntlets.stats[13] > remaining_weight)
                {
                    continue;
                }
                remaining_weight = weight_limit - chest.stats[13] - gauntlets.stats[13];
                for(Item helm: helms)
                {
                    if(helm.stats[13] > remaining_weight)
                    {
                        continue;
                    }
                    remaining_weight = weight_limit - chest.stats[13]- gauntlets.stats[13] - helm.stats[13];
                    for(Item legArmour: legArmour)
                    {
                        if(legArmour.stats[13] > remaining_weight)
                        {
                            continue;
                        }
                        float stats = helm.stats[priority]+chest.stats[priority]+gauntlets.stats[priority]+legArmour.stats[priority];
                        if(stats > best)
                        {
                            best = stats;
                            bestSet.clear();
                            bestSet.add(helm);
                            bestSet.add(chest);
                            bestSet.add(legArmour);
                            bestSet.add(gauntlets);
                        }
                    }
                }
            }
        }
        System.out.println(best);
        for (Item item : bestSet) {
            System.out.println(item.name);
        }
    }
}
