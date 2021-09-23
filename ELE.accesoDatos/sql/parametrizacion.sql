--------------------------------------------------------
- Tipos de parámetro
--------------------------------------------------------
insert into ELECDB.ELE_PARAMETRO_TIPO values (6, 'Fecha máxima de incripción de planchas');
insert into ELECDB.ELE_PARAMETRO values ( 17, 6, '2012-11-11 23:59:59', 'INI REGISTRO', 1, null);
insert into ELECDB.ELE_PARAMETRO values ( 18, 6, '2012-12-30 23:59:59', 'FIN REGISTRO', 1, null);

-- 16/En/2013
insert into ELECDB.ELE_PARAMETRO_TIPO values (8, 'Fecha habilidad para numeración de planchas');
insert into ELECDB.ELE_PARAMETRO values ( 1, 8, '2012-11-11 23:59:59', 'Numeracion1', 1, null);
insert into ELECDB.ELE_PARAMETRO values ( 2, 8, '2013-12-30 23:59:59', 'Numeracion1', 1, null);
insert into ELECDB.ELE_PARAMETRO values ( 3, 8, '2012-11-11 23:59:59', 'Numeracion2', 1, null);
insert into ELECDB.ELE_PARAMETRO values ( 4, 8, '2013-12-30 23:59:59', 'Numeracion2', 1, null);

--------------------------------------------------------
-- Formato de inhabilidad
--------------------------------------------------------
insert into ELECDB.ELE_FORMATO	values (7, 'CO-FT-209', '1');


  delete from "ELECDB"."ELE_FORMATO_PLANCHA" where CONSECUTIVO_PLANCHA in (select CONSECUTIVO_PLANCHA from "ELECDB"."ELE_PLANCHA" where CODIGO_ZONA_ELE = 3);
  delete from "ELECDB"."ELE_ESTADO_PLANCHA" where CONSECUTIVO_PLANCHA in (select CONSECUTIVO_PLANCHA from "ELECDB"."ELE_PLANCHA" where CODIGO_ZONA_ELE = 3);
  delete from "ELECDB"."ELE_PLANCHA" where CODIGO_ZONA_ELE = 3;
  
  