/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAnPhanLop;

import BUS.CourseBUS;
import DTO.CourseDTO;
import java.sql.SQLException;

/**
 *
 * @author ad
 */
public class TestGetCourse {
    public static void main(String[] args) throws SQLException {
        CourseBUS cBUS = new CourseBUS();
        cBUS.ShowCourseList();
        for (CourseDTO courseDTO : cBUS.CourseList) {
            System.out.print(courseDTO.getCourseID()+ " - ");
            System.out.print(courseDTO.getTitle()+ " - ");
            System.out.print(courseDTO.getCredits()+ " - ");
            System.out.print(courseDTO.getDepartmentName()+ " - ");
            System.out.print(courseDTO.getURL()+" - ");
            System.out.print(courseDTO.getLocation()+" - ");
            System.out.print(courseDTO.getDays()+" - ");
            System.out.println(courseDTO.getTime()+"");
        }
    }
}
