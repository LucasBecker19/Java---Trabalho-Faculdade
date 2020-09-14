package Exceptions;

import javax.swing.*;

public class ProfessorException extends Exception {
    public ProfessorException(){ }

    public String negativeValue(){
        return "Erro: Valor não pode ser negativo";
    }
}
