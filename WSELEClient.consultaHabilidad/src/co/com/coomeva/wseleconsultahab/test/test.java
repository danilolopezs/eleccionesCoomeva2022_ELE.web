package co.com.coomeva.wseleconsultahab.test;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import co.com.coomeva.wseleconsultahab.modelo.DelegadoConsultaHabilidadWS;
import co.com.coomeva.wseleconsultahab.modelo.DelegadoConsultaHabilidadWSServiceLocator;
import co.com.coomeva.wseleconsultahab.modelo.RespuestaWS;

public class test {

	public static void main(String[] args) {
		try {
			//94450194
			DelegadoConsultaHabilidadWSServiceLocator locator  = new DelegadoConsultaHabilidadWSServiceLocator();
			DelegadoConsultaHabilidadWS habilidadWS = locator.getconsultarHabilidad();
//			RespuestaWS respuestaWS = habilidadWS.obtenerMedioVotacion(1l);
//			if (respuestaWS.getCodRespuesta().equalsIgnoreCase("0")) {
//				throw new Exception(respuestaWS.getDescRespuesta());
//			}
//			
//			System.out.println(respuestaWS.getDescRespuesta());
//			
//			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
