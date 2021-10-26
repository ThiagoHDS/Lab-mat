package controller;

import java.util.List;

import dao.UsuarioDao;
import exceptions.ItemNaoEncontradoException;
import model.Usuario;

public class ControllerUsuario {

	private UsuarioDao conexao = new UsuarioDao();

	public ControllerUsuario() {
		conexao = new UsuarioDao();
	}

	public Usuario procurarUsuario(String matricula) throws Exception {
		Usuario usr = conexao.procuraUsuario(matricula);

		if (usr == null)
			throw new ItemNaoEncontradoException();

		return usr;

	}

	public boolean editarUsuario(Usuario usuario) {
		return conexao.editarUsuario(usuario);

	}

	public boolean removerUsuario(Usuario usuario) {
		return conexao.removerUsuario(usuario.getMatricula());
	}

	public boolean cadastrarUsuario(Usuario usuario) {
		return conexao.cadastraUsuario(usuario);

	}

	public List<Usuario> listarUsuarios() {
		return conexao.geraLista();
	}

}
