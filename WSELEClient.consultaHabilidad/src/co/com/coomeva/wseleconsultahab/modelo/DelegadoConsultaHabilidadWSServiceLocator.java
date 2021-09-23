/**
 * DelegadoConsultaHabilidadWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package co.com.coomeva.wseleconsultahab.modelo;

import co.com.coomeva.util.acceso.UtilAcceso;

public class DelegadoConsultaHabilidadWSServiceLocator extends org.apache.axis.client.Service implements co.com.coomeva.wseleconsultahab.modelo.DelegadoConsultaHabilidadWSService {

    // Use to get a proxy class for consultarHabilidad
    private final String consultarHabilidad_address = UtilAcceso.getParametroFuenteS("parametros", "urlService");

    public java.lang.String getconsultarHabilidadAddress() {
        return consultarHabilidad_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String consultarHabilidadWSDDServiceName = "consultarHabilidad";

    public java.lang.String getconsultarHabilidadWSDDServiceName() {
        return consultarHabilidadWSDDServiceName;
    }

    public void setconsultarHabilidadWSDDServiceName(java.lang.String name) {
        consultarHabilidadWSDDServiceName = name;
    }

    public co.com.coomeva.wseleconsultahab.modelo.DelegadoConsultaHabilidadWS getconsultarHabilidad() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(consultarHabilidad_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getconsultarHabilidad(endpoint);
    }

    public co.com.coomeva.wseleconsultahab.modelo.DelegadoConsultaHabilidadWS getconsultarHabilidad(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            co.com.coomeva.wseleconsultahab.modelo.ConsultarHabilidadSoapBindingStub _stub = new co.com.coomeva.wseleconsultahab.modelo.ConsultarHabilidadSoapBindingStub(portAddress, this);
            _stub.setPortName(getconsultarHabilidadWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (co.com.coomeva.wseleconsultahab.modelo.DelegadoConsultaHabilidadWS.class.isAssignableFrom(serviceEndpointInterface)) {
                co.com.coomeva.wseleconsultahab.modelo.ConsultarHabilidadSoapBindingStub _stub = new co.com.coomeva.wseleconsultahab.modelo.ConsultarHabilidadSoapBindingStub(new java.net.URL(consultarHabilidad_address), this);
                _stub.setPortName(getconsultarHabilidadWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("consultarHabilidad".equals(inputPortName)) {
            return getconsultarHabilidad();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost:9092/WSELE.consultaHabilidad/services/consultarHabilidad", "DelegadoConsultaHabilidadWSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("consultarHabilidad"));
        }
        return ports.iterator();
    }

}
