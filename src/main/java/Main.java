import item.Item;
import optimizer.Optimizer;
import scraper.WebScraper;

import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Choose stat to prioritize: ");
        int priority = 0; //in.nextInt();
        System.out.print("Enter your weight_limit: ");
        float weightLimit = 30.0F; // in.nextInt();
        WebScraper scraper = new WebScraper();
        List<Item> helms = scraper.getItems("https://eldenring.wiki.fextralife.com/Helms");
        List<Item> chest_armor = scraper.getItems("https://eldenring.wiki.fextralife.com/Chest+Armor");
        List<Item> gauntlets = scraper.getItems("https://eldenring.wiki.fextralife.com/Gauntlets");
        List<Item> leg_armor = scraper.getItems("https://eldenring.wiki.fextralife.com/Leg+Armor");
        Optimizer optimizer = new Optimizer(helms,chest_armor,gauntlets,leg_armor,priority,weightLimit);
        optimizer.findBestSet();
    }
}