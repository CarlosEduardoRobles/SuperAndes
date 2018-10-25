package uniandes.isis2304.b07.superandes.negocio;

public class Categoria implements VOCategoria 
{
	// -----------------------------------------------------------
	// -------------------------Atributos-------------------------
	// -----------------------------------------------------------
	/**
	 * Id de la categoria. Llave primaria.
	 */
	private long id;

	/**
	 * Tipo del porducto. Ej: Carnes, Enlatados ,etc.
	 */
	private String tipo;
	
	// -----------------------------------------------------------
	// -----------------------Constructores-----------------------
	// -----------------------------------------------------------
	/**
	 * Metodo Constructor por defecto. Inicializa todos los atributos en 0.
	 */
	public Categoria() 
	{
		this.id = 0;
		this.tipo = "";		
	}

	/**
	 * Constructor con valores.
	 * 
	 * @param id        - Id de la categoria.
	 * @param tipo      - Tipo de producto.
	 */
	public Categoria(long id, String tipo) 
	{
		this.id = id;
		this.tipo = tipo;
	}

	// -----------------------------------------------------------
	// --------------------------Metodos--------------------------
	// -----------------------------------------------------------
	public long getId() { return id; }

	/**
	 * Asigna el id a la categoria.
	 * @param id - Id a asignar.
	 */
	public void setId(long id) { this.id = id; }

	public String getTipo() { return tipo; }

	/**
	 * Asigna el tipo a la categoria. Ej: Carnes, Enlatados, etc. Deberia ser
	 * coherente con su categoria.
	 * 
	 * @param tipo - Tipo de alimento a asignar.
	 */
	public void setTipo(String tipo) { this.tipo = tipo; }

	@Override
	public String toString() { return "Categoria [id=" + id + ", tipo=" + tipo + "]"; }
	
	

}
