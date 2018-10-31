package uniandes.isis2304.b07.superandes.negocio;

import java.sql.Timestamp;

import javax.jdo.PersistenceManager;

public class Pedido implements VOPedido{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	private long id;
	
	private Timestamp fechaEntrega;
	
	private double precioTotal;
	
	private String estadoOrden;
	
	private String nitProveedor;

	public Pedido(PersistenceManager pm, String idSucursal, String[] codigosProductos, String nitProveedor2,
			Timestamp fechaPrevista, double precioTotal2) 
	{
		// TODO Hacer este constructor
	}
	
	public Pedido() 
	{
		this.id = 0;
		this.fechaEntrega = Timestamp.valueOf("0001-01-01 00:00:00.0");
		this.precioTotal = 0.0;
		this.estadoOrden = "";
		this.nitProveedor = "";
	}

	public Pedido(long id, Timestamp fechaEntrega, double precioTotal, String estadoOrden, String nitProveedor) 
	{
		this.id = id;
		this.fechaEntrega = fechaEntrega;
		this.precioTotal = precioTotal;
		this.estadoOrden = estadoOrden;
		this.nitProveedor = nitProveedor;
	}

	/**
	 * @return the id
	 */
	public long getId() { return id; }

	/**
	 * @param id the id to set
	 */
	public void setId(long id) { this.id = id; }

	/**
	 * @return the fechaEntrega
	 */
	public Timestamp getFechaEntrega() { return fechaEntrega; }

	/**
	 * @param fechaEntrega the fechaEntrega to set
	 */
	public void setFechaEntrega(Timestamp fechaEntrega) { this.fechaEntrega = fechaEntrega; }

	/**
	 * @return the precioTotal
	 */
	public double getPrecioTotal() { return precioTotal; }

	/**
	 * @param precioTotal the precioTotal to set
	 */
	public void setPrecioTotal(double precioTotal) { this.precioTotal = precioTotal; }

	/**
	 * @return the estadoOrden
	 */
	public String getEstadoOrden() { return estadoOrden; }

	/**
	 * @param estadoOrden the estadoOrden to set
	 */
	public void setEstadoOrden(String estadoOrden) { this.estadoOrden = estadoOrden; }

	/**
	 * @return the nitProveedor
	 */
	public String getNitProveedor() { return nitProveedor; }

	/**
	 * @param nitProveedor the nitProveedor to set
	 */
	public void setNitProveedor(String nitProveedor) { this.nitProveedor = nitProveedor; }

	@Override
	public String toString() 
	{
		return "Pedido [id=" + id + ", fechaEntrega=" + fechaEntrega + ", precioTotal=" + precioTotal + ", estadoOrden="
				+ estadoOrden + ", nitProveedor=" + nitProveedor + "]";
	}
}
