package com.test;

import java.io.IOException;

public class Airplane {

    public Airplane() throws IOException {
        System.out.print("airplane");
        throw new IOException();
    }
}

class AirJet extends Airplane {
    public AirJet() throws IOException {
    }
}