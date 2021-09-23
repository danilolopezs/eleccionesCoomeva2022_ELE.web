package co.com.coomeva.util.collection;

import java.util.Comparator;

public class CompNumber implements Comparator {

	public CompNumber() {
		// TODO Auto-generated constructor stub
	}

	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		int salida = o1.toString().compareToIgnoreCase(o2.toString());
		Long o1L = null,o2L = null;
		Integer o1I = null,o2I = null;
		Float o1F = null,o2F = null;
		Double o1D = null,o2D = null;
		if (o1 instanceof Long) {
			o1L = (Long)o1;
		}
		
		if (o1 instanceof Integer) {
			o1I = (Integer)o1;
		}
		
		if (o1 instanceof Float) {
			o1F = (Float)o1;
		}
		
		if (o1 instanceof Double) {
			o1D = (Double)o1;
		}		
		
		if (o2 instanceof Long) {
			o2L = (Long)o2;
			salida = o1L.compareTo(o2L);			
		}
		
		if (o2 instanceof Integer) {
			o2I = (Integer)o2;
			salida = o1I.compareTo(o2I);
		}
		
		if (o2 instanceof Float) {		
			o2F = (Float)o2;
			salida = o1F.compareTo(o2F);
		}
		
		if (o2 instanceof Double) {
			o2D = (Double)o2;
			salida = o1D.compareTo(o2D);
		}			

		
		return salida;
	}

}
