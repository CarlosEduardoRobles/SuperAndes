package uniandes.isis2304.b07.superandes.negocio;

public class ProductosEstante implements VOProductosEstante
{
	//-----------------------------------------------------------
	//-------------------------Atributos-------------------------
	//-----------------------------------------------------------		
	private long idEstante /*Id del estante que almacena el producto*/,  
		idSucursal /*Id de la sucursal a la que pertenece el estante y el producto*/;
		
	private String codigoBarras /*Codigo de barras del producto almacenado*/;
	
	private Integer cantidadProducto /*Cantidad del producto almacenado*/;

	//-----------------------------------------------------------
	//-----------------------Constructores-----------------------
	//-----------------------------------------------------------
	/**
	 * Metodo Constructor por defecto. Inicializa todos los atributos en 0.
	 */
	public ProductosEstante() 
	{
		this.idEstante = 0;
		this.idSucursal = 0;
		this.codigoBarras = null;
		this.cantidadProducto = 0;
	}	
		
	/**
	 * Constructor con valores
	 * @param idEstante - Id del estante que almacena el producto.
	 * @param idSucursal - Id de la sucursal a la que pertenece el estante y el producto.
	 * @param codigoBarras - Codigo de barras del producto almacenado.
	 * @param cantidadProducto - Cantidad del producto almacenado.
	 */
	public ProductosEstante(long idEstante, long idSucursal, String codigoBarras,
			Integer cantidadProducto) 
	{
		this.idEstante = idEstante;
		this.idSucursal = idSucursal;
		this.codigoBarras = codigoBarras;
		this.cantidadProducto = cantidadProducto;
	}

	//-----------------------------------------------------------
	//--------------------------Metodos--------------------------
	//-----------------------------------------------------------
	public long getIdEstante() {return idEstante;}

	/**
	 * Asigna el id del estante que almacena el producto.
	 * @param idEstante - id del estante que almacena el producto.
	 */
	public void setIdEstante(long idEstante) {this.idEstante = idEstante;}

	public long getIdSucursal() {return idSucursal;}

	/**
	 * Asigna el id de la sucursal al que pertenece la bodega y el producto.
	 * @param idSucursal - id de la sucursal al que pertenece la bodega y el producto.
	 */
	public void setIdSucursal(long idSucursal) {this.idSucursal = idSucursal;}
		
	public String getCodigoBarras() {return codigoBarras;}
	
	/**
	 * Asigna el codigo de barras del producto almacenado.
	 * @param codigoBarras - el codigo de barras del producto almacenado.
	 */
	public void setCodigoBarras(String codigoBarras) {this.codigoBarras = codigoBarras;}

	public Integer getCantidadProducto() {return cantidadProducto;}

	/**
	 * Asigna la cantidad del producto almacenados en la bodega.
	 * @param cantidadProducto - la cantidad del producto almacenados en la bodega.
	 */
	public void setCantidadProducto(Integer cantidadProducto) {this.cantidadProducto = cantidadProducto;}
		
	@Override
	public String toString() 
	{
		return "ProductosEstante [idEstante=" + idEstante + ", idSucursal="
				+ idSucursal + ", codigoBarras=" + codigoBarras
				+ ", cantidadProducto=" + cantidadProducto + "]";
	}	
}
