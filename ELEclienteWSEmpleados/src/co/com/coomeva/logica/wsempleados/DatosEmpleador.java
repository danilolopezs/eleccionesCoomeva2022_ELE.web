/**
 * DatosEmpleador.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package co.com.coomeva.logica.wsempleados;

public class DatosEmpleador  implements java.io.Serializable {
    private java.lang.String codCentroCosto;
    private java.lang.String centrosCosto;
    private java.util.Date fechaIngresoEmpresa;
    private java.util.Date fechaInicioContrato;
    private java.util.Date fechaFinContrato;
    private java.lang.String cedulaJefe;
    private java.lang.String cedulaJefeCC;
    private java.lang.String categoriaViajerc;
    private java.lang.String salarioMLV;
    private java.lang.String cedulaEnteAprobador;
    private java.lang.String cedulaSuplenteAprobador;
    private java.lang.String cedulaSecretariaAprobador;
    private java.lang.String cedulaTurismo;
    private java.lang.String vivienda;
    private java.lang.String codigoAgencia;
    private java.lang.String nombreAgencia;
    private java.lang.String codEmpresa;
    private java.lang.String empresa;

    public DatosEmpleador() {
    }

    public java.lang.String getCodCentroCosto() {
        return codCentroCosto;
    }

    public void setCodCentroCosto(java.lang.String codCentroCosto) {
        this.codCentroCosto = codCentroCosto;
    }

    public java.lang.String getCentrosCosto() {
        return centrosCosto;
    }

    public void setCentrosCosto(java.lang.String centrosCosto) {
        this.centrosCosto = centrosCosto;
    }

    public java.util.Date getFechaIngresoEmpresa() {
        return fechaIngresoEmpresa;
    }

    public void setFechaIngresoEmpresa(java.util.Date fechaIngresoEmpresa) {
        this.fechaIngresoEmpresa = fechaIngresoEmpresa;
    }

    public java.util.Date getFechaInicioContrato() {
        return fechaInicioContrato;
    }

    public void setFechaInicioContrato(java.util.Date fechaInicioContrato) {
        this.fechaInicioContrato = fechaInicioContrato;
    }

    public java.util.Date getFechaFinContrato() {
        return fechaFinContrato;
    }

    public void setFechaFinContrato(java.util.Date fechaFinContrato) {
        this.fechaFinContrato = fechaFinContrato;
    }

    public java.lang.String getCedulaJefe() {
        return cedulaJefe;
    }

    public void setCedulaJefe(java.lang.String cedulaJefe) {
        this.cedulaJefe = cedulaJefe;
    }

    public java.lang.String getCedulaJefeCC() {
        return cedulaJefeCC;
    }

    public void setCedulaJefeCC(java.lang.String cedulaJefeCC) {
        this.cedulaJefeCC = cedulaJefeCC;
    }

    public java.lang.String getCategoriaViajerc() {
        return categoriaViajerc;
    }

    public void setCategoriaViajerc(java.lang.String categoriaViajerc) {
        this.categoriaViajerc = categoriaViajerc;
    }

    public java.lang.String getSalarioMLV() {
        return salarioMLV;
    }

    public void setSalarioMLV(java.lang.String salarioMLV) {
        this.salarioMLV = salarioMLV;
    }

    public java.lang.String getCedulaEnteAprobador() {
        return cedulaEnteAprobador;
    }

    public void setCedulaEnteAprobador(java.lang.String cedulaEnteAprobador) {
        this.cedulaEnteAprobador = cedulaEnteAprobador;
    }

    public java.lang.String getCedulaSuplenteAprobador() {
        return cedulaSuplenteAprobador;
    }

    public void setCedulaSuplenteAprobador(java.lang.String cedulaSuplenteAprobador) {
        this.cedulaSuplenteAprobador = cedulaSuplenteAprobador;
    }

    public java.lang.String getCedulaSecretariaAprobador() {
        return cedulaSecretariaAprobador;
    }

    public void setCedulaSecretariaAprobador(java.lang.String cedulaSecretariaAprobador) {
        this.cedulaSecretariaAprobador = cedulaSecretariaAprobador;
    }

    public java.lang.String getCedulaTurismo() {
        return cedulaTurismo;
    }

    public void setCedulaTurismo(java.lang.String cedulaTurismo) {
        this.cedulaTurismo = cedulaTurismo;
    }

    public java.lang.String getVivienda() {
        return vivienda;
    }

    public void setVivienda(java.lang.String vivienda) {
        this.vivienda = vivienda;
    }

    public java.lang.String getCodigoAgencia() {
        return codigoAgencia;
    }

    public void setCodigoAgencia(java.lang.String codigoAgencia) {
        this.codigoAgencia = codigoAgencia;
    }

    public java.lang.String getNombreAgencia() {
        return nombreAgencia;
    }

    public void setNombreAgencia(java.lang.String nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
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
        if (!(obj instanceof DatosEmpleador)) return false;
        DatosEmpleador other = (DatosEmpleador) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codCentroCosto==null && other.getCodCentroCosto()==null) || 
             (this.codCentroCosto!=null &&
              this.codCentroCosto.equals(other.getCodCentroCosto()))) &&
            ((this.centrosCosto==null && other.getCentrosCosto()==null) || 
             (this.centrosCosto!=null &&
              this.centrosCosto.equals(other.getCentrosCosto()))) &&
            ((this.fechaIngresoEmpresa==null && other.getFechaIngresoEmpresa()==null) || 
             (this.fechaIngresoEmpresa!=null &&
              this.fechaIngresoEmpresa.equals(other.getFechaIngresoEmpresa()))) &&
            ((this.fechaInicioContrato==null && other.getFechaInicioContrato()==null) || 
             (this.fechaInicioContrato!=null &&
              this.fechaInicioContrato.equals(other.getFechaInicioContrato()))) &&
            ((this.fechaFinContrato==null && other.getFechaFinContrato()==null) || 
             (this.fechaFinContrato!=null &&
              this.fechaFinContrato.equals(other.getFechaFinContrato()))) &&
            ((this.cedulaJefe==null && other.getCedulaJefe()==null) || 
             (this.cedulaJefe!=null &&
              this.cedulaJefe.equals(other.getCedulaJefe()))) &&
            ((this.cedulaJefeCC==null && other.getCedulaJefeCC()==null) || 
             (this.cedulaJefeCC!=null &&
              this.cedulaJefeCC.equals(other.getCedulaJefeCC()))) &&
            ((this.categoriaViajerc==null && other.getCategoriaViajerc()==null) || 
             (this.categoriaViajerc!=null &&
              this.categoriaViajerc.equals(other.getCategoriaViajerc()))) &&
            ((this.salarioMLV==null && other.getSalarioMLV()==null) || 
             (this.salarioMLV!=null &&
              this.salarioMLV.equals(other.getSalarioMLV()))) &&
            ((this.cedulaEnteAprobador==null && other.getCedulaEnteAprobador()==null) || 
             (this.cedulaEnteAprobador!=null &&
              this.cedulaEnteAprobador.equals(other.getCedulaEnteAprobador()))) &&
            ((this.cedulaSuplenteAprobador==null && other.getCedulaSuplenteAprobador()==null) || 
             (this.cedulaSuplenteAprobador!=null &&
              this.cedulaSuplenteAprobador.equals(other.getCedulaSuplenteAprobador()))) &&
            ((this.cedulaSecretariaAprobador==null && other.getCedulaSecretariaAprobador()==null) || 
             (this.cedulaSecretariaAprobador!=null &&
              this.cedulaSecretariaAprobador.equals(other.getCedulaSecretariaAprobador()))) &&
            ((this.cedulaTurismo==null && other.getCedulaTurismo()==null) || 
             (this.cedulaTurismo!=null &&
              this.cedulaTurismo.equals(other.getCedulaTurismo()))) &&
            ((this.vivienda==null && other.getVivienda()==null) || 
             (this.vivienda!=null &&
              this.vivienda.equals(other.getVivienda()))) &&
            ((this.codigoAgencia==null && other.getCodigoAgencia()==null) || 
             (this.codigoAgencia!=null &&
              this.codigoAgencia.equals(other.getCodigoAgencia()))) &&
            ((this.nombreAgencia==null && other.getNombreAgencia()==null) || 
             (this.nombreAgencia!=null &&
              this.nombreAgencia.equals(other.getNombreAgencia()))) &&
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
        if (getCodCentroCosto() != null) {
            _hashCode += getCodCentroCosto().hashCode();
        }
        if (getCentrosCosto() != null) {
            _hashCode += getCentrosCosto().hashCode();
        }
        if (getFechaIngresoEmpresa() != null) {
            _hashCode += getFechaIngresoEmpresa().hashCode();
        }
        if (getFechaInicioContrato() != null) {
            _hashCode += getFechaInicioContrato().hashCode();
        }
        if (getFechaFinContrato() != null) {
            _hashCode += getFechaFinContrato().hashCode();
        }
        if (getCedulaJefe() != null) {
            _hashCode += getCedulaJefe().hashCode();
        }
        if (getCedulaJefeCC() != null) {
            _hashCode += getCedulaJefeCC().hashCode();
        }
        if (getCategoriaViajerc() != null) {
            _hashCode += getCategoriaViajerc().hashCode();
        }
        if (getSalarioMLV() != null) {
            _hashCode += getSalarioMLV().hashCode();
        }
        if (getCedulaEnteAprobador() != null) {
            _hashCode += getCedulaEnteAprobador().hashCode();
        }
        if (getCedulaSuplenteAprobador() != null) {
            _hashCode += getCedulaSuplenteAprobador().hashCode();
        }
        if (getCedulaSecretariaAprobador() != null) {
            _hashCode += getCedulaSecretariaAprobador().hashCode();
        }
        if (getCedulaTurismo() != null) {
            _hashCode += getCedulaTurismo().hashCode();
        }
        if (getVivienda() != null) {
            _hashCode += getVivienda().hashCode();
        }
        if (getCodigoAgencia() != null) {
            _hashCode += getCodigoAgencia().hashCode();
        }
        if (getNombreAgencia() != null) {
            _hashCode += getNombreAgencia().hashCode();
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
        new org.apache.axis.description.TypeDesc(DatosEmpleador.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "DatosEmpleador"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codCentroCosto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codCentroCosto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("centrosCosto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "centrosCosto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaIngresoEmpresa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaIngresoEmpresa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaInicioContrato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaInicioContrato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaFinContrato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaFinContrato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cedulaJefe");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cedulaJefe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cedulaJefeCC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cedulaJefeCC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoriaViajerc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "categoriaViajerc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("salarioMLV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "salarioMLV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cedulaEnteAprobador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cedulaEnteAprobador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cedulaSuplenteAprobador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cedulaSuplenteAprobador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cedulaSecretariaAprobador");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cedulaSecretariaAprobador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cedulaTurismo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cedulaTurismo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vivienda");
        elemField.setXmlName(new javax.xml.namespace.QName("", "vivienda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoAgencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoAgencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreAgencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombreAgencia"));
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
