package uniandes.isis2304.b07.superandes.negocio;

//TODO ¿Es necesario usar el nombre y apellido? Puede solo usarse uno como ambos.
public class Cliente  implements VOCliente
{
	//-----------------------------------------------------------
	//-------------------------Atributos-------------------------
	//-----------------------------------------------------------		
	private String tipoDocumento, numDocumento, nombre, apellido, correo;

	//-----------------------------------------------------------
	//-----------------------Constructores-----------------------
	//-----------------------------------------------------------
	/**
	 * Metodo Constructor por defecto. Inicializa todos los atributos en 0.
	 */
	public Cliente() 
	{
		this.tipoDocumento = "";
		this.numDocumento = "";
		this.nombre = "";
		this.apellido = "";
		this.correo = "";
	}	
	
	/**
	 * Constructor con valores
	 * @param tipoDocumento - Tipo de documento del cliente. 
	 * @param numDocumento - Numero de documento del cliente.
	 * @param nombre - Primer nombre del cliente.
	 * @param apellido - Primer apellido del cliente.
	 * @param correo - Correo del cliente.
	 */
	public Cliente(String tipoDocumento, String numDocumento, String nombre, String apellido, String correo) 
	{
		this.tipoDocumento = tipoDocumento;
		this.numDocumento = numDocumento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
	}
	
	//-----------------------------------------------------------
	//--------------------------Metodos--------------------------
	//-----------------------------------------------------------
	public String getTipoDocumento() { return tipoDocumento; }

	/**
	 * Asigna el tipo de documento del cliente.
	 * @param tipoDocumento - el tipo de documento del cliente.
	 */
	public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }

	public String getNumDocumento() { return numDocumento; }

	/**
	 * Asigna el numero de documento del cliente.
	 * @param numDocumento - el numero de documento del cliente.
	 */
	public void setNumDocumento(String numDocumento) {this.numDocumento = numDocumento; }

	public String getNombre() { return nombre; }

	/**
	 * Asigna el primer nombre del cliente.
	 * @param nombre - el primer nombre del cliente.
	 */
	public void setNombre(String nombre) { this.nombre = nombre; }

	public String getApellido() { return apellido; }

	/**
	 * Asigna el primer apellido del cliente.
	 * @param apellido - el primer apellido del cliente.
	 */
	public void setApellido(String apellido) { this.apellido = apellido; }

	public String getCorreo() {	return correo; }

	/**
	 * Asigna el correo del cliente.
	 * @param correo - el correo del cliente.
	 */
	public void setCorreo(String correo) { this.correo = correo; }

	@Override
	public String toString() 
	{
		return "Cliente [tipoDocumento=" + tipoDocumento + ", numDocumento=" + numDocumento + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", correo=" + correo + "]";
	}
	
		
}
