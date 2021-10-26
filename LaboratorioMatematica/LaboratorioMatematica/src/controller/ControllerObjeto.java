package controller;

import java.util.List;

import dao.ObjetoDao;
import exceptions.ItemNaoEncontradoException;
import model.Objeto;
import model.Usuario;

public class ControllerObjeto {

	private ObjetoDao conexao;

	public ControllerObjeto() {
		conexao = new ObjetoDao();
	}

	public boolean cadastrarObjeto(Objeto obj) {
		return conexao.cadastrarObjeto(obj);
	}

	public Objeto procurarObjeto(int id) throws Exception {
		Objeto obj = conexao.procuraObjeto(id);

		if (obj == null)
			throw new ItemNaoEncontradoException();

		return obj;
	}

	public boolean editarObjeto(Objeto obj) {
		return conexao.editarObjeto(obj);
	}

	public boolean removerObjeto(Objeto obj) {
		return conexao.removerObjeto(obj);
	}

	// falta implementar
	public boolean reservarObjeto(Usuario usuario) {
		return true;
	}

	public boolean mudaStatus(int id, boolean status) {
		return conexao.mudaStatus(id, status);
	}

	public List<Objeto> listarObjetos() {
		return conexao.geraLista();
	}

}
