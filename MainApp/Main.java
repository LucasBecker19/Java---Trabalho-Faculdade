package MainApp;
import Exceptions.DepartamentoException;
import Exceptions.ProfessorException;
import Model.*;

import javax.swing.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws DepartamentoException, ProfessorException {

    	//ENDERECOS - ok
		Cidade floripa = new Cidade(1,"Florianópolis","SC");
		Endereco manoelCoelho = new Endereco("Rua manoel coelho",110,"--",floripa); floripa.addListaEnderecos(manoelCoelho);
		Endereco bonifacio = new Endereco("Rua Bonifácio",250,"--",floripa); floripa.addListaEnderecos(bonifacio);
		Endereco paraiso = new Endereco("Rua paraiso",103,"--",floripa); floripa.addListaEnderecos(paraiso);
		Endereco europa = new Endereco("Rua Europa",56,"--",floripa); floripa.addListaEnderecos(europa);
		Endereco servidao = new Endereco("Rua Servidão",41,"--",floripa); floripa.addListaEnderecos(servidao);
		Endereco itapiranga = new Endereco("Rua Itapiranga",58,"--",floripa); floripa.addListaEnderecos(itapiranga);

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
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		String menu;
		Departamento dep = new Departamento();

		do{
			menu = menu();

			if(menu!="Sair")
				dep = setDepartamento(dass,daltec,damm,dacc,daeln);
			if(menu=="Lista de Endereços")
				//cid = setCidade();

			switch(menu) {
				case "Cadastrar novo professor": addProf(dep); break; //OK
				case "Remover Professor": rmProf(dep); break; //OK
				case "Lista de Professores": printList(dep); break; //OK
				case "Relatório Professor": printProf(dep); break; //OK
				case "Lista de Endereços": listEnderecos(); //NOT YET
				case "Sair": System.exit(0); break; //OK
			}
		}while(menu!="Sair");
	}

	private static void listEnderecos() {

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

		String nome = JOptionPane.showInputDialog(null,"Nome do professor para imprimir folha: ");
		Professor professor = dep.getPorNome(nome);


		if (professor != null){
			relatorio.imprimirFolha(professor,false);
		} else{
			JOptionPane.showMessageDialog(null,"Professor não encontrado");
		}
	}

	private static void printList(Departamento dep) throws ProfessorException {
    	dep.imprimirListaProfessores();
	}

	private static void addProf(Departamento dep) throws ProfessorException{
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

		int input = JOptionPane.showConfirmDialog(null,"Deseja inserir adicional/desconto no salário deste professor?");
		//0==sim, 1==nao, 2==cancelar
		if(input==0){
			professor.setAdicional(Double.parseDouble(JOptionPane.showInputDialog("Digite o valor adicional R$")));
			professor.setDesconto(Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do desconto R$")));
		}
		else{
			professor.setAdicional(0);
			professor.setDesconto(0);
		}
		dep.addProfessor(professor);
		JOptionPane.showMessageDialog(null,"Professor adicionado com sucesso");

	}

	private static void rmProf(Departamento dep) throws DepartamentoException {
		String nome = JOptionPane.showInputDialog("Digite o nome do professor que deseja excluir: ");
		Professor professor = dep.getPorNome(nome);

		if (professor != null){
			dep.removeProfessor(professor);
			JOptionPane.showMessageDialog(null,"Professor removido com sucesso");
		} else{
			JOptionPane.showMessageDialog(null,"Professor não encontrado");
		}

	}
//verificar se lista de enderecos ta vinculada na cidade, ou se ja foi feita
	private static Endereco addEndereco() {
		Endereco endereco = new Endereco();
		endereco.setLogradouro(JOptionPane.showInputDialog("Adicionando novo endereço...\n\nLogradouro"));
		endereco.setNumero(Integer.parseInt(JOptionPane.showInputDialog("Número")));
		endereco.setComplemento(JOptionPane.showInputDialog("Complemento"));
		endereco.setCidade(addCidade());
		JOptionPane.showMessageDialog(null,"Endereço adicionado com sucesso");
		return endereco;
	}

	private static Cidade addCidade(){
		Cidade cidade = new Cidade();
		cidade.setId(Integer.parseInt(JOptionPane.showInputDialog("ID da Cidade")));
		cidade.setNome(JOptionPane.showInputDialog("Nome da Cidade"));
		cidade.setUf(JOptionPane.showInputDialog("UF"));
		JOptionPane.showMessageDialog(null,"Cidade adicionada com sucesso");
		return cidade;
	}

	public static String menu(){
		Object[] abrirMenu = {"Cadastrar novo professor","Remover Professor","Lista de Professores","Relatório Professor","Sair"};
		Object respMenu;

		respMenu = JOptionPane.showInputDialog(null,"Escolha uma opção","Seleção de itens",
				JOptionPane.PLAIN_MESSAGE,null,abrirMenu,"");

		return respMenu.toString();
		}
}
