/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Caracteristicas;
import java.util.ArrayList;

/**
 *
 * @author Adrian
 */
public interface ICaracteristicasDAO {
    public ArrayList<Caracteristicas> getCaracteristicas(int idProducto);
    public void closeConnection();
}
