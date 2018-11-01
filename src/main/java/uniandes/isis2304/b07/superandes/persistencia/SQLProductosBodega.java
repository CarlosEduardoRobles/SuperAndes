package uniandes.isis2304.b07.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.b07.superandes.negocio.Cliente;

public class SQLProductosBodega 
{
	//-----------------------------------------------------------
	//-------------------------Constantes------------------------
	//-----------------------------------------------------------	
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra aca para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	//-----------------------------------------------------------
	//--------------------------Atributos------------------------
	//-----------------------------------------------------------
	/**
	 * El manejador de persistencia general de la aplicaciÃ³n
	 */
	private PersistenciaSuperAndes pp;

	//-----------------------------------------------------------
	//--------------------------Metodos--------------------------
	//-----------------------------------------------------------
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicacion
	 */
	public SQLProductosBodega (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarProductoBodega(PersistenceManager pm,long idBodega, long idSucursal, String codigoBarras,
			Integer cantidadProducto) 
	{		
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductosBodega()+"(idBodega, idSucursal, codigoBarras,cantidadProducto) values (?,?,?,?)");
		q.setParameters(idBodega, idSucursal, codigoBarras,cantidadProducto);
		return (long) q.executeUnique();
	}	
	
	/**
	 * Metodo para remover X unidades de un producto de una Bodega.
	 * @param pm - PersistenceManager, Maneja la persistencia
	 * @param idBodega - Id de la bodega a la que se le removera el producto.
	 * @param idSucursal - Id de la sucursal a la que se le removera el producto de su bodega.
	 * @param codigoBarras - Producto al que se disminuira su cantidad en la bodega.
	 * @param cantidadRemovida - La cantidad de productos que seran removidos.
	 * @return long.
	 */
	public long quitarProductosBodega(PersistenceManager pm, long idBodega, long idSucursal, String codigoBarras,
			Integer cantidadRemovida) 
	{		
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductosBodega() + " SET cantidadproducto = cantidadproducto - " + cantidadRemovida + " WHERE idBodega = ? AND "
				+ "idSucursal = ? AND codigoBarras = ?");
		q.setParameters(idBodega, idSucursal, codigoBarras);
		return (long) q.executeUnique();
	}
	
	/**
	 * Metodo para agregar X unidades de un producto de una Bodega.
	 * @param pm - PersistenceManager, Maneja la persistencia
	 * @param idBodega - Id de la bodega a la que se le agregara el producto.
	 * @param idSucursal - Id de la sucursal a la que se le agregara el producto de su bodega.
	 * @param codigoBarras - Producto al que se aumentara su cantidad en la bodega.
	 * @param cantidadAgregada - La cantidad de productos que seran agregados.
	 * @return long.
	 */
	public long añadirProductosBodega(PersistenceManager pm, long idBodega, long idSucursal, String codigoBarras,
			Integer cantidadAgregada) 
	{		
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductosBodega() + " SET cantidadproducto = cantidadproducto + " + cantidadAgregada + " WHERE idBodega = ? AND "
				+ "idSucursal = ? AND codigoBarras = ?");
		q.setParameters(idBodega, idSucursal, codigoBarras);
		return (long) q.executeUnique();
	}

}
