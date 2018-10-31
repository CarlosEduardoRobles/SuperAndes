package uniandes.isis2304.b07.superandes.negocio;

public class Estante implements VOEstante
{
	//-----------------------------------------------------------
	//-------------------------Atributos-------------------------
	//-----------------------------------------------------------		
	private long id /*Id del estante*/, 
		idSucursal /*Id de la sucursal a la que pertenece la bodega*/, 
		idCategoria /*Id de la categoria que posee la bodega*/;	
		
	private Double volumenActual /*Volumen actual de productos almacenados en la bodega*/,
		volumenMaximo /*Volumen maximo que puede almacenarse*/,
		pesoActual /*Peso actual de los productos almacenados en la bodega*/,
		pesoMaximo /*Peso maximo que puede almacenarse*/;
	
	private Integer nivelDeAbastecimiento /*Nivel de abastecimiento del estante*/;
	
	//-----------------------------------------------------------
	//-----------------------Constructores-----------------------
	//-----------------------------------------------------------
	public Estante() 
	{
		this.id = 0;
		this.idSucursal = 0;
		this.idCategoria = 0;
		this.volumenActual = 0.0;
		this.volumenMaximo = 0.0;
		this.pesoActual = 0.0;
		this.pesoMaximo = 0.0;
		this.nivelDeAbastecimiento = 0;
	}
	
	public Estante(long id, long idSucursal, long idCategoria,
			Double volumenActual, Double volumenMaximo, Double pesoActual,
			Double pesoMaximo, Integer nivelDeAbastecimiento) 
	{
		this.id = id;
		this.idSucursal = idSucursal;
		this.idCategoria = idCategoria;
		this.volumenActual = volumenActual;
		this.volumenMaximo = volumenMaximo;
		this.pesoActual = pesoActual;
		this.pesoMaximo = pesoMaximo;
		this.nivelDeAbastecimiento = nivelDeAbastecimiento;
	}	
	
	public Estante(long id, long idSucursal, long idCategoria,Double volumenMaximo,
			Double pesoMaximo, Integer nivelDeAbastecimiento) 
	{
		this.id = id;
		this.idSucursal = idSucursal;
		this.idCategoria = idCategoria;
		this.volumenActual = 0.0;
		this.volumenMaximo = volumenMaximo;
		this.pesoActual = 0.0;
		this.pesoMaximo = pesoMaximo;
		this.nivelDeAbastecimiento = nivelDeAbastecimiento;
	}


	//-----------------------------------------------------------
	//--------------------------Metodos--------------------------
	//-----------------------------------------------------------
	public long getId() {return id;}
	
	/**
	 * Asigna el id del estante.
	 * @param id - id del estante.
	 */
	public void setId(long id) {this.id = id;}

	public long getIdSucursal() {return idSucursal;}

	/**
	 * Asigna el id de la sucursal a la que pertenece el estante.
	 * @param idSucursal -  id de la sucursal a la que pertenece el estante.
	 */
	public void setIdSucursal(long idSucursal) {this.idSucursal = idSucursal;}

	public long getIdCategoria() {return idCategoria;}	

	/**
	 * Asigna el id de la categoria que tiene el estante.
	 * @param idCategoria - id de la categoria que tiene el estante.
	 */
	public void setIdCategoria(long idCategoria) {this.idCategoria = idCategoria;}

	public Double getVolumenActual() {return volumenActual;}

	/**
	 * Asigna el volumen que posee actualmente el estante.. Se asume que esta en m3
	 * @param volumenActual - el volumen que posee actualmente el estante.
	 */
	public void setVolumenActual(Double volumenActual) {this.volumenActual = volumenActual;}

	public Double getVolumenMaximo() {return volumenMaximo;}

	/**
	 * Asigna el volumen maximo que puede poseer el estante.. Se asume que esta en m3
	 * @param volumenMaximo - el volumen maximo que puede poseer el estante..
	 */
	public void setVolumenMaximo(Double volumenMaximo) {this.volumenMaximo = volumenMaximo;}

	public Double getPesoActual() {return pesoActual;}

	/**
	 * Asigna el peso que posee acutalmente el estante. Se asume que esta en Kg
	 * @param pesoActual - el peso que posee acutalmente el estante.
	 */
	public void setPesoActual(Double pesoActual) {this.pesoActual = pesoActual;}

	public Double getPesoMaximo() {return pesoMaximo;}

	/**
	 * Asigna el peso maximo que puede poseer el estante. Se asume que estan en Kg
	 * @param pesoMaximo - el peso maximo que puede poseer el estante.
	 */
	public void setPesoMaximo(Double pesoMaximo) {this.pesoMaximo = pesoMaximo;}
	
	public Integer getNivelDeAbastecimiento() {return nivelDeAbastecimiento;}
	
	/**
	 * Asigna el nivel de abastecimiento del estante. 
	 * @param nivelDeAbastecimiento - el nivel de abastecimiento del estante. 
	 */
	public void setNivelDeAbastecimiento(Integer nivelDeAbastecimiento) 
		{this.nivelDeAbastecimiento = nivelDeAbastecimiento;}
	
	@Override
	public String toString() 
	{
		return "Estante [id=" + id + ", idSucursal=" + idSucursal
				+ ", idCategoria=" + idCategoria + ", volumenActual="
				+ volumenActual + ", volumenMaximo=" + volumenMaximo
				+ ", pesoActual=" + pesoActual + ", pesoMaximo=" + pesoMaximo
				+ ", nivelDeAbastecimiento=" + nivelDeAbastecimiento + "]";
	}
	
	
}
