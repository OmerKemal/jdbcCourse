import java.sql.*;

public class ExecuteUpdate {

    public static void main(String[] args) throws SQLException {
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement statement = con.createStatement();

        //TASK-1. Update salaries of developers whose salaries are less than average salary with average salary

        String query= "UPDATE developers SET salary = (SELECT AVG (salary)) WHERE salary < (SELECT AVG (salary) FROM developers)";
        // int updatedRows=statement.executeUpdate(query); // returns integer - number of records to be changed
        // System.out.println("Updated rows: " + updatedRows);

        String query1 = "SELECT name, salary FROM developers";
        ResultSet resultSet=statement.executeQuery(query1);
        while (resultSet.next()){
            System.out.println(resultSet.getString("name") + "--" +resultSet.getInt("salary"));
        }

        //TASK-2. Add new developer to developers table
        //String query2="INSERT INTO developers VALUES (21, 'Bedreddin', 6000, 'Python')";
        //int update=statement.executeUpdate(query2);
        //System.out.println(update);


        //TASK-3. DELETE row which has id of 14

        //String query3="DELETE FROM developers WHERE id=14";
        //int update2=statement.executeUpdate(query3);

        //TASK-4. DELETE rows from developers table if  prog_lang is "Ruby"
        String query4="DELETE  FROM developers WHERE prog_lang ILIKE 'ruby'";
        int update3=statement.executeUpdate(query4);
        System.out.println(update3);







        statement.close();
        con.close();
    }
}
