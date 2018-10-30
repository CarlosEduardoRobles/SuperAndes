package uniandes.isis2304.b07.superandes.negocio;

public interface VOBodega 
{
	//TODO Hacer interface.
	/**
	 * Devuelve el id de la bodega.
	 * @return
	 */
	public long getId();
	
	/**
	 * Devuelve el id de la sucursal a la que pertenece la bodega.
	 * @return el id de la sucursal a la que pertenece la bodega.
	 */
	public long getIdSucursal();
	
	/**
	 * Devuelve el id de la categoria asociada a esta bodega.
	 * @return el id de la categoria asociada a esta bodega.
	 */
	public long getIdCategoria();
	
	/**
	 * Devuelve el id del producto almacenado en la bodega.
	 * @return el id del producto almacenado en la bodega.
	 */
	public long getIdProducto();
	
	/**
	 * Devuelve el valor actual del volumen almacenado en la bodega.
	 * @return el valor actual del volumen almacenado en la bodega.
	 */
	public Double getVolumenActual();
	
	/**
	 * Devuelve el volumen maximo que puede tener la bodega
	 * @return el volumen maximo que puede tener la bodega
	 */
	public Double getVolumenMaximo();
	
	/**
	 * Devuelve el valor actual del peso almacenado en la bodega.
	 * @return el valor actual del peso almacenado en la bodega.
	 */
	public Double getPesoActual();
	
	/**
	 * Devuelve el peso maximo que puede tener la bodega.
	 * @return el peso maximo que puede tener la bodega.
	 */
	public Double getPesoMaximo();	
	
	/**
	 * Metodo toString.
	 * @return metodoToString.
	 */
	public String toString();
}
