package item;

public class Item
{
    public int id;
    public String name;
    public float[] stats;
    public Item(String name, int id, float[] stats){
        this.name = name;
        this.id = id;
        this.stats = stats;
    }
    public Item(String name,float[] stats){
        this.name = name;
        this.stats = stats;
    }
    @Override
    public String toString(){
        return name;
    }

}
