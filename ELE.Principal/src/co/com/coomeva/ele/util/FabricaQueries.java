package co.com.coomeva.ele.util;

/**
 * Fabrica para obtener los queries de los reportes y consultas de las pantallas.
 * 
 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
 * @project ELE.Principal
 * @class FabricaQueries
 * @date 7/11/2012
 *
 */
public class FabricaQueries {
	
	private static FabricaQueries instance = null;
	
	private FabricaQueries (){}
	
	public static FabricaQueries getInstance(){
		if (instance == null) {
            instance = new FabricaQueries();
        }
        return instance;
	}
	
	/**
	 * Consulta asociados habiles
	 * 
	 * @author <a href="mailto:bernando.lopez@pragma.com.co">Bernardo López</a> - Pragma S.A. <br>
	 * @date 7/11/2012
	 * @param codZona
	 * @param codCiudad
	 * @return
	 */
	public StringBuffer queryAsociadosHabiles(Long codZona, Long codCiudad){
		StringBuffer query = new StringBuffer("SELECT DISTINCT A.NUMERO_DOCUMENTO AS NUMINT, C.NOMCLI,");
		query.append("(SELECT CCIU.NOMCIU FROM MULCLIDAT.CLIDIR ACD , MULCLIDAT.CLICIUDAD CCIU WHERE ACD. TIPUBI = 'RE' AND ACD.NUMINT = A.CODIGO_ASOCIADO AND ACD.CODCIU = CCIU.CODCIU ) CIUDAD,");
		query.append("db2util.get_fecha_iso(C.FECCON) FECHA_NACIMIENTO, db2util.get_fecha_iso(C.FECING) FECHA_VINCULACION,");
		query.append("db2util.get_codnom_clitab(42, C.CODSEX) GENERO, DB2UTIL.SIP_PROFESION(C.NUMINT,'AS') PROFESION,");
		query.append("DB2UTIL.SIP_DIR(C.NUMINT,char('RE')) DIRECCION, DB2UTIL.SIP_DIR(C.NUMINT,char('DIN')) EMAIL,");
		query.append("DB2UTIL.SIP_DIR(C.NUMINT,char('TEL1')) TELEFONO, DB2UTIL.SIP_DIR(C.NUMINT,char('CEL')) CELULAR,");
		query.append("(SELECT CTAB . CODNOM FROM SEGURIDAD . PLTAGCORI PLT , MULCLIDAT . CLITAB CTAB");
		query.append("WHERE PLT . CODEMP = 67890 AND CTAB . CODTAB = 908 AND PLT . AGCORI= C.AGCVIN AND PLT . CODZON = CTAB.CODINT) ZONA,");
		query.append("C.ESTRAT, (SELECT (CASE WHEN A2.CODIGO_ASOCIADO IS NULL THEN '0' ELSE '1' END) HABILIDAD_ASOCIADO");
		query.append("FROM ELECDB.ELE_ASOCIADO A2 WHERE A2.NUMERO_DOCUMENTO = A2.CODIGO_ASOCIADO");
		query.append("AND (A2.CODIGO_ASOCIADO NOT IN (SELECT I.CODIGO_ASOCIADO FROM ELECDB.ELE_INHABILIDAD I");
		query.append("WHERE I.CODIGO_ASOCIADO = A2.CODIGO_ASOCIADO) OR (A2.CODIGO_ASOCIADO IN ");
		query.append("(SELECT I.CODIGO_ASOCIADO FROM ELECDB.ELE_INHABILIDAD I WHERE I.CODIGO_ASOCIADO = A2.CODIGO_ASOCIADO)");
		query.append("AND (SELECT N.ESTADO_HABILIDAD FROM ELECDB.ELE_NOVEDAD N WHERE N.CONSECUTIVO_NOVEDAD = ");
		query.append("(SELECT MAX(N2.CONSECUTIVO_NOVEDAD) FROM ELECDB.ELE_NOVEDAD N2 WHERE N2.CODIGO_ASOCIADO = A2.CODIGO_ASOCIADO)) = '1'))) HABILIDAD_ASOCIADO,");
		query.append("(SELECT NOMAGC FROM SEGURIDAD . PLTAGCORI WHERE AGCORI = C.AGCVIN AND CODEMP = 67890 ) OFICINA,");
		query.append("(SELECT CTAB . CODNOM FROM SEGURIDAD . PLTAGCORI PLT , MULCLIDAT . CLITAB CTAB WHERE PLT . CODEMP = 67890");
		query.append("AND CTAB . CODTAB = 907 AND PLT . AGCORI = C.AGCVIN AND PLT . CODREG = CTAB . CODINT) REGIONAL");
		query.append("FROM ELECDB.ELE_ASOCIADO A INNER JOIN MULCLIDAT.CLIMAE C ON C.NUMINT = A.CODIGO_ASOCIADO, ELECDB.ELE_NOVEDAD N4, SEGURIDAD.PLTAGCORI PLT");
		query.append("WHERE (A.CODIGO_ASOCIADO NOT IN(SELECT I.CODIGO_ASOCIADO FROM ELECDB.ELE_INHABILIDAD I WHERE I.CODIGO_ASOCIADO = A.CODIGO_ASOCIADO)");
		query.append("OR (A.CODIGO_ASOCIADO IN (SELECT I.CODIGO_ASOCIADO FROM ELECDB.ELE_INHABILIDAD I WHERE I.CODIGO_ASOCIADO = A.CODIGO_ASOCIADO)");
		query.append("AND N4.CODIGO_ASOCIADO = A.CODIGO_ASOCIADO AND N4.ESTADO_HABILIDAD = '1' AND N4.CONSECUTIVO_NOVEDAD = ");
		query.append("(SELECT MAX(N3.CONSECUTIVO_NOVEDAD) FROM ELECDB.ELE_NOVEDAD N3 WHERE N3.CODIGO_ASOCIADO = A.CODIGO_ASOCIADO)))");
		
		if (codZona != null) {
			query.append("AND (SELECT PLT.CODZON FROM SEGURIDAD.PLTAGCORI PLT WHERE PLT.CODEMP = 67890 AND PLT.AGCORI = C.AGCVIN) = "+codZona);
		}
		
		if (codCiudad != null) {
			query.append("AND (SELECT A3.CODCIU FROM MULCLIDAT.CLIDIR A3 WHERE A3.NUMINT = A.CODIGO_ASOCIADO AND A3.TIPUBI = 'RE') = "+codCiudad);
		}
		
		query.append("ORDER BY A.NUMERO_DOCUMENTO, REGIONAL, ZONA, OFICINA");
		
		return query;
	}

}
