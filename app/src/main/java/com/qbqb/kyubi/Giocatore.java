package com.qbqb.kyubi;

import java.io.Serializable;

public class Giocatore {

    private String nome;
    private Character sesso;
    private Boolean occupato;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSesso(Character sesso) {
        this.sesso = sesso;
    }

    public Character getSesso() {
        return sesso;
    }

    public String getNome() {
        return nome;
    }

    public Giocatore(String n, Character c) {
        nome = n;
        sesso = c;
        occupato = false;
    }

    public void setOccupato(Boolean b) {
        occupato = b;
    }

    public Boolean getOccupato(){
        return occupato;
    }

    public Giocatore() {
        occupato = false;
    }
}
