package uniandes.isis2304.b07.superandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.b07.superandes.negocio.Categoria;


public class SQLCategoria 
{
	//-----------------------------------------------------------
	//--------------------------Atributos------------------------
	//-----------------------------------------------------------	
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;
			
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes psa;
			
	//-----------------------------------------------------------
	//-----------------------Constructores-----------------------
	//-----------------------------------------------------------
	public SQLCategoria(PersistenciaSuperAndes psa)	{ this.psa = psa; }
		
	//-----------------------------------------------------------
	//--------------------------Metodos--------------------------
	//-----------------------------------------------------------
	public long adicionarCategoria(PersistenceManager pm, long id, String tipo) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + PersistenciaSuperAndes.darTablaCategoria() + "(idcategoria, tipocat) values (?, ?)");
		q.setParameters(id, tipo);
	    return (long)q.executeUnique();            
	}
			
	public long eliminarCategoriaPorId (PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + PersistenciaSuperAndes.darTablaCliente () + " WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}
	
	public long eliminarCategoriaPorTipo (PersistenceManager pm, String tipo)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + PersistenciaSuperAndes.darTablaCliente () + " WHERE tipo = ?");
		q.setParameters(tipo);
		return (long) q.executeUnique();
	}
		
	public Categoria darCategoriaPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + PersistenciaSuperAndes.darTablaCliente () + " WHERE id = ?");
		q.setParameters(id);
		return (Categoria) q.executeUnique();
	}
	
	public Categoria darCategoriaPorTipo (PersistenceManager pm, String tipo) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + PersistenciaSuperAndes.darTablaCliente () + " WHERE tipo = ?");
		q.setParameters(tipo);
		return (Categoria) q.executeUnique();
	}
			
	public List<Categoria> darCategorias (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + PersistenciaSuperAndes.darTablaCliente ());
		q.setResultClass(Categoria.class);
		return (List<Categoria>) q.executeList();
	}

}
