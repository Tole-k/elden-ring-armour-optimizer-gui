package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame
{
    InventoryPanel inventoryPanel;
    OptimizerPanel optimizerPanel;
    public MainFrame()    {
        super("Welcome Tarnished");
        getContentPane().setBackground(Color.BLACK);
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
