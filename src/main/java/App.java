import GUI.MainFrame;
import item.Item;
import loader.DataLoader;
import optimizer.Optimizer;
import loader.CsvLoader;

import java.util.List;

public class App
{
    private final Inventory inventory;
    private final MainFrame mainFrame;
    private Optimizer optimizer;
    public App()
    {
        inventory = Inventory.getInstance();
        mainFrame = new MainFrame();
    }
    public void run()
    {
        DataLoader dataLoader = new DataLoader();
        dataLoader.loadFromCsv();
    }
}
