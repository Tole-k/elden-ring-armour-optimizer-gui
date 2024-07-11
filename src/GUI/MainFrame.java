package GUI;

import managers.ArmouryManager;
import managers.InventoryManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import item.Item;

public class MainFrame extends JFrame {
    private JPanel contentPane;
    private JTabbedPane tabs;
    private JList helmsInv;
    private JList chestsInv;
    private JList gauntletsInv;
    private JList legsInv;
    private JList helms;
    private JList chests;
    private JList gauntlets;
    private JList legs;
    private JButton removeInv;
    private JButton addInv;
    private JList parameters;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JList equipLoads;
    private JSpinner spinner3;
    private JList resultList;
    private JPanel Priority;
    private JLabel baseWeight;
    private JLabel maxWeight;
    private JLabel rollType;
    private JLabel minPiose;
    private JLabel result;
    private JButton confirmButton;
    private final InventoryManager inventoryManager;
    private final ArmouryManager armouryManager;
    private final List<JList> jLists;

    public MainFrame() {
        super("Welcome Tarnished!");
        setContentPane(contentPane);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jLists = List.of(helmsInv, chestsInv, gauntletsInv, legsInv, helms, chests, gauntlets, legs);
        inventoryManager = new InventoryManager();
        inventoryManager.buildInventory();
        armouryManager = new ArmouryManager();
        armouryManager.buildArmoury();
        initializeLists();
        for (JList list : jLists) {
            list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        }
        setupButtons();
        pack();
        setVisible(true);
    }

    public void initializeLists() {
        helmsInv.setListData(inventoryManager.showHelms().toArray());
        chestsInv.setListData(inventoryManager.showChests().toArray());
        gauntletsInv.setListData(inventoryManager.showGauntlets().toArray());
        legsInv.setListData(inventoryManager.showLegs().toArray());

        helms.setListData(armouryManager.showHelms().toArray());
        chests.setListData(armouryManager.showChests().toArray());
        gauntlets.setListData(armouryManager.showGauntlets().toArray());
        legs.setListData(armouryManager.showLegs().toArray());
    }

    public void setupButtons() {
        addInv.addActionListener(e -> {
            List<Integer> helmIds = new ArrayList<>();
            for (Object item : helms.getSelectedValuesList()) {
                helmIds.add(((Item) item).id);
            }
            if (!helmIds.isEmpty()) {
                inventoryManager.addToInventory("Helms", helmIds);
                initializeLists();
            }
            List<Integer> chestIds = new ArrayList<>();
            for (Object item : chests.getSelectedValuesList()) {
                chestIds.add(((Item) item).id);
            }
            if (!chestIds.isEmpty()) {
                inventoryManager.addToInventory("ChestArmour", chestIds);
                initializeLists();
            }
            List<Integer> gauntletIds = new ArrayList<>();
            for (Object item : gauntlets.getSelectedValuesList()) {
                gauntletIds.add(((Item) item).id);
            }
            if (!gauntletIds.isEmpty()) {
                inventoryManager.addToInventory("Gauntlets", gauntletIds);
                initializeLists();
            }
            List<Integer> legIds = new ArrayList<>();
            for (Object item : legs.getSelectedValuesList()) {
                legIds.add(((Item) item).id);
            }
            if (!legIds.isEmpty()) {
                inventoryManager.addToInventory("LegArmour", legIds);
                initializeLists();
            }
            revalidate();
        });
        removeInv.addActionListener(e -> {
            List<Integer> helmIds = new ArrayList<>();
            for (Object item : helmsInv.getSelectedValuesList()) {
                helmIds.add(((Item) item).id);
            }
            if (!helmIds.isEmpty()) {
                inventoryManager.removeFromInventory("Helms", helmIds);
                initializeLists();
            }
            List<Integer> chestIds = new ArrayList<>();
            for (Object item : chestsInv.getSelectedValuesList()) {
                chestIds.add(((Item) item).id);
            }
            if (!chestIds.isEmpty()) {
                inventoryManager.removeFromInventory("ChestArmour", chestIds);
                initializeLists();
            }
            List<Integer> gauntletIds = new ArrayList<>();
            for (Object item : gauntletsInv.getSelectedValuesList()) {
                gauntletIds.add(((Item) item).id);
            }
            if (!gauntletIds.isEmpty()) {
                inventoryManager.removeFromInventory("Gauntlets", gauntletIds);
                initializeLists();
            }
            List<Integer> legIds = new ArrayList<>();
            for (Object item : legsInv.getSelectedValuesList()) {
                legIds.add(((Item) item).id);
            }
            if (!legIds.isEmpty()) {
                inventoryManager.removeFromInventory("LegArmour", legIds);
                initializeLists();
            }
            revalidate();
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(10, 10, 10, 10), -1, -1));
        tabs = new JTabbedPane();
        contentPane.add(tabs, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 4, new Insets(0, 0, 0, 0), -1, -1));
        tabs.addTab("Inventory", panel1);
        final JLabel label1 = new JLabel();
        label1.setText("Inventory");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("All Armour pieces");
        panel1.add(label2, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        removeInv = new JButton();
        removeInv.setActionCommand("Save");
        removeInv.setText("remove from inventory");
        panel1.add(removeInv, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addInv = new JButton();
        addInv.setText("add to inventory");
        panel1.add(addInv, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        helmsInv = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        helmsInv.setModel(defaultListModel1);
        scrollPane1.setViewportView(helmsInv);
        final JScrollPane scrollPane2 = new JScrollPane();
        panel1.add(scrollPane2, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        chestsInv = new JList();
        scrollPane2.setViewportView(chestsInv);
        final JScrollPane scrollPane3 = new JScrollPane();
        panel1.add(scrollPane3, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        gauntletsInv = new JList();
        scrollPane3.setViewportView(gauntletsInv);
        final JScrollPane scrollPane4 = new JScrollPane();
        panel1.add(scrollPane4, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        legsInv = new JList();
        scrollPane4.setViewportView(legsInv);
        final JScrollPane scrollPane5 = new JScrollPane();
        panel1.add(scrollPane5, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        helms = new JList();
        scrollPane5.setViewportView(helms);
        final JScrollPane scrollPane6 = new JScrollPane();
        panel1.add(scrollPane6, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        chests = new JList();
        scrollPane6.setViewportView(chests);
        final JScrollPane scrollPane7 = new JScrollPane();
        panel1.add(scrollPane7, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        gauntlets = new JList();
        scrollPane7.setViewportView(gauntlets);
        final JScrollPane scrollPane8 = new JScrollPane();
        panel1.add(scrollPane8, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        legs = new JList();
        scrollPane8.setViewportView(legs);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabs.addTab("Optimizer", panel2);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
