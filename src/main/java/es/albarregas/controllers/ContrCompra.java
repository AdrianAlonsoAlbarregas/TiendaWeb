/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Clientes;
import es.albarregas.beans.LineaPedidos;
import es.albarregas.beans.Pedidos;
import es.albarregas.dao.ILineaPedidosDAO;
import es.albarregas.dao.IPedidosDAO;
import es.albarregas.dao.IProductosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Adrian
 */
@WebServlet(name = "ContrCompra", urlPatterns = {"/ContrCompra"})
public class ContrCompra extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession();
        Clientes cliente = (Clientes) sesion.getAttribute("sessionClient");
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IPedidosDAO pdao = daof.getPedidosDAO();
        IProductosDAO prdao = daof.getProductosDAO();
        ILineaPedidosDAO lpdao = daof.getLineaPedidosDAO();
        Pedidos pedido = new Pedidos();
        ArrayList<LineaPedidos> productosCarrito = new ArrayList();
        LineaPedidos producto = new LineaPedidos();
        if (sesion.getAttribute("pedidoActual") == null) {
            pedido.setIdCliente(cliente.getIdCliente());
            pdao.addPedido(pedido);
            ArrayList<Pedidos> listaPedidos = pdao.getPedidos("where IdCliente=" + cliente.getIdCliente());
            pedido = listaPedidos.get(listaPedidos.size() - 1);
            sesion.setAttribute("pedidoActual", pedido);
        } else {
            pedido = (Pedidos) sesion.getAttribute("pedidoActual");
        }
        if (sesion.getAttribute("carrito") == null) {
            producto.setIdPedido(pedido.getIdPedido());
            producto.setIdProducto(Integer.parseInt(request.getParameter("producto")));
            producto.setCantidad(1);
            double precioProducto = prdao.getProductos("where IdProducto=" + producto.getIdProducto()).get(0).getPrecioUnitario();
            producto.setPrecioUnitario(precioProducto);
            lpdao.addLinea(producto);
            productosCarrito = lpdao.getLineas("where IdPedido=" + producto.getIdPedido());
            sesion.setAttribute("carrito", productosCarrito);
        } else {
            boolean enCarrito = false;
            productosCarrito = (ArrayList<LineaPedidos>) sesion.getAttribute("carrito");
            for (LineaPedidos linea : productosCarrito) {
                if (linea.getIdProducto() == Integer.parseInt(request.getParameter("producto"))) {
                    producto = linea;
                    producto.setCantidad(producto.getCantidad() + Integer.parseInt(request.getParameter("cantidad")));
                    lpdao.updateLinea(producto);
                    enCarrito = true;
                    break;
                }
            }
            if (!enCarrito) {
                producto.setIdPedido(pedido.getIdPedido());
                producto.setIdProducto(Integer.parseInt(request.getParameter("producto")));
                producto.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
                double precioProducto = prdao.getProductos("where IdProducto=" + producto.getIdProducto()).get(0).getPrecioUnitario();
                producto.setPrecioUnitario(precioProducto);
                lpdao.addLinea(producto);
            }
            productosCarrito = lpdao.getLineas("where IdPedido=" + producto.getIdPedido());
            sesion.setAttribute("carrito", productosCarrito);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
