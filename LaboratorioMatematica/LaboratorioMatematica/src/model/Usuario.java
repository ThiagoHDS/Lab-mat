package model;

public class Usuario {

	private String nome, matricula;

	public Usuario(String nome, String matricula) throws Exception {
		if (nome == null || matricula == null)
			throw new NullPointerException();

		this.nome = nome;
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMatricula() {
		return this.matricula;
	}

	@Override
	public String toString() {
		return "Nome: " + this.getNome() + "\nMatricula: " + this.getMatricula();
	}

}
