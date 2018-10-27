package uniandes.isis2304.b07.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.b07.superandes.negocio.Cliente;

public class SQLCliente 
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
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLCliente (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}

	public long adicionarCliente(PersistenceManager pm,String tipoDocumento, String numDocumento, String nombre, String correo) 
	{		
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCliente()+"(TIPODOCUMENTO,NUMDOCUMENTO,NOMBRE,CORREO) values (?,?,?,?)");
		q.setParameters(tipoDocumento, numDocumento, nombre, correo);
		return (long) q.executeUnique();
	}
	
	public long eliminarClientePorNumDocumento (PersistenceManager pm, String numDocumento)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente() + " WHERE NUMDOCUMENTO = ?");
	    q.setParameters(numDocumento);
	    return (long) q.executeUnique();
	}
	
	public Cliente darClientePorNumDocumento (PersistenceManager pm, String numDocumento) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente() + " WHERE NUMDOCUMENTO = ?");
		q.setParameters(numDocumento);
		return (Cliente) q.executeUnique();
	}
	
	public Cliente darClientePorNombreCompleto (PersistenceManager pm, String nombre, String apellido) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente() + " WHERE NOMBRE = ? and APELLIDO = ?");
		q.setParameters(nombre, apellido);
		return (Cliente) q.executeUnique();
	}
		
	public List<Cliente> darClientes(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente());
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}
}
