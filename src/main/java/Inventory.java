import item.Item;

import java.util.ArrayList;

public class Inventory
{
    private static Inventory self = null;
    private ArrayList<Item> helms;
    private ArrayList<Item> chests;
    private ArrayList<Item> gauntlets;
    private ArrayList<Item> boots;
    public static Inventory getInstance()
    {
        if(self == null)
            self = new Inventory();
        return self;
    }
}
