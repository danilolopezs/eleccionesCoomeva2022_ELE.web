/**
 * RespuestaWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package co.com.coomeva.wseleconsultahab.modelo;

public class RespuestaWS  implements java.io.Serializable {
    private java.lang.String codHabilidad;
    private java.lang.String codRespuesta;
    private java.lang.String codVotacion;
    private java.lang.String descHabilidad;
    private java.lang.String descRespuesta;
    private java.lang.String descVotacion;
    private java.lang.String[] inhabilidades;
    
    

    public RespuestaWS() {
    }

    public java.lang.String getCodHabilidad() {
        return codHabilidad;
    }

    public void setCodHabilidad(java.lang.String codHabilidad) {
        this.codHabilidad = codHabilidad;
    }

    public java.lang.String getCodRespuesta() {
        return codRespuesta;
    }

    public void setCodRespuesta(java.lang.String codRespuesta) {
        this.codRespuesta = codRespuesta;
    }

    public java.lang.String getCodVotacion() {
        return codVotacion;
    }

    public void setCodVotacion(java.lang.String codVotacion) {
        this.codVotacion = codVotacion;
    }

    public java.lang.String getDescHabilidad() {
        return descHabilidad;
    }

    public void setDescHabilidad(java.lang.String descHabilidad) {
        this.descHabilidad = descHabilidad;
    }

    public java.lang.String getDescRespuesta() {
        return descRespuesta;
    }

    public void setDescRespuesta(java.lang.String descRespuesta) {
        this.descRespuesta = descRespuesta;
    }

    public java.lang.String getDescVotacion() {
        return descVotacion;
    }

    public void setDescVotacion(java.lang.String descVotacion) {
        this.descVotacion = descVotacion;
    }

    public java.lang.String[] getInhabilidades() {
        return inhabilidades;
    }

    public void setInhabilidades(java.lang.String[] inhabilidades) {
        this.inhabilidades = inhabilidades;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespuestaWS)) return false;
        RespuestaWS other = (RespuestaWS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codHabilidad==null && other.getCodHabilidad()==null) || 
             (this.codHabilidad!=null &&
              this.codHabilidad.equals(other.getCodHabilidad()))) &&
            ((this.codRespuesta==null && other.getCodRespuesta()==null) || 
             (this.codRespuesta!=null &&
              this.codRespuesta.equals(other.getCodRespuesta()))) &&
            ((this.codVotacion==null && other.getCodVotacion()==null) || 
             (this.codVotacion!=null &&
              this.codVotacion.equals(other.getCodVotacion()))) &&
            ((this.descHabilidad==null && other.getDescHabilidad()==null) || 
             (this.descHabilidad!=null &&
              this.descHabilidad.equals(other.getDescHabilidad()))) &&
            ((this.descRespuesta==null && other.getDescRespuesta()==null) || 
             (this.descRespuesta!=null &&
              this.descRespuesta.equals(other.getDescRespuesta()))) &&
            ((this.descVotacion==null && other.getDescVotacion()==null) || 
             (this.descVotacion!=null &&
              this.descVotacion.equals(other.getDescVotacion()))) &&
            ((this.inhabilidades==null && other.getInhabilidades()==null) || 
             (this.inhabilidades!=null &&
              java.util.Arrays.equals(this.inhabilidades, other.getInhabilidades())));
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
        if (getCodHabilidad() != null) {
            _hashCode += getCodHabilidad().hashCode();
        }
        if (getCodRespuesta() != null) {
            _hashCode += getCodRespuesta().hashCode();
        }
        if (getCodVotacion() != null) {
            _hashCode += getCodVotacion().hashCode();
        }
        if (getDescHabilidad() != null) {
            _hashCode += getDescHabilidad().hashCode();
        }
        if (getDescRespuesta() != null) {
            _hashCode += getDescRespuesta().hashCode();
        }
        if (getDescVotacion() != null) {
            _hashCode += getDescVotacion().hashCode();
        }
        if (getInhabilidades() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getInhabilidades());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getInhabilidades(), i);
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
        new org.apache.axis.description.TypeDesc(RespuestaWS.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("co.com.coomeva.wseleconsultahab.modelo", "RespuestaWS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codHabilidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codHabilidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codRespuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codRespuesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codVotacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codVotacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descHabilidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descHabilidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descRespuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descRespuesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descVotacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descVotacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inhabilidades");
        elemField.setXmlName(new javax.xml.namespace.QName("", "inhabilidades"));
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
