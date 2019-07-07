package com.test;


//create a singleton with an extra check.
//this is the preferred method to create a singleton
public class SingletonThreadSafety {
    private static volatile SingletonThreadSafety instance;
    private String name;
    private static Object mutex = new Object();

    private SingletonThreadSafety() {
    }

    //double check for maximum performance
    public static SingletonThreadSafety getInstance() {
        SingletonThreadSafety result = instance;

        if (result == null) {
            synchronized (mutex) {
                if (result == null) {
                    result = instance = new SingletonThreadSafety();
                }
            }
        }

        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
