package BUS;

import DAO.StudentGradeDAO;
import DTO.StudentGradeDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentGradeBUS {
    public static ArrayList<StudentGradeDTO> sgList;
    StudentGradeDAO sgDAO ;
    public StudentGradeBUS() {
        if (sgList == null) {
            sgList = new ArrayList<>();
        }
        sgDAO = new StudentGradeDAO();
    }
    
    public void GetStudentGradeList() throws SQLException{        
        sgList = sgDAO.GetStudentGradeList();
    }
    
    public boolean AddStudentGradeList(StudentGradeDTO sgDTO) throws SQLException {        
        sgList.add(sgDTO);
        return sgDAO.addStudentGrade(sgDTO);
        
    }
    public ArrayList findByID(String personID) throws SQLException{        
        return sgDAO.FindByID(personID);
    }
    public boolean UpdateStudentGrade(StudentGradeDTO sgDTO) throws SQLException{
        for (int i = 0; i < sgList.size(); i++) {
            if (sgDTO.getPersonID() == sgList.get(i).getPersonID() && sgDTO.getCourseTitle() == sgList.get(i).getCourseTitle()) {
                sgList.set(i, sgDTO);
            }
        }
        return sgDAO.UpdateStudentGrade(sgDTO);
    }
    public boolean DeleteStudentGrade(int idStudent, String CourseTitle) throws SQLException{        
        for (StudentGradeDTO sgDTO : sgList) {
            if (sgDTO.getPersonID() == idStudent && sgDTO.getCourseTitle().equals(CourseTitle)) {
                sgList.remove(sgDTO);
                break;
            }
        }
        return sgDAO.DeleteStudentGrade(idStudent, CourseTitle);
    }
}
