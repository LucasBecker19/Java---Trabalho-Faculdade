package Model;

import Exceptions.DepartamentoException;
import Exceptions.ProfessorException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private String nome;
    private String area;
    private List<Professor> listaProfessores = new ArrayList<>();

    //CONSTRUTORES

    public Departamento() {

    }

    public Departamento(String nome, String area) {
        this.nome = nome;
        this.area = area;
    }

    public Departamento(String nome, String area, List<Professor> listaProfessores) {
        this.nome = nome;
        this.area = area;
        this.listaProfessores = listaProfessores;
    }

    public Departamento(String nome, String area, Professor professor) {
        this.nome = nome;
        this.area = area;
        addProfessor(professor);

    }

    //GETTERS E SETTERS

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<Professor> getListaProfessores() {
        return listaProfessores;
    }

    public void setListaProfessores(List<Professor> listaProfessores) {
        this.listaProfessores = listaProfessores;
    }

    //TO STRING
    @Override
    public String toString() {
        return "Departamento(" + "Nome = " + nome + ", Área = " + area + ")";
    }

    //MÉTODOS
    public void addProfessor(Professor professor){
        listaProfessores.add(professor);
        professor.setDepartamento(this);
    }

    public void removeProfessor(Professor professor) throws DepartamentoException {
        listaProfessores.remove(professor);
    }

    public void imprimirListaProfessores() {
        String s="";
        for(Professor professor:listaProfessores){
            RelatorioProfessor relatorio = new RelatorioProfessor();
            s+=relatorio.imprimirFolha(professor,true);
        }
        JOptionPane.showMessageDialog(null,s);
    }

    public Professor getPorNome(String nome) {

        for(Professor professor: listaProfessores){
            if(professor.getNome().equals(nome)){
                return professor;
            }
        }
        return null;
    }

}
