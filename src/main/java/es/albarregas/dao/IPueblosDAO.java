/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Pueblos;
import java.util.ArrayList;

/**
 *
 * @author Adrian
 */
public interface IPueblosDAO {
    public ArrayList<Pueblos> getPueblos(String where);
    public String getProvincia(int idProvincia);
    public void closeConnection();
}
