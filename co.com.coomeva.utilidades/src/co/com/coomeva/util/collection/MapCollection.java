package co.com.coomeva.util.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapCollection implements co.com.coomeva.util.collection.Collection {
	private Map map;
	
	public MapCollection() {
		map = new HashMap();
 	}
	
	
	public Map getMap() {
		return map;
	}


	public void add(Object key, Object value) {
		map.put(key,value);
	}


	public void add(long key, Object value) {
		map.put(String.valueOf(key),value);
	}

	public void addAll(Map map) {
		// TODO Auto-generated method stub

	}



	public Object find(Object key) {
		return map.get(key);
	}

	public Object find(long key) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection getValues() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean containsKey(long key) {
		// TODO Auto-generated method stub
		return false;
	}

	public void set(int index, Object obj) {
		// TODO Auto-generated method stub

	}


	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public void clear() {
		// TODO Auto-generated method stub

	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	}


	public void add(Object value) {
		// TODO Auto-generated method stub
		
	}


	public void remove(Object key) {
		// TODO Auto-generated method stub
		
	}


	public long size() {
		return map.size();
	}
}
