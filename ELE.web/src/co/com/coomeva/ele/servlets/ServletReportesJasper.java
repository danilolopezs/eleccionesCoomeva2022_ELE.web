package co.com.coomeva.ele.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import co.com.coomeva.ele.delegado.generador.DelegadoGenerador;
import co.com.coomeva.ele.dto.PreguntasFormulario176DTO;
import co.com.coomeva.ele.util.PathRequest;

public class ServletReportesJasper extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7247182798335376457L;

	/**
	 * Constructor of the object.
	 */
	public ServletReportesJasper() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request  the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException      if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			executeReport(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request  the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException      if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			executeReport(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	/**
	 * @refactor GTC CORPORATION - Danilo López Sandoval - 26/10/2021
	 * @author Christian Mauricio Tangarife Colorado cmtc4227
	 *         Metodo para ejecutar todos los reportes jaspers
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	public void executeReport(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		JasperPrint jasperPrint = null;
		HttpSession session = req.getSession();

		String codigoReporte = (String) req.getSession().getAttribute("codigoReporte");
		String rutaImagen = PathRequest.getInstance().getPathServerContextPath(getServletContext(),
				"imagenes/reportes");
		String rutaReporte = getServletContext().getRealPath("WEB-INF/reports/");
		rutaReporte = rutaReporte + "/";

		try {

			if (codigoReporte != null && !codigoReporte.isEmpty()) {
				String nombreReporte = "";

				String zonaElectoral;
				Date fecha;
				String nombreAsociado;
				String nombreComision;
				String resolucion;
				String dia;
				String mes;
				String anio;
				String numResolucion;
				String cedulaAsociado;

				switch (Integer.parseInt(codigoReporte)) {

				case 172:
					// Resolución de Admisión CO-FT-172
					nombreReporte = "RESOLUCION ADMISION DE PLANCHA";
					zonaElectoral = (String) req.getSession().getAttribute("zonaElectoral");
					nombreAsociado = (String) req.getSession().getAttribute("nombreAsociado");
					numResolucion = (String) req.getSession().getAttribute("numResolucion");
					String numActa = (String) req.getSession().getAttribute("numActa");
					fecha = (Date) req.getSession().getAttribute("fecha");
					String ciudadZona = (String) req.getSession().getAttribute("ciudadZona");
					dia = (String) req.getSession().getAttribute("dia");
					mes = (String) req.getSession().getAttribute("mes");
					anio = (String) req.getSession().getAttribute("anio");

					jasperPrint = DelegadoGenerador.getInstance().reporteResolucionAdmisionPlanchas_FT_172(
							zonaElectoral, nombreAsociado, numResolucion, numActa, fecha, ciudadZona, dia, mes, anio,
							rutaImagen, rutaReporte);

					removerAtributos(session, "zonaElectoral", "nombreAsociado", "numResolucion", "numActa", "fecha",
							"ciudadZona", "dia", "mes", "anio");
					break;
				case 173:
					// resolución de Rechazo de Planchas CO-FT-173
					nombreReporte = "RESOLUCIÓN DE RECHAZO DE PLANCHAS";
					zonaElectoral = (String) req.getSession().getAttribute("zonaElectoral");
					nombreAsociado = (String) req.getSession().getAttribute("nombreAsociado");
					cedulaAsociado = (String) req.getSession().getAttribute("cedulaAsociado");
					numResolucion = (String) req.getSession().getAttribute("numResolucion");
					numActa = (String) req.getSession().getAttribute("numActa");
					fecha = (Date) req.getSession().getAttribute("fecha");
					dia = (String) req.getSession().getAttribute("dia");
					mes = (String) req.getSession().getAttribute("mes");
					anio = (String) req.getSession().getAttribute("anio");
					String razones = (String) req.getSession().getAttribute("razones");
					String mesActa = (String) req.getSession().getAttribute("mesActa");

					jasperPrint = DelegadoGenerador.getInstance().reporteResolucionRechazoPlanchas_FT_173(zonaElectoral,
							nombreAsociado, numResolucion, cedulaAsociado, numActa, fecha, dia, mes, anio, razones,
							mesActa, rutaImagen, rutaReporte);

					removerAtributos(session, "zonaElectoral", "nombreAsociado", "cedulaAsociado", "numResolucion",
							"numActa", "fecha", "dia", "mes", "anio", "razones", "mesActa");
					break;
				case 174:
					// Informacion Personal del caneza de Plancha CO-FT-174
					nombreReporte = "INFORMACION PERSONASL DEL CABEZA DE PLANCHA";
					String plancha = (String) req.getSession().getAttribute("plancha");
					zonaElectoral = (String) req.getSession().getAttribute("zonaElectoral");
					nombreAsociado = (String) req.getSession().getAttribute("nombreAsociado");
					cedulaAsociado = (String) req.getSession().getAttribute("cedulaAsociado");
					String fechaAntiguedad = (String) req.getSession().getAttribute("fechaAntiguedad");
					String profesion = (String) req.getSession().getAttribute("profesion");
					Date fechaTitulo = (Date) req.getSession().getAttribute("fechaTitulo");
					String estudios = (String) req.getSession().getAttribute("estudios");
					String empresa = (String) req.getSession().getAttribute("empresa");
					String cargo = (String) req.getSession().getAttribute("cargo");
					String antiguedad = (String) req.getSession().getAttribute("antiguedad");
					String ultimoCargo = (String) req.getSession().getAttribute("ultimoCargo");
					String imagen = (String) req.getSession().getAttribute("foto");
					Boolean esSuplene = (Boolean) req.getSession().getAttribute("esSuplente");
					imagen = PathRequest.getInstance().getPathServerContextPath(getServletContext(), "plantilla")
							+ "/foto" + imagen + ".jpg";
					String formulario = rutaImagen + "/CO-FT-174.png";
					if (esSuplene) {
						formulario = rutaImagen + "/CO-FT-766.png";
					}

					jasperPrint = DelegadoGenerador.getInstance().reporteInformacionPersonal_FT_174(plancha,
							zonaElectoral, nombreAsociado, cedulaAsociado, fechaAntiguedad, profesion, fechaTitulo,
							estudios, empresa, cargo, antiguedad, ultimoCargo, imagen, formulario, rutaReporte);

					removerAtributos(session, "plancha", "zonaElectoral", "nombreAsociado", "cedulaAsociado",
							"fechaAntiguedad", "profesion", "fechaTitulo", "estudios", "empresa", "cargo", "antiguedad",
							"ultimoCargo", "foto");
					break;

				case 176:
					// CERTIFICACIÓN DE CUMPLIMIENTO PARA SER ELEGIDO DELEGADO CO-FT-176
					nombreReporte = "CERTIFICACIÓN DE CUMPLIMIENTO PARA SER ELEGIDO DELEGADO";
					nombreAsociado = (String) req.getSession().getAttribute("nombreAsociado");
					String numPlancha = (String) req.getSession().getAttribute("numPlancha");
					String nombreRepresen = (String) req.getSession().getAttribute("nombreRepresen");
					List<PreguntasFormulario176DTO> respustasDTO = (List<PreguntasFormulario176DTO>) req.getSession()
							.getAttribute("preguntas");

					List<String> respustas = new ArrayList<String>();
					if (respustasDTO != null) {
						for (PreguntasFormulario176DTO respuesta : respustasDTO) {
							if (respuesta.getRespuesta() == 0) {
								respustas.add("N");
							} else {
								respustas.add("S");
							}
						}
					}

					jasperPrint = DelegadoGenerador.getInstance().reporteCumplimientoDelegado_FT_176(nombreAsociado,
							numPlancha, nombreRepresen, respustas, rutaImagen, rutaReporte);

					removerAtributos(session, "nombreAsociado", "numPlancha", "nombreRepresen", "preguntas");
					break;

				case 208:
					// CONSTANCIA DE RADICACIÓN Y RECIBO DE PLANCHAS CO-FT-208
					nombreReporte = "CERTIFICACIÓN DE CUMPLIMIENTO PARA SER ELEGIDO DELEGADO";
					zonaElectoral = (String) req.getSession().getAttribute("zonaElectoral");
					fecha = (Date) req.getSession().getAttribute("fecha");
					nombreAsociado = (String) req.getSession().getAttribute("nombreAsociado");
					String numComision = (String) req.getSession().getAttribute("numComision");
					String ciudad = (String) req.getSession().getAttribute("ciudad");
					cedulaAsociado = (String) req.getSession().getAttribute("cedulaAsociado");
					String ciudadCedula = (String) req.getSession().getAttribute("ciudadCedula");
					dia = (String) req.getSession().getAttribute("dia");
					mes = (String) req.getSession().getAttribute("mes");
					anio = (String) req.getSession().getAttribute("anio");
					String nombreEntrega = (String) req.getSession().getAttribute("nombreEntrega");
					String nombreRecibe = (String) req.getSession().getAttribute("nombreRecibe");

					jasperPrint = DelegadoGenerador.getInstance().reporteConstanciaRadicacionPlanchas_FT_208(
							zonaElectoral, fecha, numComision, ciudad, nombreAsociado, cedulaAsociado, ciudadCedula,
							dia, mes, anio, nombreEntrega, nombreRecibe, rutaImagen, rutaReporte);

					removerAtributos(session, "zonaElectoral", "nombreAsociado", "cedulaAsociado", "ciudad",
							"ciudadCedula", "fecha", "dia", "mes", "anio", "mes", "nombreEntrega", "nombreRecibe");
					break;

				case 209:
					// RESOLUCION INADMISION PLANCHA CO-FT-209
					nombreReporte = "RESOLUCION INADMISION PLANCHA";
					zonaElectoral = (String) req.getSession().getAttribute("zonaElectoral");
					dia = (String) req.getSession().getAttribute("dia");
					mes = (String) req.getSession().getAttribute("mes");
					anio = (String) req.getSession().getAttribute("anio");
					Date hora = (Date) req.getSession().getAttribute("hora");
					nombreAsociado = (String) req.getSession().getAttribute("nombreAsociado");
					cedulaAsociado = (String) req.getSession().getAttribute("cedulaAsociado");
					resolucion = (String) req.getSession().getAttribute("resolucion");
					String acta = (String) req.getSession().getAttribute("acta");
					fecha = (Date) req.getSession().getAttribute("fecha");
					ciudad = (String) req.getSession().getAttribute("ciudad");
					String razon1 = (String) req.getSession().getAttribute("razon1");
					String razon2 = (String) req.getSession().getAttribute("razon2");
					String razon3 = (String) req.getSession().getAttribute("razon3");
					String razon4 = (String) req.getSession().getAttribute("razon4");
					formulario = rutaImagen + "/CO-FT-209.png";

					jasperPrint = DelegadoGenerador.getInstance().reporteResolucionInadmisionPlancha_FT_209(
							zonaElectoral, anio, mes, dia, hora, nombreAsociado, cedulaAsociado, resolucion, acta,
							fecha, ciudad, razon1, razon2, razon3, razon4, formulario, rutaReporte);

					removerAtributos(session, "zonaElectoral", "dia", "mes", "anio", "hora", "nombreAsociado",
							"cedulaAsociado", "resolucion", "acta", "fecha", "ciudad", "razon1", "razon2", "razon3",
							"razon4");
					break;

				case 210:
					// INSCRIPCIÓN DE PLANCHAS CO-FT-210
					nombreReporte = "INSCRIPCIÓN DE PLANCHAS";
					zonaElectoral = (String) req.getSession().getAttribute("zonaElectoral");
					fecha = (Date) req.getSession().getAttribute("fecha");
					ciudad = (String) req.getSession().getAttribute("ciudad");

					jasperPrint = DelegadoGenerador.getInstance().reporteInscripcionPlanchas_FT_210(zonaElectoral,
							ciudad, fecha, rutaImagen, rutaReporte);

					removerAtributos(session, "zonaElectoral", "fecha", "ciudad");
					break;

				case 211:
					// Declaración para acreditar ocupación y cumplimiento de requisitos CO-FT-211
					nombreReporte = "CETIFICADO PARA ACREDITAR OCUPACIÓN Y CUMPLIMIENTO DE REQUISITOS";
					ciudad = (String) req.getSession().getAttribute("ciudad");
					dia = (String) req.getSession().getAttribute("dia");
					mes = (String) req.getSession().getAttribute("mes");
					anio = (String) req.getSession().getAttribute("anio");
					nombreAsociado = (String) req.getSession().getAttribute("nombreAsociado");
					cedulaAsociado = (String) req.getSession().getAttribute("cedulaAsociado");
					ciudadCedula = (String) req.getSession().getAttribute("ciudadCedula");
					String ciudadFirma = (String) req.getSession().getAttribute("ciudadFirma");
					String diaFirma = (String) req.getSession().getAttribute("diaFirma");
					String mesFirma = (String) req.getSession().getAttribute("mesFirma");
					String anioFirma = (String) req.getSession().getAttribute("anioFirma");
					//formulario = rutaImagen + "/CO-FT-211.png";

					jasperPrint = DelegadoGenerador.getInstance().reporteCertificadoAcreditaOcupacion_FT_211(ciudad,
							anio, mes, dia, nombreAsociado, cedulaAsociado, ciudadCedula, ciudadFirma, diaFirma,
							mesFirma, anioFirma, rutaImagen, rutaReporte);

					removerAtributos(session, "ciudad", "dia", "mes", "anio", "hora", "nombreAsociado",
							"cedulaAsociado", "ciudadCedula", "ciudadFirma", "diaFirma", "mesFirma", "anioFirma");
					break;

				case 458:
					// RESOLUCION QUE RESULELVE RECURSOSO DE REPOSICIÓN FAVORABLEMENTE CO-FT-458
					nombreReporte = "RESOLUCION QUE RESULELVE RECURSOSO DE REPOSICIÓN FAVORABLEMENTE";
					zonaElectoral = (String) req.getSession().getAttribute("zonaElectoral");
					fecha = (Date) req.getSession().getAttribute("fecha");
					nombreComision = (String) req.getSession().getAttribute("nombreComision");
					nombreAsociado = (String) req.getSession().getAttribute("nombreAsociado");
					String resolucionImpugnada = (String) req.getSession().getAttribute("resolucionImpugnada");
					String resolucionMod = (String) req.getSession().getAttribute("resolucion");
					String argumento = (String) req.getSession().getAttribute("argumento");
					String decision = (String) req.getSession().getAttribute("decision");
					String nombrePresidente = (String) req.getSession().getAttribute("nombrePresidente");
					String nombreSecretario = (String) req.getSession().getAttribute("nombreSecretario");

					jasperPrint = DelegadoGenerador.getInstance().reporteResolucionFavorable_FT_458(zonaElectoral,
							nombreComision, nombreAsociado, resolucionImpugnada, fecha, resolucionMod, argumento,
							nombrePresidente, nombreSecretario, decision, rutaImagen, rutaReporte);

					removerAtributos(session, "zonaElectoral", "fecha", "nombreComision", "nombreAsociado",
							"resolucionImpugnada", "resolucion", "argumento", "decision", "nombrePresidente",
							"nombreSecretario");
					break;

				case 459:
					// RESOLUCIÓN QUE DENIEGA UN RECURSO DE REPOSICIÓN Y NO CONCEDE APELACIÓN POR NO SER SOLICITADO CO-FT-459
					nombreReporte = "RESOLUCIÓN QUE DENIEGA UN RECURSO DE REPOSICIÓNY  NO CONCEDE APELACIÓN POR NO SER SOLICITADO";
					zonaElectoral = (String) req.getSession().getAttribute("zonaElectoral");
					fecha = (Date) req.getSession().getAttribute("fecha");
					nombreComision = (String) req.getSession().getAttribute("nombreComision");
					nombreAsociado = (String) req.getSession().getAttribute("nombreAsociado");
					resolucionImpugnada = (String) req.getSession().getAttribute("resolucionImpugnada");
					resolucion = (String) req.getSession().getAttribute("resolucion");
					argumento = (String) req.getSession().getAttribute("argumento");
					nombrePresidente = (String) req.getSession().getAttribute("nombrePresidente");
					nombreSecretario = (String) req.getSession().getAttribute("nombreSecretario");

					jasperPrint = DelegadoGenerador.getInstance().reporteResolucionDeniega_FT_459(zonaElectoral,
							nombreComision, nombreAsociado, resolucionImpugnada, fecha, resolucion, argumento,
							nombrePresidente, nombreSecretario, rutaImagen, rutaReporte);

					removerAtributos(session, "zonaElectoral", "fecha", "nombreComision", "nombreAsociado",
							"resolucionImpugnada", "resolucion", "argumento", "nombrePresidente", "nombreSecretario");
					break;

				case 460:
					// RESOLUCIÓN QUE RESUELVE UN RECURSO DE REPOSICIÓN EN CONTRA Y REMITE LA APELACIÓN CO-FT-460
					nombreReporte = "RESOLUCIÓN QUE RESUELVE UN RECURSO DE REPOSICIÓN EN CONTRA Y REMITE LA APELACIÓN";
					zonaElectoral = (String) req.getSession().getAttribute("zonaElectoral");
					fecha = (Date) req.getSession().getAttribute("fecha");
					nombreComision = (String) req.getSession().getAttribute("nombreComision");
					nombreAsociado = (String) req.getSession().getAttribute("nombreAsociado");
					resolucionImpugnada = (String) req.getSession().getAttribute("resolucionImpugnada");
					resolucion = (String) req.getSession().getAttribute("resolucion");
					argumento = (String) req.getSession().getAttribute("argumento");
					nombrePresidente = (String) req.getSession().getAttribute("nombrePresidente");
					nombreSecretario = (String) req.getSession().getAttribute("nombreSecretario");

					jasperPrint = DelegadoGenerador.getInstance().reporteResuelveReposicion_FT_460(zonaElectoral,
							nombreComision, nombreAsociado, resolucionImpugnada, fecha, resolucion, argumento,
							nombrePresidente, nombreSecretario, rutaImagen, rutaReporte);

					removerAtributos(session, "zonaElectoral", "fecha", "nombreComision", "nombreAsociado",
							"resolucionImpugnada", "resolucion", "argumento", "nombrePresidente", "nombreSecretario");
					break;

				case 461:
					// RESOLUCIÓN QUE RESUELVE UN RECURSO DE APELACIÓN FAVORABLE CO-FT-461
					nombreReporte = "RESOLUCIÓN QUE RESUELVE UN RECURSO DE APELACIÓN FAVORABLE";
					acta = (String) req.getSession().getAttribute("acta");
					fecha = (Date) req.getSession().getAttribute("fecha");
					nombreAsociado = (String) req.getSession().getAttribute("nombreAsociado");
					String resolucionApelada = (String) req.getSession().getAttribute("resolucionApelada");
					String resolucionComision = (String) req.getSession().getAttribute("resolucionComision");
					String actaTribunal = (String) req.getSession().getAttribute("actaTribunal");
					argumento = (String) req.getSession().getAttribute("argumento");
					decision = (String) req.getSession().getAttribute("decision");
					nombrePresidente = (String) req.getSession().getAttribute("nombrePresidente");
					nombreSecretario = (String) req.getSession().getAttribute("nombreSecretario");

					jasperPrint = DelegadoGenerador.getInstance().reporteResuelveApelacion_FT_461(acta, nombreAsociado,
							resolucionApelada, resolucionComision, fecha, actaTribunal, argumento, decision,
							nombrePresidente, nombreSecretario, rutaImagen, rutaReporte);

					removerAtributos(session, "acta", "fecha", "resolucionApelada", "nombreAsociado",
							"resolucionComision", "actaTribunal", "argumento", "decision", "nombrePresidente",
							"nombreSecretario");
					break;

				case 462:
					// RESOLUCIÓN QUE RESUELVE UN RECURSO DE APELACIÓN EN CONTRA CO-FT-462
					nombreReporte = "RESOLUCIÓN QUE RESUELVE UN RECURSO DE APELACIÓN EN CONTRA";
					acta = (String) req.getSession().getAttribute("acta");
					fecha = (Date) req.getSession().getAttribute("fecha");
					nombreAsociado = (String) req.getSession().getAttribute("nombreAsociado");
					resolucionApelada = (String) req.getSession().getAttribute("resolucionApelada");
					resolucionComision = (String) req.getSession().getAttribute("resolucionComision");
					actaTribunal = (String) req.getSession().getAttribute("actaTribunal");
					argumento = (String) req.getSession().getAttribute("argumento");
					nombrePresidente = (String) req.getSession().getAttribute("nombrePresidente");
					nombreSecretario = (String) req.getSession().getAttribute("nombreSecretario");

					jasperPrint = DelegadoGenerador.getInstance().reporteResuelveApelacionContra_FT_462(acta,
							nombreAsociado, resolucionApelada, resolucionComision, fecha, actaTribunal, argumento,
							nombrePresidente, nombreSecretario, rutaImagen, rutaReporte);

					removerAtributos(session, "acta", "fecha", "resolucionApelada", "nombreAsociado",
							"resolucionComision", "actaTribunal", "argumento", "nombrePresidente", "nombreSecretario");
					break;

				case 753:
					// RESOLUCIÓN QUE RESUELVE RECURSOS INTERPUESTOS EXTEMPORANEAMENTE CO-FT-753
					nombreReporte = "RESOLUCIÓN QUE RESUELVE RECURSOS INTERPUESTOS EXTEMPORANEAMENTE";
					zonaElectoral = (String) req.getSession().getAttribute("zonaElectoral");
					fecha = (Date) req.getSession().getAttribute("fecha");
					nombreAsociado = (String) req.getSession().getAttribute("nombreAsociado");
					nombreComision = (String) req.getSession().getAttribute("nombreComision");
					resolucion = (String) req.getSession().getAttribute("resolucion");
					String diaPresentado = (String) req.getSession().getAttribute("diaPresentado");
					dia = (String) req.getSession().getAttribute("dia");
					mes = (String) req.getSession().getAttribute("mes");
					anio = (String) req.getSession().getAttribute("anio");

					jasperPrint = DelegadoGenerador.getInstance().reporteResolucionExtemporaneamente_FT_753(
							zonaElectoral, fecha, nombreAsociado, nombreComision, resolucion, diaPresentado, dia, mes,
							anio, rutaImagen, rutaReporte);

					removerAtributos(session, "dia", "mes", "anio", "zonaElectoral", "nombreAsociado", "nombreComision",
							"resolucion", "diaPresentado");
					break;

				default:
					throw new Exception("El Tipo de Reporte Selecionado no existe");
				}

				resp.setContentType("application/vnd.ms-pdf");
				resp.setHeader("Content-disposition", "inline; filename=\"" + nombreReporte + ".pdf\"");
				resp.setHeader("Pragma", "no-cache");
				resp.setHeader("Expires", "-1");

				resp.setCharacterEncoding("iso-8859-1");

				JRPdfExporter pdfExporter = new JRPdfExporter();
				pdfExporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
				pdfExporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, resp.getOutputStream());
				pdfExporter.exportReport();
			} else {
				throw new Exception("No hay Tipo de Reporte Selecionado");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Excepcion generando el reporte :" + e.getMessage());
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>Informe de Error</TITLE></HEAD>");
			out.println("  <BODY>");
			out.println("  <TABLE ALIGN='CENTER'>");
			out.println("  <TR>");
			out.println("  <TD>");
			out.print("Se presentó un error generando el reporte.");
			out.println("  </TD>");
			out.println("  </TR>");
			out.println("  </TABLE>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
		}
	}

	public void removerAtributos(HttpSession session, String... atributos) {
		for (String attr : atributos) {
			session.removeAttribute(attr);
		}
	}

}
