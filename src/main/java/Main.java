import item.Item;
import scraper.WebScraper;

import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args)
    {
        //Scanner in = new Scanner(System.in);
        //int priority = in.nextInt();
        WebScraper scraper = new WebScraper();
        List<Item> helms = scraper.getItems("https://eldenring.wiki.fextralife.com/Helms");
        List<Item> chest_armor = scraper.getItems("https://eldenring.wiki.fextralife.com/Chest+Armor");
        List<Item> gauntlets = scraper.getItems("https://eldenring.wiki.fextralife.com/Gauntlets");
        List<Item> leg_armor = scraper.getItems("https://eldenring.wiki.fextralife.com/Leg+Armor");
    }
}