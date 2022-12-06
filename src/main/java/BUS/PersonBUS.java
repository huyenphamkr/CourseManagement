package BUS;

import DAO.PersonDAO;
import DTO.CourseInstructorDTO;
import DTO.PersonDTO;
import DTO.StudentGradeDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonBUS {

    public static ArrayList<PersonDTO> PersonList;
    private PersonDAO pDAO;
    public PersonBUS() {
        if (PersonList == null) {
            PersonList = new ArrayList<>();
        }
        pDAO = new PersonDAO();
    }

    public void ShowPersonList() throws SQLException {        
        PersonList = pDAO.GetPersonList();
    }

    public boolean AddPerson(PersonDTO p) {        
        PersonList.add(p);
        return pDAO.add(p);
    }

    public boolean DeleteStudent(int id) {                
        for (StudentGradeDTO stDTO : StudentGradeBUS.sgList) {
            if (stDTO.getPersonID() == id) {
                return false;                
            }
        }        
        for (PersonDTO person : PersonList) {
            if (person.getPersonID() == id) {
                PersonList.remove(person);
                break;
            }
        }
        return pDAO.delete(id);
    }
    public boolean DeleteInstructor(int id){
        for (CourseInstructorDTO ciDTO : CourseInstructorBUS.CourseInstructorList) {
            if (ciDTO.getPersonID() == id) {
                return false;                
            }
        }
        for (PersonDTO person : PersonList) {
            if (person.getPersonID() == id) {
                PersonList.remove(person);
                break;
            }
        }
        return pDAO.delete(id);
    }

    public boolean UpdatePerson(PersonDTO p) {        
        for (int i = 0; i < PersonList.size(); i++) {
            if (p.getPersonID() == PersonList.get(i).getPersonID()) {
                PersonList.set(i, p);                
                break;
            }
        }
        return pDAO.update(p);
    }

    public ArrayList<PersonDTO> findPersonByName(String name) throws SQLException {        
        return pDAO.findPersonByName(name);
    }
}
