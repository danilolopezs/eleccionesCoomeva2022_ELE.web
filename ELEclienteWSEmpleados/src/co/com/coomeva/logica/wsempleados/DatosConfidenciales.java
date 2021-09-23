/**
 * DatosConfidenciales.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package co.com.coomeva.logica.wsempleados;

public class DatosConfidenciales  implements java.io.Serializable {
    private java.lang.String direccion;
    private java.lang.String telefono;
    private java.lang.String celular;
    private java.lang.String cuentaConsignacionNomina;
    private java.lang.String salario;
    private java.lang.String cupoTotalBeneficios;
    private java.lang.String codEmpresa;
    private java.lang.String empresa;

    public DatosConfidenciales() {
    }

    public java.lang.String getDireccion() {
        return direccion;
    }

    public void setDireccion(java.lang.String direccion) {
        this.direccion = direccion;
    }

    public java.lang.String getTelefono() {
        return telefono;
    }

    public void setTelefono(java.lang.String telefono) {
        this.telefono = telefono;
    }

    public java.lang.String getCelular() {
        return celular;
    }

    public void setCelular(java.lang.String celular) {
        this.celular = celular;
    }

    public java.lang.String getCuentaConsignacionNomina() {
        return cuentaConsignacionNomina;
    }

    public void setCuentaConsignacionNomina(java.lang.String cuentaConsignacionNomina) {
        this.cuentaConsignacionNomina = cuentaConsignacionNomina;
    }

    public java.lang.String getSalario() {
        return salario;
    }

    public void setSalario(java.lang.String salario) {
        this.salario = salario;
    }

    public java.lang.String getCupoTotalBeneficios() {
        return cupoTotalBeneficios;
    }

    public void setCupoTotalBeneficios(java.lang.String cupoTotalBeneficios) {
        this.cupoTotalBeneficios = cupoTotalBeneficios;
    }

    public java.lang.String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(java.lang.String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public java.lang.String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(java.lang.String empresa) {
        this.empresa = empresa;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosConfidenciales)) return false;
        DatosConfidenciales other = (DatosConfidenciales) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.direccion==null && other.getDireccion()==null) || 
             (this.direccion!=null &&
              this.direccion.equals(other.getDireccion()))) &&
            ((this.telefono==null && other.getTelefono()==null) || 
             (this.telefono!=null &&
              this.telefono.equals(other.getTelefono()))) &&
            ((this.celular==null && other.getCelular()==null) || 
             (this.celular!=null &&
              this.celular.equals(other.getCelular()))) &&
            ((this.cuentaConsignacionNomina==null && other.getCuentaConsignacionNomina()==null) || 
             (this.cuentaConsignacionNomina!=null &&
              this.cuentaConsignacionNomina.equals(other.getCuentaConsignacionNomina()))) &&
            ((this.salario==null && other.getSalario()==null) || 
             (this.salario!=null &&
              this.salario.equals(other.getSalario()))) &&
            ((this.cupoTotalBeneficios==null && other.getCupoTotalBeneficios()==null) || 
             (this.cupoTotalBeneficios!=null &&
              this.cupoTotalBeneficios.equals(other.getCupoTotalBeneficios()))) &&
            ((this.codEmpresa==null && other.getCodEmpresa()==null) || 
             (this.codEmpresa!=null &&
              this.codEmpresa.equals(other.getCodEmpresa()))) &&
            ((this.empresa==null && other.getEmpresa()==null) || 
             (this.empresa!=null &&
              this.empresa.equals(other.getEmpresa())));
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
        if (getDireccion() != null) {
            _hashCode += getDireccion().hashCode();
        }
        if (getTelefono() != null) {
            _hashCode += getTelefono().hashCode();
        }
        if (getCelular() != null) {
            _hashCode += getCelular().hashCode();
        }
        if (getCuentaConsignacionNomina() != null) {
            _hashCode += getCuentaConsignacionNomina().hashCode();
        }
        if (getSalario() != null) {
            _hashCode += getSalario().hashCode();
        }
        if (getCupoTotalBeneficios() != null) {
            _hashCode += getCupoTotalBeneficios().hashCode();
        }
        if (getCodEmpresa() != null) {
            _hashCode += getCodEmpresa().hashCode();
        }
        if (getEmpresa() != null) {
            _hashCode += getEmpresa().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosConfidenciales.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "DatosConfidenciales"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("direccion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "direccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefono");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefono"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("celular");
        elemField.setXmlName(new javax.xml.namespace.QName("", "celular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cuentaConsignacionNomina");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cuentaConsignacionNomina"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("salario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "salario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cupoTotalBeneficios");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cupoTotalBeneficios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codEmpresa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codEmpresa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("empresa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "empresa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
