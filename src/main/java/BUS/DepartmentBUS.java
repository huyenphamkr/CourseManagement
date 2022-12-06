package BUS;

import DAO.DepartmentDAO;
import DTO.DepartmentDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepartmentBUS {
    public static ArrayList<DepartmentDTO> dpList;
    private DepartmentDAO dpDAO = new DepartmentDAO();
    public void DepartmentBUS(){    }
    public void showdpList() throws SQLException{        
        if (dpList == null) {
            dpList = new ArrayList<>();
        }
        dpList = dpDAO.GetDpList();
    }
}
