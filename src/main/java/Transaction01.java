
import java.sql.*;

public class Transaction01 {
    public static void main(String[] args) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement statement = con.createStatement();
        //TASK-1. Transfer amount of 1000 from account_nu:1234 to account_nu:5678

        con.setAutoCommit(false);//by default, it is true

        Savepoint sp= null;

        try {

            /*
                    **suppose there are other queries, these will be run
             */
            sp=con.setSavepoint();

            String query = "UPDATE accounts SET amount = amount + ? WHERE account_nu = ?";
            PreparedStatement prs = con.prepareStatement(query);
            prs.setInt(1, -1000);
            prs.setInt(2, 1234);
            prs.executeUpdate();

            //if  it is always true, we will get exception

            if(false){
                throw new Exception(); // if this was true, this exception would cancel
                // everything because at the end we used rollback method
            }
            prs.setInt(1, 1000);
            prs.setInt(2, 5678);
            prs.executeUpdate();

            con.commit(); //we have set this to false, now we are committing manually
            prs.close();
            statement.close();
            con.close();

        }catch (Exception e){
            con.rollback(sp); // cancels all previous activities
        }

    }

}
