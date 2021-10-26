package model;

public class Funcionario {

	private String nome;
	private int codigo;

	public Funcionario(String nome, int id) throws Exception {
		if (nome == null)
			throw new NullPointerException();

		this.nome = nome;
		this.codigo = id;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int id) {
		this.codigo = id;
	}

	@Override
	public String toString() {
		return "Nome: " + this.nome + "\nCodigo: " + this.codigo;
	}

}
