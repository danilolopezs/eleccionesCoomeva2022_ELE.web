
package co.com.coomeva.wseleccionesintegracion.delegadoWS.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "consultarJuegoResponse", namespace = "http://delegadoWS.wseleccionesintegracion.coomeva.com.co/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarJuegoResponse", namespace = "http://delegadoWS.wseleccionesintegracion.coomeva.com.co/")
public class ConsultarJuegoResponse {

    @XmlElement(name = "return", namespace = "")
    private co.com.coomeva.wseleccionesintegracion.modelo.RespuestaWSI _return;

    /**
     * 
     * @return
     *     returns RespuestaWSI
     */
    public co.com.coomeva.wseleccionesintegracion.modelo.RespuestaWSI getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(co.com.coomeva.wseleccionesintegracion.modelo.RespuestaWSI _return) {
        this._return = _return;
    }

}
