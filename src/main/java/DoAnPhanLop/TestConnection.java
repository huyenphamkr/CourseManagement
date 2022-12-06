/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAnPhanLop;

/**
 *
 * @author ad
 */
public class TestConnection {
    public static void main(String[] args) {
        ConnectDatabase conn = new ConnectDatabase();
        if (conn.getConnect() != null) {
            System.out.println("Successss");
        }else{
            System.out.println("Failed");
        }
    }
}
