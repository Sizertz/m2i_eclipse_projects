package balmes.m2i.dao.reflexive;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Test {

	public static void main(String[] args) {
//		testMaps();
//		System.out.println("-----------------------------");
//		testFieldArray();
		System.out.println("-----------------------------");
		testSQLQueryMap("balmes.m2i.dao.reflexive.Point");
		System.out.println("-----------------------------");
		testSQLQueryMap("balmes.m2i.dao.reflexive.Point2");
		System.out.println("-----------------------------");
		testSQLQueryMap("java.util.HashMap");
		System.out.println("-----------------------------");
		testSQLQueryMap("balmes.m2i.dao.reflexive.Personne");
	}

	/**
	 * Tests Utilities.sqlQueryMap
	 * @param fullQualifiedName - parameter fed to tested method
	 */
	private static void testSQLQueryMap(String fullQualifiedName) {
		Map<String, String> queries = Utilities.sqlQueryMap(fullQualifiedName);
		if (queries != null) {
			for (Entry<String, String> e : queries.entrySet()) {
				System.out.println(e.getKey() + " : " + e.getValue());
			}
		} else {
			System.out.println("sqlQueryMap(" + fullQualifiedName + ") = " + queries);
		}
	}

	/**
	 * Tests Utilities.fieldArray
	 */
	public static void testFieldArray() {
		Field[] myFields = Utilities.fieldArray("balmes.m2i.dao.reflexive.Point");
		for (Field f : myFields) {
			System.out.print("Nom : " + f.getName());
			System.out.println(" | Type : " + f.getGenericType());
		}
	}

	/**
	 * A couple of tests of java.util.Map and java.util.HashMap and their methods
	 */
	public static void testMaps() {
		// Map is an interface. It can't be instantiated. HashMap is a concrete class that implements Map.
		Map<String, String> map = new HashMap<>();

		// maps are a Collection of (key,value) pairs
		
		// map.put(key, value) adds a pair into our map 
		map.put("FR", "Paris");
		map.put("GB", "London");
		map.put("IT", "Roma");

		// map.keySet() returns a Set of keys
		// Set is an interface.
		// sets are a Collection of items that are all different
		// a Set is used because all keys in a Map must be different
		System.out.println("List of keys :");
		for (String key : map.keySet())
			System.out.println(key);

		// map.get(key) allows access to values if you provide the key
		System.out.println("List of values through keys :");
		for (String key : map.keySet())
			System.out.println(map.get(key));

		// map.values() returns a Collection of all the values. 
		System.out.println("List of values through values() :");
		for (String value : map.values()) {
			System.out.println(value);
		}

		// map.entrySet() returns Entry<Key, Value> objects that represent the (key,value) pairs
		System.out.println("List of (key, values) through entrySet() :");
		for (Entry<String, String> entry : map.entrySet()) {
			System.out.println("(" + entry.getKey() + ", " + entry.getValue() + ")");
		}

	}

}
