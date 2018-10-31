package uniandes.isis2304.b07.superandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLBodega 
{
	// -----------------------------------------------------------
	// -------------------------Constantes------------------------
	// -----------------------------------------------------------
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las
	 * sentencias de acceso a la base de datos Se renombra aca para facilitar la
	 * escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	// -----------------------------------------------------------
	// --------------------------Atributos------------------------
	// -----------------------------------------------------------
	/**
	 * El manejador de persistencia general de la aplicacion
	 */
	private PersistenciaSuperAndes pp;

	// -----------------------------------------------------------
	// --------------------------Metodos--------------------------
	// -----------------------------------------------------------
	public SQLBodega (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarBodega(PersistenceManager pm, long id, long idSucursal, long idCategoria, Double volumenMaximo, Double pesoMaximo) 
	{		
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaBodega()+"(id, idSucursal,idCategoria, volumenMaximo "
				+ ",pesoMaximo) values (?, ?, ?, ?, ?, ?)");
		q.setParameters(id, idSucursal,idCategoria, volumenMaximo, pesoMaximo);
		return (long) q.executeUnique();
	}
	
	public long eliminarBodegaPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBodega() + " WHERE id = ?");
	    q.setParameters(id);
	    return (long) q.executeUnique();
	}	
}

