package uniandes.isis2304.b07.superandes.persistencia;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.b07.superandes.negocio.Bodega;
import uniandes.isis2304.b07.superandes.negocio.Cliente;
import uniandes.isis2304.b07.superandes.negocio.DescPorcentajePromo;
import uniandes.isis2304.b07.superandes.negocio.Estante;
import uniandes.isis2304.b07.superandes.negocio.LlegadaPedido;
import uniandes.isis2304.b07.superandes.negocio.Pague1Lleve2ConDescPromo;
import uniandes.isis2304.b07.superandes.negocio.PagueNUnidadesLleveMPromo;
import uniandes.isis2304.b07.superandes.negocio.PagueXCantidadLleveYPromo;
import uniandes.isis2304.b07.superandes.negocio.PaqueteDeProductos;
import uniandes.isis2304.b07.superandes.negocio.Pedido;
import uniandes.isis2304.b07.superandes.negocio.PersonaJuridica;
import uniandes.isis2304.b07.superandes.negocio.Producto;
import uniandes.isis2304.b07.superandes.negocio.Promocion;
import uniandes.isis2304.b07.superandes.negocio.Proveedor;
import uniandes.isis2304.b07.superandes.negocio.Sucursal;
import uniandes.isis2304.b07.superandes.negocio.Venta;




public class PersistenciaSuperAndes {
	//------------------------------------------------------------------
	//----------------------------Constantes----------------------------
	//------------------------------------------------------------------
	/**
	 * Logger para escribir la traza de la ejecuci�n
	 */
	private static Logger log = Logger.getLogger(PersistenciaSuperAndes.class.getName());

	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	//------------------------------------------------------------------
	//-----------------------------Atributos----------------------------
	//------------------------------------------------------------------
	/**
	 * Atributo privado que es el �nico objeto de la clase - Patr�n SINGLETON
	 */
	private static PersistenciaSuperAndes instance;

	/**
	 * F�brica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;

	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 */
	private List <String> tablas;

	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaParranderos
	 */
	private SQLUtil sqlUtil;

	//------------------------------------------------------------------
	//----------Atributos para manejar los sql de las tablas------------
	//------------------------------------------------------------------
	/**
	 * Atributo para el acceso a la tabla BODEGA de la BD.
	 */
	private SQLBodega sqlBodega;

	/**
	 * Atributo para el acceso a la tabla CATEGORIA de la BD.
	 */
	private SQLCategoria sqlCategoria;

	/**
	 * Atributo para el acceso a la tabla CATEGORIAPRODUCTO de la BD.
	 */
	private SQLCategoriaProducto sqlCategoriaProducto;

	/**
	 * Atributo para el acceso a la tabla CLIENTE de la BD.
	 */
	private SQLCliente sqlCliente;

	/**
	 * Atributo para el acceso a la tabla DESCPORCENTAJEPROMO de la BD.
	 */
	private SQLDescPorcentajePromo sqlDescPorcentajePromo;

	/**
	 * Atributo para el acceso a la tabla ESTANTE de la BD.
	 */
	private SQLEstante sqlEstante;

	/**
	 * Atributo para el acceso a la tabla FACTURA de la BD.
	 */
	private SQLFactura sqlFactura;

	/**
	 * Atributo para el acceso a la tabla LLEGADAPEDIDO de la BD.
	 */
	private SQLLegadaPedido sqlLegadaPedido;

	/**
	 * Atributo para el acceso a la tabla PAGUE1LLEVE2CONDESCPROMO de la BD.
	 */
	private SQLPague1Lleve2ConDescPromo sqlPague1Lleve2ConDescPromo;

	/**
	 * Atributo para el acceso a la tabla PAGUENUNIDADESLLEVEMPROMO de la BD.
	 */
	private SQLPagueNUnidadesLleveMPromo sqlPagueNUnidadesLleveMPromo;

	/**
	 * Atributo para el acceso a la tabla PAGUEXCANTIDADLLVEYPROMO de la BD.
	 */
	private SQLPagueXCantidadLleveYPromo sqlPagueXCantidadLleveYPromo ;

	/**
	 * Atributo para el acceso a la tabla PEDIDO de la BD.
	 */
	private SQLPedido sqlPedido;

	/**
	 * Atributo para el acceso a la tabla PERSONAJURIDICA de la BD.
	 */
	private SQLPersonaJuridica sqlPersonaJuridica;

	/**
	 * Atributo para el acceso a la tabla PRODUCTO de la BD.
	 */
	private SQLProducto sqlProducto;

	/**
	 * Atributo para el acceso a la tabla PRODUCTOPEDIDO de la BD.
	 */
	private SQLProductoPedido sqlProductoPedido;

	/**
	 * Atributo para el acceso a la tabla PRODUCTOPROMOCION de la BD.
	 */
	private SQLProductoPromocion sqlProductoPromocion;

	/**
	 * Atributo para el acceso a la tabla PRODUCTOPROVEEDOR de la BD.
	 */
	private SQLProductoProveedor sqlProductoProveedor;

	/**
	 * Atributo para el acceso a la tabla PRODUCTOSUCURSAL de la BD.
	 */
	private SQLProductoSucursal sqlProductoSucursal;

	/**
	 * Atributo para el acceso a la tabla PROMOCION de la BD.
	 */
	private SQLPromocion sqlPromocion;

	/**
	 * Atributo para el acceso a la tabla PROVEEDOR de la BD.
	 */
	private SQLProveedor sqlProveedor;	

	/**
	 * Atributo para el acceso a la tabla SUCURSAL de la BD.
	 */
	private SQLSucursal sqlSucursal;

	/**
	 * Atributo para el acceso a la tabla VENTA de la BD.
	 */
	private SQLVenta sqlVenta;

	/**
	 * Atributo para el acceso a la tabla VENTAPRODUCTO de la BD.
	 */
	private SQLVentaProducto sqlVentaProducto;
	
	/**
	 * Atributo para el acceso a la tabla PRODUCTOSBODEGA de la BD.
	 */
	private SQLProductosBodega sqlProductosBodega;
	
	/**
	 * Atributo para el acceso a la tabla PRODUCTOSESTANTE de la BD.
	 */
	private SQLProductosEstante sqlProductosEstante;


	private SQLPaqueteDeProductosPromo sqlPaqueteDeProductosPromo;

	private SQLElementos sqlElementos;
	
	//------------------------------------------------------------------
	//--------------M�todos del MANEJADOR DE PERSISTENCIA---------------
	//------------------------------------------------------------------
	/**
	 * Constructor privado con valores por defecto - Patr�n SINGLETON
	 */
	private PersistenciaSuperAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("SuperAndes");		
		crearClasesSQL ();		
	}

	/**
	 * Retorna la cadena de caracteres con el nombre del secuenciador de parranderos.
	 * @return La cadena de caracteres con el nombre del secuenciador de parranderos.
	 */
	public static String darSeqSuperAndes ()	{ return "SuperAndes_sequence";	}
	
	public static String darTablaBodega()	{ return "BODEGA"; }

	public static String darTablaCategoria()	{ return "CATEGORIA"; }

	public static String darTablaCliente()	{ return "CLIENTE"; }

	public static String darTablaDescPorcentajePromo()	{ return "DESCPORCENTAJEPROMO"; }

	public static String darTablaEstante()	{ return "ESTANTE"; }

	public static String darTablaFactura()	{ return "FACTURA"; }

	public static String darTablaLlegadaPedido()	{ return "LLEGADAPEDIDO"; }

	public static String darTablaPague1Lleve2ConDescPromo()	{ return "PAGUE1LLEVE2CONDESCPROMO"; }

	public static String darTablaPagueNUnidadesLleveMPromo()	{ return "PAGUENUNIDADESLLEVEMPROMO"; }

	public static String darTablaPagueXCantidadLleveYPromo()	{ return "PAGUEXCANTIDADLLEVEYPROMO"; }
	
	public static String darTablaPaqueteDeProductosPromo() {return "PAQUETEDEPRODUCTOSPROMO";}

	public static String darTablaPedido()	{ return "PEDIDO"; }

	public static String darTablaPersonaJuridica()	{ return "PERSONAJURIDICA"; }

	public static String darTablaProducto()	{ return "PRODUCTO"; }

	public static String darTablaProductoPedido()	{ return "PRODUCTOPEDIDO"; }	

	public static String darTablaProductoPromocion()	{ return "PRODUCTOPROMOCION"; }

	public static String darTablaProductoProveedor()	{ return "PROVEEDORPRODUCTO"; }

	public static String darTablaProductoSucursal()	{ return "PRODUCTOSUCURSAL"; }

	public static String darTablaPromocion()	{ return "PROMOCION"; }

	public static String darTablaProveedor()	{ return "PROVEEDOR"; }

	public static String darTablaRestriccionBodega()	{ return "RESTRICCIONBODEGA"; }

	public static String darTablaRestriccionEstante()	{ return "RESTRICCIONESTANTE"; }

	public static String darTablaSucursal()	{ return "SUCURSAL"; }

	public static String darTablaVenta()	{ return "VENTA"; }

	public static String darTablaVentaProducto()	{ return "VENTAPRODUCTO"; }
	
	public static String darTablaProductosBodega() { return "PRODUCTOSBODEGA";}
	
	public static String darTablaProductosEstante() { return "PRODUCTOSESTANTE";}


	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patr�n SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaSuperAndes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);

		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el �nico objeto PersistenciaParranderos existente - Patr�n SINGLETON
	 */
	public static PersistenciaSuperAndes getInstance ()
	{
		if (instance == null)		
			instance = new PersistenciaSuperAndes ();
		
		return instance;
	}

	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el �nico objeto PersistenciaParranderos existente - Patr�n SINGLETON
	 */
	public static PersistenciaSuperAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)		
			instance = new PersistenciaSuperAndes (tableConfig);
		
		return instance;
	}

	/**
	 * Cierra la conexi�n con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}

	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)		
			resp.add (nom.getAsString ());		

		return resp;
	}

	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlBodega = new SQLBodega(this);	sqlCategoria = new SQLCategoria(this);	sqlCategoriaProducto = new SQLCategoriaProducto(this);
		sqlCliente = new SQLCliente(this);	sqlDescPorcentajePromo = new SQLDescPorcentajePromo(this); sqlEstante = new SQLEstante(this);
		sqlFactura = new SQLFactura(this);	sqlLegadaPedido = new SQLLegadaPedido(this);	sqlPague1Lleve2ConDescPromo = new SQLPague1Lleve2ConDescPromo(this);
		sqlPagueNUnidadesLleveMPromo = new SQLPagueNUnidadesLleveMPromo(this);	sqlProductoPromocion = new SQLProductoPromocion(this);
		sqlSucursal=new SQLSucursal(this);	sqlPagueXCantidadLleveYPromo = new SQLPagueXCantidadLleveYPromo(this);	sqlPedido = new SQLPedido(this);
		sqlPersonaJuridica = new SQLPersonaJuridica(this);	sqlProducto = new SQLProducto(this);	sqlProductoPedido = new SQLProductoPedido(this);
		sqlProductoPromocion = new SQLProductoPromocion(this);	sqlProductoProveedor = new SQLProductoProveedor(this);	sqlProductoSucursal = new SQLProductoSucursal(this);
		sqlPromocion = new SQLPromocion(this);	sqlProveedor = new SQLProveedor(this); sqlSucursal = new SQLSucursal(this);	sqlVenta = new SQLVenta(this);
		sqlVentaProducto = new SQLVentaProducto(this); sqlPaqueteDeProductosPromo = new SQLPaqueteDeProductosPromo(this); sqlUtil = new SQLUtil(this);
		sqlElementos = new SQLElementos(this); sqlProductosBodega = new SQLProductosBodega(this); sqlProductosEstante = new SQLProductosEstante(this);
		
	}	
	
	/* ****************************************************************
	 * 			Requerimientos funcionales de modificacion
	 *****************************************************************/
	public Proveedor registrarProveedor(String nit, String nombre)	{

		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();

		try {

			tx.begin();
			long tuplasInsertadas = sqlProveedor.adicionarProveedor(pm,nit,nombre);
			tx.commit();

			log.trace ("Inserci�n de proveedor: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Proveedor(nit, nombre);

		} catch (Exception e) {

			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Producto registrarProductos(String codigosBarras, String nombres, String presentaciones, String marcas, int cantidades, String unidadesMedida, String especificacionesEmpacado, String categorias)
	{
		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();

		try {

			tx.begin();
			long tuplasInsertadas = sqlProducto.adicionarProducto(pm, codigosBarras, nombres, presentaciones, marcas, cantidades, unidadesMedida, especificacionesEmpacado, categorias);
			tx.commit();

			log.trace ("Inserci�n de producto: " + nombres + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Producto(codigosBarras, nombres , presentaciones, marcas, cantidades,unidadesMedida, especificacionesEmpacado, categorias);

		} catch (Exception e) {

			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

			return null;
		}		
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Cliente registrarCliente(String tipodocumento, String numDocumento, String nombre, String correo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();

		try {

			tx.begin();
			long tuplasInsertadas = sqlCliente.adicionarCliente(pm, tipodocumento, numDocumento, nombre, correo);
			tx.commit();

			log.trace ("Inserci�n de cliente: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Cliente(tipodocumento, numDocumento, nombre, correo);

		} catch (Exception e) {

			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

			return null;
		}	
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	public PersonaJuridica registrarPersonaJuridica(String documento, String numDocumento, String direccion) {


		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();

		try {

			tx.begin();
			long tuplasInsertadas = sqlPersonaJuridica.adicionarPersonaJuridica(pm, documento, numDocumento,direccion);
			tx.commit();

			log.trace ("Inserci�n de personaJuridica: " + numDocumento + ": " + tuplasInsertadas + " tuplas insertadas");

			return new PersonaJuridica(documento, numDocumento, direccion);

		} catch (Exception e) {

			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

			return null;
		}	

		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}

	}


	public Sucursal registrarSucursal(String nombre, String segmentacion, String tamanio, String ciudad, String direccion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try 
		{
			tx.begin();
			long idSucursal= nextval();
			long tuplasInsertadas = sqlSucursal.insertarSucursal(pm, idSucursal, nombre, segmentacion, tamanio, ciudad, direccion);
			tx.commit();
			log.trace ("Inserci�n de sucursal: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Sucursal(idSucursal, nombre, segmentacion, direccion, tamanio, ciudad);

		} 
		catch (Exception e) 
		{

			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}	
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}

	}

	public Bodega registrarBodega(long idSucursal, long idCategoria, Double volumenMaximo, Double pesoMaximo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try 
		{
			tx.begin();
			long id= nextval();	
			long tuplasInsertadas = sqlBodega.adicionarBodega(pm, id, idSucursal, idCategoria, volumenMaximo, pesoMaximo);
			tx.commit();
			log.trace ("Inserci�n de estante: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Bodega( id, idSucursal, idCategoria, volumenMaximo, pesoMaximo);
		} 
		catch (Exception e) 
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Estante registrarEstante(long idSucursal, long idCategoria, 
			Double volumenMaximo, Double pesoMaximo, Integer nivelDeAbastecimiento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try 
		{
			tx.begin();
			long id= nextval();	
			long tuplasInsertadas = sqlEstante.adicionarEstante(pm, id, idSucursal,idCategoria, volumenMaximo, pesoMaximo, nivelDeAbastecimiento);
			tx.commit();
			log.trace ("Inserci�n de estante: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Estante(id, idSucursal,idCategoria, volumenMaximo, pesoMaximo, nivelDeAbastecimiento);
		} 
		catch (Exception e) 
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}




	public PagueNUnidadesLleveMPromo registrarPromocionPagueNLleveM(String codigoProducto, Timestamp fechaVencimientoPromocion, int compraUnidades, int llevaUnidades)
	{

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try 
		{
			tx.begin();
			String codigoPromo= nextval()+"";
			long tuplasInsertadas=sqlPromocion.adicionarPromocion(pm, codigoPromo, 1, fechaVencimientoPromocion);
			tuplasInsertadas+=sqlPagueNUnidadesLleveMPromo.adicionarPromocion(pm, codigoPromo, compraUnidades, llevaUnidades);		
			//tuplasInsertadas+=sqlProductoPromocion.adicionarPromocion(pm,codigoProducto , codigoPromo);
			tx.commit();
			log.trace ("Inserci�n de promocion: " + codigoPromo + ": " + tuplasInsertadas + " tuplas insertadas");
			return new PagueNUnidadesLleveMPromo(codigoPromo, compraUnidades, llevaUnidades);
		} 
		catch (Exception e) 
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	public DescPorcentajePromo registrarPromocionDescPorcentaje(String codigoProducto, Timestamp fechaVencimientoPromocion, double porcentaje)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try 
		{
			tx.begin();
			String codigoPromo= nextval()+"";
			long tuplasInsertadas=sqlPromocion.adicionarPromocion(pm, codigoPromo, 1, fechaVencimientoPromocion);
			tuplasInsertadas+=sqlDescPorcentajePromo.adicionarPromocion(pm, codigoPromo, porcentaje);		
			//tuplasInsertadas+=sqlProductoPromocion.adicionarPromocion(pm,codigoProducto , codigoPromo);
			tx.commit();
			log.trace ("Inserci�n de promocion: " + codigoPromo + ": " + tuplasInsertadas + " tuplas insertadas");
			return new DescPorcentajePromo(codigoPromo, porcentaje);
		} 
		catch (Exception e) 
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	public PagueXCantidadLleveYPromo registrarPromocionPagueXLleveY(String codigoProducto, Timestamp fechaVencimientoPromocion, int cantidadPaga, int cantidadLleva)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try 
		{
			tx.begin();
			String codigoPromo= nextval()+"";
			long tuplasInsertadas=sqlPromocion.adicionarPromocion(pm, codigoPromo, 1, fechaVencimientoPromocion);
			tuplasInsertadas+=sqlPagueXCantidadLleveYPromo.adicionarPromocion(pm, codigoPromo, cantidadPaga, cantidadLleva);		
			//tuplasInsertadas+=sqlProductoPromocion.adicionarPromocion(pm,codigoProducto , codigoPromo);
			tx.commit();
			log.trace ("Inserci�n de promocion: " + codigoPromo + ": " + tuplasInsertadas + " tuplas insertadas");
			return new PagueXCantidadLleveYPromo(codigoPromo, cantidadPaga, cantidadLleva);
		} 
		catch (Exception e) 
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	public Pague1Lleve2ConDescPromo registrarPromocionPague1Lleve2doDesc(String codigoProducto, Timestamp fechaVencimientoPromocion, double porcentaje)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try 
		{
			tx.begin();
			String codigoPromo= nextval()+"";
			long tuplasInsertadas=sqlPromocion.adicionarPromocion(pm, codigoPromo, 1, fechaVencimientoPromocion);
			tuplasInsertadas+=sqlPague1Lleve2ConDescPromo.adicionarPromocion(pm, codigoPromo, porcentaje);		
			//tuplasInsertadas+=sqlProductoPromocion.adicionarPromocion(pm,codigoProducto , codigoPromo);
			tx.commit();
			log.trace ("Inserci�n de promocion: " + codigoPromo + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Pague1Lleve2ConDescPromo(codigoPromo, porcentaje);
		} 
		catch (Exception e) 
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	public Promocion registrarPromocionPaqueteProductos( Timestamp fechaVencimientoPromocion, String codigoProducto, int precioConjunto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try 
		{
			tx.begin();
			long tuplasInsertadas=sqlPromocion.adicionarPromocion(pm, codigoProducto, 5, fechaVencimientoPromocion);
			long tuplasInsertadas2 = sqlPaqueteDeProductosPromo.adicionarPaquete(pm, codigoProducto, precioConjunto);
			tx.commit();
			log.trace ("Inserci�n de promocion: " + codigoProducto + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Promocion(codigoProducto, fechaVencimientoPromocion);
		} 
		catch (Exception e) 
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long finalizarPromocionFechaActual()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try 
		{
			tx.begin();
			long tuplasEliminadas=sqlPromocion.eliminarPromocionFechaActual(pm);
			log.trace ("Eliminacion de promocion, "  + tuplasEliminadas + " tuplas eliminadas");
			tx.commit();
			return tuplasEliminadas;
		} 
		catch (Exception e) 
		{
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return 0;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Pedido registrarPedido(String idSucursal, String[] codigosProductos, String[] cantidad, String[] precios, String nitProveedor, Timestamp fechaPrevista, double precioTotal )
	{
		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();

		try {

			tx.begin();

			long codigoPedido = nextval();

			long tuplasInsertadas = sqlPedido.adicionarPedido(pm, idSucursal, codigosProductos, nitProveedor, fechaPrevista, precioTotal);

			long tuplasInsertadas2 = 0;

			for (int i = 0; i < codigosProductos.length; i++) {

				tuplasInsertadas2 += sqlProductoPedido.adicionarProductoPedido(pm, codigoPedido,codigosProductos[i],cantidad[i],precios[i]);		

			}

			tx.commit();

			log.trace ("Inserci�n de pedido a proveedor: " + nitProveedor + ": " + tuplasInsertadas + " tuplas insertadas");

			log.trace ("Inserci�n de productosPedidos: " + codigoPedido + ": " + tuplasInsertadas2 + " tuplas insertadas");


			return new Pedido(pm, idSucursal, codigosProductos, nitProveedor, fechaPrevista, precioTotal);

		} catch (Exception e) {

			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

			return null;
		}		
	}

	public LlegadaPedido registrarLlegadaPedido(long codigoPedido, long idSucursal,Timestamp fechaLlegada, int cantidadProductos, String calidadProductos, String calificacion)
	{
		{
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx=pm.currentTransaction();
			try 
			{
				tx.begin();
				long id= nextval();
				long tuplasInsertadas=sqlLegadaPedido.registrarLlegadaPedido(pm, codigoPedido, idSucursal, fechaLlegada, cantidadProductos, calidadProductos, calificacion);
				log.trace ("Inserci�n de llegada pedido: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
				tx.commit();
				return new LlegadaPedido(id, idSucursal, fechaLlegada, cantidadProductos, calidadProductos, calificacion, codigoPedido);
			} 
			catch (Exception e) 
			{
				log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
				return null;
			}
			finally
			{
				if (tx.isActive())
				{
					tx.rollback();
				}
				pm.close();
			}
		}
	}

	public Venta registrarVenta(long idSucursal, String tipodocumento, String documento, String[] codigosProductos,
			String[] cantidad, String[] precios, double precioTotal, Date fecha) {

		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();

		try {

			tx.begin();

			long numeroVenta = nextval();

			long tuplasInsertadas = sqlVenta.adicionarVenta(pm, idSucursal, numeroVenta, tipodocumento, documento, precioTotal, fecha);

			long tuplasInsertadas2 = 0;
			
			long productosRemovidosEstante = 0;
			
			long productosRemovidosBodega = 0;

			for (int i = 0; i < codigosProductos.length; i++) 
			{
				tuplasInsertadas2 += sqlVentaProducto.
						adicionarVentaProducto(pm, numeroVenta, codigosProductos[i],cantidad[i]);
			}

			tx.commit();

			log.trace ("Inserci�n de venta: " + numeroVenta + ": " + tuplasInsertadas + " tuplas insertadas");

			log.trace ("Inserci�n de ventaProducto: " + numeroVenta + ": " + tuplasInsertadas2 + " tuplas insertadas");


			return new Venta(pm, numeroVenta, tipodocumento, documento, precioTotal);

		} catch (Exception e) {

			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

			return null;
		}		

	}

	/* ****************************************************************
	 * 			Requerimientos funcionales de consulta
	 *****************************************************************/


	public List<Object[]> dineroRecolectado(Timestamp fechaInicio,Timestamp fechaFin)
	{
		log.info ("Obteniendo dinero recolectado en las sucursales entre " + fechaInicio+" y "+fechaFin);

		PersistenceManager pm = pmf.getPersistenceManager();

		List<Object[]> respuesta = sqlVenta.obtenerDineroRecolectado(pm, fechaInicio, fechaFin);

		return respuesta;
	}

	/**
	 * 
	 */
	public void promocionesMasPopulares()
	{
		log.info ("Obteniendo las 20 promociones mas populares ");
	}

	/**
	 * 
	 * @param idSucursal
	 */
	public List<Object []> indiceOcupacion(long idSucursal)
	{
		return sqlSucursal.darIndiceOcupacion(pmf.getPersistenceManager(), idSucursal);
	}

	public void productosConCiertaCaracteristica(int precioInferior, int precioSuperior, Timestamp fechaVencimientoMinima, double pesoMinimo, double pesoMaximo,
			String nitProveedor, String ciudad, long idSucursal, String tipo, String categoria, int cantidadMinimaVentas, Timestamp fechaMinCantMinVentas,
			Timestamp fechaMaxCantMinVentas)
	{

	}
	/* ****************************************************************
	 *			Requerimientos funcionales de Bono
	 *****************************************************************/
	public void comprasAProveedores()
	{

	}

	public void ventasAUsuario()
	{

	}

	/**
	 * Transacci�n para el generador de secuencia de SuperAndes
	 * Adiciona entradas al log de la aplicaci�n
	 * @return El siguiente n�mero del secuenciador de SuperAndes
	 */
	private long nextval ()
	{
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
		log.trace ("Generando secuencia: " + resp);
		return resp;
	}

	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle espec�fico del problema encontrado
	 * @param e - La excepci�n que ocurrio
	 * @return El mensaje de la excepci�n JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}



	/* ****************************************************************
	 *		M�todos adicionales
	 *****************************************************************/


	public String[] obtenerPreciosSucursal(long sucursal, String[] productos) {

		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();

		try {

			tx.begin();

			String[] precios = sqlProductoSucursal.darPrecioProductosSucursal(pm, sucursal, productos);

			tx.commit();


			return precios;

		} catch (Exception e) {

			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

			return null;
		}	
	}
	
	
	//METODO DE RETORNO
	
	public List<Object[]> darElementos(String tabla) {
		
		PersistenceManager pm = pmf.getPersistenceManager();

		List<Object[]> retorno = sqlElementos.darElementos(pm, tabla);
		
		return retorno;
		
	}

	public void registrarProductoAProveedor(String barCode, String nit, String calif, String prec) {

		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		
		try {
		tx.begin();
		
		sqlProductoProveedor.registrarProductoAProveedor(pm,barCode, nit, calif, prec);
	
		tx.commit();
		}
		catch (Exception e) {
									
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

		}
	}

	public void registrarCategoria(String nombreCategoria) {
		

		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		
		try {
			tx.begin();
			
			long id = nextval();
			
			sqlCategoria.adicionarCategoria(pm, id, nombreCategoria);
		
			tx.commit();
			}
			catch (Exception e) {
										
				log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

			}
		
	}



}
