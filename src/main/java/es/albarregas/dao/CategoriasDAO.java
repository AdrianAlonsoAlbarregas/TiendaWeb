/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Categorias;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Adrian
 */
public class CategoriasDAO implements ICategoriasDAO {

    @Override
    public ArrayList<Categorias> getCategorias() {
        ArrayList<Categorias> listaCategorias = new ArrayList();
        String sql = "select * from categorias";
        try{
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            try(ResultSet resultado = sentencia.executeQuery(sql)) {
                while(resultado.next()){
                    Categorias categoria = new Categorias();
                    categoria.setIdCategoria(resultado.getInt("IdCategoria"));
                    categoria.setNombre(resultado.getString("Nombre"));
                    listaCategorias.add(categoria);
                }
        }
        }catch (SQLException e){
            
        }finally{
                this.closeConnection();
            }
            return listaCategorias;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
    
}
