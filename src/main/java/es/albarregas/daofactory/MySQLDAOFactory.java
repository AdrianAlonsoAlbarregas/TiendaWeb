/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.daofactory;

import es.albarregas.dao.CategoriasDAO;
import es.albarregas.dao.ClientesDAO;
import es.albarregas.dao.DireccionesDAO;
import es.albarregas.dao.ICategoriasDAO;
import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.IDireccionesDAO;
import es.albarregas.dao.ILineaPedidosDAO;
import es.albarregas.dao.IPedidosDAO;
import es.albarregas.dao.IProductosDAO;
import es.albarregas.dao.IPueblosDAO;
import es.albarregas.dao.IUsuariosDAO;
import es.albarregas.dao.LineaPedidosDAO;
import es.albarregas.dao.PedidosDAO;
import es.albarregas.dao.ProductosDAO;
import es.albarregas.dao.PueblosDAO;
import es.albarregas.dao.UsuariosDAO;

/**
 *
 * @author Adrian
 */
public class MySQLDAOFactory extends DAOFactory{
    @Override
    public IClientesDAO getClientesDAO(){
       return new ClientesDAO();
   }
    @Override
   public IUsuariosDAO getUsuariosDAO(){
       return new UsuariosDAO();
   }
    @Override
   public IProductosDAO getProductosDAO(){
       return new ProductosDAO();
   }

    @Override
   public ICategoriasDAO getCategoriasDAO(){
       return new CategoriasDAO();
   }

    @Override
    public ILineaPedidosDAO getLineaPedidosDAO() {
        return new LineaPedidosDAO();
    }

    @Override
    public IPedidosDAO getPedidosDAO() {
        return new PedidosDAO();
    }

    @Override
    public IDireccionesDAO getDireccionesDAO() {
        return new DireccionesDAO();
    }

    @Override
    public IPueblosDAO getPueblosDAO() {
        return new PueblosDAO();
    }
}
