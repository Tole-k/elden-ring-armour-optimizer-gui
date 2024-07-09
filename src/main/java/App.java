import GUI.MainFrame;
import item.Item;
import optimizer.Optimizer;

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
    }
}
