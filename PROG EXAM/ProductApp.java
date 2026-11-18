import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/** Swing GUI application.
 */
public class ProductApp extends JFrame {

    private final JTextField txtCustomer = new JTextField(20);
    private final JComboBox<String> cmbProduct = new JComboBox<>(new String[]{
            "Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"});
    private final JTextField txtPrice = new JTextField(20);
    private final JButton btnProcess = new JButton("PROCESS");

    public ProductApp() {
        super("Product App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Menu bar: File -> Exit ; Edit -> Process, Clear
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenuItem miProcess = new JMenuItem("Process");
        JMenuItem miClear = new JMenuItem("Clear");
        JMenuItem miExit = new JMenuItem("Exit");
        file.add(miExit);
        edit.add(miProcess);
        edit.add(miClear);
        bar.add(file);
        bar.add(edit);
        setJMenuBar(bar);

        // Form
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        form.add(new JLabel("Customer Name:"));
        form.add(txtCustomer);
        form.add(new JLabel("Select Product:"));
        form.add(cmbProduct);
        form.add(new JLabel("Enter Price:"));
        form.add(txtPrice);
        form.add(new JLabel());
        form.add(btnProcess);
        add(form);

        // Same logic for button and Process menu item
        btnProcess.addActionListener(e -> processSale());
        miProcess.addActionListener(e -> processSale());
        miClear.addActionListener(e -> clearForm());
        miExit.addActionListener(e -> System.exit(0));

        pack();
        setLocationRelativeTo(null);
    }

    private void processSale() {
        String customer = txtCustomer.getText().trim();
        String product = (String) cmbProduct.getSelectedItem();

        if (customer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the customer name.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double price;
        try {
            price = Double.parseDouble(txtPrice.getText().trim());
            if (price <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid positive price.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ProductCalculations calc = new ProductCalculations(price);
        double vat = calc.GetVat();
        double commission = calc.GetCommission();
        double total = calc.GetTotal();

        JOptionPane.showMessageDialog(this,
                "Customer Name: " + customer + "\n"
                + "Product: " + product + "\n"
                + "VAT: R " + vat + "\n"
                + "COMMISSION: R " + commission + "\n"
                + "TOTAL: R " + total,
                "Message", JOptionPane.INFORMATION_MESSAGE);

        saveToFile(customer, product, vat, commission, total);
    }

    private void saveToFile(String customer, String product,
                            double vat, double commission, double total) {
        try (PrintWriter out = new PrintWriter(new FileWriter("data.txt", true))) {
            out.println("DATA LOG");
            out.println("*".repeat(40));
            out.println("Customer Name: " + customer);
            out.println("Product: " + product);
            out.println("VAT: R " + vat);
            out.println("COMMISSION: R " + commission);
            out.println("TOTAL: R " + total);
            out.println("*".repeat(40));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not write to data.txt",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtCustomer.setText("");
        txtPrice.setText("");
        cmbProduct.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductApp().setVisible(true));
    }
}
