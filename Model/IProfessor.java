package Model;

import Exceptions.ProfessorException;

public interface IProfessor {
    public double calcularSalario(double adicional, double descontos) throws ProfessorException;
    public double calcularSalario() throws ProfessorException;


    //Perguntas...
    //precisa do abstract?

}
