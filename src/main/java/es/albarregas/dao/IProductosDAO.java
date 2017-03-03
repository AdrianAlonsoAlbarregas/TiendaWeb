/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Productos;
import java.util.ArrayList;

/**
 *
 * @author Adrian
 */
public interface IProductosDAO {
    public void addProducto(Productos producto);
    public ArrayList<Productos> getProductos(String where);
    public void deleteProducto(String where);
    public void updateProducto(Productos producto);
    public void closeConnection();
}
