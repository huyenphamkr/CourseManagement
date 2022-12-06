package DAO;

import DTO.DepartmentDTO;
import DoAnPhanLop.ConnectDatabase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepartmentDAO {    
    public DepartmentDAO(){}
    public ArrayList<DepartmentDTO> GetDpList() throws SQLException{
        ArrayList<DepartmentDTO> dpList = new ArrayList<>();
        String sql = "SELECT * FROM `department`";
        ResultSet rs = ConnectDatabase.GetInstance().ExcuteSELECT(sql);
        while(rs.next()) {
            DepartmentDTO dp = new DepartmentDTO();
            dp.setDepartmentID(rs.getInt("DepartmentID"));
            dp.setName(rs.getString("Name"));
            dp.setBudget(rs.getDouble("Budget"));
            dp.setAdministrator(rs.getInt("Administrator"));
            dpList.add(dp);
        }
        return dpList;
    }
}
