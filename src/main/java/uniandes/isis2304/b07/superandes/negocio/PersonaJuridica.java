package uniandes.isis2304.b07.superandes.negocio;

public class PersonaJuridica implements VOPersonaJuridica
{
	//-----------------------------------------------------------
	//-------------------------Atributos-------------------------
	//-----------------------------------------------------------
	private String tipoDocumento, numDocumento, direccion;

	//-----------------------------------------------------------
	//-----------------------Constructores-----------------------
	//-----------------------------------------------------------
	/**
	 * Metodo Constructor por defecto. Inicializa todos los atributos en 0.
	 */
	public PersonaJuridica() 
	{
		this.tipoDocumento = "";
		this.numDocumento = "";
		this.direccion = "";
	}
	
	/**
	 * Constructor con valores
	 * @param tipoDocumento - Tipo de documento de la persona juridica.
	 * @param numDocumento - Numero de documento de la persona juridica (NIT)
	 * @param direccion
	 */
	public PersonaJuridica(String tipoDocumento, String numDocumento, String direccion) 
	{
		this.tipoDocumento = tipoDocumento;
		this.numDocumento = numDocumento;
		this.direccion = direccion;
	}

	//-----------------------------------------------------------
	//--------------------------Metodos--------------------------
	//-----------------------------------------------------------
	public String getTipoDocumento() { return tipoDocumento; }

	/**
	 * Asigna el tipo de documento de la persona juridica.
	 * @param tipoDocumento - el tipo de documento de la persona juridica.
	 */
	public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }

	public String getNumDocumento() { return numDocumento; }

	/**
	 * Asigna el numero de documento de la persona juridica.
	 * @param numDocumento - el numero de documento de la persona juridica.
	 */
	public void setNumDocumento(String numDocumento) { this.numDocumento = numDocumento; }

	public String getDireccion() { return direccion; }

	/**
	 * Asigna la direccion de la persona juridica.
	 * @param direccion - la direccion de la persona juridica.
	 */
	public void setDireccion(String direccion) { this.direccion = direccion; }

	@Override
	public String toString() 
	{
		return "PersonaJuridica [tipoDocumento=" + tipoDocumento + ", numDocumento=" + numDocumento + ", direccion="
				+ direccion + "]";
	}	

}
