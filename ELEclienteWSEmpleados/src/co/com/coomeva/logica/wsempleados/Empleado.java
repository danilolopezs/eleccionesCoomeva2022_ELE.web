/**
 * Empleado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package co.com.coomeva.logica.wsempleados;

public interface Empleado extends java.rmi.Remote {

    // consulta los datos basicos del empleado
    public co.com.coomeva.logica.wsempleados.ConsultaDatosBasicosResponse consultaDatosBasicos(co.com.coomeva.logica.wsempleados.ConsultaDatosBasicosRequest consultaDatosBasicosRequest) throws java.rmi.RemoteException;

    // consulta los datos del empleado relacionados con el empleador
    public co.com.coomeva.logica.wsempleados.ConsultaDatosEmpleadorResponse consultaDatosEmpleador(co.com.coomeva.logica.wsempleados.ConsultaDatosEmpleadorRequest consultaDatosEmpleadorRequest) throws java.rmi.RemoteException;

    // consulta los datos confidenciales del empleado
    public co.com.coomeva.logica.wsempleados.ConsultaDatosConfidencialesResponse consultaDatosConfidenciales(co.com.coomeva.logica.wsempleados.ConsultaDatosConfidencialesRequest consultaDatosConfidencialesRequest) throws java.rmi.RemoteException;

    // consulta todos los datos del empleado
    public co.com.coomeva.logica.wsempleados.ConsultaDetalladaResponse consultaDetallada(co.com.coomeva.logica.wsempleados.ConsultaDetalladaRequest consultaDetalladaRequest) throws java.rmi.RemoteException;
}
