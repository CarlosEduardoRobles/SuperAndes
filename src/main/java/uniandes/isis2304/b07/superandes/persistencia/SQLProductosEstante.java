package uniandes.isis2304.b07.superandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLProductosEstante 
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
	public SQLProductosEstante (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarProductoEstante(PersistenceManager pm,long idEstante, long idSucursal, String codigoBarras,
			Integer cantidadProducto) 
	{		
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductosEstante()+"(idEstante, idSucursal, codigoBarras,cantidadProducto) values (?,?,?,?)");
		q.setParameters(idEstante, idSucursal, codigoBarras,cantidadProducto);
		return (long) q.executeUnique();
	}
	
	/**
	 * Metodo para remover X unidades de un producto de un estante.
	 * @param pm - PersistenceManager, Maneja la persistencia
	 * @param idEstante - Id del estante al que se le removera el producto.
	 * @param idSucursal - Id de la sucursal a la que se le removera el producto de su bodega.
	 * @param codigoBarras - Producto al que se disminuira su cantidad en la bodega.
	 * @param cantidadRemovida - La cantidad de productos que seran removidos.
	 * @return long.
	 */
	public long quitarProductosEstante(PersistenceManager pm, long idEstante, long idSucursal, String codigoBarras,
			Integer cantidadRemovida) 
	{		
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductosEstante() + " SET cantidadproducto = cantidadproducto - " + cantidadRemovida + " WHERE idEstante = ? AND "
				+ "idSucursal = ? AND codigoBarras = ?");
		q.setParameters(idEstante, idSucursal, codigoBarras);
		return (long) q.executeUnique();
	}
	
	/**
	 * Metodo para agregar X unidades de un producto de un estante.
	 * @param pm - PersistenceManager, Maneja la persistencia
	 * @param idEstante - Id del estante al que se le agregara el producto.
	 * @param idSucursal - Id de la sucursal a la que se le agregara el producto de su bodega.
	 * @param codigoBarras - Producto al que se aumentara su cantidad en la bodega.
	 * @param cantidadAgregada - La cantidad de productos que seran agregados.
	 * @return long.
	 */
	public long añadirProductosEstante(PersistenceManager pm, long idEstante, long idSucursal, String codigoBarras,
			Integer cantidadAgregada) 
	{		
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductosEstante() + " SET cantidadproducto = cantidadproducto + " + cantidadAgregada + " WHERE idEstante = ? AND "
				+ "idSucursal = ? AND codigoBarras = ?");
		q.setParameters(idEstante, idSucursal, codigoBarras);
		return (long) q.executeUnique();
	}
}
