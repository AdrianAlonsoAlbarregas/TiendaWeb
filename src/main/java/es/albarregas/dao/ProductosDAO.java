/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

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
public class ProductosDAO implements IProductosDAO {

    @Override
    public void addProducto(Productos producto) {
        try {
            String sql = "insert into Productos (idProducto,idCategoria,idMarca,Denominacion,Descripcion,idProveedor,PrecioUnitario,Stock,StockMinimo,FechaAlta,Oferta,FueraCatalogo,Rating) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparada = ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setInt(1, producto.getIdProducto());
            preparada.setInt(2, producto.getIdCategoria());
            preparada.setInt(3, producto.getIdMarca());
            preparada.setString(4, producto.getDenominacion());
            preparada.setString(5, producto.getDescripcion());
            preparada.setInt(6, producto.getIdProveedor());
            preparada.setDouble(7, producto.getPrecioUnitario());
            preparada.setInt(8, producto.getStock());
            preparada.setInt(9, producto.getStockMinimo());
            preparada.setDate(10, producto.getFechaAlta());
            preparada.setString(11, producto.getOferta());
            preparada.setString(12, producto.getFueraCatalogo());
            preparada.setInt(13, producto.getRating());
        } catch (SQLException e) {
            System.out.println("Algo ha petao al añadir producto");
            Logger.getLogger(ProductosDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public ArrayList<Productos> getProductos(String where) {
        ArrayList<Productos> listaProductos = new ArrayList();
        String sql = "select * from productos " + where;
        try {
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            try (ResultSet resultado = sentencia.executeQuery(sql)) {
                while (resultado.next()) {
                    Productos producto = new Productos();
                    producto.setDenominacion(resultado.getString("Denominacion"));
                    producto.setDescripcion(resultado.getString("Descripcion"));
                    producto.setFechaAlta(resultado.getDate("FechaAlta"));
                    producto.setFueraCatalogo(resultado.getString("FueraCatalogo"));
                    producto.setIdCategoria(resultado.getInt("IdCategoria"));
                    producto.setIdMarca(resultado.getInt("IdMarca"));
                    producto.setIdProducto(resultado.getInt("IdProducto"));
                    producto.setIdProveedor(resultado.getInt("IdProveedor"));
                    producto.setOferta(resultado.getString("Oferta"));
                    producto.setPrecioUnitario(resultado.getDouble("PrecioUnitario"));
                    producto.setRating(resultado.getInt("Rating"));
                    producto.setStock(resultado.getInt("Stock"));
                    producto.setStockMinimo(resultado.getInt("StockMinimo"));
                    CaracteristicasDAO caracteristicas= new CaracteristicasDAO();
                    producto.setCaracteristicas(caracteristicas.getCaracteristicas(resultado.getInt("IdProducto")));
                    producto.setImagenes(getImagenes(resultado.getInt("IdProducto")));
                    listaProductos.add(producto);
                }
            }
        } catch (SQLException e) {
            System.out.println("Algo ha petao al mostrar los productos");
            Logger.getLogger(ProductosDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
        return listaProductos;
    }

    public ArrayList<String> getImagenes(int idProducto) {
        String sql = "select Image from imagenes where idProducto=" + idProducto;
        ArrayList<String> imagenes = new ArrayList();
        try {
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            while (resultado.next()) {
                String imagen = resultado.getString("Image");
                imagenes.add(imagen);
            }
        } catch (SQLException e) {
            System.out.println("error al sacar las imagenes");
        }finally{
            this.closeConnection();
        }
        return imagenes;
    }

    @Override
    public void deleteProducto(String where) {
        String sql = "delete from productos " + where;
        try {
            Statement sentencia = ConnectionFactory.getConnection().createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Algo ha petao al borrar el producto");
            Logger.getLogger(ProductosDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void updateProducto(Productos producto) {
        /*try{
            String sql = "update usuarios set ?=? where idProducto =?";
            
        }catch(SQLException e){
            
        }*/
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

}
