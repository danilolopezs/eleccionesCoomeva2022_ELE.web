/**
 * WSEmpleado_EmpleadoHttpBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package co.com.coomeva.logica.wsempleados;

public class WSEmpleado_EmpleadoHttpBindingStub extends org.apache.axis.client.Stub implements co.com.coomeva.logica.wsempleados.Empleado {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[4];
        org.apache.axis.description.OperationDesc oper;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultaDatosBasicos");
        oper.addParameter(new javax.xml.namespace.QName("", "ConsultaDatosBasicosRequest"), new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDatosBasicosRequest"), co.com.coomeva.logica.wsempleados.ConsultaDatosBasicosRequest.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDatosBasicosResponse"));
        oper.setReturnClass(co.com.coomeva.logica.wsempleados.ConsultaDatosBasicosResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ConsultaDatosBasicosResponse"));
//        oper.setStyle(org.apache.axis.enum.Style.WRAPPED);
//        oper.setUse(org.apache.axis.enum.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultaDatosEmpleador");
        oper.addParameter(new javax.xml.namespace.QName("", "ConsultaDatosEmpleadorRequest"), new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDatosEmpleadorRequest"), co.com.coomeva.logica.wsempleados.ConsultaDatosEmpleadorRequest.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDatosEmpleadorResponse"));
        oper.setReturnClass(co.com.coomeva.logica.wsempleados.ConsultaDatosEmpleadorResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ConsultaDatosEmpleadorResponse"));
//        oper.setStyle(org.apache.axis.enum.Style.WRAPPED);
//        oper.setUse(org.apache.axis.enum.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultaDatosConfidenciales");
        oper.addParameter(new javax.xml.namespace.QName("", "ConsultaDatosConfidencialesRequest"), new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDatosConfidencialesRequest"), co.com.coomeva.logica.wsempleados.ConsultaDatosConfidencialesRequest.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDatosConfidencialesResponse"));
        oper.setReturnClass(co.com.coomeva.logica.wsempleados.ConsultaDatosConfidencialesResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ConsultaDatosConfidencialesResponse"));
//        oper.setStyle(org.apache.axis.enum.Style.WRAPPED);
//        oper.setUse(org.apache.axis.enum.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConsultaDetallada");
        oper.addParameter(new javax.xml.namespace.QName("", "ConsultaDetalladaRequest"), new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDetalladaRequest"), co.com.coomeva.logica.wsempleados.ConsultaDetalladaRequest.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDetalladaResponse"));
        oper.setReturnClass(co.com.coomeva.logica.wsempleados.ConsultaDetalladaResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ConsultaDetalladaResponse"));
//        oper.setStyle(org.apache.axis.enum.Style.WRAPPED);
//        oper.setUse(org.apache.axis.enum.Use.LITERAL);
        _operations[3] = oper;

    }

    public WSEmpleado_EmpleadoHttpBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public WSEmpleado_EmpleadoHttpBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public WSEmpleado_EmpleadoHttpBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDatosBasicosRequest");
            cachedSerQNames.add(qName);
            cls = co.com.coomeva.logica.wsempleados.ConsultaDatosBasicosRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDatosConfidencialesRequest");
            cachedSerQNames.add(qName);
            cls = co.com.coomeva.logica.wsempleados.ConsultaDatosConfidencialesRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDatosBasicosResponse");
            cachedSerQNames.add(qName);
            cls = co.com.coomeva.logica.wsempleados.ConsultaDatosBasicosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDetalladaResponse");
            cachedSerQNames.add(qName);
            cls = co.com.coomeva.logica.wsempleados.ConsultaDetalladaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "DatosConfidenciales");
            cachedSerQNames.add(qName);
            cls = co.com.coomeva.logica.wsempleados.DatosConfidenciales.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "EmpleadoDetalles");
            cachedSerQNames.add(qName);
            cls = co.com.coomeva.logica.wsempleados.EmpleadoDetalles.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "EmpleadoDatosEmpleador");
            cachedSerQNames.add(qName);
            cls = co.com.coomeva.logica.wsempleados.EmpleadoDatosEmpleador.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDetalladaRequest");
            cachedSerQNames.add(qName);
            cls = co.com.coomeva.logica.wsempleados.ConsultaDetalladaRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "DatosEmpleador");
            cachedSerQNames.add(qName);
            cls = co.com.coomeva.logica.wsempleados.DatosEmpleador.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDatosEmpleadorRequest");
            cachedSerQNames.add(qName);
            cls = co.com.coomeva.logica.wsempleados.ConsultaDatosEmpleadorRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "DatosBasicos");
            cachedSerQNames.add(qName);
            cls = co.com.coomeva.logica.wsempleados.DatosBasicos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "Detalles");
            cachedSerQNames.add(qName);
            cls = co.com.coomeva.logica.wsempleados.Detalles.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDatosConfidencialesResponse");
            cachedSerQNames.add(qName);
            cls = co.com.coomeva.logica.wsempleados.ConsultaDatosConfidencialesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "EmpleadoDatosConfidenciales");
            cachedSerQNames.add(qName);
            cls = co.com.coomeva.logica.wsempleados.EmpleadoDatosConfidenciales.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "EmpleadoDatosBasicos");
            cachedSerQNames.add(qName);
            cls = co.com.coomeva.logica.wsempleados.EmpleadoDatosBasicos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDatosEmpleadorResponse");
            cachedSerQNames.add(qName);
            cls = co.com.coomeva.logica.wsempleados.ConsultaDatosEmpleadorResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "Error");
            cachedSerQNames.add(qName);
            cls = co.com.coomeva.logica.wsempleados.Error.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    private org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call =
                    (org.apache.axis.client.Call) super.service.createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                        java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                        _call.registerTypeMapping(cls, qName, sf, df, false);
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", t);
        }
    }

    public co.com.coomeva.logica.wsempleados.ConsultaDatosBasicosResponse consultaDatosBasicos(co.com.coomeva.logica.wsempleados.ConsultaDatosBasicosRequest consultaDatosBasicosRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDatosBasicos"));

        setRequestHeaders(_call);
        setAttachments(_call);
        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {consultaDatosBasicosRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.coomeva.logica.wsempleados.ConsultaDatosBasicosResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.coomeva.logica.wsempleados.ConsultaDatosBasicosResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.coomeva.logica.wsempleados.ConsultaDatosBasicosResponse.class);
            }
        }
    }

    public co.com.coomeva.logica.wsempleados.ConsultaDatosEmpleadorResponse consultaDatosEmpleador(co.com.coomeva.logica.wsempleados.ConsultaDatosEmpleadorRequest consultaDatosEmpleadorRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDatosEmpleador"));

        setRequestHeaders(_call);
        setAttachments(_call);
        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {consultaDatosEmpleadorRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.coomeva.logica.wsempleados.ConsultaDatosEmpleadorResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.coomeva.logica.wsempleados.ConsultaDatosEmpleadorResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.coomeva.logica.wsempleados.ConsultaDatosEmpleadorResponse.class);
            }
        }
    }

    public co.com.coomeva.logica.wsempleados.ConsultaDatosConfidencialesResponse consultaDatosConfidenciales(co.com.coomeva.logica.wsempleados.ConsultaDatosConfidencialesRequest consultaDatosConfidencialesRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDatosConfidenciales"));

        setRequestHeaders(_call);
        setAttachments(_call);
        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {consultaDatosConfidencialesRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.coomeva.logica.wsempleados.ConsultaDatosConfidencialesResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.coomeva.logica.wsempleados.ConsultaDatosConfidencialesResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.coomeva.logica.wsempleados.ConsultaDatosConfidencialesResponse.class);
            }
        }
    }

    public co.com.coomeva.logica.wsempleados.ConsultaDetalladaResponse consultaDetallada(co.com.coomeva.logica.wsempleados.ConsultaDetalladaRequest consultaDetalladaRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.coomeva.com.co/Empleado", "ConsultaDetallada"));

        setRequestHeaders(_call);
        setAttachments(_call);
        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {consultaDetalladaRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (co.com.coomeva.logica.wsempleados.ConsultaDetalladaResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (co.com.coomeva.logica.wsempleados.ConsultaDetalladaResponse) org.apache.axis.utils.JavaUtils.convert(_resp, co.com.coomeva.logica.wsempleados.ConsultaDetalladaResponse.class);
            }
        }
    }

}
