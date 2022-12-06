package DAO;

import DTO.CourseDTO;
import DoAnPhanLop.ConnectDatabase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDAO {    

    public CourseDAO() {
    }
    public ArrayList<CourseDTO> GetCourseList() throws SQLException{
        ArrayList<CourseDTO> courseList = new ArrayList<>();
        String sql = "select * from course left JOIN onlinecourse on onlinecourse.CourseID = course.CourseID LEFT JOIN onsitecourse on course.CourseID = onsitecourse.CourseID";
        ResultSet rs = ConnectDatabase.GetInstance().ExcuteSELECT(sql);
        while(rs.next()) {
            String sql2 = "select * from department where DepartmentID ="+rs.getInt("DepartmentID");
            ResultSet rs2 = ConnectDatabase.GetInstance().ExcuteSELECT(sql2);
            CourseDTO cDTO = new CourseDTO();
            cDTO.setCourseID(rs.getInt("course.CourseID"));
            cDTO.setTitle(rs.getString("course.Title"));
            cDTO.setCredits(rs.getInt("course.Credits"));
            cDTO.setDepartmentID(rs.getInt("DepartmentID"));
            while (rs2.next()) {                
                cDTO.setDepartmentName(rs2.getString("Name"));
            }            
            cDTO.setURL(rs.getString("url"));
            cDTO.setLocation(rs.getString("Location"));
            cDTO.setDays(rs.getString("Days"));
            cDTO.setTime(rs.getTime("Time"));
            courseList.add(cDTO);
        }
        return courseList;
    }
    public boolean addcourse(CourseDTO c){
        String sql = null;
        if(c != null){
            sql =String.format("INSERT INTO `course`(`Title`, `Credits`, `DepartmentID`) VALUES ('%s','%d','%d')",
                    c.getTitle(),c.getCredits(),c.getDepartmentID());
        }
        String result = ConnectDatabase.GetInstance().ExcuteINSERTDELETEUPDATE(sql);
        if (result == "Thành công") {
            return true;
        }else
            return false;
        
    }
    public boolean addonlinecourse(CourseDTO c) throws SQLException{
        String sql = null;
        String sql2 = String.format("SELECT * FROM `course` WHERE Title = '%s'", c.getTitle());
        ResultSet rs2 = ConnectDatabase.GetInstance().ExcuteSELECT(sql2);
        int temp =0;
        while(rs2.next()){
            temp = rs2.getInt("CourseID");
        }
        if(c != null){
            sql =String.format("INSERT INTO `onlinecourse`(`CourseID`, `url`) VALUES ('%d','%s')",
                    temp,c.getURL());
        }
        String result = ConnectDatabase.GetInstance().ExcuteINSERTDELETEUPDATE(sql);
        if (result == "Thành công") {
            return true;
        }else
            return false;
    }
    public boolean addonsitecourse(CourseDTO c) throws SQLException{
        String sql = null;
        String sql2 = String.format("SELECT * FROM `course` WHERE Title = '%s'", c.getTitle());
        ResultSet rs2 = ConnectDatabase.GetInstance().ExcuteSELECT(sql2);
        int temp =0;
        while(rs2.next()){
            temp = rs2.getInt("CourseID");
        }
        if(c != null){
            sql =String.format("INSERT INTO `onsitecourse`(`CourseID`, `Location`, `Days`, `Time`) "
                    + "VALUES ('%d','%s','%s','%tT')",
                    temp,c.getLocation(),c.getDays(),c.getTime());
        }
        String result = ConnectDatabase.GetInstance().ExcuteINSERTDELETEUPDATE(sql);
        if (result == "Thành công") {
            return true;
        }else
            return false;
    }
    public boolean deletecourse(int id) {
        deleteonlinecourse(id);
        deleteonsitecourse(id);
        String sql = String.format("DELETE FROM `course` WHERE CourseID='%d'", id);
        String result = ConnectDatabase.GetInstance().ExcuteINSERTDELETEUPDATE(sql);
        if (result == "Thành công") {
            return true;
        }else
            return false;
    }
    public boolean deleteonlinecourse(int id){
        String sql = String.format("DELETE FROM `onlinecourse` WHERE CourseID='%d'", id);
        String result = ConnectDatabase.GetInstance().ExcuteINSERTDELETEUPDATE(sql);
        if (result == "Thành công") {
            return true;
        }else
            return false;
    }
    public boolean deleteonsitecourse(int id){
        String sql = String.format("DELETE FROM `onsitecourse` WHERE CourseID='%d'", id);
        String result = ConnectDatabase.GetInstance().ExcuteINSERTDELETEUPDATE(sql);
        System.out.println(result);
        if (result == "Thành công") {
            return true;
        }else
            return false;
    }
    public void updatecourse(CourseDTO c){
        String sql = null;
        if(c != null){
            sql = String.format("UPDATE `course` SET `Title`='%s',`Credits`='%d',`DepartmentID`='%d' "
                    + "WHERE CourseID = '%d'", c.getTitle(),c.getCredits(),c.getDepartmentID(),c.getCourseID());
        }
        if(sql != null){
            String result = ConnectDatabase.GetInstance().ExcuteINSERTDELETEUPDATE(sql);
        }
    }
    public boolean updateonlinecourse(CourseDTO c){
        String sql = null;
        if(c != null){
            sql = String.format("UPDATE `onlinecourse` SET `url`='%s' WHERE CourseID = '%d'", c.getURL(),c.getCourseID());
        }
        String result = ConnectDatabase.GetInstance().ExcuteINSERTDELETEUPDATE(sql);
        if (result == "Thành công") {
            return true;
        }else
            return false;
    }
    public boolean updateonsitecourse(CourseDTO c){
        String sql =null;
        if(c!=null){
            sql = String.format("UPDATE `onsitecourse` SET `Location`='%s',`Days`='%s',`Time`='%tT' "
                    + "WHERE CourseID = '%d'", c.getLocation(),c.getDays(),c.getTime(),c.getCourseID());
        }
        String result = ConnectDatabase.GetInstance().ExcuteINSERTDELETEUPDATE(sql);
        
        if (result == "Thành công") {
            return true;
        }else
            return false;
    }        
    public ArrayList<CourseDTO> FindIDonline (String courseID) throws SQLException{
        ArrayList<CourseDTO> searchList = new ArrayList<>();
        int courseID2 = Integer.parseInt(courseID);
        String sql = String.format("select * from course left JOIN onlinecourse on onlinecourse.CourseID = course.CourseID "
                + "WHERE course.CourseID = '%d'", courseID2);
        System.out.println(sql);
        ResultSet rs = ConnectDatabase.GetInstance().ExcuteSELECT(sql);        
        while (rs.next()) {
            CourseDTO cDTO = new CourseDTO();
            cDTO.setCourseID(rs.getInt("CourseID"));
            cDTO.setTitle(rs.getString("Title"));
            cDTO.setCredits(rs.getInt("Credits"));
            String sql2 = "select * from Department where DepartmentID = '" + rs.getInt("DepartmentID") + "'";
            System.out.println(sql2);
            ResultSet rs2 = ConnectDatabase.GetInstance().ExcuteSELECT(sql2);
            while (rs2.next()) {                
                cDTO.setDepartmentName(rs2.getString("Name"));
            }
            cDTO.setURL(rs.getString("URL"));
            searchList.add(cDTO);            
        }
        return searchList;
    }
    public ArrayList<CourseDTO> FindIDonsite (String courseID) throws SQLException{
        ArrayList<CourseDTO> searchList = new ArrayList<>();
        int courseID2 = Integer.parseInt(courseID);
        String sql = String.format("select * from course left JOIN onsitecourse on onsitecourse.CourseID = course.CourseID "
                + "WHERE course.CourseID = '%d'", courseID2);
        System.out.println(sql);
        ResultSet rs = ConnectDatabase.GetInstance().ExcuteSELECT(sql);        
        while (rs.next()) {
            CourseDTO cDTO = new CourseDTO();
            cDTO.setCourseID(rs.getInt("CourseID"));
            cDTO.setTitle(rs.getString("Title"));
            cDTO.setCredits(rs.getInt("Credits"));
            String sql2 = "select * from Department where DepartmentID = '" + rs.getInt("DepartmentID") + "'";
            System.out.println(sql2);
            ResultSet rs2 = ConnectDatabase.GetInstance().ExcuteSELECT(sql2);
            while (rs2.next()) {                
                cDTO.setDepartmentName(rs2.getString("Name"));
            }
            cDTO.setLocation(rs.getString("Location"));
            cDTO.setDays(rs.getString("Days"));
            cDTO.setTime(rs.getTime("Time"));
            searchList.add(cDTO);            
        }
        return searchList;
    }
}
