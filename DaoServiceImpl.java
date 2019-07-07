package com.test;


public class DaoServiceImpl implements DaoService {
    private Dao dao;

    public DaoServiceImpl() {

    }


    @Override
    public Dao getDao() {
        return dao;
    }

    @Override
    public void setDao(Dao dao) {
        this.dao = dao;

    }


}
