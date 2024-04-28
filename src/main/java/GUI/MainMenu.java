package GUI;
import javax.swing.*;
import java.awt.*;
public class MainMenu extends JFrame
{
    public MainMenu()
    {
        super("Welcome Tarnished");
        getContentPane().setBackground(Color.BLACK);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel title = new JLabel("Welcome Tarnished");
        title.setFont(new Font("Garamond Premier", Font.BOLD, 30));
        setVisible(true);
    }
}
