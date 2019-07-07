package com.test;


public class DaoImpl2 implements Dao {

    @Override
    public void insertCustomer(DaoCustBean bean) {
        System.out.println("cust inserted for name= " + bean.getName());

    }

}
