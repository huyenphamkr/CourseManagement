package DoAnPhanLop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ad
 */
public class ConnectDatabase {

    private static ConnectDatabase Instance;
    private Connection conn = null;
    public ConnectDatabase(){
    }
    public Connection getConnect() {
        try {
            Connection connection = null;
            String dbURL = "jdbc:mysql://localhost:3306/school2";
            String username = "root";
            String password = "";
            if (conn == null) {
                connection = DriverManager.getConnection(dbURL, username, password);
                return connection;
            }else{
                return conn;
            }
            
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
            return null;
        }

    }
    
    public ResultSet ExcuteSELECT(String sql) {
        
        try {
            conn = getConnect();
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            return rs;

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
            return null;
        }

    }
    public String ExcuteINSERTDELETEUPDATE(String sql) {
        try {
            conn = getConnect();
            Statement statement = conn.createStatement();
            
            int n = statement.executeUpdate(sql);
            if (n >= 1) {
                return "Thành công";
            } else {
                return "Không thành công";
            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
            return "Lỗi thực thi";
        }
    }
    
    public static ConnectDatabase GetInstance(){
        if (Instance == null) {
            Instance = new ConnectDatabase();
        }
        return Instance;
    }
}
