package controller;

import dao.LoginDao;
import model.Login;

public class ControllerLogin {

	private LoginDao conexao;

	public ControllerLogin() {
		conexao = new LoginDao();
	}

	public boolean realizarlogin(String usuario, String senha) {
		return conexao.autorizarLogin(usuario, senha);

	}

	public Login procurarLogin(int id) {
		return conexao.procurarLogin(id);
	}

	public void cadastrarLogin(Login login) throws Exception {
		conexao.cadastrarLogin(login);

	}

	public boolean editarSenha(Login login) {
		return conexao.editarSenha(login);
	}

	public void removerLogin(Login login) {
		conexao.removerLogin(login.getIdFuncionario());

	}

}
