/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Categorias;
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
@WebServlet(name = "ContrListas", urlPatterns = {"/ContrListas"})
public class ContrListas extends HttpServlet {

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
        /*Se cargan los productos y se inicializan 2 arrayLists, uno para cargar los productos que se visualizaran
        al entrar en la pagina y otro para guardar el numero de paginas que habra. Este ultimo es un arrayList
        para poder cargarlo dinamicamente*/
        ArrayList<Productos> productos = (ArrayList<Productos>) contexto.getAttribute("productos");
        ArrayList<Productos> productosPrimPag = new ArrayList();
        ArrayList<Integer> arrayPaginas = new ArrayList();
        String url = "";
        /*Si no se ha seleccionado una categoria en concreto, se muestra la lista completa de productos, Cargando los 10
        primeros y añadiendo un valor a arrayPaginas cada 10 productos*/
        if (request.getParameter("cat") == null) {
            url = "JSP/listaProductos.jsp";
            int numTotal = productos.size();
            int numPaginas = 0;
            for (int i = 0; i < 10; i++) {
                productosPrimPag.add(productos.get(i));
            }
            for (int i = 0; i < numTotal; i++) {
                if (i % 10 == 0) {
                    numPaginas++;
                    arrayPaginas.add(numPaginas);
                }
            }
        }
        if (request.getParameter("cat") != null) {
            /*Si se ha seleccionado una categoria, se cargan las categorias en un array y se inicializa otra lista
            para guardar los productos especificos de dichaa categroia*/
            ArrayList<Categorias> categorias = (ArrayList<Categorias>) contexto.getAttribute("categorias");
            ArrayList<Productos> productosCategoria = new ArrayList();

            String categoria = "";
            for (Productos prod : productos) {
                /*se recorren los productos, y por cada producto cuya categoria coincida con la seleccionada se guarda
                en la lista de productosCategoria*/
                if (prod.getIdCategoria() == Integer.parseInt(request.getParameter("cat"))) {
                    productosCategoria.add(prod);
                    if (productosCategoria.size() <= 10) {
                        productosPrimPag.add(prod);
                    }
                }
            }
            /*Se guarda el nombre de la categoria seleccionada para mostrarla en la vista*/
            for (Categorias cat : categorias) {
                if (cat.getIdCategoria() == Integer.parseInt(request.getParameter("cat"))) {
                    categoria = cat.getNombre();
                    break;
                }
            }
            /*Se calcula el numero de paginas que producira la lista*/
            int numTotal = productosCategoria.size();
            int numPaginas = 0;
            for (int i = 0; i < numTotal; i++) {
                if (i % 10 == 0) {
                    numPaginas++;
                    arrayPaginas.add(numPaginas);
                }
            }

            contexto.setAttribute("productosCategoria", productosCategoria);
            request.setAttribute("categoriaSelec", categoria);
            url = "JSP/productosPorCategoria.jsp";
        }
        request.setAttribute("numPaginas", arrayPaginas);
        request.setAttribute("productosMostrar", productosPrimPag);
        request.getRequestDispatcher(url).forward(request, response);
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
