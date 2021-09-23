package co.com.coomeva.modelo.reportesToExcel;

import java.util.ArrayList;

public class ReporteExcel {

	private String nombreArchivo;
	private String nombreHoja;
	private String tituloReporte;
	private ArrayList<DatoCabecera> listaDatosCabecera = new ArrayList<DatoCabecera>();
	private ArrayList<DatoColumna> listaDatoColumnas = new ArrayList<DatoColumna>();
	private String notaPie1;
	private String notaPie2;
	
	
	public String getNotaPie1() {
		return notaPie1;
	}
	public void setNotaPie1(String notaPie1) {
		this.notaPie1 = notaPie1;
	}
	public String getNotaPie2() {
		return notaPie2;
	}
	public void setNotaPie2(String notaPie2) {
		this.notaPie2 = notaPie2;
	}
	public String getNombreHoja() {
		return nombreHoja;
	}
	public void setNombreHoja(String nombreHoja) {
		this.nombreHoja = nombreHoja;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getTituloReporte() {
		return tituloReporte;
	}
	public void setTituloReporte(String tituloReporte) {
		this.tituloReporte = tituloReporte;
	}
	public ArrayList<DatoCabecera> getListaDatosCabecera() {
		return listaDatosCabecera;
	}
	public void setListaDatosCabecera(ArrayList<DatoCabecera> listaDatosCabecera) {
		this.listaDatosCabecera = listaDatosCabecera;
	}
	public ArrayList<DatoColumna> getListaDatoColumnas() {
		return listaDatoColumnas;
	}
	public void setListaDatoColumnas(ArrayList<DatoColumna> listaDatoColumnas) {
		this.listaDatoColumnas = listaDatoColumnas;
	}
	

	
	
}
