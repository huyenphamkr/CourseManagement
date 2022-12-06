/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAnPhanLop;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ad
 */
public class StudentDAL extends ConnectDatabase{
    public StudentDAL(){
        super();
        this.getConnect();
    }
    public void readStudents() throws SQLException {
        String query = "SELECT * FROM Person WHERE EnrollmentDate >0";
        ResultSet rs = this.ExcuteSELECT(query);
        if (rs != null) {
            int i = 1;
            while (rs.next()) {
                System.out.print(i + " - ");
                System.out.println(rs.getString("Lastname") + " " + rs.getString("Firstname"));
                i++;
            }
        }
    }
    public static void main(String[] args) throws SQLException {
        StudentDAL students = new StudentDAL();
        students.readStudents();
    }
    
}
