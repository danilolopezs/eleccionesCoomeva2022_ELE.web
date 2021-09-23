package co.com.coomeva.ele.util;

public class WorkStrigs 
{
	public  WorkStrigs()
	{
	}
	 public static String getValue(String string, String token1, String token2)
	    {
	        StringBuffer sb = new StringBuffer();
	        String returnString = null;
	        sb.append(string);
	        if(token1.equals("0"))
	            returnString = sb.substring(0, string.indexOf(token2));
	        else
	            returnString = sb.substring(string.indexOf(token1) + 1, string.indexOf(token2));
	        return returnString;
	    }
	 
	 public static String getMes(int mesInt)
	 {
		String mes = "";
		switch (mesInt) {
			case 0:
				mes = "ENERO";
				break;
			case 1:
				mes = "FEBRERO";
				break;
			case 2:
				mes = "MARZO";
				break;
			case 3:
				mes = "ABRIL";
				break;
			case 4:
				mes = "MAYO";
				break;
			case 5:
				mes = "JUNIO";
				break;
			case 6:
				mes = "JULIO";
				break;
			case 7:
				mes = "AGOSTO";
				break;
			case 8:
				mes = "SEPTIEMBRE";
				break;
			case 9:
				mes = "OCTUBRE";
				break;
			case 10:
				mes = "NOVIEMBRE";
				break;
			case 11:
				mes = "DICIEMBRE";
				break;
			default:
				break;
		}
		 return mes;
	 }
	 
	 public static String getAnio(int anio)
	 {
		 Long anioinicio = 1900l;
		 anioinicio = anioinicio + anio;
		 return anioinicio.toString();
	 }
}
