package co.com.coomeva.delegado.reportesToExcel;

import java.io.ByteArrayOutputStream;
import java.util.List;

import co.com.coomeva.logica.reportesToExcel.LogicaReportesToExcel;
import co.com.coomeva.modelo.reportesToExcel.ReporteExcel;



public class DelegadoReportesToExcel {
	
	
	public void generacionReportesExcel(String encabezados, String datosCabecera, List datosReporte,ReporteExcel reporteExcel)throws Exception { 

		LogicaReportesToExcel logicaExcel = new LogicaReportesToExcel();
		logicaExcel.generacionReportesExcel(encabezados, datosCabecera, datosReporte, reporteExcel);
	}
	
	public ByteArrayOutputStream generacionReportesExcelByteArrayOutputStream(String encabezados, String datosCabecera, List datosReporte,ReporteExcel reporteExcel)throws Exception { 
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		LogicaReportesToExcel logicaExcel = new LogicaReportesToExcel();
		byteArrayOutputStream = logicaExcel.generacionReportesExcel(encabezados, datosCabecera, datosReporte, reporteExcel,true);
		return byteArrayOutputStream;
	}

	
}
