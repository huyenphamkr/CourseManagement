package BUS;

import DAO.CourseInstructorDAO;
import DTO.CourseInstructorDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseInstructorBUS {

    public static ArrayList<CourseInstructorDTO> CourseInstructorList;
    private CourseInstructorDAO ciDAO;
    public CourseInstructorBUS() 
    {
        if (CourseInstructorList == null) {
            CourseInstructorList = new ArrayList<>();
        }
        ciDAO = new CourseInstructorDAO();
    }

    public void ShowCourseInstructorList() throws SQLException 
    {        
        CourseInstructorList = ciDAO.GetCourseInstructorList();
    }

    public boolean AddCourseInstructor(CourseInstructorDTO ciDTO) throws SQLException {        
        CourseInstructorList.add(ciDTO);
        return ciDAO.add(ciDTO);
    }

    public boolean deletecourse(int idPerson, String courseTitle) throws SQLException {        
        int courseId = ciDAO.findCourseID(courseTitle);
        for (CourseInstructorDTO courseInstructor : CourseInstructorList) {
            if (courseInstructor.getPersonID() == idPerson && courseInstructor.getCourseID() == courseId) {
                CourseInstructorList.remove(courseInstructor);
                break;

            }
        }
        return ciDAO.delete(idPerson, courseTitle);
    }

    public boolean UpdateCourseInstructor(CourseInstructorDTO ciDTO, int oldPersonID, String oldCourseTitle) throws SQLException {        
        int courseId = ciDAO.findCourseID(ciDTO.getTitleCourse());
        int oldCourseId = ciDAO.findCourseID(oldCourseTitle);
        for (int i = 0; i < CourseInstructorList.size(); i++) {
            if (oldPersonID == CourseInstructorList.get(i).getPersonID() && oldCourseId == CourseInstructorList.get(i).getCourseID()) {
                CourseInstructorList.set(i, ciDTO);
                break;
            }
        }
        return ciDAO.update(ciDTO, oldPersonID, oldCourseTitle);
    }
    
    public ArrayList<CourseInstructorDTO> findCourseInstructorByTitle(String title) throws SQLException {        
        return ciDAO.findByName(title);
    }
}
