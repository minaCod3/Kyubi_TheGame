package com.qbqb.kyubi;

public class Domanda {

    //ATTRIBUTI
    private String testo;

    //Costr default
    public void Domanda(){}

    //Costr generico
    public void Domanda(String t){
        testo = t;
    }

    //Get & Set
    public void setTesto(String t){
        testo = t;
    }

    public String getTesto() {
        return testo;
    }
}
