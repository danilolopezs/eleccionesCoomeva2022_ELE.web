/**
 * WSEmpleado_EmpleadoHttpServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package co.com.coomeva.logica.wsempleados;

import co.com.coomeva.util.acceso.UtilAcceso;

public class WSEmpleado_EmpleadoHttpServiceLocator extends org.apache.axis.client.Service implements co.com.coomeva.logica.wsempleados.WSEmpleado_EmpleadoHttpService {

    // Use to get a proxy class for WSEmpleado_EmpleadoHttpPort
    private final java.lang.String WSEmpleado_EmpleadoHttpPort_address = UtilAcceso.getParametroFuenteS("parametrosWsEmpleados", "urlService");

    public java.lang.String getWSEmpleado_EmpleadoHttpPortAddress() {
        return WSEmpleado_EmpleadoHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSEmpleado_EmpleadoHttpPortWSDDServiceName = "WSEmpleado_EmpleadoHttpPort";

    public java.lang.String getWSEmpleado_EmpleadoHttpPortWSDDServiceName() {
        return WSEmpleado_EmpleadoHttpPortWSDDServiceName;
    }

    public void setWSEmpleado_EmpleadoHttpPortWSDDServiceName(java.lang.String name) {
        WSEmpleado_EmpleadoHttpPortWSDDServiceName = name;
    }

    public co.com.coomeva.logica.wsempleados.Empleado getWSEmpleado_EmpleadoHttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSEmpleado_EmpleadoHttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSEmpleado_EmpleadoHttpPort(endpoint);
    }

    public co.com.coomeva.logica.wsempleados.Empleado getWSEmpleado_EmpleadoHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            co.com.coomeva.logica.wsempleados.WSEmpleado_EmpleadoHttpBindingStub _stub = new co.com.coomeva.logica.wsempleados.WSEmpleado_EmpleadoHttpBindingStub(portAddress, this);
            _stub.setPortName(getWSEmpleado_EmpleadoHttpPortWSDDServiceName());
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
            if (co.com.coomeva.logica.wsempleados.Empleado.class.isAssignableFrom(serviceEndpointInterface)) {
                co.com.coomeva.logica.wsempleados.WSEmpleado_EmpleadoHttpBindingStub _stub = new co.com.coomeva.logica.wsempleados.WSEmpleado_EmpleadoHttpBindingStub(new java.net.URL(WSEmpleado_EmpleadoHttpPort_address), this);
                _stub.setPortName(getWSEmpleado_EmpleadoHttpPortWSDDServiceName());
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
        if ("WSEmpleado_EmpleadoHttpPort".equals(inputPortName)) {
            return getWSEmpleado_EmpleadoHttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado/Binding", "WS-Empleado_EmpleadoHttpService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("WSEmpleado_EmpleadoHttpPort"));
        }
        return ports.iterator();
    }

}
