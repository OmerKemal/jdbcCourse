import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException {
        //1 Step - now we can skip this step
        //2 Step: Create connection
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");

        //3: Create Statement

        Statement statement =  con.createStatement();

        //TASK-1. GET/SELECT  "country_name" from countries table with ID between 5 and 10
        System.out.println("************** TASK 2 **************");
        String query1 = "SELECT country_name FROM countries WHERE id BETWEEN 5 AND 10";
        boolean sql1 = statement.execute(query1);
        System.out.println("sql1: " + sql1);

        ResultSet resultSet = statement.executeQuery(query1);
        //resultSet.next(); // bu metod sıradakinde bir şey var mı diye kontrol ediyor eğer doğru anladıysam varsa true veryor
        //System.out.println(resultSet.getString("country_name")); // burada sadece bir tanesini veriyor, eğer ikinciyi almak istiyorsan yine resultSet.next() kullanmalısın,
        // bunu tekrar tekrar kullanmak yerine loop kullanacağız
        // we can also use index number of the columns with getString method

        while (resultSet.next()){
            System.out.println(resultSet.getString("country_name"));
            System.out.println(resultSet.getString(1)); // same result with the previous line

        }

        //ResultSet has method such as first(), last(), or next()
        //There is no method to iterate backward

        //TASK 2: Get "phone_code" and "country_name" from countries table
        //whose value is greater than 200
        System.out.println("************TASK 2**************");
        String query2= "SELECT phone_code, country_name FROM countries WHERE phone_code > 500";
        ResultSet resultSet2= statement.executeQuery(query2);
        while (resultSet2.next()){
            System.out.println(resultSet2.getInt("phone_code") + "--" +
                    resultSet2.getString("country_name"));
        }

        //TASK-3. Get all information about the developers whose salary is lowest
        System.out.println("************** TASK 3 **************");

        String query3="SELECT * FROM developers WHERE salary = (SELECT MIN(salary) FROM developers)";
        ResultSet resultSet3 = statement.executeQuery(query3);
        while (resultSet3.next()){
            System.out.println(resultSet3.getInt("id")+"--"
                    +resultSet3.getString("name")+"--"
                    +resultSet3.getString("salary"));
        }

        //TASK - 4 : Display students' name and grade whose grades are higher than average passing grade of departments.

        System.out.println("************** TASK 4 **************");

        String query4="SELECT name, grade FROM students WHERE grade > (SELECT AVG (pass_grade) FROM departments)";
        ResultSet resultSet4=statement.executeQuery(query4);
        while(resultSet4.next()){
        System.out.println(resultSet4.getString("name")+"--"
                +resultSet4.getInt("grade"));
        }

        statement.close();
        con.close();



    }
}
