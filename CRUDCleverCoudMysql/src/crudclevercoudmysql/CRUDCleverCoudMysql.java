
package crudclevercoudmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class CRUDCleverCoudMysql {

    public static void main(String[] args) {
        //conexion a la base de datos
        Connection conn;
        String usuario = "u1yl6spacujjurd8";
        String password = "Tt6VXLOK3PsxvzDwFymp";
        String url = "jdbc:mysql://u1yl6spacujjurd8:Tt6VXLOK3PsxvzDwFymp@bc6glpoj4ptb3caxaoez-mysql.services.clever-cloud.com:3306/bc6glpoj4ptb3caxaoez";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexion exitosa");
        }
        catch(Exception ex){
            throw new RuntimeException(ex);
        }
        
        PreparedStatement pstmt;
        int opc;
        opc = Integer.parseInt(JOptionPane.showInputDialog("Escoja una opcion: \n 1: Insertar \n 2: Buscar \n 3: Modificar \n 4: Elimiminar"));
        
        switch (opc){
            case 1:
                String nombre = JOptionPane.showInputDialog("Escriba un nombre");
                String apellido = JOptionPane.showInputDialog("Escriba los apellidos");
                int edad = Integer.parseInt(JOptionPane.showInputDialog("Escriba la edad"));
                String query = "INSERT INTO personas(nombre, apellidos, edad) VALUES('"+nombre+"','"+apellido+"',"+edad+");";
                
                try {
                    pstmt = conn.prepareStatement(query); 
                    pstmt.execute();
                    System.out.println("Inserción exitosa");
                } catch (SQLException ex) {
                    Logger.getLogger(CRUDCleverCoudMysql.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 2:
                String nombreBuscar = JOptionPane.showInputDialog("Escriba un nombre que desea buscar");
                String query1 = "SELECT * FROM personas WHERE nombre ='"+nombreBuscar+"';";
                
                try {
                    pstmt = conn.prepareStatement(query1); 
                    pstmt.executeQuery();
                    ResultSet rs = pstmt.executeQuery();
                    if(rs.next()){
                        System.out.println(rs.getString("nombre")+ " " + rs.getString("apellidos"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CRUDCleverCoudMysql.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 3:
                String nombreActual = JOptionPane.showInputDialog("Escriba el nombre de la persona que desea actualizar");
                String nombreNuevo = JOptionPane.showInputDialog("Escribe el nuevo nombre");
                String query2 = "UPDATE personas SET nombre = '"+nombreNuevo+"' WHERE nombre ='"+nombreActual+"';";
                
                try {
                    pstmt = conn.prepareStatement(query2); 
                    pstmt.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(CRUDCleverCoudMysql.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 4:
                String nombreBorrar = JOptionPane.showInputDialog("Escriba el nombre de la persona que desea eliminar");
                String query3 = "DELETE FROM personas WHERE nombre ='"+nombreBorrar+"';";
                
                try {
                    pstmt = conn.prepareStatement(query3); 
                    pstmt.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(CRUDCleverCoudMysql.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
    }
}
