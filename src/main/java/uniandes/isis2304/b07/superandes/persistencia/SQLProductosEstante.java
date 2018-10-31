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
	 * El manejador de persistencia general de la aplicación
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
}
