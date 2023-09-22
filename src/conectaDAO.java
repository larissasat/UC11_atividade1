
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {
    
     public Connection conn; 
     private String url = "jdbc:mysql://localhost:3306/uc11";
     private String user = "root";
     private String password = "uc3mysql";
    
    public Connection connectDB(){
        
        try {
                Connection conn = DriverManager.getConnection(url,user,password);
                System.out.println("DataBase Conectado");
                return conn;
            
                }catch (Exception e) {
                    System.out.println("Erro ao conectar: " + e.getMessage());
                }
         return null;
        }
       
        
       
    }  