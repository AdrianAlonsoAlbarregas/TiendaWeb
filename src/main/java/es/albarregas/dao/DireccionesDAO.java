/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Direcciones;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrian
 */
public class DireccionesDAO implements IDireccionesDAO {

    @Override
    public void addDireccion(Direcciones direccion) {
        try{
            String sql= "insert into direcciones (IdCliente, NombreDireccion, Direccion, CodigoPostal, IdPueblo, Telefono) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparada= ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setInt(1, direccion.getIdCliente());
            preparada.setString(2, direccion.getNombreDireccion());
            preparada.setString(3, direccion.getDireccion());
            preparada.setInt(4, direccion.getCodigoPostal());
            preparada.setInt(5, direccion.getIdPueblo());
            preparada.setInt(6, direccion.getTelefono());
            preparada.executeUpdate();
        }catch(SQLException e){
            Logger.getLogger(DireccionesDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.closeConnection();
        }
    }

    @Override
    public void deleteDireccion(String where) {
        try{
            String sql= "delete from direcciones "+where;
            PreparedStatement preparada= ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.executeUpdate();
        }catch(SQLException e){
            Logger.getLogger(DireccionesDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.closeConnection();
        }
    }

    @Override
    public ArrayList<Direcciones> getDirecciones(String where) {
        ArrayList<Direcciones> listaDirecciones = new ArrayList();
        try{
            String sql= "select * from direcciones "+ where;
            PreparedStatement preparada= ConnectionFactory.getConnection().prepareStatement(sql);
            try(ResultSet resultado = preparada.executeQuery()){
                while(resultado.next()){
                    Direcciones direccion = new Direcciones();
                    direccion.setIdDireccion(resultado.getInt("IdDireccion"));
                    direccion.setIdCliente(resultado.getInt("IdCliente"));
                    direccion.setIdPueblo(resultado.getInt("IdPueblo"));
                    direccion.setNombreDireccion(resultado.getString("NombreDireccion"));
                    direccion.setDireccion(resultado.getString("Direccion"));
                    direccion.setCodigoPostal(resultado.getInt("CodigoPostal"));
                    direccion.setTelefono(resultado.getInt("Telefono"));
                    listaDirecciones.add(direccion);
                }
            }
            
        }catch(SQLException e){
            Logger.getLogger(DireccionesDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.closeConnection();
        }
        return listaDirecciones;
    }

    @Override
    public void closeConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
