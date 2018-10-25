package uniandes.isis2304.b07.superandes.negocio;

public interface VOCategoria 
{
	/**
	 * Devuelve el id de la categoria.
	 * Llave primaria.
	 * @return id de la categoria.
	 */
	public long getId();
	
	/**
	 * Devuelve el tipo del producto. Este deberia estar restringido en diferentes valores
	 * estaticos. Ej: Enlatado, Carnes, Pasta, Harinas. 
	 * De igual forma esto deberia ser coherente con el tipo.
	 * @return el tipo del producto.
	 */
	public String getTipo();	
	
	@Override
	/**
	 * Metodo toString()
	 * @return Una cadena de caracteres con todos los atributos.
	 */
	public String toString();
}
