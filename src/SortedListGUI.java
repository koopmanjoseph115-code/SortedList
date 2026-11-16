import javax.swing.*;
import java.awt.*;

public class SortedListGUI extends JFrame {
    private SortedList sortedList;
    private JTextField addField;
    private JTextField searchField;
    private JTextArea displayArea;

    public SortedListGUI() {
        sortedList = new SortedList();

        // Frame Setup
        setTitle("Lab 10: SortedList with Binary Search");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Top Panel for Controls
        JPanel controlPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        // Add Row
        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addField = new JTextField(20);
        JButton addButton = new JButton("Add String");
        addPanel.add(new JLabel("Enter String to Add:     "));
        addPanel.add(addField);
        addPanel.add(addButton);

        // Search Row
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search String");
        searchPanel.add(new JLabel("Enter String to Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        controlPanel.add(addPanel);
        controlPanel.add(searchPanel);
        add(controlPanel, BorderLayout.NORTH);

        // Center Panel for TextArea Output
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Operations & Current Sorted List"));
        add(scrollPane, BorderLayout.CENTER);

        // Event Listeners
        addButton.addActionListener(e -> handleAdd());
        addField.addActionListener(e -> handleAdd()); // Allow pressing Enter

        searchButton.addActionListener(e -> handleSearch());
        searchField.addActionListener(e -> handleSearch()); // Allow pressing Enter
    }

    private void handleAdd() {
        String text = addField.getText().trim();
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a string to add.");
            return;
        }

        int insertedIndex = sortedList.add(text);
        addField.setText("");
        addField.requestFocus();

        updateDisplay("ADDED: \"" + text + "\" at index " + insertedIndex);
    }

    private void handleSearch() {
        String target = searchField.getText().trim();
        if (target.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a string to search.");
            return;
        }

        int resultIndex = sortedList.binarySearch(target);
        String statusMessage;

        if (resultIndex >= 0) {
            statusMessage = "SEARCH FOUND: \"" + sortedList.get(resultIndex) + "\" is at index [" + resultIndex + "].";
        } else {
            int insertionPoint = -(resultIndex + 1);
            statusMessage = "SEARCH NOT FOUND: \"" + target + "\" would be inserted at index [" + insertionPoint + "].";
        }

        searchField.setText("");
        searchField.requestFocus();
        updateDisplay(statusMessage);
    }

    private void updateDisplay(String lastAction) {
        displayArea.setText("Last Operation:\n" + lastAction + "\n");
        displayArea.append("--------------------------------------------------\n");
        displayArea.append("Current SortedList (" + sortedList.size() + " elements):\n");
        displayArea.append(sortedList.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SortedListGUI().setVisible(true);
        });
    }
}
