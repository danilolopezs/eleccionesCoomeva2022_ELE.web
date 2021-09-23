package co.com.coomeva.util.collection;

import java.util.*;

/**
 * @author Giovanni Arzayus Mera - UTI Coomeva
 * @version 0.1
 */

public interface Collection {

  /**
   * <p>Stores a value in the collection</p>
   * @param key Value identifier
   * @param value Value to be stored
   */
  public void add(Object key, Object value);

  
   public void add(Object value);

  /**
   * <p>Stores a value in the collection</p>
   * @param key Value identifier
   * @param value Value to be stored
   */
  public void add(long key, Object value);

  /**
   * <p>Stores all the values contained in the Map</p>
   * @param map Map
   */
  public void addAll(Map map);

  /**
   * <p>Removes a value from the collection</p>
   * @param key Value identifier
   */
  public void remove(Object key);

  /**
   * <p>Search a value stored in the collection and return a reference</p>
   * @param key Value identifier
   * @return A reference to the value object
   */
  public Object find(Object key);

  /**
   * <p>Search a value stored in the collection and return a reference</p>
   * @param key Value identifier
   * @return A reference to the value object
   */
  public Object find(long key);

  /**
   * Return values collection of treeMap
   * @return java.util.Collection collection of values contained in this map.
   */
  public java.util.Collection getValues();

  public boolean containsKey(long key);
  
  public  void set(int index,Object obj);

  public long size();

  public Iterator iterator();

  public Set keySet();

  /**
   * Clear Collection
   */
  public void clear();

}
