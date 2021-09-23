package co.com.coomeva.util.literals;

import java.util.StringTokenizer;

/**
 * Calse que permite el manejo de cadenas.
 * @author Giovanni Arzayus Mera. (Se añadio metodo para validar cadenas vacias).*/

public class ManejoDeCadenas extends Object {
	
	public static String filtroCurrency =  ",.0123456789";

    /**
     * Construye una nueva instancia de la clase.
     */
    public ManejoDeCadenas() {
    }

    /**
     * Método que permite truncar una cadena teniendo en cuenta un determinada
     *       posición.
     * @param cadena, string con la cadena a truncar.
     * @param tamagnoCadena, tamaño maximo con la que debe quedar la cadena.
     * @return java.lang.String.
     */

    public static String truncarCadena(String cadena , int tamagnoCadena){
       if(cadena != null){
           if(cadena.length() > tamagnoCadena){
               cadena = cadena.substring(0,tamagnoCadena);
           }
       }

       return cadena;
    }

	/**
	  * Metodo que valida si en una cadena existen espacios en blanco.
	  * @param String  cadena
	  * @return String cadenaStringBuff (cadena concatenada)
	  */
   public static boolean validaCadenaVacia(String cadena) {
		boolean cadenaVacia = false;
	   if(cadena!=null){
		   int tamCadena = cadena.length();
		   int cuentaEspacios = 0;

		   char espacio;

		   for(int i=0;i<tamCadena;i++){
			   espacio = cadena.charAt(i);
			   if(espacio == ' '){
				   cuentaEspacios++;
			   }
		   }
		   if((cuentaEspacios-1) == (tamCadena-1)){
			   cadenaVacia = true;
		   }
	   }
	   else
		   cadenaVacia = true;


	   return cadenaVacia;
   }
  
   /**
   * Metodo que a partir de una cadena y un delimitador crea un arreglo de cadenas.
   * @param String  cadena
   * @return String cadenaStringBuff (cadena concatenada)
   */
	 public static String[] obtenerArregloTokens(String contenido, String delimitador){
		 String cadenaTokens [] = null;
		 int tamArr = 0;
		 int i = 0;
		 try{
			 StringTokenizer tokens=new StringTokenizer(contenido,delimitador);
			 tamArr = tokens.countTokens();
			 if(tamArr >0  )
				 cadenaTokens = new String[tamArr];
				 while(tokens.hasMoreTokens()){
					 if(i<cadenaTokens.length)
						 cadenaTokens[i] = tokens.nextToken();
					 i++;
				 }
		 }catch(Exception ex){
			 System.out.println(ex);
		 }

	 return cadenaTokens;
	 }
	   /**
	   * Metodo que a partir de una cadena y un filtro crea un string con solo los caracteres del filtro
	   * @param String  origen
	   * @param String filtro
	   * @return String destino (cadena concatenada)
	   */
	 public static String filtrarCaracteres(String origen, String filtro){
		 String destino = "";
		 if(filtro == null || filtro.trim().length() == 0)
			 return origen;
		 char[] filtroA = origen.toCharArray();
		 
		 for (char c : filtroA) {
			 String cS = String.valueOf(c);
			 if(filtro.indexOf(cS) > -1){
				 destino += cS;
			 }
		 }		 
		 return destino;
	 }
	   /**
	   * Metodo que a partir de una cadena, una cadena de corte previo, una cadena de corte posterior,
	   * retira estas cadenas de la cadena entrante dejando la información q se necesita.
	   * @param String cadenaOriginal
	   * @param String preCorte
	   * @param String postCorte
	   * @param String delimitadorStrCorte
	   * @return String destino (cadena recortada)
	   */
	 public static String cortadoCadena(String cadenaOriginal,String preCorte,String postCorte,String delimitadorStrCorte){
			 if(cadenaOriginal == null){
				 return cadenaOriginal;
			 }
		 	String cadenaResultado = cadenaOriginal.toString();
			boolean encontrado = false;
			if(delimitadorStrCorte == null){
				delimitadorStrCorte = ";";
			}
			if(preCorte != null && preCorte.trim().length() > 0){
				StringTokenizer tempST = new StringTokenizer(preCorte,delimitadorStrCorte);
				encontrado = false;
				while(tempST.hasMoreTokens() && !encontrado){
					String tokenPre = tempST.nextToken();
					if(cadenaResultado.indexOf(tokenPre) == 0){
						int posPre = cadenaResultado.indexOf(tokenPre)+tokenPre.length();
						encontrado = true;
						cadenaResultado = cadenaResultado.substring(posPre);
					}				
				}
			}
			
			if(postCorte != null && postCorte.trim().length() > 0){
				StringTokenizer tempST = new StringTokenizer(postCorte,delimitadorStrCorte);
				encontrado = false;
				while(tempST.hasMoreTokens() && !encontrado){
					String tokenPre = tempST.nextToken();
					int posPre = cadenaResultado.lastIndexOf(tokenPre);
					if(posPre == (cadenaResultado.length()-tokenPre.length())){						
						encontrado = true;
						cadenaResultado = cadenaResultado.substring(0,posPre);
					}				
				}
			}		 
			return cadenaResultado;
	 }
	 
	 /**
	  * Conversión de texto a texto con todas las palabras con la primera letra en mayuscula
	 * @param origen
	 * @param delimitador
	 * @return string
	 */
	public static String cadenaEnCapital(String origen, String delimitador){
		 
		 	if(origen == null){
		 		return origen;
		 	}
			String tagConversionFA[] = origen.trim().split(delimitador);
			String tagConversionF = "";
			String string2 = "";
			for (String string : tagConversionFA) {
				string2 = string.trim();
				if(string2.length() > 0){
					if(string2.length() > 1){
						tagConversionF += string2.substring(0,1).toUpperCase() + string2.substring(1).toLowerCase();
					}
					else
						tagConversionF +=string2.toLowerCase();
				tagConversionF += " ";
				}
			}
			if(tagConversionF.length() > 0){
				tagConversionF = tagConversionF.trim();
			}		 
		 return tagConversionF;
	 }
	 
}

