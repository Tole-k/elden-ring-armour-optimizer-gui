package optimizer;

import item.Item;

import java.util.*;

public class Optimizer
{
    private final List<Item> helms;
    private final List<Item> chests;
    private final List<Item> legs;
    private final List<Item> boots;
    private final int priority;
    private final float weight_limit;
    public Optimizer(List<Item> helms, List<Item> chests, List<Item> leggings, List<Item> boots, int priority, float weight_limit)
    {
        this.helms = helms;
        this.chests = chests;
        this.legs = leggings;
        this.boots = boots;
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
            if(!hm.containsKey(item.weight))
            {
                hm.put(item.weight,item.stats[priority]);
            }
            else if (hm.get(item.weight) < item.stats[priority])
            {
                hm.put(item.weight,item.stats[priority]);
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
        float[] stats = new float[13];
        float weight = 0;
        Item item = new Item(name,stats,weight);
        items.add(item);
    }
    public void preprocess()
    {
        add_naked(helms);
        add_naked(chests);
        add_naked(legs);
        add_naked(boots);
        eliminate_suboptimal(helms, priority);
        eliminate_suboptimal(chests, priority);
        eliminate_suboptimal(legs, priority);
        eliminate_suboptimal(boots, priority);
    }
    public void findBestSet()
    {
        float best = -1;
        List<Item> bestSet = new ArrayList<>();
        float remaining_weight = weight_limit;
        for(Item chest : chests){
            if(chest.weight >remaining_weight)
            {
                continue;
            }
            remaining_weight = weight_limit - chest.weight;
            for (Item leg_armor : legs)
            {
                if(leg_armor.weight > remaining_weight)
                {
                    continue;
                }
                remaining_weight = weight_limit - chest.weight - leg_armor.weight;
                for(Item helm: helms)
                {
                    if(helm.weight > remaining_weight)
                    {
                        continue;
                    }
                    remaining_weight = weight_limit - chest.weight- leg_armor.weight - helm.weight;
                    for(Item boot: boots)
                    {
                        if(boot.weight > remaining_weight)
                        {
                            continue;
                        }
                        float stats = helm.stats[priority]+chest.stats[priority]+leg_armor.stats[priority]+boot.stats[priority];
                        if(stats > best)
                        {
                            best = stats;
                            bestSet.clear();
                            bestSet.add(helm);
                            bestSet.add(chest);
                            bestSet.add(boot);
                            bestSet.add(leg_armor);
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
