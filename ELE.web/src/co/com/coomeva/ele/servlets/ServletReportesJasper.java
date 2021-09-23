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
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
	 * @author Christian Mauricio Tangarife Colorado cmtc4227
	 * 
	 * metodo para ejecutar todos los reportes jaspers
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	public void executeReport(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		
		JasperPrint jasperPrint = null;
		HttpSession session = req.getSession();
		boolean flag = false;
		
		String codigoReporte = (String)req.getSession().getAttribute("codigoReporte");
		String rutaImagen = PathRequest.getInstance().getPathServerContextPath(getServletContext(), "imagenes/reportes") ;
		String rutaReporte = getServletContext().getRealPath("WEB-INF/reports/");
		rutaReporte = rutaReporte+"/";
		
		try {
			
			if(codigoReporte != null && !codigoReporte.isEmpty())
			{
				String nombreReporte = "";
				
				//Resolución de Admisión CO-FT-172
				if(codigoReporte.equalsIgnoreCase("172"))
				{
					nombreReporte = "RESOLUCION ADMISION DE PLANCHA";
					String zonaElectoral = (String)req.getSession().getAttribute("zonaElectoral");
					String nombreAsociado = (String)req.getSession().getAttribute("nombreAsociado");
					String numResolucion = (String)req.getSession().getAttribute("numResolucion");
					String numActa = (String)req.getSession().getAttribute("numActa");
					Date fecha = (Date)req.getSession().getAttribute("fecha");
					String ciudadZona = (String)req.getSession().getAttribute("ciudadZona");
					String dia = (String)req.getSession().getAttribute("dia"); 
					String mes = (String)req.getSession().getAttribute("mes"); 
					String anio = (String)req.getSession().getAttribute("anio"); 
					
					jasperPrint =  DelegadoGenerador.getInstance().
						reporteResolucionAdmisionPlanchas_FT_172 (zonaElectoral, nombreAsociado, numResolucion, numActa, fecha,
							ciudadZona, dia, mes, anio, rutaImagen, rutaReporte);
								
					
					session.removeAttribute("zonaElectoral");
					session.removeAttribute("nombreAsociado");
					session.removeAttribute("numResolucion");
					session.removeAttribute("numActa");
					session.removeAttribute("fecha");
					session.removeAttribute("ciudadZona");
					session.removeAttribute("dia");
					session.removeAttribute("mes");
					session.removeAttribute("anio");					
				}		
				
				//resolución de Rechazo de Planchas CO-FT-173
				else if(codigoReporte.equalsIgnoreCase("173"))
				{
					nombreReporte = "RESOLUCIÓN DE RECHAZO DE PLANCHAS";
					String zonaElectoral = (String)req.getSession().getAttribute("zonaElectoral");
					String nombreAsociado = (String)req.getSession().getAttribute("nombreAsociado");
					String cedulaAsociado = (String)req.getSession().getAttribute("cedulaAsociado");
					String numResolucion = (String)req.getSession().getAttribute("numResolucion");
					String numActa = (String)req.getSession().getAttribute("numActa");
					Date fecha = (Date)req.getSession().getAttribute("fecha");
					String dia = (String)req.getSession().getAttribute("dia"); 
					String mes = (String)req.getSession().getAttribute("mes"); 
					String anio = (String)req.getSession().getAttribute("anio");  
					String razones = (String)req.getSession().getAttribute("razones");
					String mesActa = (String)req.getSession().getAttribute("mesActa");
					
					jasperPrint =  DelegadoGenerador.getInstance().
						reporteResolucionRechazoPlanchas_FT_173(zonaElectoral, nombreAsociado, numResolucion,cedulaAsociado, numActa, fecha, dia, 
							mes, anio, razones, mesActa, rutaImagen, rutaReporte);							
					
					session.removeAttribute("zonaElectoral");
					session.removeAttribute("nombreAsociado");
					session.removeAttribute("cedulaAsociado");
					session.removeAttribute("numResolucion");
					session.removeAttribute("numActa");
					session.removeAttribute("fecha");
					session.removeAttribute("dia");
					session.removeAttribute("mes");
					session.removeAttribute("anio");
					session.removeAttribute("razones");
					session.removeAttribute("mesActa");
				}
				
				//Informacion Personal del caneza de Plancha CO-FT-174
				else if(codigoReporte.equalsIgnoreCase("174"))
				{
					nombreReporte = "INFORMACION PERSONASL DEL CABEZA DE PLANCHA";
					String plancha = (String)req.getSession().getAttribute("plancha");
					String zonaElectoral = (String)req.getSession().getAttribute("zonaElectoral");
					String nombreAsociado = (String)req.getSession().getAttribute("nombreAsociado");
					String cedulaAsociado = (String)req.getSession().getAttribute("cedulaAsociado");
					String fechaAntiguedad = (String)req.getSession().getAttribute("fechaAntiguedad");
					String profesion = (String)req.getSession().getAttribute("profesion");
					Date fechaTitulo = (Date)req.getSession().getAttribute("fechaTitulo");
					String estudios = (String)req.getSession().getAttribute("estudios"); 
					String empresa = (String)req.getSession().getAttribute("empresa"); 
					String cargo = (String)req.getSession().getAttribute("cargo");  
					String antiguedad = (String)req.getSession().getAttribute("antiguedad");					
					String ultimoCargo= (String)req.getSession().getAttribute("ultimoCargo");
					String imagen =(String)req.getSession().getAttribute("foto");
					Boolean esSuplene = (Boolean)req.getSession().getAttribute("esSuplente");
					imagen = PathRequest.getInstance().getPathServerContextPath(getServletContext(), "plantilla")+"/foto"+ imagen + ".jpg";
					String formulario =  rutaImagen + "/CO-FT-174.png";
					if(esSuplene)
					{
						formulario =  rutaImagen + "/CO-FT-766.png";
					}				
					
					jasperPrint =  DelegadoGenerador.getInstance().
						reporteInformacionPersonal_FT_174(plancha,  zonaElectoral,  nombreAsociado,
							 cedulaAsociado,  fechaAntiguedad,  profesion,  fechaTitulo,  estudios,
							 empresa,  cargo,  antiguedad,  ultimoCargo,  imagen,  formulario,
							 rutaReporte);							
					
					session.removeAttribute("plancha");
					session.removeAttribute("zonaElectoral");
					session.removeAttribute("nombreAsociado");
					session.removeAttribute("cedulaAsociado");
					session.removeAttribute("fechaAntiguedad");
					session.removeAttribute("profesion");
					session.removeAttribute("fechaTitulo");
					session.removeAttribute("estudios"); 
					session.removeAttribute("empresa"); 
					session.removeAttribute("cargo");  
					session.removeAttribute("antiguedad");					
					session.removeAttribute("ultimoCargo");	
					session.removeAttribute("foto");
				}
				
				//CERTIFICACIÓN DE CUMPLIMIENTO PARA SER ELEGIDO DELEGADO CO-FT-176
				else if(codigoReporte.equalsIgnoreCase("176"))
				{
					nombreReporte = "CERTIFICACIÓN DE CUMPLIMIENTO PARA SER ELEGIDO DELEGADO";
					String nombreAsociado = (String)req.getSession().getAttribute("nombreAsociado"); 
					String numPlancha = (String)req.getSession().getAttribute("numPlancha");
					String nombreRepresen = (String)req.getSession().getAttribute("nombreRepresen"); 
					List<PreguntasFormulario176DTO> respustasDTO = (List<PreguntasFormulario176DTO>)req.getSession().getAttribute("preguntas");
					
					List<String> respustas = new ArrayList<String>();
					if(respustasDTO != null)
					{
						for (PreguntasFormulario176DTO respuesta : respustasDTO) {
							if(respuesta.getRespuesta() == 0)
							{
								respustas.add("N");
							}
							else
							{
								respustas.add("S");
							}
						}
					}
					
					jasperPrint =  DelegadoGenerador.getInstance().
						reporteCumplimientoDelegado_FT_176(nombreAsociado,  numPlancha, nombreRepresen, respustas, rutaImagen, rutaReporte);							
					
					session.removeAttribute("nombreAsociado");
					session.removeAttribute("numPlancha");
					session.removeAttribute("nombreRepresen");
					session.removeAttribute("preguntas");
				}
				
				//CONSTANCIA DE RADICACIÓN Y RECIBO DE PLANCHAS CO-FT-208
				else if(codigoReporte.equalsIgnoreCase("208"))
				{
					nombreReporte = "CERTIFICACIÓN DE CUMPLIMIENTO PARA SER ELEGIDO DELEGADO";
					String zonaElectoral = (String)req.getSession().getAttribute("zonaElectoral");
					Date fecha = (Date)req.getSession().getAttribute("fecha");
					String nombreAsociado = (String)req.getSession().getAttribute("nombreAsociado");
					String numComision = (String)req.getSession().getAttribute("numComision");	
					String ciudad = (String)req.getSession().getAttribute("ciudad");					
					String cedulaAsociado = (String)req.getSession().getAttribute("cedulaAsociado");
					String ciudadCedula = (String)req.getSession().getAttribute("ciudadCedula");
					String dia = (String)req.getSession().getAttribute("dia"); 
					String mes = (String)req.getSession().getAttribute("mes"); 
					String anio = (String)req.getSession().getAttribute("anio");
					String nombreEntrega = (String)req.getSession().getAttribute("nombreEntrega");
					String nombreRecibe = (String)req.getSession().getAttribute("nombreRecibe");
					
					jasperPrint =  DelegadoGenerador.getInstance().
						reporteConstanciaRadicacionPlanchas_FT_208(zonaElectoral, fecha,numComision, ciudad, nombreAsociado, cedulaAsociado, 
							ciudadCedula, dia, mes,  anio, nombreEntrega,nombreRecibe, rutaImagen, rutaReporte);							
					
					session.removeAttribute("zonaElectoral");
					session.removeAttribute("nombreAsociado");
					session.removeAttribute("cedulaAsociado");
					session.removeAttribute("ciudad");
					session.removeAttribute("ciudadCedula");
					session.removeAttribute("fecha");
					session.removeAttribute("dia");
					session.removeAttribute("mes");
					session.removeAttribute("anio");	
					session.removeAttribute("mes");
					session.removeAttribute("nombreEntrega");
					session.removeAttribute("nombreRecibe");
				}
				
				//RESOLUCION INADMISION PLANCHA CO-FT-209
				else if(codigoReporte.equalsIgnoreCase("209"))
				{
					nombreReporte = "RESOLUCION INADMISION PLANCHA";
					String zonaElectoral = (String)req.getSession().getAttribute("zonaElectoral");
					String dia = (String)req.getSession().getAttribute("dia"); 
					String mes = (String)req.getSession().getAttribute("mes"); 
					String anio = (String)req.getSession().getAttribute("anio");
					Date hora = (Date)req.getSession().getAttribute("hora");
					String nombreAsociado = (String)req.getSession().getAttribute("nombreAsociado");
					String cedulaAsociado = (String)req.getSession().getAttribute("cedulaAsociado");
					String resolucion = (String)req.getSession().getAttribute("resolucion");
					String acta = (String)req.getSession().getAttribute("acta");
					Date fecha = (Date)req.getSession().getAttribute("fecha");
					String ciudad = (String)req.getSession().getAttribute("ciudad");
					String razon1 = (String)req.getSession().getAttribute("razon1");
					String razon2 = (String)req.getSession().getAttribute("razon2");
					String razon3 = (String)req.getSession().getAttribute("razon3");
					String razon4 = (String)req.getSession().getAttribute("razon4");
					String formulario =  rutaImagen + "/CO-FT-209.png";
					
					jasperPrint =  DelegadoGenerador.getInstance().
						reporteResolucionInadmisionPlancha_FT_209(zonaElectoral, anio, mes, dia,hora,
							nombreAsociado,cedulaAsociado, resolucion, acta,  fecha, ciudad,
							razon1, razon2, razon3, razon4,formulario, rutaReporte);							
					
					session.removeAttribute("zonaElectoral");
					session.removeAttribute("dia"); 
					session.removeAttribute("mes"); 
					session.removeAttribute("anio");
					session.removeAttribute("hora");
					session.removeAttribute("nombreAsociado");
					session.removeAttribute("cedulaAsociado");
					session.removeAttribute("resolucion");
					session.removeAttribute("acta");
					session.removeAttribute("fecha");
					session.removeAttribute("ciudad");
					session.removeAttribute("razon1");
					session.removeAttribute("razon2");
					session.removeAttribute("razon3");
					session.removeAttribute("razon4");													
				}
				
				//INSCRIPCIÓN DE PLANCHAS CO-FT-210
				else if(codigoReporte.equalsIgnoreCase("210"))
				{
					nombreReporte = "INSCRIPCIÓN DE PLANCHAS";
					String zonaElectoral = (String)req.getSession().getAttribute("zonaElectoral");					
					Date fecha = (Date)req.getSession().getAttribute("fecha");
					String ciudad = (String)req.getSession().getAttribute("ciudad");
					
					
					jasperPrint =  DelegadoGenerador.getInstance().
						reporteInscripcionPlanchas_FT_210( zonaElectoral, ciudad, fecha, rutaImagen, rutaReporte);							
					
					session.removeAttribute("zonaElectoral");					
					session.removeAttribute("fecha");
					session.removeAttribute("ciudad");																	
				}
				
				//Declaración para acreditar ocupación y cumplimiento de requisitos CO-FT-211
				else if(codigoReporte.equalsIgnoreCase("211"))
				{
					nombreReporte = "CETIFICADO PARA ACREDITAR OCUPACIÓN Y CUMPLIMIENTO DE REQUISITOS";
					String ciudad = (String)req.getSession().getAttribute("ciudad");					
					String dia = (String)req.getSession().getAttribute("dia"); 
					String mes = (String)req.getSession().getAttribute("mes"); 
					String anio = (String)req.getSession().getAttribute("anio");					
					String nombreAsociado = (String)req.getSession().getAttribute("nombreAsociado");
					String cedulaAsociado = (String)req.getSession().getAttribute("cedulaAsociado");
					String ciudadCedula = (String)req.getSession().getAttribute("ciudadCedula");
					String ciudadFirma = (String)req.getSession().getAttribute("ciudadFirma");
					String diaFirma = (String)req.getSession().getAttribute("diaFirma"); 
					String mesFirma = (String)req.getSession().getAttribute("mesFirma"); 
					String anioFirma = (String)req.getSession().getAttribute("anioFirma");
					String formulario =  rutaImagen + "/CO-FT-211.png";
					
					jasperPrint =  DelegadoGenerador.getInstance().
						reporteCertificadoAcreditaOcupacion_FT_211(ciudad, anio, mes, dia,
							nombreAsociado,cedulaAsociado, ciudadCedula, ciudadFirma, diaFirma,
							mesFirma, anioFirma,formulario, rutaReporte);							
					
					session.removeAttribute("ciudad");
					session.removeAttribute("dia"); 
					session.removeAttribute("mes"); 
					session.removeAttribute("anio");
					session.removeAttribute("hora");
					session.removeAttribute("nombreAsociado");
					session.removeAttribute("cedulaAsociado");
					session.removeAttribute("ciudadCedula");
					session.removeAttribute("ciudadFirma");
					session.removeAttribute("diaFirma");
					session.removeAttribute("mesFirma");
					session.removeAttribute("anioFirma");																					
				}
				
				//RESOLUCION QUE RESULELVE RECURSOSO DE REPOSICIÓN FAVORABLEMENTE CO-FT-458
				else if(codigoReporte.equalsIgnoreCase("458"))
				{
					nombreReporte = "RESOLUCION QUE RESULELVE RECURSOSO DE REPOSICIÓN FAVORABLEMENTE";
					String zonaElectoral = (String)req.getSession().getAttribute("zonaElectoral");
					Date fecha = (Date)req.getSession().getAttribute("fecha");
					String nombreComision = (String)req.getSession().getAttribute("nombreComision");
					String nombreAsociado = (String)req.getSession().getAttribute("nombreAsociado");
					String resolucionImpugnada = (String)req.getSession().getAttribute("resolucionImpugnada");			
					String resolucionMod = (String)req.getSession().getAttribute("resolucion");
					String argumento = (String)req.getSession().getAttribute("argumento");
					String decision = (String)req.getSession().getAttribute("decision");
					String nombrePresidente = (String)req.getSession().getAttribute("nombrePresidente");
					String nombreSecretario = (String)req.getSession().getAttribute("nombreSecretario");
					
					jasperPrint =  DelegadoGenerador.getInstance().
						reporteResolucionFavorable_FT_458(zonaElectoral, nombreComision, nombreAsociado, resolucionImpugnada, fecha, resolucionMod, argumento, nombrePresidente, nombreSecretario, decision, rutaImagen, rutaReporte);						
					
					session.removeAttribute("zonaElectoral");
					session.removeAttribute("fecha"); 
					session.removeAttribute("nombreComision"); 
					session.removeAttribute("nombreAsociado");
					session.removeAttribute("resolucionImpugnada");
					session.removeAttribute("resolucion");
					session.removeAttribute("argumento");
					session.removeAttribute("decision");
					session.removeAttribute("nombrePresidente");
					session.removeAttribute("nombreSecretario");
				}
				
				//RESOLUCIÓN QUE DENIEGA UN RECURSO DE REPOSICIÓN
				//Y NO CONCEDE APELACIÓN POR NO SER SOLICITADO CO-FT-459
				else if(codigoReporte.equalsIgnoreCase("459"))
				{
					nombreReporte = "RESOLUCIÓN QUE DENIEGA UN RECURSO DE REPOSICIÓNY  NO CONCEDE APELACIÓN POR NO SER SOLICITADO";
					String zonaElectoral = (String)req.getSession().getAttribute("zonaElectoral");
					Date fecha = (Date)req.getSession().getAttribute("fecha");
					String nombreComision = (String)req.getSession().getAttribute("nombreComision");
					String nombreAsociado = (String)req.getSession().getAttribute("nombreAsociado");
					String resolucionImpugnada = (String)req.getSession().getAttribute("resolucionImpugnada");			
					String resolucion = (String)req.getSession().getAttribute("resolucion");
					String argumento = (String)req.getSession().getAttribute("argumento");	
					String nombrePresidente = (String)req.getSession().getAttribute("nombrePresidente");
					String nombreSecretario = (String)req.getSession().getAttribute("nombreSecretario");
					
					jasperPrint =  DelegadoGenerador.getInstance().
						reporteResolucionDeniega_FT_459( zonaElectoral, nombreComision, nombreAsociado,
							 resolucionImpugnada, fecha,  resolucion,  argumento, nombrePresidente, nombreSecretario,
							 rutaImagen, rutaReporte);							
					
					session.removeAttribute("zonaElectoral");
					session.removeAttribute("fecha"); 
					session.removeAttribute("nombreComision"); 
					session.removeAttribute("nombreAsociado");
					session.removeAttribute("resolucionImpugnada");
					session.removeAttribute("resolucion");
					session.removeAttribute("argumento");
					session.removeAttribute("nombrePresidente");
					session.removeAttribute("nombreSecretario");
				}
				
				//RESOLUCIÓN QUE RESUELVE UN RECURSO DE REPOSICIÓN
				//EN CONTRA Y REMITE LA APELACIÓN CO-FT-460
				else if(codigoReporte.equalsIgnoreCase("460"))
				{
					nombreReporte = "RESOLUCIÓN QUE RESUELVE UN RECURSO DE REPOSICIÓN EN CONTRA Y REMITE LA APELACIÓN";
					String zonaElectoral = (String)req.getSession().getAttribute("zonaElectoral");
					Date fecha = (Date)req.getSession().getAttribute("fecha");
					String nombreComision = (String)req.getSession().getAttribute("nombreComision");
					String nombreAsociado = (String)req.getSession().getAttribute("nombreAsociado");
					String resolucionImpugnada = (String)req.getSession().getAttribute("resolucionImpugnada");			
					String resolucion = (String)req.getSession().getAttribute("resolucion");
					String argumento = (String)req.getSession().getAttribute("argumento");
					String nombrePresidente = (String)req.getSession().getAttribute("nombrePresidente");
					String nombreSecretario = (String)req.getSession().getAttribute("nombreSecretario");
					
					jasperPrint =  DelegadoGenerador.getInstance().
						reporteResuelveReposicion_FT_460(zonaElectoral, nombreComision,nombreAsociado,
							resolucionImpugnada, fecha, resolucion, argumento,nombrePresidente, nombreSecretario,
							rutaImagen, rutaReporte);							
					
					session.removeAttribute("zonaElectoral");
					session.removeAttribute("fecha"); 
					session.removeAttribute("nombreComision"); 
					session.removeAttribute("nombreAsociado");
					session.removeAttribute("resolucionImpugnada");
					session.removeAttribute("resolucion");
					session.removeAttribute("argumento");
					session.removeAttribute("nombrePresidente");
					session.removeAttribute("nombreSecretario");
				}
				
				//RESOLUCIÓN QUE RESUELVE UN RECURSO DE
				//APELACIÓN FAVORABLE CO-FT-461
				else if(codigoReporte.equalsIgnoreCase("461"))
				{
					nombreReporte = "RESOLUCIÓN QUE RESUELVE UN RECURSO DE APELACIÓN FAVORABLE";
					String acta = (String)req.getSession().getAttribute("acta");
					Date fecha = (Date)req.getSession().getAttribute("fecha");
					String nombreAsociado = (String)req.getSession().getAttribute("nombreAsociado");
					String resolucionApelada = (String)req.getSession().getAttribute("resolucionApelada");
					String resolucionComision = (String)req.getSession().getAttribute("resolucionComision");			
					String actaTribunal = (String)req.getSession().getAttribute("actaTribunal");
					String argumento = (String)req.getSession().getAttribute("argumento");
					String decision = (String)req.getSession().getAttribute("decision");
					String nombrePresidente = (String)req.getSession().getAttribute("nombrePresidente");
					String nombreSecretario = (String)req.getSession().getAttribute("nombreSecretario");
					
					jasperPrint =  DelegadoGenerador.getInstance().
						reporteResuelveApelacion_FT_461( acta, nombreAsociado,
							resolucionApelada, resolucionComision, fecha, actaTribunal, 
							argumento, decision, nombrePresidente, nombreSecretario, rutaImagen,  rutaReporte);							
					
					session.removeAttribute("acta");
					session.removeAttribute("fecha"); 
					session.removeAttribute("resolucionApelada"); 
					session.removeAttribute("nombreAsociado");
					session.removeAttribute("resolucionComision");
					session.removeAttribute("actaTribunal");
					session.removeAttribute("argumento");
					session.removeAttribute("decision");
					session.removeAttribute("nombrePresidente");
					session.removeAttribute("nombreSecretario");
				}
				
				//RESOLUCIÓN QUE RESUELVE UN RECURSO DE
				//APELACIÓN EN CONTRA CO-FT-462
				else if(codigoReporte.equalsIgnoreCase("462"))
				{
					nombreReporte = "RESOLUCIÓN QUE RESUELVE UN RECURSO DE APELACIÓN EN CONTRA";
					String acta = (String)req.getSession().getAttribute("acta");
					Date fecha = (Date)req.getSession().getAttribute("fecha");
					String nombreAsociado = (String)req.getSession().getAttribute("nombreAsociado");
					String resolucionApelada = (String)req.getSession().getAttribute("resolucionApelada");
					String resolucionComision = (String)req.getSession().getAttribute("resolucionComision");			
					String actaTribunal = (String)req.getSession().getAttribute("actaTribunal");
					String argumento = (String)req.getSession().getAttribute("argumento");
					String nombrePresidente = (String)req.getSession().getAttribute("nombrePresidente");
					String nombreSecretario = (String)req.getSession().getAttribute("nombreSecretario");
					
					jasperPrint =  DelegadoGenerador.getInstance().
						reporteResuelveApelacionContra_FT_462( acta, nombreAsociado,
							resolucionApelada, resolucionComision, fecha, actaTribunal, 
							argumento, nombrePresidente, nombreSecretario, 
							rutaImagen, rutaReporte);							
					
					session.removeAttribute("acta");
					session.removeAttribute("fecha"); 
					session.removeAttribute("resolucionApelada"); 
					session.removeAttribute("nombreAsociado");
					session.removeAttribute("resolucionComision");
					session.removeAttribute("actaTribunal");
					session.removeAttribute("argumento");
					session.removeAttribute("nombrePresidente");
					session.removeAttribute("nombreSecretario");
				}
				
				//RESOLUCIÓN QUE RESUELVE RECURSOS INTERPUESTOS EXTEMPORANEAMENTE CO-FT-753
				else if(codigoReporte.equalsIgnoreCase("753"))
				{
					nombreReporte = "RESOLUCIÓN QUE RESUELVE RECURSOS INTERPUESTOS EXTEMPORANEAMENTE";
					String zonaElectoral = (String)req.getSession().getAttribute("zonaElectoral");
					Date fecha = (Date)req.getSession().getAttribute("fecha");
					String nombreAsociado = (String)req.getSession().getAttribute("nombreAsociado");
					String nombreComision = (String)req.getSession().getAttribute("nombreComision");
					String resolucion = (String)req.getSession().getAttribute("resolucion");
					String diaPresentado = (String)req.getSession().getAttribute("diaPresentado");
					String dia = (String)req.getSession().getAttribute("dia"); 
					String mes = (String)req.getSession().getAttribute("mes"); 
					String anio = (String)req.getSession().getAttribute("anio");
					
					
					jasperPrint =  DelegadoGenerador.getInstance().
						reporteResolucionExtemporaneamente_FT_753(zonaElectoral, fecha, nombreAsociado, nombreComision,
							resolucion,  diaPresentado, dia, mes, anio, rutaImagen, rutaReporte);							
					
					session.removeAttribute("dia"); 
					session.removeAttribute("mes"); 
					session.removeAttribute("anio");
					session.removeAttribute("zonaElectoral");
					session.removeAttribute("nombreAsociado");
					session.removeAttribute("nombreComision");
					session.removeAttribute("resolucion");
					session.removeAttribute("diaPresentado");
				}
				
				resp.setContentType("application/vnd.ms-pdf");
				resp.setHeader("Content-disposition", "inline; filename=\""+nombreReporte+".pdf\"");
				resp.setHeader("Pragma", "no-cache");
				resp.setHeader("Expires", "-1");
				
				resp.setCharacterEncoding("iso-8859-1");
				
				JRPdfExporter pdfExporter = new JRPdfExporter();
				pdfExporter.setParameter(JRPdfExporterParameter.JASPER_PRINT,jasperPrint);
				pdfExporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM,resp.getOutputStream());
				pdfExporter.exportReport();
			} 
			else
			{
				throw new Exception("No hay Tipo de Reporte Selecionado");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Excepcion generando el reporte :"+e.getMessage());
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			if (flag) {
				out.println("  <HEAD><TITLE>"+e.getMessage()+"</TITLE></HEAD>");
			}else
				out.println("  <HEAD><TITLE>Se generó un error generando el reporte</TITLE></HEAD>");
			out.println("  <BODY>");
			out.println("  <TABLE ALIGN='CENTER'>");
			out.println("  <TR>");
			out.println("  <TD>");
			if (!flag) {
				out.print("    Error -  ");
			}
			out.print(e.getMessage());
			out.println("  </TR>");
			out.println("  </TD>");			
			out.println("  </TABLE>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
		}finally{
			
		}
	}
		
	

}
