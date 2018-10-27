package uniandes.isis2304.b07.superandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLProductoPromocion {

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
	 * 			Metodos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLProductoPromocion (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarProductosPromocion(PersistenceManager pm, String codigoProducto, String codigoPromocion)
	{
		Query q = pm.newQuery(SQL,"INSERT INTO "+pp.darTablaProductoPromocion()+"(CODIGOPROMOCION,CODIGOPRODUCTO) VALUES (?,?)");
		q.setParameters(codigoPromocion,codigoProducto);
		return (long) q.executeUnique();

	}
}
