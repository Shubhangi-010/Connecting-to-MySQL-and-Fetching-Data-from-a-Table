package parta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FetchDataExample {
     private static final String DB_URL = "jdbc:mysql://localhost:3306/demo_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password";
     private static final String SELECT_QUERY = "SELECT EmpID, Name, Salary FROM Employee";

    public static void main(String[] args) { 
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        System.out.println("--- Starting Employee Data Fetch Program ---");
        
        try { 
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("JDBC Driver loaded successfully.");

             connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Successfully connected to the database: " + DB_URL);

             statement = connection.createStatement();
            System.out.println("Executing SQL query: " + SELECT_QUERY);

             resultSet = statement.executeQuery(SELECT_QUERY);

             System.out.println("\n--------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-15s |\n", "ID", "Name", "Salary");
            System.out.println("--------------------------------------------------");
            
            boolean found = false;
            while (resultSet.next()) {
                 int empId = resultSet.getInt("EmpID");
                String name = resultSet.getString("Name");
                double salary = resultSet.getDouble("Salary");
                
                System.out.printf("| %-5d | %-20s | $%-14.2f |\n", empId, name, salary);
                found = true;
            }
            System.out.println("--------------------------------------------------");

            if (!found) {
                System.out.println("No records found in the Employee table.");
            }

        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found. Ensure the MySQL Connector/J JAR is in your classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("A database error occurred.");
             e.printStackTrace();
        } finally {
             try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
                System.out.println("\nDatabase resources closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("--- Program finished ---");
    }
}