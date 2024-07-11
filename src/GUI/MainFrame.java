package GUI;

import javax.swing.*;

public class MainFrame extends JFrame {
    private JPanel contentPane;
    public MainFrame() {
        setTitle("Main Frame");
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
