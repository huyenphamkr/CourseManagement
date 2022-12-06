package DAO;

import DTO.StudentGradeDTO;
import DoAnPhanLop.ConnectDatabase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentGradeDAO {
    

    public StudentGradeDAO() {  }
    ;
    public ArrayList<StudentGradeDTO> GetStudentGradeList() throws SQLException {
        ArrayList<StudentGradeDTO> sgList = new ArrayList<>();

        String sql = "select * from studentgrade";
        ResultSet rs = ConnectDatabase.GetInstance().ExcuteSELECT(sql);
        while (rs.next()) {
            StudentGradeDTO sgDTO = new StudentGradeDTO();
            String sql2 = "select course.Title,studentgrade.EnrollmentID  from studentgrade left join course on studentgrade.courseID = course.courseID where studentgrade.EnrollmentID =" + rs.getInt("EnrollmentID");
            ResultSet rs2 = ConnectDatabase.GetInstance().ExcuteSELECT(sql2);
            sgDTO.setEnrollmentID(rs.getInt("EnrollmentID"));
            while (rs2.next()) {
                sgDTO.setCourseTitle(rs2.getString("Title"));
            }
            sgDTO.setPersonID(rs.getInt("StudentID"));
            sgDTO.setGrade(rs.getFloat("Grade"));
            sgList.add(sgDTO);
        }
        return sgList;
    }

    public boolean addStudentGrade(StudentGradeDTO sgDTO) throws SQLException {
        String sql1 = "select CourseID from course where Title = '" + sgDTO.getCourseTitle() + "'";
        ResultSet rs1 = ConnectDatabase.GetInstance().ExcuteSELECT(sql1);
        int titleCourse = 0;
        if (rs1.next()) {            
          titleCourse = rs1.getInt("CourseID");
        } else {
            System.err.println("Can't find the course");
            return false;
        }
        String sql2 = String.format("INSERT INTO `StudentGrade`(`CourseID`, `StudentID`, `Grade`) VALUES ('%d', '%d', '%f')",
                titleCourse, sgDTO.getPersonID(), sgDTO.getGrade());
        String result = ConnectDatabase.GetInstance().ExcuteINSERTDELETEUPDATE(sql2);
        System.out.println(result);
        if (result == "Thành công") {
            return true;
        }else
            return false;
    }
    public ArrayList<StudentGradeDTO> FindByID(String PersonID) throws SQLException{        
        ArrayList<StudentGradeDTO> StudentGradeList = new ArrayList<>();
        int personID = Integer.parseInt(PersonID);
        String sql = String.format("SELECT * FROM `studentgrade` WHERE (`StudentID` = '%d')", personID);
        System.out.println(sql);
        ResultSet rs = ConnectDatabase.GetInstance().ExcuteSELECT(sql);        
        while (rs.next()) {
            StudentGradeDTO sgDTO = new StudentGradeDTO();
            sgDTO.setPersonID(rs.getInt("StudentID"));
            String sql2 = "select Title from course where CourseID = '" + rs.getInt("CourseID") + "'";
            System.out.println(sql2);
            ResultSet rs2 = ConnectDatabase.GetInstance().ExcuteSELECT(sql2);
            while (rs2.next()) {                
                sgDTO.setCourseTitle(rs2.getString("Title"));
            }
            sgDTO.setGrade(rs.getFloat("Grade"));
            StudentGradeList.add(sgDTO);            
        }
        return StudentGradeList;
    }
    public boolean UpdateStudentGrade(StudentGradeDTO sgDTO) throws SQLException{
        int courseId = findCourseID(sgDTO.getCourseTitle());
        String sql = String.format("UPDATE `studentgrade`"
                + "SET grade='%f'"
                + "WHERE StudentID='%d' AND CourseID='%d'",
                sgDTO.getGrade(), sgDTO.getPersonID(), courseId);
        System.out.println(sql);
        String result = ConnectDatabase.GetInstance().ExcuteINSERTDELETEUPDATE(sql);
        if (result == "Thành công") {
            return true;
        } else {
            return false;
        }
    }
    public int findCourseID(String title) throws SQLException {
        String sql1 = "SELECT CourseID  FROM `course` WHERE Title = '" + title + "'";
        ResultSet rs1 = ConnectDatabase.GetInstance().ExcuteSELECT(sql1);
        if (rs1.next()) {
            return rs1.getInt("CourseID");
        } else {
            System.err.println("Can't find the course");
            return 0;
        }
    }
    
    public boolean DeleteStudentGrade(int idStudent, String CourseTitle) throws SQLException{
        int courseId = findCourseID(CourseTitle);

        String sql = String.format("DELETE FROM `studentgrade` WHERE CourseID='%d' AND StudentID='%d'", courseId, idStudent);
        String result = ConnectDatabase.GetInstance().ExcuteINSERTDELETEUPDATE(sql);
        if (result == "Thành công") {
            return true;
        } else {
            return false;
        }
    }

}
