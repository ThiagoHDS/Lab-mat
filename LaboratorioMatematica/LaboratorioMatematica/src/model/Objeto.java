package model;

import exceptions.idInvalidoException;

public class Objeto {

	private String nome;
	private int id;
	private String dirManual;
	private boolean disponivel;

	public Objeto(String nome, int id, String manual, boolean disponivel) throws Exception {
		if (nome == null || manual == null)
			throw new NullPointerException();

		if (id == 0)
			throw new idInvalidoException();

		this.dirManual = manual;
		this.nome = nome;
		this.id = id;
		this.disponivel = disponivel;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDirManual() {
		return dirManual;
	}

	public void setDirManual(String dirManual) {
		this.dirManual = dirManual;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	@Override
	public String toString() {
		return "Objeto: " + this.nome + "\nCodigo: " + this.id + "\nManual: " + this.dirManual + "\nDisponivel: "
				+ this.disponivel;
	}

}
