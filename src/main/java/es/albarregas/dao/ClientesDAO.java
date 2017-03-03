/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Clientes;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Adrian
 */
public class ClientesDAO implements IClientesDAO {

    @Override
    public void addCliente(String where) {
        try{
            String sql = "insert into clientes (IdCliente) select IdUsuario from usuarios "+where ;
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            sentencia.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println("Algo ha petado al añadir cliente");
        }finally{
            this.closeConnection();
        }
    }

    @Override
    public ArrayList<Clientes> getClientes(String where) {
        ArrayList<Clientes> listaClientes = new ArrayList<Clientes>();
            String sql = "select * from clientes "+where;
        try{
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            try(ResultSet resultado = sentencia.executeQuery(sql)){
                while (resultado.next()){
                    Clientes clientes= new Clientes();
                    clientes.setNombre(resultado.getString("Nombre"));
                    clientes.setApellidos(resultado.getString("Apellidos"));
                    clientes.setIdCliente(resultado.getInt("IdCliente"));
                    clientes.setFechaNac(resultado.getDate("FechaNacimiento"));
                    clientes.setFechaAlta(resultado.getDate("FechaAlta"));
                    clientes.setNif(resultado.getString("NIF"));
                    listaClientes.add(clientes);
                }
            }
        }catch(SQLException e){
            System.out.println("Algo ha petado al sacar la lista de clientes");
        }finally{
            this.closeConnection();
        }
        return listaClientes;
    }

    @Override
    public void updateCliente(Clientes cliente) {
        try{
            String sql= "update clientes set Nombre=?, Apellidos=?, FechaNacimiento=?, NIF=? where IdCliente=?";
            PreparedStatement preparada= ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setString(1, cliente.getNombre());
            preparada.setString(2, cliente.getApellidos());
            preparada.setDate(3, cliente.getFechaNac());
            preparada.setString(4, cliente.getNif());
            preparada.setInt(5, cliente.getIdCliente());
            preparada.executeUpdate();
        }catch(SQLException e){
            System.out.println("Algo ha petao al actualizar clientes");
        }finally{
            this.closeConnection();
        }
    }

    @Override
    public void deleteCliente(String where) {
        try{
            String sql = "delete from clientes "+ where;
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            sentencia.executeQuery(sql);
        }catch (SQLException e){
            System.out.println("Algo a petado al borrar");
        }finally{
            this.closeConnection();
        }
    }
    

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
    
}
