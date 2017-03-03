/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Categorias;
import es.albarregas.beans.Productos;
import es.albarregas.dao.ICategoriasDAO;
import es.albarregas.dao.IProductosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.util.ArrayList;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author Adrian
 */
@WebListener
public class Inicio implements ServletContextListener {
    /*Esta clase es un evento que se ejecuta al iniciar la aplicacion*/
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        /*Se cargan todos los productos y categorias en la aplicacion para acceder a ellos facilmente*/
        IProductosDAO productos = daof.getProductosDAO();
        ICategoriasDAO categorias = daof.getCategoriasDAO();
        String where = "";
        ArrayList<Productos> listaProductos = productos.getProductos(where);
        ArrayList<Categorias> listaCategorias = categorias.getCategorias();
        synchronized (sce.getServletContext()) {
            sce.getServletContext().setAttribute("productos", listaProductos);
            sce.getServletContext().setAttribute("categorias", listaCategorias);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
