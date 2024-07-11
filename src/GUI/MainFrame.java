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
    private JSpinner baseWeightSpinner;
    private JSpinner maxWeightSpinner;
    private JList equipLoads;
    private JSpinner minPoiseSpinner;
    private JList resultList;
    private JPanel Priority;
    private JLabel baseWeight;
    private JLabel maxWeight;
    private JLabel rollType;
    private JLabel minPiose;
    private JLabel result;
    private JButton confirmButton;
    private JList list1;
    private JList list2;
    private JList list3;
    private JList list4;
    private JLabel locks;
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
        baseWeightSpinner.setModel(new SpinnerNumberModel(0.0, 0.0, 1000.0, 0.1));
        maxWeightSpinner.setModel(new SpinnerNumberModel(0.0, 0.0, 1000.0, 0.1));
        minPoiseSpinner.setModel(new SpinnerNumberModel(0.0, 0.0, 1000.0, 0.1));
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

        list1.setListData(inventoryManager.showHelms().toArray());
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list2.setListData(inventoryManager.showChests().toArray());
        list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list3.setListData(inventoryManager.showGauntlets().toArray());
        list3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list4.setListData(inventoryManager.showLegs().toArray());
        list4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        parameters.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        equipLoads.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        confirmButton.addActionListener(e -> {
            int priority = parameters.getSelectedIndex();
            double baseWeight = (double) baseWeightSpinner.getValue();
            double maxWeight = (double) maxWeightSpinner.getValue();
            int equipLoadIndex = equipLoads.getSelectedIndex();
            float coefficient = switch (equipLoadIndex) {
                case 0 -> 0.3f;
                case 1 -> 0.7f;
                case 2 -> 1.0f;
                default -> 0.7f;
            };
            double minPoise = (double) minPoiseSpinner.getValue();
            int helmId = getLocked(list1);
            int chestId = getLocked(list2);
            int gauntletId = getLocked(list3);
            int legId = getLocked(list4);
            List<Item> optimalSet = inventoryManager.optimize(helmId, chestId, gauntletId, legId, (float) baseWeight, (float) maxWeight, coefficient, (float) minPoise, priority);
            resultList.setListData(optimalSet.toArray());
            revalidate();
        });
    }

    private int getLocked(JList list) {
        int lockedId = list.getSelectedIndex();
        if (lockedId != -1) {
            lockedId = ((Item) list.getSelectedValue()).id;
        }
        return lockedId;
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
        Priority = new JPanel();
        Priority.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 6, new Insets(0, 0, 0, 0), -1, -1));
        tabs.addTab("Optimizer", Priority);
        parameters = new JList();
        final DefaultListModel defaultListModel2 = new DefaultListModel();
        defaultListModel2.addElement("Physical");
        defaultListModel2.addElement("Vs.Strike");
        defaultListModel2.addElement("Vs.Slash");
        defaultListModel2.addElement("Vs.Pierce");
        defaultListModel2.addElement("Magic");
        defaultListModel2.addElement("Lightning");
        defaultListModel2.addElement("Holy");
        defaultListModel2.addElement("Immunity");
        defaultListModel2.addElement("Robustness");
        defaultListModel2.addElement("Focus");
        defaultListModel2.addElement("Vitality");
        defaultListModel2.addElement("Poise");
        parameters.setModel(defaultListModel2);
        Priority.add(parameters, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Which parameter you want to maximize?");
        Priority.add(label3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        baseWeightSpinner = new JSpinner();
        Priority.add(baseWeightSpinner, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        baseWeight = new JLabel();
        baseWeight.setText("WHat is the weight of your other items?");
        Priority.add(baseWeight, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maxWeightSpinner = new JSpinner();
        Priority.add(maxWeightSpinner, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maxWeight = new JLabel();
        maxWeight.setText("WHat is you maximal equipment load?");
        Priority.add(maxWeight, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        equipLoads = new JList();
        final DefaultListModel defaultListModel3 = new DefaultListModel();
        defaultListModel3.addElement("Light (<30%)");
        defaultListModel3.addElement("Medium (<70%)");
        defaultListModel3.addElement("Heavy (<100%)");
        equipLoads.setModel(defaultListModel3);
        Priority.add(equipLoads, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        rollType = new JLabel();
        rollType.setText("What type of equip load you want to achieve?");
        Priority.add(rollType, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minPoiseSpinner = new JSpinner();
        Priority.add(minPoiseSpinner, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minPiose = new JLabel();
        minPiose.setText("How much poise do you want to have?");
        Priority.add(minPiose, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        resultList = new JList();
        Priority.add(resultList, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 6, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        result = new JLabel();
        result.setText("Your optimal armour set:");
        Priority.add(result, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 6, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        confirmButton = new JButton();
        confirmButton.setText("Confirm");
        Priority.add(confirmButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 6, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 4, new Insets(0, 0, 0, 0), -1, -1));
        Priority.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 6, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        locks = new JLabel();
        locks.setText("Select armour pieces you would like to lock:");
        panel2.add(locks, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane9 = new JScrollPane();
        panel2.add(scrollPane9, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list4 = new JList();
        scrollPane9.setViewportView(list4);
        final JScrollPane scrollPane10 = new JScrollPane();
        panel2.add(scrollPane10, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list3 = new JList();
        scrollPane10.setViewportView(list3);
        final JScrollPane scrollPane11 = new JScrollPane();
        panel2.add(scrollPane11, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list2 = new JList();
        scrollPane11.setViewportView(list2);
        final JScrollPane scrollPane12 = new JScrollPane();
        panel2.add(scrollPane12, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        list1 = new JList();
        scrollPane12.setViewportView(list1);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
