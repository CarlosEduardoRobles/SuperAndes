package uniandes.isis2304.b07.superandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLBodega {
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
	public SQLBodega (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long insertarBodega(PersistenceManager pm,long idSucursal, long id, long idTipoProducto, double actualCapacidadVolumen, double maximaCapacidadVolumen,
			double capacidadPeso, double capacidadTotalPeso) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaBodega  () + "(idSucursal, id, idTipoProducto, actualCapacidadVolumen, maximaCapacidadVolumen,"+
    			"capacidadPeso, capacidadTotalPeso) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(idSucursal, id, idTipoProducto, actualCapacidadVolumen, maximaCapacidadVolumen,
    			capacidadPeso, capacidadTotalPeso);
        return (long) q.executeUnique();
	}
}
