/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAnPhanLop;

import BUS.PersonBUS;
import DTO.PersonDTO;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author ad
 */
public class TestGetPerson {

    public static void main(String[] args) throws SQLException {
        PersonBUS pBUS = new PersonBUS();
        Scanner in = new Scanner(System.in);
        Scanner in2 = new Scanner(System.in);

        System.out.println("Input your choice ");
        int choice;
        do {
            System.out.println("0. Exit");
            System.out.println("1. Show all people");
            System.out.println("2. Insert 1 person");
            System.out.println("3. Delete 1 person");
            System.out.println("4. Update a person information");

            choice = in2.nextInt();
            switch (choice) {
                case 1:
                    pBUS.ShowPersonList();
                    for (PersonDTO personDTO : pBUS.PersonList) {
                        System.out.print(personDTO.getPersonID() + " - ");
                        System.out.print(personDTO.getLastName() + " - ");
                        System.out.print(personDTO.getFirstName() + " - ");
                        System.out.print(personDTO.getEnrollmentDate() + " - ");
                        System.out.println(personDTO.getHireDate() + "");
                    }
                    break;
                case 2:
                    String date = "2001-03-02 04:02:01";
                    PersonDTO p = new PersonDTO();
                    System.out.println("-----Add Person----");
                    System.out.println("First name: ");
                    String firstName = in.nextLine();
                    System.out.println("Last name: ");
                    String lastName = in.nextLine();
//        System.out.println("Hire date: ");
                    String hireDate = date;
                    System.out.println("Enrollment date: ");
                    String enrollmentDate = in.nextLine();
                    p.setFirstName(firstName);
                    p.setLastName(lastName);

                    p.setHireDate(hireDate);
                    p.setEnrollmentDate(enrollmentDate);
                    pBUS.AddPerson(p);
                case 3:
                    System.out.println("-----Delete Person----");
                    Scanner in3 = new Scanner(System.in);
                    System.out.println("Input your Id: ");
                    int id = in3.nextInt();
                   // pBUS.DeletePerson(id);
                case 4:
                    String dateUpdate = "2010-02-02 04:02:01";
                    Scanner in4 = new Scanner(System.in);
                    Scanner in5 = new Scanner(System.in);

                    PersonDTO pUpdate = new PersonDTO();
                    System.out.println("-----Update Person----");
                    System.out.println("Input ID: ");
                    int idUpdate = in5.nextInt();
                    System.out.println("First name: ");
                    String firstNameUpdate = in4.nextLine();
                    System.out.println("Last name: ");
                    String lastNameUpdate = in4.nextLine();
//        System.out.println("Hire date: ");
                    String hireDateUpdate = dateUpdate;
                    System.out.println("Enrollment date: ");
                    String enrollmentDateUpdate = in4.nextLine();
                    pUpdate.setFirstName(firstNameUpdate);
                    pUpdate.setLastName(lastNameUpdate);

                    pUpdate.setHireDate(hireDateUpdate);
                    pUpdate.setEnrollmentDate(enrollmentDateUpdate);
                    //pBUS.UpdatePerson(pUpdate, idUpdate);
                default:
                    break;
            }
        } while (choice > 0);

    }
}
