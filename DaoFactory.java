package com.test;


public class DaoFactory {
    public static Dao getInstance() {
        return new DaoImpl();
    }


}
