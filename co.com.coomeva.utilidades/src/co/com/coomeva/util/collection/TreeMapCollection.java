package co.com.coomeva.util.collection;

import java.util.*;

public class TreeMapCollection
    implements co.com.coomeva.util.collection.Collection {

  private TreeMap collection;

  public TreeMapCollection() {
    this.collection = new TreeMap();
  }

  /**
   * <p>Stores a value in the collection</p>
   * @param key Value identifier
   * @param value Value to be stored
   */
  public void add(Object value) {
  
  }

  public void add(Object key, Object value) {
    this.collection.put(key, value);
  }
  
  /**
   * <p>Stores a value in the collection</p>
   * @param key Value identifier
   * @param value Value to be stored
   */
  public void add(long key, Object value) {
    this.collection.put(new Long(key), value);
  }

  public void addAll(Map map) {
    this.collection.putAll(map);
  }

  /**
   * <p>Removes a value from the collection</p>
   * @param key Value identifier
   */
  public void remove(Object key) {
    this.collection.remove(key);
  }

  /**
   * <p>Search a value stored in the collection and return a reference</p>
   * @param key Value identifier
   * @return A reference to the value object
   */
  public Object find(Object key) {
    return this.collection.get(key);
  }

  /**
   * <p>Search a value stored in the collection and return a reference</p>
   * @param key Value identifier
   * @return A reference to the value object
   */
  public Object find(long key) {
    return this.collection.get(new Long(key));
  }

  /**
   * Return values collection of treeMap
   * @return java.util.Collection collection of values contained in this map.
   */
  public java.util.Collection getValues() {
    return collection.values();
  }

  public boolean containsKey(long key) {
    return this.collection.containsKey(new Long(key));
  }

  public long size() {
    return this.collection.size();
  }

  public Iterator iterator() {
    return this.collection.values().iterator();
  }

  public Set keySet() {
    return this.collection.keySet();
  }

  /**
   * Clear Collection
   */
  public void clear() {
    this.collection.clear();
  }

/* (non-Javadoc)
 * @see co.com.coomeva.util.collection.Collection#set(int, java.lang.Object)
 */
public void set(int index, Object obj) {
	this.collection.put(new Long(index),obj);
}

}
