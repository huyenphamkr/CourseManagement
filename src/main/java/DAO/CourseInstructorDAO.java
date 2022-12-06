package DAO;

import DTO.CourseDTO;
import DTO.CourseInstructorDTO;
import DoAnPhanLop.ConnectDatabase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseInstructorDAO {
    
    public ArrayList<CourseInstructorDTO> GetCourseInstructorList() throws SQLException {
        ArrayList<CourseInstructorDTO> courseInstructorList = new ArrayList<>();
        String sql = "select * from courseinstructor left JOIN course on courseinstructor.CourseID = course.CourseID"
                + " left join person on person.PersonID = courseinstructor.PersonID";
        ResultSet rs = ConnectDatabase.GetInstance().ExcuteSELECT(sql);
        while (rs.next()) {
            CourseInstructorDTO ciDTO = new CourseInstructorDTO();
            ciDTO.setPersonID(rs.getInt("person.PersonID"));
            ciDTO.setFirstName(rs.getString("person.Firstname"));
            ciDTO.setLastName(rs.getString("person.Lastname"));
            ciDTO.setCourseID(rs.getInt("courseinstructor.CourseID"));
            ciDTO.setTitleCourse(rs.getString("course.Title"));

            courseInstructorList.add(ciDTO);
        }
        return courseInstructorList;
    }

    public int findCourseID(String title) throws SQLException {
        String sql1 = "SELECT courseinstructor.CourseID, `PersonID` FROM `courseinstructor`, `course` WHERE course.Title = '" + title + "' AND courseinstructor.CourseID = course.CourseID ";
        ResultSet rs1 = ConnectDatabase.GetInstance().ExcuteSELECT(sql1);
        if (rs1.next()) {
            return rs1.getInt("CourseID");
        } else {
            System.err.println("Can't find the course");
            return 0;
        }
    }

    public boolean add(CourseInstructorDTO ctDTO) throws SQLException {
        int courseId = findCourseID(ctDTO.getTitleCourse());

        String sql2 = String.format("INSERT INTO `courseinstructor`(`CourseID`, `PersonID`) VALUES ('%d', '%d')",
                courseId, ctDTO.getPersonID());
        String result = ConnectDatabase.GetInstance().ExcuteINSERTDELETEUPDATE(sql2);
        if (result == "Thành công") {
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(int idPerson, String title) throws SQLException {
        int courseId = findCourseID(title);

        String sql = String.format("DELETE FROM `courseinstructor` WHERE CourseID='%d' AND PersonID='%d'", courseId, idPerson);
        String result = ConnectDatabase.GetInstance().ExcuteINSERTDELETEUPDATE(sql);
        if (result == "Thành công") {
            return true;
        } else {
            return false;
        }
    }

    public boolean update(CourseInstructorDTO ciDTO, int oldPersonID, String oldCourseTitle) throws SQLException {
        int courseId = findCourseID(ciDTO.getTitleCourse());
        int oldCourseId = findCourseID(oldCourseTitle);
        String sql = String.format("UPDATE `courseinstructor`"
                + "SET PersonID='%d', CourseID='%d' "
                + "WHERE PersonID='%d' AND CourseID='%d'",
                ciDTO.getPersonID(), courseId, oldPersonID, oldCourseId);
        System.out.println(sql);
        String result = ConnectDatabase.GetInstance().ExcuteINSERTDELETEUPDATE(sql);
        if (result == "Thành công") {
            return true;
        } else {
            return false;
        }
    }
    
    public ArrayList<CourseInstructorDTO> findByName (String courseTitle) throws SQLException{
        ArrayList<CourseInstructorDTO> searchList = new ArrayList<>();
        
        int courseId = findCourseID(courseTitle);
         String sql = String.format("select * from courseinstructor left JOIN course on courseinstructor.CourseID = course.CourseID"
                + " left join person on person.PersonID = courseinstructor.PersonID WHERE courseinstructor.CourseID = '%d'", courseId);
      
        ResultSet rs = ConnectDatabase.GetInstance().ExcuteSELECT(sql);        
        while (rs.next()) {
           CourseInstructorDTO ciDTO = new CourseInstructorDTO();
            ciDTO.setPersonID(rs.getInt("person.PersonID"));
            ciDTO.setFirstName(rs.getString("person.Firstname"));
            ciDTO.setLastName(rs.getString("person.Lastname"));
            ciDTO.setCourseID(rs.getInt("courseinstructor.CourseID"));
            ciDTO.setTitleCourse(rs.getString("course.Title"));
            searchList.add(ciDTO);
        }
        return searchList;
    }
}
