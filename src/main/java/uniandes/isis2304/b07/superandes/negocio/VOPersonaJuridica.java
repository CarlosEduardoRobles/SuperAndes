package uniandes.isis2304.b07.superandes.negocio;

public interface VOPersonaJuridica 
{	
	/**
	 * Devuelve el tipo de documento de la persona juridica.
	 * Llave primaria compuesta
	 * @return el tipo de documento de la persona juridica.
	 */
	public String getTipoDocumento();
	
	/**
	 * Devuelve el numero de documento de la persona juridica.
	 * Llave primaria compuesta
	 * @return el numero de documento de la persona juridica.
	 */
	public String getNumDocumento();
	
	/**
	 * Devuelve la direccion de la persona juridica.
	 * @return la direccion de la persona juridica.
	 */
	public String getDireccion();
	
	@Override
	/**
	 * Metodo toString()
	 * @return Una cadena de caracteres con todos los atributos.
	 */
	public String toString();
}
