# Project Management System

A comprehensive Java Swing desktop application designed to efficiently manage and track projects. This application provides a user-friendly interface for adding, searching, and removing projects with secure database integration.

## 🚀 Features

- **Secure Login System**: Simple authentication mechanism
- **Project Management**: Complete CRUD operations for project data
- **Search Functionality**: Find projects quickly with an intuitive search interface
- **Database Integration**: Seamless MySQL database connectivity using JDBC
- **User-Friendly GUI**: Modern Swing interface with custom background themes
- **Data Integrity**: Secure and reliable data storage

## 🛠️ Technologies Used

- **Java**: Core application language
- **Java Swing**: GUI framework for desktop interface
- **JDBC**: Database connectivity
- **MySQL**: Database management system
- **Eclipse IDE**: Development environment

## 📋 Prerequisites

Before running this application, ensure you have the following installed:

- **Java Development Kit (JDK) 8 or higher**
- **MySQL Server 5.7 or higher**
- **MySQL Connector/J** (JDBC driver)
- **Eclipse IDE** (optional, for development)

## 🗄️ Database Setup

1. Install and start MySQL Server on your local machine
2. Create a database named `user_db`
3. Create the projects table using the following SQL schema:

```sql
CREATE DATABASE user_db;
USE user_db;

CREATE TABLE projects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    Teamname VARCHAR(255) NOT NULL,
    project_name VARCHAR(255) NOT NULL,
    team_members TEXT,
    duration VARCHAR(100),
    description TEXT,
    github_link VARCHAR(500)
);
```

4. Update database credentials in the Java files if needed:
   - **Default Username**: `root`
   - **Default Password**: `06ajAJ@@` (update this in the source code to match your MySQL setup)
   - **Database URL**: `jdbc:mysql://localhost:3306/user_db`

## 🚀 Installation & Setup

### Option 1: Running Pre-compiled Classes
1. Clone the repository:
   ```bash
   git clone https://github.com/Aastha7078/Project-Management-System.git
   cd Project-Management-System
   ```

2. Ensure you have the MySQL JDBC driver in your classpath

3. Update the background image path in the source files to match your system (currently hardcoded)

4. Run the application:
   ```bash
   java Login
   ```

### Option 2: Compiling from Source
1. Clone the repository
2. Open the project in Eclipse or your preferred Java IDE
3. Add MySQL JDBC driver to your project's build path
4. Update database credentials and image paths as needed
5. Compile and run the `Login.java` class

## 📱 Usage

### Login
- **Username**: `admin`
- **Password**: `admin123`

### Main Dashboard
After successful login, you'll access the main dashboard with options to:

1. **Add Project**: Create new project entries with team details
2. **Search Project**: View and search through existing projects
3. **Remove Project**: Delete projects by their ID

### Adding a Project
Fill in the following information:
- Team Name
- Project Name  
- Team Members
- Duration
- Project Description
- GitHub Repository Link

### Searching Projects
- View all projects in a table format
- Search functionality to filter projects

### Removing Projects
- Enter the project ID to delete a specific project
- Confirmation prompts ensure data safety

## 📁 Project Structure

```
Project-Management-System/
├── Login.java              # Main entry point and login interface
├── Dashboard.java          # Main navigation dashboard
├── AddProject.java         # Project addition functionality
├── SearchProject.java      # Project search and display
├── RemoveProject.java      # Project removal functionality
├── bb.jpg                  # Background image for GUI
├── README.md              # This documentation
└── .project               # Eclipse project configuration
```

## 🎯 Use Cases

- **Academic Institutions**: Track student project submissions and progress
- **Small Teams**: Manage team projects and assignments
- **Project Coordinators**: Maintain project databases with team information
- **Replacement for Excel**: More secure and feature-rich than spreadsheet tracking

## 🔧 Configuration

### Database Configuration
Update the following variables in each Java file as needed:
```java
String url = "jdbc:mysql://localhost:3306/user_db";
String dbUsername = "root"; 
String dbPassword = "your_password_here";
```

### Background Image
Update the image path in the constructor of each GUI class:
```java
ImageIcon backgroundImage = new ImageIcon("path/to/your/background.jpg");
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## ⚠️ Important Notes

- Ensure MySQL service is running before starting the application
- Update hardcoded file paths for the background image to match your system
- Default login credentials should be changed for production use
- Database credentials are currently hardcoded and should be externalized for security

## 📝 License

This project is open source and available under the [MIT License](LICENSE).

## 👨‍💻 Author

**Aastha** - *Initial work* - [Aastha7078](https://github.com/Aastha7078)

## 🐛 Known Issues

- Background image paths are hardcoded for Windows systems
- Database credentials are embedded in source code
- No input validation for database fields

## 🔮 Future Enhancements

- [ ] External configuration file for database settings
- [ ] Input validation and error handling improvements
- [ ] User role management system
- [ ] Export functionality for project data
- [ ] Enhanced search filters
- [ ] Cross-platform file path handling
