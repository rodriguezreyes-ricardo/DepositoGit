package aplicacion;

import java.io.Serializable;

/**
 * The Class ElectrodomesticoPequenyo.
 */
public class ElectrodomesticoPequenyo extends Electrodomestico  implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Calcular precio.
	 *
	 * @param precio the precio
	 * @return the double
	 */
	@Override
	public double calcularPrecio(double precio) {
		return precio;
	}

}
