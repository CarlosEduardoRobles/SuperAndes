package uniandes.isis2304.b07.superandes.negocio;

public class Bodega implements VOBodega
{
	//-----------------------------------------------------------
	//-------------------------Atributos-------------------------
	//-----------------------------------------------------------		
	private long id /*Id de la bodega*/, 
		idSucursal /*Id de la sucursal a la que pertenece la bodega*/, 
		idCategoria /*Id de la categoria que posee la bodega*/,
		idProducto /*Id del producto almacenado*/;
	
	
	private Double volumenActual /*Volumen actual de productos almacenados en la bodega*/,
		volumenMaximo /*Volumen maximo que puede almacenarse*/,
		pesoActual /*Peso actual de los productos almacenados en la bodega*/,
		pesoMaximo /*Peso maximo que puede almacenarse*/;
	
	//-----------------------------------------------------------
	//-----------------------Constructores-----------------------
	//-----------------------------------------------------------
	/**
	 * Metodo Constructor por defecto. Inicializa todos los atributos en 0.
	 */
	public Bodega() 
	{
		this.id = 0;
		this.idSucursal = 0;
		this.idCategoria = 0;
		this.volumenActual = 0.0;
		this.volumenMaximo = 0.0;
		this.pesoActual = 0.0;
		this.pesoMaximo = 0.0;
	}	

	/**
	 * Constructos con valores A. Permite asignar todos los atributos de la bodega.
	 * @param id - Id de la bodega.
	 * @param idSucursal - Id de la sucursal a la que pertenece la bodega.
	 * @param idCategoria - Id de la categoria que tiene la bodega.
	 * @param idProducto - Id del productos que es almacenado en la bodega.
	 * @param volumenActual - Volumen actual que tiene la bodega. Se asume que esta en m3
	 * @param volumenMaximo - Volumen maximo que puede tener la bodega. Se asume que esta en m3.
	 * @param pesoActual - Peso actual que tiene la bodega. Se asume que esta en Kg.
	 * @param pesoMaximo - Peso maximo que puede tener la bodega. Se asume que esta en Kg.
	 */
	public Bodega(long id, long idSucursal, long idCategoria, long idProducto, Double volumenActual,
			Double volumenMaximo, Double pesoActual, Double pesoMaximo) 
	{
		this.id = id;
		this.idSucursal = idSucursal;
		this.idCategoria = idCategoria;
		this.idProducto = idProducto;
		this.volumenActual = volumenActual;
		this.volumenMaximo = volumenMaximo;
		this.pesoActual = pesoActual;
		this.pesoMaximo = pesoMaximo;
	}
	
	/**
	 * Constructos con valores B. Como es coherente, crea una bodega vacia sin productos. Por lo tanto inicializa
	 * el volumen y el peso actual en 0.
	 * @param id - Id de la bodega.
	 * @param idSucursal - Id de la sucursal a la que pertenece la bodega.
	 * @param idCategoria - Id de la categoria que tiene la bodega.
	 * @param idProducto - Id del productos que es almacenado en la bodega.
	 * @param volumenMaximo - Volumen maximo que puede tener la bodega. Se asume que esta en m3.
	 * @param pesoMaximo - Peso maximo que puede tener la bodega. Se asume que esta en Kg.
	 */
	public Bodega(long id, long idSucursal, long idCategoria, long idProducto, Double volumenMaximo,
			Double pesoMaximo) 
	{
		this.id = id;
		this.idSucursal = idSucursal;
		this.idCategoria = idCategoria;
		this.idProducto = idProducto;
		this.volumenActual = 0.0;
		this.volumenMaximo = volumenMaximo;
		this.pesoActual = 0.0;
		this.pesoMaximo = pesoMaximo;
	}

	//-----------------------------------------------------------
	//--------------------------Metodos--------------------------
	//-----------------------------------------------------------
	public long getId() {return id;}

	/**
	 * Asigna el id a la bodega.
	 * @param id - id a la bodega.
	 */
	public void setId(long id) {this.id = id;}

	public long getIdSucursal() {return idSucursal;}

	/**
	 * Asigna el id de la sucursal a la que pertenece la bodega.
	 * @param idSucursal -  id de la sucursal a la que pertenece la bodega.
	 */
	public void setIdSucursal(long idSucursal) {this.idSucursal = idSucursal;}

	public long getIdCategoria() {return idCategoria;}	

	/**
	 * Asigna el id de la categoria que tiene la bodega.
	 * @param idCategoria - id de la categoria que tiene la bodega.
	 */
	public void setIdCategoria(long idCategoria) {this.idCategoria = idCategoria;}
	
	public long getIdProducto() {return idProducto;}	

	/**
	 * Asigna el id del producto alamacenado en la bodega.
	 * @param idProducto - id del producto alamacenado en la bodega.
	 */
	public void setIdProducto(long idProducto) {this.idProducto = idProducto;}

	public Double getVolumenActual() {return volumenActual;}

	/**
	 * Asigna el volumen que posee actualmente la bodega. Se asume que esta en m3
	 * @param volumenActual - el volumen que posee actualmente la bodega
	 */
	public void setVolumenActual(Double volumenActual) {this.volumenActual = volumenActual;}

	public Double getVolumenMaximo() {return volumenMaximo;}

	/**
	 * Asigna el volumen maximo que puede poseer la bodega. Se asume que esta en m3
	 * @param volumenMaximo - el volumen maximo que puede poseer la bodega.
	 */
	public void setVolumenMaximo(Double volumenMaximo) {this.volumenMaximo = volumenMaximo;}

	public Double getPesoActual() {return pesoActual;}

	/**
	 * Asigna el peso que posee acutalmente la bodega. Se asume que esta en Kg
	 * @param pesoActual - el peso que posee acutalmente la bodega.
	 */
	public void setPesoActual(Double pesoActual) {this.pesoActual = pesoActual;}

	public Double getPesoMaximo() {return pesoMaximo;}

	/**
	 * Asigna el peso maximo que puede poseer la bodega. Se asume que estan en Kg
	 * @param pesoMaximo - el peso maximo que puede poseer la bodega.
	 */
	public void setPesoMaximo(Double pesoMaximo) {this.pesoMaximo = pesoMaximo;}

	@Override
	public String toString() {
		return "Bodega [id=" + id + ", idSucursal=" + idSucursal + ", idCategoria=" + idCategoria + ", volumenActual="
				+ volumenActual + ", volumenMaximo=" + volumenMaximo + ", pesoActual=" + pesoActual + ", pesoMaximo="
				+ pesoMaximo + "]";
	}	
}
