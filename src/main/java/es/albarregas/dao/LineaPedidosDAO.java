/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.LineaPedidos;
import es.albarregas.beans.Productos;
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
public class LineaPedidosDAO implements ILineaPedidosDAO{

    @Override
    public void addLinea(LineaPedidos linea) {
        try{
            String sql = "insert into lineaspedidos (IdPedido, NumeroLinea, IdProducto, Cantidad, PrecioUnitario) values (?,?,?,?,?)";
            PreparedStatement preparada= ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setInt(1, linea.getIdPedido());
            preparada.setInt(2, linea.getNumeroLinea());
            preparada.setInt(3, linea.getIdProducto());
            preparada.setInt(4, linea.getCantidad());
            preparada.setDouble(5, linea.getPrecioUnitario());
            preparada.executeUpdate();
        }catch(SQLException e){
            System.out.println("Algo ha petao al añadir una linea");
            Logger.getLogger(LineaPedidosDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.closeConnection();
        }
    }

    @Override
    public ArrayList<LineaPedidos> getLineas(int idPedido) {
        ArrayList<LineaPedidos> listaLineas = new ArrayList();
        String sql= "select * from lineaspedidos where IdPedido="+idPedido;
        try{
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            try(ResultSet resultado = sentencia.executeQuery(sql) ){
                while(resultado.next()){
                    LineaPedidos linea= new LineaPedidos();
                    linea.setCantidad(resultado.getInt("Cantidad"));
                    linea.setIdPedido(resultado.getInt("IdPedido"));
                    linea.setIdProducto(resultado.getInt("IdProducto"));
                    linea.setNumeroLinea(resultado.getInt("NumeroLinea"));
                    linea.setPrecioUnitario(resultado.getDouble("PrecioUnitario"));
                    ProductosDAO producto = new ProductosDAO();
                    linea.setProducto(producto.getProductos("where IdProducto="+resultado.getInt("IdProducto")));
                    listaLineas.add(linea);
                }
            }
        }catch(SQLException e){
            System.out.println("Algo ha petao al mostrar las lineas");
            Logger.getLogger(LineaPedidosDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.closeConnection();
        }
        return listaLineas;
    }

    @Override
    public void deleteLinea(String where) {
        try{
            String sql= "delete from lineaspedidos "+ where;
            PreparedStatement preparada= ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.executeUpdate();
        }catch(SQLException e){
            System.out.println("Algo ha petao al mostrar las lineas");
            Logger.getLogger(LineaPedidosDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.closeConnection();
        }
    }

    @Override
    public void updateLinea(LineaPedidos linea) {
        try{
            String sql= "update lineaspedidos set Cantidad = ? where IdPedido=? and NumeroLinea=?";
            PreparedStatement preparada= ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setInt(1, linea.getCantidad());
            preparada.setInt(2, linea.getIdPedido());
            preparada.setInt(3, linea.getNumeroLinea());
            preparada.executeUpdate();
        }catch(SQLException e){
            System.out.println("Algo ha petao al mostrar las lineas");
            Logger.getLogger(LineaPedidosDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.closeConnection();
        }
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
    
}
