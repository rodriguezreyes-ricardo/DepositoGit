package aplicacion;

import java.io.Serializable;

/**
 * The Class Medida.
 */
public class Medida  implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The alto. */
	private float alto;

	/** The ancho. */
	private float ancho;

	/** The fondo. */
	private float fondo;

	/**
	 * Gets the alto.
	 *
	 * @return the alto
	 */
	public float getAlto() {
		return alto;
	}

	/**
	 * Sets the alto.
	 *
	 * @param alto the new alto
	 */
	public void setAlto(float alto) {
		this.alto = alto;
	}

	/**
	 * Gets the ancho.
	 *
	 * @return the ancho
	 */
	public float getAncho() {
		return ancho;
	}

	/**
	 * Sets the ancho.
	 *
	 * @param ancho the new ancho
	 */
	public void setAncho(float ancho) {
		this.ancho = ancho;
	}

	/**
	 * Gets the fondo.
	 *
	 * @return the fondo
	 */
	public float getFondo() {
		return fondo;
	}

	/**
	 * Sets the fondo.
	 *
	 * @param fondo the new fondo
	 */
	public void setFondo(float fondo) {
		this.fondo = fondo;
	}

	/**
	 * Gets the metros cubicos.
	 *
	 * @return the metros cubicos
	 */
	public float getMetrosCubicos() {
		return this.ancho + this.alto * this.fondo;
	}
}
