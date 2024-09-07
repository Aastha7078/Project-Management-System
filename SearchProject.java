import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SearchProject {

    private JFrame frame;
    private JTable table;
    private JTextField searchField;
    private DefaultTableModel tableModel;

    public SearchProject() {
        // Create the main frame
        frame = new JFrame("Search Project");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create a background panel with image
        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon("C:\\Users\\MY\\OneDrive\\Documents\\NetBeansProjects\\Project_Management\\src\\main\\java\\bb.jpg").getImage());
        frame.setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout());

        // Create the table model and table
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);

        // Add table columns
        tableModel.addColumn("ID");
        tableModel.addColumn("Teamname");
        tableModel.addColumn("Project Name");
        tableModel.addColumn("Team Members");
        tableModel.addColumn("Duration");
        tableModel.addColumn("Description");
        tableModel.addColumn("GitHub Link");

        // Fetch data from the database and add it to the table
        fetchAndDisplayData();

        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);

      // Create a panel for search functionality
JPanel searchPanel = new JPanel(new FlowLayout());
searchPanel.setOpaque(false); // Make panel transparent
searchPanel.setBackground(new Color(0, 0, 0, 100)); // Semi-transparent background

JLabel searchLabel = new JLabel("Search by ID or Team Name:");
searchLabel.setForeground(Color.WHITE); // Set the label text color to white
searchLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Increase font size and make it bold
searchLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding around the label

searchField = new JTextField(90);
JButton searchButton = new JButton("Search");

searchPanel.add(searchLabel);
searchPanel.add(searchField);
searchPanel.add(searchButton);

backgroundPanel.add(searchPanel, BorderLayout.NORTH);


        // Add action listener for search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                searchAndHighlightProject(searchText);
            }
        });

        // Create a Back button to return to the Dashboard
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Dashboard frame and close the current frame
                new Dashboard(); // Assuming you have a DashboardForm class
                frame.dispose();
            }
        });

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setOpaque(false); // Make panel transparent
        backPanel.add(backButton);
        backgroundPanel.add(backPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void fetchAndDisplayData() {
        String url = "jdbc:mysql://localhost:3306/user_db";
        String dbUsername = "root";
        String dbPassword = "06ajAJ@@";

        String query = "SELECT * FROM projects";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Add rows to the table model
            while (rs.next()) {
                int id = rs.getInt("id");
                String Teamname = rs.getString("Teamname");
                String projectName = rs.getString("project_name");
                String teamMembers = rs.getString("team_members");
                String duration = rs.getString("duration");
                String description = rs.getString("description");
                String githubLink = rs.getString("github_link");

                tableModel.addRow(new Object[]{id, Teamname, projectName, teamMembers, duration, description, githubLink});
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

private void searchAndHighlightProject(String searchText) {
    // Clear previous selection
    table.clearSelection();

    boolean projectFound = false; // Flag to track if a project is found

    for (int i = 0; i < table.getRowCount(); i++) {
        String id = table.getValueAt(i, 0).toString();
        String teamName = table.getValueAt(i, 1).toString();

        // Case-insensitive comparison for team name and id
        if (id.equalsIgnoreCase(searchText) || teamName.equalsIgnoreCase(searchText)) {
            table.setRowSelectionInterval(i, i);
            table.scrollRectToVisible(new Rectangle(table.getCellRect(i, 0, true)));
            projectFound = true; // Set the flag to true if project is found
            break;
        }
    }

    // Show a popup message if no project is found
    if (!projectFound) {
        JOptionPane.showMessageDialog(frame, "Project not found", "Search Result", JOptionPane.INFORMATION_MESSAGE);
    }
}



    public static void main(String[] args) {
        new SearchProject();
    }
}

class BackgroundPanel extends JPanel {

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
