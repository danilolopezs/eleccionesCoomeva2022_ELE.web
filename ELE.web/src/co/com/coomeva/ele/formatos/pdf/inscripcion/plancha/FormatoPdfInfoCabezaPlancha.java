package co.com.coomeva.ele.formatos.pdf.inscripcion.plancha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.apache.xerces.impl.dv.util.Base64;

import co.com.coomeva.ele.delegado.DelegadoCabezaPlancha;
import co.com.coomeva.ele.dto.InformacionCabezaPlanchaDTO;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleDetalleFormatoPlancha;
import co.com.coomeva.ele.formatos.pdf.AbstractFormatoPdf;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.util.FacesUtils;
import co.com.coomeva.util.acceso.UtilAcceso;
import co.com.cooomeva.uti.util.pdf.UtilPDF;

import com.icesoft.faces.context.effects.JavascriptContext;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

/**
 * Formato informacion cabeza de plancha
 * 
 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> -
 *         Pragma S.A. <br>
 * @project ELE.web
 * @class FormatoPdfInfoCabezaPlancha
 * @date 4/12/2012
 * 
 */
public class FormatoPdfInfoCabezaPlancha extends AbstractFormatoPdf {

	private String nombrePlantillaFormatoInformacionCabezaPlancha;
	private String dirDestino;
	private String tipoEleccionesSession;
	private String tipoEleccionesRepresentantes;

	public FormatoPdfInfoCabezaPlancha() {
		super();
		try {
			this.tipoEleccionesSession = (String) FacesUtils
					.getSessionParameter("tipoElecciones");
			this.tipoEleccionesRepresentantes = UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"param.tipo.elecciones.representantes");
			nombrePlantillaFormatoInformacionCabezaPlancha = UtilAcceso
					.getParametroFuenteS(
							ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
							"param.nombre.plantilla.info.cabeza.plancha."
									+ this.tipoEleccionesSession);
			dirDestino = UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_PARAMETROS_PRINCIPAL,
					"param.url.ubicacion.plantillas");
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	@Override
	public void setearValorMapaReporte(Object... params) throws Exception {
		Long numeroDocumentoAsociadoCabezaPlancha = (Long) params[0];
		Long numintAsociadoCabezaPlancha = (Long) params[1];

		InformacionCabezaPlanchaDTO dto = DelegadoCabezaPlancha.getInstance()
				.consultarInformacionCabezaPlancha(
						numeroDocumentoAsociadoCabezaPlancha);
		EleDetalleFormatoPlancha entity = DelegadoCabezaPlancha.getInstance()
				.consultarDetalleFormatoPlanchaPorId(
						numintAsociadoCabezaPlancha);

		this.getValores().put("pdfParamZona", dto.getZonaElectoral());
		this.getValores()
				.put("pdfParamNombreCompleto", dto.getNombreCompleto());
		this.getValores().put("pdfParamCedula", dto.getNumeroDocumento());
		this.getValores().put("pdfParamAntiguedadCoomeva",
				dto.getAntiguedadMeses() + " (meses)");
		this.getValores().put("pdfParamProfesion", dto.getProfesion());
		if (entity.getFechaGradoMod() != null) {
			SimpleDateFormat formatterAnno = new SimpleDateFormat("yyyy");
			String annoGrado = formatterAnno.format(entity.getFechaGradoMod());

			this.getValores().put("pdfParamAnioExpTitulo", annoGrado);
		}
		this.getValores().put("pdfParamOtrosEstudiosUno",
				entity.getOtrosEstudios());
		this.getValores().put("pdfParamOtrosEstudiosDos",
				entity.getOtrosEstudiosDos());
		this.getValores().put("pdfParamEmpresaActual",
				entity.getEmpresaActual());
		this.getValores().put("pdfParamCargoActual", entity.getCargoActual());
		this.getValores().put("pdfParamAntiguedadCargo",
				entity.getAntigLaboral().toString() + " (meses)");
		this.getValores().put("pdfParamUltimoCargoCoomeva",
				entity.getUltimoCargoCoomeva());
		if (tipoEleccionesRepresentantes.equals(tipoEleccionesSession)) {
			this.getValores().put("pdfParamPerfilCandidatoUno",
					entity.getPerfilCandidatoUno());
			// this.getValores().put("pdfParamPerfilCandidatoDos",
			// entity.getPerfilCandidatoDos());
			String mail = (entity.getCuentaPersonalMail() != null && !entity
					.getCuentaPersonalMail().equals("")) ? "Correo electronico: "
					+ entity.getCuentaPersonalMail()
					: "";
			String twitter = (entity.getCuentaPersonalTwitter() != null && !entity
					.getCuentaPersonalTwitter().equals("")) ? "Twitter: "
					+ entity.getCuentaPersonalTwitter() : "";
			String facebook = (entity.getCuentaPersonalFacebook() != null && !entity
					.getCuentaPersonalFacebook().equals("")) ? "Facebook: "
					+ entity.getCuentaPersonalFacebook() : "";

			this.getValores().put("pdfParamCuentaPersonal",
					mail + " " + twitter + " " + facebook);
		}
	}

	@Override
	public void generarReporte(Object... params) throws Exception {
		this.setearValorMapaReporte(params);
		Long idCabezaPlancha = (Long) params[0];
		String imageBase64 = params[2] == null ? null : params[2].toString();
		generarReportePDF(this.nombrePlantillaFormatoInformacionCabezaPlancha,
				"InformacionCabezaPlancha" + idCabezaPlancha, imageBase64,
				idCabezaPlancha);
	}

	public void generarReportePDF(String nombrePlantilla, String nombreArchivo,
			String imageBase64, Long idCabezaPlancha) {

		try {
			String realpath = FacesUtils.getServletContext().getRealPath("gui");
			new UtilPDF().generarPDF(realpath
					+ this.getUrlUbicacionPlantillas(), nombrePlantilla,
					nombreArchivo + ".pdf", this.getValores());

			String url = FacesUtils.getUrlAplicacion() + this.getUrlDestino()
					+ "_" + nombreArchivo + ".pdf";
			String pathPDF = realpath + this.dirDestino + nombreArchivo
					+ ".pdf";
			String pathSalidaPDF = realpath + this.dirDestino + "_"
					+ nombreArchivo + ".pdf";
			File imageDecoding = null;
			if (imageBase64 != null) {
				byte[] bytearray = Base64.decode(imageBase64);
				BufferedImage imag = ImageIO.read(new ByteArrayInputStream(
						bytearray));
				imageDecoding = new File(realpath + this.dirDestino, "foto"
						+ idCabezaPlancha + ".jpg");
				int index = imageDecoding.getName().lastIndexOf('.');
				String extensionFoto = imageDecoding.getName().substring(
						index + 1);
				ImageIO.write(imag, extensionFoto, imageDecoding);
			}
			PdfReader reader = new PdfReader(pathPDF);
			PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(
					pathSalidaPDF));
			PdfContentByte content = stamp.getOverContent(1);

			if (imageBase64 != null) {
				Image logo = null;

				InputStream streamImagen = new FileInputStream(imageDecoding);
				byte[] imagen = new byte[streamImagen.available()];
				streamImagen.read(imagen);
				logo = Image.getInstance(imagen);
				logo.setAlignment(Image.TOP);
				logo.scaleAbsolute(93, 96);
				if (tipoEleccionesRepresentantes.equals(tipoEleccionesSession)) {
					logo.scaleAbsolute(93, 96);
					logo.setAbsolutePosition(460, 536);
				} else {
					logo.scaleAbsolute(80, 104);
					logo.setAbsolutePosition(501, 521);
				}
				content.addImage(logo);
				streamImagen.close();
			}
			reader.close();
			stamp.close();
			if (imageBase64 != null) {
				imageDecoding.delete();
			}
			new File(pathPDF).delete();

			JavascriptContext.addJavascriptCall(FacesContext
					.getCurrentInstance(), "window.open('" + url + "');");
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public String generarReport() {
		try {
			generarReporte();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}
}
