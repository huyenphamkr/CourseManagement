/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAnPhanLop;

import BUS.StudentGradeBUS;
import DTO.StudentGradeDTO;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class TestStudentGrade {
    public static void main(String[] args) throws SQLException {
        StudentGradeBUS cBUS = new StudentGradeBUS();
        Scanner in = new Scanner(System.in);
        Scanner in2 = new Scanner(System.in);
        Scanner in3 = new Scanner(System.in);

//        cBUS.GetStudentGradeList();
//        for (StudentGradeDTO StudenGradeDTO : cBUS.sgList) {
//            System.out.print(StudenGradeDTO.getEnrollmentID()+ " - ");            
//            System.out.print(StudenGradeDTO.getCourseTitle()+ " - ");            
//            System.out.print(StudenGradeDTO.getPersonID()+ " - ");            
//            System.out.println(StudenGradeDTO.getGrade()+ "");
//        }

        //Only add student (student has enrollmentdate)
        System.out.println("Add Student Grade");
        System.out.println("Course Name: ");
        String title = in.next();
        System.out.println("Student ID: ");
        int idStudent = in2.nextInt();
        float grade = (float) 0.0;
        do {         
           System.out.println("Grade: ");
           grade = in3.nextFloat();
        } while (grade > 4.0);
        
        StudentGradeDTO sgDTO = new StudentGradeDTO();
        sgDTO.setCourseTitle(title);
        sgDTO.setPersonID(idStudent);
        sgDTO.setGrade(grade);
        cBUS.AddStudentGradeList(sgDTO);

    }
}
