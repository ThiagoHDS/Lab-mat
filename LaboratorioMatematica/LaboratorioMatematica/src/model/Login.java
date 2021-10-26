package model;

import exceptions.idInvalidoException;

public class Login {

	private String usuario, senha;
	private int idFuncionario;

	public Login(String usuario, String senha, int idFuncionario) throws Exception {
		if (usuario == null || senha == null)
			throw new NullPointerException();

		if (idFuncionario == 0)
			throw new idInvalidoException();

		this.senha = senha;
		this.usuario = usuario;
		this.idFuncionario = idFuncionario;

	}

	public String getUsename() {
		return usuario;

	}

	public void setUsername(String usuario) {
		this.usuario = usuario;

	}

	public String getSenha() {
		return senha;

	}

	public void setSenha(String senha) {
		this.senha = senha;

	}

	public int getIdFuncionario() {
		return idFuncionario;

	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;

	}

	@Override
	public String toString() {
		return "ID Funcionario: " + this.idFuncionario + "\nUsername: " + this.usuario + "\nSenha: ******";

	}

}
