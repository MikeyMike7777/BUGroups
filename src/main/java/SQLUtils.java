import java.sql.*;  // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package


/*
    This is pretty much the only template I found for calling SQL Queries in
    Java.
    TODO:: Add the MySQL JDBC Driver to the project Libraries?
            Need to include both Mac and Windows options, maybe means
            we will have to know operating system before running queries?
            https://dev.mysql.com/downloads/connector/j/8.0.html
            https://www3.ntu.edu.sg/home/ehchua/programming/java/JDBC_Basic.html#:~:text=STEP%201%3A%20Allocate%20a%20Connection,4%3A%20Process%20the%20query%20result.
 */
public class SQLUtils {   // Save as "JdbcSelectTest.java"
    public static void main(String[] args) {
        try (
                // Step 1: Construct a database 'Connection' object called 'conn'
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                        "myuser", "xxxx");   // For MySQL only
                // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

                // Step 2: Construct a 'Statement' object called 'stmt' inside the Connection created
                Statement stmt = conn.createStatement();
        ) {
            // Step 3: Write a SQL query string. Execute the SQL query via the 'Statement'.
            //  The query result is returned in a 'ResultSet' object called 'rset'.
            String strSelect = "select title, price, qty from books";
            System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging

            ResultSet rset = stmt.executeQuery(strSelect);

            // Step 4: Process the 'ResultSet' by scrolling the cursor forward via next().
            //  For each row, retrieve the contents of the cells with getXxx(columnName).
            System.out.println("The records selected are:");
            int rowCount = 0;
            // Row-cursor initially positioned before the first row of the 'ResultSet'.
            // rset.next() inside the whole-loop repeatedly moves the cursor to the next row.
            // It returns false if no more rows.
            while(rset.next()) {   // Repeatedly process each row
                String title = rset.getString("title");  // retrieve a 'String'-cell in the row
                double price = rset.getDouble("price");  // retrieve a 'double'-cell in the row
                int    qty   = rset.getInt("qty");       // retrieve a 'int'-cell in the row
                System.out.println(title + ", " + price + ", " + qty);
                ++rowCount;
            }
            System.out.println("Total number of records = " + rowCount);

        } catch(SQLException ex) {
            ex.printStackTrace();
        }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
    }
}