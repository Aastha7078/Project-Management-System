import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddProject {

    public AddProject() {
        // Create a JFrame (main window)
        JFrame frame = new JFrame("Add Project");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load the background image from a local path
        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\MY\\OneDrive\\Documents\\NetBeansProjects\\Project_Management\\src\\main\\java\\bb.jpg");

        // Create a JPanel with the background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null); // Allow absolute positioning

        // Create and position labels and text fields with improved visibility
        JLabel projectNoLabel = new JLabel("TeamName:");
        projectNoLabel.setBounds(50, 50, 120, 30);
        projectNoLabel.setForeground(Color.WHITE); // White text color
        backgroundPanel.add(projectNoLabel);

        JTextField projectNoText = new JTextField();
        projectNoText.setBounds(180, 50, 250, 30);
        backgroundPanel.add(projectNoText);

        JLabel projectNameLabel = new JLabel("Project Name:");
        projectNameLabel.setBounds(50, 100, 120, 30);
        projectNameLabel.setForeground(Color.WHITE); // White text color
        backgroundPanel.add(projectNameLabel);

        JTextField projectNameText = new JTextField();
        projectNameText.setBounds(180, 100, 250, 30);
        backgroundPanel.add(projectNameText);

        JLabel teamMembersLabel = new JLabel("Team Members:");
        teamMembersLabel.setBounds(50, 150, 120, 30);
        teamMembersLabel.setForeground(Color.WHITE); // White text color
        backgroundPanel.add(teamMembersLabel);

        JTextField teamMembersText = new JTextField();
        teamMembersText.setBounds(180, 150, 250, 30);
        backgroundPanel.add(teamMembersText);

        JLabel durationLabel = new JLabel("Duration:");
        durationLabel.setBounds(50, 200, 120, 30);
        durationLabel.setForeground(Color.WHITE); // White text color
        backgroundPanel.add(durationLabel);

        JTextField durationText = new JTextField();
        durationText.setBounds(180, 200, 250, 30);
        backgroundPanel.add(durationText);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(50, 250, 120, 30);
        descriptionLabel.setForeground(Color.WHITE); // White text color
        backgroundPanel.add(descriptionLabel);

        JTextArea descriptionText = new JTextArea();
        descriptionText.setBounds(180, 250, 250, 100);
        descriptionText.setLineWrap(true);
        descriptionText.setWrapStyleWord(true);
        backgroundPanel.add(descriptionText);

        JLabel githubLinkLabel = new JLabel("GitHub Link:");
        githubLinkLabel.setBounds(50, 370, 120, 30);
        githubLinkLabel.setForeground(Color.WHITE); // White text color
        backgroundPanel.add(githubLinkLabel);

        JTextField githubLinkText = new JTextField();
        githubLinkText.setBounds(180, 370, 250, 30);
        backgroundPanel.add(githubLinkText);

        // Create and add buttons
        JButton addButton = new JButton("Add");
        addButton.setBounds(180, 420, 100, 30);
        backgroundPanel.add(addButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(290, 420, 100, 30);
        backgroundPanel.add(backButton);

        // Add ActionListener for the Add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Teamname = projectNoText.getText();
                String projectName = projectNameText.getText();
                String teamMembers = teamMembersText.getText();
                String duration = durationText.getText();
                String description = descriptionText.getText();
                String githubLink = githubLinkText.getText();

                // Add the project to the database
                addProjectToDatabase(Teamname, projectName, teamMembers, duration, description, githubLink);
            }
        });

        // Add ActionListener for the Back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the Add Project frame
                new Dashboard(); // Open the Dashboard frame
            }
        });

        // Add the background panel to the frame
        frame.add(backgroundPanel);
        frame.setVisible(true);
    }

    private void addProjectToDatabase(String Teamname, String projectName, String teamMembers, String duration, String description, String githubLink) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/user_db";
        String dbUsername = "root";
        String dbPassword = "06ajAJ@@";

        String query = "INSERT INTO projects (Teamname, project_name, team_members, duration, description, github_link) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, Teamname);
            stmt.setString(2, projectName);
            stmt.setString(3, teamMembers);
            stmt.setString(4, duration);
            stmt.setString(5, description);
            stmt.setString(6, githubLink);
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Project added successfully!");
            
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding project", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new AddProject();
    }
}
