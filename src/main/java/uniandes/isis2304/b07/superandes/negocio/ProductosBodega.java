package uniandes.isis2304.b07.superandes.negocio;

public class ProductosBodega implements VOProductosBodega
{
	//-----------------------------------------------------------
	//-------------------------Atributos-------------------------
	//-----------------------------------------------------------		
	private long idBodega /*Id de la bodega que almacena el producto*/,  
		idSucursal /*Id de la sucursal a la que pertenece la bodega y el producto*/;
	
	private String codigoBarras /*Codigo de barras del producto almacenado*/;
	
	private Integer cantidadProducto /*Cantidad del producto almacenado*/;

	//-----------------------------------------------------------
	//-----------------------Constructores-----------------------
	//-----------------------------------------------------------
	/**
	 * Metodo Constructor por defecto. Inicializa todos los atributos en 0.
	 */
	public ProductosBodega() 
	{
		this.idBodega = 0;
		this.idSucursal = 0;
		this.codigoBarras = null;
		this.cantidadProducto = 0;
	}	
	
	/**
	 * Constructor con valores
	 * @param idBodega - Id de la bodega que almacena el producto.
	 * @param idSucursal - Id de la sucursal a la que pertenece la bodega y el producto.
	 * @param codigoBarras - Codigo de barras del producto almacenado.
	 * @param cantidadProducto - Cantidad del producto almacenado.
	 */
	public ProductosBodega(long idBodega, long idSucursal, String codigoBarras,
			Integer cantidadProducto) 
	{
		this.idBodega = idBodega;
		this.idSucursal = idSucursal;
		this.codigoBarras = codigoBarras;
		this.cantidadProducto = cantidadProducto;
	}

	//-----------------------------------------------------------
	//--------------------------Metodos--------------------------
	//-----------------------------------------------------------
	public long getIdBodega() {return idBodega;}

	/**
	 * Asigna el id de la bodega que almacena el producto.
	 * @param idBodega - id de la bodega que almacena el producto.
	 */
	public void setIdBodega(long idBodega) {this.idBodega = idBodega;}

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
		return "ProductosBodega [idBodega=" + idBodega + ", idSucursal="
				+ idSucursal + ", codigoBarras=" + codigoBarras
				+ ", cantidadProducto=" + cantidadProducto + "]";
	}	
}
