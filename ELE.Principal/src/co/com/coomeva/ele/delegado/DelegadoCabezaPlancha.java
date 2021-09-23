package co.com.coomeva.ele.delegado;

import java.util.List;

import co.com.coomeva.ele.dto.InfoDetalleFormatoPlanchaDTO;
import co.com.coomeva.ele.dto.InformacionCabezaPlanchaDTO;
import co.com.coomeva.ele.entidades.planchas.EleCabPlancha;
import co.com.coomeva.ele.entidades.planchas.EleExperienciaLaboral;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleDetalleFormatoPlancha;
import co.com.coomeva.ele.entidades.planchas.dosmildoce.EleFotoFormatoPlancha;
import co.com.coomeva.ele.logica.LogicaCabezaPlancha;
import co.com.coomeva.ele.modelo.EleCabPlanchaDTO;

public class DelegadoCabezaPlancha {
	private static DelegadoCabezaPlancha instance;
	private static LogicaCabezaPlancha logicaCabezaPlancha;

	// Constructor de la clase
	private DelegadoCabezaPlancha() {
	}

	// Patròn Singular
	public static DelegadoCabezaPlancha getInstance() {
		if (instance == null) {
			instance = new DelegadoCabezaPlancha();
			logicaCabezaPlancha = LogicaCabezaPlancha.getInstance();
		}
		return instance;
	}

	public void crearCabezaPlancha(String nroIdentificacion,
			String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, Long edad, String profesion, String email,
			Long antiguedad, String rutaImagen, String otrosEstudios,
			String cargosDirectivos) throws Exception {
		logicaCabezaPlancha.crearCabezaPlancha(nroIdentificacion, primerNombre,
				segundoNombre, primerApellido, segundoApellido, edad,
				profesion, email, antiguedad, rutaImagen, otrosEstudios,
				cargosDirectivos);
	}

	public EleCabPlanchaDTO consultarCabezaPlanchaDTO(String nroCabezaPlancha)
			throws Exception {
		return logicaCabezaPlancha.consultarCabezaPlanchaDTO(nroCabezaPlancha);
	}

	public EleCabPlancha consultarCabezaPlancha(String nroCabezaPlancha)
			throws Exception {
		return logicaCabezaPlancha.consultarCabezaPlancha(nroCabezaPlancha);
	}

	public void guardarRutaImagen(String nroCabezaPlancha, String ruta)
			throws Exception {
		logicaCabezaPlancha.guardarRutaImagen(nroCabezaPlancha, ruta);
	}

	public void modificarCabezaPlancha(String nroIdentificacion,
			String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, Long edad, String profesion, String email,
			Long antiguedad, String rutaImagen, String otrosEstudios,
			String cargosDirectivos) throws Exception {
		logicaCabezaPlancha.modificarCabezaPlancha(nroIdentificacion,
				primerNombre, segundoNombre, primerApellido, segundoApellido,
				edad, profesion, email, antiguedad, rutaImagen, otrosEstudios,
				cargosDirectivos);
	}

	public void modificarOrtografiaUGA(String nroCabezaPlancha,
			List<EleExperienciaLaboral> listExperienciaLaboral,
			String otrosEstudios, String cargosDirectivos,
			String tipoTransaccion, String conceptoCambio, String user,
			String profesion, String imagenCabezaPlancha) throws Exception {
		logicaCabezaPlancha.modificarOrtografiaUGA(nroCabezaPlancha,
				listExperienciaLaboral, otrosEstudios, cargosDirectivos,
				tipoTransaccion, conceptoCambio, user, profesion,
				imagenCabezaPlancha);
	}

	public void modificarRutaImagen(String nroCabezaPlancha, String rutaImagen)
			throws Exception {
		logicaCabezaPlancha.modificarRutaImagen(nroCabezaPlancha, rutaImagen);
	}

	/**************************************************************** Elecciones 2012 *************************************************************/
	/**
	 * Metodo que guarda la informacion de la cabeza de plancha en detalle
	 * formato plancha.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a>
	 *         - Pragma S.A. <br>
	 * @date 1/12/2012
	 * @param dto
	 * @throws Exception
	 */
	public void guardarInformacionCabezaPlancha(
			InfoDetalleFormatoPlanchaDTO dto, String imageToBase64)
			throws Exception {
		logicaCabezaPlancha.guardarInformacionCabezaPlancha(dto, imageToBase64);
	}

	/**
	 * Metodo que consulta la iformacion basica dela cabeza plancha.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a>
	 *         - Pragma S.A. <br>
	 * @date 1/12/2012
	 * @param numeroDocumento
	 * @return
	 * @throws Exception
	 */
	public InformacionCabezaPlanchaDTO consultarInformacionCabezaPlancha(
			Long numeroDocumento) throws Exception {
		return logicaCabezaPlancha
				.consultarInformacionCabezaPlancha(numeroDocumento);
	}

	/**
	 * Metodo que modifica la informacion de la cabeza de plancha en detalle
	 * formato plancha.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a>
	 *         - Pragma S.A. <br>
	 * @date 1/12/2012
	 * @param dto
	 * @throws Exception
	 */
	public void modificarInformacionCabezaPlancha(
			InfoDetalleFormatoPlanchaDTO dto, String imageToBase64)
			throws Exception {
		logicaCabezaPlancha.modificarInformacionCabezaPlancha(dto,
				imageToBase64);
	}

	/**
	 * Metodo que modifica la informacion de la cabeza de plancha en detalle
	 * formato plancha al finalizar.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a>
	 *         - Pragma S.A. <br>
	 * @date 1/12/2012
	 * @param dto
	 * @throws Exception
	 */
	public void finalizarEnviarInformacionCabezaPlancha(
			InfoDetalleFormatoPlanchaDTO dto, String imageToBase64)
			throws Exception {
		logicaCabezaPlancha.finalizarEnviarInformacionCabezaPlancha(dto,
				imageToBase64);
	}

	public EleFotoFormatoPlancha consultarFotoCabezaPlancha(Long codigoAsociado)
			throws Exception {
		return logicaCabezaPlancha.consultarFotoCabezaPlancha(codigoAsociado);
	}

	/**
	 * Metodo que modifica la informacion de la cabeza de plancha en detalle
	 * formato plancha al imprimir.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a>
	 *         - Pragma S.A. <br>
	 * @date 1/12/2012
	 * @param dto
	 * @throws Exception
	 */
	public void imprimirEnviarInformacionCabezaPlancha(
			InfoDetalleFormatoPlanchaDTO dto, Long consecutivoPlancha,
			String imageToBase64) throws Exception {
		logicaCabezaPlancha.imprimirEnviarInformacionCabezaPlancha(dto,
				consecutivoPlancha, imageToBase64);
	}

	/**
	 * Metodo que consulta el detale del formato de placha por clave primaria
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a>
	 *         - Pragma S.A. <br>
	 * @date 2/12/2012
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public EleDetalleFormatoPlancha consultarDetalleFormatoPlanchaPorId(Long id)
			throws Exception {
		return logicaCabezaPlancha.consultarDetalleFormatoPlanchaPorId(id);
	}

	/**
	 * Metodo que valida cuantos registros existen del detalle formato plancha
	 * por el formato inscripcion cabeza plancha.
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a>
	 *         - Pragma S.A. <br>
	 * @date 11/12/2012
	 * @param numeroDocumento
	 * @return Boolean
	 * @throws Exception
	 */
	public Boolean validarRegistroDetalleFormatoPlancha(Long numeroDocumento,
			Long codigoFormato) throws Exception {
		return logicaCabezaPlancha.validarRegistroDetalleFormatoPlancha(
				numeroDocumento, codigoFormato);
	}

	public Long obtieneFechaGradoAsociado(Long codAsociado) throws Exception {
		return logicaCabezaPlancha.obtieneFechaGradoAsociado(codAsociado);
	}

	public void guardarInformacionResolucion(InfoDetalleFormatoPlanchaDTO dto,
			String codResolucion) throws Exception {
		logicaCabezaPlancha.guardarInformacionResolucion(dto, codResolucion);
	}
}
