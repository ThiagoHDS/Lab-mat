package controller;

import java.util.List;

import dao.EmprestimoDao;
import exceptions.ItemNaoEncontradoException;
import model.Emprestimo;

public class ControllerEmprestimo {

	private EmprestimoDao conexao;

	public ControllerEmprestimo() {
		conexao = new EmprestimoDao();
	}

	public boolean realizaEmprestimo(Emprestimo emp, ControllerObjeto gen) {
		conexao.cadastrarEmprestimo(emp);
		gen.mudaStatus(emp.getIdObjeto(), false);

		return true;
	}

	public double finalizarEmprestimo(Emprestimo emp, ControllerObjeto gen) {

		conexao.removerEmprestimo(emp);
		gen.mudaStatus(emp.getIdObjeto(), true);

		return emp.calcularMulta();
	}

	public boolean editarEmprestimo(Emprestimo emp) {
		return conexao.editarEmprestimo(emp);
	}

	public List<Emprestimo> listarEmprestimos() {
		return conexao.gerarLista();
	}

	public Emprestimo procurarEmprestimo(int id) throws Exception {
		Emprestimo emp = conexao.procuraEmprestimo(id);
		if (emp == null)
			throw new ItemNaoEncontradoException();

		return emp;
	}

}
