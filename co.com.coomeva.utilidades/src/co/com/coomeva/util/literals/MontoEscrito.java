package co.com.coomeva.util.literals;

public class MontoEscrito {
	
	private static int flag;
	public static long numero;
    public static String moneda;
	public static String importe_parcial;
	public static String num;
	public static String num_letra;
	public static String num_letras;
	public static String num_letram;
	public static String num_letradm;
	public static String num_letracm;
	public static String num_letramm;
	public static String num_letradmm;
	
	public MontoEscrito(){
		numero = 0;
		flag=0;
        moneda="";
	}
	public MontoEscrito(long n){
		numero = n;
		flag=0;
                moneda="";
	}
        
    public MontoEscrito(long n, String m){
            numero = n;
            flag=0;
            moneda=m;
    }
	
	private static String unidad(long numero){
		
		int numeroInt = (int)numero;
		
		switch (numeroInt){
		case 9:
				num = "nueve";
				break;
		case 8:
				num = "ocho";
				break;
		case 7:
				num = "siete";
				break;
		case 6:
				num = "seis";
				break;
		case 5:
				num = "cinco";
				break;
		case 4:
				num = "cuatro";
				break;
		case 3:
				num = "tres";
				break;
		case 2:
				num = "dos";
				break;
		case 1:
				if (flag == 0)
                                    if (!moneda.equals("")) 
					num = "un";
                                    else num = "uno";   
				else 
					num = "un";
				break;
		case 0:
				num = "";
				break;
		}
		return num;
	}
	
	private static String decena(long numero){
	
		if (numero >= 90 && numero <= 99)
		{
			num_letra = "noventa ";
			if (numero > 90)
				num_letra = num_letra.concat("y ").concat(unidad(numero - 90));
		}
		else if (numero >= 80 && numero <= 89)
		{
			num_letra = "ochenta ";
			if (numero > 80)
				num_letra = num_letra.concat("y ").concat(unidad(numero - 80));
		}
		else if (numero >= 70 && numero <= 79)
		{
			num_letra = "setenta ";
			if (numero > 70)
				num_letra = num_letra.concat("y ").concat(unidad(numero - 70));
		}
		else if (numero >= 60 && numero <= 69)
		{
			num_letra = "sesenta ";
			if (numero > 60)
				num_letra = num_letra.concat("y ").concat(unidad(numero - 60));
		}
		else if (numero >= 50 && numero <= 59)
		{
			num_letra = "cincuenta ";
			if (numero > 50)
				num_letra = num_letra.concat("y ").concat(unidad(numero - 50));
		}
		else if (numero >= 40 && numero <= 49)
		{
			num_letra = "cuarenta ";
			if (numero > 40)
				num_letra = num_letra.concat("y ").concat(unidad(numero - 40));
		}
		else if (numero >= 30 && numero <= 39)
		{
			num_letra = "treinta ";
			if (numero > 30)
				num_letra = num_letra.concat("y ").concat(unidad(numero - 30));
		}
		else if (numero >= 20 && numero <= 29)
		{
			if (numero == 20)
				num_letra = "veinte ";
			else
				num_letra = "veinti".concat(unidad(numero - 20));
		}
		else if (numero >= 10 && numero <= 19)
		{	
			int numeroInt = (int)numero; 
			switch (numeroInt){
			case 10:

				num_letra = "diez ";
				break;

			case 11:

				num_letra = "once ";
				break;

			case 12:

				num_letra = "doce ";
				break;

			case 13:

				num_letra = "trece ";
				break;

			case 14:

				num_letra = "catorce ";
				break;

			case 15:
			
				num_letra = "quince ";
				break;
			
			case 16:
			
				num_letra = "dieciseis ";
				break;
			
			case 17:
			
				num_letra = "diecisiete ";
				break;
			
			case 18:
			
				num_letra = "dieciocho ";
				break;
			
			case 19:
			
				num_letra = "diecinueve ";
				break;
			
			}	
		}
		else
			num_letra = unidad(numero);

	return num_letra;
	}	

	private static String centena(long numero){
		if (numero >= 100)
		{
			if (numero >= 900 && numero <= 999)
			{
				num_letra = "novecientos ";
				if (numero > 900)
					num_letra = num_letra.concat(decena(numero - 900));
			}
			else if (numero >= 800 && numero <= 899)
			{
				num_letra = "ochocientos ";
				if (numero > 800)
					num_letra = num_letra.concat(decena(numero - 800));
			}
			else if (numero >= 700 && numero <= 799)
			{
				num_letra = "setecientos ";
				if (numero > 700)
					num_letra = num_letra.concat(decena(numero - 700));
			}
			else if (numero >= 600 && numero <= 699)
			{
				num_letra = "seiscientos ";
				if (numero > 600)
					num_letra = num_letra.concat(decena(numero - 600));
			}
			else if (numero >= 500 && numero <= 599)
			{
				num_letra = "quinientos ";
				if (numero > 500)
					num_letra = num_letra.concat(decena(numero - 500));
			}
			else if (numero >= 400 && numero <= 499)
			{
				num_letra = "cuatrocientos ";
				if (numero > 400)
					num_letra = num_letra.concat(decena(numero - 400));
			}
			else if (numero >= 300 && numero <= 399)
			{
				num_letra = "trescientos ";
				if (numero > 300)
					num_letra = num_letra.concat(decena(numero - 300));
			}
			else if (numero >= 200 && numero <= 299)
			{
				num_letra = "doscientos ";
				if (numero > 200)
					num_letra = num_letra.concat(decena(numero - 200));
			}
			else if (numero >= 100 && numero <= 199)
			{
				if (numero == 100)
					num_letra = "cien ";
				else
					num_letra = "ciento ".concat(decena(numero - 100));
			}
		}
		else
			num_letra = decena(numero);
		
		return num_letra;	
	}	

	private static String miles(long numero){
		if (numero >= 1000 && numero <2000){
			num_letram = ("mil ").concat(centena(numero%1000));
		}
		if (numero >= 2000 && numero <10000){
			flag=1;
			num_letram = unidad(numero/1000).concat(" mil ").concat(centena(numero%1000));
		}
		if (numero < 1000)
			num_letram = centena(numero);
		
		return num_letram;
	}		

	private static String decmiles(long numero){
		if (numero == 10000)
			num_letradm = "diez mil";
		if (numero > 10000 && numero <20000){
			flag=1;
			num_letradm = decena(numero/1000).concat("mil ").concat(centena(numero%1000));		
		}
		if (numero >= 20000 && numero <100000){
			flag=1;
			num_letradm = decena(numero/1000).concat(" mil ").concat(miles(numero%1000));		
		}
		
		
		if (numero < 10000)
			num_letradm = miles(numero);
		
		return num_letradm;
	}		

	private static String cienmiles(long numero){
		if (numero == 100000)
			num_letracm = "cien mil";
		if (numero >= 100000 && numero <1000000){
			flag=1;
			num_letracm = centena(numero/1000).concat(" mil ").concat(centena(numero%1000));		
		}
		if (numero < 100000)
			num_letracm = decmiles(numero);
		return num_letracm;
	}		

	private static String millon(long numero){
		if (numero >= 1000000 && numero <2000000){
			flag=1;
			num_letramm = ("Un millon ").concat(cienmiles(numero%1000000));
		}
		if (numero >= 2000000 && numero <10000000){
			flag=1;
			num_letramm = unidad(numero/1000000).concat(" millones ").concat(cienmiles(numero%1000000));
		}
		if (numero < 1000000)
			num_letramm = cienmiles(numero);
		
		return num_letramm;
	}		
	
	private static String decmillon(long numero){
		if (numero == 10000000)
			num_letradmm = "diez millones";
		if (numero > 10000000 && numero <20000000){
			flag=1;
			num_letradmm = decena(numero/1000000).concat("millones ").concat(cienmiles(numero%1000000));		
		}
		if (numero >= 20000000 && numero <100000000){
			flag=1;
			num_letradmm = decena(numero/1000000).concat(" millones ").concat(millon(numero%1000000));		
		}			
		if (numero < 10000000)
			num_letradmm = millon(numero);
		
		return num_letradmm;
	}		
	
	private static String cienmillon(long numero){
		long millon = 1000000L;
		if (numero == 100*millon)
			num_letracm = "cien millones";
		if (numero >= 100*millon && numero <1000*millon){
			flag=1;
			num_letracm = centena(numero/millon).concat(" millones ").concat(millon(numero%millon));		
		}
		if (numero < 100*millon)
			num_letracm = decmillon(numero);
		return num_letracm;
	}	
	
	private static String milmillon(long numero){
		long millon = 1000000L;
		if (numero == 1000*millon)
			num_letracm = "mil millones";
		if (numero >= 1000*millon && numero <10000*millon){
			flag=1;
			num_letracm = miles(numero/millon).concat(" millones ").concat(millon(numero%millon));		
		}
		if (numero < 1000*millon)
			num_letracm = cienmillon(numero);
		return num_letracm;
	}	
	
	private static String decmilmillon(long numero){
		long millon = 1000000L;
		if (numero == 10000*millon)
			num_letracm = "mil millones";
		if (numero >= 10000*millon && numero <100000*millon){
			flag=1;
			num_letracm = decmiles(numero/millon).concat(" millones ").concat(millon(numero%millon));		
		}
		if (numero < 10000*millon)
			num_letracm = milmillon(numero);
		return num_letracm;
	}	
	private static String cienmilmillon(long numero){
		long millon = 1000000L;
		if (numero == 100000*millon)
			num_letracm = "mil millones";
		if (numero >= 100000*millon && numero <1000000*millon){
			flag=1;
			num_letracm = cienmiles(numero/millon).concat(" millones ").concat(millon(numero%millon));		
		}
		if (numero < 100000*millon)
			num_letracm = decmilmillon(numero);
		return num_letracm;
	}	
	
	public static String convertir(long numero){
		num_letras = cienmilmillon(numero);
		return num_letras;
	} 	
        
    public static String convertir(long numero, String nombreMoneda){
            moneda = nombreMoneda;        
            num_letras = cienmilmillon(numero)+" "+nombreMoneda;
            return num_letras;
    }       

    public static void main(String args[]) {
    	
    	String valores = MontoEscrito.convertir(654755182456L, "Pesos");
    	System.out.println("Valor encontrado:"+valores);
    	
    }
}
