package com.test;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class ImmutableClass {

    private final int id;

    private final String name;

    private final HashMap<String, String> testMap;

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    /**
     * Accessor function for mutable objects
     */
    public Map<String, String> getTestMap() {

        return (Map<String, String>) testMap.clone();
    }

    /**
     * Constructor performing Deep Copy
     *
     * @param i
     * @param n
     * @param hm
     */

    public ImmutableClass(int i, String n, Map<String, String> hm) {
        System.out.println("Performing Deep Copy for Object initialization");
        this.id = i;
        this.name = n;
        Map<String, String> tempMap = new HashMap<String, String>();
        String key;

        if (hm != null) {
            Iterator<String> it = hm.keySet().iterator();
            while (it.hasNext()) {
                key = it.next();
                tempMap.put(key, hm.get(key));
            }
        }
        this.testMap = (HashMap<String, String>) tempMap;
    }


}
