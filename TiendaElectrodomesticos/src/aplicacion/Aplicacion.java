package aplicacion;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Class Aplicacion.
 */
public class Aplicacion {

	/** The codigo producto. */
	private static Codigo codigoProducto;

	/** The electrodomesticos. */
	private static List<Electrodomestico> electrodomesticos;

	/** The entrada. */
	static Scanner entrada = new Scanner(System.in);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		codigoProducto = new Codigo();
		electrodomesticos = recuperarFicheroDeElectrodomesticos();

		if (electrodomesticos == null) {
			electrodomesticos = new ArrayList<Electrodomestico>();
		}

		try {
			String opcionMenu = "";
			while (!opcionMenu.equals("a") && !opcionMenu.equals("v") && !opcionMenu.equals("e")) {
				opcionMenu = (String) pedirDatos("Teclee una opción Añadir [a], Vender [v] , Eliminar [e] o salir [s]",
						String.class);
			}

			switch (opcionMenu) {
			case "a":
				agregarElectrodomestico();
				break;
			case "v":
				venderElectrodomestico();
				if (electrodomesticos.isEmpty()) {
					System.err.println("No existe stock en tienda");
					cerrarAplicacion();
				}
				break;
			case "e":

				break;
			case "s":
				cerrarAplicacion();
				break;
			default:
				break;
			}
		} catch (Exception e) {
			System.err.println(e);
		}

		cerrarAplicacion();
	}

	/**
	 * Cerrar aplicacion.
	 */
	public static void cerrarAplicacion() {
		System.out.println("Fin");
		System.exit(0);
	}

	/**
	 * Agregar electrodomestico.
	 */
	public static void agregarElectrodomestico() {
		String codigo = null;
		String esElectrodomesticoGrande = "";
		boolean existeProducto = true;

		while (!esElectrodomesticoGrande.equals("s") && !esElectrodomesticoGrande.equals("n")) {
			esElectrodomesticoGrande = (String) pedirDatos("Es un electrodomestioco grande? [s|n]", String.class);
		}

		while (existeProducto) {
			codigo = pedirCodigo();
			existeProducto = existeProducto(codigo);
		}

		codigoProducto.setValor(codigo);

		String descripcion = (String) pedirDatos("Descripcion del producto:", String.class);
		String fabricante = (String) pedirDatos("Fabricante del producto:", String.class);
		String modelo = (String) pedirDatos("Modelo del producto:", String.class);
		int stock = (Integer) pedirDatos("Stock del producto:", Integer.class);
		double precioUnitario = (Double) pedirDatos("Precio del producto:", Double.class);

		if (esElectrodomesticoGrande.equals("s")) {
			float peso = (Float) pedirDatos("Peso del producto:", Float.class);
			float alto = (Float) pedirDatos("Alto del producto:", Float.class);
			float ancho = (Float) pedirDatos("Ancho del producto:", Float.class);
			float fondo = (Float) pedirDatos("Fondo del producto:", Float.class);

			String requiereInstalacion = "";
			while (!requiereInstalacion.equals("s") && !requiereInstalacion.equals("n")) {
				requiereInstalacion = (String) pedirDatos("Requiere instalacion? [s|n]", String.class);
			}

			Medida medida = new Medida();
			medida.setAlto(alto);
			medida.setAncho(ancho);
			medida.setFondo(fondo);

			ElectrodomesticoGrande electrodomesticoGrande = new ElectrodomesticoGrande();
			electrodomesticoGrande.setCodigo(codigoProducto);
			electrodomesticoGrande.setPeso(peso);
			electrodomesticoGrande.setDescripcion(descripcion);
			electrodomesticoGrande.setFabricante(fabricante);
			electrodomesticoGrande.setModelo(modelo);
			electrodomesticoGrande.setStock(stock);
			electrodomesticoGrande.setPrecioUnitario(precioUnitario);
			electrodomesticoGrande.setMedida(medida);
			electrodomesticoGrande.setRequiereInstalacion(requiereInstalacion == "s" ? true : false);

			electrodomesticos.add(electrodomesticoGrande);
		} else {
			ElectrodomesticoPequenyo electrodomesticoPequenyo = new ElectrodomesticoPequenyo();
			electrodomesticoPequenyo.setCodigo(codigoProducto);
			electrodomesticoPequenyo.setDescripcion(descripcion);
			electrodomesticoPequenyo.setFabricante(fabricante);
			electrodomesticoPequenyo.setModelo(modelo);
			electrodomesticoPequenyo.setStock(stock);
			electrodomesticoPequenyo.setPrecioUnitario(precioUnitario);

			electrodomesticos.add(electrodomesticoPequenyo);
		}
		guardarElectrodomestico();
	}

	/**
	 * Pedir codigo.
	 *
	 * @return the string
	 */
	public static String pedirCodigo() {
		boolean codigoOK = false;
		String codigo = null;
		while (!codigoOK) {
			codigo = (String) pedirDatos("Código del producto:", String.class);
			codigoOK = verificarCodigoProducto(codigo);
		}

		return codigo;
	}

	/**
	 * Vender electrodomestico.
	 */
	public static void venderElectrodomestico() {
		String tipoElectrodomestico = "";
		while (!tipoElectrodomestico.equals("g") && !tipoElectrodomestico.equals("p")) {
			tipoElectrodomestico = (String) pedirDatos(
					"Que tipo de electrodomestico deseas comprar, grande [g] o pequeño [p]?", String.class);
		}

		listadoElectrodomesticos(tipoElectrodomestico);

		String codigo = pedirCodigo();
		Electrodomestico electrodomestico = getEletrodomestico(codigo);

		int cantidad = 0;
		boolean cantidadValida = false;
		while (!cantidadValida) {
			cantidad = (Integer) pedirDatos("Introduce la cantidad solicitada:", Integer.class);
			if (validarCantidad(cantidad, electrodomestico)) {
				cantidadValida = true;
			}
		}

		actualizarStock(cantidad, electrodomestico);
		if (guardarElectrodomestico()) {

			double importe = electrodomestico.calcularPrecio(electrodomestico.getPrecioUnitario()) * cantidad;

			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("***********EL IMPORTE TOTAL ES: " + importe + "€**********");
		} else {
			// rollback a la actualización de cantidad;
			electrodomestico.setStock(electrodomestico.getStock() + cantidad);
		}
	}

	/**
	 * Actualizar stock.
	 *
	 * @param cantidad the cantidad
	 * @param electrodomestico the electrodomestico
	 */
	public static void actualizarStock(int cantidad, Electrodomestico electrodomestico) {
		electrodomestico.setStock(electrodomestico.getStock() - cantidad);
	}

	/**
	 * Gets the eletrodomestico.
	 *
	 * @param codigo the codigo
	 * @return the eletrodomestico
	 */
	public static Electrodomestico getEletrodomestico(String codigo) {
		Electrodomestico result = null;
		for (Electrodomestico electrodomestico : electrodomesticos) {
			if (codigo.equals(electrodomestico.getCodigo().getValor())) {
				result = electrodomestico;
			}
		}

		return result;
	}

	/**
	 * Validar cantidad.
	 *
	 * @param cantidad the cantidad
	 * @param electrodomestico the electrodomestico
	 * @return true, if successful
	 */
	public static boolean validarCantidad(int cantidad, Electrodomestico electrodomestico) {
		boolean result = false;
		if (cantidad > 0 && electrodomestico.getStock() > 0 && cantidad <= electrodomestico.getStock()) {
			result = true;
		} else if (electrodomestico.getStock() == 0) {
			System.err.println("No hay stock de ese producto");
			cerrarAplicacion();
		} else {
			System.err.println("No hay stock suficiente. Por favor escoge una cantidad inferior");
		}

		return result;
	}

	/**
	 * Listado electrodomesticos.
	 *
	 * @param tipoElectrodomestico the tipo electrodomestico
	 */
	public static void listadoElectrodomesticos(String tipoElectrodomestico) {
		System.out.println("ELECTRODOMESTICOS " + (tipoElectrodomestico.equals("g") ? "GRANDES" : "PEQUEÑOS"));
		for (Electrodomestico electrodomestico : electrodomesticos) {
			if (tipoElectrodomestico.equals("g") && electrodomestico instanceof ElectrodomesticoGrande) {
				ElectrodomesticoGrande electrodomesticoGrande = (ElectrodomesticoGrande) electrodomestico;
				System.out.println("        - Código: " + electrodomesticoGrande.getCodigo().getValor() + "| "
						+ "Código: " + electrodomesticoGrande.getDescripcion() + "| " + "Fabricante: "
						+ electrodomesticoGrande.getFabricante() + "| " + "Modelo: "
						+ electrodomesticoGrande.getModelo() + "| " + "Stock: " + electrodomesticoGrande.getStock()
						+ "| " + "Precio unitario: " + electrodomesticoGrande.getPrecioUnitario() + "| " + "Alto: "
						+ electrodomesticoGrande.getMedida().getAlto() + "| " + "Peso: "
						+ electrodomesticoGrande.getPeso() + "| " + "Requiere instalación: "
						+ electrodomesticoGrande.isRequiereInstalacion() + "| " + "Ancho: "
						+ electrodomesticoGrande.getMedida().getAncho() + "| " + "Fondo: "
						+ electrodomesticoGrande.getMedida().getFondo());
			} else if (tipoElectrodomestico.equals("p") && electrodomestico instanceof ElectrodomesticoPequenyo) {
				ElectrodomesticoPequenyo electrodomesticoPequenyo = (ElectrodomesticoPequenyo) electrodomestico;
				System.out.println("        - Código: " + electrodomesticoPequenyo.getCodigo().getValor() + "| "
						+ "Código: " + electrodomesticoPequenyo.getDescripcion() + "| " + "Fabricante: "
						+ electrodomesticoPequenyo.getFabricante() + "| " + "Modelo: "
						+ electrodomesticoPequenyo.getModelo() + "| " + "Stock: " + electrodomesticoPequenyo.getStock()
						+ "| " + "Precio unitario: " + electrodomesticoPequenyo.getPrecioUnitario());
			}
		}
	}

	/**
	 * Guardar electrodomestico.
	 *
	 * @return true, if successful
	 */
	public static boolean guardarElectrodomestico() {
		boolean result = false;
		String guardar = "";
		while (!guardar.equals("s") && !guardar.equals("n")) {
			guardar = (String) pedirDatos("Quieres guardar este producto? [s|n]", String.class);
		}
		if (guardar.equals("s")) {
			result = guardarFicheroDeElectrodomesticos(electrodomesticos);
		} else {
			System.err.println("Operación anulada por el usuario.");
		}
		System.out.println("");
		System.out.println("");
		System.out.println("");

		return result;
	}

	/**
	 * Verificar codigo producto.
	 *
	 * @param codigo the codigo
	 * @return true, if successful
	 */
	public static boolean verificarCodigoProducto(String codigo) {
		return codigoProducto.verificarCodigo(codigo);
	}

	/**
	 * Existe producto.
	 *
	 * @param codigo the codigo
	 * @return true, if successful
	 */
	public static boolean existeProducto(String codigo) {
		boolean result = false;
		for (Electrodomestico electrodomestico : electrodomesticos) {
			if (codigo.equals(electrodomestico.getCodigo().getValor())) {
				result = true;
				System.err.println("Este codigo de producto ya existe.");
				break;
			}
		}

		return result;
	}

	/**
	 * Pedir datos.
	 *
	 * @param mensaje the mensaje
	 * @param tipoValor the tipo valor
	 * @return the object
	 */
	public static Object pedirDatos(String mensaje, Object tipoValor) {
		boolean tipoOK = true;

		do {
			try {
				System.out.println(mensaje);
				String valor = entrada.next();
				if (tipoValor == String.class) {
					return valor;
				} else if (tipoValor == Integer.class) {
					return Integer.parseInt(valor);
				} else if (tipoValor == Float.class) {
					return Float.parseFloat(valor);
				} else if (tipoValor == Double.class) {
					return Double.parseDouble(valor);
				} else {
					tipoOK = false;
				}
			} catch (NumberFormatException e) {
				System.err.println("Formato incorrecto!");
				tipoOK = false;
			}
		} while (!tipoOK);

		return null;
	}

	/**
	 * Guardar fichero de electrodomesticos.
	 *
	 * @param electrodomesticos the electrodomesticos
	 * @return true, if successful
	 */
	public static boolean guardarFicheroDeElectrodomesticos(List<Electrodomestico> electrodomesticos) {
		boolean result = false;
		FileOutputStream fileOutputStream;
		ObjectOutputStream objectOutputStream;
		try {
			fileOutputStream = new FileOutputStream("electrodomesticos.dat");
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(electrodomesticos);
			result = true;
			System.out.println("Se ha guardado el producto.");
		} catch (IOException e) {
			System.err.println("No se encuentra el fichero");
		}

		return result;
	}

	/**
	 * Recuperar fichero de electrodomesticos.
	 *
	 * @return the list
	 */
	public static List<Electrodomestico> recuperarFicheroDeElectrodomesticos() {
		FileInputStream fileInputStream;
		ObjectInputStream objectInputStream;
		try {
			fileInputStream = new FileInputStream("electrodomesticos.dat");
			objectInputStream = new ObjectInputStream(fileInputStream);
			List<Electrodomestico> electrodomesticos = (List<Electrodomestico>) objectInputStream.readObject();
			return electrodomesticos;

		} catch (ClassNotFoundException e) {
			System.err.println("No se encuentra la clase");
		} catch (IOException e) {
			System.err.println("No se encuentra el fichero");
		}

		return null;
	}

}
