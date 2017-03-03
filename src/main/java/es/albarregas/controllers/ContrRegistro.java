/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Clientes;
import es.albarregas.beans.Usuarios;
import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.ClientesDAO;
import es.albarregas.dao.UsuariosDAO;
import es.albarregas.dao.IUsuariosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Adrian
 */
public class ContrRegistro extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = new String();
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IUsuariosDAO udao = daof.getUsuariosDAO();
        IClientesDAO cdao = daof.getClientesDAO();
        ArrayList<Usuarios> sessionUser;
        ArrayList<Clientes> sessionClient;
        HttpSession sesion = request.getSession();
        /*Este controlador se utiliza tanto para el registro como para el login. Si el cliente desea registrarse,
        se crea un nuevo usuario y un nuevo cliente (con datos predeterminados) en la base de datos*/
        if ("Aceptar".equals(request.getParameter("enviarRegistro"))) {
            Usuarios newUser = new Usuarios();
            newUser.setEmail(request.getParameter("email"));
            newUser.setClave(request.getParameter("clave"));
            udao.addUsuario(newUser);
            String where = "where Email='" + request.getParameter("email") + "' and Clave=MD5('" + request.getParameter("clave") + "')";
            cdao.addCliente(where);
            url = "JSP/registroExitoso.jsp";
        }
        if ("Aceptar".equals(request.getParameter("enviarLogin"))) {
            url = "JSP/loginExitoso.jsp";
        }
        /*se recogen tanto el usuario como el cliente que se acaba de logear/registrar. En el caso de que 
        dicho usuario sea nulo (debido a un logeo incorrecto) se redirige se le comunica al usuario de la pagina.
        Si todo ha sido correcto, se guardan en sesion para hacer uso de sus datos en la aplicacion*/
        sessionUser = udao.getUsuarios("where Email= '" + request.getParameter("email") + "' and Clave=MD5('" + request.getParameter("clave") + "')");
        sessionClient = cdao.getClientes("where IdCliente = (select IdUsuario from Usuarios where Email= '" + request.getParameter("email") + "' and Clave=MD5('" + request.getParameter("clave") + "'))");
        if (sessionUser == null || sessionClient==null || sessionUser.isEmpty() || sessionClient.isEmpty()) {
            url = "JSP/loginIncorrecto.jsp";
            sesion.invalidate();
        } else {
            sesion.setAttribute("usuarioSesion", sessionUser.get(0));
            sesion.setAttribute ("clienteSesion", sessionClient.get(0));
        }
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
