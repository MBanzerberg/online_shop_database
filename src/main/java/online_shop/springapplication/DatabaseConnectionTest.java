package online_shop.springapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    public static void main(String[] args) {
        String url="jdbc:oracle:thin:@194.29.170.4:1521:XE";
        String user = "BDBTGRC01";
        String password = "BDBTGRC01";

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver is loaded successfully");
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection is successful with"+" "+con);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
