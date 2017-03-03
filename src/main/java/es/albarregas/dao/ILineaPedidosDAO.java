/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.LineaPedidos;
import java.util.ArrayList;

/**
 *
 * @author Adrian
 */
public interface ILineaPedidosDAO {
    public void addLinea(LineaPedidos linea);
    public ArrayList<LineaPedidos> getLineas(String where);
    public void deleteLinea(String where);
    public void updateLinea (LineaPedidos linea);
    public void closeConnection();
}
