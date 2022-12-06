package BUS;

import DAO.CourseDAO;
import DTO.CourseDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseBUS {

    public static ArrayList<CourseDTO> CourseList;
    CourseDAO cDAO;

    public CourseBUS() {
        if (CourseList == null) {
            CourseList = new ArrayList<>();
        }
        cDAO = new CourseDAO();
    }

    public void ShowCourseList() throws SQLException {
        CourseList = cDAO.GetCourseList();
    }

    public boolean addcourseonline(CourseDTO c) throws SQLException {
        cDAO.addcourse(c);
        return cDAO.addonlinecourse(c);

    }

    public boolean addcourseonsite(CourseDTO c) throws SQLException {        
        cDAO.addcourse(c);
        return cDAO.addonsitecourse(c);

    }

    public void deletecourse(int id) {        
        cDAO.deleteonlinecourse(id);
        cDAO.deleteonsitecourse(id);
        cDAO.deletecourse(id);
        for (CourseDTO course : CourseList) {
            if (course.getCourseID() == id) {
                CourseList.remove(course);
                break;
            }
        }
    }

    public boolean deleteonlinecourse(int id) throws SQLException {
        return cDAO.deleteonlinecourse(id);
    }

    public boolean deleteonsitecourse(int id) throws SQLException {
        return cDAO.deleteonsitecourse(id);
    }

    public void updatecourse(CourseDTO c) {
        cDAO.updatecourse(c);
        updatelist(c);
    }

    public boolean updateonlinecourse(CourseDTO c) {
        updatelist(c);
        cDAO.updatecourse(c);
        return cDAO.updateonlinecourse(c);
    }

    public boolean updateonsitecourse(CourseDTO c) {
        updatelist(c);
        cDAO.updatecourse(c);
        return cDAO.updateonsitecourse(c);
    }

    private void updatelist(CourseDTO c) {
        for (int i = 0; i < CourseList.size(); i++) {
            if (c.getCourseID() == CourseList.get(i).getCourseID()) {
                CourseList.set(i, c);
            }
        }
    }

    public ArrayList<CourseDTO> findIDonline(String ID) throws SQLException {
        return cDAO.FindIDonline(ID);
    }

    public ArrayList<CourseDTO> findIDonsite(String ID) throws SQLException {
        return cDAO.FindIDonsite(ID);
    }
}
