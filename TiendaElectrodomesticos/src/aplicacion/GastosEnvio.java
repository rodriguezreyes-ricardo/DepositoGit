package aplicacion;

/**
 * The Interface GastosEnvio.
 */
public interface GastosEnvio {
 
 /**
  * Gets the gastos envio.
  *
  * @param peso the peso
  * @param medida the medida
  * @return the gastos envio
  */
 public double getGastosEnvio(float peso, Medida medida);
}
