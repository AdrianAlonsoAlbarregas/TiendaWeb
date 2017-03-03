package es.albarregas.daofactory;

import es.albarregas.dao.ICategoriasDAO;
import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.IDireccionesDAO;
import es.albarregas.dao.ILineaPedidosDAO;
import es.albarregas.dao.IPedidosDAO;
import es.albarregas.dao.IProductosDAO;
import es.albarregas.dao.IPueblosDAO;
import es.albarregas.dao.IUsuariosDAO;

public abstract class DAOFactory {
    public static final int MYSQL=1;
    public static final int ORACLE = 2;
    public static final int FICHERO = 3;
    
    public abstract IClientesDAO getClientesDAO();
    public abstract IProductosDAO getProductosDAO();
    public abstract IUsuariosDAO getUsuariosDAO();
    public abstract ICategoriasDAO getCategoriasDAO();
    public abstract ILineaPedidosDAO getLineaPedidosDAO();
    public abstract IPedidosDAO getPedidosDAO();
    public abstract IDireccionesDAO getDireccionesDAO();
    public abstract IPueblosDAO getPueblosDAO();
   
    public static DAOFactory getDAOFactory(int tipo){
        DAOFactory daof = null;
        
        switch(tipo){
            case MYSQL:
                daof = new MySQLDAOFactory();
                break;
        }
        
        return daof;
    }
}
