package item;

public class Item
{
    public Item(String name,float[] stats,float weight){
        this.name = name;
        this.stats = stats;
        this.weight = weight;
    }

    public String name;
    public float[] stats = new float[13];
    public float weight;
}
