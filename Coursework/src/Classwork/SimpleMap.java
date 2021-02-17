package Classwork;

import java.awt.List;
import java.util.Iterator;
import java.util.LinkedList;

public class SimpleMap {

}

public interface SimpleMap<K, V>{
	public V put(K key, V value);
	public V get(K key);
	public V remove(K key);
	public boolean containsKey(K key);
	public int size();
	public Iterator<K> keyIterator();
}

class ListBasedMap<K, V> implements SimpleMap<K, V>{
	private List<MapEntry> list = new LinkedList<MapEntry>();
	
	private class MapEntry{
		public K key;
		public V value;
		
		public MapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
	
	public V put(K key, V value) {
		if(key == null) {
			throw new IllegalArgumentException();
		}
		
		for(MapEntry entry: list) {
			if(entry.key.equals(key)) {
				V ret = entry.value;
				entry.value = value;
				return ret;
			}
		}
		
		list.add(new MapEntry(key,value));
		return null;
	}
	
	public Iterator<K> keyIterator(){
		return new KeyIterator();
	}
	
	private class KeyIterator implements Iterator<K>{
		private Iterator<MapEntry> iter = list.iterator();
		
		@Override
		public boolean hasNext() {
			return iter.hasNext();
		}
		
		@Override
		public K next() {
			return iter.hasNext().key;
		}
		
		@Override
		public void remove() {
			iter.remove();
		}
	}
}