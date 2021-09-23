package co.com.coomeva.ele.logica;


import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

import co.com.coomeva.ele.delegado.DelegadoCabezaPlancha;
import co.com.coomeva.ele.entidades.admhabilidad.LogTransacciones;
import co.com.coomeva.ele.entidades.planchas.EleCabPlancha;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.entidades.planchas.EleLog;
import co.com.coomeva.ele.entidades.planchas.ElePlanchas;
import co.com.coomeva.ele.entidades.planchas.EleZonasFinanciero;
import co.com.coomeva.ele.modelo.AsociadoReporteDTO;
import co.com.coomeva.ele.modelo.EleAsociadoDTO;
import co.com.coomeva.ele.modelo.EleAsociadoDatosDTO;
import co.com.coomeva.ele.modelo.EleNovedadDTO;
import co.com.coomeva.ele.modelo.ElePlanchaDTO;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.modelo.EleSuplentesDTO;
import co.com.coomeva.ele.modelo.ResumenHabilidadDTO;
import co.com.coomeva.ele.modelo.ResumenNovedadesDTO;
import co.com.coomeva.ele.modelo.ResumenZonaHabilidadDTO;
import co.com.coomeva.ele.modelo.ResumenZonasNovedadesDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.coomeva.util.date.ManipulacionFechas;
import co.com.coomeva.util.resources.LoaderResourceElements;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;


public class LogicaReportes {

	private static LogicaReportes instance;
	private LoaderResourceElements loaderResourceElements = LoaderResourceElements.getInstance();

	//Constructor de la clase
	private LogicaReportes() {
	}

	//Patròn Singular
	public static LogicaReportes getInstance() {
		if (instance == null) {
			instance = new LogicaReportes();
		}
		return instance;
	}
	/**
	 * Genera el pdf de Asociados
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param listaAsociados
	 * @param pathServidor
	 * @return OutputStream
	 * @throws Exception
	 */
	public OutputStream generarAsociados(List<AsociadoReporteDTO> listaAsociados,String pathServidor) throws Exception {
		Document reporteAsociado = null;
		ByteArrayOutputStream file = null;
		HeaderFooter header = null;
		try {
			file = new ByteArrayOutputStream();
			reporteAsociado = new Document(PageSize.LETTER.rotate());
			PdfWriter.getInstance(reporteAsociado, file);

			header = setCabecera(pathServidor);


			reporteAsociado.setHeader(header);


			reporteAsociado.open();
			reporteAsociado.addTitle("Reporte de Asociados");

			Font font = new Font();
			font.setColor(Color.white);


			float[] colsWidth = {15f,57f,30f,15f};
			PdfPTable table = new PdfPTable(colsWidth);

			PdfPCell cell =	new PdfPCell();
			Color HdColor = Color.decode("#008000");
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrHabCedula"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrHabNombre"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrHabZona"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrHabRegional"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);

			for (AsociadoReporteDTO asociado : listaAsociados) {
				table.addCell(asociado.getNitcli().toString());
				table.addCell(asociado.getNomCli());
				table.addCell(asociado.getNomZona());
				table.addCell(asociado.getRegional());

			}
			table.setHeaderRows(1);

			reporteAsociado.add(table);

		} catch (Exception e) {  
			System.err.println(e.getMessage());  
		}

		reporteAsociado.close(); 

		return file;
	}
	/**
	 * Pone la cabecera de los documentos
	 * @param pathServidor
	 * @return HeaderFooter
	 * @throws BadElementException
	 * @throws MalformedURLException
	 * @throws IOException
	 */

	private HeaderFooter setCabecera(String pathServidor)
	throws BadElementException, MalformedURLException, IOException {
		HeaderFooter header;
		Table th = new Table(1);
		String ubiImagen = pathServidor+"//logo_coomeva.gif";
		Image jpg = Image.getInstance(ubiImagen);
		jpg.scalePercent(70.0f);
		Cell ch = new Cell();
		ch.setBorder(0);
		ch.setVerticalAlignment(Element.ALIGN_TOP);
		ch.setHorizontalAlignment(Element.ALIGN_LEFT);
		ch.add(jpg);
		th.addCell(ch);
		th.setBorder(0);
		Paragraph pp = new Paragraph();
		pp.add(th);
		header = new HeaderFooter(pp,false);
		header.setBorder(0);
		header.setAlignment(Element.ALIGN_TOP|Element.ALIGN_LEFT);

		return header;
	}
	/**
	 * Pone la cabecera de los documentos
	 * @param pathServidor
	 * @return HeaderFooter
	 * @throws BadElementException
	 * @throws MalformedURLException
	 * @throws IOException
	 */

	private HeaderFooter setCabeceraCuadernillo(String pathServidor)
	throws BadElementException, MalformedURLException, IOException {
		HeaderFooter header;
		Table th = new Table(1);
		String ubiImagen = pathServidor+"//HeaderCabezaPDF.JPG";
		Image jpg = Image.getInstance(ubiImagen);
		jpg.scalePercent(100.0f);
		Cell ch = new Cell();
		ch.setBorder(0);
		ch.setVerticalAlignment(Element.ALIGN_TOP);
		ch.setHorizontalAlignment(Element.ALIGN_CENTER);
		ch.add(jpg);
		th.addCell(ch);
		th.setBorder(0);
		Paragraph pp = new Paragraph();
		pp.add(th);
		header = new HeaderFooter(pp,false);
		header.setBorder(0);
		header.setAlignment(Element.ALIGN_TOP|Element.ALIGN_CENTER);
		return header;
	}
	/**
	 * Genera el PDF de transacciones en habilidades
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param listaTransacciones
	 * @param pathServidor
	 * @return OutputStream
	 */

	public OutputStream generarTransacciones(
			List<LogTransacciones> listaTransacciones, String pathServidor) {
		Document reporteAsociado = null;
		ByteArrayOutputStream file = null;
		HeaderFooter header = null;
		try {
			file = new ByteArrayOutputStream();
			reporteAsociado = new Document(PageSize.LETTER.rotate());
			PdfWriter.getInstance(reporteAsociado, file);

			header = setCabecera(pathServidor);


			reporteAsociado.setHeader(header);


			reporteAsociado.open();
			reporteAsociado.addTitle("Reporte de LogTransacciones");

			Font font = new Font();
			font.setColor(Color.white);


			float[] colsWidth = {20f,20f,50f,20f,20f,20f,20f};
			PdfPTable table = new PdfPTable(colsWidth);

			PdfPCell cell =	new PdfPCell();
			Color HdColor = Color.decode("#008000");
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrNumeroLog"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrLogCedula"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrLogConcepto"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrLogFecha"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrLogHabAnt"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrLogHabAct"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrLogUs"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			for (LogTransacciones log : listaTransacciones) {
				table.addCell(log.getConsLog().toString());
				table.addCell(log.getNroIdentificacion());
				table.addCell(log.getConcpTransaccion());
				table.addCell(ManipulacionFechas.dateToString(log.getFecha(), "dd/MM/yyyy hh:mm a"));
				String antHab = "";
				if (log.getAnthabil().toString().trim().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "caracterEstadoAsociado1"))) {
					antHab =  UtilAcceso.getParametroFuenteS("parametros", "estadoAsociado1");
				}else
					antHab = UtilAcceso.getParametroFuenteS("parametros", "estadoAsociado2");
				table.addCell(antHab);
				String hab = "";
				if (log.getHabil().toString().trim().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "caracterEstadoAsociado1"))) {
					hab =  UtilAcceso.getParametroFuenteS("parametros", "estadoAsociado1");
				}else
					hab = UtilAcceso.getParametroFuenteS("parametros", "estadoAsociado2");

				table.addCell(hab);
				table.addCell(log.getUsuario());
			}
			table.setHeaderRows(1);

			reporteAsociado.add(table);

		} catch (Exception e) {  
			System.err.println(e.getMessage());  
		}

		reporteAsociado.close(); 

		return file;
	}
	/**
	 * Genera el PDF de transacciones de Plancha
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param listaTransacciones
	 * @param pathServidor
	 * @return OutputStream
	 */

	public OutputStream generarTransaccionesPlanLog(
			List<EleLog> listaTransacciones, String pathServidor, String nombreCompleto) {
		Document reporteAsociado = null;
		ByteArrayOutputStream file = null;
		HeaderFooter header = null;
		try {
			file = new ByteArrayOutputStream();
			reporteAsociado = new Document(PageSize.LETTER.rotate());
			PdfWriter.getInstance(reporteAsociado, file);

			header = setCabecera(pathServidor);


			reporteAsociado.setHeader(header);


			reporteAsociado.open();
			reporteAsociado.addTitle("Reporte de LogTransacciones de Planchas");

			Font font = new Font();
			font.setColor(Color.white);


			float[] colsWidth = {20f,40f,20f,30f,40f};
			PdfPTable table = new PdfPTable(colsWidth);

			PdfPCell cell =	new PdfPCell();
			Color HdColor = Color.decode("#008000");
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrLogCedula"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrLogConcepto"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrLogFecha"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrLogTipTrans"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrLogUs"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);

			int numeroTipo = UtilAcceso.getParametroFuenteI("parametros", "numeroEstadosPlancha");
			int numeroNombre = UtilAcceso.getParametroFuenteI("parametros", "numeroEstadosPlancha");
			int numeroTipoRevision = UtilAcceso.getParametroFuenteI("parametros", "numeroTipoRevision"); 

			int iCont = 1;

			for (EleLog log : listaTransacciones) 
			{
				table.addCell(log.getNroCaboPlancha());
				table.addCell(log.getDescripcion());
				table.addCell(ManipulacionFechas.dateToString(log.getFecha(), "dd/MM/yyyy hh:mm a"));
				String tipoTransaccion = log.getTipotrans();
				tipoTransaccion.trim();

				iCont = 1;

				for (int i = 0; i < numeroTipo; i++) 
				{
					String compareToType = UtilAcceso.getParametroFuenteS("parametros", "tipoTransaccion"+iCont);

					if (tipoTransaccion.equalsIgnoreCase(compareToType)) 
					{
						tipoTransaccion =  UtilAcceso.getParametroFuenteS("parametros", "nombreTransaccion"+iCont);
					}
					iCont++;
				}

				iCont = 1;
				for (int j = 0; j < numeroNombre; j++) 
				{
					String compareToType2 = UtilAcceso.getParametroFuenteS("parametros", "tipoTransaccionAso"+iCont);
					if (tipoTransaccion.equalsIgnoreCase(compareToType2)) 
					{
						tipoTransaccion =  UtilAcceso.getParametroFuenteS("parametros", "nombreTransaccionAso"+iCont);
					}
					iCont++;
				}

				iCont = 1;
				for (int j = 0; j < numeroTipoRevision; j++) 
				{
					String compareToType2 = UtilAcceso.getParametroFuenteS("parametros", "tipoRevision"+iCont);
					if (tipoTransaccion.equalsIgnoreCase(compareToType2)) 
					{
						tipoTransaccion =  UtilAcceso.getParametroFuenteS("parametros", "nombreRevision"+iCont);
					}
					iCont++;
				}

				table.addCell(tipoTransaccion);

				if (log.getUsuario().equalsIgnoreCase("Asociado")) {

					String primerNombre = "";
					String segundoNombre = "";
					String primerApellido = "";
					String segundoApellido = "";

					EleCabPlancha eleCabPlancha = DelegadoCabezaPlancha.getInstance().consultarCabezaPlancha(log.getNroCaboPlancha());

					if (eleCabPlancha != null) {
						if (eleCabPlancha.getPrimerNombre() != null) {
							primerNombre = eleCabPlancha.getPrimerNombre().trim().toString();
						}
						if (eleCabPlancha.getSegundoNombre() != null) {
							segundoNombre = eleCabPlancha.getSegundoNombre().trim().toString();
						}
						if (eleCabPlancha.getPrimerApellido() != null) {
							primerApellido = eleCabPlancha.getPrimerApellido().trim().toString();
						}
						if (eleCabPlancha.getSegundoApellido() != null) {
							segundoApellido = eleCabPlancha.getSegundoApellido().trim().toString();
						}
						nombreCompleto = primerNombre+" "+segundoNombre+" "+primerApellido+" "+segundoApellido;
					}
				}else
					nombreCompleto = log.getUsuario().toString();

				table.addCell(nombreCompleto);
			}
			table.setHeaderRows(1);

			reporteAsociado.add(table);

		} catch (Exception e) {  
			System.err.println(e.getMessage());  
		}

		reporteAsociado.close(); 

		return file;
	}
	/**
	 * Genera el PDF de consulta de Planchas
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param listaPlanchas
	 * @param pathServerContextPath
	 * @return ByteArrayOutputStream
	 * @throws Exception
	 */

	public ByteArrayOutputStream generarPlanchas(
			List<ElePlanchas> listaPlanchas, String pathServerContextPath)throws Exception {
		Document reportePlancha = null;
		ByteArrayOutputStream file = null;
		HeaderFooter header = null;
		boolean flag = false;
		
		try {
			file = new ByteArrayOutputStream();
			reportePlancha = new Document(PageSize.LETTER.rotate());
			PdfWriter.getInstance(reportePlancha, file);

			header = setCabecera(pathServerContextPath);


			reportePlancha.setHeader(header);


			reportePlancha.open();
			reportePlancha.addTitle("Reporte de Planchas");

			Font font = new Font();
			font.setColor(Color.white);


			float[] colsWidth = {20f,20f,20f,60f,20f,40f};
			PdfPTable table = new PdfPTable(colsWidth);

			PdfPCell cell =	new PdfPCell();
			Color HdColor = Color.decode("#008000");
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrNroCabPlancha"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrZona"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrFechaIns"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrEstado"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrNroPlancha"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrDescEstado"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			for (ElePlanchas plancha : listaPlanchas) {
				table.addCell(plancha.getNroCabPlancha());
				table.addCell(plancha.getEleZonas().getNomZona());
				table.addCell(ManipulacionFechas.dateToString(plancha.getFechaInscripcion(), "dd/MM/yyyy hh:mm a"));

				if (plancha.getEstado().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha1"))) {
					table.addCell(UtilAcceso.getParametroFuenteS("parametros", "displayEstado1"));
					flag = true;
				}
				
				if (plancha.getEstado().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha3"))) {
					table.addCell(UtilAcceso.getParametroFuenteS("parametros", "displayEstado3"));
					flag = true;
				}
				
				if (plancha.getEstado().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha2"))) {
					table.addCell(UtilAcceso.getParametroFuenteS("parametros", "displayEstado2"));
					flag = true;
				}
				
				if (plancha.getEstado().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha7"))) {
					table.addCell(UtilAcceso.getParametroFuenteS("parametros", "displayEstado7"));
					flag = true;
				}
				
				if (plancha.getEstado().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha8"))) {
					table.addCell(UtilAcceso.getParametroFuenteS("parametros", "displayEstado8"));
					flag = true;
				}
				
				if (plancha.getEstado().equalsIgnoreCase(UtilAcceso.getParametroFuenteS("parametros", "estadoPlancha9"))) {
					table.addCell(UtilAcceso.getParametroFuenteS("parametros", "displayEstado9"));
					flag = true;
				}
				
				if (!flag) {
					table.addCell(plancha.getEstado());
				}
				flag = false;
				
				if (plancha.getNroPlancha() != null) {
					table.addCell(plancha.getNroPlancha().toString());
				}else
					table.addCell(" ");
				table.addCell(plancha.getDescEstado());
			}
			table.setHeaderRows(1);

			reportePlancha.add(table);

		} catch (Exception e) {  
			System.err.println(e.getMessage());  
		}

		reportePlancha.close(); 

		return file;
	}
	/**
	 * Genera el formato lleno de la plantilla
	 * @author Manuel Galvez y Ricardo Chiriboga
	 * @param plancha
	 * @param pathContext
	 * @return ByteArrayOutputStream
	 * @throws Exception
	 */

	public ByteArrayOutputStream generarCuadernillo(ElePlanchaDTO plancha,String pathContext) throws Exception{
		ByteArrayOutputStream fileCabezaPlancha = null;
		ByteArrayOutputStream fileSuplentesPrincipales = null;

		try {
			Document reporteAsociado = null;
			fileSuplentesPrincipales = new ByteArrayOutputStream();
			reporteAsociado = new Document(PageSize.LETTER);
//			reporteAsociado.setMargins(80, 60, 50.0f, 40.f);
			reporteAsociado.setMargins(0, 0, 50.0f, 0);
			PdfWriter.getInstance(reporteAsociado, fileSuplentesPrincipales);

			reporteAsociado.open();

			Table tableImagenSuplentes = new Table(1);
			String ubiImagenSuplentes = pathContext+"//imagenes"+"//CabeceraSuplentes.bmp";
			Image jpgSuplentes = Image.getInstance(ubiImagenSuplentes);
			jpgSuplentes.scalePercent(100.0f);
			Cell celdaImagenSuplentes = new Cell();
			celdaImagenSuplentes.setBorder(0);
			celdaImagenSuplentes.setVerticalAlignment(Element.ALIGN_CENTER);
			celdaImagenSuplentes.setHorizontalAlignment(Element.ALIGN_CENTER);
			celdaImagenSuplentes.add(jpgSuplentes);
			tableImagenSuplentes.addCell(celdaImagenSuplentes);
			tableImagenSuplentes.setBorder(0);
			reporteAsociado.add(tableImagenSuplentes);

			Font fontBold = new Font();
			fontBold.setColor(Color.decode("#006633"));
			fontBold.setStyle(Font.BOLD);
			Font font = new Font();
			font.setColor(Color.decode("#006633"));
			font.setSize(9f);
			Font fontInt = new Font();
			fontInt.setSize(9f);
			float[] colsWidthInfo = {20f,10f,15f,20f,20f,14f,12f,12f,12f,20f,20f,10f};
			PdfPTable tablaInformacion = new PdfPTable(colsWidthInfo);
			PdfPCell cell =	new PdfPCell();
			Color HdColor = Color.white;
			
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "pdfZonaNo"),font));
			cell.setBorderWidthTop(2);
			cell.setBorderColor(Color.decode("#006633"));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.disableBorderSide(Cell.RIGHT);
			tablaInformacion.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(plancha.getEleZonas().getCodZona(),fontInt));
			cell.setBorderWidthTop(2);
			cell.setBorderColor(Color.decode("#006633"));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.disableBorderSide(Cell.LEFT);
			cell.disableBorderSide(Cell.RIGHT);
			tablaInformacion.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "pdfCiudad"),font));
			cell.setBorderWidthTop(2);
			cell.setBorderColor(Color.decode("#006633"));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.disableBorderSide(Cell.RIGHT);
			tablaInformacion.addCell(cell);
			
			EleAsociadoDTO asociadoDTO = LogicaClimae.getInstance().find(plancha.getNroCabPlancha());
			EleZonasFinanciero zonaFin = LogicaZonaFinanciera.getInstance().consultarZonaFinanciero(asociadoDTO.getOficina());
			
			cell =	new PdfPCell(new Paragraph(zonaFin.getNomZonaFin(),fontInt));
			cell.setBorderWidthTop(2);
			cell.setBorderColor(Color.decode("#006633"));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.disableBorderSide(Cell.LEFT);
			cell.disableBorderSide(Cell.RIGHT);
			tablaInformacion.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "pdfFechaIns"),font));
			cell.setBorderWidthTop(2);
			cell.setBorderColor(Color.decode("#006633"));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.disableBorderSide(Cell.RIGHT);
			tablaInformacion.addCell(cell);
			
			Date fechaActual = ManipulacionFechas.getFechaActual();
			String ano = ManipulacionFechas.getAgno(fechaActual);
			String mes = ManipulacionFechas.getMes(fechaActual);
			String dia = ManipulacionFechas.getDia(fechaActual);
			String hora = ManipulacionFechas.getHora(fechaActual);
			String minutos = ManipulacionFechas.getMinutos(fechaActual);
			String segundos = ManipulacionFechas.getSegundos(fechaActual);
			
			PdfPTable pdfInte = new PdfPTable(1);
			cell =	new PdfPCell(new Paragraph(ano,fontInt));
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			pdfInte.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "pdfAno"),font));
			cell.setBorderColor(Color.decode("#006633"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_BASELINE);
			cell.disableBorderSide(Cell.TOP);
			cell.disableBorderSide(Cell.BOTTOM);
			pdfInte.addCell(cell);
			cell =  new PdfPCell();
			cell.setBorderColor(Color.decode("#006633"));
			cell.setBorderWidthTop(2);
			cell.setVerticalAlignment(Element.ALIGN_BASELINE);
			cell.disableBorderSide(Cell.LEFT);
			cell.disableBorderSide(Cell.RIGHT);
			cell.addElement(pdfInte);
			tablaInformacion.addCell(cell);
			
			pdfInte = new PdfPTable(1);
			cell =	new PdfPCell(new Paragraph(mes,fontInt));
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			pdfInte.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "pdfMes"),font));
			cell.setBorderColor(Color.decode("#006633"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_BASELINE);
			cell.disableBorderSide(Cell.TOP);
			cell.disableBorderSide(Cell.BOTTOM);
			pdfInte.addCell(cell);
			cell =  new PdfPCell();
			cell.addElement(pdfInte);
			cell.setBorderColor(Color.decode("#006633"));
			cell.setBorderWidthTop(2);
			cell.setVerticalAlignment(Element.ALIGN_BASELINE);
			cell.disableBorderSide(Cell.LEFT);
			cell.disableBorderSide(Cell.RIGHT);
			tablaInformacion.addCell(cell);
			
			pdfInte = new PdfPTable(1);
			cell =	new PdfPCell(new Paragraph(dia,fontInt));
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			pdfInte.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "pdfDia"),font));
			cell.setBorderColor(Color.decode("#006633"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_BASELINE);
			cell.disableBorderSide(Cell.TOP);
			cell.disableBorderSide(Cell.BOTTOM);
			pdfInte.addCell(cell);
			cell =  new PdfPCell();
			cell.addElement(pdfInte);
			cell.setBorderWidthTop(2);
			cell.setBorderColor(Color.decode("#006633"));
			cell.setVerticalAlignment(Element.ALIGN_BASELINE);
			cell.disableBorderSide(Cell.LEFT);
			cell.disableBorderSide(Cell.RIGHT);
			tablaInformacion.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "pdfHora"),font));
			cell.setBorderWidthTop(2);
			cell.setBorderColor(Color.decode("#006633"));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.disableBorderSide(Cell.RIGHT);
			tablaInformacion.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(hora+":"+minutos+":"+segundos,fontInt));
			cell.setBorderWidthTop(2);
			cell.setBorderColor(Color.decode("#006633"));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.disableBorderSide(Cell.LEFT);
			tablaInformacion.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "pdfPlanchaNo"),font));
			cell.setBorderWidthTop(2);
			cell.setBorderColor(Color.decode("#006633"));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.disableBorderSide(Cell.RIGHT);
			tablaInformacion.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(" ",fontInt));
			cell.setBorderWidthTop(2);
			cell.setBorderColor(Color.decode("#006633"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.disableBorderSide(Cell.LEFT);
			tablaInformacion.addCell(cell);
			
			reporteAsociado.add(tablaInformacion);
			

			float[] colsWidth = {10f,40f,30f,30f,30f};
			PdfPTable tablePrincipal = new PdfPTable(colsWidth);

			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrttlPrincipales"),fontBold));
			cell.setBackgroundColor(Color.decode("#99CCCC"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//			cell.setBorderWidthTop(2);
			cell.setColspan(5);
			cell.setBorderColor(Color.decode("#006633"));
			tablePrincipal.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrNumero"),font));
			cell.setBackgroundColor(HdColor);
			cell.setBorderColor(Color.decode("#006633"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tablePrincipal.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrNombreApellidos"),font));
			cell.setBackgroundColor(HdColor);
			cell.setBorderColor(Color.decode("#006633"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tablePrincipal.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrProfesion"),font));
			cell.setBackgroundColor(HdColor);
			cell.setBorderColor(Color.decode("#006633"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tablePrincipal.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrCedulaCiudadania"),font));
			cell.setBackgroundColor(HdColor);
			cell.setBorderColor(Color.decode("#006633"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tablePrincipal.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrFirma"),font));
			cell.setBackgroundColor(HdColor);
			cell.setBorderColor(Color.decode("#006633"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tablePrincipal.addCell(cell);



			List<ElePrincipalesDTO> listaPrincipales =  plancha.getListaPrincipales();
			int cont = 1;

			for (ElePrincipalesDTO principal : listaPrincipales) {
				cell =	new PdfPCell(new Paragraph(cont+"",fontInt));
				cell.setBorderColor(Color.decode("#006633"));
				tablePrincipal.addCell(cell);
				cell =	new PdfPCell(new Paragraph(principal.getNombreCompleto(),fontInt));
				cell.setBorderColor(Color.decode("#006633"));
				tablePrincipal.addCell(cell);
				cell =	new PdfPCell(new Paragraph(principal.getProfesion(),fontInt));
				cell.setBorderColor(Color.decode("#006633"));
				tablePrincipal.addCell(cell);
				cell =	new PdfPCell(new Paragraph(principal.getNroPriIdentificacion(),fontInt));
				cell.setBorderColor(Color.decode("#006633"));
				tablePrincipal.addCell(cell);
				cell =	new PdfPCell(new Paragraph(" "));
				cell.setBorderColor(Color.decode("#006633"));
				tablePrincipal.addCell(cell);
				cont++;
			}

			reporteAsociado.add(tablePrincipal);

			PdfPTable tableSuplentes = new PdfPTable(colsWidth);

			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrttlSuplentes"),fontBold));
			cell.setBackgroundColor(Color.decode("#99CCCC"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setColspan(5);
			cell.setBorderColor(Color.decode("#006633"));
			tableSuplentes.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrNumero"),font));
			cell.setBackgroundColor(HdColor);
			cell.setBorderColor(Color.decode("#006633"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableSuplentes.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrNombreApellidos"),font));
			cell.setBackgroundColor(HdColor);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderColor(Color.decode("#006633"));
			tableSuplentes.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrProfesion"),font));
			cell.setBackgroundColor(HdColor);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderColor(Color.decode("#006633"));
			tableSuplentes.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrCedulaCiudadania"),font));
			cell.setBackgroundColor(HdColor);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderColor(Color.decode("#006633"));
			tableSuplentes.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "hdrFirma"),font));
			cell.setBackgroundColor(HdColor);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderColor(Color.decode("#006633"));
			tableSuplentes.addCell(cell);


			List<EleSuplentesDTO> listaSuplentes =  plancha.getListaSuplentes();
			cont = 1;

			for (EleSuplentesDTO suplente : listaSuplentes) {
				cell =	new PdfPCell(new Paragraph(cont+"",fontInt));
				cell.setBorderColor(Color.decode("#006633"));
				tableSuplentes.addCell(cell);
				cell =	new PdfPCell(new Paragraph(suplente.getNombreCompleto(),fontInt));
				cell.setBorderColor(Color.decode("#006633"));
				tableSuplentes.addCell(cell);
				cell =	new PdfPCell(new Paragraph(suplente.getProfesion(),fontInt));
				cell.setBorderColor(Color.decode("#006633"));
				tableSuplentes.addCell(cell);
				cell =	new PdfPCell(new Paragraph(suplente.getNroSuIdentificacion(),fontInt));
				cell.setBorderColor(Color.decode("#006633"));
				tableSuplentes.addCell(cell);
				cell =	new PdfPCell(new Paragraph(" "));
				cell.setBorderColor(Color.decode("#006633"));
				tableSuplentes.addCell(cell);
				cont++;
			}
			reporteAsociado.add(tableSuplentes);
			
			PdfPTable tableNomIns = new PdfPTable(1);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "pdfNombreInsPlancha"),font));
			cell.setBorder(0);
			tableNomIns.addCell(cell);
			
			reporteAsociado.add(tableNomIns);
			
			tableNomIns = new PdfPTable(1);
			cell =	new PdfPCell(new Paragraph(" ",font));
			cell.setBorder(0);
			tableNomIns.addCell(cell);
			
			reporteAsociado.add(tableNomIns);
			
			float[] colWidthsFoot = {60f,30f,30f}; 
			tableNomIns = new PdfPTable(colWidthsFoot);
			cell =	new PdfPCell(new Paragraph("",font));
			cell.setBorder(0);
			tableNomIns.addCell(cell);
			cell =	new PdfPCell(new Paragraph(UtilAcceso.getParametroFuenteS("parametros", "pdfFirmaInsPlancha"),font));
			cell.setBorder(0);
			tableNomIns.addCell(cell);
			cell =	new PdfPCell(new Paragraph("",font));
			cell.setBorderColor(Color.decode("#006633"));
			cell.disableBorderSide(Cell.TOP);
			cell.disableBorderSide(Cell.LEFT);
			cell.disableBorderSide(Cell.RIGHT);
			tableNomIns.addCell(cell);
			
			
			reporteAsociado.add(tableNomIns);
			

			reporteAsociado.close();


			fileCabezaPlancha = new ByteArrayOutputStream();

			PdfReader pdfReader = new PdfReader(pathContext+"//plantilla"+"//plantillaCuadernillo.pdf");

			PdfStamper pdfStamper = new PdfStamper(pdfReader, fileCabezaPlancha);

			AcroFields form = pdfStamper.getAcroFields();
			form.setField("codZona1", plancha.getEleZonas().getCodZona());
			form.setField("nombreCompletoCabeza", plancha.getEleCabPlanchaDTO().getNombreCompleto());
			form.setField("documentoCabeza", plancha.getEleCabPlanchaDTO().getNroIdentificacion());
			form.setField("antiguedadCabeza", plancha.getEleCabPlanchaDTO().getAntiguedad()+" años");
			form.setField("profesionCabeza", plancha.getEleCabPlanchaDTO().getProfesion());
			String[] otrosEstudios = plancha.getEleCabPlanchaDTO().getOtrosEstudios().split(UtilAcceso.getParametroFuenteS("parametros", "separadorEstudios"));
			String estudios1 = otrosEstudios[0];
			String estudios2 = otrosEstudios[1];
			if (!estudios1.trim().equalsIgnoreCase("-")) {
				form.setField("otrosEstudiosCabeza1", estudios1);
			}

			if (!estudios2.trim().equalsIgnoreCase("-")) {
				form.setField("otrosEstudiosCabeza2", estudios2);
			}

			List<EleExperienciaLaboral> listaEmpresas = plancha.getListExperienciaLaboral();
			int icont = 1;
			for (EleExperienciaLaboral eleExperienciaLaboral : listaEmpresas) {
				form.setField("empresaCabeza"+icont, eleExperienciaLaboral.getEmpresa());
				form.setField("cargoCabeza"+icont, eleExperienciaLaboral.getCargo());
				icont++;
			}
			if (!plancha.getEleCabPlanchaDTO().getCargosDirectivos().equalsIgnoreCase("-")) {
				form.setField("cargosDirectivosCabeza1",plancha.getEleCabPlanchaDTO().getCargosDirectivos());
			}

			String nombreAsociado = plancha.getEleCabPlanchaDTO().getNombreCompleto();
			nombreAsociado = nombreAsociado.replace(" ", "");
			nombreAsociado = nombreAsociado.replace(" ", "");
			nombreAsociado = nombreAsociado.replace ('á','a');
			nombreAsociado = nombreAsociado.replace ('é','e');
			nombreAsociado = nombreAsociado.replace ('í','i');
			nombreAsociado = nombreAsociado.replace ('ó','o');
			nombreAsociado = nombreAsociado.replace ('ú','u');
			nombreAsociado.trim();
			String zonas = plancha.getEleZonas().getNomZona();
			zonas = zonas.replace(" ", "");
			zonas = zonas.replace ('á','a');
			zonas = zonas.replace ('é','e');
			zonas = zonas.replace ('í','i');
			zonas = zonas.replace ('ó','o');
			zonas = zonas.replace ('ú','u');
			zonas.trim();
			String numeroPlancha = "";
			
			if (plancha.getNroPlancha()!=null) {
				numeroPlancha  = plancha.getNroPlancha().toString();
			}
			
			String imagenCabezaPlancha = "";
			if (!numeroPlancha.trim().equalsIgnoreCase("")) {
				imagenCabezaPlancha = pathContext+"//"+"imagenes"+"//"+"asociados"+"//"+zonas+"_"+numeroPlancha+"_"+nombreAsociado+".jpg";
			}else
				imagenCabezaPlancha = pathContext+"//"+"imagenes"+"//"+"asociados"+"//"+zonas+"_"+nombreAsociado+".jpg";

			if (plancha.getEleCabPlanchaDTO().getRutaImagen()!= null&&!plancha.getEleCabPlanchaDTO().getRutaImagen().equalsIgnoreCase("")) {
				try {
					Image jpgCabeza = Image.getInstance(imagenCabezaPlancha);
					PdfContentByte over = pdfStamper.getOverContent(1);
					over.addImage(jpgCabeza,81f,0,0,101f,500.5f,513.5f);
				} catch (Exception e) {
				}
				
			}		

			pdfStamper.setFormFlattening(true);
			

			PdfReader read = new PdfReader(fileSuplentesPrincipales.toByteArray());

			PdfContentByte under;
			int numeroPag = read.getNumberOfPages();
			for (int i = 1; i < numeroPag+1; i++) {
				pdfStamper.insertPage(i+1, PageSize.LETTER);
				under = pdfStamper.getUnderContent(i+1);
				under.addTemplate(pdfStamper.getImportedPage(read, i),1,0);
			}
			

			pdfStamper.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());  
		}

		return fileCabezaPlancha;
	}
	
	/**
	 * Reporte asociados habiles Web
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 14/11/2012
	 * @param listaAsociados
	 * @param pathServidor
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream generarReporteAsociadosHabilesWeb(List<EleAsociadoDatosDTO> listAsociadosHabiles,String pathServidor) throws Exception {
		Document reporteAsociado = null;
		ByteArrayOutputStream file = null;
		HeaderFooter header = null;
		try {
			file = new ByteArrayOutputStream();
			reporteAsociado = new Document(PageSize.LETTER.rotate());
			PdfWriter.getInstance(reporteAsociado, file);

			header = setCabecera(pathServidor);


			reporteAsociado.setHeader(header);


			reporteAsociado.open();
			reporteAsociado.addTitle("Reporteo de Asociados Habiles");

			Font font = new Font();
			font.setColor(Color.white);


			float[] colsWidth = {15f,57f,30f,15f};
			PdfPTable table = new PdfPTable(colsWidth);

			PdfPCell cell =	new PdfPCell();
			Color HdColor = Color.decode("#008000");
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteZona"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteRegional"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteOficina"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteNumeroDocumento"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteNombreCompleto"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteCiudad"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteProfesion"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteFechaNac"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteFechaVin"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteEstrato"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteGenero"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteEmail"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteDireccion"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteTelefono"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteCelular"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteEstadoHabilidad"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);			

			for (EleAsociadoDatosDTO dto : listAsociadosHabiles) {
				table.addCell(dto.getZonaElectoral());
				table.addCell(dto.getRegional());
				table.addCell(dto.getOficina());
				table.addCell(dto.getNitcli());
				table.addCell(dto.getNombreCompleto());
				table.addCell(dto.getCiudad());
				table.addCell(dto.getProfesion());
				table.addCell(dto.getFechaNacimiento());
				table.addCell(dto.getFechaVinculacion());
				table.addCell(dto.getEstratoSocial());
				table.addCell(dto.getGenero());
				table.addCell(dto.getEmail());
				table.addCell(dto.getDireccionReside());
				table.addCell(dto.getTelefono());
				table.addCell(dto.getCelular());
				table.addCell(dto.getEstadoHabilidad());
			}
			table.setHeaderRows(1);

			reporteAsociado.add(table);

		} catch (Exception e) {  
			System.err.println(e.getMessage());  
		}

		reporteAsociado.close(); 

		return file;
	}
	
	
	/**
	 * Reporte asociados habiles PDF
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 14/11/2012
	 * @param listaAsociados
	 * @param pathServidor
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream generarReporteAsociadosHabilesInhabilesPDF(List<EleAsociadoDatosDTO> listAsociadosHabiles,String pathServidor) throws Exception {
		Document reporteAsociado = null;
		ByteArrayOutputStream file = null;
		HeaderFooter header = null;
		try {
			file = new ByteArrayOutputStream();
			reporteAsociado = new Document(PageSize.LEGAL.rotate());
			PdfWriter.getInstance(reporteAsociado, file);
			String periodo = LoaderResourceElements.getInstance().getKeyResourceValue("parametros", "periodoelectoral");
			
			header = setCabecera(pathServidor);


			reporteAsociado.setHeader(header);


			reporteAsociado.open();
			reporteAsociado.addTitle("Reporte de Asociados Habiles");

			Font font = new Font();
			font.setColor(Color.white);

			float[] colsWidth = {1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f};
			PdfPTable table = new PdfPTable(colsWidth);

			PdfPCell cell =	new PdfPCell();
			Color HdColor = Color.decode("#008000");		
			
			Integer i = 1, c = 1;
			for (EleAsociadoDatosDTO dto : listAsociadosHabiles) {
				if (c == 1) {
					table = new PdfPTable(colsWidth);
					cell =	new PdfPCell();
					
					cell =	new PdfPCell(new Paragraph("No",font));
					cell.setBackgroundColor(HdColor);
					table.addCell(cell);
					
					cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreportePeriodo"),font));
					cell.setBackgroundColor(HdColor);
					table.addCell(cell);
					
					
					cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblZona"),font));
					cell.setBackgroundColor(HdColor);
					table.addCell(cell);

					
					cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteNumeroDocumento"),font));
					cell.setBackgroundColor(HdColor);
					table.addCell(cell);
					
					cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteNombreCompleto"),font));
					cell.setBackgroundColor(HdColor);
					table.addCell(cell);
							
					cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteProfesion"),font));
					cell.setBackgroundColor(HdColor);
					table.addCell(cell);						
					
					cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteEstadoHabilidad"),font));
					cell.setBackgroundColor(HdColor);
					table.addCell(cell);
					
					cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteRegional"),font));
					cell.setBackgroundColor(HdColor);
					table.addCell(cell);
					
					cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteZona"),font));
					cell.setBackgroundColor(HdColor);
					table.addCell(cell);
					
					cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteOficina"),font));
					cell.setBackgroundColor(HdColor);
					table.addCell(cell);
				}
				table.addCell(i.toString());
				table.addCell(periodo);
				table.addCell(dto.getZonaElectoral());				
				table.addCell(dto.getNitcli());
				table.addCell(dto.getNombreCompleto());
				table.addCell(dto.getProfesion());
				table.addCell(dto.getEstadoHabilidad());
				table.addCell(dto.getRegional());
				table.addCell(dto.getCiudad());
				table.addCell(dto.getOficina());
				
				i++;
				c++;
				if (c >= 11 || (i > listAsociadosHabiles.size())) {
					c = 1;
					reporteAsociado.add(table);
					reporteAsociado.newPage();
				}
			}

		} catch (Exception e) {  
			System.err.println(e.getMessage());  
		}

		reporteAsociado.close(); 

		return file;
	}
	
	/**
	 * Metodo que genera el reporte de novedades aplicadas filtrado por
	 * novedad, zona y fecha de proceso.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 20/11/2012
	 * @param list
	 * @param pathServidor
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream generarReporteNovedadesAplicadasPDF(List<EleNovedadDTO> list, String pathServidor) throws Exception {
		Document reporteAsociado = null;
		ByteArrayOutputStream file = null;
		HeaderFooter header = null;
		try {
			file = new ByteArrayOutputStream();
			reporteAsociado = new Document(PageSize.LEGAL.rotate());
			PdfWriter.getInstance(reporteAsociado, file);

			header = setCabecera(pathServidor);


			reporteAsociado.setHeader(header);


			reporteAsociado.open();
			reporteAsociado.addTitle("Reporte de novedades aplicadas");

			Font font = new Font();
			font.setColor(Color.white);


			float[] colsWidth = {1f, 1f, 1f, 1f, 1f, 1f};
			PdfPTable table = new PdfPTable(colsWidth);

			PdfPCell cell =	new PdfPCell();
			Color HdColor = Color.decode("#008000");
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteNumeroCedula"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteNombreCompleto"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblNovedadAplicada"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblReporteFechaNovedad"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);									
			
			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblZona"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);								

			cell =	new PdfPCell(new Paragraph(loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblFechaCorte"),font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);								
		
			
			for (EleNovedadDTO dto : list) {
				table.addCell(dto.getNumeroDocumento());				
				table.addCell(dto.getNombreCompletoAsociado());
				table.addCell(dto.getNovedadAplicada());
				table.addCell(ManipulacionFechas.dateToString(dto.getFechaAplicacionNovedad()));
				table.addCell(dto.getZona());
				table.addCell(ManipulacionFechas.dateToString(dto.getFechaCorte()));
			}
			
			table.setHeaderRows(1);

			reporteAsociado.add(table);

		} catch (Exception e) {  
			System.err.println(e.getMessage());  
		}

		reporteAsociado.close(); 

		return file;
	}
	
	/**
	 * Metodo que genera el resumen de habilidades en pdf.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 26/11/2012
	 * @param list
	 * @param pathServidor
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream generarReporteResumenHabilidadesPDF(List<ResumenHabilidadDTO> list, String pathServidor) throws Exception {
		Document reporteAsociado = null;
		ByteArrayOutputStream file = null;
		HeaderFooter header = null;
		try {
			file = new ByteArrayOutputStream();
			reporteAsociado = new Document(PageSize.LEGAL.rotate());
			PdfWriter.getInstance(reporteAsociado, file);

			header = setCabecera(pathServidor);


			reporteAsociado.setHeader(header);


			reporteAsociado.open();
			reporteAsociado.addTitle("Resumen Habilidades");

			Font font = new Font();
			font.setColor(Color.white);


			float[] colsWidth = {1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f};
			PdfPTable table = new PdfPTable(colsWidth);

			PdfPCell cell =	new PdfPCell();
			Color HdColor = Color.decode("#008000");
			
			cell =	new PdfPCell(new Paragraph("Regional",font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph("Zona Electoral",font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph("Total Asociados",font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph("Suma Hábiles",font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph("% Hábiles",font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph("Suma Inhábiles",font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph("% Inhábiles",font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph("Muestra",font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph("%",font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			Double totalAsociados = 0d, totalHabiles = 0d, totalInhabiles = 0d, 
					totalPorcentajeHab = 0d, totalPorcentajeInhab = 0d, totalMuestra = 0d, totalPorcentajeMuestra = 0d;
			int c = 0;
			for (ResumenHabilidadDTO dto : list) {
				table.addCell(dto.getRegional());
				for (ResumenZonaHabilidadDTO informe : dto.getListResumen()) {
					if (c > 0){
						table.addCell(" ");
					}
					table.addCell(informe.getZona());
					table.addCell(informe.getTotalAsociadosZona().toString());
					table.addCell(informe.getTotalHabilesZona().toString());
					table.addCell(informe.getPorcentajeHabiles().toString() + "%");
					table.addCell(informe.getTotalInhabilesZona().toString());
					table.addCell(informe.getPorcentajeInhabiles().toString() + "%");
					table.addCell(informe.getMuestraZona().toString());
					table.addCell(informe.getPorcentajeMuestra().toString());
					c++;
				}
				table.addCell("Total "+dto.getRegional());
				table.addCell(" ");
				table.addCell(dto.getTotalAsociadosZona().toString());
				table.addCell(dto.getTotalHabilesZona().toString());
				table.addCell(dto.getPorcentajeHabiles().toString() + "%");
				table.addCell(dto.getTotalInhabilesZona().toString());
				table.addCell(dto.getPorcentajeInhabiles().toString() + "%");
				table.addCell(dto.getMuestraZona().toString());
				table.addCell(dto.getPorcentajeMuestra().toString());
				
				totalAsociados += dto.getTotalAsociadosZona();
				totalHabiles += dto.getTotalHabilesZona();
				totalInhabiles += dto.getTotalInhabilesZona();	
				
				c=0;
			}
			totalPorcentajeHab = (totalHabiles/totalAsociados)*100;
			totalPorcentajeHab = LogicaInformeResumen.round(totalPorcentajeHab, 2);
			totalPorcentajeInhab = (totalInhabiles/totalAsociados)*100;
			totalPorcentajeInhab = LogicaInformeResumen.round(totalPorcentajeInhab, 2);
			totalMuestra = totalInhabiles * ConstantesProperties.VALOR_PORCENTAJE_MUESTRA;
			totalPorcentajeMuestra = totalMuestra / totalInhabiles;
			totalPorcentajeMuestra = LogicaInformeResumen.round(totalPorcentajeMuestra, 2);
			
			table.addCell("Total general ");
			table.addCell(" ");
			table.addCell(totalAsociados.toString());
			table.addCell(totalHabiles.toString());
			table.addCell(totalPorcentajeHab.toString() + "%");
			table.addCell(totalInhabiles.toString());
			table.addCell(totalPorcentajeInhab.toString() + "%");
			table.addCell(totalMuestra.toString());
			table.addCell(totalPorcentajeMuestra.toString());
			
			table.setHeaderRows(1);

			reporteAsociado.add(table);

		} catch (Exception e) {  
			System.err.println(e.getMessage());  
		}

		reporteAsociado.close(); 

		return file;
	}
	
	/**
	 * Metodo que genera el resumen de novedades en pdf.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 27/11/2012
	 * @param list
	 * @param pathServidor
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream generarReporteResumenNovedadesPDF(List<ResumenNovedadesDTO> list, String pathServidor) throws Exception {
		Document reporteAsociado = null;
		ByteArrayOutputStream file = null;
		HeaderFooter header = null;
		try {
			file = new ByteArrayOutputStream();
			reporteAsociado = new Document(PageSize.LEGAL.rotate());
			PdfWriter.getInstance(reporteAsociado, file);

			header = setCabecera(pathServidor);


			reporteAsociado.setHeader(header);


			reporteAsociado.open();
			reporteAsociado.addTitle("Resumen Novedades");

			Font font = new Font();
			font.setColor(Color.white);


			float[] colsWidth = {1f, 1f, 1f, 1f, 1f};
			PdfPTable table = new PdfPTable(colsWidth);

			PdfPCell cell =	new PdfPCell();
			Color HdColor = Color.decode("#008000");
			
			cell =	new PdfPCell(new Paragraph("Fecha",font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph("Tipo Novedad",font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph("Zona",font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph("Número Novedades",font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);
			
			cell =	new PdfPCell(new Paragraph("Porcetnaje Novedades",font));
			cell.setBackgroundColor(HdColor);
			table.addCell(cell);						
			
			int c = 0;
			for (ResumenNovedadesDTO dto : list) {
				table.addCell(dto.getFechaProceso());
				table.addCell(dto.getTipoNovedad());
				for (ResumenZonasNovedadesDTO informe : dto.getList()) {
					if (c > 0){
						table.addCell(" ");
						table.addCell(" ");
					}
					table.addCell(informe.getZona());
					table.addCell(informe.getNumeroNovedades());
					table.addCell(informe.getPorcentajeNovedades());
					c++;
				}
				table.addCell(" ");
				table.addCell("Total "+dto.getTipoNovedad());
				table.addCell(" ");
				table.addCell(dto.getTotalNumeroNov());
				table.addCell(dto.getTotalPorcentajeNov());				
				c=0;
			}
			
			table.setHeaderRows(1);

			reporteAsociado.add(table);

		} catch (Exception e) {  
			System.err.println(e.getMessage());  
		}

		reporteAsociado.close(); 

		return file;
	}
	
}