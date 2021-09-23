function servletInvokerXLS(){
	var protocolo = location.protocol;
	var ip = location.hostname;
	var puerto = location.port;
	var ipUrl = protocolo+"//"+ip+":"+puerto+"/ELE.web/servlet/ArchivoXls";
	window.open(ipUrl,"Archivo_XLS","toolbar=yes,status=yes,scrollbars=yes,location=no,menubar=no,directories=no,width=850,height=600,left=50,top=0,resizable=1");
}

function servletInvokerAsociado(){
	var protocolo = location.protocol;
	var ip = location.hostname;
	var puerto = location.port;
	var ipUrl = protocolo+"//"+ip+":"+puerto+"/ELE.web/servlet/ReporteAsociado";
	window.open(ipUrl,"REPORTE_ASOCIADOS","toolbar=yes,status=yes,scrollbars=yes,location=no,menubar=no,directories=no,width=850,height=600,left=50,top=0,resizable=1");
}

function servletInvoker(){
	var protocolo = location.protocol;
	var ip = location.hostname;
	var puerto = location.port;
	var ipUrl = protocolo+"//"+ip+":"+puerto+"/ELE.web/servlet/Reporte";
	window.open(ipUrl,"REPORTE_LOG","toolbar=yes,status=yes,scrollbars=yes,location=no,menubar=no,directories=no,width=850,height=600,left=50,top=0,resizable=1");
}

function servletInvokerLog(){
	var protocolo = location.protocol;
	var ip = location.hostname;
	var puerto = location.port;
	var ipUrl = protocolo+"//"+ip+":"+puerto+"/ELE.web/servlet/ReporteLog";
	window.open(ipUrl,"REPORTE_LOG","toolbar=yes,status=yes,scrollbars=yes,location=no,menubar=no,directories=no,width=850,height=600,left=50,top=0,resizable=1");
}

function servletInvokerRepPlanchas(){
	var protocolo = location.protocol;
	var ip = location.hostname;
	var puerto = location.port;
	var ipUrl = protocolo+"//"+ip+":"+puerto+"/ELE.web/servlet/ReportePlanchas";
	window.open(ipUrl,"REPORTE_PLANCHAS","toolbar=yes,status=yes,scrollbars=yes,location=no,menubar=no,directories=no,width=850,height=600,left=50,top=0,resizable=1");
}

function servletInvokerCuadernillo(){
	var protocolo = location.protocol;
	var ip = location.hostname;
	var puerto = location.port;
	var ipUrl = protocolo+"//"+ip+":"+puerto+"/ELE.web/servlet/Cuadernillo";
	window.open(ipUrl,"CUADERNILLO","toolbar=yes,status=yes,scrollbars=yes,location=no,menubar=no,directories=no,width=850,height=600,left=50,top=0,resizable=1");
}

function servletInvokerRepoLog()
{
	var protocolo = location.protocol;
	var ip = location.hostname;
	var puerto = location.port;
	var ipUrl = protocolo+"//"+ip+":"+puerto+"/ELE.web/servlet/ReportePlanLog";
	window.open(ipUrl,"REPORTE","toolbar=yes,status=yes,scrollbars=yes,location=no,menubar=no,directories=no,width=850,height=600,left=50,top=0,resizable=1");
}

/**
 * Permite descargar los reportes de Habilidades, Inhabilidades y novedades
 * formato excel y pdf
 */

function servletReportesInhabilidades(){
	var protocolo = location.protocol;
	var ip = location.hostname;
	var puerto = location.port;
	var ipUrl = protocolo+"//"+ip+":"+puerto+"/ELE.web/servlet/ServletReportesInhabilidades";
	window.open(ipUrl,"_blank","REPORTE","toolbar=yes,status=yes,scrollbars=yes,location=no,menubar=no,directories=no,width=850,height=600,left=50,top=0,resizable=1");
}

function ServletReportesHabilidadesWeb(){
	var protocolo = location.protocol;
	var ip = location.hostname;
	var puerto = location.port;
	var ipUrl = protocolo+"//"+ip+":"+puerto+"/ELE.web/servlet/ServletReportesHabilidadesWeb";
	window.open(ipUrl,"_blank","REPORTE","toolbar=yes,status=yes,scrollbars=yes,location=no,menubar=no,directories=no,width=850,height=600,left=50,top=0,resizable=1");
}

function ServletReportesJasper(){
	var protocolo = location.protocol;
	var ip = location.hostname;
	var puerto = location.port;
	var ipUrl = protocolo+"//"+ip+":"+puerto+"/ELE.web/servlet/ServletReportesJasper";
	window.open(ipUrl,"_blank","REPORTE","toolbar=yes,status=yes,scrollbars=yes,location=no,menubar=no,directories=no,width=850,height=600,left=50,top=0,resizable=1");
}

function ServletReportesNovedades(){
	var protocolo = location.protocol;
	var ip = location.hostname;
	var puerto = location.port;
	var ipUrl = protocolo+"//"+ip+":"+puerto+"/ELE.web/servlet/ServletReportesNovedades";
	window.open(ipUrl,"_blank","REPORTE","toolbar=yes,status=yes,scrollbars=yes,location=no,menubar=no,directories=no,width=850,height=600,left=50,top=0,resizable=1");
}

function ServletReportesResumenHab(){
	var protocolo = location.protocol;
	var ip = location.hostname;
	var puerto = location.port;
	var ipUrl = protocolo+"//"+ip+":"+puerto+"/ELE.web/servlet/ServletReportesResumenHab";
	window.open(ipUrl,"_blank","REPORTE","toolbar=yes,status=yes,scrollbars=yes,location=no,menubar=no,directories=no,width=850,height=600,left=50,top=0,resizable=1");
}

function ServletReporteResumenNovedades(){
	var protocolo = location.protocol;
	var ip = location.hostname;
	var puerto = location.port;
	var ipUrl = protocolo+"//"+ip+":"+puerto+"/ELE.web/servlet/ServletReporteResumenNovedades";
	window.open(ipUrl,"_blank","REPORTE","toolbar=yes,status=yes,scrollbars=yes,location=no,menubar=no,directories=no,width=850,height=600,left=50,top=0,resizable=1");
}

function ServletReportePlanchasEstado(){
	var protocolo = location.protocol;
	var ip = location.hostname;
	var puerto = location.port;
	var ipUrl = protocolo+"//"+ip+":"+puerto+"/ELE.web/servlet/ServletReportePlanchasEstado";
	window.open(ipUrl,"_blank","REPORTE","toolbar=yes,status=yes,scrollbars=yes,location=no,menubar=no,directories=no,width=850,height=600,left=50,top=0,resizable=1");
}

function ServletReporteDelegadosZona(){
	var protocolo = location.protocol;
	var ip = location.hostname;
	var puerto = location.port;
	var ipUrl = protocolo+"//"+ip+":"+puerto+"/ELE.web/servlet/ServletReporteDelegadosZona";
	window.open(ipUrl,"_blank","REPORTE","toolbar=yes,status=yes,scrollbars=yes,location=no,menubar=no,directories=no,width=850,height=600,left=50,top=0,resizable=1");
}

function servletReporteProcesoMarcacion(){
	var protocolo = location.protocol;
	var ip = location.hostname;
	var puerto = location.port;
	var ipUrl = protocolo+"//"+ip+":"+puerto+"/ELE.web/servlet/ServletReporteProcesoMarcacion";
	window.open(ipUrl,"_blank","REPORTE","toolbar=yes,status=yes,scrollbars=yes,location=no,menubar=no,directories=no,width=850,height=600,left=50,top=0,resizable=1");
}
/**
 * Permite descargar los reportes de Habilidades, Inhabilidades y novedades
 * formato excel y pdf
 */

function servletReportesJuego(){
	var protocolo = location.protocol;
	var ip = location.hostname;
	var puerto = location.port;
	var ipUrl = protocolo+"//"+ip+":"+puerto+"/ELE.web/servlet/ServletReportesJuego";
	window.open(ipUrl,"_blank","REPORTE","toolbar=yes,status=yes,scrollbars=yes,location=no,menubar=no,directories=no,width=850,height=600,left=50,top=0,resizable=1");
}

