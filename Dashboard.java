import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard {

    public Dashboard() {
        // Create a JFrame (main window)
        JFrame frame = new JFrame("Project Management System Dashboard");
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

        // Create and add buttons with larger sizes
        JButton addButton = new JButton("Add Project");
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.setBounds(50, 100, 200, 50);
        backgroundPanel.add(addButton);

        JButton removeButton = new JButton("Remove Project");
        removeButton.setFont(new Font("Arial", Font.BOLD, 20));
        removeButton.setBounds(50, 170, 200, 50);
        backgroundPanel.add(removeButton);

        JButton searchButton = new JButton("Search Project");
        searchButton.setFont(new Font("Arial", Font.BOLD, 20));
        searchButton.setBounds(50, 240, 200, 50);
        backgroundPanel.add(searchButton);

        JButton signOutButton = new JButton("Sign Out");
        signOutButton.setFont(new Font("Arial", Font.BOLD, 16));
        signOutButton.setBounds(50, 310, 200, 40);
        backgroundPanel.add(signOutButton);
             // Add ActionListener for the Add Project button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the dashboard frame
                new AddProject(); // Open the add project frame
            }
        });
        
            // Add ActionListener for the Remove Project button
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the dashboard frame
                new RemoveProject(); // Open the remove project frame
            }
        });
        // Add ActionListener for the Sign Out button
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the dashboard frame
                new Login(); // Open the login frame
            }
        });
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the dashboard frame
                new SearchProject(); // Open the search project frame
            }
        });

        // Add Minimize and Maximize Buttons
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        controlPanel.setOpaque(false); // Transparent background

        // Add Minimize button
        JButton minimizeButton = new JButton("-");
        minimizeButton.setFont(new Font("Arial", Font.BOLD, 16));
        minimizeButton.setPreferredSize(new Dimension(50, 30));
        minimizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setState(JFrame.ICONIFIED);
            }
        });
        controlPanel.add(minimizeButton);

        // Add Maximize button
        JButton maximizeButton = new JButton("[]");
        maximizeButton.setFont(new Font("Arial", Font.BOLD, 16));
        maximizeButton.setPreferredSize(new Dimension(50, 30));
        maximizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frame.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                    frame.setExtendedState(JFrame.NORMAL);
                } else {
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
            }
        });
        controlPanel.add(maximizeButton);

        // Add the control panel to the frame
        controlPanel.setBounds(frame.getWidth() - 120, 10, 120, 30);
        backgroundPanel.add(controlPanel);

        // Add the background panel to the frame
        frame.add(backgroundPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
