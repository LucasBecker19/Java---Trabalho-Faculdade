package Exceptions;

import javax.swing.*;

public class ProfessorException extends Exception {
    public ProfessorException(){
        //eu acredito que posso usar ifs e elses para tratar tipos de excessões diferentes (caso existam).
        JOptionPane.showMessageDialog(null,"Algo deu errado");
    }
    public ProfessorException(String msg){
        super(msg);
    }
}
