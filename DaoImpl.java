package com.test;


public class DaoImpl implements Dao {

    @Override
    public void insertCustomer(DaoCustBean bean) {
        System.out.println("cust inserted 2 for " + bean.getName());

    }

}
