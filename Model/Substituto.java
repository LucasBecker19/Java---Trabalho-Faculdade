package Model;

import Exceptions.ProfessorException;

import javax.swing.*;

public class Substituto extends Professor{
    private static double valorHoraAula;
    private double qtdHorasTrabalhadasMensal;

    //CONSTRUTORES
    public Substituto() {
    }

    public Substituto(int matricula, String nome, String titulacao, Endereco endereco, Departamento departamento, double qtdHorasTrabalhadasMensal) {
        this.setMatricula(matricula);
        this.setNome(nome);
        this.setTitulacao(titulacao);
        this.setEndereco(endereco);
        this.setDepartamento(departamento);
        this.qtdHorasTrabalhadasMensal = qtdHorasTrabalhadasMensal;
        this.getDepartamento().addProfessor(this);
    }

    //GETTERS E SETTERS
    public static double getValorHoraAula() {
        return valorHoraAula;
    }

    public static void setValorHoraAula(double valorHoraAula) {
        Substituto.valorHoraAula = valorHoraAula;
    }

    public double getQtdHorasTrabalhadasMensal() {
        return qtdHorasTrabalhadasMensal;
    }

    public void setQtdHorasTrabalhadasMensal(double qtdHorasTrabalhadasMensal) {
        this.qtdHorasTrabalhadasMensal = qtdHorasTrabalhadasMensal;
    }

    //MÉTODOS
    @Override
    public double calcularSalario(double adicional, double descontos) throws ProfessorException {
        if(adicional<0 || descontos<0){
            throw new ProfessorException();
        }
        return (qtdHorasTrabalhadasMensal * valorHoraAula) + adicional - descontos;
    }

    @Override
    public double calcularSalario() throws ProfessorException {
        return qtdHorasTrabalhadasMensal * valorHoraAula;
    }
}
