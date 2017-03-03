/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Usuarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrian
 */
public class UsuariosDAO implements IUsuariosDAO {

    @Override
    public void addUsuario(Usuarios usuario) {
        try {
            String sql = "insert into usuarios (Email, Clave) values (?, MD5(?))";
            PreparedStatement preparada = ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setString(1, usuario.getEmail());
            preparada.setString(2, usuario.getClave());
            preparada.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Esto a petao al insertar. a ver que pasa");
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public ArrayList<Usuarios> getUsuarios(String where) {
        ArrayList<Usuarios> listaUsuarios = new ArrayList<Usuarios>();
        String sql = "select IdUsuario, Email, Clave, Tipo, Bloqueado from usuarios " + where;
        try {
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(sql)) {
                while (resultado.next()) {
                    Usuarios usuario = new Usuarios();
                    usuario.setIdUsuario(resultado.getInt("IdUsuario"));
                    usuario.setEmail(resultado.getString("Email"));
                    usuario.setClave(resultado.getString("Clave"));
                    usuario.setTipo(resultado.getString("Tipo"));
                    usuario.setBloqueado(resultado.getString("Bloqueado"));
                    listaUsuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            System.out.println("Algo ha petao al mostrar los usuarios");
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
        return listaUsuarios;
    }

    @Override
    public void updateUsuario(Usuarios usuario) {
        try {
            String sql = "update usuarios set Email=?, Clave=? where IdUsuario = ?";
            PreparedStatement preparada = ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setString(1, usuario.getEmail());
            preparada.setString(2, usuario.getClave());
            preparada.setInt(3, usuario.getIdUsuario());
            preparada.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Algo ha petao al actualizar");
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void deleteUsuario(String where) {
        try {
            String sql = "delete from usuarios " + where;
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Algo ha petao al borrar");
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

}
