package DAO;

import DTO.PersonDTO;
import DoAnPhanLop.ConnectDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PersonDAO {
    
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public PersonDAO() {
    }

    public ArrayList<PersonDTO> GetPersonList() throws SQLException {
        ArrayList<PersonDTO> personList = new ArrayList<>();

        String sql = "select * from person";
        ResultSet rs = ConnectDatabase.GetInstance().ExcuteSELECT(sql);
        while (rs.next()) {
            PersonDTO pDTO = new PersonDTO();
            pDTO.setPersonID(rs.getInt("PersonID"));
            pDTO.setLastName(rs.getString("Lastname"));
            pDTO.setFirstName(rs.getString("Firstname"));
            pDTO.setHireDate(rs.getString("HireDate"));
            pDTO.setEnrollmentDate(rs.getString("EnrollmentDate"));
            personList.add(pDTO);
        }
        return personList;
    }

    public boolean delete(int id) {
        String sql = String.format("DELETE FROM `person` WHERE PersonID='%s'", id);
        String result = ConnectDatabase.GetInstance().ExcuteINSERTDELETEUPDATE(sql);
        if (result == "Thành công") {
            return true;
        } else {
            return false;
        }
    }

    public boolean add(PersonDTO p) {

        String sql;
        if (p.getHireDate() == null) {
            LocalDate enrollmentDate = LocalDate.parse(p.getEnrollmentDate(), dtf);
            sql = String.format("INSERT INTO `person`(`Lastname`, `Firstname`, `EnrollmentDate`) VALUES ('%s', '%s', '%s')",
                    p.getFirstName(), p.getLastName(), enrollmentDate);
        } else {
            LocalDate hireDate = LocalDate.parse(p.getHireDate(), dtf);
            sql = String.format("INSERT INTO `person`(`Lastname`, `Firstname`, `HireDate`) VALUES ('%s', '%s', '%s')",
                    p.getFirstName(), p.getLastName(), hireDate);
        }
        String result = ConnectDatabase.GetInstance().ExcuteINSERTDELETEUPDATE(sql);
        if (result == "Thành công") {
            return true;
        } else {
            return false;
        }
    }

    public boolean update(PersonDTO p) {
        String sql;
        if (p.getHireDate() == null) {
            LocalDate enrollmentDate = LocalDate.parse(p.getEnrollmentDate(), dtf);
            sql = String.format("UPDATE `person`"
                    + "SET LastName='%s', Firstname='%s', EnrollmentDate='%s'"
                    + "WHERE PersonID='%s'",
                    p.getLastName(), p.getFirstName(), enrollmentDate, p.getPersonID());
        } else {
            LocalDate hireDate = LocalDate.parse(p.getHireDate(), dtf);
            sql = String.format("UPDATE `person`"
                    + "SET LastName='%s', Firstname='%s', HireDate='%s'"
                    + "WHERE PersonID='%s'",
                    p.getLastName(), p.getFirstName(), hireDate, p.getPersonID());
        }
        String result = ConnectDatabase.GetInstance().ExcuteINSERTDELETEUPDATE(sql);
        if (result == "Thành công") {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<PersonDTO> findPersonByName(String name) throws SQLException {
        ArrayList<PersonDTO> personList = new ArrayList<>();
        if (name.contains(" ")) {
            String[] word = name.split("\\s");
            String sql = String.format("SELECT * FROM `person` WHERE (`Firstname` LIKE '%%s%' AND Lastname LIKE '%%s%')", word[0], word[1]);
            ResultSet rs = ConnectDatabase.GetInstance().ExcuteSELECT(sql);
            while (rs.next()) {
                PersonDTO pDTO = new PersonDTO();
                pDTO.setPersonID(rs.getInt("PersonID"));
                pDTO.setLastName(rs.getString("Lastname"));
                pDTO.setFirstName(rs.getString("Firstname"));
                pDTO.setHireDate(rs.getString("HireDate"));
                pDTO.setEnrollmentDate(rs.getString("EnrollmentDate"));
                personList.add(pDTO);
            }
        } else {
            String sql = "SELECT * FROM `person` WHERE (`Firstname` LIKE '%"+name+"%' OR Lastname LIKE '%"+name+"%')";
            ResultSet rs = ConnectDatabase.GetInstance().ExcuteSELECT(sql);
            while (rs.next()) {
                PersonDTO pDTO = new PersonDTO();
                pDTO.setPersonID(rs.getInt("PersonID"));
                pDTO.setLastName(rs.getString("Lastname"));
                pDTO.setFirstName(rs.getString("Firstname"));
                pDTO.setHireDate(rs.getString("HireDate"));
                pDTO.setEnrollmentDate(rs.getString("EnrollmentDate"));
                personList.add(pDTO);
            }
        }
        return personList;
    }

}
