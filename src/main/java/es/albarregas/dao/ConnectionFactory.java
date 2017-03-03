/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {
    static DataSource ds = null;
    static Connection conexion = null;
    static final String DATASOURCE_NAME = "java:comp/env/jdbc/tiendaweb";
        
    public static Connection getConnection(){
        try {
            Context contextoInicial = new InitialContext();
            ds = (DataSource) contextoInicial.lookup(DATASOURCE_NAME);
            conexion = ds.getConnection();
        } catch (NamingException | SQLException ex) {
            System.out.println("error al conectar");
        }
        return conexion;
    }
    
    public static void closeConnection(){
        try {
            conexion.close();
        } catch(SQLException e){
            System.out.println("Error al cerrar la conexión a la BD");
            e.printStackTrace();
        }
    }
}
