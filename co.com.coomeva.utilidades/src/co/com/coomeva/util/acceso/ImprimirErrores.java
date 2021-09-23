/**
 * 
 */
package co.com.coomeva.util.acceso;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Date;

import co.com.coomeva.util.date.ManipulacionFechas;

/**
 * @author clmasw02
 *
 */
public class ImprimirErrores {
	
	private PrintStream salida;
	private PrintStream errores;	
	private PrintStream outSystem;
	private PrintStream errSystem;
	private String origen;
	private static ImprimirErrores imprimir;
	public static ImprimirErrores getInstance(String origen) throws Exception{		
		if(imprimir == null){
			imprimir = new ImprimirErrores(origen);
		}		
		return imprimir;		
	}

	/**
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	
	public ImprimirErrores(String fileName) throws Exception {
		String nameError,nameOut;
		Date actual = ManipulacionFechas.getFechaActual();
		nameError = "file";
		nameOut = "file";
		if(fileName != null){
			if(fileName.indexOf(".") != -1){
				String[] fileNA = fileName.split("\\.");
				fileName = fileNA[0];
			}
			nameError = fileName.trim();
			nameOut = fileName.trim();
		}
		nameError += "Error-"+ManipulacionFechas.dateToString(actual, "dd-MM-yyyy-HH-mm-ss");
		nameOut   += "Out-"+ManipulacionFechas.dateToString(actual, "dd-MM-yyyy-HH-mm-ss");		
		
		salida = new PrintStream(nameOut+".log");
		errores = new PrintStream(nameError+".log");
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the salida
	 */
	public PrintStream getSalida() {
		return salida;
	}

	/**
	 * @param salida the salida to set
	 */
	public void setSalida(PrintStream salida) {
		this.salida = salida;
	}

	/**
	 * @return the errores
	 */
	public PrintStream getErrores() {
		return errores;
	}

	/**
	 * @param errores the errores to set
	 */
	public void setErrores(PrintStream errores) {
		this.errores = errores;
	}

	/**
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}

	/**
	 * @return the imprimir
	 */
	public static ImprimirErrores getImprimir() {
		return imprimir;
	}

	/**
	 * @param imprimir the imprimir to set
	 */
	public static void setImprimir(ImprimirErrores imprimir) {
		ImprimirErrores.imprimir = imprimir;
	}

	/**
	 * @return the outSystem
	 */
	public PrintStream getOutSystem() {
		return outSystem;
	}

	/**
	 * @param outSystem the outSystem to set
	 */
	public void setOutSystem(PrintStream outSystem) {
		this.outSystem = outSystem;
	}

	/**
	 * @return the errSystem
	 */
	public PrintStream getErrSystem() {
		return errSystem;
	}

	/**
	 * @param errSystem the errSystem to set
	 */
	public void setErrSystem(PrintStream errSystem) {
		this.errSystem = errSystem;
	}

}
