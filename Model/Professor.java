package Model;

import Exceptions.ProfessorException;

public abstract class Professor implements IProfessor{

    private int matricula;
    private String nome;
    private String titulacao;
    private Endereco endereco;
    private Departamento departamento;
    private double desconto;
    private double adicional;

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getAdicional() {
        return adicional;
    }

    public void setAdicional(double adicional) {
        this.adicional = adicional;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    //TO STRING
    @Override
    public String toString() {
        return "\nProfessor{" +
                "matricula=" + matricula +
                ", nome='" + nome + '\'' +
                ", titulacao='" + titulacao + '\'' +
                ", endereco=" + endereco +
                ", departamento=" + departamento +
                '}';
    }

    @Override
    public abstract double calcularSalario(double adicional, double descontos) throws ProfessorException;

    @Override
    public abstract double calcularSalario() throws ProfessorException;
}
