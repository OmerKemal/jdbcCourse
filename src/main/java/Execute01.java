import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Step: Register Driver - (optional)
        Class.forName("org.postgresql.Driver");

        //2. Step: Create Connection to the DB;
        Connection con = DriverManager.
                getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user",
                        "password");

        //3. Step: Create Statement -- to execute SQL queries
        Statement statement = con.createStatement();

        //to test if we have created the connection to DB successfully
        // System.out.println("Connected successfully!");

        //4. Step: Execute SQL Queries
        //TASK: create a table named "employee" with column names of
        //"employee_id", employee_name", "salary"
        // boolean sql1 = statement.execute("CREATE TABLE employee (employee_id INT, employee_name VARCHAR (50),salary REAL)");

        // System.out.println("sql1:" +sql1);

        /*execute() returns boolean value and can be used for DDL (Data Definition L.)
        or DQL (Data Query L.)

            --if it is used with DDL it returns false
            --if it is used with DQL (Select..) it returns true
         */

        //TASK 2: add Varchar (20) column name "city" to the employee table

        String query = "ALTER TABLE employee ADD COLUMN city VARCHAR (20)";
        // boolean sql2 = statement.execute(query);
        // System.out.println("sql2: " + sql2);

        // TASK 3: DELETE employee table from schema

        String query1= "DROP TABLE employee";
        statement.execute(query1);


        //5. Step: close connection and statement
        statement.close();
        con.close();




    }
}
