/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Pueblos;
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
public class PueblosDAO implements IPueblosDAO {

    @Override
    public ArrayList<Pueblos> getPueblos(String where) {
        ArrayList<Pueblos> listaPueblos = new ArrayList();
        try{
            String sql= "select * from pueblos "+where;
            PreparedStatement preparada= ConnectionFactory.getConnection().prepareStatement(sql);
            try(ResultSet resultado = preparada.executeQuery()){
                while(resultado.next()){
                    Pueblos pueblo = new Pueblos();
                    pueblo.setIdPueblo(resultado.getInt("IdPueblo"));
                    pueblo.setCodigoPostal(resultado.getInt("CodigoPostal"));
                    pueblo.setIdProvincia(resultado.getInt("IdProvincia"));
                    pueblo.setNombrePueblo(resultado.getString("Nombre"));
                    pueblo.setNombreProvincia(getProvincia(resultado.getInt("IdProvincia")));
                    listaPueblos.add(pueblo);
                }
            }
        }catch(SQLException e){
            Logger.getLogger(PueblosDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.closeConnection();
        }
        return listaPueblos;
    }

    @Override
    public String getProvincia(int idProvincia) {
        String provincia= "";
        try{
            String sql= "select Nombre from provincia where IdProvincia= "+idProvincia;
            PreparedStatement preparada= ConnectionFactory.getConnection().prepareStatement(sql);
            ResultSet resultado = preparada.executeQuery();
            while(resultado.next()){
                provincia=resultado.getString("Nombre");
            }
        }catch(SQLException e){
            Logger.getLogger(PueblosDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.closeConnection();
        }
        return provincia;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
    
}
