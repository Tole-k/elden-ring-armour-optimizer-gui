package item;

public class Item
{
    public String name;
    public float[] stats;
    public Item(String name,float[] stats){
        this.name = name;
        this.stats = stats;
    }
    @Override
    public String toString(){
        return name;
    }

}
