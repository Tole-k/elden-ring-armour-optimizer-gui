import managers.InventoryManager;
import item.Item;
import managers.ArmouryManager;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class App
{
    private final ArmouryManager armouryManager;
    private final InventoryManager inventoryManager;
    public App()
    {
        armouryManager = new ArmouryManager();
        inventoryManager = new InventoryManager();
    }
    public void run()
    {
        Scanner scanner = new Scanner(System.in);
        inventoryManager.buildInventory();
        armouryManager.buildArmoury();
        while(true)
        {
            System.out.println("Select 1 to update the database, 2 to read the database, 3 to see the inventory, 0 to exit");
            int option = scanner.nextInt();
            switch (option)
            {
                case 1:
                    armouryManager.updateDatabase();
                    armouryManager.buildArmoury();
                    break;
                case 2:
                    for(Item item : armouryManager.showHelms())
                    {
                        System.out.println(item);
                    }
                    System.out.println("Select ids of items, separated by comma, to add them to the inventory, -1 to exit");
                    String ids = scanner.next();
                    if(!ids.equals("-1"))
                    {
                        String[] idArray = ids.split(",");
                        List<Integer>toAdd = Stream.of(idArray).map(Integer::parseInt).toList();
                        inventoryManager.updateInventory("HelmsInv",toAdd,List.of());
                        inventoryManager.buildInventory();
                    }
                    break;
                case 3:
                    System.out.println("Inventory:");
                    for(Item item : inventoryManager.showHelms())
                    {
                        System.out.println(item);
                    }
                    System.out.println("Select ids of items, separated by comma, to remove them from the inventory, -1 to exit");
                    String idss = scanner.next();
                    if(!idss.equals("-1"))
                    {
                        String[] idArray = idss.split(",");
                        List<Integer>toRemove = Stream.of(idArray).map(Integer::parseInt).toList();
                        inventoryManager.updateInventory("HelmsInv",List.of(),toRemove);
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
