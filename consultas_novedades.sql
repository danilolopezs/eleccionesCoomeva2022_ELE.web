--contar novedades
SELECT COUNT(A.NUMERO_DOCUMENTO) TOTAL_NOVEDADES 
FROM ELECDB.ELEASOCIA A INNER JOIN ELECDB.ELE_NOVEDAD N ON N.CODIGO_ASOCIADO = A.CODIGO_ASOCIADO 
WHERE N.ESTADO_HABILIDAD = '0' AND N.FECHA_REGISTRO <= '2021-09-28' AND A.COD_ZONA_ASO = '7'


--consulta para consulta de zona   
SELECT 
    NUMINT                                                          numInt, 
    NITCLI                                                          nitCli, 
    NOMCLI                                                          nomCli, 
    NOMCL1                                                          nomCl1, 
    CONCAT(ZE.CODIGO_ZONA_ELE, CONCAT('-',ZE.DESCRIPCION_ZONA_ELE)) zonaElectoral,
    (   SELECT 
            DISTINCT CONCAT(AG.CODREG,CONCAT('-',CT.CODNOM))
        FROM 
            SEGURIDAD.PLTAGCORI        AG,
            MULCLIDAT.CLITAB           RG,
            MULCLIDAT.CLITAB           ZN, 
            ELECDB .ELE_ZONA_ELECTORAL ZE, 
            ELECDB.ELE_ZONA            ZI, 
            MULCLIDAT.CLITAB           CT
        WHERE
                ZN.CODTAB = 908                                 AND RG.CODTAB = 907                     
                AND ZN.CODINT = AG.CODZON                       AND ZI.CODIGO_ZONA = AG.CODZON                  
                AND CT.CODTAB = 608                             AND CT.CODINT <> 0
                AND ZE.CODIGO_ZONA_ELE = ZI.CODIGO_ZONA_ELE 
                AND CT.CODINT = AG.CODREG        
                AND ZI.CODIGO_ZONA_ELE = 
                    (   SELECT  ZE.CODIGO_ZONA_ELE 
                        FROM ELECDB.ELEASOCIA A 
                        INNER JOIN ELECDB.ELE_ZONA Z ON  A.COD_ZONA_ASO = Z.CODIGO_ZONA
                        INNER JOIN ELECDB.ELE_ZONA_ELECTORAL ZE ON Z.CODIGO_ZONA_ELE = ZE.CODIGO_ZONA_ELE 
                        WHERE A.NUMERO_DOCUMENTO = C.nitcli 
                    )
    ) regional
                
FROM ELECDB.ELEASOMUL C 
INNER JOIN ELECDB.ELEASOCIA A ON A.NUMERO_DOCUMENTO=C.nitcli
INNER JOIN ELECDB.ELE_ZONA Z ON Z.CODIGO_ZONA=A.COD_ZONA_ASO
INNER JOIN ELECDB.ELE_ZONA_ELECTORAL ZE ON ZE.CODIGO_ZONA_ELE = Z.CODIGO_ZONA_ELE
WHERE NITCLI = 94392852



--consulta que hay que modificar
SELECT 
    DISTINCT * 
FROM 
    (   SELECT 
            A.NUMERO_DOCUMENTO, 
            C.NOMCLI, 
            N.ESTADO_HABILIDAD, 
            N.FECHA_REGISTRO, 
            A.DESC_ZONA_ASO, 
            SUBSTR(C.NOMCL1,(LOCATE('2',C.NOMCL1)+1),(LOCATE('3',C.NOMCL1)-LOCATE('2',C.NOMCL1))-1 ) AS NOMBRE1,
            (   SELECT 
            DISTINCT CONCAT(AG.CODREG,CONCAT('-',CT.CODNOM))
        FROM 
            SEGURIDAD.PLTAGCORI        AG,
            MULCLIDAT.CLITAB           RG,
            MULCLIDAT.CLITAB           ZN, 
            ELECDB .ELE_ZONA_ELECTORAL ZE, 
            ELECDB.ELE_ZONA            ZI, 
            MULCLIDAT.CLITAB           CT
        WHERE
                ZN.CODTAB = 908                                 AND RG.CODTAB = 907                     
                AND ZN.CODINT = AG.CODZON                       AND ZI.CODIGO_ZONA = AG.CODZON                  
                AND CT.CODTAB = 608                             AND CT.CODINT <> 0
                AND ZE.CODIGO_ZONA_ELE = ZI.CODIGO_ZONA_ELE 
                AND CT.CODINT = AG.CODREG        
                AND ZI.CODIGO_ZONA_ELE = 
                    (   SELECT  ZE.CODIGO_ZONA_ELE 
                        FROM ELECDB.ELEASOCIA A 
                        INNER JOIN ELECDB.ELE_ZONA Z ON  A.COD_ZONA_ASO = Z.CODIGO_ZONA
                        INNER JOIN ELECDB.ELE_ZONA_ELECTORAL ZE ON Z.CODIGO_ZONA_ELE = ZE.CODIGO_ZONA_ELE 
                        WHERE A.NUMERO_DOCUMENTO = C.nitcli 
                    )
    ) regional
        FROM ELECDB.ELEASOCIA A 
        INNER JOIN ELECDB.ELE_NOVEDAD N ON N.CODIGO_ASOCIADO = A.CODIGO_ASOCIADO 
        INNER JOIN ELECDB.ELEASOMUL C ON A.CODIGO_ASOCIADO = C.NUMINT 
        WHERE N.ESTADO_HABILIDAD = 0 
        AND N.FECHA_REGISTRO <= '2021-09-29' AND A.COD_ZONA_ASO = 1 
    )AS todo 
ORDER BY 
    todo.NOMCLI ASC
    
    
    
    
    
    
    
    
    
    
     SELECT 
            DISTINCT CONCAT(AG.CODREG,CONCAT('-',CT.CODNOM))
        FROM 
            SEGURIDAD.PLTAGCORI        AG,
            MULCLIDAT.CLITAB           RG,
            MULCLIDAT.CLITAB           ZN, 
            ELECDB .ELE_ZONA_ELECTORAL ZE, 
            ELECDB.ELE_ZONA            ZI, 
            MULCLIDAT.CLITAB           CT
        WHERE
                ZN.CODTAB = 908                                 AND RG.CODTAB = 907                     
                AND ZN.CODINT = AG.CODZON                       AND ZI.CODIGO_ZONA = AG.CODZON                  
                AND CT.CODTAB = 608                             AND CT.CODINT <> 0
                AND ZE.CODIGO_ZONA_ELE = ZI.CODIGO_ZONA_ELE 
                AND CT.CODINT = AG.CODREG        
                AND ZI.CODIGO_ZONA_ELE = 
                    (   SELECT  ZE.CODIGO_ZONA_ELE 
                        FROM ELECDB.ELEASOCIA A 
                        INNER JOIN ELECDB.ELE_ZONA Z ON  A.COD_ZONA_ASO = Z.CODIGO_ZONA
                        INNER JOIN ELECDB.ELE_ZONA_ELECTORAL ZE ON Z.CODIGO_ZONA_ELE = ZE.CODIGO_ZONA_ELE 
                        WHERE A.NUMERO_DOCUMENTO = 94392852 
                    )