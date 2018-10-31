package uniandes.isis2304.b07.superandes.negocio;

public interface VOProductosBodega 
{
	/**
	 * Devuelve el id del estante al que el producto pertenece.
	 * @return el id del estante al que el producto pertenece.
	 */
	public long getIdBodega();
	
	/**
	 * Devuelve el id de la sucursal que provee el producto almacenado.
	 * @return el id de la sucursal que provee el producto almacenado.
	 */
	public long getIdSucursal();
	
	/**
	 * Devuelve el codigo de barras del producto almacenado.
	 * @return el codigo de barras del producto almacenado.
	 */
	public String getCodigoBarras();
	
	/**
	 * Metodo toString.
	 * @return toString.
	 */
	public String toString();}
