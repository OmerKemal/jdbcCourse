import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws SQLException {
        Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement statement = con.createStatement();

        System.out.println("************** Task-1 **************");
        //Task-1: Print department name and grade of department which has second highest pass_grade

        String query="SELECT department, pass_grade FROM departments ORDER BY pass_grade DESC OFFSET 1 LIMIT 1";

        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            System.out.println(resultSet.getInt("pass_grade")+"--"+resultSet.getString("department"));
        }

        System.out.println("************** Task-3 **************");
        //Task-3: List department name, campus and highest grades of students from every department
        String query2 = "SELECT department, campus, (SELECT MAX (grade) FROM students s WHERE d.department=s.department) " +
                "AS max_grade FROM departments d";
        ResultSet resultSet2=statement.executeQuery(query2);
        while (resultSet2.next()){
            System.out.println(resultSet2.getString("department")+"--"+resultSet2.getString("campus")+"--"
            +resultSet2.getInt("max_grade"));
        }



        statement.close();
        con.close();
    }
}
