package Exceptions;

import javax.swing.*;

public class DepartamentoException extends Exception{
    public DepartamentoException(){ }

    public String profNotFound(){
        return "Erro: Professor não encontrado";
    }
}
