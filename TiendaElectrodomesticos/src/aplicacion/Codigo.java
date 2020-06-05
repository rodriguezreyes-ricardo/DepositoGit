package aplicacion;

import java.io.Serializable;

/**
 * The Class Codigo.
 */
public class Codigo  implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The valor. */
	private String valor;

	/**
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Sets the valor.
	 *
	 * @param valor the new valor
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * Verificar codigo.
	 *
	 * @param valor the valor
	 * @return true, if successful
	 */
	public boolean verificarCodigo(String valor) {
		boolean codigoOK = false;
		if (valor.length() == 8 && esSoloLetrasYNumeros(valor)) {
			codigoOK = true;
		}

		return codigoOK;
	}

	/**
	 * Es solo letrasy numeros.
	 *
	 * @param valor the valor
	 * @return true, if successful
	 */
	private boolean esSoloLetrasYNumeros(String valor) {
		boolean esSoloLetraYNumeros = false;
		if (valor.length() == 8 && valor.toUpperCase().matches("[0-9 A-Z]*")) {
			esSoloLetraYNumeros = true;
		}
		return esSoloLetraYNumeros;
	}
}
