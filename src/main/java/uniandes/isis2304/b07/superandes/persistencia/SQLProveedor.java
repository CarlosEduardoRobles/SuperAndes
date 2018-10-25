package uniandes.isis2304.b07.superandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLProveedor {
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
	public SQLProveedor (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarProveedor(PersistenceManager pm, String nit, String nombre)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProveedor()+"(NIT,NOMBRE) values (?,?)");
		q.setParameters(nit,nombre);
		return (long) q.executeUnique();
		
	}
}
