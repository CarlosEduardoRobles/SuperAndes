package uniandes.isis2304.b07.superandes.persistencia;

import java.sql.Timestamp;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;



public class SQLLegadaPedido {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLLegadaPedido (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long registrarLlegadaPedido(PersistenceManager pm, long codigoPedido, long idSucursal,
			Timestamp fechaLlegada, int cantidadProductos, String calidadProductos, String calificacion) {
		Query q = pm.newQuery(SQL,"INSERT INTO "+ pp.darTablaLlegadaPedido() +"(CODIGOPEDIDO,IDSUCURSAL,FECHAENTREGA,CANTIDADPRODUCTOS,CALIDADPRODUCTOS,CALIFICACION) VALUES (?, ?, ?, ?, ?, ?)");
		q.setParameters(codigoPedido,idSucursal,fechaLlegada,cantidadProductos,calidadProductos,calificacion);
		return (long) q.executeUnique();

	}

}
