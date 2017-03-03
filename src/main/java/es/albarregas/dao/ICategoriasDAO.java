/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Categorias;
import java.util.ArrayList;

/**
 *
 * @author Adrian
 */
public interface ICategoriasDAO {
    public ArrayList<Categorias> getCategorias();
    public void closeConnection();
}
