/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Clientes;
import java.util.ArrayList;

/**
 *
 * @author Adrian
 */
public interface IClientesDAO {
    public void addCliente(String where);
    public ArrayList<Clientes> getClientes(String where);
    public void updateCliente (Clientes cliente);
    public void deleteCliente (String where);
    public void closeConnection();
}
