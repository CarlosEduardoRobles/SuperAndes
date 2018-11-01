package uniandes.isis2304.b07.superandes.persistencia;

import java.sql.Timestamp;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLPromocion 
{
	//-----------------------------------------------------------
	//-------------------------Constantes------------------------
	//-----------------------------------------------------------
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
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
	public SQLPromocion (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarPromocion(PersistenceManager pm, String codigoPromocion, int tipoPromocion, Timestamp fechaTerminacion)
	{
		Query q = pm.newQuery(SQL,"INSERT INTO "+pp.darTablaPromocion()+" (CODIGOPROMOCION, TIPOPROMOCION,FECHATERMINACION) VALUES (?,?,?)");
		q.setParameters(codigoPromocion,tipoPromocion,fechaTerminacion);
		return (long) q.executeUnique();

	}
	
	//TODO Esta operacion se hace sin interaccion del usuario, sea cuando se vendieron todos los productos de
	//la promocion o porque llego la fecha de vencimiento de la promoci�n.
	public long eliminarPromocion(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL,"DELETE FROM "+ pp.darTablaPromocion()+ " "
				+ "WHERE FECHATERMINACION = SYSDATE ");
		return (long) q.executeUnique();
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
