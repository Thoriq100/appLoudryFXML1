/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loundry_app;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Toriq
 */
public class Koneksi {
     public static Connection KoneksiBD() throws ClassNotFoundException{
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String user="apploundry";
            String pass="loundry";
            Connection Koneksi=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",user,pass);
            return Koneksi;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }
    
}