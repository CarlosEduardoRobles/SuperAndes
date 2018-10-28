package uniandes.isis2304.b07.superandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLProductoProveedor 
{
	
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
	public SQLProductoProveedor(PersistenciaSuperAndes pp){
		
		this.pp = pp;
	}
	

	public void registrarProductoAProveedor(PersistenceManager pm, String barCode, String nit, String calif, String prec) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + PersistenciaSuperAndes.darTablaProductoProveedor()+ "(NITPROVEEDOR,CODIGOPRODUCTO,PRECIO,CALIFICACIONCALIDAD) values (?, ?, ?, ?)"); 
		q.setParameters(nit,barCode, prec,calif);
		q.executeUnique();
	}

}
