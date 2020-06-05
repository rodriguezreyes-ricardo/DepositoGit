package aplicacion;

import java.io.Serializable;

/**
 * The Class ElectrodomesticoGrande.
 */
public class ElectrodomesticoGrande extends Electrodomestico implements GastosEnvio,Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The peso. */
	private float peso;

	/** The medida. */
	private Medida medida;

	/** The requiere instalacion. */
	private boolean requiereInstalacion;

	/**
	 * Gets the peso.
	 *
	 * @return the peso
	 */
	public float getPeso() {
		return peso;
	}

	/**
	 * Sets the peso.
	 *
	 * @param peso the new peso
	 */
	public void setPeso(float peso) {
		this.peso = peso;
	}

	/**
	 * Gets the medida.
	 *
	 * @return the medida
	 */
	public Medida getMedida() {
		return medida;
	}

	/**
	 * Sets the medida.
	 *
	 * @param medida the new medida
	 */
	public void setMedida(Medida medida) {
		this.medida = medida;
	}

	/**
	 * Checks if is requiere instalacion.
	 *
	 * @return true, if is requiere instalacion
	 */
	public boolean isRequiereInstalacion() {
		return requiereInstalacion;
	}

	/**
	 * Sets the requiere instalacion.
	 *
	 * @param requiereInstalacion the new requiere instalacion
	 */
	public void setRequiereInstalacion(boolean requiereInstalacion) {
		this.requiereInstalacion = requiereInstalacion;
	}

	/**
	 * Calcular precio.
	 *
	 * @param precioUnitario the precio unitario
	 * @return the double
	 */
	@Override
	public double calcularPrecio(double precioUnitario) {
		double precio = this.getPrecioUnitario() + getGastosEnvio(this.getPeso(), this.getMedida());
		if (requiereInstalacion) {
			precio += 15.00;
		}
		return precio;
	}


	/**
	 * Gets the gastos envio.
	 *
	 * @param peso the peso
	 * @param medida the medida
	 * @return the gastos envio
	 */
	@Override
	public double getGastosEnvio(float peso, Medida medida) {
		float gastosEnvio = peso * medida.getMetrosCubicos() / 1000000;

		if (gastosEnvio > 50) {
			gastosEnvio = 0L;
		}
		return gastosEnvio;

	}

}
