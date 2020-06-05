package aplicacion;

import java.io.Serializable;

/**
 * The Class Electrodomestico.
 */
public abstract class Electrodomestico implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The codigo. */
	private Codigo codigo;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The fabricante. */
	private String fabricante;
	
	/** The modelo. */
	private String modelo;
	
	/** The stock. */
	private int stock;
	
	/** The precio unitario. */
	private double precioUnitario;
	
	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public Codigo getCodigo() {
		return codigo;
	}
	
	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(Codigo codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Gets the fabricante.
	 *
	 * @return the fabricante
	 */
	public String getFabricante() {
		return fabricante;
	}
	
	/**
	 * Sets the fabricante.
	 *
	 * @param fabricante the new fabricante
	 */
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	
	/**
	 * Gets the modelo.
	 *
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}
	
	/**
	 * Sets the modelo.
	 *
	 * @param modelo the new modelo
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	/**
	 * Gets the stock.
	 *
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}
	
	/**
	 * Sets the stock.
	 *
	 * @param stock the new stock
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	/**
	 * Gets the precio unitario.
	 *
	 * @return the precio unitario
	 */
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	
	/**
	 * Sets the precio unitario.
	 *
	 * @param precioUnitario the new precio unitario
	 */
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	/**
	 * Calcular precio.
	 *
	 * @param precio the precio
	 * @return the double
	 */
	public abstract double calcularPrecio(double precio);
}
