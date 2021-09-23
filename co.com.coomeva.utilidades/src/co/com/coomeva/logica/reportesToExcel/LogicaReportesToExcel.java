package co.com.coomeva.logica.reportesToExcel;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.IndexedColors;

import co.com.coomeva.modelo.reportesToExcel.DatoCabecera;
import co.com.coomeva.modelo.reportesToExcel.DatoColumna;
import co.com.coomeva.modelo.reportesToExcel.ReporteExcel;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.literals.ManejoDeCadenas;

/**
 * Clase que contiene los métodos necesarios para generar reportes en formato excel a partir de 
 * información extraída de la base de datos
 * @author Boris Castro Valencia 
 * Octubre 2008
 *
 */

public class LogicaReportesToExcel {

	public  static HSSFFont fontBold = null;
	
	private static LogicaReportesToExcel logica = null;
	
	public static LogicaReportesToExcel getInstance(){
		
		if(logica == null){
			logica = new LogicaReportesToExcel();
		}
		
		return logica;
	}
	
	public LogicaReportesToExcel(){
		
	}
	
	public static HSSFFont getFontBold(HSSFWorkbook wb){		
		if(fontBold == null){
			fontBold = wb.createFont();
			fontBold.setColor(HSSFFont.BOLDWEIGHT_BOLD);
			fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
		return fontBold;
	}
	
	/**
	 * Método que recibe toda la información necesaria para generar el reporte, procesa el listado de 
	 * los nombres de las columnas y los datos de la cabecera  y llama al método que genera el reporte
	 * @param encabezados
	 * @param datosCabecera
	 * @param datosReporte
	 * @param reporteExcel
	 * @throws Exception
	 */
	
	public void generacionReportesExcel(String nombreColumnas, String datosCabecera, List<Object> datosReporte,ReporteExcel reporteExcel)throws Exception { 
		
		String[] nomColumnasExcel=null;
		String[] DatosFilaCabecera =null;
		
		try {
			// Se setean los nombres de las columnas
			nomColumnasExcel = ManejoDeCadenas.obtenerArregloTokens(nombreColumnas, ",");
			ArrayList<DatoColumna> listaColumnas = new ArrayList<DatoColumna>();
			for (int i = 0; i < nomColumnasExcel.length; i++) {
				DatoColumna datoColumna = new DatoColumna();
				datoColumna.setTituloColumna(nomColumnasExcel[i]);
				listaColumnas.add(datoColumna);
			}
			reporteExcel.setListaDatoColumnas(listaColumnas);
			listaColumnas=null;
			// Se setean los datos de la cabecera
			if (datosCabecera!=null && datosCabecera != "") { 
				String[] listDatosCabecera = ManejoDeCadenas.obtenerArregloTokens(datosCabecera, ",");
				ArrayList<DatoCabecera> listaCabecera = new ArrayList<DatoCabecera>();
				for (int i = 0; i < listDatosCabecera.length; i++) {
					DatosFilaCabecera = ManejoDeCadenas.obtenerArregloTokens(listDatosCabecera[i], "-");
					DatoCabecera datoCabecera = new DatoCabecera();
					datoCabecera.setNombreDato(DatosFilaCabecera[0]);
					datoCabecera.setValorDato(DatosFilaCabecera[1]);
					listaCabecera.add(datoCabecera);
				}
				reporteExcel.setListaDatosCabecera(listaCabecera);
				listDatosCabecera=null;
				DatosFilaCabecera=null;
			}
			
			// Se genera el reporte en Excel
			creacionArchivo(reporteExcel,datosReporte);
			nomColumnasExcel=null;

		}catch (Exception e) {
			UtilAcceso.printStackTrace(e, true);
	        throw e;
	    }
		finally {
			nomColumnasExcel=null;
			DatosFilaCabecera=null;
		}
	
	}
	
	public ByteArrayOutputStream generacionReportesExcel(String nombreColumnas, String datosCabecera, List<Object> datosReporte,ReporteExcel reporteExcel,boolean flujoBytes)throws Exception { 
		ByteArrayOutputStream bytearrayoutputstreamDocDef =  new ByteArrayOutputStream();
		String[] nomColumnasExcel=null;
		String[] DatosFilaCabecera =null;
		
		try {
			// Se setean los nombres de las columnas
			nomColumnasExcel = ManejoDeCadenas.obtenerArregloTokens(nombreColumnas, ",");
			ArrayList<DatoColumna> listaColumnas = new ArrayList<DatoColumna>();
			for (int i = 0; i < nomColumnasExcel.length; i++) {
				DatoColumna datoColumna = new DatoColumna();
				datoColumna.setTituloColumna(nomColumnasExcel[i]);
				listaColumnas.add(datoColumna);
			}
			reporteExcel.setListaDatoColumnas(listaColumnas);
			listaColumnas=null;
			// Se setean los datos de la cabecera
			if (datosCabecera!=null) { 
				String[] listDatosCabecera = ManejoDeCadenas.obtenerArregloTokens(datosCabecera, ",");
				ArrayList<DatoCabecera> listaCabecera = new ArrayList<DatoCabecera>();
				for (int i = 0; i < listDatosCabecera.length; i++) {
					DatosFilaCabecera = ManejoDeCadenas.obtenerArregloTokens(listDatosCabecera[i], "-");
					DatoCabecera datoCabecera = new DatoCabecera();
					datoCabecera.setNombreDato(DatosFilaCabecera[0]);
					datoCabecera.setValorDato(DatosFilaCabecera[1]);
					listaCabecera.add(datoCabecera);
				}
				reporteExcel.setListaDatosCabecera(listaCabecera);
				listDatosCabecera=null;
				DatosFilaCabecera=null;
			}
			
			// Se genera el reporte en Excel
			bytearrayoutputstreamDocDef=creacionArchivo(reporteExcel,datosReporte,flujoBytes);
			nomColumnasExcel=null;

		}catch (Exception e) {
	        throw e;
	    }
		finally {
			nomColumnasExcel=null;
			DatosFilaCabecera=null;
		}
		
		return bytearrayoutputstreamDocDef;
		
	}	
	
	/**
	 * Método que genera el reporte en excel a partir de los datos básicos del reporte y de la 
	 * lista de datos ingresada (la información de las columnas de datos)
	 * @param reporteExcel
	 * @param listaDatos
	 * @throws Exception
	 */		
	public void creacionArchivo(ReporteExcel reporteExcel,List<Object>listaDatos ) throws Exception { 

		int posFila =0;
		int numHojas=1;
		short general = HSSFCellStyle.ALIGN_GENERAL;
	    short centrado = HSSFCellStyle.ALIGN_CENTER;

		// Se crea el archivo de salida
		HSSFWorkbook objWB = new HSSFWorkbook();
		
		FileOutputStream archivoSalida = new FileOutputStream(reporteExcel.getNombreArchivo());
			
		// Se crea la hoja de trabajo
		HSSFSheet hoja1 = objWB.createSheet(reporteExcel.getNombreHoja()+"_"+numHojas);
		
		
		//hoja1.setColumnWidth((short)0,(short)700);
		// Se crea la primera fila de trabajo
		HSSFRow fila = hoja1.createRow((short)1);
		// Se crea la fila donde irá el título del reporte
		HSSFCell celda = fila.createCell(0);
		
		
	
		HSSFCellStyle cellStyle = objWB.createCellStyle();
		cellStyle.setAlignment(general);
		celda.setCellStyle(cellStyle);
		
		cellStyle.setFont(getFontBold(objWB));
		
		createCell(objWB, fila, 0, general,reporteExcel.getTituloReporte(),2,true,false);
	
		posFila=3;
		
		// Se genera la cabecera del reporte
		for (int i = 0; i < reporteExcel.getListaDatosCabecera().size(); i++) {
			fila = hoja1.createRow(posFila);
			createCell(objWB, fila, 0, general,reporteExcel.getListaDatosCabecera().get(i).getNombreDato(),2,false,false);
			createCell(objWB, fila, 1, general,reporteExcel.getListaDatosCabecera().get(i).getValorDato(),2,false,false);
			posFila=posFila+1;
		}
		
		posFila=posFila+1;
		
		fila = hoja1.createRow(posFila);
		// Se generan los títulos de las columnas
		for (int i = 0; i < reporteExcel.getListaDatoColumnas().size(); i++) {
			hoja1.setColumnWidth((int)i,(int)10000 );
			createCell(objWB, fila, i, centrado,reporteExcel.getListaDatoColumnas().get(i).getTituloColumna(),2,true,false);
		}
		
		posFila=posFila+1;

		for (Object registroA : listaDatos) {		
			try{
				//Se itera sobre la cantidad de registros encontrados
				
				// Si las columnas de información llegan a 65500 se genera una nueva hoja
				if(fila.getRowNum()>65500 || fila.getRowNum()<0) {
					numHojas=numHojas+1;
					// Se crea una nueva hoja de trabajo
					hoja1 = objWB.createSheet(reporteExcel.getNombreHoja()+"_"+numHojas);	
					posFila=1;
					// Se generan los títulos de las columnas para la siguiente hoja
					for (int i = 0; i < reporteExcel.getListaDatoColumnas().size(); i++) {
						//fila = hoja1.createRow((short)posFila);
						fila=getRow(posFila, hoja1);
						createCell(objWB, fila, i, centrado,reporteExcel.getListaDatoColumnas().get(i).getTituloColumna(),2,false,true);
					}
					posFila=posFila+1;
				}
				
				//Object registroA = (Object) iterator.next();
				Object[] registro = null;
				if(registroA.getClass().isArray()) {
					registro = (Object[])registroA;
				}
				
				fila=getRow(posFila, hoja1);
				
				// Se ejecuta un ciclo hasta el número de datos que llegan por registro
				for (int i = 0; i < registro.length; i++) {

					int tipoDato=0;
	
				    if (registro[i]!=null){	 
						// Se verifica que tipo de información llega por cada dato del registro
						if (registro[i] instanceof Long) 
							tipoDato=1;
						else if (registro[i] instanceof String)	
							tipoDato=2;
						else if (registro[i] instanceof java.sql.Date)	
							tipoDato=3;
				    }else
					    registro[i]="";
					
					// Se crea la celda en excel con la información obtenida
					createCell(objWB, fila, i, centrado, registro[i].toString(),tipoDato,false,false);
				}
				
				posFila=posFila+1;
			}catch (Exception e) {
				System.out.println("Error en la lectura de información de listaDatos: "+e);
				UtilAcceso.printStackTrace(e, true);
			}					
		}

		posFila=posFila+1;
		// Primera nota al pié de página
		if (reporteExcel.getNotaPie1()!=null){
			posFila=posFila+1;
			// Se crea la primera fila de trabajo
		    //fila = hoja1.createRow((short)posFila);
		    fila=getRow(posFila, hoja1);
			createCell(objWB, fila, 0, general,reporteExcel.getNotaPie1(),2,false,false);
		}
		posFila=posFila+1;
		// Segunda nota al pié de página
		if (reporteExcel.getNotaPie2()!=null){
			// Se crea la primera fila de trabajo
		    fila=getRow(posFila, hoja1);
			createCell(objWB, fila, 0, general,reporteExcel.getNotaPie2(),2,false,false);
		}
		
		// Escribir y cerrar libro
		objWB.write(archivoSalida);
		archivoSalida.close();
		System.out.println( "Se acaba de crear el archivo "+reporteExcel.getNombreArchivo());

	}
	
	/**
	 * Método que genera el reporte en excel a partir de los datos básicos del reporte y de la 
	 * lista de datos ingresada (la información de las columnas de datos)
	 * @param reporteExcel
	 * @param listaDatos
	 * @throws Exception
	 */		
	public ByteArrayOutputStream creacionArchivo(ReporteExcel reporteExcel,List<Object>listaDatos,boolean flujoBytes) throws Exception { 
		ByteArrayOutputStream bytearrayoutputstreamDocDef =  new ByteArrayOutputStream();
		int posFila =0;
		int numHojas=1;
		short general = HSSFCellStyle.ALIGN_GENERAL;
		short centrado = HSSFCellStyle.ALIGN_CENTER;

		// Se crea el archivo de salida
		HSSFWorkbook objWB = new HSSFWorkbook();
		
		//FileOutputStream archivoSalida = new FileOutputStream(reporteExcel.getNombreArchivo());
			
		// Se crea la hoja de trabajo
		HSSFSheet hoja1 = objWB.createSheet(reporteExcel.getNombreHoja()+"_"+numHojas);
		
		
		//hoja1.setColumnWidth((short)0,(short)700);
		// Se crea la primera fila de trabajo
		HSSFRow fila = hoja1.createRow((short)1);
		// Se crea la fila donde irá el título del reporte
		HSSFCell celda = fila.createCell(0);
		
		

		HSSFCellStyle cellStyle = objWB.createCellStyle();
		cellStyle.setAlignment(general);
		celda.setCellStyle(cellStyle);
		
		cellStyle.setFont(getFontBold(objWB));
		
		
		
		createCell(objWB, fila, 0, general,reporteExcel.getTituloReporte(),2,true,false);
	
		posFila=3;
		
		// Se genera la cabecera del reporte
		for (int i = 0; i < reporteExcel.getListaDatosCabecera().size(); i++) {
			fila = hoja1.createRow(posFila);
			createCell(objWB, fila, 0, general,reporteExcel.getListaDatosCabecera().get(i).getNombreDato(),2,false,false);
			createCell(objWB, fila, 1, general,reporteExcel.getListaDatosCabecera().get(i).getValorDato(),2,false,false);
			posFila=posFila+1;
		}
		
		posFila=posFila+1;
		
		fila = hoja1.createRow(posFila);
		// Se generan los títulos de las columnas
		for (int i = 0; i < reporteExcel.getListaDatoColumnas().size(); i++) {
			hoja1.setColumnWidth((int)i,(int)10000 );
			createCell(objWB, fila, i, centrado,reporteExcel.getListaDatoColumnas().get(i).getTituloColumna(),2,true,false);
		}
		
		posFila=posFila+1;

		
		try{
			System.out.println("*********************lista de datos clinton "+
					listaDatos ==null? 0: listaDatos.size());
			//Se itera sobre la cantidad de registros encontrados
			for (Iterator<Object> iterator = listaDatos.iterator(); iterator.hasNext();) {
				
				// Si las columnas de información llegan a 65500 se genera una nueva hoja
				if(fila.getRowNum()>65500 || fila.getRowNum()<0) {
					numHojas=numHojas+1;
					// Se crea una nueva hoja de trabajo
					hoja1 = objWB.createSheet(reporteExcel.getNombreHoja()+"_"+numHojas);	
					posFila=1;
					// Se generan los títulos de las columnas para la siguiente hoja
					for (int i = 0; i < reporteExcel.getListaDatoColumnas().size(); i++) {
						//fila = hoja1.createRow((short)posFila);
						fila=getRow(posFila, hoja1);
						createCell(objWB, fila, i, centrado,reporteExcel.getListaDatoColumnas().get(i).getTituloColumna(),2,false,true);
					}
					posFila=posFila+1;
				}
				
				Object registroA = (Object) iterator.next();
				Object[] registro = null;
				if(registroA.getClass().isArray()) {
					registro = (Object[])registroA;
				}
				
				fila=getRow(posFila, hoja1);
				
				// Se ejecuta un ciclo hasta el número de datos que llegan por registro
				for (int i = 0; i < registro.length; i++) {

					int tipoDato=0;
	
				    if (registro[i]!=null){	 
						// Se verifica que tipo de información llega por cada dato del registro
						if (registro[i] instanceof Long) 
							tipoDato=1;
						else if (registro[i] instanceof String)	
							tipoDato=2;
						else if (registro[i] instanceof java.sql.Date)	
							tipoDato=3;
				    }else
					    registro[i]="";
					
					// Se crea la celda en excel con la información obtenida
					createCell(objWB, fila, i, centrado, registro[i].toString(),tipoDato,false,false);
				}
				
				posFila=posFila+1;
					
			}
		}catch (Exception e) {			
			System.out.println("Error en la lectura de información de listaDatos: "+e);
			UtilAcceso.printStackTrace(e, true);
		}

		posFila=posFila+1;
		// Primera nota al pié de página
		if (reporteExcel.getNotaPie1()!=null){
			posFila=posFila+1;
			// Se crea la primera fila de trabajo
		    //fila = hoja1.createRow((short)posFila);
		    fila=getRow(posFila, hoja1);
			createCell(objWB, fila, 0, general,reporteExcel.getNotaPie1(),2,false,false);
		}
		posFila=posFila+1;
		// Segunda nota al pié de página
		if (reporteExcel.getNotaPie2()!=null){
			// Se crea la primera fila de trabajo
		    fila=getRow(posFila, hoja1);
			createCell(objWB, fila, 0, general,reporteExcel.getNotaPie2(),2,false,false);
		}
		
		// Escribir y cerrar libro
		objWB.write(bytearrayoutputstreamDocDef);
		//archivoSalida.close();
		System.out.println( "Se acaba de crear el archivo "+reporteExcel.getNombreArchivo());
		
		return bytearrayoutputstreamDocDef;
	}	
 
	/**
	 * Solución al Bug
	 * @param rowCounter
	 * @param sheet
	 * @return
	 */

	public static HSSFRow getRow(int rowCounter, HSSFSheet sheet) {

		HSSFRow row = sheet.getRow(rowCounter);
		if (row == null) {
			row = sheet.createRow(rowCounter);
		}
		return row;
	}	

	
	/**
	* Creates a cell and aligns it a certain way.
	*
	* @param wb        the workbook
	* @param row       the row to create the cell in
	* @param column    the column number to create the cell in
	* @param align     the alignment for the cell.
	*/
	private static void createCell(HSSFWorkbook wb, HSSFRow row, int column, short align, Object data, int tipoDato,boolean resaltarDato,boolean crearBorde){
		HSSFCell cell = row.createCell(column);
		
		if(align!=0 || resaltarDato){
				HSSFCellStyle cellStyle = wb.createCellStyle();
				cellStyle.setAlignment(align);
				
				if(crearBorde){
					cellStyle.setBorderBottom(CellStyle.BORDER_DOTTED);
					cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
					cellStyle.setBorderLeft(CellStyle.BORDER_DOTTED);
					cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
					cellStyle.setBorderRight(CellStyle.BORDER_DOTTED);
					cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
					cellStyle.setBorderTop(CellStyle.BORDER_DOTTED);
					cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
				}
				
			
				if(resaltarDato){				
					cellStyle.setFont(getFontBold(wb));
					cellStyle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
					cellStyle.setFillPattern(CellStyle.BIG_SPOTS);
				}
				cell.setCellStyle(cellStyle);
		}	
	//	HSSFCellStyle estiloFecha = wb.createCellStyle();
	//	estiloFecha.setDataFormat(HSSFDataFormat.getBuiltinFormat("d-mmm-yy"));
		
		switch(tipoDato) {
		case 0: // otro tipo de dato. Se trata como String
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString((String)data));
			break;
		case 1:	// Long
			cell.setCellValue(Long.parseLong((String)data));
			break;
		case 2: // String
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString((String)data));
			break;
		case 3: // Date
			HSSFCellStyle estiloFecha = wb.createCellStyle();
			estiloFecha.setDataFormat(HSSFDataFormat.getBuiltinFormat("d-mmm-yy"));
			cell.setCellValue(new Date(0));
			cell.setCellStyle( estiloFecha );
			break;
		}	
			
	}
	  	
	
}
