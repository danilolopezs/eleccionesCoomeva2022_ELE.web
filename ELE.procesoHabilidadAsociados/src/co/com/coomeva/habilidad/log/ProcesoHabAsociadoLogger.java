package co.com.coomeva.habilidad.log;

import org.slf4j.Logger;
import org.slf4j.Marker;

public class ProcesoHabAsociadoLogger extends ProcesoHabAsociadoPerformanceLogger implements IProcesoHabAsociadoLogger{
	private Logger logger;

	public ProcesoHabAsociadoLogger(Logger logger) {
		super(logger);
		this.logger = logger;
	}

	public void debug(String arg0) {
		logger.debug(arg0);
	}

	public void debug(String arg0, Object arg1) {
		logger.debug(arg0, arg1);
	}

	public void debug(String arg0, Object[] arg1) {
		logger.debug(arg0, arg1);
	}

	public void debug(String arg0, Throwable arg1) {
		logger.debug(arg0, arg1);

	}

	public void debug(Marker arg0, String arg1) {
		logger.debug(arg0, arg1);

	}

	public void debug(String arg0, Object arg1, Object arg2) {
		logger.debug(arg0, arg1, arg2);
	}

	public void debug(Marker arg0, String arg1, Object arg2) {
		logger.debug(arg0, arg1, arg2);
	}

	public void debug(Marker arg0, String arg1, Object[] arg2) {
		logger.debug(arg0, arg1, arg2);
	}

	public void debug(Marker arg0, String arg1, Throwable arg2) {
		logger.debug(arg0, arg1, arg2);
	}

	public void debug(Marker arg0, String arg1, Object arg2, Object arg3) {
		logger.debug(arg0, arg1, arg2, arg3);
	}

	public void error(String arg0) {
		logger.error(arg0);
	}

	public void error(String arg0, Object arg1) {
		logger.error(arg0, arg1);
	}

	public void error(String arg0, Object[] arg1) {
		logger.error(arg0, arg1);
	}

	public void error(String arg0, Throwable arg1) {
		logger.error(arg0, arg1);

	}

	public void error(Marker arg0, String arg1) {
		logger.error(arg0, arg1);

	}

	public void error(String arg0, Object arg1, Object arg2) {
		logger.error(arg0, arg1, arg2);
	}

	public void error(Marker arg0, String arg1, Object arg2) {
		logger.error(arg0, arg1, arg2);
	}

	public void error(Marker arg0, String arg1, Object[] arg2) {
		logger.error(arg0, arg1, arg2);
	}

	public void error(Marker arg0, String arg1, Throwable arg2) {
		logger.error(arg0, arg1, arg2);
	}

	public void error(Marker arg0, String arg1, Object arg2, Object arg3) {
		logger.error(arg0, arg1, arg2, arg3);
	}

	public String getName() {
		return logger.getName();
	}

	public void info(String arg0) {
		logger.info(arg0);
	}

	public void info(String arg0, Object arg1) {
		logger.info(arg0, arg1);
	}

	public void info(String arg0, Object[] arg1) {
		logger.info(arg0, arg1);
	}

	public void info(String arg0, Throwable arg1) {
		logger.info(arg0, arg1);

	}

	public void info(Marker arg0, String arg1) {
		logger.info(arg0, arg1);

	}

	public void info(String arg0, Object arg1, Object arg2) {
		logger.info(arg0, arg1, arg2);
	}

	public void info(Marker arg0, String arg1, Object arg2) {
		logger.info(arg0, arg1, arg2);
	}

	public void info(Marker arg0, String arg1, Object[] arg2) {
		logger.info(arg0, arg1, arg2);
	}

	public void info(Marker arg0, String arg1, Throwable arg2) {
		logger.info(arg0, arg1, arg2);
	}

	public void info(Marker arg0, String arg1, Object arg2, Object arg3) {
		logger.info(arg0, arg1, arg2, arg3);
	}

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public boolean isDebugEnabled(Marker arg0) {
		return logger.isDebugEnabled(arg0);
	}

	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	public boolean isErrorEnabled(Marker arg0) {
		return logger.isErrorEnabled(arg0);
	}

	public boolean isInfoEnabled() {

		return logger.isInfoEnabled();
	}

	public boolean isInfoEnabled(Marker arg0) {
		return logger.isInfoEnabled(arg0);
	}

	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	public boolean isTraceEnabled(Marker arg0) {
		return logger.isTraceEnabled(arg0);
	}

	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	public boolean isWarnEnabled(Marker arg0) {
		return logger.isWarnEnabled(arg0);
	}

	public void trace(String arg0) {
		logger.trace(arg0);
	}

	public void trace(String arg0, Object arg1) {
		logger.trace(arg0, arg1);
	}

	public void trace(String arg0, Object[] arg1) {
		logger.trace(arg0, arg1);
	}

	public void trace(String arg0, Throwable arg1) {
		logger.trace(arg0, arg1);

	}

	public void trace(Marker arg0, String arg1) {
		logger.trace(arg0, arg1);

	}

	public void trace(String arg0, Object arg1, Object arg2) {
		logger.trace(arg0, arg1, arg2);
	}

	public void trace(Marker arg0, String arg1, Object arg2) {
		logger.trace(arg0, arg1, arg2);
	}

	public void trace(Marker arg0, String arg1, Object[] arg2) {
		logger.trace(arg0, arg1, arg2);
	}

	public void trace(Marker arg0, String arg1, Throwable arg2) {
		logger.trace(arg0, arg1, arg2);
	}

	public void trace(Marker arg0, String arg1, Object arg2, Object arg3) {
		logger.trace(arg0, arg1, arg2, arg3);
	}

	public void warn(String arg0) {
		logger.warn(arg0);
	}

	public void warn(String arg0, Object arg1) {
		logger.warn(arg0, arg1);
	}

	public void warn(String arg0, Object[] arg1) {
		logger.warn(arg0, arg1);
	}

	public void warn(String arg0, Throwable arg1) {
		logger.warn(arg0, arg1);

	}

	public void warn(Marker arg0, String arg1) {
		logger.warn(arg0, arg1);

	}

	public void warn(String arg0, Object arg1, Object arg2) {
		logger.warn(arg0, arg1, arg2);
	}

	public void warn(Marker arg0, String arg1, Object arg2) {
		logger.warn(arg0, arg1, arg2);
	}

	public void warn(Marker arg0, String arg1, Object[] arg2) {
		logger.warn(arg0, arg1, arg2);
	}

	public void warn(Marker arg0, String arg1, Throwable arg2) {
		logger.warn(arg0, arg1, arg2);
	}

	public void warn(Marker arg0, String arg1, Object arg2, Object arg3) {
		logger.warn(arg0, arg1, arg2, arg3);
	}
}
