package uniandes.isis2304.b07.superandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLVentaProducto {
	
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
	public SQLVentaProducto(PersistenciaSuperAndes pp) {
		
		this.pp = pp;
	}

	public long adicionarVentaProducto(PersistenceManager pm, long numeroVenta, String codigoProducto, String cantidad) {

		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaVentaProducto()+"(NUMEROVENTA,CODIGOPRODUCTO,UNIDADES) values (?,?,?)");
		q.setParameters(numeroVenta,codigoProducto,cantidad);
		return (long) q.executeUnique();
	}

}
