package co.com.coomeva.ele.logica.generador;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import co.com.coomeva.ele.delegado.DelegadoPlanchas;
import co.com.coomeva.ele.delegado.inscripcion.plancha.DelegadoZonaElectoral;
import co.com.coomeva.ele.entidades.cuociente.EleCuocienteDelegadosZona;
import co.com.coomeva.ele.entidades.cuociente.EleCuocienteElectoral;
import co.com.coomeva.ele.entidades.cuociente.EleCuocienteRegional;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.logica.LogicaInformeResumen;
import co.com.coomeva.ele.modelo.EleAsociadoDatosDTO;
import co.com.coomeva.ele.modelo.EleNovedadDTO;
import co.com.coomeva.ele.modelo.ElePlanchaDTO;
import co.com.coomeva.ele.modelo.ElePrincipalesDTO;
import co.com.coomeva.ele.modelo.EleSuplentesDTO;
import co.com.coomeva.ele.modelo.PlanchaPorEstadoDTO;
import co.com.coomeva.ele.modelo.ResumenHabilidadDTO;
import co.com.coomeva.ele.modelo.ResumenNovedadesDTO;
import co.com.coomeva.ele.modelo.ResumenZonaHabilidadDTO;
import co.com.coomeva.ele.modelo.ResumenZonasNovedadesDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.generador.LoadProperties;
import co.com.coomeva.util.date.ManipulacionFechas;
import co.com.coomeva.util.resources.LoaderResourceElements;

public class LogicaGenerador {

	private static LogicaGenerador instance;
	private static final String REGIONAL = "REGIONAL ";
	private LoaderResourceElements loaderResourceElements = LoaderResourceElements.getInstance();

	private LogicaGenerador() {
	}

	// Patròn Singular
	public static LogicaGenerador getInstance() {
		if (instance == null) {
			instance = new LogicaGenerador();
		}
		return instance;
	}

	public ByteArrayOutputStream generarExcel() throws Exception {
		ByteArrayOutputStream file = new ByteArrayOutputStream();
		try {

			WritableWorkbook workbook = Workbook.createWorkbook(file);

			WritableSheet sheet = workbook.createSheet(
					LoadProperties.getInstance().getLoadBundlePropiedades().getProperty("nombrePestaña").toString(), 0);

			WritableFont arial10fontBlackBold = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontBlackBold.setBoldStyle(WritableFont.BOLD);
			arial10fontBlackBold.setColour(Colour.BLACK);
			WritableFont arial10fontBlack = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontBlack.setColour(Colour.BLACK);
			WritableFont arial10fontWhite = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontWhite.setColour(Colour.WHITE);
			arial10fontWhite.setBoldStyle(WritableFont.BOLD);

			CellView cellview = new CellView();
			cellview.setAutosize(true);

			sheet.setColumnView(0, cellview);

			WritableCellFormat cellhdFormatBold = new WritableCellFormat();
			cellhdFormatBold.setWrap(true);
			cellhdFormatBold.setFont(arial10fontBlackBold);
			cellhdFormatBold.setAlignment(Alignment.LEFT);
			cellhdFormatBold.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableCellFormat cellhdFormat = new WritableCellFormat();
			cellhdFormat.setWrap(true);
			arial10fontBlackBold.setColour(Colour.BLACK);
			cellhdFormat.setFont(arial10fontBlack);
			cellhdFormat.setAlignment(Alignment.LEFT);
			cellhdFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableCellFormat cellhdFormatZona = new WritableCellFormat();
			cellhdFormat.setWrap(true);
			cellhdFormatZona.setBackground(Colour.GREEN);
			cellhdFormatZona.setFont(arial10fontWhite);
			cellhdFormatZona.setAlignment(Alignment.CENTRE);
			cellhdFormatZona.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableCellFormat cellhdFormatNumero = new WritableCellFormat();
			cellhdFormat.setWrap(true);
			cellhdFormatNumero.setBackground(Colour.SEA_GREEN);
			cellhdFormatNumero.setFont(arial10fontWhite);
			cellhdFormatNumero.setAlignment(Alignment.CENTRE);
			cellhdFormatNumero.setBorder(Border.ALL, BorderLineStyle.THIN);

			List<ElePlanchaDTO> listPlanchas = DelegadoPlanchas.getInstance().consultarPlanchasReporte();

			String ttlZona = LoadProperties.getInstance().getLoadBundlePropiedades().getProperty("ttlZona");
			String ttlNumeroPlancha = LoadProperties.getInstance().getLoadBundlePropiedades()
					.getProperty("ttlNumeroPlancha");
//			String ttlDocumentoCabeza = LoadProperties.getInstance().getLoadBundlePropiedades().getProperty("ttlDocumentoCabeza");
			String ttlCargosDirectivos = LoadProperties.getInstance().getLoadBundlePropiedades()
					.getProperty("ttlCargosDirectivos");
//			String ttlOtrosEstudios = LoadProperties.getInstance().getLoadBundlePropiedades().getProperty("ttlOtrosEstudios");
			String ttlEmpresa = LoadProperties.getInstance().getLoadBundlePropiedades().getProperty("ttlEmpresa");
			String ttlCargo = LoadProperties.getInstance().getLoadBundlePropiedades().getProperty("ttlCargo");
			String ttlPrincipales = LoadProperties.getInstance().getLoadBundlePropiedades()
					.getProperty("ttlPrincipales");
			String ttlSuplentes = LoadProperties.getInstance().getLoadBundlePropiedades().getProperty("ttlSuplentes");
			String ttlNombreCompleto = LoadProperties.getInstance().getLoadBundlePropiedades()
					.getProperty("ttlNombreCompleto");
//			String ttlAntiguedad = LoadProperties.getInstance().getLoadBundlePropiedades().getProperty("ttlAntiguedad");
//			String ttlProfesion = LoadProperties.getInstance().getLoadBundlePropiedades().getProperty("ttlProfesion");

			String zonaActual = "";
			int cont = 0;
			for (ElePlanchaDTO elePlanchaDTO : listPlanchas) {
				if (!zonaActual.equalsIgnoreCase(elePlanchaDTO.getEleZonas().getCodZona())) {
					zonaActual = elePlanchaDTO.getEleZonas().getCodZona();
					sheet.addCell(new Label(0, cont + 1, ttlZona + " N° " + elePlanchaDTO.getEleZonas().getCodZona()
							+ " " + elePlanchaDTO.getEleZonas().getNomZona(), cellhdFormatZona));
					cont++;
				}
				sheet.addCell(new Label(0, cont + 1, ttlNumeroPlancha + " " + elePlanchaDTO.getNroPlancha().toString(),
						cellhdFormatNumero));
				cont++;
//				sheet.addCell(new Label(0,cont+1,ttlDocumentoCabeza,cellhdFormatBold));
//				cont++;
//				sheet.addCell(new Label(0,cont+1,elePlanchaDTO.getNroCabPlancha(),cellhdFormat));
//				cont++;
				sheet.addCell(new Label(0, cont + 1, ttlNombreCompleto, cellhdFormatBold));
				cont++;
				sheet.addCell(new Label(0, cont + 1, DelegadoPlanchas.getInstance()
						.formatearCadena(elePlanchaDTO.getEleCabPlanchaDTO().getNombreCompleto()), cellhdFormat));
				cont++;
//				sheet.addCell(new Label(0,cont+1,ttlAntiguedad,cellhdFormatBold));
//				cont++;
//				sheet.addCell(new Label(0,cont+1,elePlanchaDTO.getEleCabPlanchaDTO().getAntiguedad().toString(),cellhdFormat));
//				cont++;
				sheet.addCell(new Label(0, cont + 1, DelegadoPlanchas.getInstance()
						.formatearCadena(elePlanchaDTO.getEleCabPlanchaDTO().getProfesion().toString()), cellhdFormat));
				cont++;
				List<EleExperienciaLaboral> list = elePlanchaDTO.getListExperienciaLaboral();
				for (EleExperienciaLaboral experiencia : list) {
					sheet.addCell(new Label(0, cont + 1, ttlCargo, cellhdFormatBold));
					cont++;
					sheet.addCell(new Label(0, cont + 1,
							DelegadoPlanchas.getInstance().formatearCadena(experiencia.getCargo()), cellhdFormat));
					cont++;
					sheet.addCell(new Label(0, cont + 1, ttlEmpresa, cellhdFormatBold));
					cont++;
					sheet.addCell(new Label(0, cont + 1,
							DelegadoPlanchas.getInstance().formatearCadena(experiencia.getEmpresa()), cellhdFormat));
					cont++;
					break;
				}

				sheet.addCell(new Label(0, cont + 1, ttlCargosDirectivos, cellhdFormatBold));
				cont++;
				sheet.addCell(
						new Label(0, cont + 1,
								DelegadoPlanchas.getInstance().formatearCadena(
										elePlanchaDTO.getEleCabPlanchaDTO().getCargosDirectivos().toString()),
								cellhdFormat));
				cont++;
//				sheet.addCell(new Label(0,cont+1,ttlOtrosEstudios,cellhdFormatBold));
//				cont++;
//				String[] otrosEstudios = elePlanchaDTO.getEleCabPlanchaDTO().getOtrosEstudios().split("&");
//				sheet.addCell(new Label(0,cont+1,DelegadoPlanchas.getInstance().formatearCadena(otrosEstudios[0]),cellhdFormat));
//				cont++;
//				sheet.addCell(new Label(0,cont+1,DelegadoPlanchas.getInstance().formatearCadena(otrosEstudios[1]),cellhdFormat));
//				cont++;
				sheet.addCell(new Label(0, cont + 1, ttlPrincipales, cellhdFormatBold));
				cont++;
				List<ElePrincipalesDTO> listaPrincipales = elePlanchaDTO.getListaPrincipales();

				for (ElePrincipalesDTO elePrincipalesDTO : listaPrincipales) {
					sheet.addCell(new Label(0, cont + 1,
							DelegadoPlanchas.getInstance().formatearCadena(elePrincipalesDTO.getNombreCompleto()),
							cellhdFormat));
					cont++;
					sheet.addCell(new Label(0, cont + 1,
							DelegadoPlanchas.getInstance().formatearCadena(elePrincipalesDTO.getProfesion()),
							cellhdFormat));
					cont++;
				}
				sheet.addCell(new Label(0, cont + 1, ttlSuplentes, cellhdFormatBold));
				cont++;
				List<EleSuplentesDTO> listaSuplentes = elePlanchaDTO.getListaSuplentes();

				for (EleSuplentesDTO eleSuplentesDTO : listaSuplentes) {
					sheet.addCell(new Label(0, cont + 1,
							DelegadoPlanchas.getInstance().formatearCadena(eleSuplentesDTO.getNombreCompleto()),
							cellhdFormat));
					cont++;
					sheet.addCell(new Label(0, cont + 1,
							DelegadoPlanchas.getInstance().formatearCadena(eleSuplentesDTO.getProfesion()),
							cellhdFormat));
					cont++;
				}

			}

			workbook.write();
			workbook.close();

		} catch (Exception e) {
			throw e;
		}

		return file;

	}

	/**
	 * Metodo que genera el reporte de asociados habiles e inhabiles en excel
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 19/11/2012
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream generarReporteHabilesInhabilesExcel(List<EleAsociadoDatosDTO> listAsociadosHabiles,
			String tipoReporte) throws Exception {
		ByteArrayOutputStream file = new ByteArrayOutputStream();
		try {

			WritableWorkbook workbook = Workbook.createWorkbook(file);

			WritableFont arial10fontBlackBold = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontBlackBold.setBoldStyle(WritableFont.BOLD);
			arial10fontBlackBold.setColour(Colour.BLACK);
			WritableFont arial10fontBlack = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontBlack.setColour(Colour.BLACK);
			WritableFont arial10fontWhite = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontWhite.setColour(Colour.WHITE);
			arial10fontWhite.setBoldStyle(WritableFont.BOLD);

			CellView cellview = new CellView();
			cellview.setAutosize(true);

			WritableCellFormat cellhdFormatBold = new WritableCellFormat();
			cellhdFormatBold.setWrap(true);
			cellhdFormatBold.setFont(arial10fontBlackBold);
			cellhdFormatBold.setAlignment(Alignment.LEFT);
			cellhdFormatBold.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableCellFormat cellhdFormat = new WritableCellFormat();
			cellhdFormat.setWrap(true);
			arial10fontBlackBold.setColour(Colour.BLACK);
			cellhdFormat.setFont(arial10fontBlack);
			cellhdFormat.setAlignment(Alignment.LEFT);
			cellhdFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableCellFormat cellhdFormatZona = new WritableCellFormat();
			cellhdFormat.setWrap(true);
			cellhdFormatZona.setBackground(Colour.GREEN);
			cellhdFormatZona.setFont(arial10fontWhite);
			cellhdFormatZona.setAlignment(Alignment.CENTRE);
			cellhdFormatZona.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableCellFormat cellhdFormatNumero = new WritableCellFormat();
			cellhdFormat.setWrap(true);
			cellhdFormatNumero.setBackground(Colour.SEA_GREEN);
			cellhdFormatNumero.setFont(arial10fontWhite);
			cellhdFormatNumero.setAlignment(Alignment.CENTRE);
			cellhdFormatNumero.setBorder(Border.ALL, BorderLineStyle.THIN);

			String ttlZona = loaderResourceElements
					.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteZona");
			String ttlNumeroDocumento = loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteNumeroDocumento");
			String ttlNombreCompleto = loaderResourceElements
					.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteNombreCompleto");
			String ttlProfesion = loaderResourceElements
					.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteProfesion");
			String ttlEstadoHabilidad = loaderResourceElements.getKeyResourceValue(
					ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteEstadoHabilidad");

			WritableSheet sheet = null;
			int j = 1;
			for (EleAsociadoDatosDTO dtoList : listAsociadosHabiles) {

				sheet = workbook.createSheet("Habilidades - hoja " + j, j);
				sheet.setColumnView(0, cellview);
				int cont = 0;

				sheet.addCell(new Label(0, cont + 1, ttlZona, cellhdFormatBold));
				sheet.addCell(new Label(1, cont + 1, ttlNumeroDocumento, cellhdFormatBold));
				sheet.addCell(new Label(2, cont + 1, ttlNombreCompleto, cellhdFormatBold));
				sheet.addCell(new Label(3, cont + 1, ttlProfesion, cellhdFormatBold));
				sheet.addCell(new Label(4, cont + 1, ttlEstadoHabilidad, cellhdFormatBold));

				cont++;
				for (int i = 0; i < ConstantesProperties.NUMERO_REGISTROS_POR_HOJA_XLS; i++) {
					sheet.addCell(new Label(0, cont + 1, dtoList.getZonaElectoral(), cellhdFormat));
					sheet.addCell(new Label(1, cont + 1, dtoList.getNitcli(), cellhdFormat));
					sheet.addCell(new Label(2, cont + 1, dtoList.getNombreCompleto(), cellhdFormat));
					sheet.addCell(new Label(3, cont + 1, dtoList.getProfesion(), cellhdFormat));
					sheet.addCell(new Label(4, cont + 1, dtoList.getEstadoHabilidad(), cellhdFormat));

					cont++;
				}
				workbook.write();
				j++;
			}

			workbook.close();

		} catch (Exception e) {
			throw e;
		}

		return file;

	}

	/**
	 * Metodo que genera el reporte en excel de las novedades aplicadas filtradas
	 * por novedad, zona y fecha proceso.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 20/11/2012
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream generarReporteNovedadesExcel(List<EleNovedadDTO> list) throws Exception {
		ByteArrayOutputStream file = new ByteArrayOutputStream();
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(file);

			WritableFont arial10fontBlackBold = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontBlackBold.setBoldStyle(WritableFont.BOLD);
			arial10fontBlackBold.setColour(Colour.WHITE);
			WritableFont arial10fontBlack = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontBlack.setColour(Colour.BLACK);
			WritableFont arial10fontWhite = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontWhite.setColour(Colour.WHITE);
			arial10fontWhite.setBoldStyle(WritableFont.BOLD);

			WritableCellFormat cellhdFormatBold = new WritableCellFormat();
			cellhdFormatBold.setWrap(true);
			cellhdFormatBold.setFont(arial10fontBlackBold);
			cellhdFormatBold.setAlignment(Alignment.CENTRE);
			cellhdFormatBold.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellhdFormatBold.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellhdFormatBold.setBackground(Colour.GREEN);

			WritableCellFormat cellhdFormat = new WritableCellFormat();
			cellhdFormat.setWrap(true);
			cellhdFormat.setFont(arial10fontBlack);
			cellhdFormat.setAlignment(Alignment.CENTRE);
			cellhdFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellhdFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableCellFormat cellhdFormatZona = new WritableCellFormat();
			cellhdFormat.setWrap(true);
			cellhdFormatZona.setBackground(Colour.GREEN);
			cellhdFormatZona.setFont(arial10fontWhite);
			cellhdFormatZona.setAlignment(Alignment.CENTRE);
			cellhdFormatZona.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableCellFormat cellhdFormatNumero = new WritableCellFormat();
			cellhdFormat.setWrap(true);
			cellhdFormatNumero.setBackground(Colour.SEA_GREEN);
			cellhdFormatNumero.setFont(arial10fontWhite);
			cellhdFormatNumero.setAlignment(Alignment.CENTRE);
			cellhdFormatNumero.setBorder(Border.ALL, BorderLineStyle.THIN);

			String ttlNumeroDocumento = loaderResourceElements
					.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteNumeroCedula");
			String ttlNombreCompleto = loaderResourceElements
					.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteNombreCompleto");
			String ttlNovedadAplicada = loaderResourceElements
					.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblNovedadAplicada");
			String ttlFechaProceso = loaderResourceElements
					.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblReporteFechaNovedad");
			String ttlZona = loaderResourceElements
					.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblZonaElectoral");
			// String ttlFechaCorte =
			// loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
			// "lblFechaCorte");
			String ttlRegional = loaderResourceElements
					.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblRegional");
			String ttlTitulo = "INFORME NOVEDADES APLICADAS";

			WritableSheet sheet = null;

			sheet = workbook.createSheet("Novedades Aplicadas", 0);
			sheet.setColumnView(0, 20);
			sheet.setColumnView(1, 30);
			sheet.setColumnView(2, 20);
			sheet.setColumnView(3, 20);
			sheet.setColumnView(4, 20);
			sheet.setColumnView(5, 20);
			int cont = 1;

			sheet.addCell(new Label(0, 0, ttlTitulo, cellhdFormatBold));
			sheet.mergeCells(0, 0, 5, cont);

			sheet.addCell(new Label(0, cont + 1, ttlNumeroDocumento, cellhdFormatBold));
			sheet.addCell(new Label(1, cont + 1, ttlNombreCompleto, cellhdFormatBold));
			sheet.addCell(new Label(2, cont + 1, ttlNovedadAplicada, cellhdFormatBold));
			sheet.addCell(new Label(3, cont + 1, ttlFechaProceso, cellhdFormatBold));
			sheet.addCell(new Label(4, cont + 1, ttlRegional, cellhdFormatBold));
			sheet.addCell(new Label(5, cont + 1, ttlZona, cellhdFormatBold));

			cont++;

			for (EleNovedadDTO dtoList : list) {
				sheet.addCell(new Label(0, cont + 1, dtoList.getNumeroDocumento(), cellhdFormat));
				sheet.addCell(new Label(1, cont + 1, dtoList.getNombreCompletoAsociado(), cellhdFormat));
				sheet.addCell(new Label(2, cont + 1, dtoList.getNovedadAplicada(), cellhdFormat));
				sheet.addCell(new Label(3, cont + 1,
						ManipulacionFechas.dateToString(dtoList.getFechaAplicacionNovedad()), cellhdFormat));
				sheet.addCell(new Label(4, cont + 1, dtoList.getZona(), cellhdFormat));
				sheet.addCell(new Label(5, cont + 1, dtoList.getRegional(), cellhdFormat));
				// sheet.addCell(new
				// Label(5,cont+1,ManipulacionFechas.dateToString(dtoList.getFechaCorte()),cellhdFormat));
				cont++;
			}

			workbook.write();
			workbook.close();

		} catch (Exception e) {
			throw e;
		}

		return file;

	}

	/**
	 * Metodo que genera el resumen de habilidades en excel.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 26/11/2012
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream generarResumenHabilidad(List<ResumenHabilidadDTO> list) throws Exception {
		ByteArrayOutputStream file = new ByteArrayOutputStream();
		try {

			WritableWorkbook workbook = Workbook.createWorkbook(file);

			WritableFont arial10fontBlackBold = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontBlackBold.setBoldStyle(WritableFont.BOLD);
			arial10fontBlackBold.setColour(Colour.WHITE);
			WritableFont arial10fontBlack = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontBlack.setColour(Colour.BLACK);
			WritableFont arial10fontWhite = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontWhite.setColour(Colour.WHITE);
			arial10fontWhite.setBoldStyle(WritableFont.BOLD);

			WritableCellFormat cellhdFormatBold = new WritableCellFormat();
			cellhdFormatBold.setWrap(true);
			cellhdFormatBold.setFont(arial10fontBlackBold);
			cellhdFormatBold.setAlignment(Alignment.CENTRE);
			cellhdFormatBold.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellhdFormatBold.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellhdFormatBold.setBackground(Colour.GREEN);

			WritableCellFormat cellhdFormatBoldLeft = new WritableCellFormat();
			cellhdFormatBoldLeft.setWrap(true);
			cellhdFormatBoldLeft.setFont(arial10fontBlackBold);
			cellhdFormatBoldLeft.setAlignment(Alignment.LEFT);
			cellhdFormatBoldLeft.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellhdFormatBoldLeft.setBackground(Colour.GREEN);

			WritableCellFormat cellhdFormatBoldRight = new WritableCellFormat();
			cellhdFormatBoldRight.setWrap(true);
			cellhdFormatBoldRight.setFont(arial10fontBlackBold);
			cellhdFormatBoldRight.setAlignment(Alignment.RIGHT);
			cellhdFormatBoldRight.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellhdFormatBoldRight.setBackground(Colour.GREEN);

			WritableCellFormat cellhdFormat = new WritableCellFormat();
			cellhdFormat.setWrap(true);
			cellhdFormat.setFont(arial10fontBlack);
			cellhdFormat.setAlignment(Alignment.RIGHT);
			cellhdFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableCellFormat cellhdFormatCenter = new WritableCellFormat();
			cellhdFormatCenter.setWrap(true);
			cellhdFormatCenter.setFont(arial10fontBlack);
			cellhdFormatCenter.setAlignment(Alignment.CENTRE);
			cellhdFormatCenter.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableCellFormat cellhdFormatLeft = new WritableCellFormat();
			cellhdFormatLeft.setWrap(true);
			cellhdFormatLeft.setFont(arial10fontBlack);
			cellhdFormatLeft.setAlignment(Alignment.LEFT);
			cellhdFormatLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableCellFormat cellhdFormatZona = new WritableCellFormat();
			cellhdFormatZona.setWrap(true);
			cellhdFormatZona.setBackground(Colour.GREEN);
			cellhdFormatZona.setFont(arial10fontWhite);
			cellhdFormatZona.setAlignment(Alignment.CENTRE);
			cellhdFormatZona.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableCellFormat cellhdFormatNumero = new WritableCellFormat();
			cellhdFormat.setWrap(true);
			cellhdFormatNumero.setBackground(Colour.SEA_GREEN);
			cellhdFormatNumero.setFont(arial10fontWhite);
			cellhdFormatNumero.setAlignment(Alignment.CENTRE);
			cellhdFormatNumero.setBorder(Border.ALL, BorderLineStyle.THIN);

			String ttlRegional = "Regional";
			String ttlZona = "Zona Electoral";
			String ttlTotalAsociados = "Total Asociados";
			String ttlTotalHabiles = "Suma Hábiles";
			String ttlPorcentajeHabiles = "% Hábiles";
			String ttlTotalInhabiles = "Suma Inhábiles";
			String ttlPorcentajeInhabiles = "% Inhábiles";
			String ttlmuestra = "Muestra Inhábiles";
			String porcentajeMuestra = "%";
			String ttlTitulo = "RESUMEN DE HABILIDAD DE ASOCIADOS Y REVISIÓN ALEATORIA DE INHÁBILES";

			WritableSheet sheet = workbook.createSheet("Resumen de Habilidades", 0);
			sheet.setColumnView(0, 20);
			sheet.setColumnView(1, 30);
			sheet.setColumnView(2, 10);
			int cont = 2;

			for (int i = 0; i <= cont; i++) {

				sheet.addCell(new Label(0, cont, "", cellhdFormatBold));
				sheet.addCell(new Label(1, cont, "", cellhdFormatBold));
				sheet.addCell(new Label(2, cont, "", cellhdFormatBold));
				sheet.addCell(new Label(3, cont, "", cellhdFormatBold));
				sheet.addCell(new Label(4, cont, "", cellhdFormatBold));
				sheet.addCell(new Label(5, cont, "", cellhdFormatBold));
				sheet.addCell(new Label(6, cont, "", cellhdFormatBold));
				sheet.addCell(new Label(7, cont, "", cellhdFormatBold));
				sheet.addCell(new Label(8, cont, "", cellhdFormatBold));
			}
			sheet.addCell(new Label(0, 0, ttlTitulo, cellhdFormatBold));
			sheet.mergeCells(0, 0, 8, cont);

			sheet.addCell(new Label(0, cont + 1, ttlRegional, cellhdFormatBold));
			sheet.addCell(new Label(1, cont + 1, ttlZona, cellhdFormatBold));
			sheet.addCell(new Label(2, cont + 1, ttlTotalAsociados, cellhdFormatBold));
			sheet.addCell(new Label(3, cont + 1, ttlTotalHabiles, cellhdFormatBold));
			sheet.addCell(new Label(4, cont + 1, ttlPorcentajeHabiles, cellhdFormatBold));
			sheet.addCell(new Label(5, cont + 1, ttlTotalInhabiles, cellhdFormatBold));
			sheet.addCell(new Label(6, cont + 1, ttlPorcentajeInhabiles, cellhdFormatBold));
			sheet.addCell(new Label(7, cont + 1, ttlmuestra, cellhdFormatBold));
			sheet.addCell(new Label(8, cont + 1, porcentajeMuestra, cellhdFormatBold));
			cont++;

			Double totalAsociados = 0d;
			Double totalHabiles = 0d;
			Double totalInhabiles = 0d;
			Double totalPorcentajeHab = 0d;
			Double totalPorcentajeInhab = 0d;
			Double totalMuestra = 0d;
			Double totalPorcentajeMuestra = 0d;

			for (ResumenHabilidadDTO dto : list) {
				sheet.addCell(new Label(0, cont + 1, dto.getRegional(), cellhdFormatLeft));
				// Agrego el '%' en las columnas de porcentajes
				for (ResumenZonaHabilidadDTO informe : dto.getListResumen()) {
					sheet.addCell(new Label(1, cont + 1, informe.getZona(), cellhdFormatLeft));
					sheet.addCell(new Label(2, cont + 1, informe.getTotalAsociadosZona().toString(), cellhdFormat));
					sheet.addCell(new Label(3, cont + 1, informe.getTotalHabilesZona().toString(), cellhdFormat));
					sheet.addCell(
							new Label(4, cont + 1, informe.getPorcentajeHabiles().toString() + "%", cellhdFormat));
					sheet.addCell(new Label(5, cont + 1, informe.getTotalInhabilesZona().toString(), cellhdFormat));
					sheet.addCell(
							new Label(6, cont + 1, informe.getPorcentajeInhabiles().toString() + "%", cellhdFormat));
					sheet.addCell(new Label(7, cont + 1, informe.getMuestraZona().toString(), cellhdFormatCenter));
					sheet.addCell(new Label(8, cont + 1, informe.getPorcentajeMuestra().toString() + "%", cellhdFormat));
					cont++;
				}
				sheet.addCell(new Label(0, cont + 1, "Total " + dto.getRegional(), cellhdFormatBoldLeft));
				sheet.addCell(new Label(1, cont + 1, "", cellhdFormatBoldLeft));
				sheet.mergeCells(0, cont + 1, 1, cont + 1);
				sheet.addCell(new Label(2, cont + 1, dto.getTotalAsociadosZona().toString(), cellhdFormatBoldRight));
				sheet.addCell(new Label(3, cont + 1, dto.getTotalHabilesZona().toString(), cellhdFormatBoldRight));
				sheet.addCell(
						new Label(4, cont + 1, dto.getPorcentajeHabiles().toString() + "%", cellhdFormatBoldRight));
				sheet.addCell(new Label(5, cont + 1, dto.getTotalInhabilesZona().toString(), cellhdFormatBoldRight));
				sheet.addCell(
						new Label(6, cont + 1, dto.getPorcentajeInhabiles().toString() + "%", cellhdFormatBoldRight));
				sheet.addCell(new Label(7, cont + 1, dto.getMuestraZona().toString(), cellhdFormatBold));
				sheet.addCell(new Label(8, cont + 1, (dto.getPorcentajeMuestra()*100) + "%", cellhdFormatBoldRight));

				totalAsociados += dto.getTotalAsociadosZona();
				totalHabiles += dto.getTotalHabilesZona();
				totalInhabiles += dto.getTotalInhabilesZona();

				cont++;
			}

			totalPorcentajeHab = (totalHabiles / totalAsociados) * 100;
			totalPorcentajeHab = LogicaInformeResumen.round((totalPorcentajeHab), 2);
			totalPorcentajeInhab = (totalInhabiles / totalAsociados) * 100;
			totalPorcentajeInhab = LogicaInformeResumen.round((totalPorcentajeInhab), 2);
			totalMuestra = totalInhabiles * ConstantesProperties.VALOR_PORCENTAJE_MUESTRA;
			totalMuestra = LogicaInformeResumen.round(totalMuestra, 2);
			totalPorcentajeMuestra = totalMuestra / totalInhabiles;
			totalPorcentajeMuestra = LogicaInformeResumen.round(totalPorcentajeMuestra, 2)*100;

			sheet.addCell(new Label(0, cont + 1, "Total general ", cellhdFormatBoldLeft));
			sheet.addCell(new Label(1, cont + 1, "", cellhdFormatBoldLeft));
			sheet.mergeCells(0, cont + 1, 1, cont + 1);
			sheet.addCell(new Label(2, cont + 1, totalAsociados.toString(), cellhdFormatBoldRight));
			sheet.addCell(new Label(3, cont + 1, totalHabiles.toString(), cellhdFormatBoldRight));
			sheet.addCell(new Label(4, cont + 1, totalPorcentajeHab.toString() + "%", cellhdFormatBoldRight));
			sheet.addCell(new Label(5, cont + 1, totalInhabiles.toString(), cellhdFormatBoldRight));
			sheet.addCell(new Label(6, cont + 1, totalPorcentajeInhab.toString() + "%", cellhdFormatBoldRight));
			sheet.addCell(new Label(7, cont + 1, totalMuestra.toString(), cellhdFormatBold));
			sheet.addCell(new Label(8, cont + 1, totalPorcentajeMuestra.toString() + "%", cellhdFormatBoldRight));

			workbook.write();
			workbook.close();

		} catch (Exception e) {
			throw e;
		}

		return file;

	}

	/**
	 * Metodo que genera el resumen de novedades en excel.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
	 *         Pragma S.A. <br>
	 * @date 27/11/2012
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream generarResumenNovedades(List<ResumenNovedadesDTO> list) throws Exception {
		ByteArrayOutputStream file = new ByteArrayOutputStream();
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(file);

			WritableFont arial10fontBlackBold = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontBlackBold.setBoldStyle(WritableFont.BOLD);
			arial10fontBlackBold.setColour(Colour.WHITE);
			WritableFont arial10fontBlack = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontBlack.setColour(Colour.BLACK);
			WritableFont arial10fontWhite = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontWhite.setColour(Colour.WHITE);
			arial10fontWhite.setBoldStyle(WritableFont.BOLD);

			CellView cellview = new CellView();
			cellview.setAutosize(true);

			WritableCellFormat cellhdFormatBold = new WritableCellFormat();
			cellhdFormatBold.setWrap(true);
			cellhdFormatBold.setFont(arial10fontBlackBold);
			cellhdFormatBold.setAlignment(Alignment.CENTRE);
			cellhdFormatBold.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellhdFormatBold.setBackground(Colour.GREEN);
			cellhdFormatBold.setVerticalAlignment(VerticalAlignment.CENTRE);

			WritableCellFormat cellhdFormat = new WritableCellFormat();
			cellhdFormat.setWrap(true);
			cellhdFormat.setFont(arial10fontBlack);
			cellhdFormat.setAlignment(Alignment.CENTRE);
			cellhdFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableCellFormat cellhdFormatLeft = new WritableCellFormat();
			cellhdFormatLeft.setWrap(true);
			cellhdFormatLeft.setFont(arial10fontBlack);
			cellhdFormatLeft.setAlignment(Alignment.LEFT);
			cellhdFormatLeft.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableCellFormat cellhdFormatNumero = new WritableCellFormat();
			cellhdFormat.setWrap(true);
			cellhdFormatNumero.setBackground(Colour.SEA_GREEN);
			cellhdFormatNumero.setFont(arial10fontWhite);
			cellhdFormatNumero.setAlignment(Alignment.CENTRE);
			cellhdFormatNumero.setBorder(Border.ALL, BorderLineStyle.THIN);

			String ttlFecha = "Fecha";
			String ttlTipoNovedad = "Tipo Novedad";
			String ttlRegional = loaderResourceElements
					.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblRegional");
			String ttlZona = loaderResourceElements
					.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblZona");
			String ttlNumeroNovedades = "Número Novedades";
			String ttlPorcentajeNovedades = "% Novedades";
			String ttlTitulo = "RESUMEN DE NOVEDADES";

			WritableSheet sheet = workbook.createSheet("Resumen de Novedades", 0);
			sheet.setColumnView(0, cellview);
			// se fijan los tañaños de las columnas
			sheet.setColumnView(0, 15);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 30);
			sheet.setColumnView(3, 30);
			sheet.setColumnView(4, 15);
			sheet.setColumnView(5, 15);

			int cont = 2;
			for (int i = 0; i <= cont; i++) {
				sheet.addCell(new Label(0, cont, "", cellhdFormatBold));
				sheet.addCell(new Label(1, cont, "", cellhdFormatBold));
				sheet.addCell(new Label(2, cont, "", cellhdFormatBold));
				sheet.addCell(new Label(3, cont, "", cellhdFormatBold));
				sheet.addCell(new Label(4, cont, "", cellhdFormatBold));
				sheet.addCell(new Label(5, cont, "", cellhdFormatBold));
			}

			sheet.addCell(new Label(0, 0, ttlTitulo, cellhdFormatBold));
			// col[0,0] fila[5,cont]
			sheet.mergeCells(0, 0, 5, cont);
			sheet.addCell(new Label(0, cont + 1, ttlFecha, cellhdFormatBold));
			sheet.addCell(new Label(1, cont + 1, ttlTipoNovedad, cellhdFormatBold));
			sheet.addCell(new Label(2, cont + 1, ttlRegional, cellhdFormatBold));
			sheet.addCell(new Label(3, cont + 1, ttlZona, cellhdFormatBold));
			sheet.addCell(new Label(4, cont + 1, ttlNumeroNovedades, cellhdFormatBold));
			sheet.addCell(new Label(5, cont + 1, ttlPorcentajeNovedades, cellhdFormatBold));

			cont++;
			boolean showTitle = Boolean.TRUE;

			for (ResumenNovedadesDTO dto : list) {
				sheet.addCell(new Label(0, cont + 1, dto.getFechaProceso(), cellhdFormat));
				sheet.addCell(new Label(1, cont + 1, dto.getTipoNovedad(), cellhdFormat));
				for (ResumenZonasNovedadesDTO informe : dto.getList()) {
					sheet.addCell(new Label(2, cont + 1, informe.getRegional().toString(), cellhdFormatLeft));
					sheet.addCell(new Label(3, cont + 1, informe.getZona().toString(), cellhdFormatLeft));
					sheet.addCell(new Label(4, cont + 1, informe.getNumeroNovedades().toString(), cellhdFormat));
					sheet.addCell(new Label(5, cont + 1, informe.getPorcentajeNovedades()+" %", cellhdFormat));
					cont++;
				}
				sheet.addCell(new Label(0, cont + 1, "Total " + dto.getTipoNovedad().toString(), cellhdFormatBold));
				sheet.addCell(new Label(1, cont + 1, "", cellhdFormatBold));
				sheet.addCell(new Label(2, cont + 1, "", cellhdFormatBold));
				sheet.addCell(new Label(3, cont + 1, "", cellhdFormatBold));
				sheet.mergeCells(0, cont + 1, 3, cont + 1);
				sheet.addCell(new Label(4, cont + 1, dto.getTotalNumeroNov().toString(), cellhdFormatBold));
				sheet.addCell(new Label(5, cont + 1, dto.getTotalPorcentajeNov() + " %", cellhdFormatBold));

				cont += 2;
				if (showTitle) {
					sheet.addCell(new Label(0, cont, ttlFecha, cellhdFormatBold));
					sheet.addCell(new Label(1, cont, ttlTipoNovedad, cellhdFormatBold));
					sheet.addCell(new Label(2, cont, ttlRegional, cellhdFormatBold));
					sheet.addCell(new Label(3, cont, ttlZona, cellhdFormatBold));
					sheet.addCell(new Label(4, cont, ttlNumeroNovedades, cellhdFormatBold));
					sheet.addCell(new Label(5, cont, ttlPorcentajeNovedades, cellhdFormatBold));
					showTitle = Boolean.FALSE;
				}
			}

			workbook.write();
			workbook.close();

		} catch (Exception e) {
			throw e;
		}

		return file;

	}

	public ByteArrayOutputStream generarCoucienteElectoral(List<EleCuocienteRegional> regList,
			List<EleCuocienteDelegadosZona> list, EleCuocienteElectoral cuocienteElectoral) throws Exception {
		ByteArrayOutputStream file = new ByteArrayOutputStream();
		try {

			WritableWorkbook workbook = Workbook.createWorkbook(file);

			WritableFont arial10fontBlackBold = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontBlackBold.setBoldStyle(WritableFont.BOLD);
			arial10fontBlackBold.setColour(Colour.BLACK);

			WritableFont arial10fontBlack = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontBlack.setColour(Colour.BLACK);

			WritableFont arial10fontWhite = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontWhite.setColour(Colour.WHITE);
			arial10fontWhite.setBoldStyle(WritableFont.BOLD);

			WritableFont arial13fontBlackBold = new WritableFont(WritableFont.ARIAL, 13);
			arial13fontBlackBold.setBoldStyle(WritableFont.BOLD);
			arial13fontBlackBold.setColour(Colour.BLACK);
			
			WritableFont arial13fontWhiteBold = new WritableFont(WritableFont.ARIAL, 13);
			arial13fontWhiteBold.setBoldStyle(WritableFont.BOLD);
			arial13fontWhiteBold.setColour(Colour.WHITE);

			WritableCellFormat cellhdFormatBoldRight = new WritableCellFormat();
			cellhdFormatBoldRight.setWrap(true);
			cellhdFormatBoldRight.setFont(arial10fontBlackBold);
			cellhdFormatBoldRight.setAlignment(Alignment.RIGHT);
			cellhdFormatBoldRight.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableCellFormat cellhdFormat = new WritableCellFormat();
			cellhdFormat.setWrap(true);
			cellhdFormat.setFont(arial10fontBlack);
			cellhdFormat.setAlignment(Alignment.LEFT);
			cellhdFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableCellFormat cellhdFormatBoldLeftYellow = new WritableCellFormat();
			cellhdFormatBoldLeftYellow.setWrap(true);
			cellhdFormatBoldLeftYellow.setFont(arial10fontBlackBold);
			cellhdFormatBoldLeftYellow.setAlignment(Alignment.LEFT);
			cellhdFormatBoldLeftYellow.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellhdFormatBoldLeftYellow.setBackground(Colour.YELLOW);

			WritableCellFormat cellhdFormatBoldRightYellow = new WritableCellFormat();
			cellhdFormatBoldRightYellow.setWrap(true);
			cellhdFormatBoldRightYellow.setFont(arial10fontBlackBold);
			cellhdFormatBoldRightYellow.setAlignment(Alignment.RIGHT);
			cellhdFormatBoldRightYellow.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellhdFormatBoldRightYellow.setBackground(Colour.YELLOW);

			WritableCellFormat cellhdFormatBoldRightGreen = new WritableCellFormat();
			cellhdFormatBoldRightGreen.setWrap(true);
			cellhdFormatBoldRightGreen.setFont(arial10fontWhite);
			cellhdFormatBoldRightGreen.setAlignment(Alignment.RIGHT);
			cellhdFormatBoldRightGreen.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellhdFormatBoldRightGreen.setBackground(Colour.GREEN);

			WritableCellFormat cellhdFormatRight = new WritableCellFormat();
			cellhdFormatRight.setWrap(true);
			cellhdFormatRight.setFont(arial10fontBlack);
			cellhdFormatRight.setAlignment(Alignment.RIGHT);
			cellhdFormatRight.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableCellFormat cellhdFormatTitle = new WritableCellFormat();
			cellhdFormatTitle.setWrap(true);
			cellhdFormatTitle.setFont(arial13fontWhiteBold);
			cellhdFormatTitle.setAlignment(Alignment.CENTRE);
			cellhdFormatTitle.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellhdFormatTitle.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellhdFormatTitle.setBackground(Colour.GREEN);

			WritableCellFormat cellhdFormatBoldCenterGreen = new WritableCellFormat();
			cellhdFormatBoldCenterGreen.setWrap(true);
			cellhdFormatBoldCenterGreen.setFont(arial10fontBlackBold);
			cellhdFormatBoldCenterGreen.setAlignment(Alignment.CENTRE);
			cellhdFormatBoldCenterGreen.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellhdFormatBoldCenterGreen.setBackground(Colour.GREEN);
			cellhdFormatBoldCenterGreen.setVerticalAlignment(VerticalAlignment.CENTRE);

			WritableCellFormat cellhdFormatCenter = new WritableCellFormat();
			cellhdFormatCenter.setWrap(true);
			cellhdFormatCenter.setFont(arial10fontBlack);
			cellhdFormatCenter.setAlignment(Alignment.CENTRE);
			cellhdFormatCenter.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellhdFormatCenter.setVerticalAlignment(VerticalAlignment.CENTRE);

			WritableCellFormat cellhdFormatBoldCenter = new WritableCellFormat();
			cellhdFormatBoldCenter.setWrap(true);
			cellhdFormatBoldCenter.setFont(arial10fontBlackBold);
			cellhdFormatBoldCenter.setAlignment(Alignment.CENTRE);
			cellhdFormatBoldCenter.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellhdFormatBoldCenter.setVerticalAlignment(VerticalAlignment.CENTRE);

			WritableCellFormat cellhdFormatBoldCenterNoBorders = new WritableCellFormat();
			cellhdFormatBoldCenterNoBorders.setWrap(true);
			cellhdFormatBoldCenterNoBorders.setFont(arial10fontWhite);
			cellhdFormatBoldCenterNoBorders.setAlignment(Alignment.CENTRE);
			cellhdFormatBoldCenterNoBorders.setBackground(Colour.GREEN);
			cellhdFormatBoldCenterNoBorders.setVerticalAlignment(VerticalAlignment.CENTRE);

			WritableCellFormat cellhdFormatAsociados = new WritableCellFormat();
			cellhdFormatAsociados.setWrap(true);
			cellhdFormatAsociados.setFont(arial10fontBlackBold);
			cellhdFormatAsociados.setAlignment(Alignment.CENTRE);
			cellhdFormatAsociados.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellhdFormatAsociados.setBackground(Colour.GRAY_25);
			cellhdFormatAsociados.setVerticalAlignment(VerticalAlignment.CENTRE);

			WritableCellFormat cellhdFormatAsociadosEspeciales = new WritableCellFormat();
			cellhdFormatAsociadosEspeciales.setWrap(Boolean.TRUE);
			cellhdFormatAsociadosEspeciales.setFont(arial10fontBlackBold);
			cellhdFormatAsociadosEspeciales.setAlignment(Alignment.CENTRE);
			cellhdFormatAsociadosEspeciales.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellhdFormatAsociadosEspeciales.setBackground(Colour.ORANGE);
			cellhdFormatAsociadosEspeciales.setVerticalAlignment(VerticalAlignment.CENTRE);			

			WritableCellFormat cellhdFormatCuo = new WritableCellFormat();
			cellhdFormatCuo.setWrap(true);
			cellhdFormatCuo.setBackground(Colour.GREEN);
			cellhdFormatCuo.setFont(arial10fontWhite);
			cellhdFormatCuo.setAlignment(Alignment.RIGHT);
			cellhdFormatCuo.setBorder(Border.ALL, BorderLineStyle.THIN);

			// Agrego las columnas

			String ttlTitulo = "Cálculo Cuociente Electoral";
			String ttlTotalAsoHab = "Total Asociados Hábiles";
			String ttlTotalDelegElegir = "Total Delegados a Elegir";
			String ttlTotalDelegEspElegir = "Total Delegados Especiales (Promotores, Empleados)";
			String ttlTotalFinalDeleg = "Final Total Delegados a Elegir";
			String ttlCuociente = "Cuociente";

			String ttlNoZonaElectoral = "No Zona Electoral";
			String ttlRegional = "Regional";
			String ttlZona = loaderResourceElements
					.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblZona");
			String ttlTotalAsoHabil = "Total Asociados Hábiles (Asociados y Empleados)";
			String ttlTotalAsoHabiles = "Total Asociados Hábiles a la Fecha";
			String ttlTotalAsoEspHabiles = "Total de Asociados empleados y corredores hábiles";
			String ttlFraccionDelegados = "Cuociente por Zona Electoral";
			String ttlTotalDelegadosZona = "Delegados por cuociente X Zona Electoral";
			String ttlFraccionRestantes = "Fracción de Residuo por Zona Electoral (Ordenar de Mayor a Menor)";
			String ttlTotalDelegadosRestantes = "Delegados por residuo por Zona Electoral (Ordenar de Mayor a Menor)";
			String ttlSumaTotalDelegados = "TOTAL DELEGADOS POR ZONA";
			String totalDelegados = "TOTAL DELEGADOS";
			String ttlTotal = "Total";
			String ttlValidacion = "Validación 33%";
			String ttlMsgValidacion = "Ninguna regional se pasa del 33% del total de delegados,  no se debe redistribuir.";
			String ttlAsoEsp = "Empleados Asociados Especiales";			

			WritableSheet sheet = workbook.createSheet("Cuociente Electoral", 0);

			// Ajusto el ancho de las columnas
			sheet.setColumnView(0, 15);
			sheet.setColumnView(1, 33);
			sheet.setColumnView(2, 30);
			int indx = 9;
			for (int i = 3; i <= indx; i++) {
				sheet.setColumnView(i, 25);
			}
			sheet.setColumnView(10, 15);			
			
			//se fijan los datos
			sheet.mergeCells(0, 0, 2, 0);
			sheet.addCell(new Label(0, 0, ttlTitulo, cellhdFormatTitle));
			
			sheet.mergeCells(0, 1, 1, 1);
			sheet.addCell(new Label(0, 1, ttlTotalAsoHab, cellhdFormat));
			sheet.addCell(new Label(2, 1, cuocienteElectoral.getTotalAsocHabiles().toString(), cellhdFormatBoldRight));
			
			sheet.mergeCells(0, 2, 1, 2);
			sheet.addCell(new Label(0, 2, ttlTotalDelegElegir, cellhdFormat));
			sheet.addCell(new Label(2, 2, cuocienteElectoral.getTotalDelegadosElegir().toString(), cellhdFormatRight));
			
			sheet.mergeCells(0, 3, 1, 3);
			sheet.addCell(new Label(0, 3, ttlTotalDelegEspElegir, cellhdFormat));
			sheet.addCell(new Label(2, 3, cuocienteElectoral.getTotalDelegadosEspeciales().toString(), cellhdFormatRight));
			
			sheet.mergeCells(0, 4, 1, 4);
			sheet.addCell(new Label(0, 4, ttlTotalFinalDeleg, cellhdFormat));
			sheet.addCell(new Label(2, 4, cuocienteElectoral.getFinalTotalDelegadosElegir().toString(), cellhdFormatBoldRight));
			
			sheet.mergeCells(0, 5, 1, 5);
			sheet.addCell(new Label(0, 5, ttlCuociente, cellhdFormatCuo));
			sheet.addCell(new Label(2, 5, cuocienteElectoral.getCuocienteElectoral().toString(), cellhdFormatCuo));

			int cont = 7;
			sheet.addCell(new Label(0, cont, ttlNoZonaElectoral, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(1, cont, ttlRegional, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(2, cont, ttlZona, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(3, cont, ttlTotalAsoHabil, cellhdFormatAsociados));
			sheet.addCell(new Label(4, cont, ttlTotalAsoHabiles, cellhdFormatAsociados));
			sheet.addCell(new Label(5, cont, ttlTotalAsoEspHabiles, cellhdFormatAsociadosEspeciales));
			sheet.addCell(new Label(6, cont, ttlFraccionDelegados, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(7, cont, ttlTotalDelegadosZona, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(8, cont, ttlFraccionRestantes, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(9, cont, ttlTotalDelegadosRestantes, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(10, cont, ttlSumaTotalDelegados, cellhdFormatBoldCenterGreen));
			cont++;
			sheet.getSettings().setVerticalFreeze(cont);

			Double sumTotalAso = 0d;
			Double sumTotalAsoHab = 0d;
			Double sumTotalAsoHabEsp = 0d;
			Double sumTotalDelegZona = 0d;
			Double sumTotalDelegRest = 0d;
			Double sumTotalFinalDeleg = 0d;
			for (EleCuocienteDelegadosZona dto : list) {
				sheet.addCell(new Label(0, cont, splitDescCodZona(dto.getDescCodZona(), Boolean.TRUE), cellhdFormatCenter));
				sheet.addCell(new Label(1, cont, REGIONAL + dto.getDescCodRegional(), cellhdFormat));
				sheet.addCell(new Label(2, cont, splitDescCodZona(dto.getDescCodZona(), Boolean.FALSE), cellhdFormat));
//				sheet.addCell(new Label(3, cont,
//						String.valueOf((dto.getSumaHabiles() - dto.getSumaNovedades() + dto.getSumaEspHabiles())),
//						cellhdFormatRight));
				sheet.addCell(new Label(3, cont,
						String.valueOf(dto.getSumaTotalHabiles()), cellhdFormatRight));
				
				sheet.addCell(new Label(4, cont, String.valueOf(dto.getSumaTotalHabiles() - dto.getSumaEspHabiles()),
						cellhdFormatRight));
				
				sheet.addCell(new Label(5, cont, dto.getSumaEspHabiles().toString(), cellhdFormatRight));
				sheet.addCell(new Label(6, cont,
						String.valueOf(LogicaInformeResumen.round(dto.getFraccion() + dto.getDelegadosDirectos(), 4)),
						cellhdFormatRight));
				sheet.addCell(new Label(7, cont, dto.getDelegadosDirectos().toString(), cellhdFormatRight));
				sheet.addCell(new Label(8, cont, dto.getFraccion().toString(), cellhdFormatRight));
				sheet.addCell(new Label(9, cont, dto.getDelegadosResiduo().toString(), cellhdFormatRight));
				sheet.addCell(new Label(10, cont, dto.getTotalDelegadosZona().toString(), cellhdFormatRight));

				sumTotalAso += dto.getSumaTotalHabiles();
				sumTotalAsoHab += dto.getSumaTotalHabiles() - dto.getSumaEspHabiles();
				sumTotalAsoHabEsp += dto.getSumaEspHabiles();
				sumTotalDelegZona += dto.getDelegadosDirectos();
				sumTotalDelegRest += dto.getDelegadosResiduo();
				sumTotalFinalDeleg += dto.getTotalDelegadosZona();
				cont++;
			}
			sheet.addCell(new Label(0, cont, "", cellhdFormat));
			sheet.addCell(new Label(1, cont, "", cellhdFormat));
			sheet.addCell(new Label(2, cont, totalDelegados, cellhdFormatBoldLeftYellow));
			sheet.addCell(new Label(3, cont, sumTotalAso.toString(), cellhdFormatBoldRightYellow));
			sheet.addCell(new Label(4, cont, sumTotalAsoHab.toString(), cellhdFormatBoldRightYellow));
			sheet.addCell(new Label(5, cont, sumTotalAsoHabEsp.toString(), cellhdFormatBoldRightYellow));
			sheet.addCell(new Label(6, cont, "", cellhdFormatBoldRight));
			sheet.addCell(new Label(7, cont, sumTotalDelegZona.toString(), cellhdFormatBoldRightGreen));
			sheet.addCell(new Label(8, cont, "", cellhdFormatBoldRight));
			sheet.addCell(new Label(9, cont, sumTotalDelegRest.toString(), cellhdFormatBoldRightGreen));
			sheet.addCell(new Label(10, cont, sumTotalFinalDeleg.toString(), cellhdFormatBoldRightGreen));

			cont += 2;
			indx = 1;

			sheet.addCell(new Label(indx, cont, ttlRegional, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(indx + 1, cont, ttlTotal, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(indx + 2, cont, ttlValidacion, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(indx + 3, cont, ttlMsgValidacion, cellhdFormatCenter));
			sheet.addCell(new Label(indx + 4, cont, ttlAsoEsp, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(indx + 5, cont, ttlTotal, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(indx + 6, cont, ttlValidacion, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(indx + 7, cont, ttlMsgValidacion, cellhdFormatCenter));
			
			sheet.mergeCells(indx + 7, cont, indx + 7, cont + 6);
			sheet.mergeCells(indx + 3, cont, indx + 3, cont + 6);

			cont++;
			// Agrego las regionales
			int sumaTotal = 0;
			int sumaPorcentajes = 0;
			Double sumaRelacionPorc = 0d;
			int sumaHabiles = 0;
			for (EleCuocienteRegional dto : regList) {
				sheet.addCell(new Label(indx, cont, "TOTAL " + dto.getDesRegional(), cellhdFormat));
				sheet.addCell(new Label(indx + 1, cont, dto.getTotalDelegados().toString(), cellhdFormatCenter));
				sheet.addCell(
						new Label(indx + 2, cont, dto.getRelacionDelegados().toString() + "%", cellhdFormatCenter));
				sheet.addCell(new Label(indx + 4, cont, "1", cellhdFormatCenter));
				
				sheet.addCell(new Label(indx + 5, cont, String.valueOf(dto.getTotalDelegados().intValue() + 1),
						cellhdFormatBoldCenter));
				
				sheet.addCell(new Label(indx + 6, cont,
						String.valueOf((dto.getTotalDelegados().intValue() + 1)) + ",00%", cellhdFormatRight));
				
				sumaTotal += dto.getTotalDelegados().intValue();
				sumaRelacionPorc += dto.getRelacionDelegados();
				sumaHabiles += (dto.getTotalDelegados() + 1);
				cont++;
			}

			sheet.addCell(new Label(indx + 1, cont, String.valueOf(sumaTotal), cellhdFormatBoldCenterNoBorders));
			sheet.addCell(new Label(indx + 2, cont,
					String.valueOf((int) LogicaInformeResumen.round(sumaRelacionPorc, 0)) + "%",
					cellhdFormatBoldCenterNoBorders));
			sheet.addCell(new Label(indx + 5, cont, String.valueOf(sumaHabiles), cellhdFormatBoldCenterNoBorders));

			workbook.write();
			workbook.close();

		} catch (Exception e) {
			throw e;
		}

		return file;

	}
	
	private String splitDescCodZona(String value, boolean esNumero) {
		if(esNumero)
			return value.split(" ")[0].trim();
		else {
			String[] split = value.split(" ");
			String desc = "";
			for (int i = 1; i < split.length; i++) {
				desc = desc + " " + split[i]; 
			}
			return desc.trim();
		}
	}

	public ByteArrayOutputStream generarReportePlanchasEstado(List<PlanchaPorEstadoDTO> list) throws Exception {

		ByteArrayOutputStream file = new ByteArrayOutputStream();
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(file);

			WritableFont arial10fontBlackBold = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontBlackBold.setBoldStyle(WritableFont.BOLD);
			arial10fontBlackBold.setColour(Colour.BLACK);
			WritableFont arial10fontBlack = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontBlack.setColour(Colour.BLACK);
			WritableFont arial10fontWhite = new WritableFont(WritableFont.ARIAL, 10);
			arial10fontWhite.setColour(Colour.WHITE);
			arial10fontWhite.setBoldStyle(WritableFont.BOLD);

			CellView cellview = new CellView();
			cellview.setAutosize(true);

			WritableCellFormat cellhdFormatBold = new WritableCellFormat();
			cellhdFormatBold.setWrap(false);
			cellhdFormatBold.setFont(arial10fontBlackBold);
			cellhdFormatBold.setAlignment(Alignment.LEFT);
			cellhdFormatBold.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableCellFormat cellhdFormat = new WritableCellFormat();
			cellhdFormat.setWrap(false);
			arial10fontBlackBold.setColour(Colour.BLACK);
			cellhdFormat.setFont(arial10fontBlack);
			cellhdFormat.setAlignment(Alignment.LEFT);
			cellhdFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableCellFormat cellhdFormatZona = new WritableCellFormat();
			// cellhdFormat.setWrap(true);
			cellhdFormatZona.setBackground(Colour.GREEN);
			cellhdFormatZona.setFont(arial10fontWhite);
			cellhdFormatZona.setAlignment(Alignment.CENTRE);
			cellhdFormatZona.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableCellFormat cellhdFormatNumero = new WritableCellFormat();
			// cellhdFormat.setWrap(true);
			cellhdFormatNumero.setBackground(Colour.SEA_GREEN);
			cellhdFormatNumero.setFont(arial10fontWhite);
			cellhdFormatNumero.setAlignment(Alignment.CENTRE);
			cellhdFormatNumero.setBorder(Border.ALL, BorderLineStyle.THIN);

			String tNumPlancha = "Nro. Plancha";
			String tZonaElectoral = "Zona Electoral";
			String tCabezaPlancha = "Cabeza Plancha";
			String tDocumento = "Documento";
			String tFechaInscripcion = "Fecha Inscripción";
			String tEstado = "Estado";

			WritableSheet sheet = workbook.createSheet("PlanchasEstado - hoja ", 0);
			sheet.setColumnView(0, cellview);
			int cont = 0;

			sheet.addCell(new Label(0, cont + 1, tNumPlancha, cellhdFormatBold));
			sheet.addCell(new Label(1, cont + 1, tZonaElectoral, cellhdFormatBold));
			sheet.addCell(new Label(2, cont + 1, tCabezaPlancha, cellhdFormatBold));
			sheet.addCell(new Label(3, cont + 1, tDocumento, cellhdFormatBold));
			sheet.addCell(new Label(4, cont + 1, tFechaInscripcion, cellhdFormatBold));
			sheet.addCell(new Label(5, cont + 1, tEstado, cellhdFormatBold));

			cont++;

			for (PlanchaPorEstadoDTO dto : list) {
				sheet.addCell(new Label(0, cont + 1,
						dto.getNumeroPlancha() != null ? dto.getNumeroPlancha().toString() : "", cellhdFormat));
				sheet.addCell(new Label(1, cont + 1, dto.getZona(), cellhdFormat));
				sheet.addCell(new Label(2, cont + 1, dto.getAsociadoCabeza(), cellhdFormat));
				sheet.addCell(new Label(3, cont + 1, dto.getNumCedula().toString(), cellhdFormat));
				sheet.addCell(new Label(4, cont + 1, dto.getFechaInscripcion().toString(), cellhdFormat));
				sheet.addCell(new Label(5, cont + 1, dto.getEstado().toString(), cellhdFormat));

				cont++;
			}

			workbook.write();
			workbook.close();

		} catch (Exception e) {
			throw e;
		}

		return file;
	}

	/**
	 * @author christian Mauricio Tangarife Colorado cmtc4227
	 * @since 2016-08-23
	 * 
	 *        Metodo para generar el reporte Jasper de RESOLUCIÓN DE ADMISIÓN DE
	 *        PLANCHAS
	 * 
	 * @param zonaElectoral
	 * @param nombreAsociado
	 * @param numResolucion
	 * @param numActa
	 * @param dia
	 * @param mes
	 * @param anio
	 * @param rutaImagen
	 * @param rutaReporte
	 * @return
	 */
	public JasperPrint reporteResolucionAdmisionPlanchas_FT_172(String zonaElectoral, String nombreAsociado,
			String numResolucion, String numActa, String fecha, String ciudadZona, String dia, String mes, String anio,
			String rutaImagen, String rutaReporte) throws Exception {
		JasperPrint jasperPrint = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("zona", zonaElectoral);
			parameters.put("nombre", nombreAsociado);
			parameters.put("resolucion", numResolucion);
			parameters.put("acta", numActa);
			parameters.put("fechaActa", fecha);
			parameters.put("ciudadZona", ciudadZona);
			parameters.put("dia", dia);
			parameters.put("mes", mes);
			parameters.put("anio", anio);
			parameters.put("rutaImagen", rutaImagen);
			
			rutaReporte = rutaReporte + "plantilla_CO_FT_172.jasper";
			JRDataSource ds = new JREmptyDataSource();
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(rutaReporte);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);

			return jasperPrint;
		} catch (JRException e) {
			throw e;
		}
	}

	/**
	 * @author christian Mauricio Tangarife Colorado cmtc4227
	 * @since 2016-08-23
	 * 
	 *        Metodo para generar el reporte Jasper de RESOLUCIÓN DE RECHAZO DE
	 *        PLANCHAS
	 * 
	 * @param zonaElectoral
	 * @param nombreAsociado
	 * @param numResolucion
	 * @param cedulaAsociado
	 * @param numActa
	 * @param dia
	 * @param mes
	 * @param anio
	 * @param razones
	 * @param rutaImagen
	 * @param rutaReporte
	 * @return
	 * @throws Exception
	 */
	public JasperPrint reporteResolucionRechazoPlanchas_FT_173(String zonaElectoral, String nombreAsociado,
			String numResolucion, String cedulaAsociado, String numActa, String fecha, String dia, String mes,
			String anio, String mesActa, String rutaImagen, String rutaReporte) throws Exception {
		JasperPrint jasperPrint = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("zona", zonaElectoral);
			parameters.put("nombre", nombreAsociado);
			parameters.put("resolucion", numResolucion);
			parameters.put("cedula", cedulaAsociado);
			parameters.put("acta", numActa);
			parameters.put("fechaActa", fecha);
			parameters.put("dia", dia);
			parameters.put("mes", mes);
			parameters.put("anio", anio);
			parameters.put("mesActa", mesActa);
			parameters.put("rutaImagen", rutaImagen);

			rutaReporte = rutaReporte + "plantilla_CO_FT_173.jasper";
			JRDataSource ds = new JREmptyDataSource();
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(rutaReporte);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);

			return jasperPrint;

		} catch (JRException e) {
			throw e;
		}
	}

	/**
	 * @author christian Mauricio Tangarife Colorado cmtc4227
	 * @since 2016-08-23
	 * 
	 *        Metodo para generar el reporte Jasper de CERTIFICACIÓN DE CUMPLIMIENTO
	 *        PARA SER ELEGIDO DELEGADO(formato CO-FT-176)
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	public JasperPrint reporteCumplimientoDelegado_FT_176(String zonaElectoral, String nombreAsociado,
			String cedulaAsociado, String dia, String mes, String anio, String ciudad, 
			List<String> observaciones, String rutaImagen,
			String rutaReporte) throws Exception {
		JasperPrint jasperPrint = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("zonaElectoral", zonaElectoral);
			parameters.put("nombreAsociado", nombreAsociado);
			parameters.put("cedulaAsociado", cedulaAsociado);
			parameters.put("dia", dia);
			parameters.put("mes", mes);
			parameters.put("annio", anio);
			parameters.put("ciudad", ciudad);
			parameters.put("observaciones", observaciones);
			parameters.put("rutaImagen", rutaImagen);

			rutaReporte = rutaReporte + "plantilla_CO_FT_176.jasper";
			JRDataSource ds = new JREmptyDataSource();
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(rutaReporte);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);

			return jasperPrint;
		} catch (JRException e) {
			throw e;
		}
	}

	/**
	 * @author christian Mauricio Tangarife Colorado cmtc4227
	 * @since 2016-08-23
	 * 
	 *        Metodo para generar el reporte Jasper de CONSTANCIA DE RADICACIÓN Y
	 *        RECIBO DE PLANCHAS(formato CO-FT-208)
	 * 
	 * @param zonaElectoral
	 * @param ciudadZona
	 * @param nombreAsociado
	 * @param cedulaAsociado
	 * @param ciudadCedula
	 * @param dia
	 * @param mes
	 * @param anio
	 * @param rutaImagen
	 * @param rutaReporte
	 * @return
	 * @throws Exception
	 */
	public JasperPrint reporteConstanciaRadicacionPlanchas_FT_208(String zonaElectoral, Date fecha, String numComision,
			String ciudadZona, String nombreAsociado, String cedulaAsociado, String ciudadCedula, String dia,
			String mes, String anio, String nombreEntrega, String nombreRecibe, String rutaImagen, String rutaReporte)
			throws Exception {
		JasperPrint jasperPrint = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("zona", zonaElectoral);
			parameters.put("fechaActual", fecha);
			parameters.put("comision", numComision);
			parameters.put("ciudad", ciudadZona);
			parameters.put("nombre", nombreAsociado);
			parameters.put("cedula", cedulaAsociado);
			parameters.put("ciudadCedula", ciudadCedula);
			parameters.put("dia", dia);
			parameters.put("mes", mes);
			parameters.put("anio", anio);
			parameters.put("nombreEntrega", nombreEntrega);
			parameters.put("nombreRecibe", nombreRecibe);
			parameters.put("rutaImagen", rutaImagen);

			rutaReporte = rutaReporte + "plantilla_CO_FT_208.jasper";
			JRDataSource ds = new JREmptyDataSource();
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(rutaReporte);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);

			return jasperPrint;

		} catch (JRException e) {
			throw e;
		}
	}

	/**
	 * 
	 * @param zonaElectoral
	 * @param ciudad
	 * @param rutaImagen
	 * @param rutaReporte
	 * @return
	 * @throws Exception
	 */
	public JasperPrint reporteInscripcionPlanchas_FT_210(HashMap<String, String> parametros,
			String rutaReporte, String rutaImagen) throws Exception {
		JasperPrint jasperPrint = null;
		try {
			parametros.put("rutaImagen", rutaImagen);
			rutaReporte = rutaReporte + "plantilla_CO_FT_210.jasper";
			JRDataSource ds = new JREmptyDataSource();
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(rutaReporte);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, ds);

			return jasperPrint;
		} catch (JRException e) {
			throw e;
		}
	}

	/**
	 * @author christian Mauricio Tangarife Colorado cmtc4227
	 * @since 2016-08-23
	 * 
	 *        Metodo para generar el reporte Jasper de RESOLUCIÓN QUE RESUELVE
	 *        RECURSOS INTERPUESTOS EXTEMPORANEAMENTE(formato CO-FT-753)
	 * 
	 * @return
	 * @throws Exception
	 */
	public JasperPrint reporteResolucionExtemporaneamente_FT_753(String zonaElectoral, Date fecha,
			String nombreAsociado, String nombreComision, String resolucion, String diaPresentado, String dia,
			String mes, String anio, String rutaImagen, String rutaReporte) throws Exception {
		JasperPrint jasperPrint = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("zona", zonaElectoral);
			parameters.put("fechaActual", fecha);
			parameters.put("nombre", nombreAsociado);
			parameters.put("nombreComision", nombreComision);
			parameters.put("resolucion", resolucion);
			parameters.put("diaPresntado", diaPresentado);
			parameters.put("dia", dia);
			parameters.put("mes", mes);
			parameters.put("anio", anio);
			parameters.put("rutaImagen", rutaImagen);

			rutaReporte = rutaReporte + "plantilla_CO_FT_753.jasper";
			JRDataSource ds = new JREmptyDataSource();
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(rutaReporte);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
			

			return jasperPrint;

		} catch (JRException e) {
			throw e;
		}
	}

	/**
	 * @author christian Mauricio Tangarife Colorado cmtc4227
	 * @since 2016-08-24
	 * 
	 *        Metodo para generar el reporte Jasper de RESOLUCIÓN QUE DENIEGA UN
	 *        RECURSO DE REPOSICIÓN Y NO CONCEDE APELACIÓN POR NO SER SOLICITADO
	 *
	 * @return
	 * @throws Exception
	 */
	public JasperPrint reporteResolucionDeniega_FT_459(String zonaElectoral, String nombreAsociado,
			String resolucionImpugnada, Date fecha, String resolucion, String argumento, String nombrePresidente,
			String nombreSecretario, String rutaImagen, String rutaReporte) throws Exception {
		JasperPrint jasperPrint = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("zonaElectoral", zonaElectoral);
			parameters.put("nombreAccionante", nombreAsociado);
			parameters.put("resolucionImpugnada", resolucionImpugnada);
			parameters.put("fecha", fecha);
			parameters.put("resolucionNumero", resolucion);
			parameters.put("argumento", argumento);
			parameters.put("nombrePresidente", nombrePresidente);
			parameters.put("nombreSecretario", nombreSecretario);
			parameters.put("rutaImagen", rutaImagen);

			rutaReporte = rutaReporte + "plantilla_CO-FT-459.jasper";
			JRDataSource ds = new JREmptyDataSource();
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(rutaReporte);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);

			return jasperPrint;

		} catch (JRException e) {
			throw e;
		}
	}

	/**
	 * @author christian Mauricio Tangarife Colorado cmtc4227
	 * @since 2016-08-24
	 * 
	 *        Metodo para generar el reporte Jasper de RESOLUCIÓN QUE RESUELVE UN
	 *        RECURSO DE REPOSICIÓN EN CONTRA Y REMITE LA APELACIÓN
	 * @return
	 * @throws Exception
	 */
	public JasperPrint reporteResuelveReposicion_FT_460(String zonaElectoral, String nombreComision,
			String nombreAsociado, String resolucionImpugnada, Date fecha, String resolucion, String argumento,
			String nombrePresidente, String nombreSecretario, String rutaImagen, String rutaReporte) throws Exception {
		JasperPrint jasperPrint = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("zona", zonaElectoral);
			parameters.put("nombreComision", nombreComision);
			parameters.put("nombreAccionante", nombreAsociado);
			parameters.put("resolucionImpugnada", resolucionImpugnada);
			parameters.put("fecha", fecha);
			parameters.put("resolucion", resolucion);
			parameters.put("argumento", argumento);
			parameters.put("nombrePresidente", nombrePresidente);
			parameters.put("nombreSecretario", nombreSecretario);
			parameters.put("rutaImagen", rutaImagen);

			rutaReporte = rutaReporte + "plantilla_CO-FT-460.jasper";
			JRDataSource ds = new JREmptyDataSource();
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(rutaReporte);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);

			return jasperPrint;

		} catch (JRException e) {
			throw e;
		}
	}

	/**
	 * @author christian Mauricio Tangarife Colorado cmtc4227
	 * @since 2016-08-24
	 * 
	 *        Metodo para generar el reporte Jasper de RESOLUCIÓN QUE RESUELVE UN
	 *        RECURSO DE APELACIÓN FAVORABLE
	 * @return
	 * @throws Exception
	 */
	public JasperPrint reporteResuelveApelacion_FT_461(String acta, String nombreAsociado, String resolucionApelada,
			String resolucionComision, Date fecha, String actaTribunal, String argumento, String desicion,
			String nombrePresidente, String nombreSecretario, String resolucionNro, String rutaImagen, String rutaReporte) throws Exception {
		JasperPrint jasperPrint = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("acta", acta);
			parameters.put("nombreApelante", nombreAsociado);
			parameters.put("resolucionApelada", resolucionApelada);
			parameters.put("resolucionComision", resolucionComision);
			parameters.put("fecha", fecha);
			parameters.put("actaTribunal", actaTribunal);
			parameters.put("argumento", argumento);
			parameters.put("decision", desicion);
			parameters.put("resolucionNumero", resolucionNro);
			parameters.put("nombrePresidente", nombrePresidente);
			parameters.put("nombreSecretario", nombreSecretario);
			parameters.put("rutaImagen", rutaImagen);

			rutaReporte = rutaReporte + "plantilla_CO-FT-461.jasper";
			JRDataSource ds = new JREmptyDataSource();
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(rutaReporte);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);

			return jasperPrint;

		} catch (JRException e) {
			throw e;
		}
	}

	/**
	 * @author christian Mauricio Tangarife Colorado cmtc4227
	 * @since 2016-08-24
	 * 
	 *        Metodo para generar el reporte Jasper de RESOLUCIÓN QUE RESUELVE UN
	 *        RECURSO DE APELACIÓN EN CONTRA
	 * @return
	 * @throws Exception
	 */
	public JasperPrint reporteResuelveApelacionContra_FT_462(String acta, String nombreAsociado,
			String resolucionApelada, String resolucionComision, Date fecha, String actaTribunal, String argumento,
			String nombrePresidente, String nombreSecretario, String rutaImagen, String rutaReporte) throws Exception {
		JasperPrint jasperPrint = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("acta", acta);
			parameters.put("nombreApelante", nombreAsociado);
			parameters.put("resolucionApelada", resolucionApelada);
			parameters.put("resolucionComision", resolucionComision);
			parameters.put("fecha", fecha);
			parameters.put("actaTribunal", actaTribunal);
			parameters.put("argumento", argumento);
			parameters.put("nombrePresidente", nombrePresidente);
			parameters.put("nombreSecretario", nombreSecretario);
			parameters.put("rutaImagen", rutaImagen);

			rutaReporte = rutaReporte + "plantilla_CO-FT-462.jasper";
			JRDataSource ds = new JREmptyDataSource();
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(rutaReporte);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);

			return jasperPrint;

		} catch (JRException e) {
			throw e;
		}
	}

	/**
	 * @author christian Mauricio Tangarife Colorado cmtc4227
	 * @since 2016-08-24
	 * 
	 * @return
	 */
	public JasperPrint reporteInformacionPersonal_FT_174(String plancha, String zonaElectoral, String nombreAsociado,
			String cedulaAsociado, String fechaAntiguedad, String profesion, Date fechaTitulo, String estudios,
			String empresa, String cargo, String antiguedad, String ultimoCargo, String imagen, String rutaImagen,
			String rutaReporte, String tipoAsociado, String nombreReporteFoter) throws Exception {
		JasperPrint jasperPrint = null;
		SimpleDateFormat formateadorFecha = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("plancha", plancha);
			parameters.put("zona", zonaElectoral);
			parameters.put("nombre", nombreAsociado);
			parameters.put("cedula", cedulaAsociado);
			parameters.put("fecha_antiguedad", fechaAntiguedad);
			parameters.put("profesion", profesion);
			parameters.put("fecha_titulo", formateadorFecha.format(fechaTitulo));
			parameters.put("estudios", estudios);
			parameters.put("empresa", empresa);
			parameters.put("cargo", cargo);
			parameters.put("antiguedad", antiguedad);
			parameters.put("ultimo_cargo", ultimoCargo);
			parameters.put("imagen", imagen);
			parameters.put("rutaImagen", rutaImagen);
			parameters.put("tipoAsociado", tipoAsociado);
			parameters.put("nombreReporte", nombreReporteFoter);
			
			rutaReporte = rutaReporte + "plantilla_CO_FT_174.jasper";
			JRDataSource ds = new JREmptyDataSource();
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(rutaReporte);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);

			return jasperPrint;
		} catch (JRException e) {
			throw e;
		}
	}

	/**
	 * @author christian Mauricio Tangarife Colorado cmtc4227
	 * @since 2016-08-24
	 * 
	 * @return
	 */
	public JasperPrint reporteResolucionInadmisionPlancha_FT_209(String zonaElectoral, String anio, String mes,
			String dia, Date hora, String nombreAsociado, String cedulaAsociado, String resolucion, String acta,
			String fecha, String ciudad, String razon1, String razon2, String razon3, String razon4, String rutaImagen,
			String rutaReporte) throws Exception {
		JasperPrint jasperPrint = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("zona", zonaElectoral);
			parameters.put("año", anio);
			parameters.put("mes", mes);
			parameters.put("dia", dia);
			//parameters.put("hora", hora);
			parameters.put("nombre", nombreAsociado);
			//parameters.put("cedula", cedulaAsociado);
			parameters.put("resolucion", resolucion);
			parameters.put("acta", acta);
			parameters.put("fecha", fecha);
			parameters.put("ciudad", ciudad);
			parameters.put("razon1", razon1);
			parameters.put("razon2", razon2);
			parameters.put("razon3", razon3);
			parameters.put("razon4", razon4);
			parameters.put("rutaImagen", rutaImagen);

			rutaReporte = rutaReporte + "plantilla_CO_FT_209.jasper";

			JRDataSource ds = new JREmptyDataSource();
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(rutaReporte);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);

			return jasperPrint;
		} catch (JRException e) {
			throw e;
		}
	}

	/**
	 * @author christian Mauricio Tangarife Colorado cmtc4227
	 * @since 2016-08-24
	 * 
	 * @return
	 */
	public JasperPrint reporteCertificadoAcreditaOcupacion_FT_211(String ciudad, String anio, String mes, String dia,
			String nombreAsociado, String cedulaAsociado, String ciudadCedula, String ciudadFirma, String diaFirma,
			String mesFirma, String anioFirma, String rutaImagen, String rutaReporte) throws Exception {
		JasperPrint jasperPrint = null;
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ciudad", ciudad);
		parameters.put("año", anio);
		parameters.put("mes", mes);
		parameters.put("dia", dia);
		parameters.put("nombre", nombreAsociado);
		parameters.put("cedula", cedulaAsociado);
		parameters.put("ciudad_cedula", ciudadCedula);
		parameters.put("ciudad_firma", ciudadFirma);
		parameters.put("dias_firma", diaFirma);
		parameters.put("mes_firma", mesFirma);
		parameters.put("año_firma", anioFirma);
		parameters.put("rutaImagen", rutaImagen);

		rutaReporte = rutaReporte + "plantilla_CO-FT-211.jasper";
		JRDataSource ds = new JREmptyDataSource();
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(rutaReporte);
		jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);

		return jasperPrint;
	}

	/**
	 * @author christian Mauricio Tangarife Colorado cmtc4227
	 * @since 2016-08-24
	 * 
	 *        Metodo para generar el reporte Jasper de RESOLUCIÓN QUE DENIEGA UN
	 *        RECURSO DE REPOSICIÓN Y NO CONCEDE APELACIÓN POR NO SER SOLICITADO
	 *
	 * @return
	 * @throws Exception
	 */
	public JasperPrint reporteResolucionFavorable_FT_458(String zonaElectoral, String nombreAsociado,
			String resolucionImpugnada, Date fecha, String resolucionNumero, String argumento, String nombrePresidente,
			String nombreSecretario, String decision, String rutaImagen, String rutaReporte) throws Exception {
		JasperPrint jasperPrint = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("zonaElectoral", zonaElectoral);
			parameters.put("nombreAccionante", nombreAsociado);
			parameters.put("resolucionNumero", resolucionNumero);
			parameters.put("resolucionInpugnada", resolucionImpugnada);
			parameters.put("fecha", fecha);
			parameters.put("argumento", argumento);
			parameters.put("decision", decision);
			parameters.put("nombre_presidente", nombrePresidente);
			parameters.put("nombre_secretario", nombreSecretario);
			parameters.put("rutaImagen", rutaImagen);

			rutaReporte = rutaReporte + "CO-FT-458.jasper";
			JRDataSource ds = new JREmptyDataSource();
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(rutaReporte);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);

			return jasperPrint;

		} catch (JRException e) {
			throw e;
		}
	}
}
