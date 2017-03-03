/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Usuarios;
import java.util.ArrayList;

/**
 *
 * @author Adrian
 */
public interface IUsuariosDAO {
    public void addUsuario (Usuarios usuario);
    public ArrayList<Usuarios> getUsuarios(String where);
    public void updateUsuario (Usuarios usuario);
    public void deleteUsuario (String where);
    public void closeConnection();
}
