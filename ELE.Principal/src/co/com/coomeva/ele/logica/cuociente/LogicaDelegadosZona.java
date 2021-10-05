package co.com.coomeva.ele.logica.cuociente;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import co.com.coomeva.ele.delegado.DelegadoAsociado;
import co.com.coomeva.ele.delegado.DelegadoCuocienteElectoral;
import co.com.coomeva.ele.delegado.DelegadoDelegadosRegional;
import co.com.coomeva.ele.delegado.DelegadoFiltros;
import co.com.coomeva.ele.entidades.cuociente.EleCuocienteDelegadosZona;
import co.com.coomeva.ele.entidades.cuociente.EleCuocienteElectoral;
import co.com.coomeva.ele.entidades.cuociente.dao.EleCuocienteDelegadosZonaDAO;
import co.com.coomeva.ele.entidades.habilidad.acceso.HibernateSessionFactoryElecciones2012;
import co.com.coomeva.ele.logica.LogicaNovedad;
import co.com.coomeva.ele.logica.LogicaProceso;
import co.com.coomeva.ele.modelo.FiltrosConsultasDTO;
import co.com.coomeva.ele.util.ConstantesProperties;
import co.com.coomeva.ele.utilidades.ConstantesNamedQueries;
import co.com.coomeva.ele.utilidades.GeneradorConsecutivos;
import co.com.coomeva.ele.utilidades.UtilNumber;
import co.com.coomeva.util.acceso.UtilAcceso;

public class LogicaDelegadosZona extends EleCuocienteDelegadosZonaDAO implements
		ILogicaDelegadosZona {

	private static LogicaDelegadosZona instance;
	private Double totalHabiles;
	private Double totalDirectos = 0d;
	private Double totalReciduo = 0d;
	private Double totalDelegados = 0d;
	
	private static HashMap<String, String> regionales;
	private static HashMap<String, String> zonas;
	private static HashMap<String, Integer> delegadosRegional;

	// Patrón Singular
	public static LogicaDelegadosZona getInstance() {
		if (instance == null) {
			instance = new LogicaDelegadosZona();
			cargarRegionales();
			delegadosRegional = new HashMap<String, Integer>();
		}
		return instance;
	}

	private static void cargarRegionales() {
		try {
			List<FiltrosConsultasDTO> listadoRegionales = DelegadoFiltros
					.getInstance().consultarRegionales();
			List<FiltrosConsultasDTO> listadoZonas = DelegadoFiltros
					.getInstance().consultarZonasElectorales();

			regionales = new HashMap<String, String>();
			for (int i = 0; i < listadoRegionales.size(); i++) {
				regionales.put(listadoRegionales.get(i).getCodigoFiltro()
						.toString(), listadoRegionales.get(i)
						.getDescripcionFiltro().toUpperCase());
			}

			zonas = new HashMap<String, String>();
			for (int i = 0; i < listadoZonas.size(); i++) {
				zonas.put(listadoZonas.get(i).getCodigoFiltro().toString(),
						listadoZonas.get(i).getDescripcionFiltro()
								.toUpperCase());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EleCuocienteDelegadosZona calcularDelegadosZona(
			String periodoElectoral, String codRegional, String codZona,
			Double sumaHabiles) throws Exception {

		boolean nuevo = true;
		Double sumaEspHabiles;
		/**
		 * Se validan los campos requeridos para el calculo
		 */
		if (periodoElectoral == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.periodoEelctoral"));
		}

		if (codRegional == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.regional"));
		}

		if (codZona == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.zona"));
		}

		if (sumaHabiles == null) {
			// totalAsociadosPorZona
			// sumaHabiles = consultarDelegadosZona(periodoElectoral,
			// codRegional, codZona);
			// List<EleCuocienteDelegadosZona> listado;

			// Se consulta el total de asociados por zona.
			sumaHabiles = new Double(DelegadoAsociado.getInstance()
					.consultarTotalAsociadosHabilesAsociado(new Long(codZona)));
			// sumaHabiles =
			// DelegadoAsociado.getInstance().totalAsociadosPorZona(new
			// Long(codZona));

			/*
			 * listado = consultarDelegadosZona(periodoElectoral,codRegional,
			 * codZona); if(listado != null && listado.size() == 1){ sumaHabiles
			 * = listado.get(0).getSumaHabiles(); }
			 */
			/*
			 * throw new Exception(UtilAcceso.getParametroFuenteS(
			 * ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
			 * "campo.obligatorio.delegadoZona.habiles"));
			 */
		}
		
		sumaEspHabiles = new Double(DelegadoAsociado.getInstance().consultarTotalAsociadosEspecialesHabilesAsociado(new Long(codZona)));

		/**
		 * Se debe consultar si existe el registro de delegados por Zona
		 */
		List<EleCuocienteDelegadosZona> listado;
		EleCuocienteDelegadosZona cuocienteDelegadosZona = null;
		listado = consultarDelegadosZona(periodoElectoral, codRegional, codZona);

		if (listado != null && listado.size() == 1) {
			cuocienteDelegadosZona = listado.get(0);
		}

		if (cuocienteDelegadosZona == null) {
			nuevo = true;
			cuocienteDelegadosZona = new EleCuocienteDelegadosZona();

			Integer idRegistro = new Integer(
					GeneradorConsecutivos
							.getInstance()
							.getConsecutivo(
									ConstantesNamedQueries.QUERY_SEQ_CUOCIENTE_DELEGADOS_ZONA,
									this.getSession()).toString());
			cuocienteDelegadosZona.setIdRegistro(idRegistro);
		} else {
			nuevo = false;
		}

		/**
		 * Se consulta el cuociente electoral para realizar el calculo de
		 * delegados directos y por residuo
		 */
		EleCuocienteElectoral cuocienteElectoral = DelegadoCuocienteElectoral
				.getInstance().getConsultarCuocienteElectoral(periodoElectoral);

		/**
		 * Se valida que exista un cuociente para el periodo ingresado
		 */
		if (cuocienteElectoral == null) {

			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.noCuociente"));
		}

		/**
		 * Se calcula la razon suma de habiles / cuociente electoral. Esta razón
		 * determina el total de delegados directos de dicha regional
		 */
		// sumaHabiles =
		// DelegadoAsociado.getInstance().totalAsociadosPorZona(new
		// Long(codZona));

		
		/* 
		 * Modificado: Juliana Nobile
		 * Se comenta esta validacion, porque aveces una zona electoral puede no tener asociados habiles.
		 */
		/*if (sumaHabiles == 0) {
			throw new Exception("campo.obligatorio.delegadoZona.asociadosHabi");
		}*/

		// TODO JD : Agregar aquí las novedades, restar al sumaHabiles
		
		String fechaProceso = LogicaProceso.getInstance().consultaFechaUltimoProcesoEjecutado();
		Double novedades = LogicaNovedad.getInstance().consultarNumeroNov(fechaProceso, "1", new Long(codZona));
		Double novedadesEsp = LogicaNovedad.getInstance().consultarNumeroNovAsocEsp(fechaProceso, "1", new Long(codZona));
		
		//TODO novedades especiales
		//Double novedadesEsp;
		
		
		double delegados = sumaHabiles / cuocienteElectoral.getCuocienteElectoral();
		delegados = UtilNumber.getInstance().redondear(delegados, 4);
		cuocienteDelegadosZona.setDelegados(delegados);
		/**
		 * Se obtiene la parte entera y decimal de la fracción
		 */
		long entero = (long) delegados;
		double decimal = delegados - (double) entero;
		cuocienteDelegadosZona.setFraccion(decimal);
		cuocienteDelegadosZona.setPeriodoElectoral(periodoElectoral);
		cuocienteDelegadosZona.setCodRegional(codRegional);
		cuocienteDelegadosZona.setCodZona(codZona);
		cuocienteDelegadosZona.setSumaHabiles(sumaHabiles - novedades);
		
		
		cuocienteDelegadosZona.setSumaNovedades(novedades);
		cuocienteDelegadosZona.setSumaEspHabiles(sumaEspHabiles);
		cuocienteDelegadosZona.setSumaNovedadesEsp(novedadesEsp);
		cuocienteDelegadosZona.setSumaTotalHabiles(sumaHabiles);
		cuocienteDelegadosZona.setDelegados(delegados);
		cuocienteDelegadosZona.setDelegadosDirectos(0d);
		cuocienteDelegadosZona.setDelegadosResiduo(0d);
		cuocienteDelegadosZona.setDistribuidosRestantes(0d);
		cuocienteDelegadosZona.setTotalDelegadosZona(0d);

		Transaction transaction = null;
		try {
			transaction = this.getSession().beginTransaction();
			if (nuevo) {
				save(cuocienteDelegadosZona);
			} else {
				merge(cuocienteDelegadosZona);
			}

			this.getSession().flush();
			transaction.commit();
			return cuocienteDelegadosZona;

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		} finally {
			this.getSession().clear();
			this.getSession().close();
		}
	}

	public EleCuocienteDelegadosZona calcularDelegadosZonaSinTr(
			String periodoElectoral, String codRegional, String codZona,
			Double sumaHabiles) throws Exception {

		boolean nuevo = true;
		/**
		 * Se validan los campos requeridos para el calculo
		 */
		if (periodoElectoral == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.periodoEelctoral"));
		}

		if (codRegional == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.regional"));
		}

		if (codZona == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.zona"));
		}

		if (sumaHabiles == null) {
			// totalAsociadosPorZona
			// sumaHabiles = consultarDelegadosZona(periodoElectoral,
			// codRegional, codZona);
			// List<EleCuocienteDelegadosZona> listado;

			// Se consulta el total de asociados por zona.
			sumaHabiles = new Double(DelegadoAsociado.getInstance()
					.consultarTotalAsociadosHabilesAsociado(new Long(codZona)));
			// sumaHabiles =
			// DelegadoAsociado.getInstance().totalAsociadosPorZona(new
			// Long(codZona));

			/*
			 * listado = consultarDelegadosZona(periodoElectoral,codRegional,
			 * codZona); if(listado != null && listado.size() == 1){ sumaHabiles
			 * = listado.get(0).getSumaHabiles(); }
			 */
			/*
			 * throw new Exception(UtilAcceso.getParametroFuenteS(
			 * ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
			 * "campo.obligatorio.delegadoZona.habiles"));
			 */
		}

		/**
		 * Se debe consultar si existe el registro de delegados por Zona
		 */
		List<EleCuocienteDelegadosZona> listado;
		EleCuocienteDelegadosZona cuocienteDelegadosZona = null;
		listado = consultarDelegadosZona(periodoElectoral, codRegional, codZona);

		if (listado != null && listado.size() == 1) {
			cuocienteDelegadosZona = listado.get(0);
		}

		if (cuocienteDelegadosZona == null) {
			nuevo = true;
			cuocienteDelegadosZona = new EleCuocienteDelegadosZona();

			Integer idRegistro = new Integer(
					GeneradorConsecutivos
							.getInstance()
							.getConsecutivo(
									ConstantesNamedQueries.QUERY_SEQ_CUOCIENTE_DELEGADOS_ZONA,
									this.getSession()).toString());
			cuocienteDelegadosZona.setIdRegistro(idRegistro);
		} else {
			nuevo = false;
		}

		/**
		 * Se consulta el cuociente electoral para realizar el calculo de
		 * delegados directos y por residuo
		 */
		EleCuocienteElectoral cuocienteElectoral = DelegadoCuocienteElectoral
				.getInstance().getConsultarCuocienteElectoral(periodoElectoral);

		/**
		 * Se valida que exista un cuociente para el periodo ingresado
		 */
		if (cuocienteElectoral == null) {

			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.noCuociente"));
		}

		/**
		 * Se calcula la razon suma de habiles / cuociente electoral. Esta razón
		 * determina el total de delegados directos de dicha regional
		 */
		// sumaHabiles =
		// DelegadoAsociado.getInstance().totalAsociadosPorZona(new
		// Long(codZona));

		// Se comenta porque es posible que una zona no tenga asociados habiles
		/*if (sumaHabiles == 0) {
			throw new Exception("campo.obligatorio.delegadoZona.asociadosHabi");
		}*/
		double delegados = sumaHabiles / cuocienteElectoral.getCuocienteElectoral();
		delegados = UtilNumber.getInstance().redondear(delegados, 4);
		cuocienteDelegadosZona.setDelegados(delegados);
		/**
		 * Se obtiene la parte entera y decimal de la fracción
		 */
		long entero = (long) delegados;
		double decimal = delegados - (double) entero;
		cuocienteDelegadosZona.setFraccion(UtilNumber.getInstance().redondear(decimal, 4));
		cuocienteDelegadosZona.setPeriodoElectoral(periodoElectoral);
		cuocienteDelegadosZona.setCodRegional(codRegional);
		cuocienteDelegadosZona.setCodZona(codZona);
		cuocienteDelegadosZona.setSumaHabiles(sumaHabiles);
		cuocienteDelegadosZona.setDelegados(delegados);
		cuocienteDelegadosZona.setDelegadosDirectos(new Double(entero));
		cuocienteDelegadosZona.setDelegadosResiduo(0d);
		cuocienteDelegadosZona.setDistribuidosRestantes(0d);
		cuocienteDelegadosZona.setTotalDelegadosZona(0d);

		try {
			if (nuevo) {
				save(cuocienteDelegadosZona);
			} else {
				merge(cuocienteDelegadosZona);
			}

			this.getSession().flush();
			return cuocienteDelegadosZona;

		} catch (Exception e) {
			throw e;
		} finally {
			this.getSession().clear();
		}
	}

	/**
	 * Proceso para el calculo de los delegados directos y por reciduo.
	 * 
	 * @throws Exception
	 */
	public List<EleCuocienteDelegadosZona> calcularDelegadosZonaFinal(
			String periodoElectoral) throws Exception {
		List<EleCuocienteDelegadosZona> resultado = consultarDelegadosZona(
				periodoElectoral, "fraccion");

		Double delegadosAElegir = 0d;
		Double delegadosElegidos = 0d;

		totalDirectos = 0d;
		totalReciduo = 0d;
		totalHabiles = 0d;
		totalDelegados = 0d;

		if (resultado != null) {
			/**
			 * Se consulta el cuociente electoral para realizar el calculo de
			 * delegados directos y por residuo
			 */
			Transaction transaction = null;

			try {
				EleCuocienteElectoral cuocienteElectoral = DelegadoCuocienteElectoral
						.getInstance().getConsultarCuocienteElectoral(
								periodoElectoral);

				if (cuocienteElectoral != null) {
					transaction = this.getSession().beginTransaction();
					
					delegadosAElegir = cuocienteElectoral
							.getFinalTotalDelegadosElegir();

					for (int i = 0; i < resultado.size(); i++) {
						// Se recalculan los valores de cada zona
						resultado.set(i, calcularDelegadosZonaSinTr(
								periodoElectoral, resultado.get(i)
										.getCodRegional(), resultado.get(i)
										.getCodZona(), null));
						delegadosElegidos = delegadosElegidos
								+ resultado.get(i).getDelegadosDirectos();
						delegadosElegidos = delegadosElegidos
								+ resultado.get(i).getDelegadosResiduo();

						/**
						 * Se ajustó esta parte para que durante el proceso del
						 * calculo de los delegados por zona se calculara los
						 * asociados hábiles por zona
						 */
						resultado
								.get(i)
								.setSumaHabiles(
										new Double(
												DelegadoAsociado
														.getInstance()
														.consultarTotalAsociadosHabilesAsociado(
																new Long(
																		resultado
																				.get(
																						i)
																				.getCodZona()))));
						totalHabiles = (totalHabiles
								+ resultado.get(i).getSumaHabiles());
						totalDirectos = totalDirectos
								+ resultado.get(i).getDelegadosDirectos();
					}

					for (int i = 0; (i < resultado.size()); i++) {

						if (delegadosElegidos < delegadosAElegir) {
							// Se suma el nuevo delegado correspondiente al
							// cuociente.
							resultado.get(i).setDelegadosResiduo(resultado.get(i).getDelegadosResiduo() + 1);
							delegadosElegidos = delegadosElegidos + resultado.get(i).getDelegadosResiduo();
							Double total = resultado.get(i).getDelegadosDirectos() + resultado.get(i).getDelegadosResiduo();
							resultado.get(i).setTotalDelegadosZona(total);
						}else{
							Double total = resultado.get(i).getDelegadosDirectos() + resultado.get(i).getDelegadosResiduo();
							resultado.get(i).setTotalDelegadosZona(total);
						}

						totalReciduo = totalReciduo + resultado.get(i).getDelegadosResiduo();
						totalDelegados = totalDelegados + resultado.get(i).getTotalDelegadosZona();
					}

					/**
					 * Se deben guardar todos los registros actualizados
					 */
					Integer valor;
					delegadosRegional = new HashMap<String, Integer>(0);
					for (int z = 0; z < resultado.size(); z++) {
						
						if( resultado.get(z) != null ){
							if( delegadosRegional.get(resultado.get(z).getCodRegional()) == null){
								delegadosRegional.put(resultado.get(z).getCodRegional(), new Integer(resultado.get(z).getTotalDelegadosZona().intValue()));
							}else{
								valor = resultado.get(z).getTotalDelegadosZona().intValue();
								valor = valor + delegadosRegional.get(resultado.get(z).getCodRegional());
								delegadosRegional.put(resultado.get(z).getCodRegional(), valor);
							}
						}
						guardarDelegadosZona(resultado.get(z));
					}
					
					/**
					 * Se registran los delegados por Regional
					 */
					DelegadoDelegadosRegional.getInstance().registrarDelegadosRegional(delegadosRegional, cuocienteElectoral);
					this.getSession().flush();
					transaction.commit();
				}
			} catch (Exception e) {
				e.printStackTrace();
				if (transaction != null) {
					transaction.rollback();
				}
				throw e;
			} finally {
				this.getSession().clear();
				this.getSession().close();
			}

			resultado = cargarDescripciones(resultado);

			EleCuocienteDelegadosZona cuocienteDelegadosZona;
			cuocienteDelegadosZona = new EleCuocienteDelegadosZona();
			cuocienteDelegadosZona.setPeriodoElectoral("TOTAL");
			cuocienteDelegadosZona.setDelegadosDirectos(totalDirectos);
			cuocienteDelegadosZona.setDelegadosResiduo(totalReciduo);
			cuocienteDelegadosZona.setSumaHabiles(totalHabiles);
			cuocienteDelegadosZona.setSumaTotalHabiles(totalHabiles);
			cuocienteDelegadosZona.setTotalDelegadosZona(totalDelegados);
			resultado.add(cuocienteDelegadosZona);
		}

		return resultado;
	}

	/**
	 * Consulta del cuociente electoral
	 */
	public List<EleCuocienteDelegadosZona> consultarDelegadosZona(
			String periodoElectoral, String codRegional, String codZona)
			throws Exception {
		/**
		 * Se validan los campos requeridos para el calculo
		 */
		if (periodoElectoral == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.periodoEelctoral"));
		}

		if (codRegional == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.regional"));
		}

		/*
		 * if (codZona == null) { throw new
		 * Exception(UtilAcceso.getParametroFuenteS(
		 * ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
		 * "campo.obligatorio.delegadoZona.zona")); }
		 */

		/**
		 * Se consulta los delegados de la zona y regional entregados por
		 * parámetro
		 */
		Criteria crit = getSession().createCriteria(
				EleCuocienteDelegadosZona.class);
		crit.add(Restrictions.eq("periodoElectoral", periodoElectoral));
		crit.add(Restrictions.eq("codRegional", codRegional));

		if (codZona != null) {
			crit.add(Restrictions.eq("codZona", codZona));
		}

		List<EleCuocienteDelegadosZona> cuocienteDelegadosZona = crit.list();
		cuocienteDelegadosZona = cargarDescripciones(cuocienteDelegadosZona);
		return cuocienteDelegadosZona;
	}

	/**
	 * Consulta del cuociente electoral
	 */
	public List<EleCuocienteDelegadosZona> consultarDelegadosZona(
			String periodoElectoral, String ordenarPor) throws Exception {
		/**
		 * Se validan los campos requeridos para el calculo
		 */
		if (periodoElectoral == null) {
			UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.periodoEelctoral");
		}

		/**
		 * Se consulta los delegados de la zona y regional entregados por
		 * parámetro
		 */
		Criteria crit = getSession().createCriteria(EleCuocienteDelegadosZona.class);
		crit.add(Restrictions.eq("periodoElectoral", periodoElectoral));

		if (ordenarPor != null
				&& !ordenarPor.equals(UtilAcceso.getParametroFuenteS(
						"parametros", "selectValueDefault"))) {
			crit.addOrder(Order.desc(ordenarPor.toString()));
		}

		List<EleCuocienteDelegadosZona> cuocienteDelegadosZona = crit.list();
		cuocienteDelegadosZona = cargarDescripciones(cuocienteDelegadosZona);
		return cuocienteDelegadosZona;
	}

	public void guardarDelegadosZona(
			EleCuocienteDelegadosZona eleCuocienteDelegadosZona)
			throws Exception {
		/**
		 * Se validan los campos requeridos para el calculo
		 
		if (eleCuocienteDelegadosZona.getIdRegistro() == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.periodoEelctoral"));
		}
		if (eleCuocienteDelegadosZona.getPeriodoElectoral() == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.periodoEelctoral"));
		}

		if (eleCuocienteDelegadosZona.getCodRegional() == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.regional"));
		}

		if (eleCuocienteDelegadosZona.getCodZona() == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.zona"));
		}

		List<EleCuocienteDelegadosZona> listado;
		EleCuocienteDelegadosZona cuocienteDelegadosZona = null;
		listado = consultarDelegadosZona(eleCuocienteDelegadosZona
				.getPeriodoElectoral(), eleCuocienteDelegadosZona
				.getCodRegional(), eleCuocienteDelegadosZona.getCodZona());

		if (listado != null && listado.size() == 1) {
			cuocienteDelegadosZona = listado.get(0);
		}

		if (cuocienteDelegadosZona == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.noExiste"));
		}

		if (eleCuocienteDelegadosZona.getSumaHabiles() == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.sumaHabiles"));
		}

		if (eleCuocienteDelegadosZona.getDelegados() == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.delegados"));
		}

		if (eleCuocienteDelegadosZona.getDelegadosDirectos() == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.delegadosDirectos"));
		}

		if (eleCuocienteDelegadosZona.getFraccion() == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.fraccion"));
		}

		if (eleCuocienteDelegadosZona.getDelegadosResiduo() == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.delegadosReciduo"));
		}
		if (eleCuocienteDelegadosZona.getDistribuidosRestantes() == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.distribuidosRestantes"));
		}
		if (eleCuocienteDelegadosZona.getTotalDelegadosZona() == null) {
			throw new Exception(UtilAcceso.getParametroFuenteS(
					ConstantesProperties.NOMBRE_ARCHIVO_MENSAJES,
					"campo.obligatorio.delegadoZona.totalDelegadosZona"));
		}*/

		merge(eleCuocienteDelegadosZona);
	}
	
	public void eliminarDelegadosZona(String periodo){
		
		Session session=null;
		Query query=null;
		
    	try {

    		session= HibernateSessionFactoryElecciones2012.getSession();
    		query = session.createQuery("DELETE FROM EleCuocienteDelegadosZona dz	WHERE dz.periodoElectoral = :periodo");
    		query.setParameter("periodo", periodo);
    		int result = query.executeUpdate();

			query.executeUpdate();
        } catch (RuntimeException re) {
            throw re;
        }
    }

	public List<EleCuocienteDelegadosZona> cargarDescripciones(
			List<EleCuocienteDelegadosZona> cuocienteDelegadosZona) {
		if (cuocienteDelegadosZona != null) {
			for (int i = 0; i < cuocienteDelegadosZona.size(); i++) {
				cuocienteDelegadosZona.get(i).setDescCodRegional(
						regionales.get(cuocienteDelegadosZona.get(i)
								.getCodRegional()));
				cuocienteDelegadosZona.get(i).setDescCodZona(
						zonas.get(cuocienteDelegadosZona.get(i).getCodZona()));
			}
		}
		return cuocienteDelegadosZona;
	}
	
	
	public List<EleCuocienteDelegadosZona> cargarNovedadesZona(
			List<EleCuocienteDelegadosZona> cuocienteDelegadosZona) {
		if (cuocienteDelegadosZona != null) 
		{
			for (int i = 0; i < cuocienteDelegadosZona.size(); i++) 
			{
				try 
				{
					String fechaProceso = LogicaProceso.getInstance().consultaFechaUltimoProcesoEjecutado();
					Double numeroNovedades = LogicaNovedad.getInstance().consultarNumeroNov(fechaProceso, "1", new Long(cuocienteDelegadosZona.get(i).getCodZona()));
					if (!numeroNovedades.equals(null)) 
					{
						cuocienteDelegadosZona.get(i).setSumaNovedades(numeroNovedades);
					}
					else
					{
						cuocienteDelegadosZona.get(i).setSumaNovedades(new Double(0));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cuocienteDelegadosZona;
	}

	public Double getTotalDirectos() {
		return totalDirectos;
	}

	public void setTotalDirectos(Double totalDirectos) {
		this.totalDirectos = totalDirectos;
	}

	public Double getTotalReciduo() {
		return totalReciduo;
	}

	public void setTotalReciduo(Double totalReciduo) {
		this.totalReciduo = totalReciduo;
	}

	public HashMap<String, String> getRegionales() {
		return regionales;
	}

	public void setRegionales(HashMap<String, String> regionales) {
		this.regionales = regionales;
	}

	public HashMap<String, String> getZonas() {
		return zonas;
	}

	public void setZonas(HashMap<String, String> zonas) {
		this.zonas = zonas;
	}

	public Double getTotalHabiles() {
		return totalHabiles;
	}

	public void setTotalHabiles(Double totalHabiles) {
		this.totalHabiles = totalHabiles;
	}

}
