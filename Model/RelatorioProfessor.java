package Model;

import Exceptions.ProfessorException;

import javax.swing.*;

public class RelatorioProfessor {
   public void imprimirFolha(Professor professor) throws ProfessorException {
        try {
            StringBuilder sb = new StringBuilder();

            if (professor instanceof Substituto)
                sb.append("Professor Substituto").append("\n");
            else if (professor instanceof Concursado)
                sb.append("Professor Concursado").append("\n");

            sb.append("Nome: ").append(professor.getNome()).append("\n");
            sb.append("Matrícula: ").append(professor.getMatricula()).append("\n");
            sb.append("Titulação: ").append(professor.getTitulacao()).append("\n");
            sb.append("Departamento: ").append(professor.getDepartamento().getNome()).append("\n");
            sb.append("Endereco: ").append(professor.getEndereco()).append("\n");
            if (professor instanceof Concursado) {
                sb.append("Salário Base: R$").append(((Concursado) professor).getSalarioBase()).append("\n");
                sb.append("Retricuição Titulação: R$").append(((Concursado) professor).getRetribuicaoTitulacao()).append("\n");
                sb.append("Plano de saúde: R$").append(((Concursado) professor).getPlanoSaude()).append("\n");
            } else if (professor instanceof Substituto) {
                sb.append("Valor Hora Aula: R$").append(((Substituto) professor).getValorHoraAula()).append("\n");
                sb.append("Quantidade de horas tabalhadas (mensal)").append(((Substituto) professor).getQtdHorasTrabalhadasMensal()).append("\n");
            }
            sb.append("Salário calculado: ").append(professor.calcularSalario()).append("\n");
            System.out.println(sb.toString());
        } catch (ProfessorException exc){
            throw new ProfessorException(exc.getMessage());//verificar se ta certo
        }
    }
}
