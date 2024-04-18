package optimizer;

import item.Item;

import java.util.ArrayList;
import java.util.List;

public class Optimizer
{
    private List<Item> helms;
    private List<Item> chests;
    private List<Item> legs;
    private List<Item> boots;
    private int priority;
    private float weight_limit;
    public Optimizer(List<Item> helms, List<Item> chests, List<Item> leggings, List<Item> boots, int priority, float weight_limit)
    {
        this.helms = helms;
        this.chests = chests;
        this.legs = leggings;
        this.boots = boots;
        this.priority = priority;
        this.weight_limit=weight_limit;
    }
    public void findBestSet()
    {
        float best = 0;
        List<Item> bestSet = new ArrayList<>();
        for(Item helm : helms){
            for(Item chest : chests){
                for(Item leg : legs){
                    for(Item boot : boots){
                        float weight = helm.weight+chest.weight+leg.weight+boot.weight;
                        float value = helm.stats[priority]+chest.stats[priority]+leg.stats[priority]+boot.stats[priority];
                        if(weight <=weight_limit){
                            if(value > best){
                                best = value;
                                bestSet.clear();
                                bestSet.add(helm);
                                bestSet.add(chest);
                                bestSet.add(leg);
                                bestSet.add(boot);
                            }
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
