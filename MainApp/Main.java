package MainApp;

import Exceptions.DepartamentoException;
import Exceptions.ProfessorException;
import Model.*;

import javax.swing.*;
import java.util.List;

public class Main {

	public static void main(String[] args) throws DepartamentoException {

		// ENDERECOS - ok
		Cidade floripa = new Cidade(1, "Florianópolis", "SC");
		Endereco manoelCoelho = new Endereco("Rua Manoel Coelho", 110, "--", floripa);
		Endereco bonifacio = new Endereco("Rua Bonifácio", 250, "--", floripa);
		Endereco paraiso = new Endereco("Rua Paraíso", 103, "--", floripa);
		Endereco europa = new Endereco("Rua Europa", 56, "--", floripa);
		Endereco servidao = new Endereco("Rua Servidão", 41, "--", floripa);
		Endereco itapiranga = new Endereco("Rua Itapiranga", 58, "--", floripa);

		// DEPARTAMENTOS - ok
		Departamento dass = new Departamento("DASS", "Saúde e Serviços");
		Departamento daltec = new Departamento("DALTEC", "Linguagem, Tecnologia, Educação e Ciência");
		Departamento damm = new Departamento("DAMM", "Metal-Mecânica");
		Departamento dacc = new Departamento("DACC", "Construção Civil");
		Departamento daeln = new Departamento("DAELN", "Eletrônica");

		// PROFESSORES - ok
		Professor prof1 = new Concursado(123, "Carlos da Silva", "Graduado", manoelCoelho, dass, 5000, 400);
		Professor prof2 = new Concursado(456, "Carlos Emanuel", "Especialista", bonifacio, dass, 5000, 400);
		Professor prof3 = new Concursado(123, "Manoel Pereira", "Mestre", paraiso, daltec, 5000, 400);
		Professor prof4 = new Concursado(456, "Gustavo Fonseca", "Doutor", europa, damm, 5000, 400);

		Substituto.setValorHoraAula(40);
		Professor prof5 = new Substituto(789, "Lucas Pereira", "Graduado", servidao, dacc, 160);
		Professor prof6 = new Substituto(568, "Francieli Peixoto", "Especialista", itapiranga, daeln, 160);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		String menu;
		Departamento dep = new Departamento();

		do {
			menu = menu();

			if (menu != "Sair")
				dep = setDepartamento(dass, daltec, damm, dacc, daeln);

			switch (menu) {
			case "Cadastrar novo professor":
				addProf(dep);
				break; // OK
			case "Remover Professor":
				rmProf(dep);
				break; // OK
			case "Lista de Professores":
				printList(dep);
				break; // OK
			case "Relatório Professor":
				printProf(dep);
				break; // OK
			case "Sair":
				System.exit(0);
				break; // OK
			}
		} while (menu != "Sair");
	}

	private static Departamento setDepartamento(Departamento dass, Departamento daltec, Departamento damm,
			Departamento dacc, Departamento daeln) {
		Object[] deps = { "DASS", "DALTEC", "DAMM", "DACC", "DAELN" };
		Object escolherDep;
		Departamento dep = new Departamento();
		escolherDep = JOptionPane.showInputDialog(null, "Escolha um departamento", "Seleção de Departamentos",
				JOptionPane.PLAIN_MESSAGE, null, deps, "");

		if ("DASS".equals(escolherDep))
			dep = dass;
		else if ("DALTEC".equals(escolherDep))
			dep = daltec;
		else if ("DAMM".equals(escolherDep))
			dep = damm;
		else if ("DACC".equals(escolherDep))
			dep = dacc;
		else if ("DAELN".equals(escolherDep))
			dep = daeln;

		return dep;
	}

	private static void printProf(Departamento dep) {
		RelatorioProfessor relatorio = new RelatorioProfessor();

		String nome = inputString("Nome do professor para imprimir folha: ");
		Professor professor = dep.getPorNome(nome);

		try {
			if (professor != null) {
				relatorio.imprimirFolha(professor, false);
			} else {
				throw new ProfessorException();
			}
		} catch (ProfessorException exc) {
			JOptionPane.showMessageDialog(null, exc.profNotFound());
		}
	}

	private static void printList(Departamento dep) {
		dep.imprimirListaProfessores();
	}

	private static Number validateNumber(String msg, Class<?> type) {
		Boolean valid;
		Number input = 0;
		do {
			try {
				if (type.equals(Double.class)) {
					input = Double.parseDouble(JOptionPane.showInputDialog(msg));
				}
				if (type.equals(Integer.class)) {
					input = Integer.parseInt(JOptionPane.showInputDialog(msg));
				}
				valid = true;
			} catch (NumberFormatException exc) {
				valid = false;
				JOptionPane.showMessageDialog(null, "Erro: Digite um valor numérico");
			}
		} while (valid == false);
		return input;
	}

	public static double inputDouble(String msg) {

		Number result = validateNumber(msg, Double.class);

		return result.doubleValue();
	}

	private static Integer inputInt(String msg) {
		Number result = validateNumber(msg, Integer.class);

		return result.intValue();
	}

	private static String inputString(String msg) {
		String input;
		Boolean valid;
		do {
			input = JOptionPane.showInputDialog(msg);
			if (input.length() != 0)
				valid = true;
			else {
				JOptionPane.showMessageDialog(null, "Erro: Você precisa preencher este campo");
				valid = false;
			}
		} while (valid == false);
		return input;
	}

	private static void addProf(Departamento dep) {
		Object[] opcoes = { "Concursado", "Substituto" };
		Object resp;
		resp = JOptionPane.showInputDialog(null, "Escolha o Tipo de professor", "Seleção de itens",
				JOptionPane.PLAIN_MESSAGE, null, opcoes, "");

		Object[] abrirTitulacao = { "Graduado", "Especialista", "Mestre", "Doutor" };
		Object titulacao = JOptionPane.showInputDialog(null, "Selecione uma titulação", "Seleção de itens",
				JOptionPane.PLAIN_MESSAGE, null, abrirTitulacao, "");

		Professor professor = null;

		if (resp.toString() == "Concursado")
			professor = new Concursado();
		else if (resp.toString() == "Substituto")
			professor = new Substituto();

		// professor.setMatricula(Integer.parseInt(JOptionPane.showInputDialog("Matrícula")));
		professor.setMatricula(inputInt("Matrícula"));
		professor.setNome(inputString("Nome"));
		professor.setDepartamento(dep);
		professor.setTitulacao(titulacao.toString());
		if (resp.toString() == "Concursado") {
			((Concursado) professor).setSalarioBase(inputDouble("Salário Base (R$)"));
			((Concursado) professor).setPlanoSaude(inputDouble("Plano de Saúde (R$)"));
		} else if (resp.toString() == "Substituto")
			((Substituto) professor).setQtdHorasTrabalhadasMensal(inputInt("Quantidade de Horas Trabalhadas"));

		professor.setEndereco(addEndereco());

		int input = JOptionPane.showConfirmDialog(null,
				"Deseja inserir adicional/desconto no salário deste professor?");
		// 0==sim, 1==nao, 2==cancelar
		if (input == 0) {
			Boolean valid;
			do {
				professor.setAdicional(inputDouble("Digite o valor adicional R$"));
				professor.setDesconto(inputDouble("Digite o valor do desconto R$"));
				try {
					professor.calcularSalario(professor.getAdicional(), professor.getDesconto());
					valid = true;
				} catch (ProfessorException exc) {
					valid = false;
					JOptionPane.showMessageDialog(null, exc.negativeValue());
				}
			} while (valid == false);
		} else {
			professor.setAdicional(0);
			professor.setDesconto(0);
		}

		dep.addProfessor(professor);
		JOptionPane.showMessageDialog(null, "Professor adicionado com sucesso");

	}

	private static void rmProf(Departamento dep) throws DepartamentoException {
		String nome = inputString("Digite o nome do professor que deseja excluir: ");
		Professor professor = dep.getPorNome(nome);

		try {
			if (professor != null) {
				dep.removeProfessor(professor);
				JOptionPane.showMessageDialog(null, "Professor removido com sucesso");
			} else {
				throw new DepartamentoException();
			}
		} catch (DepartamentoException exc) {
			JOptionPane.showMessageDialog(null, exc.profNotFound());
		}

	}

	private static Endereco addEndereco() {
		Endereco endereco = new Endereco();
		endereco.setLogradouro(inputString("Adicionando novo endereço...\n\nLogradouro"));
		endereco.setNumero(inputInt("Número"));
		endereco.setComplemento(inputString("Complemento"));
		JOptionPane.showMessageDialog(null, "Endereço adicionado com sucesso");
		endereco.setCidade(addCidade());
		return endereco;
	}

	private static Cidade addCidade() {
		Cidade cidade = new Cidade();
		cidade.setId(inputInt("Adicionando nova cidade...\n\nID da Cidade"));
		cidade.setNome(inputString("Nome da Cidade"));
		cidade.setUf(inputString("UF"));
		JOptionPane.showMessageDialog(null, "Cidade adicionada com sucesso");
		return cidade;
	}

	public static String menu() {
		Object[] abrirMenu = { "Cadastrar novo professor", "Remover Professor", "Lista de Professores",
				"Relatório Professor", "Sair" };
		Object respMenu;

		respMenu = JOptionPane.showInputDialog(null, "Escolha uma opção", "Seleção de itens", JOptionPane.PLAIN_MESSAGE,
				null, abrirMenu, "");

		return respMenu.toString();
	}

}
