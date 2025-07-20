import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Inventory item class
class Item {
    String name;
    int quantity;
    double price;

    Item(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}

public class InventoryManagementSystem extends JFrame {

    private ArrayList<Item> itemList = new ArrayList<>();
    private JTable table;
    private DefaultTableModel model;
    private JTextField nameField, quantityField, priceField;

    public InventoryManagementSystem() {
        setTitle("Inventory Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // UI Components
        JLabel nameLabel = new JLabel("Item Name:");
        nameField = new JTextField(10);
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(5);
        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField(7);

        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        // Table
        model = new DefaultTableModel(new Object[]{"Name", "Quantity", "Price"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Layout
        JPanel inputPanel = new JPanel();
        inputPanel.add(nameLabel); inputPanel.add(nameField);
        inputPanel.add(quantityLabel); inputPanel.add(quantityField);
        inputPanel.add(priceLabel); inputPanel.add(priceField);
        inputPanel.add(addButton); inputPanel.add(updateButton); inputPanel.add(deleteButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Event Handlers
        addButton.addActionListener(e -> addItem());
        updateButton.addActionListener(e -> updateItem());
        deleteButton.addActionListener(e -> deleteItem());

        // Populate form when a row is selected
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    nameField.setText(model.getValueAt(row, 0).toString());
                    quantityField.setText(model.getValueAt(row, 1).toString());
                    priceField.setText(model.getValueAt(row, 2).toString());
                }
            }
        });
    }

    private void addItem() {
        String name = nameField.getText();
        int qty;
        double price;
        try {
            qty = Integer.parseInt(quantityField.getText());
            price = Double.parseDouble(priceField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid quantity or price.");
            return;
        }

        itemList.add(new Item(name, qty, price));
        model.addRow(new Object[]{name, qty, price});
        clearFields();
    }

    private void updateItem() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String name = nameField.getText();
            int qty;
            double price;
            try {
                qty = Integer.parseInt(quantityField.getText());
                price = Double.parseDouble(priceField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid quantity or price.");
                return;
            }

            itemList.set(selectedRow, new Item(name, qty, price));
            model.setValueAt(name, selectedRow, 0);
            model.setValueAt(qty, selectedRow, 1);
            model.setValueAt(price, selectedRow, 2);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to update.");
        }
    }

    private void deleteItem() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            itemList.remove(selectedRow);
            model.removeRow(selectedRow);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to delete.");
        }
    }

    private void clearFields() {
        nameField.setText("");
        quantityField.setText("");
        priceField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InventoryManagementSystem().setVisible(true);
        });
    }
}

