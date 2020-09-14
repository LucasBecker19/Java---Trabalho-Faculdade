package Model;

import Exceptions.ProfessorException;

import javax.swing.*;

public class Concursado extends Professor {
    private double salarioBase;
    private double retribuicaoTitulacao;
    private double planoSaude;

    private double calcularRetribuicao() throws ProfessorException{
        if(this.getTitulacao().equals("Graduado"))
            this.setRetribuicaoTitulacao(this.salarioBase*0);

        else if(this.getTitulacao().equals("Especialista"))
            this.setRetribuicaoTitulacao(this.salarioBase*0.1);

        else if(this.getTitulacao().equals("Mestre"))
            this.setRetribuicaoTitulacao(this.salarioBase*0.15);

        else if(this.getTitulacao().equals("Doutor"))
            this.setRetribuicaoTitulacao(this.salarioBase*0.4);

            return retribuicaoTitulacao;
    }

    //CONSTRUTORES
    public Concursado() {
    }

    public Concursado(int matricula,String nome,String titulacao, Endereco endereco, Departamento departamento, double salarioBase, double planoSaude) throws ProfessorException {
        this.setMatricula(matricula);
        this.setNome(nome);
        this.setTitulacao(titulacao);
        this.setEndereco(endereco);
        this.setDepartamento(departamento);
        this.salarioBase = salarioBase;
        this.retribuicaoTitulacao = calcularRetribuicao(); //isso pode dar mt problema eu acho
        this.planoSaude = planoSaude;
        this.getDepartamento().addProfessor(this);
    }

    //GETTERS E SETTERS
    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public double getRetribuicaoTitulacao() {
        return retribuicaoTitulacao;
    }

    public void setRetribuicaoTitulacao(double retribuicaoTitulacao) {
        this.retribuicaoTitulacao = retribuicaoTitulacao;
    }

    public double getPlanoSaude() {
        return planoSaude;
    }

    public void setPlanoSaude(double planoSaude) {
        this.planoSaude = planoSaude;
    }

    //MÉTODOS
    @Override
    public double calcularSalario(double adicional, double descontos) throws ProfessorException {
        return this.salarioBase + this.calcularRetribuicao() + this.planoSaude + adicional - descontos;
    }

    @Override
    public double calcularSalario() throws ProfessorException {
        return this.salarioBase + this.calcularRetribuicao() + this.planoSaude;
    }
}
