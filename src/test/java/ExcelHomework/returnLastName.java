package ExcelHomework;

import org.testng.annotations.Test;

import java.sql.*;

public class returnLastName {


    public static void dataBase(String candidateId){
        //Use localhost with port 1433 in database url
        String url = "jdbc:sqlserver://localhost:1433";
        String user = "user1";
        String password = "admin";


        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt0 = conn.createStatement();

            String sql = "SELECT lastName FROM students where id = ?";
            ResultSet rs;

            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, candidateId);

            rs = stmt.executeQuery();

            while(rs.next()){
                //and returns lastname
                String lastName = rs.getString("lastName");
                System.out.println(lastName);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void main(String[] args) {
        //Create stored procedure that receives student id as a parameter
        dataBase("1002");
    }
}
