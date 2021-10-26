package controller;

import java.util.List;

import dao.FuncionarioDao;
import exceptions.ItemNaoEncontradoException;
import model.Funcionario;

public class ControllerFuncionario {

	private FuncionarioDao conexao;

	public ControllerFuncionario() {
		conexao = new FuncionarioDao();
	}

	public boolean cadastrarFuncionario(Funcionario funcionario) {
		return conexao.cadastrarFuncionario(funcionario);
	}

	public boolean removerFuncionario(Funcionario funcionario) {
		return conexao.removerFuncionario(funcionario.getCodigo());
	}

	public boolean editarFuncionario(Funcionario funcionario) {
		return conexao.editarFuncionario(funcionario);
	}

	public Funcionario procurarFuncionario(int id) throws Exception {
		Funcionario fun = conexao.procurarFuncionario(id);

		if (fun == null)
			throw new ItemNaoEncontradoException();

		return fun;
	}

	public List<Funcionario> listarFuncionarios() {
		return conexao.geraLista();
	}

}
