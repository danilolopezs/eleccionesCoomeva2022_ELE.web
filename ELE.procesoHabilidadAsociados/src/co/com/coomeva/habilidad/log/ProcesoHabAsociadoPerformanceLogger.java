package co.com.coomeva.habilidad.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcesoHabAsociadoPerformanceLogger implements IProcesoHabAsociadoPerformanceLogger{
	
	private Logger logger;

	public ProcesoHabAsociadoPerformanceLogger(Logger logger) {
		super();
		this.logger = LoggerFactory.getLogger("PROCESO_HAB_ASOCIADOS_INFO");
	}	

	public void log(String methodName, String error) {
		if (logger.isInfoEnabled() == false) {
			return;
		}

		StringBuffer msgLog = new StringBuffer();

		msgLog.append(" ");
		msgLog.append("Method name: ");
		msgLog.append(methodName);
		msgLog.append(" ");
		msgLog.append("Error: ");
		msgLog.append(error);

		logger.info(msgLog.toString());
	}

	public void log(String methodName, String error, String causa) {
		if (logger.isInfoEnabled() == false) {
			return;
		}

		StringBuffer msgLog = new StringBuffer();

		msgLog.append(" ");
		msgLog.append("Method name: ");
		msgLog.append(methodName);
		msgLog.append(" ");
		msgLog.append("Error: ");
		msgLog.append(error);
		msgLog.append(" ");
		msgLog.append("Causa: ");
		msgLog.append(causa);
		
		logger.info(msgLog.toString());
	}

}
