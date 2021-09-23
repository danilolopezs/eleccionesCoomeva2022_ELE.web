/*
 * ArrayListCollection.java
 *
 * Created on 12 de agosto de 2004, 05:29 PM
 */

package co.com.coomeva.util.collection;
import java.util.*;
/**
 *
 * @author Giovanni Arzayus Mera - UTI Coomeva
 */
public class ArrayListCollection implements co.com.coomeva.util.collection.Collection{
    
    private ArrayList collection;
    
    public ArrayList getCollection() {
		return collection;
	}

	public void setCollection(ArrayList collection) {
		this.collection = collection;
	}

	/** Creates a new instance of ArrayListCollection */
    public ArrayListCollection() {
        collection = new ArrayList();
    }
    
    public void add(Object value) {
        collection.add(value);
    }
    
    public void add(Object key, Object value){
    }
    
    public void add(long key, Object value) {
    }
    
    public void addAll(java.util.Map map) {
    }
    
    public void clear() {
        this.collection.clear();
    }
    
    public boolean containsKey(long key) {
        return this.collection.contains(new Long(key));
    }
    
    public Object find(Object key) {
        return null;
    }
    
    public Object find(long key) {
        return this.collection.get(new Long(key).intValue());
    }
    
    public java.util.Collection getValues() {
        return null;
    }
    
    public  void set(int index,Object obj){
		this.collection.set(index,obj);
    }
	
    
    public java.util.Iterator iterator() {
        return this.collection.iterator();
    }
    
    public java.util.Set keySet() {
        return null;
    }
    
    public void remove(Object key) {
        this.collection.remove(((Integer)key).intValue());
    }
    
    public long size() {
        return this.collection.size();
    }
    
    
    
}
