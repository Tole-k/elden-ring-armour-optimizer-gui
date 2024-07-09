import GUI.MainFrame;
import item.Item;
import optimizer.Optimizer;
import scraper.WebScraper;

import java.util.List;

public class App
{
    private final WebScraper webScraper;
    private final Inventory inventory;
    private final MainFrame mainFrame;
    private Optimizer optimizer;
    public App()
    {
        webScraper = WebScraper.getInstance();
        inventory = Inventory.getInstance();
        mainFrame = new MainFrame();
    }
    public void run()
    {
        List<Item> helms = webScraper.getItems("https://eldenring.wiki.fextralife.com/Helms");
        //List<Item> chestArmour = webScraper.getItems("https://eldenring.wiki.fextralife.com/Chest+Armor");
        //List<Item> gauntlets = webScraper.getItems("https://eldenring.wiki.fextralife.com/Gauntlets");
        //List<Item> legArmour = webScraper.getItems("https://eldenring.wiki.fextralife.com/Leg+Armor");
        //optimizer = Optimizer.getInstance(helms,chestArmour,gauntlets,legArmour,1,50);
        //optimizer.preprocess();
        //optimizer.findBestSet();
    }
}
