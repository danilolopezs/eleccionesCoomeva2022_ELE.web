package co.com.coomeva.habilidad.log;

public interface IProcesoHabAsociadoPerformanceLogger {
	public void log(String methodName, String error);
	public void log(String methodName, String error, String causa);
}
