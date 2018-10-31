package uniandes.isis2304.b07.superandes.negocio;

public interface VOEstante 
{
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
	 * Devuelve el nivel de abastecimiento de un estante.
	 * Este representa la cantidad minima de productos que puede
	 * haber en un estante.
	 * @return el nivel de abastecimiento de un estante.
	 */
	public Integer getNivelDeAbastecimiento();
	
	/**
	 * Metodo toString.
	 * @return metodoToString.
	 */
	public String toString();	
}
