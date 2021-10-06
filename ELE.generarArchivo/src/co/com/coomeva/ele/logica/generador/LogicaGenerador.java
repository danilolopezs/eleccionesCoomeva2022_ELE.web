package co.com.coomeva.ele.logica.generador;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

	private LoaderResourceElements loaderResourceElements = LoaderResourceElements.getInstance();

	private LogicaGenerador() {
	}

	// Patr�n Singular
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
					LoadProperties.getInstance().getLoadBundlePropiedades().getProperty("nombrePesta�a").toString(), 0);

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
					sheet.addCell(new Label(0, cont + 1, ttlZona + " N� " + elePlanchaDTO.getEleZonas().getCodZona()
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
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo L�pez</a> -
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
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo L�pez</a> -
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
					.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblZonaRegional");
			// String ttlFechaCorte =
			// loaderResourceElements.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB,
			// "lblFechaCorte");
			String ttlRegional = loaderResourceElements
					.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteRegional");
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
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo L�pez</a> -
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
			String ttlTotalHabiles = "Suma H�biles";
			String ttlPorcentajeHabiles = "% H�biles";
			String ttlTotalInhabiles = "Suma Inh�biles";
			String ttlPorcentajeInhabiles = "% Inh�biles";
			String ttlmuestra = "Muestra Inh�biles";
			String porcentajeMuestra = "%";
			String ttlTitulo = "RESUMEN DE HABILIDAD DE ASOCIADOS Y REVISI�N ALEATORIA DE INH�BILES";

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
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo L�pez</a> -
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
					.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblZonaRegional");
			String ttlZona = loaderResourceElements
					.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblZona");
			String ttlNumeroNovedades = "N�mero Novedades";
			String ttlPorcentajeNovedades = "% Novedades";
			String ttlTitulo = "RESUMEN DE NOVEDADES";

			WritableSheet sheet = workbook.createSheet("Resumen de Novedades", 0);
			sheet.setColumnView(0, cellview);
			// se fijan los ta�a�os de las columnas
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
			cellhdFormatBoldRightGreen.setFont(arial10fontBlackBold);
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
			cellhdFormatTitle.setFont(arial13fontBlackBold);
			cellhdFormatTitle.setAlignment(Alignment.CENTRE);
			cellhdFormatTitle.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellhdFormatTitle.setVerticalAlignment(VerticalAlignment.CENTRE);

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
			cellhdFormatBoldCenterNoBorders.setFont(arial10fontBlackBold);
			cellhdFormatBoldCenterNoBorders.setAlignment(Alignment.CENTRE);
			cellhdFormatBoldCenterNoBorders.setVerticalAlignment(VerticalAlignment.CENTRE);

			WritableCellFormat cellhdFormatAsociados = new WritableCellFormat();
			cellhdFormatAsociados.setWrap(true);
			cellhdFormatAsociados.setFont(arial10fontBlackBold);
			cellhdFormatAsociados.setAlignment(Alignment.CENTRE);
			cellhdFormatAsociados.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellhdFormatAsociados.setBackground(Colour.GRAY_25);
			cellhdFormatAsociados.setVerticalAlignment(VerticalAlignment.CENTRE);

			WritableCellFormat cellhdFormatAsociadosEspeciales = new WritableCellFormat();
			cellhdFormatAsociadosEspeciales.setWrap(true);
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

			String ttlTitulo = "C�lculo Cuociente Electoral";
			String ttlTotalAsoHab = "Total Asociados H�biles";
			String ttlTotalDelegElegir = "Total Delegados a Elegir";
			String ttlTotalDelegEspElegir = "Total Delegados Especiales (Promotores, Empleados)";
			String ttlTotalFinalDeleg = "Final Total Delegados a Elegir";
			String ttlCuociente = "Cuociente";

			String ttlRegional = "Regional";
			String ttlZona = loaderResourceElements
					.getKeyResourceValue(ConstantesProperties.NOMBRE_ARCHIVO_ETIQUETAS_WEB, "lblreporteZona");
			String ttlAsoHabiles = "Asociados H�biles";
			String ttlTotalAsoHabil = "Total Asociados H�biles";
			String ttlNovedadesAsoHabiles = "Novedades al corte de cada congelamiento de BD";
			String ttlTotalAsoHabiles = "Total Asociados H�biles a la Fecha";
			String ttlAsoEspHabil = "Asociados Especiales (Empleados y Promotores)";
			String ttlTotalAsoEspHabil = "Total Asociados Especiales H�biles";
			String ttlNovedadesAsoEspHabiles = "Novedades al corte de cada congelamiento de BD";
			String ttlTotalAsoEspHabiles = "Total Asociados H�biles a la Fecha";
			String ttlSumaHabiles = "Suma H�biles";
			String ttlFraccionDelegados = "Fracci�n Delegados x Zona Electoral";
			String ttlTotalDelegadosZona = "Total Delegados x Zona Electoral";
			String ttlFraccionRestantes = "Fracci�n Distribuci�n de Restantes (Ordenar de Mayor a Menor)";
			String ttlTotalDelegadosRestantes = "Total Delegados (distribuci�n restantes)";
			String ttlSumaTotalDelegados = "TOTAL DELEGADOS POR ZONA";
			String totalDelegados = "TOTAL DELEGADOS";
			String ttlTotal = "Total";
			String ttlValidacion = "Validaci�n 33%";
			String ttlMsgValidacion = "Ninguna regional se pasa del 33% del total de delegados,  no se debe redistribuir.";
			String ttlAsoEsp = "Empleados Asociados Especiales";

			// Sumatorias para el cuadrado de abajo con los resultados finales

			Double sumTotalAso = 0d;
			Double sumNovAso = 0d;
			Double sumTotalAsoHab = 0d;
			Double sumTotalAsoEsp = 0d;
			Double sumNovAsoEsp = 0d;
			Double sumTotalAsoHabEsp = 0d;
			Double sumTotalSumHab = 0d;
			Double sumTotalDelegZona = 0d;
			Double sumTotalDelegRest = 0d;
			Double sumTotalFinalDeleg = 0d;

			WritableSheet sheet = workbook.createSheet("Cuociente Electoral", 0);

			// Ajusto el ancho de las columnas
			sheet.setColumnView(0, 50);
			sheet.setColumnView(1, 22);
			sheet.setColumnView(2, 25);
			int indx = 13;
			for (int i = 3; i <= indx; i++) {
				sheet.setColumnView(i, 15);

			}
			sheet.setColumnView(7, 25);
			int cont = 10;

			sheet.addCell(new Label(0, 0, ttlTitulo, cellhdFormatTitle));
			sheet.addCell(new Label(1, 0, "", cellhdFormatTitle));
			sheet.mergeCells(0, 0, 1, 0);

			sheet.addCell(new Label(0, 2, ttlTotalAsoHab, cellhdFormat));
			sheet.addCell(new Label(1, 2, cuocienteElectoral.getTotalAsocHabiles().toString(), cellhdFormatBoldRight));
			sheet.addCell(new Label(0, 3, ttlTotalDelegElegir, cellhdFormat));
			sheet.addCell(new Label(1, 3, cuocienteElectoral.getTotalDelegadosElegir().toString(), cellhdFormatRight));
			sheet.addCell(new Label(0, 4, ttlTotalDelegEspElegir, cellhdFormat));
			sheet.addCell(
					new Label(1, 4, cuocienteElectoral.getTotalDelegadosEspeciales().toString(), cellhdFormatRight));
			sheet.addCell(new Label(0, 5, ttlTotalFinalDeleg, cellhdFormat));
			sheet.addCell(new Label(1, 5, cuocienteElectoral.getFinalTotalDelegadosElegir().toString(),
					cellhdFormatBoldRight));
			sheet.addCell(new Label(0, 6, ttlCuociente, cellhdFormatCuo));
			sheet.addCell(new Label(1, 6, cuocienteElectoral.getCuocienteElectoral().toString(), cellhdFormatCuo));

			sheet.addCell(new Label(0, cont, ttlRegional, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(0, cont + 1, "", cellhdFormatBoldCenterGreen));
			sheet.mergeCells(0, cont, 0, cont + 1);
			sheet.addCell(new Label(1, cont, ttlZona, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(1, cont + 1, "", cellhdFormatBoldCenterGreen));
			sheet.mergeCells(1, cont, 1, cont + 1);

			sheet.addCell(new Label(2, cont, ttlAsoHabiles, cellhdFormatAsociados));
			sheet.addCell(new Label(3, cont, "", cellhdFormatAsociados));
			sheet.addCell(new Label(4, cont, "", cellhdFormatAsociados));
			sheet.mergeCells(2, cont, 4, cont);

			sheet.addCell(new Label(2, cont + 1, ttlTotalAsoHabil, cellhdFormatAsociados));
			sheet.addCell(new Label(3, cont + 1, ttlNovedadesAsoHabiles, cellhdFormatAsociados));
			sheet.addCell(new Label(4, cont + 1, ttlTotalAsoHabiles, cellhdFormatAsociados));

			sheet.addCell(new Label(5, cont, ttlAsoEspHabil, cellhdFormatAsociadosEspeciales));
			sheet.addCell(new Label(6, cont, "", cellhdFormatAsociadosEspeciales));
			sheet.addCell(new Label(7, cont, "", cellhdFormatAsociadosEspeciales));
			sheet.mergeCells(5, cont, 7, cont);

			sheet.addCell(new Label(5, cont + 1, ttlTotalAsoEspHabil, cellhdFormatAsociadosEspeciales));
			sheet.addCell(new Label(6, cont + 1, ttlNovedadesAsoEspHabiles, cellhdFormatAsociadosEspeciales));
			sheet.addCell(new Label(7, cont + 1, ttlTotalAsoEspHabiles, cellhdFormatAsociadosEspeciales));

			sheet.addCell(new Label(8, cont, ttlSumaHabiles, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(8, cont + 1, "", cellhdFormatBoldCenterGreen));
			sheet.mergeCells(8, cont, 8, cont + 1);

			sheet.addCell(new Label(9, cont, ttlFraccionDelegados, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(9, cont + 1, "", cellhdFormatBoldCenterGreen));
			sheet.mergeCells(9, cont, 9, cont + 1);

			sheet.addCell(new Label(10, cont, ttlTotalDelegadosZona, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(10, cont + 1, "", cellhdFormatBoldCenterGreen));
			sheet.mergeCells(10, cont, 10, cont + 1);

			sheet.addCell(new Label(11, cont, ttlFraccionRestantes, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(11, cont + 1, "", cellhdFormatBoldCenterGreen));
			sheet.mergeCells(11, cont, 11, cont + 1);

			sheet.addCell(new Label(12, cont, ttlTotalDelegadosRestantes, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(12, cont + 1, "", cellhdFormatBoldCenterGreen));
			sheet.mergeCells(12, cont, 12, cont + 1);

			sheet.addCell(new Label(13, cont, ttlSumaTotalDelegados, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(13, cont + 1, "", cellhdFormatBoldCenterGreen));
			sheet.mergeCells(13, cont, 13, cont + 1);
			cont++;
			cont++;

			sheet.getSettings().setVerticalFreeze(cont);

			for (EleCuocienteDelegadosZona dto : list) {
				sheet.addCell(new Label(0, cont, "REGIONAL " + dto.getDescCodRegional(), cellhdFormat));
				sheet.addCell(new Label(1, cont, dto.getDescCodZona(), cellhdFormat));

				sheet.addCell(new Label(2, cont,
						String.valueOf((dto.getSumaHabiles() - dto.getSumaNovedades() + dto.getSumaEspHabiles())),
						cellhdFormatRight));
				sheet.addCell(new Label(3, cont, dto.getSumaNovedades().toString(), cellhdFormatRight));
				sheet.addCell(new Label(4, cont, String.valueOf(dto.getSumaHabiles() + dto.getSumaEspHabiles()),
						cellhdFormatRight));

				sheet.addCell(new Label(5, cont, String.valueOf((dto.getSumaEspHabiles() - dto.getSumaNovedadesEsp())),
						cellhdFormatRight));
				sheet.addCell(new Label(6, cont, dto.getSumaNovedadesEsp().toString(), cellhdFormatRight));
				sheet.addCell(new Label(7, cont, dto.getSumaEspHabiles().toString(), cellhdFormatRight));

				sheet.addCell(new Label(8, cont, dto.getSumaTotalHabiles().toString(), cellhdFormatRight));
				sheet.addCell(new Label(9, cont,
						String.valueOf(LogicaInformeResumen.round(dto.getFraccion() + dto.getDelegadosDirectos(), 4)),
						cellhdFormatRight));
				sheet.addCell(new Label(10, cont, dto.getDelegadosDirectos().toString(), cellhdFormatRight));
				sheet.addCell(new Label(11, cont, dto.getFraccion().toString(), cellhdFormatRight));
				sheet.addCell(new Label(12, cont, dto.getDelegadosResiduo().toString(), cellhdFormatRight));
				sheet.addCell(new Label(13, cont, dto.getTotalDelegadosZona().toString(), cellhdFormatRight));

				sumTotalAso += (dto.getSumaHabiles() - dto.getSumaNovedades() + dto.getSumaEspHabiles());
				sumNovAso += dto.getSumaNovedades();
				sumTotalAsoHab += (dto.getSumaHabiles() + dto.getSumaEspHabiles());
				sumTotalAsoEsp += (dto.getSumaEspHabiles() - dto.getSumaNovedadesEsp());
				sumNovAsoEsp += dto.getSumaNovedadesEsp();
				sumTotalAsoHabEsp += dto.getSumaEspHabiles();
				sumTotalSumHab += dto.getSumaTotalHabiles();
				sumTotalDelegZona += dto.getDelegadosDirectos();
				sumTotalDelegRest += dto.getDelegadosResiduo();
				sumTotalFinalDeleg += dto.getTotalDelegadosZona();

				cont++;
			}
			sheet.addCell(new Label(0, cont, "", cellhdFormat));
			sheet.addCell(new Label(1, cont, totalDelegados, cellhdFormatBoldLeftYellow));
			sheet.addCell(new Label(2, cont, sumTotalAso.toString(), cellhdFormatBoldRightYellow));
			sheet.addCell(new Label(3, cont, sumNovAso.toString(), cellhdFormatBoldRightYellow));
			sheet.addCell(new Label(4, cont, sumTotalAsoHab.toString(), cellhdFormatBoldRightYellow));
			sheet.addCell(new Label(5, cont, sumTotalAsoEsp.toString(), cellhdFormatBoldRightYellow));
			sheet.addCell(new Label(6, cont, sumNovAsoEsp.toString(), cellhdFormatBoldRightYellow));
			sheet.addCell(new Label(7, cont, sumTotalAsoHabEsp.toString(), cellhdFormatBoldRightYellow));
			sheet.addCell(new Label(8, cont, sumTotalSumHab.toString(), cellhdFormatBoldRightYellow));
			sheet.addCell(new Label(9, cont, "", cellhdFormatBoldRight));
			sheet.addCell(new Label(10, cont, sumTotalDelegZona.toString(), cellhdFormatBoldRightGreen));
			sheet.addCell(new Label(11, cont, "", cellhdFormatBoldRight));
			sheet.addCell(new Label(12, cont, sumTotalDelegRest.toString(), cellhdFormatBoldRightGreen));
			sheet.addCell(new Label(13, cont, sumTotalFinalDeleg.toString(), cellhdFormatBoldRightGreen));

			cont += 2;
			indx = 1;

			sheet.addCell(new Label(indx, cont, ttlRegional, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(indx + 1, cont, "", cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(indx + 2, cont, ttlTotal, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(indx + 3, cont, ttlValidacion, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(indx + 4, cont, ttlMsgValidacion, cellhdFormatCenter));
			sheet.addCell(new Label(indx + 6, cont, ttlAsoEsp, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(indx + 7, cont, ttlTotal, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(indx + 8, cont, ttlValidacion, cellhdFormatBoldCenterGreen));
			sheet.addCell(new Label(indx + 9, cont, ttlMsgValidacion, cellhdFormatCenter));
			// Creo las celdas vac�as para agruparlas
			for (int i = 1; i < 7; i++) {
				sheet.addCell(new Label(indx + 4, cont + i, "", cellhdFormatCenter));
				sheet.addCell(new Label(indx + 5, cont + i, "", cellhdFormatCenter));
				sheet.addCell(new Label(indx + 9, cont + i, "", cellhdFormatCenter));
				sheet.addCell(new Label(indx + 10, cont + i, "", cellhdFormatCenter));
				sheet.addCell(new Label(indx + 1, cont + i, "", cellhdFormat));

			}
			sheet.mergeCells(indx + 9, cont, indx + 10, cont + 6);
			sheet.mergeCells(indx + 4, cont, indx + 5, cont + 6);
			sheet.mergeCells(indx, cont, indx + 1, cont);

			cont++;
			// Agrego las regionales
			int sumaTotal = 0;
			int sumaPorcentajes = 0;
			Double sumaRelacionPorc = 0d;
			int sumaHabiles = 0;
			for (EleCuocienteRegional dto : regList) {

				sheet.addCell(new Label(indx, cont, "TOTAL " + dto.getDesRegional(), cellhdFormat));
				sheet.mergeCells(indx, cont, indx + 1, cont);
				sheet.addCell(new Label(indx + 2, cont, dto.getTotalDelegados().toString(), cellhdFormatCenter));
				sheet.addCell(
						new Label(indx + 3, cont, dto.getRelacionDelegados().toString() + "%", cellhdFormatCenter));
				sheet.addCell(new Label(indx + 6, cont, "1", cellhdFormatCenter));
				sheet.addCell(new Label(indx + 7, cont, String.valueOf(dto.getTotalDelegados().intValue() + 1),
						cellhdFormatBoldCenter));
				sheet.addCell(new Label(indx + 8, cont,
						String.valueOf((dto.getTotalDelegados().intValue() + 1)) + ",00%", cellhdFormatRight));
				sumaTotal += dto.getTotalDelegados().intValue();
				sumaRelacionPorc += dto.getRelacionDelegados();
				sumaHabiles += (dto.getTotalDelegados() + 1);
				cont++;
			}

			sheet.addCell(new Label(indx + 2, cont, String.valueOf(sumaTotal), cellhdFormatBoldCenterNoBorders));
			sheet.addCell(new Label(indx + 3, cont,
					String.valueOf((int) LogicaInformeResumen.round(sumaRelacionPorc, 0)) + "%",
					cellhdFormatBoldCenterNoBorders));
			sheet.addCell(new Label(indx + 7, cont, String.valueOf(sumaHabiles), cellhdFormatBoldCenterNoBorders));

			workbook.write();
			workbook.close();

		} catch (Exception e) {
			throw e;
		}

		return file;

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
			String tFechaInscripcion = "Fecha Inscripci�n";
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
	 *        Metodo para generar el reporte Jasper de RESOLUCI�N DE ADMISI�N DE
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
			String numResolucion, String numActa, Date fecha, String ciudadZona, String dia, String mes, String anio,
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
	 *        Metodo para generar el reporte Jasper de RESOLUCI�N DE RECHAZO DE
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
			String numResolucion, String cedulaAsociado, String numActa, Date fecha, String dia, String mes,
			String anio, String razones, String mesActa, String rutaImagen, String rutaReporte) throws Exception {
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
			parameters.put("razones", razones);
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
	 *        Metodo para generar el reporte Jasper de CERTIFICACI�N DE CUMPLIMIENTO
	 *        PARA SER ELEGIDO DELEGADO(formato CO-FT-176)
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	public JasperPrint reporteCumplimientoDelegado_FT_176(String nombreAsociado, String numPlancha,
			String nombreRepresen, List<String> respustas, String rutaImagen, String rutaReporte) throws Exception {
		JasperPrint jasperPrint = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("nombre", nombreAsociado);
			parameters.put("plancha", numPlancha);
			parameters.put("nombreRepresen", nombreRepresen);
			parameters.put("rutaImagen", rutaImagen);

			if (respustas != null) {
				for (int i = 0; i < respustas.size(); i++) {

					// pregunta 1
					if (i == 0 && respustas.get(i).equalsIgnoreCase("S")) {
						parameters.put("1SI", "X");
					} else if (i == 0 && respustas.get(i).equalsIgnoreCase("N")) {
						parameters.put("1NO", "X");
					}

					if (i == 1 && respustas.get(i).equalsIgnoreCase("S")) {
						parameters.put("2SI", "X");
					} else if (i == 1 && respustas.get(i).equalsIgnoreCase("N")) {
						parameters.put("2NO", "X");
					}

					if (i == 2 && respustas.get(i).equalsIgnoreCase("S")) {
						parameters.put("3SI", "X");
					} else if (i == 2 && respustas.get(i).equalsIgnoreCase("N")) {
						parameters.put("3NO", "X");
					}

					if (i == 3 && respustas.get(i).equalsIgnoreCase("S")) {
						parameters.put("4SI", "X");
					} else if (i == 3 && respustas.get(i).equalsIgnoreCase("N")) {
						parameters.put("4NO", "X");
					}

					if (i == 3 && respustas.get(i).equalsIgnoreCase("S")) {
						parameters.put("4SI", "X");
					} else if (i == 3 && respustas.get(i).equalsIgnoreCase("N")) {
						parameters.put("4NO", "X");
					}

					if (i == 4 && respustas.get(i).equalsIgnoreCase("S")) {
						parameters.put("5SI", "X");
					} else if (i == 4 && respustas.get(i).equalsIgnoreCase("N")) {
						parameters.put("5NO", "X");
					}

					if (i == 5 && respustas.get(i).equalsIgnoreCase("S")) {
						parameters.put("6SI", "X");
					} else if (i == 5 && respustas.get(i).equalsIgnoreCase("N")) {
						parameters.put("6NO", "X");
					}

					if (i == 6 && respustas.get(i).equalsIgnoreCase("S")) {
						parameters.put("7SI", "X");
					} else if (i == 6 && respustas.get(i).equalsIgnoreCase("N")) {
						parameters.put("7NO", "X");
					}

					if (i == 7 && respustas.get(i).equalsIgnoreCase("S")) {
						parameters.put("8SI", "X");
					} else if (i == 7 && respustas.get(i).equalsIgnoreCase("N")) {
						parameters.put("8NO", "X");
					}

					if (i == 8 && respustas.get(i).equalsIgnoreCase("S")) {
						parameters.put("9SI", "X");
					} else if (i == 8 && respustas.get(i).equalsIgnoreCase("N")) {
						parameters.put("9NO", "X");
					}

					if (i == 9 && respustas.get(i).equalsIgnoreCase("S")) {
						parameters.put("10SI", "X");
					} else if (i == 9 && respustas.get(i).equalsIgnoreCase("N")) {
						parameters.put("10NO", "X");
					}

					if (i == 10 && respustas.get(i).equalsIgnoreCase("S")) {
						parameters.put("11SI", "X");
					} else if (i == 10 && respustas.get(i).equalsIgnoreCase("N")) {
						parameters.put("11NO", "X");
					}

					if (i == 11 && respustas.get(i).equalsIgnoreCase("S")) {
						parameters.put("12SI", "X");
					} else if (i == 11 && respustas.get(i).equalsIgnoreCase("N")) {
						parameters.put("12NO", "X");
					}
				}
			}

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
	 *        Metodo para generar el reporte Jasper de CONSTANCIA DE RADICACI�N Y
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
	public JasperPrint reporteInscripcionPlanchas_FT_210(String zonaElectoral, String ciudad, Date fecha,
			String rutaImagen, String rutaReporte) throws Exception {
		JasperPrint jasperPrint = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("zona", zonaElectoral);
			parameters.put("ciudad", ciudad);
			parameters.put("fechaActual", fecha);
			parameters.put("rutaImagen", rutaImagen);
			parameters.put("SUBREPORT_DIR", rutaReporte);

			rutaReporte = rutaReporte + "plantilla_CO_FT_588.jasper";
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
	 *        Metodo para generar el reporte Jasper de RESOLUCI�N QUE RESUELVE
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
	 *        Metodo para generar el reporte Jasper de RESOLUCI�N QUE DENIEGA UN
	 *        RECURSO DE REPOSICI�N Y NO CONCEDE APELACI�N POR NO SER SOLICITADO
	 *
	 * @return
	 * @throws Exception
	 */
	public JasperPrint reporteResolucionDeniega_FT_459(String zonaElectoral, String nombreComision,
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
	 *        Metodo para generar el reporte Jasper de RESOLUCI�N QUE RESUELVE UN
	 *        RECURSO DE REPOSICI�N EN CONTRA Y REMITE LA APELACI�N
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
	 *        Metodo para generar el reporte Jasper de RESOLUCI�N QUE RESUELVE UN
	 *        RECURSO DE APELACI�N FAVORABLE
	 * @return
	 * @throws Exception
	 */
	public JasperPrint reporteResuelveApelacion_FT_461(String acta, String nombreAsociado, String resolucionApelada,
			String resolucionComision, Date fecha, String actaTribunal, String argumento, String desicion,
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
			parameters.put("decision", desicion);
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
	 *        Metodo para generar el reporte Jasper de RESOLUCI�N QUE RESUELVE UN
	 *        RECURSO DE APELACI�N EN CONTRA
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
			String empresa, String cargo, String antiguedad, String ultimoCargo, String imagen, String formulario,
			String rutaReporte) throws Exception {
		JasperPrint jasperPrint = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("plancha", plancha);
			parameters.put("zona", zonaElectoral);
			parameters.put("nombre", nombreAsociado);
			parameters.put("cedula", cedulaAsociado);
			parameters.put("fecha_antiguedad", fechaAntiguedad);
			parameters.put("profesion", profesion);
			parameters.put("fecha_titulo", fechaTitulo);
			parameters.put("estudios", estudios);
			parameters.put("empresa", empresa);
			parameters.put("cargo", cargo);
			parameters.put("antiguedad", antiguedad);
			parameters.put("ultimo_cargo", ultimoCargo);
			parameters.put("imagen", imagen);
			parameters.put("formulario", formulario);

			rutaReporte = rutaReporte + "CO-FT-174.jasper";
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
			Date fecha, String ciudad, String razon1, String razon2, String razon3, String razon4, String formulario,
			String rutaReporte) throws Exception {
		JasperPrint jasperPrint = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("zona", zonaElectoral);
			parameters.put("a�o", anio);
			parameters.put("mes", mes);
			parameters.put("dia", dia);
			parameters.put("hora", hora);
			parameters.put("nombre", nombreAsociado);
			parameters.put("cedula", cedulaAsociado);
			parameters.put("resolucion", resolucion);
			parameters.put("acta", acta);
			parameters.put("fecha", fecha);
			parameters.put("ciudad", ciudad);
			parameters.put("razon1", razon1);
			parameters.put("razon2", razon2);
			parameters.put("razon3", razon3);
			parameters.put("razon4", razon4);
			parameters.put("formulario", formulario);

			rutaReporte = rutaReporte + "CO-FT-209.jasper";
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
			String mesFirma, String anioFirma, String formulario, String rutaReporte) throws Exception {
		JasperPrint jasperPrint = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("ciudad", ciudad);
			parameters.put("a�o", anio);
			parameters.put("mes", mes);
			parameters.put("dia", dia);
			parameters.put("nombre", nombreAsociado);
			parameters.put("cedula", cedulaAsociado);
			parameters.put("ciudad_cedula", ciudadCedula);
			parameters.put("ciudad_firma", ciudadFirma);
			parameters.put("dias_firma", diaFirma);
			parameters.put("mes_firma", mesFirma);
			parameters.put("a�o_firma", anioFirma);
			parameters.put("formulario", formulario);

			rutaReporte = rutaReporte + "CO-FT-211.jasper";
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
	 *        Metodo para generar el reporte Jasper de RESOLUCI�N QUE DENIEGA UN
	 *        RECURSO DE REPOSICI�N Y NO CONCEDE APELACI�N POR NO SER SOLICITADO
	 *
	 * @return
	 * @throws Exception
	 */
	public JasperPrint reporteResolucionFavorable_FT_458(String zonaElectoral, String nombreComision,
			String nombreAsociado, String resolucionImpugnada, Date fecha, String resolucion, String argumento,
			String nombrePresidente, String nombreSecretario, String resolucionMod, String decision, String rutaImagen,
			String rutaReporte) throws Exception {
		JasperPrint jasperPrint = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("zona", zonaElectoral);
			parameters.put("nombreComision", nombreComision);
			parameters.put("nombreAccionante", nombreAsociado);
			parameters.put("resolucion", resolucionImpugnada);
			parameters.put("fecha", fecha);
			parameters.put("resolucion_mod", resolucionMod);
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
