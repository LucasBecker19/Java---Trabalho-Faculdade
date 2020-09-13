package MainApp;
import Exceptions.DepartamentoException;
import Exceptions.ProfessorException;
import Model.*;

import javax.swing.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws DepartamentoException, ProfessorException {

    	//MAIN DE TESTES

    	//ENDERECOS - ok
		Cidade floripa = new Cidade(1,"Florianópolis","SC");
		Endereco manoelCoelho = new Endereco("Rua manoel coelho",110,"--",floripa);
		Endereco bonifacio = new Endereco("Rua Bonifácio",250,"--",floripa);
		Endereco paraiso = new Endereco("Rua paraiso",103,"--",floripa);
		Endereco europa = new Endereco("Rua Europa",56,"--",floripa);
		Endereco servidao = new Endereco("Rua Servidão",41,"--",floripa);
		Endereco itapiranga = new Endereco("Rua Itapiranga",58,"--",floripa);

		//DEPARTAMENTOS - ok
		Departamento dass = new Departamento("DASS","Saúde e Serviços");
		Departamento daltec = new Departamento("DALTEC","Linguagem, Tecnologia, Educação e Ciência");
		Departamento damm = new Departamento("DAMM","Metal-Mecânica");
		Departamento dacc = new Departamento("DACC","Construção Civil");
		Departamento daeln = new Departamento("DAELN","Acadêmico de Eletrônica");

		//PROFESSORES - ok
		Professor prof1 = new Concursado(123,"Carlos da Silva","Graduado", manoelCoelho, dass, 5000, 400);
		Professor prof2 = new Concursado(456,"Carlos Emanuel","Especialista", bonifacio, dass, 5000, 400);
		Professor prof3 = new Concursado(123,"Manoel Pereira","Mestre", paraiso, daltec, 5000, 400);
		Professor prof4 = new Concursado(456,"Gustavo Fonseca","Doutor", europa, damm, 5000, 400);

		Substituto.setValorHoraAula(40);
		Professor prof5 = new Substituto(789,"Lucas Pereira","Graduado", servidao, dacc, 160);
		Professor prof6 = new Substituto(568,"Francieli Peixoto","Especialista", itapiranga, daeln,160);
///////////////////////////////////////////////////////////////////////////////////////////////////////
String menu;
Departamento dep = new Departamento();

		do{
			menu = menu();

			if(menu!="Sair")
				dep = setDepartamento(dass,daltec,damm,dacc,daeln);

			switch(menu) {
				case "Cadastrar novo professor": addProf(dep); break; //OK
				case "Remover Professor": rmProf(dep); break; //OK
				case "Lista de Professores": printList(dep); break; //OK
				case "Relatório Professor": printProf(dep); break; //OK
				case "Calcular Salário": calcularSalario(dep); break; //OK
				case "Sair": System.exit(0); break; //OK
			}
			System.out.println("--------------------------------------------\n");
		}while(menu!="Sair");
	}

	private static void calcularSalario(Departamento dep) throws ProfessorException {
		double adicional,desconto;
    	String nome = JOptionPane.showInputDialog("Nome do professor para calcular salário: " );
		Professor professor = dep.getPorNome(nome);

		if (professor != null){
			int input = JOptionPane.showConfirmDialog(null, "Deseja inserir adicionais ou descontos? ");
			// 0=sim, 1=não, 2=cancelar
			if (input==0) {
				adicional = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor adicional: R$"));
				desconto = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do desconto: R$"));
				JOptionPane.showMessageDialog(null,"Salário: R$"+professor.calcularSalario(adicional,desconto));
			}
			else if (input==1)
				JOptionPane.showMessageDialog(null,"Salário: R$"+professor.calcularSalario());

		} else{
			JOptionPane.showMessageDialog(null,"Professor não encontrado");
		}
	}

	private static Departamento setDepartamento(Departamento dass, Departamento daltec, Departamento damm, Departamento dacc, Departamento daeln) {
		Object[] deps = {"DASS","DALTEC","DAMM","DACC","DAELN"};
		Object escolherDep;
		Departamento dep = new Departamento();
    	escolherDep = JOptionPane.showInputDialog(null,"Escolha um departamento","Seleção de Departamentos",
				JOptionPane.PLAIN_MESSAGE,null,deps,"");

		if ("DASS".equals(escolherDep))
			dep = dass;
		else if("DALTEC".equals(escolherDep))
			dep=daltec;
		else if("DAMM".equals(escolherDep))
			dep=damm;
		else if ("DACC".equals(escolherDep))
			dep=dacc;
		else if ("DAELN".equals(escolherDep))
			dep=daeln;

		return dep;
    }

	private static void printProf(Departamento dep) throws ProfessorException {
    	RelatorioProfessor relatorio = new RelatorioProfessor();

		String nome = JOptionPane.showInputDialog("Nome do professor para imprimir folha: " );
		Professor professor = dep.getPorNome(nome);

		if (professor != null){
			relatorio.imprimirFolha(professor);
		} else{
			JOptionPane.showMessageDialog(null,"Professor não encontrado");
		}
	}

	private static void printList(Departamento dep) throws ProfessorException {
    	dep.imprimirListaProfessores();
	}

	private static void addProf(Departamento dep){
		Object[] opcoes = {"Concursado","Substituto"}; Object resp;
		resp = JOptionPane.showInputDialog(null,"Escolha o Tipo de professor",
				"Seleção de itens", JOptionPane.PLAIN_MESSAGE,null,opcoes,"");

		Object[] abrirTitulacao = {"Graduado","Especialista","Mestre","Doutor"};
		Object titulacao = JOptionPane.showInputDialog(null,"Selecione uma titulação","Seleção de itens",
				JOptionPane.PLAIN_MESSAGE,null,abrirTitulacao,"");

		Professor professor = null;

		if(resp.toString()=="Concursado"){
			professor = new Concursado();
			professor.setMatricula(Integer.parseInt(JOptionPane.showInputDialog("Matrícula")));
			professor.setNome(JOptionPane.showInputDialog("Nome"));
			professor.setDepartamento(dep);
			professor.setTitulacao(titulacao.toString());
			((Concursado)professor).setSalarioBase(Double.parseDouble(JOptionPane.showInputDialog("Salário Base (R$)")));
			((Concursado)professor).setPlanoSaude(Double.parseDouble(JOptionPane.showInputDialog("Plano de Saúde (R$)")));
		}
		else if (resp.toString()=="Substituto") {
			professor = new Substituto();
			professor.setMatricula(Integer.parseInt(JOptionPane.showInputDialog("Matrícula")));
			professor.setNome(JOptionPane.showInputDialog("Nome"));
			professor.setDepartamento(dep);
			professor.setTitulacao(titulacao.toString());
			((Substituto) professor).setQtdHorasTrabalhadasMensal(Double.parseDouble(JOptionPane.showInputDialog("Quantidade de Horas Trabalhadas")));
		}

		professor.setEndereco(addEndereco());
		dep.addProfessor(professor);
	}

	private static void rmProf(Departamento dep) throws DepartamentoException {
		String nome = JOptionPane.showInputDialog("Digite o nome do professor que deseja excluir: ");
		Professor professor = dep.getPorNome(nome);

		if (professor != null){
			dep.removeProfessor(professor);
		} else{
			JOptionPane.showMessageDialog(null,"Professor não encontrado");
		}
	}
//verificar se lista de enderecos ta vinculada na cidade, ou se ja foi feita
	private static Endereco addEndereco() {
		Endereco endereco = new Endereco();
		endereco.setLogradouro(JOptionPane.showInputDialog("Logradouro"));
		endereco.setNumero(Integer.parseInt(JOptionPane.showInputDialog("Número")));
		endereco.setComplemento(JOptionPane.showInputDialog("Complemento"));
		endereco.setCidade(addCidade());
		return endereco;
	}

	private static Cidade addCidade(){
		Cidade cidade = new Cidade();
		cidade.setId(Integer.parseInt(JOptionPane.showInputDialog("ID")));
		cidade.setNome(JOptionPane.showInputDialog("Nome"));
		cidade.setUf(JOptionPane.showInputDialog("UF"));
		return cidade;
	}

	public static String menu(){
		Object[] abrirMenu = {"Cadastrar novo professor","Remover Professor","Lista de Professores","Relatório Professor","Calcular Salário","Sair"};
		Object respMenu;

		respMenu = JOptionPane.showInputDialog(null,"Escolha uma opção","Seleção de itens",
				JOptionPane.PLAIN_MESSAGE,null,abrirMenu,"");

		return respMenu.toString();
		}

}
