package GUI;
import javax.swing.*;

public class baseFrame extends JFrame
{
    InventoryPanel inventoryPanel;
    OptimizerPanel optimizerPanel;
    public baseFrame()    {
        super("Welcome Tarnished!");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.inventoryPanel = new InventoryPanel();
        this.optimizerPanel = new OptimizerPanel();
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Inventory", inventoryPanel);
        tabbedPane.addTab("Optimizer", optimizerPanel);
        add(tabbedPane);
        setVisible(true);
    }


}
