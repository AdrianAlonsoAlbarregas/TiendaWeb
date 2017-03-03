/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Direcciones;
import java.util.ArrayList;

/**
 *
 * @author Adrian
 */
public interface IDireccionesDAO {
    public void addDireccion(Direcciones direccion);
    public void deleteDireccion(String where);
    public ArrayList<Direcciones> getDirecciones(String where);
    public void closeConnection();
}
