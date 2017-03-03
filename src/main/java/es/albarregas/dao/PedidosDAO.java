/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Pedidos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrian
 */
public class PedidosDAO implements IPedidosDAO {

    @Override
    public void addPedido(Pedidos pedido) {
        try{
            String sql = "insert into pedidos (Fecha, Estado, IdCliente, BaseImponible, Descuento, GastosEnvio, Iva, IdDireccion) values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparada= ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setDate(1, pedido.getFecha());
            preparada.setString(2, pedido.getEstado());
            preparada.setInt(3, pedido.getIdCliente());
            preparada.setDouble(4, pedido.getBaseImponible());
            preparada.setDouble(5, pedido.getDescuento());
            preparada.setDouble(6, pedido.getGastosEnvio());
            preparada.setDouble(7, pedido.getIva());
            preparada.setInt(8, pedido.getIdDireccion());
            preparada.executeUpdate();
        }catch(SQLException e){
            Logger.getLogger(PedidosDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.closeConnection();
        }
    }

    @Override
    public void updatePedido(Pedidos pedido) {
        try{
            String sql= "update pedidos set Fecha=?, Estado=?, IdDireccion=? where IdPedido=?";
            PreparedStatement preparada= ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setDate(1, pedido.getFecha());
            preparada.setString(2, pedido.getEstado());
            preparada.setInt(3, pedido.getIdDireccion());
            preparada.setInt(4, pedido.getIdPedido());
            preparada.executeUpdate();
        }catch(SQLException e){
            Logger.getLogger(PedidosDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.closeConnection();
        }
    }

    @Override
    public void deletePedido(String where) {
        try{
            String sql = "delete from pedidos "+ where;
            PreparedStatement preparada= ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.executeUpdate();
        }catch(SQLException e){
            Logger.getLogger(PedidosDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.closeConnection();
        }
    }

    @Override
    public ArrayList<Pedidos> getPedidos(String where) {
        ArrayList<Pedidos> listaPedidos = new ArrayList();
       try{
           String sql = "select * from pedidos" + where;
           PreparedStatement preparada = ConnectionFactory.getConnection().prepareStatement(sql);
           try(ResultSet resultado= preparada.executeQuery()){
               while(resultado.next()){
                   Pedidos pedido = new Pedidos();
                   pedido.setBaseImponible(resultado.getDouble("BaseImponible"));
                   pedido.setDescuento(resultado.getDouble("Descuento"));
                   pedido.setEstado(resultado.getString("Estado"));
                   pedido.setIdCliente(resultado.getInt("IdCliente"));
                   pedido.setIdDireccion(resultado.getInt("IdDireccion"));
                   pedido.setFecha(resultado.getDate("Fecha"));
                   pedido.setIva(resultado.getDouble("Iva"));
                   pedido.setIdPedido(resultado.getInt("IdPedido"));
                   pedido.setGastosEnvio(resultado.getDouble("GastosEnvio"));
                   LineaPedidosDAO lpdao = new LineaPedidosDAO();
                   pedido.setLineaPedidos(lpdao.getLineas("where IdPedido="+pedido.getIdPedido()));
                   listaPedidos.add(pedido);
               }
           }
       }catch(SQLException e){
            Logger.getLogger(PedidosDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.closeConnection();
        }
       return listaPedidos;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
    
}
