package uniandes.isis2304.b07.superandes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import javafx.scene.control.cell.CheckBoxListCell;
import uniandes.isis2304.b07.superandes.negocio.Cliente;
import uniandes.isis2304.b07.superandes.negocio.IndiceOcupacion;
import uniandes.isis2304.b07.superandes.negocio.PersonaJuridica;
import uniandes.isis2304.b07.superandes.negocio.Producto;
import uniandes.isis2304.b07.superandes.negocio.Promocion;
import uniandes.isis2304.b07.superandes.negocio.Proveedor;
import uniandes.isis2304.b07.superandes.negocio.SuperAndes;
import uniandes.isis2304.b07.superandes.negocio.VOBodega;
import uniandes.isis2304.b07.superandes.negocio.VODescPorcentajePromo;
import uniandes.isis2304.b07.superandes.negocio.VOEstante;
import uniandes.isis2304.b07.superandes.negocio.VOLlegadaPedido;
import uniandes.isis2304.b07.superandes.negocio.VOPague1Lleve2ConDescPromo;
import uniandes.isis2304.b07.superandes.negocio.VOPagueNUnidadesLleveMPromo;
import uniandes.isis2304.b07.superandes.negocio.VOPagueXCantidadLleveY;
import uniandes.isis2304.b07.superandes.negocio.VOPedido;
import uniandes.isis2304.b07.superandes.negocio.VOSucursal;
import uniandes.isis2304.b07.superandes.negocio.Venta;
import uniandes.isis2304.b07.superandes.persistencia.PersistenciaSuperAndes;
/**
 * Clase principal de la interfaz
 * @author Santiago Carrero y Nicolas Hernandez, Tomado de esquema paranderos jdo, autor German Bravo
 */
@SuppressWarnings("serial")
public class InterfazSuperAndesApp extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazSuperAndesApp.class.getName());

	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 

	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * Asociación a la clase principal del negocio.
	 */
	private SuperAndes superAndes;

	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
	/**
	 * Objeto JSON con la configuración de interfaz de la app.
	 */
	private JsonObject guiConfig;

	/**
	 * Panel de despliegue de interacción para los requerimientos
	 */
	private PanelDatos panelDatos;

	/**
	 * Menú de la aplicación
	 */
	private JMenuBar menuBar;

	private String var;

	private boolean confirmacion;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Construye la ventana principal de la aplicación. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazSuperAndesApp( )
	{
		var = new String();

		confirmacion = false;

		// Carga la configuración de la interfaz desde un archivo JSON
		guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gráfica
		configurarFrame ( );
		if (guiConfig != null) 	   
		{
			crearMenu( guiConfig.getAsJsonArray("menuBar") );
		}

		tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
		superAndes = new SuperAndes (tableConfig);

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos ( );

		setLayout (new BorderLayout());
		add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
		add( panelDatos, BorderLayout.CENTER );        
	}

	/* ****************************************************************
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/
	/**
	 * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuración deseada
	 * @param archConfig - Archivo Json que contiene la configuración
	 * @return Un objeto JSON con la configuración del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	/**
	 * Método para configurar el frame principal de la aplicación
	 */
	private void configurarFrame(  )
	{
		int alto = 0;
		int ancho = 0;
		String titulo = "";	

		if ( guiConfig == null )
		{
			log.info ( "Se aplica configuración por defecto" );			
			titulo = "SuperAndes APP Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
			titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocation (50,50);
		setResizable( true );
		setBackground( Color.WHITE );

		setTitle( titulo );
		setSize ( ancho, alto);        
	}

	/**
	 * Método para crear el menú de la aplicación con base em el objeto JSON leído
	 * Genera una barra de menú y los menús con sus respectivas opciones
	 * @param jsonMenu - Arreglo Json con los menùs deseados
	 */
	private void crearMenu(  JsonArray jsonMenu )
	{    	
		// Creación de la barra de menús
		menuBar = new JMenuBar();       
		for (JsonElement men : jsonMenu)
		{
			// Creación de cada uno de los menús
			JsonObject jom = men.getAsJsonObject(); 

			String menuTitle = jom.get("menuTitle").getAsString();        	
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu( menuTitle);

			for (JsonElement op : opciones)
			{       	
				// Creación de cada una de las opciones del menú
				JsonObject jo = op.getAsJsonObject(); 
				String lb =   jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem( lb );
				mItem.addActionListener( this );
				mItem.setActionCommand(event);

				menu.add(mItem);
			}       
			menuBar.add( menu );
		}        
		setJMenuBar ( menuBar );	
	}

	/* ****************************************************************
	 * 			Métodos administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de Parranderos
	 */
	public void mostrarLogParranderos ()
	{
		mostrarArchivo ("parranderos.log");
	}

	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}

	/**
	 * Limpia el contenido del log de parranderos
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogParranderos ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("parranderos.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de parranderos ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de parranderos
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			//			long eliminados [] = parranderos.limpiarParranderos();
			//			
			//			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			//			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			//			resultado += eliminados [0] + " Gustan eliminados\n";
			//			resultado += eliminados [1] + " Sirven eliminados\n";
			//			resultado += eliminados [2] + " Visitan eliminados\n";
			//			resultado += eliminados [3] + " Bebidas eliminadas\n";
			//			resultado += eliminados [4] + " Tipos de bebida eliminados\n";
			//			resultado += eliminados [5] + " Bebedores eliminados\n";
			//			resultado += eliminados [6] + " Bares eliminados\n";
			//			resultado += "\nLimpieza terminada";

			//			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Muestra la presentación general del proyecto
	 */
	public void mostrarPresentacionGeneral ()
	{
		mostrarArchivo ("data/00-ST-ParranderosJDO.pdf");
	}

	/**
	 * Muestra el modelo conceptual de Parranderos
	 */
	public void mostrarModeloConceptual ()
	{
		mostrarArchivo ("docs/Modelo Conceptual SuperAndes.pdf");
	}

	/**
	 * Muestra el esquema de la base de datos de Parranderos
	 */
	public void mostrarEsquemaBD ()
	{
		mostrarArchivo ("docs/Esquema BD SuperAndes.pdf");
	}

	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("docs/ScriptCreadoTablas.sql");
	}

	/**
	 * Muestra la arquitectura de referencia para Parranderos
	 */
	public void mostrarArqRef ()
	{
		mostrarArchivo ("docs/ArquitecturaReferencia.pdf");
	}

	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}

	/**
	 * Muestra la información acerca del desarrollo de esta apicación
	 */
	public void acercaDe ()
	{
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: SuperAndes Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Santiago Carrero\n";
		resultado += " * @author Nicolas Hernandez\n";
		resultado += " * Octubre de 2018\n";
		resultado += " * Esquema tomado de parranderos jdo de German Bravo de 2018\n";
		resultado += " * \n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
	}

	/* ****************************************************************
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/
	/**
	 * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
	 * @param e - La excepción recibida
	 * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
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

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
			//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
	/**
	 * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
	 * Invoca al método correspondiente según el evento recibido
	 * @param pEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
		try 
		{
			Method req = InterfazSuperAndesApp.class.getMethod ( evento );			
			req.invoke ( this );
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
	/**
	 * Este método ejecuta la aplicación, creando una nueva interfaz
	 * @param args Arreglo de argumentos que se recibe por línea de comandos
	 */
	public static void main( String[] args )
	{
		try
		{

			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
			InterfazSuperAndesApp interfaz = new InterfazSuperAndesApp( );
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}

	/* ****************************************************************
	 * 			Requerimientos funcionales de modificacion
	 *****************************************************************/
	public void registrarProveedores()
	{
		try 
		{
			String nombre = JOptionPane.showInputDialog (this, "Ingrese el nombre del proveedor", "Registrar proveedor", JOptionPane.QUESTION_MESSAGE);
			String nit = JOptionPane.showInputDialog (this, "Ingrese el NIT del proveedor", "Registrar proveedor", JOptionPane.QUESTION_MESSAGE);    		

			if (nombre != null && nit != null)
			{				

				Proveedor proveedor = superAndes.registrarProveedor(nit,nombre);

				if (proveedor == null)
				{
					throw new Exception ("No se pudo crear el proveedor" );
				}

				String resultado = "Proveedor adicionado exitosamente";			

				resultado += "\n Operaci�n terminada";

				panelDatos.actualizarInterfaz(resultado);
			}

			else
			{
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void registrarProductosAProveedor() {

		List<Object[]> proveedores = superAndes.darElementos(PersistenciaSuperAndes.darTablaProveedor()); 

		String[] options = new String[proveedores.size()];

		int i = 0;

		for (Object[] objeto : proveedores) {

			options[i] = (String) objeto[1];			

			i++;

		}

		ImageIcon icon = new ImageIcon("https://image.flaticon.com/icons/png/512/16/16075.png");

		String opcion = (String) JOptionPane.showInputDialog(this, "Elija el proveedor","Agregar productos a proveedor",JOptionPane.QUESTION_MESSAGE, icon ,options, options[0]);

		int x = 0;

		while(opcion.equals(proveedores.get(x)[1])) {

			x++;
		}

		String nit = (String) proveedores.get(x)[0];	

		String productos = JOptionPane.showInputDialog("Inserte el codigo de los productos que ofrece el proveedor separados por comas");

		String[] barCodes = productos.split(",");
		
		String calificaciones = JOptionPane.showInputDialog("Inserte las calificaciones de los productos que ofrece el proveedor separados por comas");

		String[] calif = calificaciones.split(",");
		
		String precios = JOptionPane.showInputDialog("Inserte los precios de los productos que ofrece el proveedor separados por comas");

		String[] prec = precios.split(",");
		
		
		
		for (int j = 0; j < barCodes.length; j++) {
			
			superAndes.registrarProductoAProveedor(barCodes[j], nit, calif[j], prec[j]);			
		}

	}


	public void verProductos() {

		List<Object[]> productos = superAndes.darElementos(PersistenciaSuperAndes.darTablaProducto());
		
		String[] options = new String[productos.size()];

		int i = 0;
		
		for (Object[] objects : productos) {

			options[i] = objects[0]+" - "+objects[1]+"\n";
			
			i++;
		}
		
		ImageIcon icon = new ImageIcon("https://image.flaticon.com/icons/png/512/16/16075.png");

		String opcion = (String) JOptionPane.showInputDialog(this, "Productos","Ver productos",JOptionPane.QUESTION_MESSAGE, icon ,options, options[0]);


	}



	public void registrarProductos()
	{
		try 
		{
			String codigoBarra = JOptionPane.showInputDialog (this, "Codigos de barras de los productos? Separados por comas", "Registrar producto", JOptionPane.QUESTION_MESSAGE);
			String nombre = JOptionPane.showInputDialog (this, "Nombres de los productos? Separados por comas", "Registrar producto", JOptionPane.QUESTION_MESSAGE);
			String presentacion = JOptionPane.showInputDialog (this, "Presentaciones de los productos? Separados por comas", "Registrar producto", JOptionPane.QUESTION_MESSAGE);
			String marca = JOptionPane.showInputDialog (this, "Marcas de los productos? Separados por comas", "Registrar producto", JOptionPane.QUESTION_MESSAGE);
			String cantidad = JOptionPane.showInputDialog (this, "Cantidades de los productos? Separados por comas", "Registrar producto", JOptionPane.QUESTION_MESSAGE);
			String unidadMedida = JOptionPane.showInputDialog (this, "Unidades de medida de los productos? Separados por comas", "Registrar producto", JOptionPane.QUESTION_MESSAGE);
			String especificacionEmpecado = JOptionPane.showInputDialog (this, "Especificaciones empacado de los productos? Separados por comas", "Registrar producto", JOptionPane.QUESTION_MESSAGE);

			String[] codigosBarras=codigoBarra.split(",");
			String[] nombres=nombre.split(",");
			String[] presentaciones=presentacion.split(",");
			String[] marcas = marca.split(",");
			String[] cantidadesS=cantidad.split(",");

			if(confirmacion)
				var = codigosBarras[0];	

			int[] cantidades=new int[cantidadesS.length];
			for (int i = 0; i < cantidades.length; i++) {
				cantidades[i]=Integer.parseInt(cantidadesS[i]);
			}
			String[] unidadesMedida=unidadMedida.split(",");
			String[] especificacionesEmpacado=especificacionEmpecado.split(",");


			if (codigosBarras != null && nombres != null && presentaciones != null && marcas != null && cantidades != null && unidadesMedida != null && especificacionesEmpacado != null)
			{
				List<Producto> productos =superAndes.registrarProductos(codigosBarras, nombres, presentaciones,marcas, cantidades, unidadesMedida, especificacionesEmpacado);
				if (productos == null)
				{
					throw new Exception ("No se pudo crear productos  " );
				}
				String resultado = "En registrarProveedores\n\n";
				for (Producto producto : productos) {
					resultado += "Producto adicionado exitosamente: " + producto;

				}
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void registrarClientes()
	{
		String[] options1 = {"1. Persona natural","2. Persona juridica"};

		ImageIcon icon = new ImageIcon("https://image.flaticon.com/icons/png/512/16/16075.png");

		String opcion = (String) JOptionPane.showInputDialog(this, "Elija el tipo de cliente","Agregar cliente",JOptionPane.QUESTION_MESSAGE, icon ,options1, options1[0]);

		if(opcion == options1[0]) {   

			try 
			{

				String[] options2 = {"TI", "Cedula", "Pasaporte"};

				String documento = (String) JOptionPane.showInputDialog(this, "Elija el tipo de documento","Agregar cliente",JOptionPane.QUESTION_MESSAGE, icon ,options2, options2[1]);

				String numDocumento = JOptionPane.showInputDialog (this, "Inserte el numero de documento", "Agregar cliente", JOptionPane.QUESTION_MESSAGE);

				String nombre = JOptionPane.showInputDialog (this, "Inserte el nombre", "Agregar cliente", JOptionPane.QUESTION_MESSAGE);

				String correo = JOptionPane.showInputDialog (this, "Inserte el correo", "Agregar cliente", JOptionPane.QUESTION_MESSAGE);


				if(documento != null && numDocumento != null && nombre != null && correo != null){

					Cliente cliente = superAndes.registrarCliente(documento, numDocumento, nombre, correo);

					if (cliente == null)
					{
						throw new Exception ("No se pudo crear el cliente: " + nombre);
					}	

					String resultado = "En registrarCliente\n\n";
					resultado += "Cliente anadido exitosamente: " + cliente;
					resultado += "\n Operacion terminada";
					panelDatos.actualizarInterfaz(resultado);
				}

				else
				{
					panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
				}

			}

			catch (Exception e){

				//				e.printStackTrace();
				String resultado = generarMensajeError(e);
				panelDatos.actualizarInterfaz(resultado);

			}
		}

		else if(opcion == options1[1]) {    

			try 
			{

				String documento = "NIT";

				String numDocumento = JOptionPane.showInputDialog (this, "Inserte el NIT", "Agregar cliente", JOptionPane.QUESTION_MESSAGE);

				String nombre = JOptionPane.showInputDialog (this, "Inserte el nombre de la empresa", "Agregar cliente", JOptionPane.QUESTION_MESSAGE);

				String correo = JOptionPane.showInputDialog (this, "Inserte el correo", "Agregar cliente", JOptionPane.QUESTION_MESSAGE);

				String direccion = JOptionPane.showInputDialog (this, "Inserte la direccion", "Agregar cliente", JOptionPane.QUESTION_MESSAGE);

				if(documento != null && numDocumento != null && nombre != null && correo != null){

					Cliente cliente = superAndes.registrarCliente(documento, numDocumento, nombre, correo);

					PersonaJuridica cl = superAndes.registrarPersonaJuridica(documento, numDocumento, direccion);

					if (cliente == null)
					{
						throw new Exception ("No se pudo crear el cliente: " + nombre);
					}	

					String resultado = "En registrarCliente\n\n";
					resultado += "Cliente a�adido exitosamente: " + cliente;
					resultado += "\n Operacion terminada";
					panelDatos.actualizarInterfaz(resultado);

				}



			}

			catch (Exception e){

			}
		}
	}


	public void registrarSucursal()
	{
		try 
		{
			String nombre = JOptionPane.showInputDialog (this, "Nombre de la sucursal?", "Registrar sucursal", JOptionPane.QUESTION_MESSAGE);
			String segmentacion = JOptionPane.showInputDialog (this, "Segmentacion de la sucursal?", "Registrar sucursal", JOptionPane.QUESTION_MESSAGE);
			String tamanio = JOptionPane.showInputDialog (this, "Tamano de la sucursal?", "Registrar sucursal", JOptionPane.QUESTION_MESSAGE);
			String ciudad = JOptionPane.showInputDialog (this, "Ciudad de la sucursal?", "Registrar sucursal", JOptionPane.QUESTION_MESSAGE);
			String direccion = JOptionPane.showInputDialog (this, "Direccion de la sucursal?", "Registrar sucursal", JOptionPane.QUESTION_MESSAGE);


			if (nombre != null && segmentacion != null && tamanio != null && ciudad != null && direccion != null)
			{
				VOSucursal sucursal =superAndes.registrarSucursal(nombre, segmentacion, tamanio, ciudad, direccion);
				if (sucursal == null)
				{
					throw new Exception ("No se pudo crear una sucursal nombre: " + nombre);
				}
				String resultado = "En registrarSucursal\n\n";
				resultado += "Sucursal adicionada exitosamente: " + sucursal;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}

	}

	public void registrarBodega()
	{
		//TODO Hacer metodo.
	}

	public void registrarEstante()
	{
		try 
		{
			String nombre = JOptionPane.showInputDialog (this, "Nombre del estante?", "Registrar estante", JOptionPane.QUESTION_MESSAGE);

			long idSucursal = Long.parseLong(JOptionPane.showInputDialog (this, "Id de la sucursal?", "Registrar estante", JOptionPane.QUESTION_MESSAGE));
			double capacidadVolumen = Double.parseDouble(JOptionPane.showInputDialog (this, "Capacidad actual en litros del estante?", "Registrar estante", JOptionPane.QUESTION_MESSAGE));
			double capacidadTotalVolumen = Double.parseDouble(JOptionPane.showInputDialog (this, "Capacidad maxima en litros del estante?", "Registrar estante", JOptionPane.QUESTION_MESSAGE));
			double capacidadPeso = Double.parseDouble(JOptionPane.showInputDialog (this, "Capacidad actual en kg del estante?", "Registrar estante", JOptionPane.QUESTION_MESSAGE));
			double capacidadTotalPeso = Double.parseDouble(JOptionPane.showInputDialog (this, "Capacidad maxima en kg del estante?", "Registrar estante", JOptionPane.QUESTION_MESSAGE));


			if (idSucursal != 0 && capacidadVolumen != 0 && capacidadTotalVolumen != 0 && capacidadPeso != 0 && capacidadTotalPeso != 0)
			{
				VOEstante estante =superAndes.registrarEstante(idSucursal, nombre, capacidadVolumen, capacidadTotalVolumen, capacidadPeso, capacidadTotalPeso);
				if (estante == null)
				{
					throw new Exception ("No se pudo registrar estante en sucursal: " + idSucursal);
				}
				String resultado = "En registrarEstante\n\n";
				resultado += "Estante adicionado exitosamente: " + estante;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void registrarPromocion()
	{
		try 
		{
			String codigoProducto = "";

			String fecha = JOptionPane.showInputDialog (this, "Fecha de vencimiento de la promocion (dd/mm/yyyy)", "Registrar promocion", JOptionPane.QUESTION_MESSAGE);
			SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
			Date d = dateformat.parse(fecha);
			Timestamp fechaVencimientoPromocion = new Timestamp(d.getTime());
			String tipo = JOptionPane.showInputDialog (this, "Tipo de promocion:"
					+ "\n 1. Pague N unidades lleve M unidades"
					+ "\n 2. Descuento de porcentaje"
					+ "\n 3. Pague X cantidad lleve Y cantidad"
					+ "\n 4. Pague 1 lleve el 2do con descuento de porcentaje"
					+ "\n 5. Paquete de productos (cree antes el producto en conjunto)", "Registrar promocion", JOptionPane.QUESTION_MESSAGE);


			if(!tipo.equals("5")){			
				codigoProducto = JOptionPane.showInputDialog (this, "Codigo del producto al cual se le va a aplicar la promocion", "Registrar promocion", JOptionPane.QUESTION_MESSAGE);
			}


			switch (tipo) {

			//Pague N lleve M
			case "1":
				int pagaUnid = Integer.parseInt(JOptionPane.showInputDialog (this, "Numero de unidades que debe pagar el cliente?", "Registrar promocion", JOptionPane.QUESTION_MESSAGE));
				int llevaUnid = Integer.parseInt(JOptionPane.showInputDialog (this, "Numero de unidades que lleva el cliente?", "Registrar promocion", JOptionPane.QUESTION_MESSAGE));
				if(pagaUnid != 0 && llevaUnid != 0)
				{
					VOPagueNUnidadesLleveMPromo promocion = superAndes.registrarPromocionPagueNLleveM(codigoProducto, fechaVencimientoPromocion, pagaUnid, llevaUnid);
					if (promocion == null)
					{
						throw new Exception ("No se pudo registrar promocion en producto: " + codigoProducto);
					}
					String resultado = "En registrarPromocion\n\n";
					resultado += "Promocion registrada exitosamente: " + promocion;
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
				}
				break;
				//Desc porcentaje
			case "2":
				double porcentaje = Double.parseDouble(JOptionPane.showInputDialog (this, "Porcentaje de descuento", "Registrar promocion", JOptionPane.QUESTION_MESSAGE));
				if(porcentaje != 0)
				{
					VODescPorcentajePromo promocion = superAndes.registrarPromocionDescPorcentaje(codigoProducto, fechaVencimientoPromocion, porcentaje);
					if (promocion == null)
					{
						throw new Exception ("No se pudo registrar promocion en producto: " + codigoProducto);
					}
					String resultado = "En registrarPromocion\n\n";
					resultado += "Promocion registrada exitosamente: " + promocion;
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
				}
				break;
				//Pague X lleve Y
			case "3":
				int pagaCant = Integer.parseInt(JOptionPane.showInputDialog (this, "Cantidad que debe pagar el cliente?", "Registrar promocion", JOptionPane.QUESTION_MESSAGE));
				int llevaCant = Integer.parseInt(JOptionPane.showInputDialog (this, "Cantidad que lleva el cliente?", "Registrar promocion", JOptionPane.QUESTION_MESSAGE));
				if(pagaCant != 0 && llevaCant != 0)
				{
					VOPagueXCantidadLleveY promocion = superAndes.registrarPromocionPagueXLleveY(codigoProducto, fechaVencimientoPromocion, pagaCant, llevaCant);
					if (promocion == null)
					{
						throw new Exception ("No se pudo registrar promocion en producto: " + codigoProducto);
					}
					String resultado = "En registrarPromocion\n\n";
					resultado += "Promocion registrada exitosamente: " + promocion;
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
				}
				break;
				//Pague 2do con desc
			case "4":
				double porcentajeSegUnid = Double.parseDouble(JOptionPane.showInputDialog (this, "Porcentaje de descuento", "Registrar promocion", JOptionPane.QUESTION_MESSAGE));
				if(porcentajeSegUnid != 0)
				{
					VOPague1Lleve2ConDescPromo promocion = superAndes.registrarPromocionPague1Lleve2doDesc(codigoProducto, fechaVencimientoPromocion, porcentajeSegUnid);
					if (promocion == null)
					{
						throw new Exception ("No se pudo registrar promocion en producto: " + codigoProducto);
					}
					String resultado = "En registrarPromocion\n\n";
					resultado += "Promocion registrada exitosamente: " + promocion;
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
				}
				break;
				//Paguete descuentos


			case "5":

				confirmacion = true;

				JOptionPane.showMessageDialog(null, "Creacion del producto en conjunto");

				registrarProductos();

				String codigoProductoPaquete = var;				


				int precioConjunto = Integer.parseInt(JOptionPane.showInputDialog (this, "Precio en conjunto", "Registrar promocion", JOptionPane.QUESTION_MESSAGE));	


				if(precioConjunto != 0 && codigoProductoPaquete != null)
				{
					Promocion promocion = superAndes.registrarPromocionPaqueteProductos(fechaVencimientoPromocion, codigoProductoPaquete, precioConjunto);

					if (promocion == null)
					{
						throw new Exception ("No se pudo registrar promocion en producto: " + codigoProducto);
					}
					String resultado = "En registrarPromocion\n\n";
					resultado += "Promocion registrada exitosamente: " + promocion;
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
				}
				break;	


			default:
				panelDatos.actualizarInterfaz("Por favor selecciona un valor del 1 al 5 sin ningun otro caracter. Gracias"
						+ "\nOperación cancelada por el usuario");

				break;
			}


		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void finalizarPromocion()
	{
		try 
		{
			String fechaS = JOptionPane.showInputDialog (this, "Fecha actual o de las cuales se quieren acabar las promociones (dd/mm/yyyy)", "Finalizar promocion", JOptionPane.QUESTION_MESSAGE);
			SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
			Date d = dateformat.parse(fechaS);
			Timestamp fecha = new Timestamp(d.getTime());



			if (fecha != null)
			{
				long eliminados =superAndes.finalizarPromocion(fecha);
				String resultado = "En finalizarPromocion\n\n";
				resultado += "Se eliminaron exitosamente: " + eliminados;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}

		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void registrarPedido()
	{
		try 
		{

			String nitProveedor = JOptionPane.showInputDialog (this, "Nit del proveedor?", "Registrar pedido", JOptionPane.QUESTION_MESSAGE);
			String idSucursal = JOptionPane.showInputDialog (this, "Id de la sucursal?", "Registrar pedido", JOptionPane.QUESTION_MESSAGE);



			String fecha = JOptionPane.showInputDialog (this, "Fecha de llegada del pedido (dd/mm/yyyy)", "Registrar pedido", JOptionPane.QUESTION_MESSAGE);
			SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
			Date d = dateformat.parse(fecha);
			Timestamp fechaPrevista = new Timestamp(d.getTime());					


			String[] codigosProductos = (JOptionPane.showInputDialog(this,"Ingrese los codigos de los productos a pedir separados por comas", "Registrar pedido", JOptionPane.QUESTION_MESSAGE)).split(",");
			String[] cantidad = (JOptionPane.showInputDialog(this,"Ingrese la cantidad de productos a pedir separados por comas", "Registrar pedido", JOptionPane.QUESTION_MESSAGE)).split(",");
			String[] precios = (JOptionPane.showInputDialog(this,"Ingrese los precios del total de la cantidad de cada producto, separados por comas", "Registrar pedido", JOptionPane.QUESTION_MESSAGE)).split(",");

			double precioTotal = 0;

			for (int i = 0; i < precios.length; i++) {

				precioTotal += Double.parseDouble(precios[i]);
			}

			if (precioTotal != 0 && nitProveedor != null && fechaPrevista != null && codigosProductos != null)
			{
				VOPedido pedido =superAndes.registrarPedido(idSucursal, codigosProductos, cantidad, precios, nitProveedor, fechaPrevista, precioTotal);
				if (pedido == null)
				{
					throw new Exception ("No se pudo registrar pedido a proveedor: " + nitProveedor);
				}
				String resultado = "En registrarPedido\n\n";
				resultado += "Pedido adicionado exitosamente: " + pedido;
				resultado += "\n Operacion terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void registrarLlegadaPedido()
	{
		try 
		{
			long codigoPedido = Long.parseLong(JOptionPane.showInputDialog (this, "Codigo del pedido", "Registrar llegada pedido", JOptionPane.QUESTION_MESSAGE));
			long idSucursal = Long.parseLong(JOptionPane.showInputDialog (this, "Id de la sucursal a la cual llega", "Registrar llegada pedido", JOptionPane.QUESTION_MESSAGE));
			long fecha = Long.parseLong(JOptionPane.showInputDialog (this, "Fecha  de llegada", "Registrar llegada pedido", JOptionPane.QUESTION_MESSAGE));
			Timestamp fechaLlegada = new Timestamp(fecha);
			int cantidadProductos = Integer.parseInt(JOptionPane.showInputDialog (this, "Cantidad productos que llegaron?", "Registrar llegada pedido", JOptionPane.QUESTION_MESSAGE));
			String calidadProductos = JOptionPane.showInputDialog (this, "Calidad de los productos que llegaron", "Registrar llegada pedido", JOptionPane.QUESTION_MESSAGE);
			String calificacion = JOptionPane.showInputDialog (this, "Calificacion", "Registrar llegada pedido", JOptionPane.QUESTION_MESSAGE);



			if (codigoPedido != 0 && idSucursal != 0 && cantidadProductos != 0 && fechaLlegada != null && calidadProductos != null && calificacion != null)
			{
				VOLlegadaPedido llegadaPedido =superAndes.registrarLlegadaPedido(codigoPedido, idSucursal, fechaLlegada, cantidadProductos, calidadProductos, calificacion);
				if (llegadaPedido == null)
				{
					throw new Exception ("No se pudo registrar llegada pedido pedido del pedido: " + codigoPedido);
				}
				String resultado = "En registrarLlegadaPedido\n\n";
				resultado += "Estante adicionado exitosamente: " + llegadaPedido;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void registrarVenta()
	{
		try{

			String sucursal = JOptionPane.showInputDialog (this, "Digite el id de la sucursal", "Registrar venta", JOptionPane.QUESTION_MESSAGE);
			java.util.Date fecha = new Date();			
			String[] options2 = {"TI", "Cedula", "Pasaporte","NIT"};
			ImageIcon icon = new ImageIcon("https://image.flaticon.com/icons/png/512/16/16075.png");
			String tipodocumento = (String) JOptionPane.showInputDialog(this, "Elija el tipo de documento","Registrar venta",JOptionPane.QUESTION_MESSAGE, icon ,options2, options2[1]);
			String documento = JOptionPane.showInputDialog (this, "Digite el numero de documento", "Registrar venta", JOptionPane.QUESTION_MESSAGE);
			String[] codigosProductos = (JOptionPane.showInputDialog(this,"Ingrese los codigos de los productos a pedir separados por comas", "Registrar pedido", JOptionPane.QUESTION_MESSAGE)).split(",");
			String[] cantidad = (JOptionPane.showInputDialog(this,"Ingrese la cantidad de productos a pedir separados por comas", "Registrar pedido", JOptionPane.QUESTION_MESSAGE)).split(",");

			String[] precios = superAndes.obtenerPreciosSucursal(sucursal, codigosProductos);

			double precioTotal = 0;

			for (int i = 0; i < precios.length; i++) {

				precioTotal += (Double.parseDouble(precios[i]) * (Double.parseDouble(cantidad[i])));

			}

			if (precioTotal != 0 && sucursal != null && tipodocumento != null && documento != null && codigosProductos != null && cantidad != null && precios != null)
			{
				Venta venta = superAndes.registrarVenta(sucursal,tipodocumento, documento, codigosProductos, cantidad, precios, precioTotal, fecha);

				if (venta == null)
				{
					throw new Exception ("No se pudo registrar la venta en la sucursal: "+sucursal);
				}

				String resultado = "En registrarVenta\n\n";
				resultado += "Venta adicionada exitosamente: " + venta;
				resultado += "\n Operacion terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operacion cancelada por el usuario");
			}
		} 

		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}


	}
	/* ****************************************************************
	 * 			Requerimientos funcionales de consulta
	 *****************************************************************/
	public void dineroRecolectado()
	{
		try 
		{
			long fechaI = Long.parseLong(JOptionPane.showInputDialog (this, "Fecha  de inicio", "Dinero recolectado por ventas", JOptionPane.QUESTION_MESSAGE));
			Timestamp fechaInicio = new Timestamp(fechaI);
			long fechaF = Long.parseLong(JOptionPane.showInputDialog (this, "Fecha  de fin", "Dinero recolectado por ventas", JOptionPane.QUESTION_MESSAGE));
			Timestamp fechaFin= new Timestamp(fechaF);


			if (fechaInicio != null && fechaFin != null)
			{
				List<Object[]> dinero = superAndes.dineroRecolectado(fechaInicio, fechaFin);
				String resultado = "En dineroRecolectado\n\n";
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void promocionesMasPopulares()
	{
		System.out.println("Hola");
	}

	public void indiceOcupacion()
	{
		try 

		{
			long sucursal = Long.parseLong(JOptionPane.showInputDialog (this, "Id de la sucursal", "Indice de ocupacion sucursal", JOptionPane.QUESTION_MESSAGE));

			if (sucursal != 0)
			{
				List<Object []> lista=superAndes.indiceOcupacion(sucursal);
				String resultado = "En indiceOcupacion\n";
				resultado += "\n ID_ELEMENTO | TIPO | INDICE_VOLUMEN | INDICE_PESO ";
				if(lista!=null){
					for (Object[] objeto : lista) {
						resultado += "\n "+objeto;
					}
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);

				}
				else
				{
					panelDatos.actualizarInterfaz("No se econtro informacion de la sucursal"+sucursal);
				}
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}

		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}

	}

	public void productosConCiertaCaracteristica()
	{
		try 
		{
			String caracteristica = JOptionPane.showInputDialog (this, "Seleccione la caracteristica por la cual desea filtrar los productos ", "Producto con cierta caracteristica", JOptionPane.QUESTION_MESSAGE);
			String resultado;

			switch (caracteristica) {
			case "1":
				double precioMin = Double.parseDouble(JOptionPane.showInputDialog (this, "Precio minimo ", "Producto con cierta caracteristica", JOptionPane.QUESTION_MESSAGE));
				double precioMax = Double.parseDouble(JOptionPane.showInputDialog (this, "Precio maximo ", "Producto con cierta caracteristica", JOptionPane.QUESTION_MESSAGE));

				superAndes.productosEnRangoPrecios(precioMin,precioMax);
				resultado = "En indiceOcupacion\n\n";
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);

				break;
			case "2":
				long fechaV = Long.parseLong(JOptionPane.showInputDialog (this, "Fecha  de vencimiento", "Producto con cierta caracteristica", JOptionPane.QUESTION_MESSAGE));
				Timestamp fechaVencimiento = new Timestamp(fechaV);

				superAndes.productosPorFechaVencimiento(fechaVencimiento);
				resultado = "En indiceOcupacion\n\n";
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);

				break;
			case "3":
				double pesoMin = Double.parseDouble(JOptionPane.showInputDialog (this, "Peso minimo ", "Producto con cierta caracteristica", JOptionPane.QUESTION_MESSAGE));
				double pesoMax = Double.parseDouble(JOptionPane.showInputDialog (this, "Peso maximo ", "Producto con cierta caracteristica", JOptionPane.QUESTION_MESSAGE));

				superAndes.productosEnRangoPesos(pesoMin,pesoMax);
				resultado = "En indiceOcupacion\n\n";
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);

				break;
			case "4":
				double volumenMin = Double.parseDouble(JOptionPane.showInputDialog (this, "Volumen minimo ", "Producto con cierta caracteristica", JOptionPane.QUESTION_MESSAGE));
				double volumenMax = Double.parseDouble(JOptionPane.showInputDialog (this, "Volumen maximo ", "Producto con cierta caracteristica", JOptionPane.QUESTION_MESSAGE));

				superAndes.productosEnRangoVolumen(volumenMin,volumenMax);
				resultado = "En indiceOcupacion\n\n";
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			case "5":
				String nit = JOptionPane.showInputDialog (this, "Proveedor ", "Producto con cierta caracteristica", JOptionPane.QUESTION_MESSAGE);

				superAndes.productosDeProveedor(nit);
				resultado = "En indiceOcupacion\n\n";
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);

				break;

			default:
				break;
			}

		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}

	}
	/* ****************************************************************
	 *			Requerimientos funcionales de Bono
	 *****************************************************************/
	public void comprasAProveedores()
	{
		System.out.println("Hola");
	}

	public void ventasAUsuario()
	{
		System.out.println("Hola");
	}

}
