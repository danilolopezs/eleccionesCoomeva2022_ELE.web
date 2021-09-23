/**
 * ConsultaDetalladaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package co.com.coomeva.logica.wsempleados;

public class ConsultaDetalladaResponse  implements java.io.Serializable {
    private co.com.coomeva.logica.wsempleados.EmpleadoDetalles empleado;
    private co.com.coomeva.logica.wsempleados.Error error;

    public ConsultaDetalladaResponse() {
    }

    public co.com.coomeva.logica.wsempleados.EmpleadoDetalles getEmpleado() {
        return empleado;
    }

    public void setEmpleado(co.com.coomeva.logica.wsempleados.EmpleadoDetalles empleado) {
        this.empleado = empleado;
    }

    public co.com.coomeva.logica.wsempleados.Error getError() {
        return error;
    }

    public void setError(co.com.coomeva.logica.wsempleados.Error error) {
        this.error = error;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaDetalladaResponse)) return false;
        ConsultaDetalladaResponse other = (ConsultaDetalladaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.empleado==null && other.getEmpleado()==null) || 
             (this.empleado!=null &&
              this.empleado.equals(other.getEmpleado()))) &&
            ((this.error==null && other.getError()==null) || 
             (this.error!=null &&
              this.error.equals(other.getError())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getEmpleado() != null) {
            _hashCode += getEmpleado().hashCode();
        }
        if (getError() != null) {
            _hashCode += getError().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaDetalladaResponse.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDetalladaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("empleado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Empleado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "EmpleadoDetalles"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("error");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Error"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "Error"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
