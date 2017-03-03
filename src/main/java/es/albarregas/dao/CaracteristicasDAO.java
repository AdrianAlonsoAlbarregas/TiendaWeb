/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Caracteristicas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrian
 */
public class CaracteristicasDAO implements ICaracteristicasDAO {

    @Override
    public ArrayList<Caracteristicas> getCaracteristicas(int idProducto) {
        ArrayList<Caracteristicas> caracteristicas = new ArrayList<Caracteristicas>();
        String sql = "select c.IdCaracteristica, c.IdCategoria, c.Nombre, cp.Descripcion from caracteristicas c, caracyprods cp where cp.IdProducto="+idProducto+" and cp.IdCaracteristica=c.IdCaracteristica";
        try {
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(sql)) {
                while (resultado.next()) {
                    Caracteristicas caracteristica = new Caracteristicas();
                    caracteristica.setIdCaracteristica(resultado.getInt("IdCaracteristica"));
                    caracteristica.setIdCategoria(resultado.getInt("IdCategoria"));
                    caracteristica.setDescripcion(resultado.getString("Descripcion"));
                    caracteristica.setNombre(resultado.getString("Nombre"));
                    caracteristicas.add(caracteristica);
                }
            }
        } catch (SQLException e) {
            System.out.println("Algo ha petado al sacar la lista de caracteristicas");
            Logger.getLogger(CaracteristicasDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
        return caracteristicas;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
}
