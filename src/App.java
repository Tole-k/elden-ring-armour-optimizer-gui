import GUI.MainFrame;
import managers.InventoryManager;
import item.Item;
import managers.ArmouryManager;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class App
{
    private final ArmouryManager armouryManager;
    private final InventoryManager inventoryManager;
    private MainFrame mainFrame;
    public App()
    {
        armouryManager = new ArmouryManager();
        inventoryManager = new InventoryManager();
    }
    public void runCLI()
    {
        Scanner scanner = new Scanner(System.in);
        inventoryManager.buildInventory();
        armouryManager.buildArmoury();
        while(true)
        {
            System.out.println("Select 1 to update the database, 2 to read the database, 3 to see the inventory, 4 to optimize, 0 to exit");
            int option = scanner.nextInt();
            switch (option)
            {
                case 1:
                    armouryManager.updateDatabase();
                    armouryManager.buildArmoury();
                    break;
                case 2:
                    System.out.println("Select 1 to show helms, 2 to show chests, 3 to show gauntlets, 4 to show legs");
                    int option2 = scanner.nextInt();
                    switch (option2)
                    {
                        case 1:
                            for(Item item : armouryManager.showHelms())
                            {
                                System.out.println(item);
                            }
                            break;
                        case 2:
                            for(Item item : armouryManager.showChests())
                            {
                                System.out.println(item);
                            }
                            break;
                        case 3:
                            for(Item item : armouryManager.showGauntlets())
                            {
                                System.out.println(item);
                            }
                            break;
                        case 4:
                            for(Item item : armouryManager.showLegs())
                            {
                                System.out.println(item);
                            }
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                    System.out.println("Select ids of items, separated by comma, to add them to the inventory, -1 to exit");
                    String ids = scanner.next();
                    if(!ids.equals("-1"))
                    {
                        String[] idArray = ids.split(",");
                        List<Integer>toAdd = Stream.of(idArray).map(Integer::parseInt).toList();
                        switch (option2)
                        {
                            case 1:
                                inventoryManager.addToInventory("Helms",toAdd);
                                break;
                            case 2:
                                inventoryManager.addToInventory("ChestArmour",toAdd);
                                break;
                            case 3:
                                inventoryManager.addToInventory("Gauntlets",toAdd);
                                break;
                            case 4:
                                inventoryManager.addToInventory("LegArmour",toAdd);
                                break;
                        }
                        inventoryManager.buildInventory();
                    }
                    break;
                case 3:
                    System.out.println("Select 1 to show helms, 2 to show chests, 3 to show gauntlets, 4 to show legs");
                    int option3 = scanner.nextInt();
                    switch (option3)
                    {
                        case 1:
                            System.out.println("Inventory:");
                            for(Item item : inventoryManager.showHelms())
                            {
                                System.out.println(item);
                            }
                            break;
                        case 2:
                            System.out.println("Inventory:");
                            for(Item item : inventoryManager.showChests())
                            {
                                System.out.println(item);
                            }
                            break;
                        case 3:
                            System.out.println("Inventory:");
                            for(Item item : inventoryManager.showGauntlets())
                            {
                                System.out.println(item);
                            }
                            break;
                        case 4:
                            System.out.println("Inventory:");
                            for(Item item : inventoryManager.showLegs())
                            {
                                System.out.println(item);
                            }
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                    System.out.println("Select ids of items, separated by comma, to remove them from the inventory, -1 to exit");
                    String idss = scanner.next();
                    if(!idss.equals("-1"))
                    {
                        String[] idArray = idss.split(",");
                        List<Integer>toRemove = Stream.of(idArray).map(Integer::parseInt).toList();
                        switch (option3)
                        {
                            case 1:
                                inventoryManager.removeFromInventory("Helms",toRemove);
                                break;
                            case 2:
                                inventoryManager.removeFromInventory("ChestArmour",toRemove);
                                break;
                            case 3:
                                inventoryManager.removeFromInventory("Gauntlets",toRemove);
                                break;
                            case 4:
                                inventoryManager.removeFromInventory("LegArmour",toRemove);
                                break;
                        }
                        inventoryManager.buildInventory();
                    }
                    break;
                case 4:
                    System.out.println("Enter base weight, weight limit, coefficient, min poise level, priority");
                    float base_weight = scanner.nextFloat();
                    float weight_limit = scanner.nextFloat();
                    float coefficient = scanner.nextFloat();
                    float minPoiseLevel = scanner.nextFloat();
                    int priority = scanner.nextInt();
                    System.out.println("Enter helm id if you want to lock one, -1 otherwise");
                    int helmId = scanner.nextInt();
                    System.out.println("Enter chest id if you want to lock one, -1 otherwise");
                    int chestId = scanner.nextInt();
                    System.out.println("Enter gauntlet id if you want to lock one, -1 otherwise");
                    int gauntletId = scanner.nextInt();
                    System.out.println("Enter leg id if you want to lock one, -1 otherwise");
                    int legId = scanner.nextInt();
                    for(Item item : inventoryManager.optimize(helmId,chestId,gauntletId,legId,base_weight,weight_limit,coefficient,minPoiseLevel,priority))
                    {
                        System.out.println(item);
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
    public void runGUI()
    {
        SwingUtilities.invokeLater(() -> mainFrame = new MainFrame());
    }
}
