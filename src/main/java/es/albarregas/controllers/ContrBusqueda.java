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
@WebServlet(name = "ContrBusqueda", urlPatterns = {"/ContrBusqueda"})
public class ContrBusqueda extends HttpServlet {

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
        /*Se cargan los productos en un ArrayList, y se inicializa el ArrayList de resultados */
        ArrayList<Productos> productos = (ArrayList<Productos>) contexto.getAttribute("productos");
        ArrayList<Productos> resultados = new ArrayList();
        String texto = "";
        String cadena = request.getParameter("cadena");
        /*Si el parametro cadena esta vacio, se devuelve un texto vacio para borrar los resultados anteriores*/
        if ("".equals(cadena) || " ".equals(cadena)) {
            texto = "";
        } else {
            /*Por cada producto, se comprueba que haya resultados con la cadena de busqueda. Los que
            coincidan se añaden a la lista de resultados. El metodo contains() discrimina mayusculas de minusculas*/
            for (Productos producto : productos) {
                if (producto.getDenominacion().toLowerCase().contains(cadena.toLowerCase())) {
                    resultados.add(producto);
                }
            }
            /*Si ningun producto coincide con la cadena de busqueda, se notifica en la respuesta*/
            if (resultados.isEmpty()) {
                texto = "No ha habido coincidencias en su busqueda";
            } else {
                /*Se escribe la respuesta para enviarsela a Ajax y que este la escriba en la pagina*/
                texto = texto + "<ul>";
                for (Productos resultado : resultados) {
                    texto = texto + "<li><a href=\"ContrProducto?prod=" + resultado.getIdProducto() + "\"> <img src=\"" + request.getContextPath() + "/imagenesProductos/" + resultado.getImagenes().get(0) + "\" style=\"width: 64px; height: 64px;\"> " + resultado.getDenominacion() + "</a></li>";
                }
                texto = texto + "</ul>";
            }
        }
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
