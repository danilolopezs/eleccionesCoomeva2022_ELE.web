/**
 * EmpleadoDatosBasicos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package co.com.coomeva.logica.wsempleados;

public class EmpleadoDatosBasicos  implements java.io.Serializable {
    private co.com.coomeva.logica.wsempleados.DatosBasicos[] registro;

    public EmpleadoDatosBasicos() {
    }

    public co.com.coomeva.logica.wsempleados.DatosBasicos[] getRegistro() {
        return registro;
    }

    public void setRegistro(co.com.coomeva.logica.wsempleados.DatosBasicos[] registro) {
        this.registro = registro;
    }

    public co.com.coomeva.logica.wsempleados.DatosBasicos getRegistro(int i) {
        return registro[i];
    }

    public void setRegistro(int i, co.com.coomeva.logica.wsempleados.DatosBasicos value) {
        this.registro[i] = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EmpleadoDatosBasicos)) return false;
        EmpleadoDatosBasicos other = (EmpleadoDatosBasicos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.registro==null && other.getRegistro()==null) || 
             (this.registro!=null &&
              java.util.Arrays.equals(this.registro, other.getRegistro())));
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
        if (getRegistro() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRegistro());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRegistro(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EmpleadoDatosBasicos.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "EmpleadoDatosBasicos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Registro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "DatosBasicos"));
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
