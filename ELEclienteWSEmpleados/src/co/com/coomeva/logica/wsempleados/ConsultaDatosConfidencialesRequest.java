/**
 * ConsultaDatosConfidencialesRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package co.com.coomeva.logica.wsempleados;

public class ConsultaDatosConfidencialesRequest  implements java.io.Serializable {
    private java.lang.String aplicativoOrigen;
    private java.lang.String cedulaEmpleado;

    public ConsultaDatosConfidencialesRequest() {
    }

    public java.lang.String getAplicativoOrigen() {
        return aplicativoOrigen;
    }

    public void setAplicativoOrigen(java.lang.String aplicativoOrigen) {
        this.aplicativoOrigen = aplicativoOrigen;
    }

    public java.lang.String getCedulaEmpleado() {
        return cedulaEmpleado;
    }

    public void setCedulaEmpleado(java.lang.String cedulaEmpleado) {
        this.cedulaEmpleado = cedulaEmpleado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaDatosConfidencialesRequest)) return false;
        ConsultaDatosConfidencialesRequest other = (ConsultaDatosConfidencialesRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.aplicativoOrigen==null && other.getAplicativoOrigen()==null) || 
             (this.aplicativoOrigen!=null &&
              this.aplicativoOrigen.equals(other.getAplicativoOrigen()))) &&
            ((this.cedulaEmpleado==null && other.getCedulaEmpleado()==null) || 
             (this.cedulaEmpleado!=null &&
              this.cedulaEmpleado.equals(other.getCedulaEmpleado())));
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
        if (getAplicativoOrigen() != null) {
            _hashCode += getAplicativoOrigen().hashCode();
        }
        if (getCedulaEmpleado() != null) {
            _hashCode += getCedulaEmpleado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaDatosConfidencialesRequest.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDatosConfidencialesRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aplicativoOrigen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aplicativoOrigen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cedulaEmpleado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cedulaEmpleado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
