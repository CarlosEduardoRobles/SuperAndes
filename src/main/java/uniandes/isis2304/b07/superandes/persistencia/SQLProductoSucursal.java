package uniandes.isis2304.b07.superandes.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLProductoSucursal {
	
	
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
	public SQLProductoSucursal(PersistenciaSuperAndes pp) {
		
		this.pp = pp;
	}

	public String[] darPrecioProductosSucursal(PersistenceManager pm, String sucursal, String[] productos) {
		
		String[] precios = new String[productos.length];
				
		for (int i = 0; i < productos.length; i++) {
		
			Query q = pm.newQuery(SQL, "SELECT PRECIOUNITARIO FROM " + pp.darTablaProductoSucursal()+" WHERE IDSUCURSAL = "+sucursal+" AND CODIGOBARRAS = "+productos[i]);
			precios[i] = Integer.toString((int) q.executeUnique());				
			
		}
		
		return precios;
	}

}
