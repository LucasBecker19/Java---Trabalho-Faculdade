package Model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Cidade {
    private int id;
    private String nome;
    private String uf;

    //CONSTRUTORES
    public Cidade() {
    }

    public Cidade(int id, String nome, String uf) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
    }

    //GETTERS E SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    //TO STRING
    @Override
    public String toString() {
        return "Cidade(" +
                "ID " + id +
                ", " + nome+
                ", " + uf +
                ')';
    }
}
