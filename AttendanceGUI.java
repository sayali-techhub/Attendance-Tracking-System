import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main Swing GUI for Student Attendance Management System.
 * Professional layout with input validation, scrollable output, and all required features.
 */
public class AttendanceGUI extends JFrame {
    private AttendanceManager manager = new AttendanceManager();
    private JTextField idField, nameField;
    private JTextArea outputArea;
    private JScrollPane scrollPane;
    private int currentId = -1; // For operations needing ID

    public AttendanceGUI() {
        setTitle("Student Attendance Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        // Top Panel: Inputs
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Student ID:"));
        idField = new JTextField(10);
        topPanel.add(idField);
        topPanel.add(new JLabel("Name:"));
        nameField = new JTextField(15);
        topPanel.add(nameField);

        // Middle Panel: Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        addButton(buttonPanel, "Add Student", e -> addStudent());
        addButton(buttonPanel, "Update Student", e -> updateStudent());
        addButton(buttonPanel, "Remove Student", e -> removeStudent());
        addButton(buttonPanel, "Mark Present", e -> markAttendance(true));
        addButton(buttonPanel, "Mark Absent", e -> markAttendance(false));
        addButton(buttonPanel, "Search Student", e -> searchStudent());
        addButton(buttonPanel, "View All Students", e -> viewAll());
        addButton(buttonPanel, "Show Defaulters", e -> showDefaulters());
        addButton(buttonPanel, "Show Topper", e -> showTopper());
        addButton(buttonPanel, "Total Classes", e -> showTotalClasses());
        addButton(buttonPanel, "Clear Output", e -> clearOutput());
        addButton(buttonPanel, "Exit", e -> System.exit(0));

        // Bottom Panel: Output
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        scrollPane = new JScrollPane(outputArea);

        // Layout
        setLayout(new BorderLayout(10, 10));
        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        appendOutput("Student Attendance Management System Ready!\nTotal Students: 0 | Total Classes: 0\n");
    }

    private void addButton(JPanel panel, String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        panel.add(button);
    }

    // Validate inputs
    private boolean validateInputs() {
        try {
            currentId = Integer.parseInt(idField.getText().trim());
            if (currentId <= 0) throw new NumberFormatException();
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                showError("Name cannot be empty.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            showError("Invalid ID. Enter positive integer.");
            return false;
        }
    }

    private void appendOutput(String message) {
        outputArea.append(message + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
        updateStatus();
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void updateStatus() {
        String status = String.format("\nTotal Students: %d | Total Classes: %d\n",
                manager.getTotalStudents(), manager.getTotalClassesConducted());
        // Note: Status updated in appendOutput
    }

    // Button actions
    private void addStudent() {
        if (!validateInputs()) return;
        if (manager.addStudent(currentId, nameField.getText().trim())) {
            appendOutput("Student added successfully.");
        } else {
            showError("Student ID already exists.");
        }
        clearFields();
    }

    private void updateStudent() {
        if (!validateInputs()) return;
        if (manager.updateStudentName(currentId, nameField.getText().trim())) {
            appendOutput("Student updated successfully.");
        } else {
            showError("Student not found.");
        }
    }

    private void removeStudent() {
        if (!validateInputs()) return;
        if (manager.removeStudent(currentId)) {
            appendOutput("Student removed successfully.");
        } else {
            showError("Student not found.");
        }
        clearFields();
    }

    private void markAttendance(boolean present) {
        if (!validateInputs()) return;
        String status = present ? "Present" : "Absent";
        if (manager.markAttendance(currentId, present)) {
            appendOutput("Marked " + status + " for student ID: " + currentId);
        } else {
            showError("Student not found.");
        }
    }

    private void searchStudent() {
        if (!validateInputs()) return;
        appendOutput(manager.getStudentReport(currentId));
    }

    private void viewAll() {
        appendOutput("=== ALL STUDENTS ===\n" + manager.viewAllStudents());
    }

    private void showDefaulters() {
        appendOutput("=== DEFAULTERS ===\n" + manager.showDefaulters());
    }

    private void showTopper() {
        appendOutput("=== TOPPER ===\n" + manager.showTopper());
    }

    private void showTotalClasses() {
        appendOutput("Total Classes Conducted: " + manager.getTotalClassesConducted());
    }

    private void clearOutput() {
        outputArea.setText("");
        appendOutput("Output cleared.");
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
    }

    // Main method to launch GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AttendanceGUI().setVisible(true));
    }
}
