import org.testng.asserts.SoftAssert;

import java.sql.*;


public class DBgettingStarted {

    public static int insertCandidate(String ID, String Name,String surName, String phone) {
        // for insert a new candidate


        String insert = "INSERT INTO students(id,firstName,lastName,phone) "
                + "VALUES(?,?,?,?)";

        String url = "jdbc:sqlserver://localhost:1433";
        String user = "user1";
        String password = "admin";

        String sqlUpdate = "UPDATE students "
                + "SET firstName = ? "
                + "WHERE id = ?";



        int candidateId = 0;
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            //Use autosaved false mode
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM students";
            ResultSet rs = stmt.executeQuery(sql);

            candidateId = 0;




            PreparedStatement pstmt = conn.prepareStatement(insert,
                    Statement.RETURN_GENERATED_KEYS);


            // set parameters for statement

            pstmt.setString(1, ID);
            pstmt.setString(2, Name);
            pstmt.setString(3, surName);
            pstmt.setString(4, phone);

            int rowAffected = pstmt.executeUpdate();
            if (rowAffected == 1) {
                System.out.println("Row has added SUCCSSEFULLY");
                candidateId++;
            }else {
                System.out.println("row didnt add");
            }
            conn.commit();





            //Validate all the values of inserted row using TestNG
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals("1004",ID);
            softAssert.assertEquals("Nika",Name);
            softAssert.assertEquals("Jvelauri",surName);
            softAssert.assertEquals("555667788",phone);
            softAssert.assertAll();






            PreparedStatement pstmt1 = conn.prepareStatement(sqlUpdate);

            //Update firstName of added student
            String firstName = "Name";
            pstmt1.setString(1, firstName);
            pstmt1.setString(2,"1004");
            int rowAffected1 = pstmt1.executeUpdate();
            //Validate updated firstName using TestNG
            softAssert.assertEquals("Name",firstName);
            softAssert.assertAll();
            conn.commit();



        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        return candidateId;


    }

    public static void main(String[] args) {
        // insert a new candidate
        int id = insertCandidate("1004", "Nika", "Jvelauri", "555667788");

        System.out.println("A new candidate with has been inserted");

    }



}











