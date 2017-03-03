/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Productos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Adrian
 */
@WebServlet(name = "ContrPaginar", urlPatterns = {"/ContrPaginar"})
public class ContrPaginar extends HttpServlet {

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
        ServletContext contexto = request.getServletContext();
        /*Se calcula el numero del producto inicial. Segun la formula, si el numero de pagina inicial es 1,
        el primer produco correspondera al indice 0 de la lista de productos. si el numero es 2, correspondera al indice 10,
        si es 3 al 20 y asi sucesivamente*/
        int numProdInicial = (Integer.parseInt(request.getParameter("numeroPag")) - 1) * 10;
        String texto = "";
        /*Si se procede de una pagina de categorias se trabaja tan solo con los productos de dicha categoria.
        Si no, se trabaja con todos los productos*/
        if ("cat".equals(request.getParameter("origen"))) {
            ArrayList<Productos> productosCategoria = (ArrayList<Productos>) contexto.getAttribute("productosCategoria");
            /*se recogen los 10 productos correspondientes a la pagina elegida, a no ser que dicha pagina tenga menos de 10
            productos, por ejemplo si es la ultima pagina de la lista*/
            if ((numProdInicial + 10) < productosCategoria.size()) {
                for (int i = numProdInicial; i < (numProdInicial + 10); i++) {
                    texto = texto + "<div class=\"col-lg-6\">"
                            + "<p id=\"mensaje\"><a href=\"ContrProducto?prod=" + productosCategoria.get(i).getIdProducto() + "\"><img src=\"" + request.getContextPath() + "/imagenesProductos/" + productosCategoria.get(i).getImagenes().get(0) + "\" style=\"width: 120px; height: 120px;\"></a><br/>"
                            + productosCategoria.get(i).getDenominacion() + "</p>"
                            + "</div>";
                }
            } else {
                for (int i = numProdInicial; i < productosCategoria.size(); i++) {
                    ArrayList<String> imagenes = productosCategoria.get(i).getImagenes();
                    texto = texto + "<div class=\"col-lg-6\">"
                            + "<p id=\"mensaje\"><a href=\"ContrProducto?prod=" + productosCategoria.get(i).getIdProducto() + "\"><img src=\"" + request.getContextPath() + "/imagenesProductos/" + imagenes.get(0) + "\" style=\"width: 120px; height: 120px;\"></a><br/>"
                            + productosCategoria.get(i).getDenominacion() + "</p>"
                            + "</div>";
                }
            }
        }
        /*Si se procede de la lista de todos los productos, el procedimiento es el mismo, pero trabajando con las lista
        de productos completa*/
        if ("todos".equals(request.getParameter("origen"))) {
            ArrayList<Productos> productos = (ArrayList<Productos>) contexto.getAttribute("productos");
            if ((numProdInicial + 10) < productos.size()) {
                for (int i = numProdInicial; i < (numProdInicial + 10); i++) {
                    texto = texto + "<div class=\"col-lg-6\">"
                            + "<p id=\"mensaje\"><a href=\"ContrProducto?prod=" + productos.get(i).getIdProducto() + "\"><img src=\"" + request.getContextPath() + "/imagenesProductos/" + productos.get(i).getImagenes().get(0) + "\" style=\"width: 120px; height: 120px;\"></a><br/>"
                            + productos.get(i).getDenominacion() + "</p>"
                            + "</div>";
                }
            } else {
                for (int i = numProdInicial; i < productos.size(); i++) {
                    texto = texto + "<div class=\"col-lg-6\">"
                            + "<p id=\"mensaje\"><a href=\"ContrProducto?prod=" + productos.get(i).getIdProducto() + "\"><img src=\"" + request.getContextPath() + "/imagenesProductos/" + productos.get(i).getImagenes().get(0) + "\" style=\"width: 120px; height: 120px;\"></a><br/>"
                            + productos.get(i).getDenominacion() + "</p>"
                            + "</div>";
                }

            }
        }
        /*Se devuelve a Ajax la respuesta del servlet, que este escribira en la vista*/
        response.getWriter().write(texto);
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
