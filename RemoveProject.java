import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RemoveProject {

    private JFrame frame;
    private JTextField idField;

    public RemoveProject() {
        // Create the main frame
        frame = new JFrame("Remove Project");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create a background panel with image
        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon("C:\\Users\\MY\\OneDrive\\Documents\\NetBeansProjects\\Project_Management\\src\\main\\java\\bb.jpg").getImage());
        frame.setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout());

        // Create a panel for removing project
        JPanel removePanel = new JPanel();
        removePanel.setLayout(new GridBagLayout());
        removePanel.setOpaque(false); // Make panel transparent
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Create and add components
        JLabel idLabel = new JLabel("Enter Project ID to Remove:");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Medium font size
        idLabel.setForeground(Color.WHITE); // White color
        gbc.gridx = 0;
        gbc.gridy = 0;
        removePanel.add(idLabel, gbc);

        idField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        removePanel.add(idField, gbc);

        JButton deleteButton = new JButton("Delete Project");
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 18)); // Medium font size
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        removePanel.add(deleteButton, gbc);

        backgroundPanel.add(removePanel, BorderLayout.CENTER);

        // Add action listener for delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                deleteProject(id);
            }
        });

        // Create a Back button to return to the Dashboard
        JButton backButton = new JButton("Back to Dashboard");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18)); // Medium font size
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the remove project frame
                new Dashboard(); // Open the dashboard frame
            }
        });

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setOpaque(false); // Make panel transparent
        backPanel.add(backButton);
        backgroundPanel.add(backPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void deleteProject(String id) {
        String url = "jdbc:mysql://localhost:3306/user_db";
        String dbUsername = "root";
        String dbPassword = "06ajAJ@@";

        String query = "DELETE FROM projects WHERE id = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(id));

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Project deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Project ID does not exist", "Error", JOptionPane.ERROR_MESSAGE);
            }

            pstmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error deleting project", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new RemoveProject();
    }

    static class BackgroundPanel extends JPanel {

        private Image backgroundImage;

        public BackgroundPanel(Image image) {
            this.backgroundImage = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
