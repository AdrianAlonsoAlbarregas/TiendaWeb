/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Clientes;
import es.albarregas.beans.Usuarios;
import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.IUsuariosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ContrModificacion", urlPatterns = {"/ContrModificacion"})
public class ContrModificacion extends HttpServlet {

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
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        HttpSession sesion = request.getSession();
        String respuesta="";
        if ("usuario".equals(request.getParameter("modificar"))){
            IUsuariosDAO udao = daof.getUsuariosDAO();
            Usuarios usuario = (Usuarios)sesion.getAttribute("usuarioSesion");
            if (!usuario.getClave().equals(request.getParameter("oldPass"))){
                respuesta = "Negativa";
            }else{
                usuario.setClave(request.getParameter("newPass"));
                udao.updateUsuario(usuario);
                respuesta= "positiva";
                sesion.setAttribute("usuarioSesion", usuario);
            }
        }
        if ("cliente".equals(request.getParameter("modificar"))){
            IClientesDAO cdao = daof.getClientesDAO();
            Clientes cliente = (Clientes)sesion.getAttribute("clienteSesion");
            cliente.setNombre(request.getParameter("newFName"));
            cliente.setApellidos(request.getParameter("newLName"));
            if (!"Nif".equals(request.getParameter("newNif"))){
                cliente.setNif(request.getParameter("newNif"));
            }
            cdao.updateCliente(cliente);
            sesion.setAttribute("clienteSesion", cliente);
        }
        response.getWriter().write(respuesta);
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
