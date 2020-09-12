package Exceptions;

import javax.swing.*;

public class DepartamentoException extends Exception{
    public DepartamentoException(){
        JOptionPane.showMessageDialog(null,"Algo deu errado");
    }
    public DepartamentoException(String msg){
        super(msg);
    }
}
