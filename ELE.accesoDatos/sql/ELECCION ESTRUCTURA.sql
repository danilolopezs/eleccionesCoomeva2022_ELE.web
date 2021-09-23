CREATE TABLE ELE_PLANCHAS
(
  NRO_CAB_PLANCHA    VARCHAR2(20 BYTE)          NOT NULL,
  COD_ZONA           VARCHAR2(30 BYTE)          NOT NULL,
  FECHA_INSCRIPCION  DATE                       NOT NULL,
  ESTADO             VARCHAR2(20 BYTE)          NOT NULL,
  NRO_PLANCHA        NUMBER(38),
  DESC_ESTADO        VARCHAR2(200 BYTE)
)

CREATE TABLE ELE_CAB_PLANCHA
(
  NRO_IDENTIFICACION  VARCHAR2(20 BYTE)         NOT NULL,
  PRIMER_NOMBRE       VARCHAR2(100 BYTE)        NOT NULL,
  SEGUNDO_NOMBRE      VARCHAR2(100 BYTE),
  PRIMER_APELLIDO     VARCHAR2(100 BYTE)        NOT NULL,
  SEGUNDO_APELLIDO    VARCHAR2(100 BYTE),
  EDAD                INTEGER                   NOT NULL,
  PROFESION           VARCHAR2(150 BYTE)        NOT NULL,
  EMAIL               VARCHAR2(60 BYTE)         NOT NULL,
  ANTIGUEDAD          INTEGER                   NOT NULL,
  RUTA_IMAGEN         VARCHAR2(200 BYTE),
  OTROS_ESTUDIOS      VARCHAR2(300 BYTE),
  CARGOS_DIRECTIVOS   VARCHAR2(300 BYTE)        NOT NULL
)

CREATE TABLE ELE_PRINCIPALES
(
  NRO_PRI_IDENTIFICACION  VARCHAR2(20 BYTE)     NOT NULL,
  NRO_CAB_PLANCHA         VARCHAR2(20 BYTE)     NOT NULL,
  PRIMER_NOMBRE           VARCHAR2(100 BYTE)    NOT NULL,
  SEGUNDO_NOMBRE          VARCHAR2(100 BYTE),
  PRIMER_APELLIDO         VARCHAR2(100 BYTE)    NOT NULL,
  SEGUNDO_APELLIDO        VARCHAR2(100 BYTE),
  PROFESION               VARCHAR2(150 BYTE)    NOT NULL,
  EMAIL                   VARCHAR2(60 BYTE)     NOT NULL,
  ORDEN                   NUMBER(2)
)

COMMENT ON COLUMN ELE_PRINCIPALES.ORDEN IS 'Orden que se generan los principales';


CREATE TABLE ELE_LOG
(
  CONSECUTIVO       NUMBER(38)                  NOT NULL,
  NRO_CABO_PLANCHA  VARCHAR2(20 BYTE)           NOT NULL,
  TIPOTRANS         VARCHAR2(20 BYTE)           NOT NULL,
  FECHA             DATE                        NOT NULL,
  USUARIO           VARCHAR2(100 BYTE)          NOT NULL,
  DESCRIPCION       VARCHAR2(200 BYTE)          NOT NULL
)

CREATE TABLE ELE_SUPLENTES
(
  NRO_SU_IDENTIFICACION   VARCHAR2(20 BYTE)     NOT NULL,
  NRO_PRI_IDENTIFICACION  VARCHAR2(20 BYTE),
  NRO_CAB_PLANCHA         VARCHAR2(20 BYTE)     NOT NULL,
  PRIMER_NOMBRE           VARCHAR2(100 BYTE)    NOT NULL,
  SEGUNDO_NOMBRE          VARCHAR2(100 BYTE),
  PRIMER_APELLIDO         VARCHAR2(100 BYTE)    NOT NULL,
  SEGUNDO_APELLIDO        VARCHAR2(100 BYTE),
  PROFESION               VARCHAR2(150 BYTE)    NOT NULL,
  EMAIL                   VARCHAR2(60 BYTE)     NOT NULL,
  ORDEN                   NUMBER(2)
)

COMMENT ON COLUMN ELE_SUPLENTES.ORDEN IS 'Orden en que se generan los suplentes';


CREATE TABLE ELE_P_PARAMETROS
(
  CODIGO_PARAMETRO  NUMBER(20)                  NOT NULL,
  NOMBRE_PARAMETRO  VARCHAR2(100 BYTE)          NOT NULL,
  VALOR_PARAMETRO   VARCHAR2(100 BYTE),
  CODIGO_ESTADO     NUMBER(5)                   NOT NULL,
  TIPO_PARAMETRO    VARCHAR2(10 BYTE)           NOT NULL
)

CREATE TABLE ELE_P_VALOR_PARAMETROS
(
  CODIGO_VAL_PARAMETRO  NUMBER(20)              NOT NULL,
  CODIGO_PARAMETRO      NUMBER(20)              NOT NULL,
  NOMBRE_PARAMETRO      VARCHAR2(100 BYTE)      NOT NULL,
  CODIGO_ESTADO         NUMBER(5)               NOT NULL
)

CREATE TABLE ELE_EXPERIENCIA_LABORAL
(
  CONS_EXPERIENCIA    NUMBER(38)                NOT NULL,
  NRO_IDENTIFICACION  VARCHAR2(20 BYTE)         NOT NULL,
  EMPRESA             VARCHAR2(200 BYTE)        NOT NULL,
  CARGO               VARCHAR2(200 BYTE)        NOT NULL
)

CREATE TABLE ELE_INHABILIDADES
(
  CONS_HABILIDAD      NUMBER(38)                NOT NULL,
  NRO_IDENTIFICACION  VARCHAR2(20 BYTE)         NOT NULL,
  INHABILIDAD         VARCHAR2(200 BYTE)        NOT NULL
)

CREATE TABLE ELE_ASESORES
(
  NRO_IDENTIFICACION  VARCHAR2(30 BYTE)         NOT NULL,
  EMPRESA             VARCHAR2(30 BYTE)         NOT NULL,
  RETIRADO            VARCHAR2(30 BYTE)         NOT NULL
)

CREATE TABLE ELE_ZONAS
(
  COD_ZONA         VARCHAR2(30 BYTE)            NOT NULL,
  MAX_PRINCIPALES  NUMBER                       NOT NULL,
  ZON_ESPECIAL     VARCHAR2(30 BYTE)            NOT NULL,
  NOM_ZONA         VARCHAR2(30 BYTE)            NOT NULL
)

CREATE TABLE ELE_ZONAS_FINANCIERO
(
  COD_ZONA_FIN   VARCHAR2(30 BYTE)              NOT NULL,
  COD_ZONA_ELEC  VARCHAR2(30 BYTE)              NOT NULL,
  NOM_ZONA_FIN   VARCHAR2(30 BYTE)              NOT NULL
)

CREATE TABLE ELE_SUBCOMISION
(
  NRO_IDENTIFICACION  VARCHAR2(30 BYTE)         NOT NULL,
  SUBCOMISIONZONA     VARCHAR2(50 BYTE)         NOT NULL
)

ALTER TABLE ELE_PLANCHAS ADD (
  PRIMARY KEY
 (NRO_CAB_PLANCHA));


ALTER TABLE ELE_CAB_PLANCHA ADD (
  PRIMARY KEY
 (NRO_IDENTIFICACION));


ALTER TABLE ELE_PRINCIPALES ADD (
  PRIMARY KEY
 (NRO_PRI_IDENTIFICACION));


ALTER TABLE ELE_LOG ADD (
  PRIMARY KEY
 (CONSECUTIVO));


ALTER TABLE ELE_SUPLENTES ADD (
  PRIMARY KEY
 (NRO_SU_IDENTIFICACION));


ALTER TABLE ELE_P_PARAMETROS ADD (
  PRIMARY KEY
 (CODIGO_PARAMETRO));


ALTER TABLE ELE_P_VALOR_PARAMETROS ADD (
  PRIMARY KEY
 (CODIGO_VAL_PARAMETRO, CODIGO_PARAMETRO));


ALTER TABLE ELE_EXPERIENCIA_LABORAL ADD (
  PRIMARY KEY
 (CONS_EXPERIENCIA, NRO_IDENTIFICACION));


ALTER TABLE ELE_INHABILIDADES ADD (
  PRIMARY KEY
 (CONS_HABILIDAD, NRO_IDENTIFICACION));


ALTER TABLE ELE_ASESORES ADD (
  PRIMARY KEY
 (NRO_IDENTIFICACION));


ALTER TABLE ELE_ZONAS ADD (
  PRIMARY KEY
 (COD_ZONA));


ALTER TABLE ELE_ZONAS_FINANCIERO ADD (
  PRIMARY KEY
 (COD_ZONA_FIN, COD_ZONA_ELEC));


ALTER TABLE ELE_SUBCOMISION ADD (
  PRIMARY KEY
 (NRO_IDENTIFICACION));


ALTER TABLE ELE_PLANCHAS ADD (
  FOREIGN KEY (COD_ZONA) 
 REFERENCES ELE_ZONAS (COD_ZONA));


ALTER TABLE ELE_CAB_PLANCHA ADD (
  FOREIGN KEY (NRO_IDENTIFICACION) 
 REFERENCES ELE_PLANCHAS (NRO_CAB_PLANCHA));


ALTER TABLE ELE_PRINCIPALES ADD (
  FOREIGN KEY (NRO_CAB_PLANCHA) 
 REFERENCES ELE_PLANCHAS (NRO_CAB_PLANCHA));


ALTER TABLE ELE_SUPLENTES ADD (
  FOREIGN KEY (NRO_CAB_PLANCHA) 
 REFERENCES ELE_PLANCHAS (NRO_CAB_PLANCHA));

ALTER TABLE ELE_SUPLENTES ADD (
  FOREIGN KEY (NRO_PRI_IDENTIFICACION) 
 REFERENCES ELE_PRINCIPALES (NRO_PRI_IDENTIFICACION));


ALTER TABLE ELE_P_VALOR_PARAMETROS ADD (
  FOREIGN KEY (CODIGO_PARAMETRO) 
 REFERENCES ELE_P_PARAMETROS (CODIGO_PARAMETRO));


ALTER TABLE ELE_EXPERIENCIA_LABORAL ADD (
  FOREIGN KEY (NRO_IDENTIFICACION) 
 REFERENCES ELE_CAB_PLANCHA (NRO_IDENTIFICACION));
 
-----------------------------------------------------------
-- Log de transacciones b�sicas del asociado.
-----------------------------------------------------------
 
CREATE TABLE ELECDB.ELE_LOG_ASOCIADO
(
  ID   			 	 NUMERIC		     NOT NULL,
  TIPO_TRN	         VARCHAR(30)          NOT NULL,
  IP_TRN	         VARCHAR(30)          NOT NULL,
  FECHA				 TIMESTAMP             NOT NULL,
  NRO_IDENTIFICACION VARCHAR(20)          NOT NULL,          
  DESCRIPCION        VARCHAR(500)
)

ALTER TABLE ELECDB.ELE_LOG_ASOCIADO ADD PRIMARY KEY(ID);

CREATE SEQUENCE ELECDB.ELE_LOG_ASOCIADO_SEQ AS INTEGER
START WITH 1
INCREMENT BY 1
NO ORDER
NO CYCLE
NO MINVALUE
NO MAXVALUE;


--Couciente.
CREATE TABLE ELECDB.ELE_CUOCIENTE_ELECTORAL
(
  ID_REGISTRO		   			 	NUMERIC		     	   NOT NULL,
  PERIODO_ELECTORAL		   			VARCHAR(30)	    	   NOT NULL,
  TOTAL_ASOC_HABILES  		 		DECIMAL(10,4)		   NOT NULL,
  TOTAL_DELEGADOS_ELEGIR	 		DECIMAL(10,4)          NOT NULL,
  TOTAL_DELEGADOS_ESPECIALES		DECIMAL(10,4)          NOT NULL,
  FINAL_TOTAL_DELEGADOS_ELEGIR 		DECIMAL(10,4)          NOT NULL,
  CUOCIENTE_ELECTORAL 				DECIMAL(10,4)          NOT NULL         
);

ALTER TABLE ELECDB.ELE_CUOCIENTE_ELECTORAL ADD PRIMARY KEY(ID_REGISTRO);


CREATE TABLE ELECDB.ELE_CUOCIENTE_REGIONAL
(
  ID_REGISTRO		   			 	NUMERIC		     	   NOT NULL,
  PERIODO_ELECTORAL		   			VARCHAR(30)			   NOT NULL,
  COD_REGIONAL  		 			VARCHAR(30)			   NOT NULL,
  TOTAL_DELEGADOS	 				DECIMAL(10,4)          NOT NULL,
  RELACION_DELEGADOS				DECIMAL(10,4)          NOT NULL
);

ALTER TABLE ELECDB.ELE_CUOCIENTE_REGIONAL ADD PRIMARY KEY(ID_REGISTRO);


CREATE TABLE ELECDB.ELE_CUOCIENTE_DELEGADOS_ZONA
(
  ID_REGISTRO		   			 	NUMERIC		     NOT NULL,
  PERIODO_ELECTORAL		   			VARCHAR(30)			   NOT NULL,
  COD_REGIONAL  		 			VARCHAR(30)			   NOT NULL,
  COD_ZONA	  		 				VARCHAR(30)			   NOT NULL,
  SUMA_HABILES		 				DECIMAL(10,4)          NOT NULL,
  DELEGADOS							DECIMAL(10,4)          NOT NULL,
  DELEGADOS_DIRECTOS				DECIMAL(10,4)          NOT NULL,
  FRACCION							DECIMAL(10,4)          NOT NULL,
  DELEGADOS_RECIDUO					DECIMAL(10,4)          NOT NULL,
  DISTRIBUIDOS_RESTANTES			DECIMAL(10,4)          NOT NULL,
  TOTAL_DELEGADOS_ZONA				DECIMAL(10,4)          NOT NULL
);

ALTER TABLE ELECDB.ELE_CUOCIENTE_DELEGADOS_ZONA ADD PRIMARY KEY(ID_REGISTRO);

CREATE SEQUENCE ELECDB.ELE_CUOCIENTE_DELEGADOS_ZONA_SEQ AS INTEGER
START WITH 1
INCREMENT BY 1
NO ORDER
NO CYCLE
NO MINVALUE
NO MAXVALUE;

CREATE SEQUENCE ELECDB.ELE_ELECTORAL_ZONA_SEQ AS INTEGER
START WITH 1
INCREMENT BY 1
NO ORDER
NO CYCLE
NO MINVALUE
NO MAXVALUE;

CREATE SEQUENCE ELECDB.ELE_CUOCIENTE_ELECTORAL_SEQ AS INTEGER
START WITH 1
INCREMENT BY 1
NO ORDER
NO CYCLE
NO MINVALUE
NO MAXVALUE;

CREATE SEQUENCE ELECDB.ELE_CUOCIENTE_DELEGADOS_ZONA_SEQ AS INTEGER
START WITH 1
INCREMENT BY 1
NO ORDER
NO CYCLE
NO MINVALUE
NO MAXVALUE;
