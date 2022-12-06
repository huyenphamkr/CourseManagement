/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ad
 */
public class CourseInstructorDTO {
    private int CourseID;
    private String TitleCourse;
    private int PersonID;
    private String LastName;
    private String FirstName;

    public CourseInstructorDTO() {
    }

    public String getTitleCourse() {
        return TitleCourse;
    }

    public void setTitleCourse(String TitleCourse) {
        this.TitleCourse = TitleCourse;
    }

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int PersonID) {
        this.PersonID = PersonID;
    }

    public String getLastName() {
        return LastName;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }
}
