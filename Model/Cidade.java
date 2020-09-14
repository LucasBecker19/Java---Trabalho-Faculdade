package Model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Cidade {
    private int id;
    private String nome;
    private String uf;
    private List<Endereco> listaEnderecos = new ArrayList<>();


    //CONSTRUTORES
    public Cidade() {
    }

    public Cidade(int id, String nome, String uf) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
    }

    public Cidade(int id, String nome, String uf, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
        addListaEnderecos(endereco);
    }

    public Cidade(int id, String nome, String uf, List<Endereco> endereco) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
        this.listaEnderecos = endereco;
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

    public List<Endereco> getEndereco() {
        return listaEnderecos;
    }

    public void setEndereco(List<Endereco> endereco) {
        this.listaEnderecos = endereco;

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

    //MÉTODOS
    public void addListaEnderecos(Endereco endereco){
        listaEnderecos.add(endereco);
    }

}
