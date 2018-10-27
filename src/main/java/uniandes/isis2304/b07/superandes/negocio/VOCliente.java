package uniandes.isis2304.b07.superandes.negocio;

public interface VOCliente 
{
	/**
	 * Devuelve el tipo de documento del cliente.
	 * Llave primaria compuesta
	 * @return el tipo de documento del cliente.
	 */
	public String getTipoDocumento();
	
	//TODO ¿Esta bien?
	//Esto solo funciona por el NIT pero puede generar errores, arreglar aqui o
	//en interfaz
	/**
	 * Devuelve el numero de documento del cliente.
	 * Llave primaria compuesta.
	 * @return el numero de documento del cliente.
	 */
	public String getNumDocumento();
	
	/**
	 * Devuelve el primer nombre del cliente.
	 * @return el primer nombre del cliente.
	 */
	public String getNombre();
	
	/**
	 * Devuelve el correo del cliente.
	 * @return el correo del cliente.
	 */
	public String getCorreo();
	
	@Override
	/**
	 * Metodo toString()
	 * @return Una cadena de caracteres con todos los atributos.
	 */
	public String toString();
}
